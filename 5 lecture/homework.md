Mathematical induction, homework (slide 20)

# Discuss

Remind yourselves what is the complete induction (aka
strong induction) and how does it differ from the basic
form of induction (aka weak induction).

http://www.cs.cornell.edu/courses/cs211/2005sp/Sections/S2/Induction.pdf

---

# Discuss

Prove that mathematical induction is valid.

---

Mathematical induction, homework (slide 36) 

Come up with a finite formula for summing up first n
odd integer numbers:

    `1 + 3 + 5 + ... + (2n - 1) = ?`

**Answer:**
  `n * n`

---

Prove that the devised formula is correct using
mathematical induction.

**Answer:**

```
1) P(1): 1 = 1
2) P(k): 1 + 3 + 5 + ... + (2k - 1) = k * k
3) P(k+1): 1 + 3 + 5 + ... + (2k - 1) + (2k + 1)
   k * k + (2k + 1) = (k + 1)^2

```

---

Recursive definition of functions, homework (slide 47)

Provide a recursive definition for function

`f(x,n) = x^n , x ∈ R, n ∈ Z, n >= 0;`

**Answer:*
Basis step: f(0) = 1
Recursive step: f(n+1) = x * f(x,n)

Compute f(2,3)

f(2, 3) = 2 * f(2,2) = 2 * (2 * f(2,1)) = 2 * (2 * (2 * f(2, 0))) = 2 * 2 * 2 * 1 = 8

---

# DISCUSS (check with Yuri)

Provide a recursive define for function `f(n) = ∑(k=0, n): a_k`
where a_k are members of some sequence, n ∈ Z, n >= 0;

**Answer:**
Basic step: f(0) = a(0)
Recursive step: f(n+1) = a(n+1) + a(n)

E.g.

a = 1; 4; 9; 16; 25

f(4) = 25 + f(4) = 25 + (16 + f(3)) = 25 + (16 + (9 + f(2))) ...

---

# DISCUSS (check with Yuri)

Arithmetic operations for rational numbers, homework (slide 106)

Apply the substitution model to the following expression in order to
satisfy yourselves that the alternative implementation works.

numer(add(newRational(1, 2), newRational(1, 3)))

**Answer:**
```
numer(add((f: (Int, Int) => Int) => f(1, 2), newRational(1, 3)))
numer(add((f: (Int, Int) => Int) => f(1, 2), (f: (Int, Int) => Int) => f(1, 3)))

_a = (f: (Int, Int) => Int) => f(1, 2)
_b = (f: (Int, Int) => Int) => f(1, 3)

numer(add((_a, _b))
numer(newRational((1, 2) => 1 * denom(_b) + numer(_b) * denom(_a), denom(_a) * denom(_b)))
numer(newRational(1 * denom(_b) + numer(_b) * denom(_a), denom(_a) * denom(_b)))
numer(newRational(1 * (1, 3) => 3 + numer(_b) * denom(_a), denom(_a) * denom(_b)))
numer(newRational(1 * 3 + numer(_b) * denom(_a), denom(_a) * denom(_b)))
numer(newRational(3 + numer(_b) * denom(_a), denom(_a) * denom(_b)))
numer(newRational(3 + (1, 3) => 1 * denom(_a), denom(_a) * denom(_b)))
numer(newRational(3 + (1, 3) => 1 * (1, 2) => 2, denom(_a) * denom(_b)))
numer(newRational(3 + 1 * (1, 2) => 2, denom(_a) * denom(_b)))
numer(newRational(3 + 1 * 2, denom(_a) * denom(_b)))
numer(newRational(3 + 2, denom(_a) * denom(_b)))
numer(newRational(5, denom(_a) * denom(_b)))
numer(newRational(5, (1, 2) => 2 * denom(_b)))
numer(newRational(5, (1, 2) => 2 * (1, 2) => 3))
numer(newRational(5, 2 * (1, 2) => 3))
numer(newRational(5, 2 * 3))
numer((f: (Int, Int) => Int) => f(5, 6))
(5, 6) => 5
5
```

---

Rational numbers, modelling with classes, homework (slide 115)

1) Introduce operation of negation `def neg: Rational` as method of type Rational.
2) Implement operation def sub(that: Rational): Rational in terms of neg (also as method of Rational).
3) What is the order of operations in expression `one half.sub(one half).sub(one third)`?

**Answer:**

1) `def neg: Rational = new Rational(-n, d)`
2) `def sub(that: Rational): Rational = this.add(that.neg)`
3) First - `half.sub(one half)`, second - `.sub(one third)`

---

# Discuss question 2

Rational numbers, modelling with classes, homework (slide 121)

1) Change the implementation of `Rational` so that `gcd` is only used in the implementation of `toString`.
2) Will this change affect the users of our data type? If yes, then how and under what circumstances?

**Answers:**

1) ```
   override def toString = {
    val cd = gcd(n, d)  
    numer / cd + "/" + denom / cd;
  }
```

2) I think no


