package services

import java.util.concurrent.atomic.AtomicInteger
import javax.inject._

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
class DataService @Inject() (airports: AirportsData) {
  private val atomicCounter = new AtomicInteger()
  def nextCount(): Int = atomicCounter.getAndIncrement()
}
