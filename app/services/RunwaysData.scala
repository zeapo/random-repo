package services

import java.io.File
import models.Runway
import scala.collection.mutable
import com.github.tototoshi.csv._


class RunwaysData(dataFile: File) {
  val reader = CSVReader.open(dataFile)
  val runwaysByAirport : mutable.Map[String, List[Runway]] = mutable.Map.empty
  private val runwaysBuffer : mutable.ListBuffer[Runway] = mutable.ListBuffer.empty

  reader.iteratorWithHeaders.foreach(row => {
                                       val airportId = row.get("airport_ref").get
                                       if (!runwaysByAirport.contains(airportId)) {
                                         runwaysByAirport += (airportId -> List.empty)
                                       }
                                       val runway = new Runway(row.get("id").get,
                                                               row.get("airport_ref").get,
                                                               row.get("surface").get,
                                                               row.get("le_ident").get)

                                       runwaysByAirport(airportId) = runway :: runwaysByAirport(airportId)
                                       runwaysBuffer += runway
                                     })
  val data = runwaysBuffer.toList
  reader.close

  def listRunways(airportId: String) = {
    runwaysByAirport.getOrElse(airportId, List.empty)
  }
}
