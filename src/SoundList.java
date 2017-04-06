import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Manages the file names/paths in audioList.txt and loads them into an array
 * No set methods in order to make sure the list doesn't change while the program is running
 */
public class SoundList
{
	private static ArrayList<String> sounds = null;
	private static int length;
	
	// Load all the file names/paths from audioList.txt into an ArrayList
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
		if(sounds == null) { // avoid null pointer exception
			createList(); }
		return sounds.get(idx);
	}
	
	public static int getLength() {
		if(sounds == null) { // avoid null pointer exception
			createList(); }
		return length;
	}
}
