package org.stairbook.scells
import swing._

class Spreadsheet (val width: Int, val height: Int) extends ScrollPane {
  val table = new Table(width, height) {
    showGrid = true  
  }
  viewportView = table
}