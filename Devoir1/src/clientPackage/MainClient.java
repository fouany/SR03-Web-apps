package clientPackage;

import java.io.*;
import java.net.*;
import Model.Utilisateur;

/**
 * Chaque client aura un thread receveur et envoyeur, ainsi qu'un objet
 * utilisateur pour stocker le pseudo
 */
public class MainClient {

	private Socket sock = null;
	private MessageReceptor msgReceptor;
	private MessageSender msgSender;
	protected Utilisateur utilisateur;
	
	public MainClient(String serverAddr, int port) throws IOException {
		sock = new Socket(serverAddr, port);
	}

	public void mainLoop() {
		msgReceptor = new MessageReceptor(sock, this); // on démarre le thread receveur sur la socket client
		msgReceptor.start();
		msgSender = new MessageSender(sock, this); // on démarre le thread envoyeur sur la socket client
		msgSender.start();
	}
}