package Model;

import java.io.*;

public class Participant implements Serializable{
	
	private String pseudo;

	public Participant(String pseudo) {
		super();
		this.pseudo = pseudo;
	}
	
	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Participant other = (Participant) obj;
		if (pseudo == null) {
			if (other.pseudo != null)
				return false;
		} else if (!pseudo.equals(other.pseudo))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Participant [pseudo=" + pseudo + "]";
	}

}