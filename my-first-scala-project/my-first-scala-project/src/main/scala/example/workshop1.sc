package example

object workshop1 {
  {
  	def fib(n: Int): Int = {
  		if (n == 0) 0
  		else
  			if (n == 1) 1
  			else fib(n - 2) + fib(n - 1)
  	}
  	
  	fib(6)
  	fib(7)
  	fib(7)
  }
   
   
  	def fib(n: Int): Int = {
  		@annotation.tailrec
	  	def fibIter(sum: Int, lastValue: Int, counter: Int): Int = {
	   		if (counter < n)
	   			fibIter(sum + lastValue, sum, counter + 1) + 0
	   		else
	   			lastValue
	    }
	    
	    if (n == 0) 0
  		else
  			fibIter(1, 1, 1);
  	}
  	
  	fib(0)
  	fib(1)
  	fib(7)
}