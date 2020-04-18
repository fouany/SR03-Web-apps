package clientPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.Utilisateur;

/**
 * 
 * Thread pour le client qui envoie les messages au serveur
 *
 */
public class MessageSender extends Thread {

	private Socket sock;
	private ObjectOutputStream oos = null;
	private BufferedReader consoleIn = null;
	
	MainClient mainclient;

	public MessageSender(Socket sock, MainClient mainclient) {
		this.sock = sock;
		this.mainclient = mainclient;
	}

	@Override
	public void run() {

		try {
			oos = new ObjectOutputStream(sock.getOutputStream());
			oos.flush();

			requestLoop();
			
			oos.flush();
			oos.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void requestLoop() {

		consoleIn = new BufferedReader(new InputStreamReader(System.in));
		connexion(); // On atomise en diffrentes fonctions pour plus de clarte
		saisirMessages();
	}
	
	/**
	 * Methode envoyant le pseudo au serveur
	 */
	public void connexion(){
		String pseudo = "Exemple";
		System.out.println("Bienvenue dans votre application de chat !");
		
		try {
			/**
			 * Nous n'avons pas reussi a faire la verification du pseudo dans une boucle do while
			 * a cause d'un probleme de synchronisation des objets
			 */
			System.out.println("Entrez votre pseudo: ");
			pseudo = consoleIn.readLine();
			mainclient.utilisateur = new Utilisateur(pseudo); // on cree une nouvelle instance d'utilisateur puis on l'envoie
			oos.writeObject(mainclient.utilisateur);
			oos.flush();
			
		} catch (IOException e) {
			Logger.getLogger(MessageSender.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	/**
	 * Permet au client d'envoyer des messages tant qu'il n'crit pas exit
	 */
	public void saisirMessages(){
		String message = "Exemple";
		boolean condition = true;
		System.out.println("Vous pouvez desormais envoyer des messages aux autres utilisateurs en ligne !");

		try {
			while (condition) {
				message = consoleIn.readLine();
				oos.writeObject(message);
				oos.flush();
				if (message.equals("exit")) {
					System.out.print("Deconnexion");
					condition = false;
				}
			}
		} catch (IOException e) {
			Logger.getLogger(MessageSender.class.getName()).log(Level.SEVERE, null, e);
		}
	}
}