package org.stairbook.scells
import swing._
import scala.swing.event.TableUpdated

class Spreadsheet(val width: Int, val height: Int) extends ScrollPane {
  
  val cellModel = new Model(height, width)
  import cellModel._
  
  val table = new Table(width, height) {
    showGrid = true
    rowHeight = 25
    autoResizeMode = Table.AutoResizeMode.Off
    gridColor = new java.awt.Color(150, 150, 150)

    override def rendererComponent(isSelected: Boolean, focused: Boolean, row: Int, col: Int): Component = {
      if (hasFocus) new TextField(userData(row, col))
      else new Label(cells(row)(col).toString) { xAlignment = Alignment.Right }
    }

    def userData(row: Int, col: Int): String = {
      val v = this(row, col)
      if (v == null) "" else v.toString
    }
    
    reactions += {
      case TableUpdated(table, rows, col) => 
        for (row <- rows) 
          cells(row)(col).formula = FormulaParsers.parse(userData(row, col))
    }
  }
  viewportView = table

  val rowNumbers = new ListView((0 until height).map(_.toString)) {
    fixedCellWidth = 30
    fixedCellHeight = table.rowHeight
  }
  rowHeaderView = rowNumbers
}