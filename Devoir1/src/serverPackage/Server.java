package serverPackage;

import java.io.IOException;

/**
 * Server contient la programme principal c�t� serveur Server va instancier un
 * MainServer sur le port 7000
 */
public class Server {

	private static int port = 7000;

	public static void main(String[] args) {

		MainServer server = null;

		try {
			server = new MainServer(port);
			System.out.println("Serveur pr�t");
			server.mainLoop(); // On d�marre la m�thode principale de MainServer
		} catch (IOException e) {
			System.out.println("Probl�me de cr�ation serveur : " + e.getMessage());
			System.exit(1);
		}
	}
}