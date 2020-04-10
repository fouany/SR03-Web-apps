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

	public MessageReceptor(Socket sock) {
		this.sock = sock;
	}

	@Override
	public void run() {

		String messageRecu = "Exemple";

		try {
			ois = new ObjectInputStream(sock.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

		while (true) {
			try {

				messageRecu = (String) ois.readObject();
				System.out.println(messageRecu);

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
