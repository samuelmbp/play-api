package employee

case class Employee(
   id: Option[Long],
   firstName: String,
   lastName: String,
   email: String,
   mobileNumber: String,
   address: String
)