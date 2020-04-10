package clientPackage;

import java.io.IOException;

public class Client {

	private static String serverAddr = "localhost";
	private static int port = 7000;
	
	public static void main(String[] args) {

		MainClient client;

		try {
			client = new MainClient(serverAddr, port);
			client.mainLoop();
			
		} catch (IOException e) {
			System.out.println("Probl�me de connexion au serveur : " + e.getMessage());
			System.exit(1);
		}
	}
}
