package serverPackage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import Model.Participant;

public class Server {
	
	private static ArrayList<Socket> clientsSockets = new ArrayList<Socket>();
	private static ArrayList<Participant> participants = new ArrayList<Participant>();

	public static void main(String[] args) {
       
		Participant potentiel;
		boolean pseudoAccepte;
		
		participants.add(new Participant("antoine"));
		
        try {
        	 ServerSocket server = new ServerSocket(7000);
             System.out.println("Serveur à l'écoute ......");
             Socket client = server.accept();
             System.out.println(" Nouveau client ...");
             
        	do {
        		do {
        			ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
                    potentiel = (Participant) ois.readObject();
                    ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
 
                    if (!checkPseudo(potentiel)) {
                    	pseudoAccepte = false;
                    	oos.writeBoolean(false);
                    } else {
                    	pseudoAccepte = true;
                    	oos.writeBoolean(true);
                    }
                    oos.flush();
                 
        		} while (!pseudoAccepte);
                System.out.println("Pseudo du nouveau participant : " + potentiel.getPseudo()); 

        		 	                  
        	} while(true);
           

        } catch (IOException ex) {
            System.err.println("Client deconnecté ...");
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	public static boolean checkPseudo(Participant potentiel) {
		 for (int i = 0; i < participants.size(); i++) {
			 if (participants.get(i).equals(potentiel))
				 return false;	 
		 }
		 return true;
	}	
		
}
