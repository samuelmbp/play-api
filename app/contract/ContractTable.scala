package contract

import employee.Employees
import slick.jdbc.MySQLProfile.api._

class Contracts(tag: Tag) extends Table[Contract](tag, "contracts") {
  def id               = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def employeeId       = column[Option[Long]]("employee_id")
  def contractType     = column[String]("contract_type")
  def startDate        = column[String]("start_date")
  def fullTimePartTime = column[String]("full_time_part_time")
  def endDate          = column[Option[String]]("end_date")
  def hoursPerWeek     = column[String]("hours_per_week")

  def employeeFk = foreignKey("fk_employee", employeeId, TableQuery[Employees])(_.id.?, onDelete = ForeignKeyAction.SetNull)

  def * = (
    id.?,
    employeeId,
    contractType,
    startDate,
    fullTimePartTime,
    endDate,
    hoursPerWeek
  ) <> (Contract.tupled, Contract.unapply)
}


object Table {
  val contracts = TableQuery[Contracts]
}