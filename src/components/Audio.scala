package components
import java.net.URL
import javax.sound.sampled.{AudioSystem, Clip}
import scala.util.Random

/**
 * Class representing an Audio file. Only accepts `.wav` files.
 * @param url java.net.URL object pointing to the `.wav` file
 */
class Audio (url : URL) {
  var audioClip: Clip = _

  try {
    val audioStream = AudioSystem.getAudioInputStream(url)

    audioClip = AudioSystem.getClip.asInstanceOf[Clip]
    audioClip.open(audioStream)
  } catch {
    case e: Exception =>
      e.printStackTrace()
  }

  /**
   * Class representing an Audio file. Only accepts `.wav` files.
   * @param path Path pointing to the `.wav` file, relative to the `/src` folder.
   */
  def this(path: String) = {
    this(classOf[Audio].getResource(path))
  }

  /**
   * Plays the audio file
   */
  def play(): Unit = {
    // Open stream and play
    try {
      if (!audioClip.isOpen) audioClip.open()
      audioClip.stop()
      audioClip.setMicrosecondPosition(0)
      audioClip.start()
    } catch {
      case e: Exception =>
        e.printStackTrace()
    }
  }

  /**
   * Stops immediately playing the audio file.
   */
  def stop(): Unit = {
    try {
      audioClip.stop()
    } catch {
      case e: Exception =>
        e.printStackTrace()
    }
  }
}

/**
 * Audio object containing functions to create a Audio class.
 */
object Audio {
  // List of musics to include
  private val musics = List(
    "Metallic Mario - Super Mario 64.wav",
    "Dire, Dire Docks - Super Mario 64.wav"
  )
  // Base folder where the musics are stored
  private val musicsFolderPath: String = "/res/audio/music/"

  /**
   * Gets a random `.wav` music file from the `/res/audio/music` folder
   * @return If a music has been found, returns Some containing an Audio object
   */
  def getRandomMusic(): Option[Audio] = {
    var music: String = ""
    music = musics(Random.nextInt(musics.length))

    try {
      var musicUrl = classOf[Audio].getResource(musicsFolderPath + music)
      if (musicUrl != null) {
        return Some(new Audio(musicUrl))
      } else {
        return None
      }
    } catch {
      case e: Exception => return None
    }
  }

}
