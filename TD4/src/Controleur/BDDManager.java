package Controleur;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BDDManager {
	
	public static void main(String[] args){
		
		try {
            Connection con = ConfigConnection.getConnection();
        } catch (IOException ex) {
            Logger.getLogger(ConfigConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConfigConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConfigConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

	}
}
