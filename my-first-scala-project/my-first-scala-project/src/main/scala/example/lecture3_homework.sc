package example

object lecture3_homework {
  def factorial(n: Int): Int = {
    if (n == 1) 1
    else n * factorial(n - 1)
  }                                               //> factorial: (n: Int)Int

  def sumIntegers(a: Int, b: Int): Int =
    if (a > b) 0
    else a + sumIntegers(a + 1, b)                //> sumIntegers: (a: Int, b: Int)Int

  def sumFactorials(a: Int, b: Int): Int =
    if (a > b) 0
    else factorial(a) + sumFactorials(a + 1, b)   //> sumFactorials: (a: Int, b: Int)Int

  def sum(f: Int => Int, a: Int, b: Int): Int =
    if (a > b) 0 else f(a) + sum(f, a + 1, b)     //> sum: (f: Int => Int, a: Int, b: Int)Int

  /****** My answers *********/
  
  /******* Higher-order functions, micro-homework slide 101 *******/
  def sumFactorialsMy(a: Int, b: Int) = sum(factorial, a, b)
                                                  //> sumFactorialsMy: (a: Int, b: Int)Int

  sumFactorials(3, 3) == sumFactorialsMy(3, 3)    //> res0: Boolean = true
  sumFactorials(3, 5) == sumFactorialsMy(3, 5)    //> res1: Boolean = true

  /******* Making sum tail-recursive, homework
     The current implementation of function sum generates a
     recursive process. Reimplement function sum to be
     tail-recursive in order to generate an iterative process.
   *******/
   
   def tailSum(f: Int => Int, a: Int, b: Int): Int = {
    def tailSumIt(sum: Int,
    							a: Int):Int = {
      if (a > b) sum else tailSumIt(sum + f(a), a + 1)
    }
    
    tailSumIt(0, a)
   }                                              //> tailSum: (f: Int => Int, a: Int, b: Int)Int
   
   sumFactorials(3, 3) == tailSum(factorial, 3, 3)//> res2: Boolean = true
   sumFactorials(5, 6) == tailSum(factorial, 5, 6)//> res3: Boolean = true
   sumFactorials(2, 5) == tailSum(factorial, 2, 5)//> res4: Boolean = true
}