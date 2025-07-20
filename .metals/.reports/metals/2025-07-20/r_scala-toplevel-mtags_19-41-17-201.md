error id: file://<WORKSPACE>/app/employee/EmployeeTable.scala:[709..710) in Input.VirtualFile("file://<WORKSPACE>/app/employee/EmployeeTable.scala", "import slick.jdbc.MySQLProfile.api._
import java.sql.Timestamp

class EmployeeTable extends Table[Employee] ("employee") {
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

    def 
  
}

/* 
class Categories(tag: Tag) extends Table[Category](tag, "categories") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def description = column[Option[String]]("description")
  def createdAt = column[Timestamp]("created_at")
  def updatedAt = column[Timestamp]("updated_at")

  def * = (id.?, name, description, createdAt, updatedAt) <> (Category.tupled, Category.unapply)
}

object Table {
    val categories = TableQuery[Categories]
} */")
file://<WORKSPACE>/file:<WORKSPACE>/app/employee/EmployeeTable.scala
file://<WORKSPACE>/app/employee/EmployeeTable.scala:19: error: expected identifier; obtained rbrace
}
^
#### Short summary: 

expected identifier; obtained rbrace