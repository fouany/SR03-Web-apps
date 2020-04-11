package serverPackage;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.Utilisateur;

/**
 * ThreadServer est le processus qui s'�xecute en continu Il re�oit les donn�es
 * des clients avec ois Il envoie les donn�es aux clients avec oos
 */
public class ThreadServer extends Thread {

	private ObjectOutputStream oos = null;
	private ObjectInputStream ois = null;
	private ServerSocket conn = null;
	private Socket sock = null;
	private int port = -1;

	protected MainServer mainserver; // permettra d'acc�der � la liste des Threads

	public ThreadServer(Socket sock, MainServer mainserver) throws IOException {
		this.sock = sock;
		this.mainserver = mainserver;
	}

	@Override
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
		try {
			String message = "Exemple";
			Utilisateur potentiel;

			potentiel = (Utilisateur) ois.readObject();

			if (isPseudoOk(potentiel)) { // on v�rifie le pseudo

				envoyerATous("------" + potentiel.getPseudo() + " s'est connect� ------");

				// Boucle "infinie" pour recevoir et diffuser des messages aux clients
				while ((message != null) && (!message.isEmpty()) && (!message.equals("exit"))) {

					message = (String) ois.readObject();

					// On g�re la d�connexion
					if (message.equals("exit")) {
						envoyerATous("------" + potentiel.getPseudo() + " s'est d�connect� ------");
						break; // On sort de la boucle si l'utilisateur se d�connecte pour arr�ter le thread
					}
					envoyerATous(potentiel, message);
					System.out.println("\nMessage client : " + message);
				}
			}

		} catch (IOException e) {
			Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, e);
		} catch (ClassNotFoundException e) {
			System.out.println("error in request loop :" + e.getMessage());
		}
	}

	/**
	 * V�rifie si le pseudo n'est pas d�j� utilis�
	 * 
	 * @param potentiel
	 * @return true si le pseudo n'est pas utilis�, faux sinon
	 */
	public boolean isPseudoOk(Utilisateur potentiel) {
		for (int i = 0; i < mainserver.utilisateurs.size(); i++) {
			if (mainserver.utilisateurs.get(i).equals(potentiel))
				return false;
		}
		return true;
	}

	/**
	 * On diffuse le param�tre message � tous les clients
	 * @param message
	 */
	public void envoyerATous(String message) {
		for (int i = 0; i < mainserver.threadsServer.size(); i++) { // on acc�de � la liste globale des utilisateurs
			try {
				mainserver.threadsServer.get(i).oos.writeObject(message);
				oos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * M�thode surcharg�e pour ajouter des informations sur l'utilisateur 
	 * On diffuse le param�tre message � tous les clients
	 * @param potentiel est l'utilisateur, on le passe en param�tre pour acc�der � ses donn�es
	 * @param message
	 */
	public void envoyerATous(Utilisateur potentiel, String message) {
		for (int i = 0; i < mainserver.threadsServer.size(); i++) { // on acc�de � la liste globale des utilisateurs
			try {
				mainserver.threadsServer.get(i).oos.writeObject(potentiel.getPseudo() + " dit: " + message);
				oos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
