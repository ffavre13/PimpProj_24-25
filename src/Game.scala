import hevs.graphics.FunGraphics
import java.awt.Color
import java.awt.event.{KeyEvent, KeyListener}

object Game extends App {
  // Update the position of the players in the grid
  def updateGrid(player1: Player, player2: Player, a: Array[Array[Int]]): Array[Array[Int]] = {
    val tmp: Array[Array[Int]] = a.clone()
    try {
      if (tmp(player1.getPosY())(player1.getPosX()) == 0 && tmp(player2.getPosY())(player2.getPosX()) == 0) {
        tmp(player1.getPosY())(player1.getPosX()) = 1
        tmp(player2.getPosY())(player2.getPosX()) = 2
      }
      else if (tmp(player1.getPosY())(player1.getPosX()) == 2 || tmp(player1.getPosY())(player1.getPosX()) == 1) {
        println("Game over player 2 win")
        System.exit(0)
      }
      else {
        println("Game over player 1 win")
        System.exit(0)
      }
    }
    catch {
      case e: IndexOutOfBoundsException => {
        println("Game over you have hit a wall")
        System.exit(0)
      }
    }

    return tmp
  }

  // Update Display with a grid
  def updateDisplay(a: Array[Array[Int]], cP1: Color, cP2: Color): Unit = {
    for (l <- a.indices) {
      for (c <- a(0).indices) {
        if (a(l)(c) == 1) {
          display.setColor(cP1)
          display.drawCircle(c * sizeMult, l * sizeMult, sizeMult)
        }
        else if (a(l)(c) == 2) {
          display.setColor(cP2)
          display.drawCircle(c * sizeMult, l * sizeMult, sizeMult)
        }
      }
    }
  }

  val dimGrid: Int = 20 // size of the grid
  val sizeMult: Int = 60 // size multiplier for display
  val gameTickSpeed: Int = 250

  val p1: Player = new Player(0, 0) // Player 1
  val p2: Player = new Player(dimGrid - 1, dimGrid - 1) // Player 2
  val colorP1: Color = Color.RED // Color player 1
  val colorP2: Color = Color.BLUE // Color player 2
  var directionP1: String = "right" // Direction for player 1
  var directionP2: String = "left" // Direction for player 2

  val grid: Array[Array[Int]] = Array.ofDim(dimGrid, dimGrid) // Grid for the game
  val display: FunGraphics = new FunGraphics(dimGrid * sizeMult, dimGrid * sizeMult) // Display Windows

  // Draw grid on display windows
  for (i <- 1 until dimGrid) {
    display.drawLine(0, i * sizeMult, dimGrid * sizeMult, i * sizeMult)
    display.drawLine(i * sizeMult, 0, i * sizeMult, dimGrid * sizeMult)
  }

  // Game
  while (true) {
    display.setKeyManager(new KeyListener {
      override def keyTyped(e: KeyEvent): Unit = {

      }

      override def keyPressed(e: KeyEvent): Unit = {
        e.getKeyChar match {
          case 'w' => directionP1 = "up"
          case 'a' => directionP1 = "left"
          case 's' => directionP1 = "down"
          case 'd' => directionP1 = "right"

          case 'i' => directionP2 = "up"
          case 'j' => directionP2 = "left"
          case 'k' => directionP2 = "down"
          case 'l' => directionP2 = "right"
        }
      }

      override def keyReleased(e: KeyEvent): Unit = {

      }
    })

    updateGrid(p1, p2, grid)

    updateDisplay(grid, colorP1, colorP2)

    Thread.sleep(gameTickSpeed)

    directionP1 match {
      case "up" => p1.setPosY(p1.getPosY() - 1)
      case "left" => p1.setPosX(p1.getPosX() - 1)
      case "down" => p1.setPosY(p1.getPosY() + 1)
      case "right" => p1.setPosX(p1.getPosX() + 1)
    }

    directionP2 match {
      case "up" => p2.setPosY(p2.getPosY() - 1)
      case "left" => p2.setPosX(p2.getPosX() - 1)
      case "down" => p2.setPosY(p2.getPosY() + 1)
      case "right" => p2.setPosX(p2.getPosX() + 1)
    }
  }
}
