package clientPackage;

import java.io.IOException;

public class Client {

	public static void main(String[] args) {

		MainClient client;

		if (args.length != 2) {
			System.out.println("Usage: Client ip_server port");
			System.exit(1);
		}

		try {
			String serverAddr = args[0];
			int port = Integer.parseInt(args[1]);
			client = new MainClient(serverAddr, port);
			client.mainLoop();
			
		} catch (IOException e) {
			System.out.println("Problème de connexion au serveur : " + e.getMessage());
			System.exit(1);
		}
	}
}
