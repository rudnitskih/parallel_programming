package intervals_works

import org.scalatest.FunSuite

class intervalSuite extends FunSuite {
  test("Interval boundaries are correct") {
    val sample = new Interval(7.8, 9.0)

    assert(sample.lowerBound == 7.8)
    assert(sample.upperBound == 9.0)
  }

  test("Interval boundaries could not be reverted") {
    intercept[IllegalArgumentException] {
      val sample = new Interval(9.0, 7.8)
    }
  }

  test("The sum of two intervals is correct") {
    val sample1 = new Interval(1, 2)
    val sample2 = new Interval(2, 3)

    assert(sample1 + sample2 == new Interval(3, 5))
  }
  
  test("The fiff of two intervals is correct") {
    val sample1 = new Interval(1, 4)
    val sample2 = new Interval(2, 3)

    assert(sample1 - sample2 == new Interval(-1, 1))
  }
  
  test("The fiff of two intervals is correct") {
    val sample1 = new Interval(1, 4)
    val sample2 = new Interval(2, 3)

    assert(sample1 - sample2 == new Interval(-1, 1))
  }
  
  

  //  test("Negated interval is correct") {
  //    assert(-new Interval(1, 2) == new Interval(-1, -2))
  //  }
}