package org.stairbook.scells

class Model(val width: Int, val height: Int) {
  case class Cell(row: Int, col: Int)
  val cells = Array.ofDim[Cell](height, width)

  for (i <- 0 until height; j <- 0 until width) {
    cells(i)(j) = new Cell(i, j)
  }
}