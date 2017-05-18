package intervals_works

class Interval(left: Double, right: Double) {
  require(left < right, "Left should be less than right")

  def lowerBound = left
  def upperBound = right

  def +(that: Interval): Interval =
    new Interval(this.lowerBound + that.lowerBound,
      this.lowerBound + that.upperBound)

  def -(that: Interval): Interval =
    new Interval(this.lowerBound - that.lowerBound,
      this.lowerBound - that.upperBound)

  def ==(that: Interval): Boolean =
    this.lowerBound == that.lowerBound &&
      this.upperBound == that.upperBound

  def unary_- : Interval =
    new Interval(-this.lowerBound, -this.upperBound)

  def ∩(that: Interval): Interval =
    new Interval(
      math.max(this.lowerBound, that.lowerBound),
      math.min(this.upperBound, that.upperBound))
  
  override def toString = "[" + this.lowerBound + ";" + this.upperBound + "]"
  
  
}