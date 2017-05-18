package example

object lecture4_homework {
  def prod(f: Int => Int)(a: Int, b: Int): Int =
    if (a > b) 1 else f(a) * prod(f)(a + 1, b)    //> prod: (f: Int => Int)(a: Int, b: Int)Int

  prod(x => x)(1, 4)                              //> res0: Int = 24

  def factorial(n: Int) = prod(x => x)(1, n)      //> factorial: (n: Int)Int

  factorial(4)                                    //> res1: Int = 24
  factorial(5)                                    //> res2: Int = 120

  def mapReduce(f: Int => Int, combine: (Int, Int) => Int, unit: Int)(a: Int, b: Int): Int =
    if (a > b) unit
    else combine(f(a), mapReduce(f, combine, unit)(a + 1, b))
                                                  //> mapReduce: (f: Int => Int, combine: (Int, Int) => Int, unit: Int)(a: Int, b:
                                                  //|  Int)Int

  def sum(f: Int => Int)(a: Int, b: Int): Int = mapReduce(f, (x, y) => x + y, 0)(a, b)
                                                  //> sum: (f: Int => Int)(a: Int, b: Int)Int

  def tailMapReduce(f: Int => Int, combine: (Int, Int) => Int, unit: Int)(a: Int, b: Int): Int = {
    def tailMapReduceIt(sum: Int,
                        a: Int): Int = {
      if (a > b) sum
      else tailMapReduceIt(combine(sum, f(a)), a + 1)
    }

    tailMapReduceIt(unit, a)
  }                                               //> tailMapReduce: (f: Int => Int, combine: (Int, Int) => Int, unit: Int)(a: Int
                                                  //| , b: Int)Int

  def tailSum(f: Int => Int)(a: Int, b: Int): Int = tailMapReduce(f, (x, y) => x + y, 0)(a, b)
                                                  //> tailSum: (f: Int => Int)(a: Int, b: Int)Int

  sum(x => x)(1, 4)                               //> res3: Int = 10
  tailSum(x => x)(1, 4)                           //> res4: Int = 10

  def derivativeOf(f: Double => Double): Double => Double = {
    def g(x: Double): Double = f(x + 0.00000001) - f(x) / x
    g
  }                                               //> derivativeOf: (f: Double => Double)Double => Double

  derivativeOf(x => 2 * x * x)(1)                 //> res5: Double = 3.999999975690116E-8

  def compose[A, B, C](f: B => C, g: A => B): A => C = (x: A) => f(g(x))
                                                  //> compose: [A, B, C](f: B => C, g: A => B)A => C

  def id(x: Double) = x                           //> id: (x: Double)Double

  def square(x: Double) = x * x                   //> square: (x: Double)Double

  compose(id, square)(5)                          //> res6: Double = 25.0

  def product[A](f1: A => Double,
                 f2: A => Double): A => Double =
    (x: A) => f1(x) + f2(x)                       //> product: [A](f1: A => Double, f2: A => Double)A => Double
    
   product(id, square)(2)                         //> res7: Double = 6.0
   
   var john = new User("john");                   //> abstreact
                                                  //| user
                                                  //| john  : example.User = toString()
}