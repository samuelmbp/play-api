package utils

import slick.jdbc.JdbcProfile
import play.api.db.slick.DatabaseConfigProvider
import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}
import java.sql.Timestamp
import java.time.LocalDateTime

import employee.Table.employees
import employee.Employee

import contract.Table.contracts
import contract.Contract

@Singleton
class DataSeeder @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
  private val dbConfig = dbConfigProvider.get[JdbcProfile]
  import dbConfig._
  import profile.api._

  def seed(): Future[Unit] = {
    val now = Timestamp.valueOf(LocalDateTime.now())

    val initialEmployees = Seq(
      Employee(None, "Alice", "Smith", "alice@example.com", "1234567890", "123 Main St"),
      Employee(None, "Bob", "Jones", "bob@example.com", "9876543210", "456 Elm St")
    )

    val setup = for {
      employeeExists <- employees.exists.result
      contractExists <- contracts.exists.result

      insertedEmployees <- if (!employeeExists) {
        val insertQuery = employees returning employees.map(_.id) into ((emp, id) => emp.copy(id = Some(id)))
        insertQuery ++= initialEmployees
      } else {
        employees.result
      }

      employeeIdMap = insertedEmployees.map(e => e.firstName -> e.id.get).toMap

      _ <- if (!contractExists) {
        val initialContracts = Seq(
          Contract(None, Some(employeeIdMap("Alice")), "Permanent", "2023-01-01", "Full-Time", Some("2025-12-31"), "40"),
          Contract(None, Some(employeeIdMap("Bob")), "Contract", "2024-03-01", "Part-Time", Some("2024-12-31"), "20")
        )
        contracts ++= initialContracts
      } else {
        DBIO.successful(())
      }

    } yield ()

    db.run(setup.transactionally)
  }
}
