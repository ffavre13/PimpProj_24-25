import hevs.graphics.FunGraphics

import java.awt.Color
import java.awt.event.{KeyAdapter, KeyEvent, KeyListener}

object Game extends App {
  // Game settings
  val dimGrid: Int = 20           // Size of the grid
  val sizeMult: Int = 20          // Size multiplier for display
  val fps: Int = 2                // Number of Frames per second generated
  val showGrid: Boolean = true    // Tells if the game should render the game grid

  val p1: Player = new Player(0, 0) // Player 1
  val p2: Player = new Player(dimGrid - 1, dimGrid - 1) // Player 2
  val colorP1: Color = Color.RED // Color player 1
  val colorP2: Color = Color.BLUE // Color player 2
  var directionP1: String = "right" // Direction for player 1
  var directionP2: String = "left" // Direction for player 2
  var gaming: Boolean = true    // Tells if the game is ongoing

  val grid: Array[Array[Int]] = Array.ofDim(dimGrid, dimGrid) // Grid for the game
  val display: FunGraphics = new FunGraphics(dimGrid * sizeMult, dimGrid * sizeMult) // Display Windows


  // Update the position of the players in the grid
  def updateGrid(player1: Player, player2: Player, a: Array[Array[Int]]): Array[Array[Int]] = {
    val tmp: Array[Array[Int]] = a.clone()
    // Check lose conditions
    if (isOutOfBounds(player1) && isOutOfBounds(player2)) {
      println("You both are bad at the game")
      gaming = false
    }
    else if (isOutOfBounds(player1)) {
      println("Player 1 you have hit a wall. PLAYER 2 WINS")
      gaming = false
    }
    else if (isOutOfBounds(player2)) {
      println("Player 2 you have hit a wall. PLAYER 1 WINS")
      gaming = false
    }
    else if (tmp(player1.getPosY())(player1.getPosX()) == 2 || tmp(player1.getPosY())(player1.getPosX()) == 1 || tmp(player1.getPosY())(player1.getPosX()) == 3) {
      println("Game over player 2 win")
      gaming = false
    }
    else if (tmp(player2.getPosY())(player2.getPosX()) == 1 || tmp(player2.getPosY())(player2.getPosX()) == 2 || tmp(player2.getPosY())(player2.getPosX()) == 3) {
      println("Game over player 1 win")
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
   * @param player Player to check
   * @return True if player is out of bounds
   */
  def isOutOfBounds(player: Player): Boolean = {
    return (player.getPosX() < 0 || player.getPosX() > dimGrid - 1 ||
      player.getPosY() < 0 || player.getPosY() > dimGrid - 1)
  }

  // Update Display with a grid
  def updateDisplay(a: Array[Array[Int]], cP1: Color, cP2: Color): Unit = {
    display.frontBuffer.synchronized {
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

  // Draw grid on display windows
  if (showGrid) {
    for (i <- 1 until dimGrid) {
      display.drawLine(0, i * sizeMult, dimGrid * sizeMult, i * sizeMult)
      display.drawLine(i * sizeMult, 0, i * sizeMult, dimGrid * sizeMult)
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
      }
    })

    updateGrid(p1, p2, grid)

    updateDisplay(grid, colorP1, colorP2)

    display.syncGameLogic(fps)

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
