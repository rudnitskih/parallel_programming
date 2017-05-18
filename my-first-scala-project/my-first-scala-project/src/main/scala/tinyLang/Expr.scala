package tinyLang

sealed trait Expr {
  def isReducible = true

  def show: String = this match {
    case Var(name)                             => name
    case Bool(value)                           => value.toString
    case Number(n)                             => n.toString
    case Sum(lOp, rOp)                         => lOp.show + " + " + rOp.show
    case Prod(lOp, rOp)                        => showSubexpression(lOp) + " * " + showSubexpression(rOp)
    case LessThan(lOp, rOp)                    => lOp.show + " < " + rOp.show
    case IfElse(condition, ifBlock, elseBlock) => s"if (${condition.show}) { ${ifBlock.show} } else { ${elseBlock.show} }"
  }

  override def equals(that: Any): Boolean = that match {
    case that: Expr => this.show == that.show
    case _          => false
  }

  override def toString: String = show

  private def showSubexpression(operand: Expr): String = operand match {
    case Sum(_, _) => "(" + operand.show + ")"
    case _         => operand.show
  }
}

case class Number(n: Int) extends Expr {
  override def isReducible = false
}

case class Bool(bool: Boolean) extends Expr {
  override def isReducible = false
}

case class Var(name: String) extends Expr

case class Sum(lOp: Expr, rOp: Expr) extends Expr

case class Prod(lOp: Expr, rOp: Expr) extends Expr

case class LessThan(lOp: Expr, rOp: Expr) extends Expr

case class IfElse(condition: Expr, ifBlock: Expr, elseBlock: Expr) extends Expr

