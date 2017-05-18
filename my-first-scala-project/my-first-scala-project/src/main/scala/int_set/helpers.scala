package int_set

object helpers {
  def height(set: IntSet): Int = set match {
    case Empty =>
      0
    case NonEmpty(_, left, right) =>
      1 + Math.max(height(left), height(right))
  }

  def size(set: IntSet): Int = set match {
    case Empty =>
      0
    case NonEmpty(_, left, right) =>
      1 + size(left) + size(right)
  }
}

