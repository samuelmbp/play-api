package employee;

import slick.jdbc.MySQLProfile.api._

class Employees(tag: Tag) extends Table[Employee](tag, "employees") {
    def id           = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def firstName    = column[String]("first_name")
    def lastName     = column[String]("last_name")
    def email        = column[String]("email")
    def mobileNumber = column[String]("mobile_number")
    def address      = column[String]("address")

    def * = (
      id.?,
      firstName,
      lastName,
      email,
      mobileNumber,
      address
      ) <> (Employee.tupled, Employee.unapply)
}

object Table {
    val employees = TableQuery[Employees]
}
