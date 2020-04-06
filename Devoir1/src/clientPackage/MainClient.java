package clientPackage;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import Model.Utilisateur;

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

		String pseudo = "";
		boolean pseudoAccepte = true;

		try {

			do {
				if (pseudoAccepte)
					System.out.println("Entrez votre pseudo : ");
				else
					System.out.println("Veuillez saisir un autre pseudo : ");

				pseudo = consoleIn.readLine();

				Utilisateur potentiel = new Utilisateur(pseudo, null);
				potentiel.setPseudo(pseudo);
				oos.writeObject(potentiel);
				oos.flush();
				
				pseudoAccepte = ois.readBoolean();
				
				if (pseudo.equals("exit")) {
					System.out.println("Vous avez quitté le serveur");
					break;
				}

			} while (!pseudoAccepte);
			
			
			if (!pseudo.equals("exit")) {
				String message = "Exemple";
				System.out.println("Vous pouvez désormais envoyer vos messages au serveur !");

				while ((message != null) && (!message.isEmpty()) && (!message.equals("exit"))) {
					
					System.out.println("Entrez un message : ");
					message = consoleIn.readLine();
					oos.writeObject(message);
					oos.flush();
					
					if (message.equals("exit")) {
						System.out.println("Vous avez quitté le serveur");
						break;
					}
					
				}
			}
			
			
			oos.close();
			ois.close();
		} catch (IOException e) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);
		}
	}
}
