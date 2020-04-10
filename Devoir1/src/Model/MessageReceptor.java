package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MessageReceptor extends Thread{
	
	private Socket sock;
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;

	
	public MessageReceptor(Socket sock) {
		this.sock = sock;
	    try {
			this.oos = new ObjectOutputStream(sock.getOutputStream());
			this.ois = new ObjectInputStream(sock.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		
			while(true) {
				try {
					System.out.println((String) ois.readObject());
					System.out.println("blablouuuuu");

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
	}

}

