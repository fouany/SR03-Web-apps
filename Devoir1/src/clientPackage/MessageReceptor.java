package clientPackage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Thread pour le client qui reçoit les messages de ThreadServer 
 */
public class MessageReceptor extends Thread {

	private Socket sock;
	private ObjectInputStream ois = null;

	protected MainClient mainclient;

	public MessageReceptor(Socket sock, MainClient mainclient) {
		this.sock = sock;
		this.mainclient = mainclient;
	}

	@Override
	public void run() {

		try {
			ois = new ObjectInputStream(sock.getInputStream());

			requestLoop();

			ois.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void requestLoop() {

		String messageRecu = "Exemple";
		boolean condition = true;
		try {
			// Tant que l'utilisateur n'a pas envoyé exit, on lit les messages venant des autres utilisateurs
			while (condition) {
				messageRecu = (String) ois.readObject();
				if (!messageRecu.startsWith(mainclient.utilisateur.getPseudo()) || !messageRecu.contains(mainclient.utilisateur.getPseudo()))
					System.out.println(messageRecu); // On affiche le message uniquement si ce n'est pas le même utilisateur qui l'a envoyé
				if (messageRecu.startsWith("exit")) condition = false;
				// Si exit a été envoyé, il faut déconnecter le client et donc arrêter ses threads
				// Par manque de temps, nous ne l'avons pas fait
				// Il suffit de vérifier si le "exit" reçu vient du même utilisateur que cette instance du thread
			}
		} catch (ClassNotFoundException e) {
			Logger.getLogger(MessageReceptor.class.getName()).log(Level.SEVERE, null, e);
		} catch (IOException e) {
			Logger.getLogger(MessageReceptor.class.getName()).log(Level.SEVERE, null, e);
		}
	}
}