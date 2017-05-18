package example

abstract class U {
  println("abstreact");
}

class User(name: String) extends U{
  println("user")
  override def toString() = { "toString()" }
}