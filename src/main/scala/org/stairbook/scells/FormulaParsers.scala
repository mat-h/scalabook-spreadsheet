package org.stairbook.scells

import scala.util.parsing.combinator.RegexParsers

object FormulaParsers extends RegexParsers {
  def ident: Parser[String] = """[a-zA-Z_]\w*""".r
  def decimal: Parser[String] = """-?\d+(\.\d+)?""".r
  def number: Parser[Number] = decimal ^^ { s => Number(s.toDouble) }
  def cell: Parser[Coord] = """[a-zA-Z]\d""".r ^^ { s =>
    val col = s.charAt(0).toUpper - 'A'
    val row = s.substring(1).toInt
    Coord(row, col)
  }
  def range: Parser[Range] = cell ~ ":" ~ cell ^^ {
    case from ~ ":" ~ to => Range(from, to)
  }
  def expr: Parser[Formula] = range | cell | number | application
  def application: Parser[Application] =
    ident ~ "(" ~ repsep(expr, ",") ~ ")" ^^ {
      case fun ~ "(" ~ args ~ ")" => Application(fun, args)
    }
  def textual: Parser[Textual] = """[^=].*""".r ^^ Textual
  def formula: Parser[Formula] = number | textual | "=" ~> expr

  def parse(input: String): Formula =
    parseAll(formula, input) match {
      case Success(e, _) => e
      case f: NoSuccess  => Textual("[" + f.msg + "]")
    }
}