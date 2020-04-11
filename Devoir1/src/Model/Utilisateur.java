package Model;

import java.io.Serializable;

/**
 * Constitue le modele d'un utilisateur, modelise par un peudo
 * Il a une getter, une setter, une equals() et une toString()
 * Ainsi, les donnes sont facilement acessibles et affichables en console grace a la toString()
 */
public class Utilisateur implements Serializable{
	
	private String pseudo;
	
	public Utilisateur(String pseudo) {
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
		return "Utilisateur [pseudo=" + pseudo + "]";
	}

}
