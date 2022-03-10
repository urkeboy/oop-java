package test;

import solution.Broj;

public class TestBroj {

	public static void main(String[] args) {
		Broj b1 = new Broj(381, 60, 161);
		Broj b2 = new Broj(381, 60, 888);
		Broj b3 = new Broj("+38161123456");
		Broj b4 = new Broj("+38160161");
		
		System.out.println(b1);
		System.out.println(b2);
		System.out.println(b3);
		System.out.println(b4);
		
		System.out.println(b1.uporediKodDrzave(b2));
		System.out.println(b1.uporediMreze(b2));
		System.out.println(b1.uporediBroj(b2));
		System.out.println(b1.equals(b2));
		System.out.println(b1.equals(b4));
	}

}
