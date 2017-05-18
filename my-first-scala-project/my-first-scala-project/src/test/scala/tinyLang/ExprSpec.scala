package tinyLang

import org.scalatest.FlatSpec

class ExprSpec extends FlatSpec {
  it should "show number" in {
    assert(Number(3).show === "3")
  }

  it should "show simple sum of numbers" in {
    assert(Sum(Number(3), Number(5)).show === "3 + 5")
  }

  it should "show simple prod of numbers" in {
    assert(Prod(Number(3), Number(5)).show === "3 * 5")
  }

  it should "show prod of number and var" in {
    assert(Prod(Number(3), Var("x")).show === "3 * x")
  }

  it should "show prod of number and var plus var" in {
    assert(Sum(Prod(Number(3), Var("x")), Var("y")).show === "3 * x + y")
  }

  it should "show sum of number and prod of vars" in {
    assert(Sum(Number(3), Prod(Var("x"), Var("y"))).show === "3 + x * y")
  }

  it should "show sum of number and var multiply by var" in {
    assert(Prod(Sum(Number(3), Var("x")), Var("y")).show === "(3 + x) * y")
  }

  it should "show prod of number and sum of vars" in {
    assert(
      Prod(
        Number(3),
        Sum(
          Var("x"),
          Var("y"))).show === "3 * (x + y)")
  }

  it should "show prod of two sums" in {
    assert(
      Prod(
        Sum(
          Number(42),
          Number(43)),
        Sum(
          Var("x"),
          Var("y"))).show === "(42 + 43) * (x + y)")
  }

  it should "show 3 < 5" in {
    assert(
      LessThan(Number(3), Number(5)).show === "3 < 5")
  }

  it should "show ifElse statement" in {
    assert(
      IfElse(Bool(true), Number(42), Number(43)).show === "if (true) { 42 } else { 43 }")
  }
}