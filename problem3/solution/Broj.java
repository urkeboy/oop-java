package solution;

public class Broj {
	
	private int kodDrzave;
	private int pozivniBroj;
	private int brojPretplatnika;
	
	public Broj(int kodDrzave, int pozivniBroj, int brojPretplatnika) {
		this.kodDrzave = kodDrzave;
		this.pozivniBroj = pozivniBroj;
		this.brojPretplatnika = brojPretplatnika;
	}
	
	public Broj(String broj) {
		this.kodDrzave = Integer.parseInt(broj.substring(1, 4));
		this.pozivniBroj = Integer.parseInt(broj.substring(4, 6));
		this.brojPretplatnika = Integer.parseInt(broj.substring(6));
	}

	public boolean uporediKodDrzave(Broj b) {
		return this.kodDrzave == b.kodDrzave;
	}
	
	public boolean uporediMreze(Broj b) {
		return uporediKodDrzave(b) & (this.pozivniBroj == b.pozivniBroj);
	}
	
	public boolean uporediBroj(Broj b) {
		return uporediKodDrzave(b) & uporediMreze(b) & (this.brojPretplatnika == b.brojPretplatnika);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Broj br = (Broj) obj;
		return this.uporediBroj(br);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("+").append(kodDrzave + " ").append(pozivniBroj + " ").append(brojPretplatnika);
		return sb.toString();
	}
	
}
