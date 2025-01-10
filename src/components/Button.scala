package components

import hevs.graphics.FunGraphics

import java.awt.Color
import javax.swing.SwingConstants

/**
 * Creates a button on the screen at the coordinates given in parameter
 *
 * @param display Display object where the button will be drawn
 * @param x      X position of the button
 * @param y      Y position of the button
 * @param width  Button's width
 * @param height Button's height
 * @param text   Text to display in the button
 */
class Button(display: FunGraphics, x: Int, y: Int, width: Int = 300, height: Int = 40, text: String = "Button") {

  var borderSize: Int = 10
  display.setColor(Color.DARK_GRAY)
  display.drawFillRect(x - (borderSize / 2), y - (borderSize / 2), width + borderSize, height + borderSize)
  display.setColor(Color.LIGHT_GRAY)
  display.drawFillRect(x, y, width, height)
  display.drawFancyString(
    x + (width / 2),
    y + (height / 2),
    text,
    fontFamily = "Comic Sans MS",
    color = Color.BLACK,
    halign = SwingConstants.CENTER,
    valign = SwingConstants.CENTER
  )

  def checkButtonPressed(mouseX: Int, mouseY: Int): Boolean =
    mouseX > x && mouseX < x + width &&
      mouseY > y && mouseY < y + height
}
