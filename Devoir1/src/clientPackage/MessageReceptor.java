package clientPackage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MessageReceptor extends Thread {

	private Socket sock;
	private ObjectInputStream ois = null;

	MainClient mainclient;

	public MessageReceptor(Socket sock, MainClient mainclient) {
		this.sock = sock;
		this.mainclient = mainclient;
	}

	@Override
	public void run() {

		try {
			ois = new ObjectInputStream(sock.getInputStream());

			requestLoop();

			ois.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void requestLoop() {

		String messageRecu = "Exemple";
		try {
			
			/*do {
				mainclient.pseudoAccepte = ois.readBoolean();
			    System.out.println(mainclient.pseudoAccepte);
			} while (!mainclient.pseudoAccepte);*/

			while (true) {
				messageRecu = (String) ois.readObject();
				System.out.println(messageRecu);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();}
		 catch (IOException e) {
			e.printStackTrace();
		}
	}
}
