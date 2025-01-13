package components

import hevs.graphics.FunGraphics
import hevs.graphics.utils.GraphicsBitmap

import java.awt.event.{MouseAdapter, MouseEvent}

class TitleScreen(display: FunGraphics) {

  private val playButton: Button = new Button(display, display.width/3, 430, display.width/3, 40, "PLAY THE GAME")
  private val quitButton: Button = new Button(display, display.width/3, 500, display.width/3, 40, "QUIT :(")
  private val easterEggButton: Button = new Button(display, 100, 50, 550, 200, visible = false)

  private var playButtonPressed: Boolean = false
  private var quitButtonPressed: Boolean = false
  private var easterEggBtnCount: Int = 0

  drawTitleImg()



  display.addMouseListener(new MouseAdapter() {
    override def mouseClicked(e: MouseEvent): Unit = {
      val event = e

      if (playButton.checkButtonPressed(event.getX, event.getY)) {
        playButtonPressed = true
      }
      if (quitButton.checkButtonPressed(event.getX, event.getY)) {
        quitButtonPressed = true
      }
      if (easterEggButton.checkButtonPressed(event.getX, event.getY)) {
        easterEggBtnCount += 1

        easterEggBtnCount match {
          case 1 =>
            DialogBox.showDialog(">:(", "Stop touching it, you'll break it...")
          case 2 => {
            drawTitleImg(broken = true)
            DialogBox.showDialog(">:(", "CONGRATS, YOU BROKE IT !")
          }

          case _ =>
        }
      }

    }
  })

  def playButtonIsPressed(): Boolean = playButtonPressed
  def quitButtonIsPressed(): Boolean = quitButtonPressed

  /**
   * Draws the AWESOME title image
   *
   * @param broken Tells if the title image is... broken ?
   */
  def drawTitleImg(broken: Boolean = false): Unit = {
    // Title image
    val titleImg = if (!broken) new GraphicsBitmap("/img/title.png") else new GraphicsBitmap("/img/broken_title.png")
    val imgScale = 0.20
    var imgAngle = 0
    val imgX = display.getFrameWidth / 2
    val imgY = display.getFrameHeight() / 5

    display.drawTransformedPicture(imgX, imgY, imgAngle, imgScale, titleImg)
  }
}
