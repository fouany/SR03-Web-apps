package Modele;

import java.time.Instant;

public class Message {
	
	private int id;
	private String contenu;
	private Instant date;
	private int heure;

	
	public Message(int id, String contenu, Instant date, int heure) {
		this.id = id;
		this.contenu = contenu;
		this.date = date;
		this.heure = heure;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getContenu() {
		return contenu;
	}


	public void setContenu(String contenu) {
		this.contenu = contenu;
	}


	public Instant getDate() {
		return date;
	}


	public void setDate(Instant date) {
		this.date = date;
	}


	public int getHeure() {
		return heure;
	}


	public void setHeure(int heure) {
		this.heure = heure;
	}


	@Override
	public String toString() {
		return "Message [id=" + id + ", contenu=" + contenu + ", date=" + date + ", heure=" + heure + "]";
	}
	
	
	
	

}
