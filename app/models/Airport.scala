package models

import play.api.libs.json._

case class Airport(val id: String, val iso_country: String, val name: String, val runways: List[String])

object Airport {
  implicit val jsonFormat = Json.format[Airport]
}
