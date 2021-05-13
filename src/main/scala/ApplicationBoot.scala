import akka.actor.ActorSystem
import scala.concurrent.ExecutionContext
import services.NumberConverterService
import http.HttpRoute
import akka.http.scaladsl.Http
import scala.io.StdIn

object ApplicationBoot extends App {
  def startApplication(): Unit = {
    implicit val actorSystem: ActorSystem = ActorSystem()
    implicit val executor: ExecutionContext = actorSystem.dispatcher

    val numberConverterService = new NumberConverterService()

    val httpRoute = new HttpRoute(numberConverterService)

    val bindingFuture =
      Http().newServerAt("localhost", 8080).bind(httpRoute.route)

    println(s"Server online at http://localhost:8080/")
    println("Press Enter to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => actorSystem.terminate()) // and shutdown when done
  }

  startApplication()

}
