package Controleur;

import java.sql.*;

public class TestJDBCMysql {

	Connection db = null; // Connexion � la BD
	Statement sql = null; // Curseur pour la requ�te
	DatabaseMetaData dbmd; // M�ta-donn�es issues du driver

	public TestJDBCMysql(String argv[]) throws ClassNotFoundException, SQLException, java.io.IOException {

		String database = argv[0];
		String username = argv[1];
		String password = argv[2];

		Class.forName("com.mysql.jbdc.Driver"); // R�cup�ration du Driver
		db = DriverManager.getConnection("jdbc:mysql: " + database, username, password); // Connexion
	}

	public void createTableAdmin() {
		try {
			sql = db.createStatement(); // Cr�ation du curseur pour la requ�te

			String sqlText = "CREATE TABLE Admin ( " 
					+ "id_admin SERIAL," + "pseudo_admin VARCHAR,"
					+ "CONSTRAINT id_amin PRIMARY KEY (id_admin)" 
					+ ");";

			System.out.println("Executing this command : " + sqlText + "\n");
			sql.execute(sqlText); // Execution de la commande
			db.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
