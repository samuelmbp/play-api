package contract

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
}
