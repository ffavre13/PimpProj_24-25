import hevs.graphics.FunGraphics

import java.awt.{Color, Dialog}
import java.awt.event.{KeyAdapter, KeyEvent, KeyListener}

object Game extends App {
  // Game settings
  val dimGrid: Int = 20 // Size of the grid
  val sizeMult: Int = 20 // Size multiplier for display
  val fps: Int = 120 // Number of Frames per second generated
  val speed: Int = 500 // Speed of the game
  val showGrid: Boolean = true // Tells if the game should render the game grid

  val p1: Player = new Player(0, 0) // Player 1
  val p2: Player = new Player(dimGrid - 1, dimGrid - 1) // Player 2
  val colorP1: Color = Color.RED // Color player 1
  val colorP2: Color = Color.BLUE // Color player 2
  var directionP1: String = "right" // Direction for player 1
  var directionP2: String = "left" // Direction for player 2
  var gaming: Boolean = true // Tells if the game is ongoing

  var grid: Array[Array[Int]] = Array.ofDim(dimGrid, dimGrid) // Grid for the game
  val display: FunGraphics = new FunGraphics(dimGrid * sizeMult, dimGrid * sizeMult) // Display Windows

  /**
   * Updates the position of the players in the grid with the player objects given in parameter
   *
   * @param player1 Player 1 object
   * @param player2 Player 2 object
   * @param a       Array containing current game state
   * @return Returns an array containing the new game state
   */
  def updateGrid(player1: Player, player2: Player, a: Array[Array[Int]]): Array[Array[Int]] = {
    val tmp: Array[Array[Int]] = a.clone()
    // Check lose conditions
    if (isOutOfBounds(player1) && isOutOfBounds(player2)) {
      DialogBox.showDialog("Game over", "Player 1 and 2 loose, you both hit a wall")
      gaming = false
    }
    else if (isOutOfBounds(player1)) {
      DialogBox.showDialog("Game over", "Player 1 you have hit a wall. PLAYER 2 WINS")
      gaming = false
    }
    else if (isOutOfBounds(player2)) {
      DialogBox.showDialog("Game over", "Player 2 you have hit a wall. PLAYER 1 WINS")
      gaming = false
    }
    else if (tmp(player1.getPosY())(player1.getPosX()) == 2 || tmp(player1.getPosY())(player1.getPosX()) == 1 || tmp(player1.getPosY())(player1.getPosX()) >= 3) {
      DialogBox.showDialog("Game over", "Game over PLAYER 2 WINS")
      gaming = false
    }
    else if (tmp(player2.getPosY())(player2.getPosX()) == 1 || tmp(player2.getPosY())(player2.getPosX()) == 2 || tmp(player2.getPosY())(player2.getPosX()) >= 3) {
      DialogBox.showDialog("Game over", "Game over PLAYER 1 WINS")
      gaming = false
    }

    // Apply positions
    if (gaming) {
      tmp(player1.getPosY())(player1.getPosX()) += 1
      tmp(player2.getPosY())(player2.getPosX()) += 2
    }


    return tmp
  }

  /**
   * Tells if the player is out of the game's bound
   *
   * @param player Player to check
   * @return True if player is out of bounds
   */
  def isOutOfBounds(player: Player): Boolean = {
    return (player.getPosX() < 0 || player.getPosX() > dimGrid - 1 ||
      player.getPosY() < 0 || player.getPosY() > dimGrid - 1)
  }

  /**
   * Updates display
   *
   * @param a   Array containing game state
   * @param cP1 Player 1 color to display
   * @param cP2 Player 2 color to display
   */
  def updateDisplay(a: Array[Array[Int]], cP1: Color, cP2: Color): Unit = {
    display.frontBuffer.synchronized {
      display.clear(Color.white)

      // Draw grid on display windows
      if (showGrid) {
        display.setColor(Color.BLACK)
        for (i <- 1 until dimGrid) {
          display.drawLine(0, i * sizeMult, dimGrid * sizeMult, i * sizeMult)
          display.drawLine(i * sizeMult, 0, i * sizeMult, dimGrid * sizeMult)
        }
      }

      // Draw players
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
          else if (a(l)(c) == 3) {
            display.setColor(Color.ORANGE)
            display.drawCircle(c * sizeMult, l * sizeMult, sizeMult)
          }
        }
      }
    }

  }

  // Game
  while (gaming) {
    display.setKeyManager(new KeyAdapter {
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

          case _ =>
        }

        e.getKeyCode match {
          case KeyEvent.VK_UP => directionP2 = "up"
          case KeyEvent.VK_LEFT => directionP2 = "left"
          case KeyEvent.VK_DOWN => directionP2 = "down"
          case KeyEvent.VK_RIGHT => directionP2 = "right"

          case _ =>
        }
      }
    })

    grid = updateGrid(p1, p2, grid)

    updateDisplay(grid, colorP1, colorP2)

    display.syncGameLogic(fps)
    Thread.sleep(speed)

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
  System.exit(0)
}
