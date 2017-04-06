import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ControlSocket
{
	static String targetIP; // IP address of machine running TargetSocket.java
	static final int portNum = 65532; // port number. this MUST be the same for the target and controller

	public static void main(String args[])
	{
		try {
			targetIP = args[0]; // set the IP address to the first argument for the process
		} catch (ArrayIndexOutOfBoundsException err) {
			System.out.println("Using local host");
			targetIP = "0.0.0.0"; // if no IP was given, default to localHost
		}

		try {
			Socket socket = new Socket(targetIP, portNum); // create socket and attempt to connect to TargetSocket
			DataInputStream dIn = new DataInputStream(socket.getInputStream()); // initialize input stream
			DataOutputStream dOut = new DataOutputStream(socket.getOutputStream()); // initialize output stream
			Scanner in = new Scanner(System.in); // create new Scanner for user input
			int input; // declare integer for user commands
			String result; // declare string for return message from using commands

			// wait for TargetSocket to respond with confirmation message
			result = dIn.readUTF(); // wait for TargetSocket to respond
			System.out.println(result);
			
			do {
				input = in.nextInt(); // get next command
				dOut.writeInt(input); // send command to TargetSocket
				dOut.flush(); // push message through
				result = dIn.readUTF(); // wait for TargetSocket to respond
				System.out.println(result);
			}while(!result.equalsIgnoreCase("bye")); // exit when TargetSocket returns "bye"

			in.close();
			socket.close();
		} catch(Exception err) {err.printStackTrace(); }
	}
}
