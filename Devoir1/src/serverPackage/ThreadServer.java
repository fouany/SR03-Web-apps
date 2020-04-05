package serverPackage;

import java.io.*;
import java.net.*;

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
		
		String messageClient = "";
		
		try {
			//while (true) {
				messageClient = ois.readObject().toString();
				System.out.println("ThreadServer : " + messageClient);
				oos.flush();
			//}
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("error in request loop :" + e.getMessage());
		}
	}
}
