package int_set

sealed trait IntSet {
  def height: Int
  def size: Int
  def isBalanced: Boolean 

  def contains(x: Int): Boolean
  def include(x: Int): IntSet
  def union(other: IntSet): IntSet
  def map(f: Int => Int): IntSet
  def rebalance(): IntSet
}

case object Empty extends IntSet {
  def height = 0
  def size = 0
  def isBalanced = true

  def contains(x: Int) = false
  def include(x: Int) = NonEmpty(x, Empty, Empty)
  def union(other: IntSet) = other
  def map(f: Int => Int) = this
  def rebalance() = this
  
  override def toString = "."
}

case class NonEmpty(el: Int,
                    left: IntSet,
                    right: IntSet) extends IntSet {
  def height = 1 + Math.max(left.height, right.height)
  def size = 1 + left.size + right.height
  def isBalanced = Math.abs(left.height - right.height) <= 1 && left.isBalanced && right.isBalanced

  def contains(x: Int) =
    if (x < el) left contains x
    else if (x > el) right contains x
    else true

  def include(x: Int)  =
    if (x < el) NonEmpty(el, left include x, right)
    else if (x > el) NonEmpty(el, left, right include x)
    else this

  def union(set: IntSet): IntSet =
    set match {
      case Empty =>
        this
      case NonEmpty(el, left, right) =>
        this.include(el).union(left).union(right)
    }
  
  def map(f: Int => Int) = NonEmpty(f(el), Empty, Empty).union(left.map(f)).union(right.map(f))
  
  def rebalance() =
    if (this.isBalanced) this
    else this

  override def toString = "{" + left + el + right + "}"
}