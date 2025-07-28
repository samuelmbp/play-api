package contract

case class Contract (
  id: Option[Long] = None,
  employeeId: Option[Long],
  contractType: String,
  startDate: String,
  fullTimeParTTime: String,
  endDate: Option[String] = None,
  hoursPerWeek: String,
)