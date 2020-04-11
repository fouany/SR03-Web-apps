package serverPackage;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.Utilisateur;


public class MainServer {

	private ServerSocket conn = null;
	private Socket sock = null;
	private int port = -1;
	
	protected ArrayList<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
	protected ArrayList<ThreadServer> threadsServer = new ArrayList<ThreadServer>();

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
				Logger.getLogger(MainServer.class.getName()).log(Level.SEVERE, null, e);
			}
		}
	}
}