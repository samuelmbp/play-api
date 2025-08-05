package contract

import play.api.libs.json._

case class ContractResponse(
   id: Long,
   employee: String,
   contractType: String,
   startDate: String,
   fullTimePartTime: String,
   endDate: Option[String],
   hoursPerWeek: String
 )

object ContractResponse {
  implicit val format: OFormat[ContractResponse] = Json.format[ContractResponse]
  
  def fromModel(contract: Contract, employeeName: Option[String]): ContractResponse = {
    ContractResponse(
      id = contract.id.getOrElse(0L),
      employee = employeeName.getOrElse("Unknown"),
      contractType = contract.contractType,
      startDate = contract.startDate,
      fullTimePartTime = contract.fullTimeParTTime,
      endDate = contract.endDate,
      hoursPerWeek = contract.hoursPerWeek
    )
  }
}