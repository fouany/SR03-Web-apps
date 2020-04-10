package clientPackage;

import java.io.*;
import java.net.*;
import Model.ThreadClient;

class MainClient {

	private Socket sock = null;
	private MessageReceptor msgReceptor;
	private MessageSender msgSender;

	public MainClient(String serverAddr, int port) throws IOException {
		sock = new Socket(serverAddr, port);
	}

	public void mainLoop() {
		msgReceptor = new MessageReceptor(sock);
		msgReceptor.start();
		msgSender = new MessageSender(sock);
		msgSender.start();
	}
}