package serverPackage;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
       
		String message ="";
		int compteur = 0;

        try {
        	 ServerSocket server = new ServerSocket(7000);
             System.out.println("Serveur à l'écoute ......");
             Socket client = server.accept();
             System.out.println(" Nouveau client ...");
             
        	do {
        		 ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
                 message = (String) ois.readObject();
                 System.out.println("Message du client : " + message);    	
        	} while(true);
           

        } catch (IOException ex) {
            System.err.println("Client deconnecté ...");
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}
