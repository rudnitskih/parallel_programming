package example

object workshop_final_1 {
  def hanoi(n: Int): Int = {
  	if (n == 0)  {
  	  0
   	} else {
   	  2 * hanoi(n - 1) + 1;
   	}
   
  }
  
	hanoi(0) == 0
	hanoi(1) == 1
	hanoi(2) == 3
	hanoi(3) == 7
	hanoi(4) == 15
	hanoi(7) == 127
	hanoi(9) == 511
  
  
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
  }
  
  isPrime(9) == false
  isPrime(6) == false
  isPrime(5) == true
  isPrime(3) == true
  isPrime(121) == false
  isPrime(113) == true
  
  def goldbach(x: BigInt): (BigInt, BigInt) = {
   def goldbachIt(currentX: BigInt):(BigInt, BigInt) = {
        if (isPrime(currentX) && isPrime(x - currentX)) {
          (currentX, x - currentX)
        } else {
          goldbachIt(currentX - 1);
        }
   }
   
   goldbachIt(x - 1);
  }
  
  goldbach(28)
  goldbach(10)
}