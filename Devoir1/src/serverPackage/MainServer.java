package serverPackage;

import java.io.*;
import java.net.*;

public class MainServer {

	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;
	ServerSocket conn = null;
	Socket sock = null;
	int port = -1;

	public MainServer(int port) throws IOException {
		this.port = port;
		conn = new ServerSocket(port);
	}

	public void mainLoop() {
		ThreadServer t = null;
		while (true) {
			try {
				sock = conn.accept();
				t = new ThreadServer(sock);
				t.start();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
