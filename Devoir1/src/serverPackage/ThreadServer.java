package serverPackage;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import Model.MessageReceptor;
import Model.Utilisateur;

public class ThreadServer extends Thread {

	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;
	ServerSocket conn = null;
	Socket sock = null;
	int port = -1;

	MainServer mainserver;

	public ThreadServer(Socket sock, MainServer mainserver) throws IOException {
		this.sock = sock;
		this.mainserver = mainserver;
	}

	public void run() {

		try {
			ois = new ObjectInputStream(sock.getInputStream());
			oos = new ObjectOutputStream(sock.getOutputStream());
			oos.flush();

			requestLoop();

			oos.close();
			ois.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void requestLoop() {

		String message = "Exemple";
		Utilisateur potentiel;
		boolean pseudoAccepte;

		try {

			do {
				potentiel = (Utilisateur) ois.readObject();

				if (!checkPseudo(potentiel)) {
					pseudoAccepte = false;
					oos.writeBoolean(false);
				} else {
					pseudoAccepte = true;
					oos.writeBoolean(true);
				}

				if (potentiel.getPseudo().equals("exit")) {
					oos.writeBoolean(false);
					break;
				}
				oos.flush();

			} while (!pseudoAccepte);

			if (!potentiel.getPseudo().equals("exit")) {
				

				//potentiel.setSock(new Socket("localhost", potentiel.getPort()));
				//potentiel.setMessageReceptor(t);
				//potentiel.getMesageReceptor().start();
				
				
				mainserver.utilisateurs.add(potentiel);
				System.out.println("Utilisateurs en ligne\n" + mainserver.utilisateurs);

				while ((message != null) && (!message.isEmpty()) && (!message.equals("exit"))) {

					message = (String) ois.readObject();
					//oos.writeObject("Message � tous les clients : " + message);
					envoyerATous(message);
					//oos.flush();
					
					if (message.equals("exit")) {
						System.out.print("Un client s'est d�connect�\n");
						break;
					}
					System.out.println("\nMessage client : " + message);					
				}
			}

		} catch (IOException e) {
			Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, e);
		} catch (ClassNotFoundException e) {
			System.out.println("error in request loop :" + e.getMessage());
		}
	}

	public boolean checkPseudo(Utilisateur potentiel) {
		for (int i = 0; i < mainserver.utilisateurs.size(); i++) {
			if (mainserver.utilisateurs.get(i).equals(potentiel))
				return false;
		}
		return true;
	}
	
	public void envoyerATous(String message) {
		System.out.println("Liste des threadsServer: " + mainserver.threadsServer);
		
		for (int i = 0; i < mainserver.threadsServer.size(); i++) {
			try {
				mainserver.threadsServer.get(i).oos.writeObject("Message � tous les clients : " + message);
				oos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
