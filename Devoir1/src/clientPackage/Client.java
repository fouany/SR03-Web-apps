package clientPackage;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
	
	public static void main(String[] args){
		
	    try {
	    	Socket client = new Socket("localhost", 7000);
	        System.out.println("Connecté ....");
	        Scanner sc = new Scanner(System.in);
	        
	    	do {
	    		String message = "";
	  	       
	 	        System.out.println ("Entrez un message : ");
	 	        message = sc.nextLine();
	 	        
	 	        ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
	 	        oos.writeObject(message);
	 	        oos.flush();
	 	        
	    	} while(true);
                      
	    } catch (IOException ex) {
	        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
}
