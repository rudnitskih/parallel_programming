package tinyLang

import org.scalatest._

class MachineSpec extends FunSpec {
  describe("Reduction model") {
    it("should reduce `(1 + 2) + (4 + 3)` to 3 + (4 + 3)") {
      assert(
        new Machine().reductionStep(
          Prod(
            Sum(
              Number(1),
              Number(2)),
            Sum(
              Number(4),
              Number(3))),
          Map())
          ==
          Prod(
            Number(3),
            Sum(
              Number(4),
              Number(3))))
    }

    it("should reduce `3 + (5 + (-3))` to 3 + 2") {
      assert(
        new Machine().reductionStep(
          Sum(
            Number(3),
            Sum(
              Number(5),
              Number(-3))), Map())
          ===
          Sum(
            Number(3),
            Number(2)))
    }

    it("should reduce `((1 + 1) * 1 + 1) * 1` to `(2 * 1 + 1) * 1`") {
      assert(
        new Machine().reductionStep(
          Prod(
            Sum(
              Prod(
                Sum(
                  Number(1),
                  Number(1)),
                Number(1)),
              Number(1)),
            Number(1)),
          Map())
          ===
          Prod(
            Sum(
              Prod(
                Number(2),
                Number(1)),
              Number(1)),
            Number(1)))
    }

    it("should reduce `(x + 2) * (4 + y)` where x = 1 and y = 3 to `(1 + 2) * (4 + y)`") {
      assert(
        new Machine().reductionStep(
          Prod(
            Sum(
              Var("x"),
              Number(2)),
            Sum(
              Number(4),
              Var("y"))),
          Map("x" -> 1,
            "y" -> 3))
          ===
          Prod(
            Sum(
              Number(1),
              Number(2)),
            Sum(
              Number(4),
              Var("y"))))
    }
  }

  describe("Expression evaluation") {
    describe("Primitive values") {
      it("should not reduce Bool expression") {
        assert(
          new Machine().run(
            Bool(true),
            Map()) == Bool(true))
      }

      it("should not reduce Number expression") {
        assert(
          new Machine().run(
            Number(42),
            Map()) == Number(42))
      }
    }

    describe("Sum") {
      it("should reduce 3 + 5 expression") {
        assert(
          new Machine().run(
            Sum(
              Number(3),
              Number(5)),
            Map()) === Number(8))
      }

      it("should reduce 3 + 5 - 3 expression") {
        assert(
          new Machine().run(
            Sum(
              Number(3),
              Sum(
                Number(5),
                Number(-3))),
            Map()) === Number(5))
      }
    }

    describe("Prod") {
      it("should reduce 3 * 5 expression") {
        assert(
          new Machine().run(
            Prod(
              Number(3),
              Number(5)),
            Map()) === Number(15))
      }
    }

    describe("LessThan") {
      it("should reduce 3 < 5 expression to Bool true") {
        assert(
          new Machine().run(
            LessThan(
              Number(3),
              Number(5)),
            Map()) === Bool(true))
      }

      it("should reduce 5 < 5 expression to Bool false") {
        assert(
          new Machine().run(
            LessThan(
              Number(5),
              Number(5)),
            Map()) === Bool(false))
      }
    }

    describe("If-Else") {
      it("should reduce If-Else expression to Number 42") {
        assert(
          new Machine().run(
            IfElse(
              Bool(true),
              Number(42),
              Number(43)),
            Map()) === Number(42))
      }

      it("should reduce If-Else expression to Number 43") {
        assert(
          new Machine().run(
            IfElse(
              Bool(false),
              Number(42),
              Sum(
                Number(42),
                Number(1))),
            Map()) === Number(43))
      }

      it("should reduce If-Else expression to Number 42 if condition LessThan expression") {
        assert(
          new Machine().run(
            IfElse(
              LessThan(
                Number(3),
                Number(5)),
              Number(42),
              Number(43)),
            Map()) === Number(42))
      }
    }

    describe("Combination of expressions") {
      it("should reduce (1 + 2) < (3 * 4) to `true`") {
        assert(
          new Machine().run(
            LessThan(
              Sum(
                Number(1),
                Number(2)),
              Prod(
                Number(3),
                Number(4))),
            Map()) === Bool(true))
      }

      it("should reduce `(1 + 2) * (4 + 3)` to `21`") {
        assert(
          new Machine().run(
            Prod(
              Sum(
                Number(1),
                Number(2)),
              Sum(
                Number(4),
                Number(3))),
            Map())
            ===
            Number(21))
      }

      it("should calculate ((1 + 1) * 1 + 1) * 1") {
        assert(
          new Machine().run(
            Prod(
              Sum(
                Prod(
                  Sum(
                    Number(1),
                    Number(1)),
                  Number(1)),
                Number(1)),
              Number(1)),
            Map()) === Number(3))
      }

      it("should calculate (x + 2) * (4 + y) where x = 1 and y = 3") {
        assert(
          new Machine().run(
            Prod(
              Sum(
                Var("x"),
                Number(2)),
              Sum(
                Number(4),
                Var("y"))),
            Map("x" -> 1,
              "y" -> 3)) === Number(21))
      }

      it("should calculate if (42 < 43) { 42 } else { 43 < 42 } to false") {
        assert(
          new Machine().run(
            IfElse(
              LessThan(
                Number(43),
                Number(42)),
              Number(42),
              Sum(
                Number(42),
                Number(42))),
            Map()) === Number(84))
      }

      it("should calculate (1 + 2 + 3) * (4 + 5)") {
        assert(
          new Machine().run(
            Prod(
              Sum(
                Sum(
                  Number(1),
                  Number(2)),
                Number(3)),
              Sum(
                Number(4),
                Number(5))),
            Map()) === Number(54))
      }
    }
  }

  describe("Statements execution") {
    it("two empty machines is equal") {
      assert(
        new Machine()
          .exec(DoNothing())
          .env
          ===
          new Machine(Map())
          .exec(DoNothing())
          .env)
    }

    it("should add value to variable") {
      assert(
        new Machine(Map("x" -> 0)).exec(Assign("x", Number(1))).env
          ===
          new Machine(Map("x" -> 1)).exec(DoNothing()).env)
    }

    it("should execute sequance of statements") {
      assert(
        new Machine(Map("x" -> 0)).exec(
          SeqStat(
            Assign("x", Number(1)),
            DoNothing(),
            Assign("x", Number(2)))).env
          ===
          new Machine(Map("x" -> 2)).exec(DoNothing()).env)
    }
    
    it("should execute while statement") {
      assert(
        new Machine(Map("x" -> 0, "y" -> 0)).exec(
          While(
              LessThan(Var("x"), Number(10)),
              SeqStat(
                Assign("x", Sum(Var("x"), Number(1))),
                Assign("y", Sum(Var("y"), Number(2)))
              )
         )).env
          
          ===
          new Machine(Map("x" -> 10, "y" -> 20)).exec(DoNothing()).env)
    }
  }
}