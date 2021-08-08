package CourseRegistrationSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	
	private Socket aSocket;
	private ServerSocket serverSocket;
	private PrintWriter socketOut;
	private BufferedReader socketIn;
	
	private ExecutorService pool;
	
	public Server(int port) {
		try {
			serverSocket = new ServerSocket(port);
			pool = Executors.newFixedThreadPool(2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void runServer () {
		try {
			while (true) {
			
				aSocket = serverSocket.accept();
				System.out.println("Connection accepted by server!");
			    socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
				socketOut = new PrintWriter((aSocket.getOutputStream()), true);
				BackEnd bE = new BackEnd(socketIn, socketOut);

				pool.execute(bE);
			}
		} catch (IOException e) {
			e.getStackTrace();
		}
		closeConnection();
		
	}
	private void closeConnection() {
		try {
			socketIn.close();
			socketOut.close();
		} catch (IOException e) {
			e.getStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		Server myServer = new Server(9898);
		myServer.runServer();
	}

}
