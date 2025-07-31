package employee

import utils.ApiError
import utils.validation.EmployeeValidator

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class EmployeeService @Inject()(employeeRepository: EmployeeRepository)(implicit ec: ExecutionContext) {

  def getAllEmployees(): Future[Seq[EmployeeResponse]] = {
    employeeRepository.findAll().map(_.map(EmployeeResponse.fromModel))
  }

  def getEmployeeById(id: Long): Future[Either[ApiError, EmployeeResponse]] = {
    employeeRepository.findById(id).map {
      case Some(employee) => Right(EmployeeResponse.fromModel(employee))
      case None => Left(ApiError.NotFound(s"Employee with id #$id was not found!"))
    }
  }

  def createEmployee(data: CreateEmployeeDto): Future[Either[ApiError, EmployeeResponse]] = {
    val errors = EmployeeValidator.createValidate(data)
    if (errors.nonEmpty) {
      Future.successful(Left(ApiError.ValidationError(errors)))
    } else {
      val preSavedEmployee = Employee(
        id = None,
        firstName = data.firstName.trim,
        lastName = data.lastName.trim,
        email = data.email.trim,
        mobileNumber = data.mobileNumber.trim,
        address = data.address.trim
      )

      employeeRepository.create(preSavedEmployee).map(saveEmployee => Right(EmployeeResponse.fromModel(saveEmployee)))
    }
  }

  def updateEmployeeById(id: Long, data: UpdateEmployeeDto): Future[Either[ApiError, EmployeeResponse]] = {
    val errors = EmployeeValidator.validatePatch(data)
    if (errors.nonEmpty) {
      Future.successful(Left(ApiError.ValidationError(errors)))
    } else {
      employeeRepository.findById(id).flatMap {
        case None => Future.successful((Left(ApiError.NotFound(s"Employee with id $id was not found!"))))
        case Some(existing) =>
          val updated = existing.copy(
            firstName = data.firstName.map(_.trim).getOrElse(existing.firstName),
            lastName = data.lastName.map(_.trim).getOrElse(existing.lastName),
            email = data.email.map(_.trim).getOrElse(existing.email),
            mobileNumber = data.mobileNumber.map(_.trim).getOrElse(existing.mobileNumber),
            address = data.address.map(_.trim).getOrElse(existing.address)
          )

          employeeRepository.update(updated).map(employee => Right(EmployeeResponse.fromModel(employee)))
      }
    }
  }

  def deleteEmployeeById(id: Long): Future[Either[ApiError, Unit]] = {
    employeeRepository.delete(id).map { rowsAffected =>
      if (rowsAffected > 0) Right(())
      else Left(ApiError.NotFound(s"Employee with id $id not found!"))
    }
  }
}
