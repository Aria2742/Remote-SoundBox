import java.io.*;
import java.net.*;

import javax.swing.JOptionPane;

/*
 * This is the main class that deals with the connection on the remote side
 * Anything input into the controller gets sent here to be processed
 * 
 * Plans: implement a command to allow control to decide if disconnect means
 * 			to terminate this process or reset the connection and wait for
 * 			controller to reconnect
 */
public class TargetSocket
{
	private static boolean reopen; // tells program whether or not to remain open when controller disconnects
	// start the process here
	public static void main(String[] args) throws IOException
	{
		JOptionPane.showMessageDialog(null, "Remote soundbox opening");
		do {
			new TargetSocket();
			//System.out.println("Connection closed");
		} while(reopen); // reopen the connection if set to
		JOptionPane.showMessageDialog(null, "Remote soundbox closing");
	}

	private ServerSocket radar; // waits for connection
	private Socket socket; // socket returned by ServerSocket.accept()
	private final int portNum = 65532; // port number. this MUST be the same for the target and controller
	private DataInputStream dIn; // stream to read input to the socket
	private DataOutputStream dOut; // stream to snd data back to controller

	public TargetSocket() {
		run(); }

	public void run()
	{
		try {
			radar = new ServerSocket(portNum); // create a ServerSocket at portNum
			//System.out.println("Waiting for connection");
			socket = radar.accept(); // wait for a connection
			//System.out.println("Connection found");
			dIn = new DataInputStream(socket.getInputStream()); // initialize input stream
			dOut = new DataOutputStream(socket.getOutputStream()); // initialize output stream
			dOut.writeUTF("Connection established"); // tell the controller it is connected
			dOut.flush(); // send the message
			SoundList.createList(); // read the file of audio file names before any commands can be sent
			commandLoop(); // enter the main loop
			socket.close(); // close the Socket
			radar.close(); // close the ServerSocket
			//System.out.println("Socket closed");
		} catch (Exception err) {
			//err.printStackTrace(); // comment this out to hide it on the target's computer
			try {
				socket.close(); // now try to close the socket in case it was open
			} catch (Exception err2) {
				//err2.printStackTrace(); // comment this out to hide it on the target's computer
			}
		}
	}

	public void commandLoop() throws Exception
	{
		boolean done = false;
		while(!done) {
			int val = dIn.readInt(); // get the next input integer
			//System.out.println(val); // used for debug purposes
			if(val < -2) { // close both connections
				done = true; // exit if the input is < -1
				reopen = false;
				dOut.writeUTF("bye"); // send the return message back to control
				dOut.flush(); // send off the message
			} else if(val == -2) {
				done = true; // exit if the input is < -1
				reopen = true; // keep this connection open
				dOut.writeUTF("bye"); // send the return message back to control
				dOut.flush(); // send off the message
			} else if(val == -1) {
				dOut.writeUTF(AudioManager.getAudioList()); // send the list of audio files
				dOut.flush(); // send off the message
			} else {
				String result = AudioManager.playSound(val); // store return message from AudioManager
				dOut.writeUTF(result); // send the return message back to control
				dOut.flush(); // send off the message
			}
		}
	}
	
	// used to send an error message back to control
	// currently not used except in AudioPlayer.run()
	public void returnError(Exception err) {
		try {
			dOut.writeUTF(err.getMessage());
			dOut.flush();
		} catch (IOException e) { } // well, you fucked. program errored beyond your typical error
	}
}
