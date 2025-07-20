import play.api.libs.json._
import slick.jdbc.MySQLProfile.api._
import java.sql.Timestamp

case class Employee(
  id: Option[Long],
  first_name: String,
  last_name: String,
  email: String,
  mobile_number: String,
  address: String,
  contract_type: String,
  start_date: String,
  full_time_part_time: String,
  end_date: String,
  hours_per_week: String
)
