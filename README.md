# Awesome TRON Game
A simple 2 players Tron game made in Scala.  
The player needs to avoid touching obstacles while blocking the other.

## Screenshots
<img src="res%2Fimg%2Fingame.png" width="49%">
<img src="res%2Fimg%2Fingame_2.png" width="49%">

## Structure
The project is structured as it follows :
```
📁 <root>
├── 📁 res             # Project ressources (libs)
│   └── 📁 lib         # Contains libraries needed in order for the project to run
├── 📁 src             # Scala code of the project 
│   ├── 📄 Game.scala  # Main scala file. Launch the project from here
│   ├── 📁 components  # Graphical components
│   └── 📁 res         # Ressources called in code
│       ├── 📁 img     # Project images
└──     └── 📁 audio   # SFX and music
```

## Controls
**For player 1 :** `WASD` to move  
**For player 2 :** `IJKL` or `↑ ← → ↓` to move

If you touch a wall, you lose.  
If you touch the other's head, a yellow square will appear. That means that the two players are close.

## Launch the project
**Using IntelliJ Community :**
1. Clone the repository
2. Set up your JDK and Scala SDK
3. If the FunGraphics lib isn't imported correctly, import it by right-clicking   
   on the `res/lib/fungraphics-1.5.15.jar` file and pressing `Add as library` button.
4. Launch the `src/Game.scala` file.
5. Enjoy ! :smile:

## TODO
- [x] Add a head for player
- [x] Add a replay option
- [x] Add comment

### Optionnal features
- [ ] Add graphics
- [x] Add sounds
- [ ] Add menu with settings
- [ ] Add multiplayer
- [ ] Add bonuses (Speed boost for example)
- [ ] Add obstacles in map
- [ ] Add Leaderboard
- [x] 🥚?