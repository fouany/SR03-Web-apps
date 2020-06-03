package Modele;

public class Utilisateur {
	
	private int id;
	private String pseudo;
	private int age;
	
	public Utilisateur(int id, String pseudo, int age) {
		this.id = id;
		this.pseudo = pseudo;
		this.age = age;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", pseudo=" + pseudo + ", age=" + age + "]";
	}
	
	
	
	

}
