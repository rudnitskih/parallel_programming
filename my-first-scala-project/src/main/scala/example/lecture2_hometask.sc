package example

object lecture2_hometask {
  def square(x: Double) = x * x

//def sqrt

def abs(x: Double) = if (x >= 0 ) x else -x

def avg(x: Double, y: Double) = (x + y) / 2

def sqrt(x: Double) = {
    def sqrtIteration(y: Double): Double =
        if (isEstimateGood(y))
            y
        else
            sqrtIteration(improve(y))

    def isEstimateGood(y: Double) =
        abs(square(y) - x) < 0.001

    def improve(y: Double) =
        avg(y, x/y)

    sqrtIteration(1)
}

def factorial(n: Int): Int = {
    if (n == 1)
        1
    else
        n * factorial(n - 1)
	}
	
{
	def factorial(n: Int) = {
	
		def factIteration(
		    product: Int,
		    counter: Int,
		    maxCount: Int): Int = {
		    if (counter > maxCount)
		        product
		    else
		        factIteration(counter * product, counter + 1, maxCount)
		}
		    
		factIteration(1, 1, n)
	}
}

 avg(5, 6)

}