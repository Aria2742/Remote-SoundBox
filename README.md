# Remote-SoundBox
Use this program to remotely play sounds on another device. Originally written to prank a friend (he did find it quite entertaining once he figured it out and I explained it), but it might have other uses.

## NOTE
This program requires the [JLayer library](http://www.javazoom.net/javalayer/javalayer.html) to play audio since it supports mp3 files. If you want to edit the code yourself and recompile, you will need the library for it to work. You do not need it if you are only using the compiled code. I used version 1.0.1 for this project.

## How To Use
1. Download the remote.jar, audioList.txt, and controller.java (or controller.class if you don't want to compile the code)
2. (optional) Create a folder for the remote soundbox for organization
  * This will make it easier to set up the program on your computer then move it to other computers
3. Find all audio files you want to be able to play and type the location of each file into audioList.txt
  * Examples are in the default audioList.txt
  * If the audio files are in the same directory as remote.jar, only the file name is needed
  * If the audio files are NOT in the same directory as remote.jar, the full file path must be used
4. Place remote.jar, audioList.txt, and all audio files on the computer to wish to remotely play sound from
  * All audio files must have the same file path if the path is specified
  * AudioList.txt MUST be in the same directory as remote.jar
5. Run remote.jar
  * You should see a pop-up window saying that the program is executing. You can close that window
6. Get the IP address of the computer runnning remote.jar
7. Go back to the computer you want to control the program from and open command prompt
8. Type in "java controller IP_from_step_6"
  * If all goes smoothly, you should get a message saying "Connection established"
9. Type in your commands

## Command List
### -1
Returns the list of all audio files and the number command to play each file</br>
Example output: "0:boop.mp3"
### 0, 1, 2, 3, ...
Plays the audio file (use -1 to see what numbers play what audio)</br>
Returns either "command successful" or an error message depending on whether or not the command works
### -2
Close the controller's connection, but keeps the remote open to reconnect
### -3, -4, -5, -6, ...
Close both the controller's connection and the remote's connection</br>
Note: to reconnect to the remote after this, you MUST restart the remote.jar
