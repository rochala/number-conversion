package http

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import services.NumberConverterService
import scala.concurrent.ExecutionContext
import io.circe.Encoder
import io.circe.syntax._
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport

class HttpRoute(numbersConverterService: NumberConverterService)(implicit executionContext: ExecutionContext) extends FailFastCirceSupport {
  import StatusCodes._

  val route: Route =
    concat(
      pathPrefix("api") {
        path(Segment / IntNumber) { (conversion, value) =>
          pathEndOrSingleSlash {
            get {
              complete(numbersConverterService.convert(value.toInt, conversion).map {
                case Some(result) =>
                  OK -> result.asJson
                case None =>
                  BadRequest -> None.asJson
              })
            }
          }
        }
      },
      pathPrefix("healthcheck") {
        get {
          complete("OK")
        }
      }
    )
}
