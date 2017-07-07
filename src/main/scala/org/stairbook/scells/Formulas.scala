package org.stairbook.scells

trait Formula {}

case class Coord(row: Int, col: Int) extends Formula {
  override def toString = ('A' + col).toChar.toString + row
}

case class Range(from: Coord, to: Coord) extends Formula {
  override def toString = from.toString + ":" + to.toString
}

case class Number(value: Double) extends Formula {
  override def toString = value.toString
}

case class Textual(value: String) extends Formula {
  override def toString = value
}

case class Application(function: String, arguments: List[Formula]) extends Formula {
  override def toString = function + arguments.mkString("(", ",", ")")
}

object Empty extends Textual("")