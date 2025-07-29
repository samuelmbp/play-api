package employee

import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.jdbc.MySQLProfile.api._

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class EmployeeRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {

  private val dbConfig = dbConfigProvider.get[JdbcProfile]
  import dbConfig._

  def findAll(): Future[Seq[Employee]] = {
    db.run(Table.employees.result)
  }

  def findById(id: Long): Future[Option[Employee]] = {
    db.run(Table.employees.filter(_.id === id).result.headOption)
  }
}
