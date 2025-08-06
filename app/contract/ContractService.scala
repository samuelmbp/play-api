package contract

import utils.ApiError

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ContractService @Inject()(contractRepository: ContractRepository)(implicit ec: ExecutionContext) {

  def getAllContracts(): Future[Seq[ContractResponse]] = {
    contractRepository.findAllContractsWithEmployeeName().map { results =>
      results.map {case (contract, employeeName) =>
        ContractResponse.fromModel(contract, employeeName)
      }
    }
  }

  def getContractById(id: Long): Future[Either[ApiError, ContractResponse]] = {
    contractRepository.findContractById(id).map {
      case Some((contract, employeeName)) => Right(ContractResponse.fromModel(contract, employeeName))
      case None => Left(ApiError.NotFound(s"Contract with id $id not found!"))
    }
  }

  def deleteContractById(id: Long): Future[Either[ApiError, Unit]] = {
    contractRepository.deleteContract(id).map { rowsAffected =>
      if (rowsAffected > 0) Right(())
      else Left(ApiError.NotFound(s"Contract with id $id not found!"))
    }
  }
}
