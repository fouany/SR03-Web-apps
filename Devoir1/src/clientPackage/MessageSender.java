package clientPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MessageSender extends Thread {

	private Socket sock;
	private ObjectOutputStream oos = null;
	private BufferedReader consoleIn = null;

	public MessageSender(Socket sock) {
		this.sock = sock;
	}

	@Override
	public void run() {
		
		try {
			oos = new ObjectOutputStream(sock.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String message = "Exemple";
		consoleIn = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			try {
				System.out.println("Entrez un message: ");
				message = consoleIn.readLine();
				oos.writeObject(message);
				oos.flush();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
