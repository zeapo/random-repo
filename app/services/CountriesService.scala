package services

import java.io.File

import com.github.tototoshi.csv._

class CountriesService(dataFile: File) {
  val reader = CSVReader.open(dataFile)
  val data : List[Map[String, String]] = reader.allWithHeaders()
  reader.close

  def getCountryCode(country: String) : String = {
    data.filter(row => {
                  val code = row.get("code").get
                  val name = row.get("name").get
                  code == country || name == name
                }).head.get("code").get
  }
}
