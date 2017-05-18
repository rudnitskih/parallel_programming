package list

object worksheet {
  val list = Cons(1, Cons(Cons(2, Nil), Cons(3, Nil)));
                                                  //> list  : list.Cons[Any] = 1->[2->Nil]->3->Nil
  def concat[A](xs: List[A], ys: List[A]): List[A] =
    xs match {
      case Nil => ys
      case Cons(h, t) => Cons(h, concat(t, ys))
    }                                             //> concat: [A](xs: list.List[A], ys: list.List[A])list.List[A]
    
  concat(Cons(1, Cons(2, Nil)), Cons(3, Nil))     //> res0: list.List[Int] = 1->2->3->Nil
  
  
  val stream = Stream(1,2,3)                      //> stream  : scala.collection.immutable.Stream[Int] = Stream(1, ?)
  
  
}