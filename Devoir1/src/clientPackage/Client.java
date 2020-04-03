package clientPackage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import Model.Utilisateur;

public class Client {
	
	public static void main(String[] args){
		
		boolean pseudoAccepte = true;
		
	    try {
	    	Socket client = new Socket("localhost", 7000);
	        System.out.println("Connecté ....");
	        Scanner sc = new Scanner(System.in);
	        
	    	do {
	    		
	    		do {
	    			String pseudo = "";
	    			if (pseudoAccepte) System.out.println ("Entrez votre pseudo : ");
	    			else System.out.println ("Veuillez saisir un autre pseudo : ");
		 	        
	    			pseudo = sc.nextLine();
		 	        	 	        
		 	        Utilisateur potentiel = new Utilisateur(pseudo, null);
		 	      
		 	        ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
		 	        oos.writeObject(potentiel);
		 	        oos.flush();
		 	        
        			ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
		 	        pseudoAccepte = ois.readBoolean();
		 	     
	    		} while(!pseudoAccepte);

	    	} while(true);
                      
	    } catch (IOException ex) {
	        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
}
