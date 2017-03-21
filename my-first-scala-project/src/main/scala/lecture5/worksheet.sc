package lecture5

object worksheet {
  def r = new Rational(4, 5)                      //> r: => lecture5.Rational

  r.neg                                           //> res0: lecture5.Rational = -4/5

  r.sub(new Rational(1, 5)).sub(new Rational(2, 5))
                                                  //> res1: lecture5.Rational = 1/5
  
  r.sub(new Rational(1, 5))                       //> res2: lecture5.Rational = 3/5
}