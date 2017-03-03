package example

object assignment_2 {
  type Set = Int => Boolean
  
  val max = 1000;
  val min = -max;
  

  def contains(s: Set, elem: Int): Boolean = s(elem)

  def singletonSet(elem: Int): Set = (x: Int) => x == elem

  singletonSet(4)(5) == false
  singletonSet(4)(4) == true

  def union(s: Set, t: Set): Set = (x: Int) => s(x) || t(x)

  def intersect(s: Set, t: Set): Set = (x: Int) => s(x) && t(x)

  def diff(s: Set, t: Set): Set = (x: Int) => s(x) && !t(x)
  
  def filter(s: Set, p: Int => Boolean): Set = intersect(s, p)

  val from0to15 = union((x: Int) => x < 10 && x > 0, (x: Int) => x >= 10 && x < 15)
  val from5to8 = intersect((x: Int) => x < 10 && x > 0, (x: Int) => x > 5 && x < 8)

  val from0to10 = diff((x: Int) => x < 15 && x > 0, (x: Int) => x > 10)

  from0to15(11) == true
  from0to15(20) == false
  from5to8(7) == true
  from5to8(9) == false
  from0to10(5) == true
  from0to10(13) == false

  def forall(s: Set, p: Int => Boolean): Boolean = {
    def iter(a: Int): Boolean = {
      if (a > max) true
      else if (s(a) && !p(a)) false
      else iter(a + 1)
    }

    iter(min)
  }

  forall((x) => x < 40, (x) => x < 50) == true
  forall((x) => x < 40, (x) => x < 30) == false

  def exists(s: Set, p: Int => Boolean): Boolean = {
    !forall(s, x => !p(x));
  }
  
  exists((x) => x < 40, (x) => x > 35) == true
  exists((x) => x < 40, (x) => x == 39) == true
  exists((x) => x < 40, (x) => x > 40) == false
  exists((x) => x < 40, (x) => x == 50) == false

  def map(s: Set, f: Int => Int): Set = (x: Int) => exists(s, y => f(y) == x)

  val multiplyBy2 = (x: Int) => x * 2
  val oddGreaterThan20 = map((x) => x > 10, multiplyBy2)
  val oddFrom20to40 = map((x) => x > 10 && x < 20, (x: Int) => x * 2)

  oddGreaterThan20(21) == false
  oddGreaterThan20(22) == true
  oddGreaterThan20(9) == false
  oddFrom20to40(12) == false
  oddFrom20to40(36) == true
  map(singletonSet(3), multiplyBy2)(6)
}