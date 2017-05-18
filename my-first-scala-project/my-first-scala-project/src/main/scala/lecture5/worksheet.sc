package lecture5

object worksheet {
  def r = new Rational(4, 5)                      //> r: => lecture5.Rational

  -r                                              //> res0: lecture5.Rational = -4/5

  r - new Rational(1, 5) - new Rational(2, 5)     //> res1: lecture5.Rational = 1/5
  
  r - new Rational(1, 5)                          //> res2: lecture5.Rational = 3/5
  
  new Rational(4, 5) < new Rational(5, 5) == true //> res3: Boolean = true
  new Rational(4, 5) < new Rational(3, 5) == false//> res4: Boolean = true
  
  -(-5)                                           //> res5: Int = 5
}