package employee

import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.jdbc.MySQLProfile.api._

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

import employee.Table.employees

@Singleton
class EmployeeRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {

  private val dbConfig = dbConfigProvider.get[JdbcProfile]
  import dbConfig._

  def findAll(): Future[Seq[Employee]] = {
    db.run(employees.result)
  }

  def findById(id: Long): Future[Option[Employee]] = {
    db.run(employees.filter(_.id === id).result.headOption)
  }

  def create(employee: Employee): Future[Employee] = {
    val insertQuery = employees returning employees.map(_.id) into ((emp, id) => emp.copy(id = Some(id)))
    db.run(insertQuery += employee)
  }
}
