package serverPackage;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.Utilisateur;

/**
 * MainServer stocke la liste des utilisateurs et la liste des ThreadServers
 * Les listes sont en protected pour pouvoir être accessibles depuis le ThreadServer
 */
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
				sock = conn.accept(); // on accepte les connexions sans interruption
				System.out.println("Nouvelle connexion client");
				t = new ThreadServer(sock, this); // on instancie un ThreadServer pour chaque nouvelle connexion acceptée
				t.start(); // on démarre le thread
				threadsServer.add(t); // on ajoute le nouveau thread à la liste globale des ThreadServer

			} catch (IOException e) {
				Logger.getLogger(MainServer.class.getName()).log(Level.SEVERE, null, e);
			}
		}
	}
}