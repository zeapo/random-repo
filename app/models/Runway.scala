package models

import play.api.libs.json._

//"id","airport_ref","airport_ident","length_ft","width_ft","surface","lighted","closed","le_ident","le_latitude_deg","le_longitude_deg","le_elevation_ft","le_heading_degT","le_displaced_threshold_ft","he_ident","he_latitude_deg","he_longitude_deg","he_elevation_ft","he_heading_degT","he_displaced_threshold_ft",
case class Runway(val id: String, val airport_ref: String, val surface: String, val le_ident: String)

object Runway {
  implicit val jsonFormat = Json.format[Runway]
}
