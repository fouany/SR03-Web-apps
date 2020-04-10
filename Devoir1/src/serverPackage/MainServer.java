package serverPackage;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import Model.Utilisateur;

public class MainServer {

	ServerSocket conn = null;
	Socket sock = null;
	int port = -1;
	
	ArrayList<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
	ArrayList<ThreadServer> threadsServer = new ArrayList<ThreadServer>();

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
				threadsServer.add(t);

				
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

}