package tinyLang
import scala.util.{ Try, Success, Failure }

final case class Machine() {
  def reduce(expr: Expr, env: Map[String, Any]): Expr = {
    println(expr)

    Try({
      if (expr.isReducible)
        reduce(reductionStep(expr, env), env)
      else
        expr
    }) match {
      case Failure(e) => expr
      case Success(v) => v
    }
  }

  def reductionStep(expr: Expr,
                    env: Map[String, Any]): Expr = {
    Try({
      expr match {
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
                throw new Exception(s"Cannot prod ${lOp} and ${rOp}")
            }
          }
        }

        case Less(lOp, rOp) => {
          if (lOp.isReducible) {
            Less(reductionStep(lOp, env), rOp)
          } else if (rOp.isReducible) {
            Less(lOp, reductionStep(rOp, env))
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
          case otherValue               => throw new Exception(s"Unknown variable ${name}.")
        }

        case _ => throw new Exception("Unrecognized expression ${expr}.")
      }
    }) match {
      case Failure(e) => throw new Exception(s"\nCannot evaluate expression `$expr` because of: \n   - ${e.getMessage} \n")
      case Success(v) => v
    }
  }

  def run(stat: Stat,
          env: Map[String, Any] = Map()): Map[String, Any] = {
    stat match {
      case DoNothing() => env

      case Assign(name, expression) => {
        val reducedExpression = this.reduce(expression, env)

        if (reducedExpression == expression && reducedExpression.isReducible) {
          env.updated("___error", s"Cannot evalute expression `$expression`")
        } else {
          reducedExpression match {
            case Bool(boolValue) => env.updated(name, boolValue)
            case Number(n)       => env.updated(name, n)
            case _               => env.updated("___error", s"Cannot assign expression `reducedExpression`")
          }
        }
      }

      case If(condition, ifStat, elseStat) => {
        val reducedCondition = this.reduce(condition, env)

        if (reducedCondition == condition && reducedCondition.isReducible) {
          env.updated("___error", s"Cannot evalute condition `$reducedCondition`")
        } else {
          reducedCondition match {
            case Bool(true)  => this.run(ifStat, env)
            case Bool(false) => this.run(elseStat, env)
            case _           => env.updated("___error", s"Incorrect condition `${condition}`.")
          }
        }
      }

      case Seq(stats @ _*) => {
        val newEnv = this.run(stats.head, env)
        val error = newEnv.get("___error")

        if (error == None) {
          val otherStatments = stats.tail

          if (!otherStatments.isEmpty) {
            this.run(Seq(otherStatments: _*), newEnv)
          } else {
            newEnv
          }
        } else {
          env.updated("___error", s"Cannot Seq because of `$error`")
        }
      }

      case While(expression, stat) => {
        val reducedExpression = this.reduce(expression, env)

        if (reducedExpression == expression && reducedExpression.isReducible) {
          env.updated("___error", s"Cannot While because of ${"TODO"}")
        } else {
          reducedExpression match {
            case Bool(true) => {
              val newEnv = this.run(stat, env)
              val error = newEnv.get("___error")

              if (error == None) {
                this.run(While(expression, stat), newEnv)
              } else {
                newEnv.updated("___error", s"Cannot While because of $error")
              }
            }

            case _ => env
          }
        }

      }

      case _ => env.updated("___error", s"Unrecognized statement ${stat}.")
    }
  }
}