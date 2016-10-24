package services

import java.util.concurrent.atomic.AtomicInteger
import javax.inject._
import models.Airport

/**
 * This class is a concrete implementation of the [[Counter]] trait.
 * It is configured for Guice dependency injection in the [[Module]]
 * class.
 *
 * This class has a `Singleton` annotation because we need to make
 * sure we only use one counter per application. Without this
 * annotation we would get a new instance every time a [[Counter]] is
 * injected.
 */
@Singleton
class DataService @Inject() (environment: play.api.Environment, configuration: play.api.Configuration) {
  val runwaysData = new RunwaysData(environment.getFile("conf/resources/runways.csv"))
  val airportsData = new AirportsData(environment.getFile("conf/resources/airports.csv"), runwaysData)

  private val atomicCounter = new AtomicInteger()
  def nextCount(): Int = atomicCounter.getAndIncrement()
  def queryAirports(country: String) : List[Airport] = {
    val airports = airportsData.listAirports(country)
    airports.map(airport => airport.copy(runways = runwaysData.listRunways(airport.id)))
  }
}
