package clientPackage;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
		
		String message = "Exemple";
		String pseudo = "";

		try {
			while ((message != null) && (!message.isEmpty()) && (!message.equals("exit"))) {
				System.out.println("Entrez votre pseudo : ");
				message = consoleIn.readLine();
				oos.writeObject(message);
				oos.flush();
				
				if (message.equals("exit")) {
					System.out.println("Déconnexion du serveur réussie");
					break;
				}
								
			}
			oos.close();
			ois.close();
		} catch (IOException e) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);
		}
	}
}
