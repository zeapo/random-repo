package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import services.DataService
import play.api.libs.json._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class AssessController @Inject() (data: DataService) extends Controller {

  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index = Action {
    Ok(views.html.index(s"""Data count check : ${data.nextCount()}
                            airports : ${data.getAirportsNumber} runways : ${data.getRunwaysNumber};;"""))
  }

  def queryAirports(iso_country: String) = Action { request =>
    val airports = data.queryAirports(iso_country)
    Ok(Json.toJson(airports))
  }
}
