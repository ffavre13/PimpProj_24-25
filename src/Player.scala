class Player(posX: Int, posY: Int) {
  private var x: Int = posX
  private var y: Int = posY

  /**
   * Returns the position X of the player
   * @return Int containing X position
   */
  def getPosX(): Int = x

  /**
   * Returns the position Y of the player
   * @return Int containing Y position
   */
  def getPosY(): Int = y

  /**
   * Sets the position X of the player while checking for incoherent values
   * @param newPosX New position X for the player
   */
  def setPosX(newPosX: Int): Unit = {
    if (newPosX == x - 1 || newPosX == x + 1) {
      x = newPosX
    }
  }

  /**
   * Sets the position Y of the player while checking for incoherent values
   * @param newPosY New position Y for the player
   */
  def setPosY(newPosY: Int): Unit = {
    if (newPosY == y - 1 || newPosY == y + 1) {
      y = newPosY
    }
  }
}
