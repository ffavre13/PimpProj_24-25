package components

import hevs.graphics.FunGraphics
import hevs.graphics.utils.GraphicsBitmap

import java.awt.event.{MouseAdapter, MouseEvent}

class TitleScreen(display: FunGraphics) {

  drawTitleImg()

  var playButton: Button = new Button(display, display.width/3, 430, display.width/3, 40, "PLAY THE GAME")
  var quitButton: Button = new Button(display, display.width/3, 500, display.width/3, 40, "QUIT :(")

  var playButtonPressed: Boolean = false
  var quitButtonPressed: Boolean = false

  display.addMouseListener(new MouseAdapter() {
    override def mouseClicked(e: MouseEvent): Unit = {
      val event = e

      if (playButton.checkButtonPressed(event.getX, event.getY)) {
        playButtonPressed = true
      }
      if (quitButton.checkButtonPressed(event.getX, event.getY)) {
        quitButtonPressed = true
      }

    }
  })

  def playButtonisPressed(): Boolean = playButtonPressed
  def quitButtonisPressed(): Boolean = quitButtonPressed

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
}
