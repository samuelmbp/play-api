package utils

import play.api.{Configuration, Environment}
import play.api.inject.{Binding, Module}
import scala.concurrent.ExecutionContext

class AppModule extends Module {
  override def bindings(env: Environment, config: Configuration): Seq[Binding[_]] = {
    Seq(
      bind[Startup].toSelf.eagerly()
    )
  }
}
