package list

sealed trait List[+A]

case object Nil extends List[Nothing]

case class Cons[+A](head: A, tail: List[A]) extends List[A] {
  override def toString() = head match {
    case _: List[_] => "[" + head + "]" + "->" + tail
    case _ => head + "->" + tail
  }
}


object List {
  def cons[A](h: A, t: List[A]): List[A] = {
    Cons(h, t)
    }
  
  def empty[A]: List[A] = Nil
  
//  def apply[A](args: A*): List[A] = {
//    if (args.isEmpty) empty
//    else cons(args.head, apply(args.tail: _∗))
//  }
}