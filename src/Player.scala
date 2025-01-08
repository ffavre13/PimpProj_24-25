class Player(posX: Int, posY: Int) {
  private var x: Int = posX
  private var y: Int = posY

  // Return the position X of the player
  def getPosX(): Int = x

  // Return the position Y of the player
  def getPosY(): Int = y

  // Set the position X of the player
  def setPosX(newPosX: Int): Unit = {
    if (newPosX == x - 1 || newPosX == x + 1) {
      x = newPosX
    }
  }

  // Set the position Y of the player
  def setPosY(newPosY: Int): Unit = {
    if (newPosY == y - 1 || newPosY == y + 1) {
      y = newPosY
    }
  }
}
