package Model;

import java.io.*;
import java.net.Socket;

public class Utilisateur implements Serializable{
	
	private String pseudo;
	private Socket sock;
	private MessageReceptor messageReceptor;
	
	
	public Utilisateur(String pseudo, Socket sock, MessageReceptor messageReceptor) {
		this.pseudo = pseudo;
		this.sock = sock;
		this.messageReceptor = messageReceptor;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public Socket getSock() {
		return sock;
	}

	public void setSock(Socket sock) {
		this.sock = sock;
	}

	public MessageReceptor getMesageReceptor() {
		return messageReceptor;
	}

	public void setMessageReceptor(MessageReceptor msgReceptor) {
		this.messageReceptor = msgReceptor;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utilisateur other = (Utilisateur) obj;
		
		if (pseudo == null) {
			if (other.pseudo != null)
				return false;
		} else if (!pseudo.equals(other.pseudo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Utilisateur [pseudo=" + pseudo + ", sock=" + sock + ", messageReceptor=" + messageReceptor + "]";
	}

}