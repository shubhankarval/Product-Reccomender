package recommendations
object Recommendations {

  def standardDeviation[T](elements: List[T], property: T => Double): Double = {
    val lst:List[Double]=elements.map(property(_))
    val mean:Double=lst.sum/lst.length
    val variance:Double=lst.map(_-mean).map(Math.pow(_,2.0)).sum/(lst.length-1)
    Math.pow(variance,0.5)
  }


  // population or sample? sample coded here
  def correlation[T](elements: List[T], property1: T => Double, property2: T => Double): Double = {
    val lst1:List[Double]=elements.map(property1(_))
    val lst2:List[Double]=elements.map(property2(_))
    val mean1:Double=lst1.sum/lst1.length
    val mean2:Double=lst2.sum/lst2.length
    val a_lst:List[Double]=lst1.map(_ - mean1)
    val a:Double=a_lst.map(Math.pow(_,2.0)).sum
    val b_lst:List[Double]=lst2.map(_ - mean2)
    val b:Double=b_lst.map(Math.pow(_,2.0)).sum
    val ab:Double=(for(n <- a_lst.indices) yield { a_lst(n) * b_lst(n) }).toList.sum
    ab/Math.pow(a*b,0.5)

  }


  def topKCorrelations[T](elements: List[T], allProperties: Map[String, T => Double], testPropertyName: String, k: Int): List[String] = {
    val map=allProperties.transform((key, _) => correlation(elements,allProperties(testPropertyName),allProperties(key)))
    val res = map.-(testPropertyName)
    val sorted=res.toList.sortBy(-_._2)
    sorted.slice(0,k).map(_._1)
  }

}
