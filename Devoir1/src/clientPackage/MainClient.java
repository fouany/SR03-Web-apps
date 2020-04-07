package clientPackage;

import java.io.*;
import java.net.*;
import Model.MessageReceptor;

class MainClient {

	Socket sock = null;

	public MainClient(String serverAddr, int port) throws IOException {
		sock = new Socket(serverAddr, port);
	}

	public void mainLoop() {
		MessageReceptor messageReceptor;
		messageReceptor = new MessageReceptor(sock);
		messageReceptor.start();
	}
}