package tinyLang

final case class Machine(environment: Map[String, AnyVal] = Map()) {
  var env = environment;

  override def equals(that: Any): Boolean = {
    that match {
      case that: Machine => {
        println(this.env)
        println(that.env)
        this.env == that.env
      }
      case _ => false
    }
  }

  def run(expr: Expr, env: Map[String, AnyVal]): Expr = {
    println(expr)

    if (expr.isReducible)
      run(reductionStep(expr, env), env)
    else
      expr
  }

  def reductionStep(expr: Expr,
                    env: Map[String, AnyVal]): Expr = expr match {
    case Sum(lOp, rOp) => {
      if (lOp.isReducible) {
        Sum(reductionStep(lOp, env), rOp)
      } else if (rOp.isReducible) {
        Sum(lOp, reductionStep(rOp, env))
      } else {
        (lOp, rOp) match {
          case (lOp: Number, rOp: Number) => Number(lOp.n + rOp.n)
          case _ =>
            throw new Exception(s"Cannot sum ${lOp} and ${rOp}")
        }
      }
    }

    case Prod(lOp, rOp) => {
      if (lOp.isReducible) {
        Prod(reductionStep(lOp, env), rOp)
      } else if (rOp.isReducible) {
        Prod(lOp, reductionStep(rOp, env))
      } else {
        (lOp, rOp) match {
          case (lOp: Number, rOp: Number) => Number(lOp.n * rOp.n)
          case _ =>
            throw new Exception("Incompatible types")
        }
      }
    }

    case LessThan(lOp, rOp) => {
      if (lOp.isReducible) {
        LessThan(reductionStep(lOp, env), rOp)
      } else if (rOp.isReducible) {
        LessThan(lOp, reductionStep(rOp, env))
      } else {
        (lOp, rOp) match {
          case (lOp: Number, rOp: Number) => Bool(lOp.n < rOp.n)
          case _ =>
            throw new Exception("Incompatible types")
        }
      }
    }

    case IfElse(condition, ifBlock, elseBlock) => {
      if (condition.isReducible) {
        IfElse(reductionStep(condition, env), ifBlock, elseBlock)
      } else {
        condition match {
          case (condition: Bool) => if (condition.bool) ifBlock else elseBlock
          case _ =>
            throw new Exception("Condition of expression` " + condition + "` should be a Bool expression")
        }
      }
    }

    case Var(name: String) => env.get(name) match {
      case Some(boolValue: Boolean) => Bool(boolValue)
      case Some(intValue: Int)      => Number(intValue)
      case otherValue               => throw new Exception(s"Unsupported primitive ${otherValue}.")
    }

    case _ => throw new Exception("Unrecognized expression ${expr}.")
  }

  def exec(stat: Stat): Machine = {
    stat match {
      case DoNothing() => {}

      case Assign(name, expression) => {
        val expressionValue = this.run(expression, this.env) match {
          case Bool(boolValue) => boolValue
          case Number(n)       => n
          case expression      => throw new Exception("Unrecognized primitive expression ${expression} cannot be assigned to ${name} variable.")
        }

        this.env = this.env.updated(name, expressionValue)
      }

      case IfElseStat(condition, ifStat, elseStat) => this.run(condition, this.env) match {
        case Bool(true)  => this.exec(ifStat)
        case Bool(false) => this.exec(elseStat)
        case _           => throw new Exception(s"Cannot evalute condition ${condition}.")
      }

      case SeqStat(stats @ _*) => stats.foreach(stat => this.exec(stat))

      case While(expression, stat) => this.run(expression, this.env) match {
        case Bool(true) => {
          this.exec(stat)
          this.exec(While(expression, stat))
        }

        case _ => {}
      }

      case _ =>
        throw new Exception(s"Unrecognized statement ${stat}.")
    }

    this
  }
}