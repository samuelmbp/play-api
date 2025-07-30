package utils.validation

import employee.CreateEmployeeDto

trait Validator {

  def isNonEmpty(fieldName: String, value: String): Option[(String, String)] = {
    if (value.trim.isEmpty) Some(fieldName -> s"$fieldName cannot be empty")
    else None
  }

  def isNonBlankDefined(fieldName: String, value: Option[String]): Option[(String, String)] = {
    value match {
      case Some(v) if v.trim.isEmpty => Some(fieldName -> s"$fieldName cannot be blank is provided.")
      case _ => None
    }
  }
}

object EmployeeValidator extends Validator {
  def validateCreate(dto: CreateEmployeeDto): Map[String, String] = {
    List(
      isNonEmpty("firstName", dto.firstName),
      isNonEmpty("lastName", dto.lastName),
      isNonEmpty("email", dto.email),
      isNonEmpty("mobileNumber", dto.mobileNumber),
      isNonEmpty("address", dto.address)
    ).flatten.toMap
  }
}