package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import services.DataService
import play.api.libs.json._

// Move those
case class CountryCount(val country: String, val count: Int)

object CountryCount {
  implicit val jsonFormat = Json.format[CountryCount]
}

import CountryCount._

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
    Ok(views.html.index(s"""Data count check : ${data.nextCount()}"""))
  }

  def queryAirports(iso_country: String) = Action { request =>
    val airports = data.queryAirports(iso_country)
    Ok(Json.toJson(airports))
  }

  def queryCountries(nb: Int) = Action { request =>
    val (top, last) = data.queryCountries(nb)
    val items = top ++ last
    Ok(Json.toJson(items.map(item => new CountryCount(item._1, item._2))))
  }
}
