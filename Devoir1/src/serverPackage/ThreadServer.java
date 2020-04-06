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
	
	MainServer mainserver;
	
	ArrayList<String> messagesThread = new ArrayList<String>();


	public ThreadServer(Socket sock, MainServer mainserver) throws IOException {
		this.sock = sock;
		this.mainserver = mainserver;
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
				
				if (message.equals("exit")) {
					System.out.print("Un client s'est déconnecté\n");
					break;
				}
				
				System.out.println("Message du client : " + message);
				mainserver.messages.add(message);
				System.out.println("messages globaux " + mainserver.messages);

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
