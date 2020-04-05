package serverPackage;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadServer extends Thread {

	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;
	ServerSocket conn = null;
	Socket sock = null;
	int port = -1;
	
	ArrayList<String> messagesThread = new ArrayList<String>();


	public ThreadServer(Socket sock) throws IOException {
		this.sock = sock;
	}

	public void run() {

		try {
			ois = new ObjectInputStream(sock.getInputStream());
			oos = new ObjectOutputStream(sock.getOutputStream());
			oos.flush();

			requestLoop();

			oos.close();
			ois.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void requestLoop() {

		String message = "";
		
		try {
			while (true) {
				message = (String) ois.readObject();
				System.out.println("Message du client : " + message);
				messagesThread.add(message);
				System.out.println("messages thread " + messagesThread);
				oos.writeObject("message to mainserver");
				
				oos.flush();
			}
		} catch (IOException e) {
			Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, e);
		} catch (ClassNotFoundException e) {
			System.out.println("error in request loop :" + e.getMessage());
		}
	}

	public ArrayList<String> getMessagesThread() {
		return messagesThread;
	}

	
	
	
}
