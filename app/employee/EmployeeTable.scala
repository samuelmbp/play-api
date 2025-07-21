package employee;

import slick.jdbc.MySQLProfile.api._

class Employees(tag: Tag) extends Table[Employee](tag, "employees") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def first_name = column[String]("first_name")
    def last_name = column[String]("last_name")
    def email = column[String]("email")
    def mobile_number = column[String]("mobile_number")
    def address = column[String]("address")
    def contract_type = column[String]("contract_type")
    def start_date = column[String]("start_date")
    def full_time_part_time = column[String]("full_time_part_time")
    def end_date = column[String]("end_date")
    def hours_per_week = column[String]("hours_per_week")

    def * = (
      id.?,
      first_name,
      last_name,
      email,
      mobile_number,
      address,
      contract_type,
      start_date,
      full_time_part_time,
      end_date,
      hours_per_week) <> (Employee.tupled, Employee.unapply)
}

object Table {
    val employees = TableQuery[Employees]
}
