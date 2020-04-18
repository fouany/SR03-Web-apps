package serverPackage;

import java.io.IOException;

/**
 * Server contient la programme principal côté serveur Server va instancier un
 * MainServer sur le port 7000
 */
public class Server {

	private static int port = 7000;

	public static void main(String[] args) {

		MainServer server = null;

		try {
			server = new MainServer(port);
			System.out.println("Serveur prêt");
			server.mainLoop(); // On démarre la méthode principale de MainServer
		} catch (IOException e) {
			System.out.println("Problème de création serveur : " + e.getMessage());
			System.exit(1);
		}
	}
}