import java.io.FileInputStream;
import javazoom.jl.player.Player;

/*
 * This class loads and plays the audio file found at fileLocation
 * By making this class a thread, the main program won't get blocked when audio plays
 * 		This also means you can make multiple audio files overlap
 */
public class AudioPlayer extends Thread
{
	private FileInputStream buffer;
	private Player player; // imported from JLayer library to play mp3 files
	private String fileLocation;
	
	/*
	 * Constructor Method
	 * Reads file at fileLocation and creates the Player class to play that audio file
	 * Throws an exception back to AudioManager if something goes wrong
	 * 		(and that error message is sent back to the controller)
	 */
	public AudioPlayer(String fl) {
		fileLocation = fl; }

	@Override
	public void run() {
		try {
			buffer = new FileInputStream(fileLocation); // initialize file input
			player = new Player(buffer); // create the Player to play audio
			player.play(); // play the sound now since Thread has been made
		} catch (Exception err) {
			AudioManager.returnError(err); // only way I could think of for returning errors from a Thread
		} // do nothing
	}
}
