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
			oos.flush();

			requestLoop();

			oos.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void requestLoop() {

		String message = "Exemple";
		consoleIn = new BufferedReader(new InputStreamReader(System.in));
		try {
			while (true) {
				System.out.println("Entrez un message: ");
				message = consoleIn.readLine();
				oos.writeObject(message);
				oos.flush();
				if (message.equals("exit")) {
					System.out.print("Un client s'est déconnecté\n");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
