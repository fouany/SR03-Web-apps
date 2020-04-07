package Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import clientPackage.Client;

public class MessageReceptor extends Thread {
	
	private Socket sock;
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;
	BufferedReader consoleIn = null;
	
	public MessageReceptor(Socket sock) {
		this.sock = sock;
	}

	@Override
	public void run() {
		consoleIn = new BufferedReader(new InputStreamReader(System.in));

		try {
			oos = new ObjectOutputStream(sock.getOutputStream());
			ois = new ObjectInputStream(sock.getInputStream());
			oos.flush();

			requestLoopClient();

			oos.close();
			ois.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void requestLoopClient() {
		
		String pseudo = "";
		boolean pseudoAccepte = true;

		try {

			do {
				if (pseudoAccepte)
					System.out.println("Entrez votre pseudo : ");
				else
					System.out.println("Veuillez saisir un autre pseudo : ");

				pseudo = consoleIn.readLine();

				Utilisateur potentiel = new Utilisateur(pseudo, null, null);
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
				System.out.println("Vous pouvez désormais envoyer vos messages au serveur !\n");

				while ((message != null) && (!message.isEmpty()) && (!message.equals("exit"))) {
					
				
					System.out.println("Entrez un message : ");
					message = consoleIn.readLine();
					oos.writeObject(message);
					oos.flush();
					
					String messageInterClient = (String) ois.readObject();
					System.out.println("\n-- Nouveau message --\n " + messageInterClient); 

					if (message.equals("exit")) {
						System.out.println("Vous avez quitté le serveur");
						break;
					}
				}
			}
			oos.close();
			ois.close();
		} catch (IOException | ClassNotFoundException e) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);
		}
	}

}
