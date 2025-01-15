package components

import javax.swing.{JButton, JFrame, JOptionPane}

object DialogBox {
  /**
   * Shows a dialog using JOptionPane object
   *
   * @param title   Title of the Dialog
   * @param content Content of the Dialog
   */
  def showDialog(title: String, content: String): Unit = {
    val frame: JFrame = new JFrame(title)
    JOptionPane.showMessageDialog(frame, content)
  }

  /**
   * Shows the Replay Dialog using JOptionPane object
   *
   * @param title   Title of the Dialog
   * @param content Content of the Dialog
   * @return True if the player wants to replay
   */
  def showDialogReplay(title: String, content: String): Boolean = {
    val frame: JFrame = new JFrame(title)

    val choice: Int = JOptionPane.showOptionDialog(
      frame,
      content,
      title,
      JOptionPane.YES_NO_OPTION,
      JOptionPane.INFORMATION_MESSAGE,
      null,
      null,
      JOptionPane.YES_OPTION
    )

    choice == JOptionPane.YES_OPTION
  }
}
