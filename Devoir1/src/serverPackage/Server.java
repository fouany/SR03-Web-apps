package serverPackage;

import java.io.IOException;

public class Server {

	public static void main(String[] args) {

		MainServer server = null;

		if (args.length != 1) {
			System.out.println("Usage: Server port");
			System.exit(1);
		}

		try {
			int port = Integer.parseInt(args[0]);
			server = new MainServer(port);

			System.out.println("Server ready");
			server.mainLoop();
		} catch (IOException e) {
			System.out.println("Problème création serveur : " + e.getMessage());
			System.exit(1);
		}
	}
}