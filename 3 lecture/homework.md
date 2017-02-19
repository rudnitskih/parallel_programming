---

# DISCUSS

Try answering the following questions.

<dl>
  <dt>Is it valid to speak of functions with domain A = ∅?</dt>
  <dd>Maybe yes, e.g. function which no accept arguments and always return constant?</dd>
  <dt>What could be the purpose of functions with domain A, where |A| = 1?</dt>
  <dd>Convert one value to another?</dd>
  <dt>What is the significance of functions with codomain B, where |B| = 2?</dt>
  <dd>Boolean function which returns TRUE or FALSE</dd>
</dl>

---
# DISCUSS

Find the number of functions from A to B.
<pre>         | A → B | ? </pre>

**Could you please explain it**

---

Prove that association holds for function composition:
(f ∘ g ) ∘ h = f ∘ (g ∘ h)

**My evidence:**

```((f ∘ g ) ∘ h)(x) = (f ∘ g)(h(x)) = f(g(h(x)))```

```(f ∘ (g ∘ h))(x) = f((g ∘ h)(x)) = f(g(h(x)))```

---

# DISCUSS

Implement function plus(x,y) ≡ x + y without the use of operation +.

```scala
def plus (x: Int, y: Int): Int = {
    
}
```

**Please explain how to solve this task**

---

Higher-order functions, micro-homework slide 101

**Answer:**

```def sumFactorialsMy(a: Int, b: Int) = sum(factorial, a, b)```

---

Making sum tail-recursive, homework

The current implementation of function sum generates a
recursive process. Reimplement function sum to be
tail-recursive in order to generate an iterative process.

**Answer:**
```scala
    def tailSum(f: Int => Int, a: Int, b: Int): Int = {
        def tailSumIt(sum: Int,
                      a: Int):Int = {
          if (a > b) sum else tailSumIt(sum + f(a), a + 1)
        }

        tailSumIt(0, a)
    }
```
