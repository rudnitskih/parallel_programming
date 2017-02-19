---

Closures, homework (slides 19)

Apply the substitution model to compute expression
isSqrtOf4(2).

```scala
def square(x: Double): Double = x * x

def isSqrtOf(x: Double) = (y: Double) => y >= 0 && square(y) == x

val isSqrtOf4 = isSqrtOf(4)

isSqrtOf4(2)
```

**substitution model:**

isSqrtOf(4):

-> { (y: Double) => y >= 0 && square(y) == 4 }

isSqrtOf4(2)

-> ({ (y: Double) => y >= 0 && square(y) == 4 })(2)
-> 2 >= 0 && square(y) == 4
-> square(2) == 4
-> 2 * 2 == 4
-> 4 == 4
-> true

---

# DISCUSS

**What are benefits of currying?** 

---

Express function factorial(n) in terms of prod

```scala
def prod(f: Int => Int)(a: Int, b: Int): Int =
    if (a > b) 1 else f(a) * prod(f)(a + 1, b)    //> prod: (f: Int => Int)(a: Int, b: Int)Int
  
def factorial(n: Int) = prod(x => x)(1, n) 

factorial(5) //> res0: Int = 120
```

---

Higher-order functions, homework (slide 79)

Modify function mapReduce to be tail-recursive.

```scala
  def tailMapReduce(f: Int => Int, combine: (Int, Int) => Int, unit: Int)(a: Int, b: Int): Int = {
    def tailMapReduceIt(sum: Int,
                        a: Int): Int = {
      if (a > b) sum
      else tailMapReduceIt(combine(sum, f(a)), a + 1)
    }

    tailMapReduceIt(unit, a)
  } 
```
# DISCUSS

Implement function derivativeOf(f) that would compute a derivative function of f.

```scala
def derivativeOf(f: Double=>Double): Double => Double = {
    def g(x: Double): Double = f(x + 0.00000001) - f(x) / x
    g
  }
  
  derivativeOf(x => 2 * x * x)(1) 
```

**Please help with this task. Do not understand relation with this topic**

---

Parametric polymorphism, homework (slide 100)

Analyse and provide an explanation for the following error:

```scala
def id(x: Int) = x

def double(x: Double) = x * x

compose(id, double)(5) // type mismatch;  found   : Int => Int  required: Double => Int

```
**`id` function should has the same otput type as 'double' input type**

---

Parametric polymorphism, homework

Implement multiplication of functions in accordance with the following definition:

(f1 * f2)(a) = f1(a) * f2(a), where f1, f2 in A -> R

```scala
def product[A](f1: A => Double,
                 f2: A => Double): A => Double =
    (x: A) => f1(x) + f2(x)
    
   product(id, square)(2) 
```