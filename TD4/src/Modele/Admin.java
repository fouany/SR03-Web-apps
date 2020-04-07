package Modele;

public class Admin {
	
	private int id;
	private String pseudo;
	
	public Admin(int id, String pseudo) {
		this.id = id;
		this.pseudo = pseudo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", pseudo=" + pseudo + "]";
	}
	
	
	
	public String insert(int id, String pseudo) {
		return "INSERT INTO Admin VALUES ("
				+ id + ", " 
				+ pseudo
				+ ");";
	}
	
	public String update(int id, String pseudo) {
		return "UPDATE Admin SET "
				+ "pseudo_admin" + pseudo 
				+ "WHERE id_admin = " + id
				+ ";";
	}
	
	public String delete(int id) {
		return "DELETE FROM Admin WHERE id_admin = " + id + ");";
	}
	

}
