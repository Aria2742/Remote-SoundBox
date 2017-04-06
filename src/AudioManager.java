/*
 * This class does the heavy lifting of processing commands and relaying the result back to remote socket
 * Audio File names are stored in SoundList.java for cleanliness of code
 */
public class AudioManager
{
	private static TargetSocket sender; // use for returning error messages
	
	// plays the sound file specified by SoundList.sounds[soundID]
	public static String playSound(int soundID)
	{
		if(soundID >= SoundList.getLength()) { // check index upper bounds. TargetSocket.java already checked lower bounds
			return "Index out of bounds. Largest index is " + (SoundList.getLength() - 1);	
		} else { // index is in bounds
			try {
				new AudioPlayer(SoundList.getSoundPath(soundID)).start(); // try to create an AudioPlayer with the file name
			} catch (Exception err) {
				return err.toString(); // return the error message if creating the AudioPlayer errored
			}
			return "Command successful"; // everything went well in order to get here, so let the controller know
		}
	}
	
	/* 
	 * returns the list of all audio files names in SoundList.java
	 * format:
	 * 		command_number:file_name
	 */
	public static String getAudioList()
	{
		String list = "";
		for(int i=0; i<SoundList.getLength(); i++) {
			list += i + ":" + SoundList.getSoundPath(i);
			if(i < SoundList.getLength()-1) {
				list += "\n"; }
		}
		return list;
	}
	
	// set the socket to return errors to
	public static void setSocket(TargetSocket s) {
		sender = s; }
	
	// error passing, used only once in current program
	public static void returnError(Exception err) {
		sender.returnError(err); }
}
