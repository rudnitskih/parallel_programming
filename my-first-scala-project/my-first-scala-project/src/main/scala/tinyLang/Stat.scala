package tinyLang

sealed trait Stat {
  override def toString = this match {
    case DoNothing()              => ";nothing;\n"

    case Assign(name, expression) => s"""${name} = ${expression}\n"""

    case IfElseStat(condition, ifStat, elseStat) => s"""
if (${condition}) {
    ${ifStat}} else {
    ${elseStat}}\n
"""
    case SeqStat(stats @ _*) => {
      stats.mkString("", " ", " ")
    }

    case While(expr, stat) => s"""
while(${expr}) {
    ${stat}}\n
"""
  }
}

case class DoNothing() extends Stat
case class Assign(name: String, expression: Expr) extends Stat
case class IfElseStat(condition: Expr, ifStat: Stat, elseStat: Stat) extends Stat
case class SeqStat(stats: Stat*) extends Stat
case class While(expr: Expr, stat: Stat) extends Stat