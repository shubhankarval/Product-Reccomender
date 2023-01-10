package tests

import org.scalatest.FunSuite

class TestCorrelation extends FunSuite {

  class TwoDoubles(val a: Double, val b: Double) {

  }

  val EPSILON: Double = 0.01

  def equalDoubles(d1: Double, d2: Double): Boolean = {
    (d1 - d2).abs < EPSILON
  }

  test("Test Correlation") {
  }

}
