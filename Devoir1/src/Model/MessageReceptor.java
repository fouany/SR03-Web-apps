package Model;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MessageReceptor extends Thread {
	
	private Socket client;
	
	public MessageReceptor(Socket client) {
		this.client = client;
	}

	@Override
	public void run() {
		try {
			InputStream ins = client.getInputStream();
			while(true) {
				//lecture du contenu du InputStream
				System.out.println("blablou");
			}
		} catch (IOException ex) {
	        Logger.getLogger(MessageReceptor.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}

}
