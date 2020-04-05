package serverPackage;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class MainServer {

	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;
	ServerSocket conn = null;
	Socket sock = null;
	int port = -1;
	
	ArrayList<String> messages = new ArrayList<String>();

	public MainServer(int port) throws IOException {
		this.port = port;
		conn = new ServerSocket(port);
		
	}

	public void mainLoop() {
		ThreadServer t = null;
		while (true) {
			try {
				sock = conn.accept();
				t = new ThreadServer(sock, this);
				t.start();
				
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
