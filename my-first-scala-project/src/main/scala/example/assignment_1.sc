package example

object assignment_1 {
	def pascal(c: Int, r: Int): Int = {
  	c
  }                                               //> pascal: (c: Int, r: Int)Int
  
  pascal(0, 2) == 1                               //> res0: Boolean = false
  pascal(1, 2) == 2                               //> res1: Boolean = false
  pascal(1, 3) == 3                               //> res2: Boolean = false

	def balance(chars: List[Char]): Boolean = {
		true
	}                                         //> balance: (chars: List[Char])Boolean
	
	balance(":-)".toList) == false            //> res3: Boolean = false
	balance("())(".toList) == false           //> res4: Boolean = false
	balance("ось ще однин ( тут ( та й тут ) i може) (тут) є збалансованими".toList) == true
                                                  //> res5: Boolean = true
	balance("(щось тут ( та й тут ) i може ( ще й тут) є)".toList) == true
                                                  //> res6: Boolean = true
	
	def countChange(money: Int, coins: List[Int]): Int = {
		money
	}                                         //> countChange: (money: Int, coins: List[Int])Int
	
	countChange(4, List(1, 2)) == 3           //> res7: Boolean = false
}