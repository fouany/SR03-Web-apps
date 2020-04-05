package serverPackage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import Model.MessageReceptor;
import Model.Utilisateur;

public class Server {

	public static void main(String []args) {

	    MainServer server = null;
	    
	    if (args.length != 1) {
	      System.out.println("Usage: Server port");
	      System.exit(1);
	    }
	    
	    try {
	      int port = Integer.parseInt(args[0]);
	      server = new MainServer(port);
	      server.mainLoop();
	    }
	    catch(IOException e) {
	      System.out.println("Problème création serveur : "+ e.getMessage());
	      System.exit(1);
	    }
	  }
	
	
}
	
	/*
	private static ArrayList<Utilisateur> clients = new ArrayList<Utilisateur>();

	public static void main(String[] args) {

		Utilisateur potentiel;
		boolean pseudoAccepte;
		ServerSocket server = null;
		Socket client = null;
		
		try {
			server = new ServerSocket(7000);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		while (true) {
			try {
				System.out.println("Serveur à l'écoute ...");
				client = server.accept();
				System.out.println("Client en attente ...");

				do {
					do {
						ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
						potentiel = (Utilisateur) ois.readObject();
						ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());

						if (!checkPseudo(potentiel)) {
							pseudoAccepte = false;
							oos.writeBoolean(false);
						} else {
							pseudoAccepte = true;
							oos.writeBoolean(true);
						}
						oos.flush();

					} while (!pseudoAccepte);

					potentiel.setSock(client);
					potentiel.setMessageReceptor(new MessageReceptor(client));
					clients.add(potentiel);

					System.out.println("\n" + potentiel.getPseudo() + " a rejoint la conversation \n");
					System.out.println(potentiel);
					afficherUtilisateurs();

				} while (true);

			} catch (IOException ex) {
				System.err.println("Client déconnecté ...");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean checkPseudo(Utilisateur potentiel) {
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i).equals(potentiel))
				return false;
		}
		return true;
	}

	public static void afficherUtilisateurs() {
		System.out.println("\nUtilisateurs en ligne : ");
		for (int i = 0; i < clients.size(); i++) {
			System.out.println(clients.get(i));
		}
	}*/
