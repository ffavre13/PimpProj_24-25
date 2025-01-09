import javax.swing.{JFrame, JOptionPane}

object DialogBox {
  def showDialog(title: String, content: String): Unit = {
    val frame = new JFrame(title)
    JOptionPane.showMessageDialog(frame, content)
  }
}
