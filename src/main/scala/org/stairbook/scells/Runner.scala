package org.stairbook.scells
import swing._

object Runner extends SimpleSwingApplication {
  def top = new MainFrame {
    title = "Sheeeeeet"
    val sheet = new Spreadsheet(100,26)
    contents = sheet
  }
}