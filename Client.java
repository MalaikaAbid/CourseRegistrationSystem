package CourseRegistrationSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	
	private Socket aSocket;
	private PrintWriter socketOut;
	private BufferedReader socketIn;
	
	public Client (String serverName, int portNumber) {
		
		try {
			aSocket = new Socket (serverName, portNumber);
			//Socket input stream
			socketIn = new BufferedReader (new InputStreamReader (aSocket.getInputStream()));
			socketOut = new PrintWriter((aSocket.getOutputStream()), true);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main (String [] args) throws IOException{
		Client myClient = new Client ("localhost", 9898);
		FrontEnd myFrontEnd = new FrontEnd(myClient);
	}
	
	public PrintWriter getSocketOut()
	{
		return socketOut;
	}

	public BufferedReader getSocketIn()
	{
		return socketIn;
	}

}
