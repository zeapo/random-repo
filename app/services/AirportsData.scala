package services

import java.io.File

import com.github.tototoshi.csv._
import models.Airport

/**
  *
  * Note: I use a `get` in all cases as I suppose that the targeted values are non-empty
  */
class AirportsData(dataFile: File, runwaysData: RunwaysData) {
  val reader = CSVReader.open(dataFile)
  // initialize the aiport data; each airport with all its runways
  val data : List[Airport] = reader.allWithHeaders().map(row => {
                                                           val airportId = row.get("id").get
                                                           val runways = runwaysData.listRunways(airportId)
                                                           new Airport(row.get("id").get,
                                                                       row.get("iso_country").get,
                                                                       row.get("name").get,
                                                                       runways)
                                                         })
  reader.close

  /**
    *  Lists the airports with their runways given a country code
    */
  def listAirports(country: String) = {
    data.filter(airport => airport.iso_country.equals(country))
  }
}
