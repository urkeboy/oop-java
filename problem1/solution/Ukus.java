package solution;

import java.awt.Color;

public class Ukus {

	private String naziv;
	private Color boja;
	
	public String getNaziv() {
		return naziv;
	}

	public Color getBoja() {
		return boja;
	}

	public Ukus(String naziv, Color boja) {
		this.naziv = naziv;
		this.boja = boja;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[").append(naziv).append("]");
		return sb.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ukus other = (Ukus) obj;
		if (naziv == null) {
			if (other.naziv != null)
				return false;
		} else if (!naziv.equals(other.naziv))
			return false;
		return true;
	}
	
}
