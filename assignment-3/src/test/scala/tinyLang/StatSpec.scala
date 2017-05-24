package tinyLang

import org.scalatest.FlatSpec

class StatSpec extends FlatSpec {
  it should "print sequense of doNothing; assign; doNothing" in {
    println(
      Seq(
        If(
          Bool(true),
          Assign("x", Number(0)),
          DoNothing()),
        Assign("x", Number(1)),
        If(
          Bool(true),
          Assign("x", Number(2)),
          DoNothing()),
        DoNothing(),
        Assign("x", Number(3)),
        DoNothing(),
        While(
            Bool(true),
            Assign("x", Number(4)))))
  }
}