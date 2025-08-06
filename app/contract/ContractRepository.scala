package contract

import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.jdbc.MySQLProfile.api._

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ContractRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
  private val dbConfig = dbConfigProvider.get[JdbcProfile]
  import dbConfig._
  import employee.Table.employees
  import Table.contracts

  def findAllContractsWithEmployeeName(): Future[Seq[(Contract, Option[String])]] = {
    val query = for {
      (contract, employeeOptional) <- contracts
        .joinLeft(employees)
        .on(_.employeeId === _.id)
    } yield (contract, employeeOptional.map(_.firstName))

    db.run(query.result)
  }

  def findContractById(id: Long): Future[Option[(Contract, Option[String])]] = {
    val query = for {
      (contract, employeeOptional) <- contracts
        .joinLeft(employees)
        .on(_.employeeId === _.id)
        .filter(_._1.id === id)
    } yield (contract, employeeOptional.map(_.firstName))

    db.run(query.result.headOption)
  }
}
