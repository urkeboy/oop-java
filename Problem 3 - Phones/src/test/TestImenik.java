package test;

import greske.GNePostoji;
import solution.Broj;
import solution.Imenik;
import solution.Kontakt;

public class TestImenik {

	public static void main(String[] args) {
		Imenik i = new Imenik();
		Broj b1 = new Broj("+381 60 161");
		Broj b2 = new Broj("+381 60 123");
		i.dodajStavku(new Kontakt("Uros", b1));
		i.dodajStavku(new Kontakt("Gagi", b2));
		
		try {
			System.out.println(i.dohvatiBrojKorisnika("Uros"));
			System.out.println(i.dohvatiImeKorisnika(b2));
			System.out.println(i.dohvatiBrojKorisnika("Bane"));
		} catch (GNePostoji e) {
			System.out.println(e.getMessage());
//			e.printStackTrace();
		}
	}
	
}
