package org.stairbook.scells
import swing._

class Spreadsheet (val width: Int, val height: Int) extends ScrollPane {
  val table = new Table(width, height) {
    showGrid = true  
    rowHeight = 25
    autoResizeMode = Table.AutoResizeMode.Off
    gridColor = new java.awt.Color(150,150,150)
  }
  viewportView = table
  
  val rowNumbers = new ListView ((0 until height).map(_.toString)) {
    fixedCellWidth = 30
    fixedCellHeight = table.rowHeight
  }
  rowHeaderView = rowNumbers
}