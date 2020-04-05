package clientPackage;

import java.io.*;
import java.net.*;

class MainClient {

	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;
	BufferedReader consoleIn = null;
	Socket sock = null;

	public MainClient(String serverAddr, int port) throws IOException {
		consoleIn = new BufferedReader(new InputStreamReader(System.in));
		sock = new Socket(serverAddr, port);

		oos = new ObjectOutputStream(sock.getOutputStream());
		oos.flush();
		ois = new ObjectInputStream(sock.getInputStream());
	}

	public void mainLoop() {
		
		String message = "";
		String pseudo = "";

		try {
			//while ((message != null) && (!message.isEmpty()) && (message != "exit")) {
				System.out.println("Entrez votre pseudo : ");
				message = consoleIn.readLine();
				oos.writeObject(message);
				oos.flush();
			//}
			oos.close();
			ois.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
