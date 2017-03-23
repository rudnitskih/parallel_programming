# DISCUSS 3, 4

Rational numbers, using symbolic names, homework (slide 15)

1. Learn the rules for making symbolic names and their precedence in the infix notation.
(http://scala-lang.org/files/archive/spec/2.12/06-expressions.html#infix-operations)

2. Reimplement the subtraction operation `def -(that: Rational): Rational` in terms of `+` and unary `-`.

3. Implement the comparison operation `def <(that: Rational): Rational`.

4. Improve `Rational` by always associating the minus sign for negative numbers with the numerator; if both numerator and denominator are negative then the resultant positive rational number should have no minus signs in its string representation.

**Answers:**

1. Learned

2. 
```
  def -(that: Rational): Rational = this + -that
```

3. Do `<` should return Boolean value?

    ```
        def <(that: Rational): Boolean = this.numer * that.denom < that.numer * this.denom
    ```

4. Don`t know how implement # TODO

---

# DISCUSS

The substitution model for classes, homework (slide 35)

Once `Rational` is provided with operation `less (<)`, evaluate the following expression using the substitution model.
    `new Rational(1,2) < new Rational(2,3)`

**Answer:**

```
	new Rational(1,2) < new Rational(2,3)
	
	[1/n, 2/d][new Rational(2,3)/that][new Rational(1,2)/this]
	this.numer * that.denom < that.numer * this.denom

	new Rational(1,2).numer * new Rational(2,3).denom < new Rational(2, 3).numer * new Rational(1, 2).denom
```

---
# Discuss 2, 3

Complex numbers, homework (slide 36)

1. Similar as with `Rational`, implement type `Complex` that would model arithmetic operations for complex numbers.

2. Learn about property-based testing using ScalaCheck. (https://www.scalacheck.org/)

3. Express properties for arithmetic operations of complex numbers (e.g. associativity for +) and prove those using the property-based testing.

**Answers:**

1. 
```
case class Complex(real: Int, img: Int) {
  def a = real;
  def b = img;
  
  def +(that: Complex): Complex = 
    new Complex(this.a + that.a, this.b + that.b);
}
```

2. Learned

3.

```
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
```

---

Recursive definition of sets, homework (slide 44)

1. Provide recursive definition for set N0 (natural numbers including 0).

2. Perform several steps of the recursive step application to observe how the set changes. How fast does it grow?


**Answers:**

1. Basic step: 0 ∈ S
   Recursive step: if x ∈ S then (x + 1) ∈ S 


---

Sets as binary full search trees, homework (slide 125)

Implement set-theoretic operation union.

```
sealed trait IntSet {
  ...
  def union(other: IntSet): IntSet
}

case object Empty extends IntSet {
  ...
  def union(other: IntSet): IntSet = other
}

case class NonEmpty() extends IntSet {
  def union(other: IntSet): IntSet = ???
}
```