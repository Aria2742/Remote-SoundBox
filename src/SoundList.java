import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Just a class to help keep the code clean
 * Both variables are public, static, and final
 * 		So they canNOT be changed during run-time, and
 * 		You can access them with SoundList.sounds[index] and SoundList.length
 * Put all sound file names into the String array with the file extension in the name
 */
public class SoundList
{
	private static ArrayList<String> sounds;
	private static int length;
	
	public static void createList()
	{
		try {
		sounds = new ArrayList<String>();
		Scanner s = new Scanner(new File("audioList.txt"));
		while(s.hasNextLine()) {
			sounds.add(s.nextLine()); }
		s.close();
		length = sounds.size();
		} catch(FileNotFoundException err) { AudioManager.returnError(err); }
	}
	
	public static String getSoundPath(int idx) {
		return sounds.get(idx); }
	
	public static int getLength() {
		return length; }
}
