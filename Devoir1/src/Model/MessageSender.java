package Model;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MessageSender extends Thread{
	
	private Socket client;
	
	public MessageSender(Socket client) {
		this.client = client;
	}

	@Override
	public void run() {
		try {
			OutputStream outs = client.getOutputStream();
			while(true) {
				//lecture du contenu du InputStream
				System.out.println("blablou");
			}
		} catch (IOException ex) {
	        Logger.getLogger(MessageReceptor.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}

}

