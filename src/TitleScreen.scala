import hevs.graphics.FunGraphics
import hevs.graphics.utils.GraphicsBitmap

import java.awt.Color
import javax.swing.SwingConstants

object TitleScreen extends App {
  val display = new FunGraphics(600, 700)

  drawTitleImg()
  drawButton(200, 430, 200, 40, "PLAY THE GAME")
  drawButton(200, 500, 200, 40, "QUIT :(")

  /**
   * Draws the AWESOME title image
   */
  def drawTitleImg(): Unit = {
    // Title image
    val titleImg = new GraphicsBitmap("/img/title.png")
    val imgScale = 0.20
    var imgAngle = 0
    val imgX = display.getFrameWidth / 2
    val imgY = display.getFrameHeight() / 5

    display.drawTransformedPicture(imgX, imgY, imgAngle, imgScale, titleImg)
  }

  /**
   * Draws a button at the coordinates given in parameter
   *
   * @param x      X position of the button
   * @param y      Y position of the button
   * @param width  Button's width
   * @param height Button's height
   * @param text   Text to display in the button
   */
  def drawButton(x: Int, y: Int, width: Int, height: Int, text: String): Unit = {
    var borderSize: Int = 10
    display.setColor(Color.DARK_GRAY)
    display.drawFillRect(x - (borderSize/2), y - (borderSize/2), width + borderSize, height + borderSize)
    display.setColor(Color.LIGHT_GRAY)
    display.drawFillRect(x, y, width, height)
    display.drawFancyString(x + (width/2), y + (height/2), text, color = Color.BLACK, halign = SwingConstants.CENTER, valign = SwingConstants.CENTER)
  }
}
