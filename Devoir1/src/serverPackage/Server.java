package serverPackage;

import java.io.IOException;

public class Server {
	
	private static int port = 7000;

	public static void main(String[] args) {

		MainServer server = null;

		try {
			server = new MainServer(port);
			System.out.println("Serveur prêt");
			server.mainLoop();
		} catch (IOException e) {
			System.out.println("Problème de création serveur : " + e.getMessage());
			System.exit(1);
		}
	}
}