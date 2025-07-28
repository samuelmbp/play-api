package utils

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext
import play.api.inject.ApplicationLifecycle

@Singleton
class Startup @Inject()(dataSeeder: DataSeeder, lifecycle: ApplicationLifecycle)(implicit ec: ExecutionContext) {
  println("Runs on startup")

  dataSeeder.seed().map(_ =>
    println("Database seeding completed.")
  ).recover {
    case ex =>
      println(s"Seeding failed: ${ex.getMessage}")
  }

  lifecycle.addStopHook { () =>
    println("Application shutting down...")
    scala.concurrent.Future.successful(())
  }
}