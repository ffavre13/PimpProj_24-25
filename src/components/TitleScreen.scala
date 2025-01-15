package components

import hevs.graphics.FunGraphics
import hevs.graphics.utils.GraphicsBitmap

import java.awt.event.{MouseAdapter, MouseEvent}

class TitleScreen(display: FunGraphics) {

  val playButton: Button = new Button(display, display.width/3, 430, display.width/3, 40, "PLAY THE GAME")
  val quitButton: Button = new Button(display, display.width/3, 500, display.width/3, 40, "QUIT :(")
  val easterEggButton: Button = new Button(display, 100, 50, 550, 200, visible = false)

  drawTitleImg()

  /**
   * Draws the AWESOME title image
   *
   * @param broken Tells if the title image is... broken ?
   */
  def drawTitleImg(broken: Boolean = false): Unit = {
    // Title image
    val titleImg = if (!broken) new GraphicsBitmap("/res/img/title.png") else new GraphicsBitmap("/res/img/broken_title.png")
    val imgScale = 0.4
    var imgAngle = 0
    val imgX = display.getFrameWidth / 2
    val imgY = display.getFrameHeight() / 4

    display.drawTransformedPicture(imgX, imgY, imgAngle, imgScale, titleImg)
  }
}
