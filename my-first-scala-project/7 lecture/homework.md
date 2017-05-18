Mapping must be structure preserving, homework (slide 14)

The presented implementation of map is invalid!

```
scala > val set = Empty include 7 include 5 ... i n cl u d e 15
set: IntSet = {{.5.}7{{.9.}12{.15.}}}

scala > set map { x => if ( x > 7 ) -x else x}
res14: IntSet = {{.5.}7{{.-15{.-12.}}-9.}}
```

**Answer:**

```
def map(f: Int => Int) =
    NonEmpty(f(el), Empty, Empty).union(left.map(f)).union(right.map(f))
```

---

# TODO 2

Find the balance, homework (slide 15)

1. Construct function `def isBalanced(tree: IntSet): Boolean`, which would identify if a binary tree that represents a set is balanced or not.

2. Construct function `def rebalance(tree: IntSet): IntSet` that would produce a balanced tree.

**Answers:*

1.

```
case object Empty extends IntSet {
  ...
  def isBalanced = true
}
case class NonEmpty(el: Int,
                    left: IntSet,
                    right: IntSet) extends IntSet {
  ...
  def isBalanced = Math.abs(left.height - right.height) <= 1 && left.isBalanced && right.isBalanced
}
```

2. # TODO
---

# TODO answer on the question

Type variance for functions (slide 86)

Function types A => B have types in two places – arguments and result!

When a function type is a subtype of another?

Learn the rules of type variance for functions.

Refer chapter “Type Parameterisation” in “Programming in Scala” by Martin Odersky et al.

---

Operations on lists, concat (slide 93)

Refactor operation `concat` to become member `++` of type `List`.

```
sealed trait List[+A] {
  def ++[B >: A](ys: List[B]): List[B] = ???
}
```

```
scala> val ys = Cons(1, Cons(2, Cons(3, Nil)))
ys: Cons[Int] = 1 → 2 → 3 → Nil

scala> val xs = Cons(42, Nil)
xs: Cons[Int] = 42 → Nil

scala> xs ++ ys
res1: List[Int] = 42 → 1 → 2 → 3 → Nil
```

---

Operations on lists, accessing elements (slide 99)

Implement function `nth` to access the n^th element of a list.

`def nth[A](n: Int, xs: List[A]): A = ???`

What if `n` is greater than the `size` of `xs` or negative?
Learn what is type `Option` and implement an improved function.

`def nth[A](n: Int, xs: List[A]): Option[A] = ???`

---

Operations on lists, generalising HOF, homework (slide 111)

Implement function for computing the length of a list using `foldRight`.

`def length[A](xs: List[A]): Int = ???`

---

Operations on lists, generalising HOF (slide 115)

The implementation of `foldRight` is not tail-recursive.
Implement alternative folding function `foldLeft` tail-recursively.

```
@annotation.tailrec
def foldLeft[A,B](xs: List[A], z: B)(f: (B, A) => B): B = ???
```

---

Operations on lists, generalising HOF, homework (slide 117)

Implement alternative to `foldRight` function `foldRightViaFoldLeft` in terms of `foldLeft`.

`def foldRightViaFoldLeft[A, B](l: List[A], z: B)(f: (A, B) => B): B = ???`

---

Operations on lists, filtering (slide 129)

Implement function `filter` that computes to a list with only those elements from the original list that satisfy the predicate.

`def filter[A](xs: List[A])(p: A=> Boolean): List[A] = ???`

Implement operations `map` and `filter` as members of `List`.

---

Lazy lists, homework (slide 148)

Implement function drop(n) for skipping the first n elements of a stream.

`def drop(n: Int): Stream[A]`

---

Lazy lists, for all homework (slide 153)

Implement function forAll, which should terminate as soon as it encounters the first non-matching value.

`def forAll(p: A => Boolean): Boolean`

---

Lazy lists, homework (slide 157)

Implement operations map and filter for Stream.

---

Lazy lists, primary homework (slide 158)

Implement streaming of primary numbers.
