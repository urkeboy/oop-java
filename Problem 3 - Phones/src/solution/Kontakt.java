package solution;

@SuppressWarnings("serial")
public class Kontakt extends Stavka {
	
	private String ime;
	private Broj broj;

	public Kontakt(String ime, Broj broj) {
		super(ime, broj.toString());
		this.ime = ime;
		this.broj = broj;
	}
	
	public String getIme() {
		return ime;
	}
	
	public Broj getBroj() {
		return broj;
	}
	
}
