package example

object assignment_2 {
  type Set = Int => Boolean

  def contains(s: Set, elem: Int): Boolean = s(elem)
                                                  //> contains: (s: example.assignment_2.Set, elem: Int)Boolean
  def singletonSet(elem: Int): Set = (x: Int) => x == elem
                                                  //> singletonSet: (elem: Int)example.assignment_2.Set

  singletonSet(4)(5) == false                     //> res0: Boolean = true
  singletonSet(4)(4) == true                      //> res1: Boolean = true

  def union(s: Set, t: Set): Set = (x: Int) => s(x) || t(x)
                                                  //> union: (s: example.assignment_2.Set, t: example.assignment_2.Set)example.ass
                                                  //| ignment_2.Set

  def intersect(s: Set, t: Set): Set = (x: Int) => s(x) && t(x)
                                                  //> intersect: (s: example.assignment_2.Set, t: example.assignment_2.Set)example
                                                  //| .assignment_2.Set

  def diff(s: Set, t: Set): Set = (x: Int) => s(x) && !t(x)
                                                  //> diff: (s: example.assignment_2.Set, t: example.assignment_2.Set)example.assi
                                                  //| gnment_2.Set
  // Filter is the same as intersection ?
  def filter(s: Set, p: Int => Boolean): Set = intersect(s, p)
                                                  //> filter: (s: example.assignment_2.Set, p: Int => Boolean)example.assignment_2
                                                  //| .Set

  val from0to15 = union((x: Int) => x < 10 && x > 0, (x: Int) => x >= 10 && x < 15)
                                                  //> from0to15  : example.assignment_2.Set = <function1>
  val from5to8 = intersect((x: Int) => x < 10 && x > 0, (x: Int) => x > 5 && x < 8)
                                                  //> from5to8  : example.assignment_2.Set = <function1>

  val from0to10 = diff((x: Int) => x < 15 && x > 0, (x: Int) => x > 10)
                                                  //> from0to10  : example.assignment_2.Set = <function1>
  from0to15(11) == true                           //> res2: Boolean = true
  from0to15(20) == false                          //> res3: Boolean = true
  from5to8(7) == true                             //> res4: Boolean = true
  from5to8(9) == false                            //> res5: Boolean = true
  from0to10(5) == true                            //> res6: Boolean = true
  from0to10(13) == false                          //> res7: Boolean = true

  def forall(s: Set, p: Int => Boolean): Boolean = {
    val min = -1000;
    val max = 1000;

    def iter(a: Int): Boolean = {
      if (a == max) true
      else if (s(a) && !p(a)) false
      else iter(a + 1)
    }

    iter(min)
  }                                               //> forall: (s: example.assignment_2.Set, p: Int => Boolean)Boolean

  forall((x) => x < 40, (x) => x < 50) == true    //> res8: Boolean = true
  forall((x) => x < 40, (x) => x < 30) == false   //> res9: Boolean = true

  // DISCUSS do not know how reuse `forall` function
  def exists(s: Set, p: Int => Boolean): Boolean = {
    /*val min = -1000;
    val max = 1000;

    def iter(a: Int): Boolean = {
      if (a == max) false
      else if (s(a) && p(a)) true
      else iter(a + 1)
    }

    iter(min)*/
    forall(s, diff(s, p))
  }                                               //> exists: (s: example.assignment_2.Set, p: Int => Boolean)Boolean

	exists((x) => x < 40, (x) => x > 35) == true
                                                  //> res10: Boolean = false
  exists((x) => x < 40, (x) => x == 39) == true   //> res11: Boolean = false
  exists((x) => x < 40, (x) => x > 40) == false   //> res12: Boolean = false
  exists((x) => x < 40, (x) => x == 50) == false  //> res13: Boolean = false

  def map(s: Set, f: Int => Int): Set = (x: Int) => s(f(x))
                                                  //> map: (s: example.assignment_2.Set, f: Int => Int)example.assignment_2.Set

  val from10to20 = map((x) => x > 10 && x < 20, (x: Int) => x * 2)
                                                  //> from10to20  : example.assignment_2.Set = <function1>

  from10to20(6) == true                           //> res14: Boolean = true
  from10to20(12) == false                         //> res15: Boolean = true

}