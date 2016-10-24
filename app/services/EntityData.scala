package services

import java.io.File
import javax.inject._
import play.api.Play
import com.github.tototoshi.csv._

class EntityData(val dataFile: File) {
  val reader = CSVReader.open(dataFile)
  val data : List[Map[String, String]] = reader.allWithHeaders()

  def getFileSize() = data.size
}
