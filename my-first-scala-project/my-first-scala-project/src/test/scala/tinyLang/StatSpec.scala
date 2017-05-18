package tinyLang

import org.scalatest.FlatSpec

class StatSpec extends FlatSpec {
  it should "print sequense of doNothing; assign; doNothing" in {
    println(
      SeqStat(
        IfElseStat(
          Bool(true),
          Assign("x", Number(0)),
          DoNothing()),
        Assign("x", Number(1)),
        IfElseStat(
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