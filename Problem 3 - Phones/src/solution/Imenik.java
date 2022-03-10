package solution;

import java.awt.Component;

import greske.GNePostoji;

@SuppressWarnings("serial")
public class Imenik extends Lista {

	public Imenik() {
		super();
	}

	@Override
	public void dodajStavku(Stavka stavka) {
		if (!(stavka instanceof Kontakt)) {
			return;
		}
		else {
			super.dodajStavku(stavka);
		}
	}
	
	public String dohvatiImeKorisnika(Broj broj) throws GNePostoji {
		for (Component c : this.getComponents()) {
			Kontakt k = (Kontakt) c;
			if (broj.equals(k.getBroj())) {
				return k.getIme();
			}
		}
		throw new GNePostoji();
	}
	
	public Broj dohvatiBrojKorisnika(String ime) throws GNePostoji {
		for (Component c : this.getComponents()) {
			Kontakt k = (Kontakt) c;
			if (ime.equals(k.getIme())) {
				return k.getBroj();
			}
		}
		throw new GNePostoji();
	}
	
}
