package employee

import play.api.libs.json._

case class EmployeeResponse(id: Long, firstName: String, lastName: String, email: String, mobileNumber: String, address: String)
object EmployeeResponse {
  implicit val format: OFormat[EmployeeResponse] = Json.format[EmployeeResponse]

  def fromModel(model: Employee): EmployeeResponse = {
    EmployeeResponse(
      id = model.id.getOrElse(0L),
      firstName = model.firstName,
      lastName = model.lastName,
      email = model.email,
      mobileNumber = model.mobileNumber,
      address = model.address
    )
  }
}

case class CreateEmployeeDto(firstName: String, lastName: String, email: String, mobileNumber: String, address: String)
object CreateEmployeeDto {
  implicit val reads: Reads[CreateEmployeeDto] = Json.reads[CreateEmployeeDto]
}

