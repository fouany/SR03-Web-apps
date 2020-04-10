package clientPackage;

import java.io.*;
import java.net.*;
import Model.ThreadClient;

class MainClient {

	Socket sock = null;

	public MainClient(String serverAddr, int port) throws IOException {
		sock = new Socket(serverAddr, port);
	}

	public void mainLoop() {
		ThreadClient threadClient;
		threadClient = new ThreadClient(sock);
		threadClient.start();
	}
}