package lecture6

case class Complex(real: Int, img: Int) {
  def a = real;
  def b = img;
  
  def +(that: Complex): Complex = 
    new Complex(this.a + that.a, this.b + that.b);
}