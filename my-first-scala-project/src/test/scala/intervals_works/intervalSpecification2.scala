package intervals_works

import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

import org.scalacheck._
import Arbitrary._
import Gen._
import Prop._

object intervalSpecification2 extends Properties("Interval") {
  /*val genInterval: Gen[Interval] = for {
    k <- arbitrary[Double].suchThat(x => x < 1000 || x > -1000) 
    m <- arbitrary[Double].filter(x => x > k)
  } yield new Interval(k, m)
  
  implicit val arbInterval: Arbitrary[Interval] = Arbitrary[genInterval]
  
  property("union commutativity") = forAll { (i1: Interval, i2: Interval) =>
    i1 + i2 == i2 + i1
  }*/
}