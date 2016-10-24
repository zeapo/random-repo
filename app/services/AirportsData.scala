package services

import java.io.File

import models.Airport

/**
  *
  * Note: I use a `get` in all cases as I suppose that the targeted values are non-empty
  */
class AirportsData(dataFile: File) extends EntityData(dataFile) {
  def listAirports(country: String) = {
    data.filter(row => {
                  val rowCountry: String = row.get("iso_country").get
                  rowCountry.equals(country)
                }).map(row => new Airport(row.get("id").get,
                                          row.get("iso_country").get,
                                          row.get("name").get, List.empty))
  }
}
