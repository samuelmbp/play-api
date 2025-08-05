package contract

import play.api.mvc._
import play.api.libs.json.{Json}
import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext

@Singleton
class ContractController @Inject()
  (cc: ControllerComponents, contractService: ContractService)
  (implicit  ec: ExecutionContext) extends AbstractController(cc) {

  def getAllContracts: Action[AnyContent] = Action.async {
    contractService.getAllContracts().map {
      contracts => Ok(Json.toJson(contracts))
    }
  }
}
