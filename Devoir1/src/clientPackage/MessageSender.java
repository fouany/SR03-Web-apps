package clientPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import Model.Utilisateur;

public class MessageSender extends Thread {

	private Socket sock;
	private ObjectOutputStream oos = null;
	private BufferedReader consoleIn = null;
	
	MainClient mainclient;

	public MessageSender(Socket sock, MainClient mainclient) {
		this.sock = sock;
		this.mainclient = mainclient;
	}

	@Override
	public void run() {

		try {
			oos = new ObjectOutputStream(sock.getOutputStream());
			oos.flush();

			requestLoop();
			
			oos.flush();
			oos.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void requestLoop() {

		consoleIn = new BufferedReader(new InputStreamReader(System.in));
		connexion();
		saisirMessages();
	}
	
	
	public void connexion(){
		String pseudo = "Exemple";
		
		try {
			System.out.println("Entrez votre pseudo: ");
			pseudo = consoleIn.readLine();
			mainclient.utilisateur = new Utilisateur(pseudo);
			oos.writeObject(mainclient.utilisateur);
			oos.flush();
			
		} catch (IOException e) {
			Logger.getLogger(MessageSender.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	public void saisirMessages(){
		String message = "Exemple";
		try {
			while (true) {
				System.out.println("Entrez un message: ");
				message = consoleIn.readLine();
				oos.writeObject(message);
				oos.flush();
				/*if (message.equals("exit")) {
					System.out.print("Un client s'est déconnecté\n");
				}*/
			}
		} catch (IOException e) {
			Logger.getLogger(MessageSender.class.getName()).log(Level.SEVERE, null, e);
		}
	}
}