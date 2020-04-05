package serverPackage;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import clientPackage.Client;

public class ThreadServer extends Thread {

	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;
	ServerSocket conn = null;
	Socket sock = null;
	int port = -1;

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
				oos.flush();
			}
		} catch (IOException e) {
			Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, e);
		} catch (ClassNotFoundException e) {
			System.out.println("error in request loop :" + e.getMessage());
		}
	}
}
