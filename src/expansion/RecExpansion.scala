package expansion

import app.Customer
import recommendations.Recommendations.correlation

import scala.io.Source

object RecExpansion {
  val numberOfRecommendations: Int = 6

  def getStat(item: String): Customer => Double = {
    c: Customer => c.purchases(item)
  }

  //csv reading method for expansion
  def expansion(filename: String, viewingItem: String): List[String] = {
    val lines = Source.fromFile(filename).getLines()
    val header = lines.next()
    val itemNames = header.split(",")
    val customers: List[Customer] = (for (line <- lines) yield {
      new Customer({
        val values = line.split(",")
        (itemNames zip values.map(_.toInt)).toMap
      })
    }).toList

    topCorrelations(customers, (itemNames zip itemNames.map(getStat)).toMap, viewingItem, numberOfRecommendations)
  }

  //topK method for expansion
  def topCorrelations[T](elements: List[T], allProperties: Map[String, T => Double], testPropertyName: String, k: Int): List[String] = {
    val map=allProperties.transform((key, _) => correlation(elements,allProperties(testPropertyName),allProperties(key)))
    val res = map.-(testPropertyName)
    val sorted=res.toList.sortBy(-_._2.abs)
    sorted.slice(0,k).map(_._1)
  }

  def main(args: Array[String]): Unit = {
    println(expansion("data/math.csv","address"))
  }

}
