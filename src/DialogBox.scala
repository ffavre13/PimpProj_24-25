import javax.swing.{JFrame, JOptionPane}

object DialogBox {
  /**
   * Shows a dialog using JOptionPane object
   * @param title Title of the Dialog
   * @param content Content of the Dialog
   */
  def showDialog(title: String, content: String): Unit = {
    val frame = new JFrame(title)
    JOptionPane.showMessageDialog(frame, content)
  }
}
