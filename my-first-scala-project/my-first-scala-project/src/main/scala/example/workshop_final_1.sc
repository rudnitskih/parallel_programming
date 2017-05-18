package example

object workshop_final_1 {
  def hanoi(n: Int): Int = {
  	if (n == 0)  {
  	  0
   	} else {
   	  2 * hanoi(n - 1) + 1;
   	}
   
  }                                               //> hanoi: (n: Int)Int
  
	hanoi(0) == 0                             //> res0: Boolean = true
	hanoi(1) == 1                             //> res1: Boolean = true
	hanoi(2) == 3                             //> res2: Boolean = true
	hanoi(3) == 7                             //> res3: Boolean = true
	hanoi(4) == 15                            //> res4: Boolean = true
	hanoi(7) == 127                           //> res5: Boolean = true
	hanoi(9) == 511                           //> res6: Boolean = true
  
  
  def isPrime(number: BigInt): Boolean = {
    def f (currentNumber: BigInt): Boolean = {
      if (currentNumber == number) {
        true
      } else if (number % currentNumber == 0) {
        false
      } else {
        f(currentNumber + 1);
      }
    }
    
    f(2);
  }                                               //> isPrime: (number: BigInt)Boolean
  
  isPrime(9) == false                             //> res7: Boolean = true
  isPrime(6) == false                             //> res8: Boolean = true
  isPrime(5) == true                              //> res9: Boolean = true
  isPrime(3) == true                              //> res10: Boolean = true
  isPrime(121) == false                           //> res11: Boolean = true
  isPrime(113) == true                            //> res12: Boolean = true
  
  def goldbach(x: BigInt): (BigInt, BigInt) = {
   def goldbachIt(currentX: BigInt):(BigInt, BigInt) = {
        if (isPrime(currentX) && isPrime(x - currentX)) {
          (currentX, x - currentX)
        } else {
          goldbachIt(currentX - 1);
        }
   }
   
   goldbachIt(x - 1);
  }                                               //> goldbach: (x: BigInt)(BigInt, BigInt)
  
  goldbach(28)                                    //> res13: (BigInt, BigInt) = (23,5)
  goldbach(10)                                    //> res14: (BigInt, BigInt) = (7,3)
}