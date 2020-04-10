package clientPackage;

import java.io.*;
import java.net.*;
import Model.ThreadClient;
import Model.Utilisateur;

class MainClient {

	private Socket sock = null;
	private MessageReceptor msgReceptor;
	private MessageSender msgSender;
	protected boolean pseudoAccepte;
	protected Utilisateur utilisateur;
	
	public MainClient(String serverAddr, int port) throws IOException {
		sock = new Socket(serverAddr, port);
	}

	public void mainLoop() {
		pseudoAccepte = true;
		msgReceptor = new MessageReceptor(sock, this);
		msgReceptor.start();
		msgSender = new MessageSender(sock, this);
		msgSender.start();
	}
}