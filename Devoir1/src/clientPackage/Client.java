package clientPackage;

import java.io.IOException;

/**
 * Client contient le programme principal c�t� client Client contient les
 * attributs de connexion : localhost pour l'adresse et 7000 pour le port
 */
public class Client {

	private static String serverAddr = "localhost";
	private static int port = 7000;

	public static void main(String[] args) {

		MainClient client;

		try {
			client = new MainClient(serverAddr, port);
			client.mainLoop(); // On d�marre la m�thode principale de MainClient

		} catch (IOException e) {
			System.out.println("Probl�me de connexion au serveur : " + e.getMessage());
			System.exit(1);
		}
	}
}