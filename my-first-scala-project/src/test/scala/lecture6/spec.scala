package lecture6

import org.scalacheck._
import Prop.forAll

object ComplexSpecification extends Properties("Complex") {
  val complexes: Gen[Complex] =
  for {
    a <- Gen.choose(-100, 100)
    b <- Gen.choose(-100, 100)
  } yield Complex(a, b)
  
  property("+") = forAll(complexes, complexes) { (c1: Complex, c2: Complex) =>
    (c1 + c2).a == c1.a + c2.a && (c1 + c2).b == c1.b + c2.b
  }
}