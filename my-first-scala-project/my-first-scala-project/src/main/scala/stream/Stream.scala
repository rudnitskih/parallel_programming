package stream

sealed trait Stream[+A]

case object Empty extends Stream[Nothing]

case class Cons[+A](head: () => A, tail: () => Stream[A]) extends Stream[A]

object Stream {
  def cons[A](h: => A, t: => Stream[A]): Stream[A] = {
    lazy val head = h
    lazy val tail = t
    Cons(() => head, () => tail)
    }

   def empty[A]: Stream[A] = Empty

   def apply[A](args: A*): Stream[A] = 
     if (args.isEmpty) empty
     else cons(args.head, apply(args.tail: _*))
}