package lecture5;

class Rational(n: Int, d: Int) {
  def numer = n;
  def denom = d;
  
  def unary_- : Rational = new Rational(-numer, denom)

  def +(that: Rational): Rational =
    new Rational(this.numer * that.denom + that.numer * this.denom, this.denom * that.denom)

  def -(that: Rational): Rational = this + -that
  
  def <(that: Rational): Boolean = this.numer * that.denom < that.numer * this.denom

  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)

  override def toString = {
    val cd = gcd(n, d)
    numer / cd + "/" + denom / cd;
  }

}
