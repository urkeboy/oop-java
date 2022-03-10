package test;

import java.awt.Color;

import solution.Sladoled;
import solution.Ukus;

public class Test {

	public static void main(String[] args) {
		Ukus u1 = new Ukus("kruska", Color.green);
		Ukus u2 = new Ukus("kruska", Color.yellow);
		
		Sladoled s1 = new Sladoled(100);
		System.out.println(s1);
		s1.dodajUkus(u1, 20);
		System.out.println(s1);
		s1.dodajUkus(u1, 30);
		System.out.println(s1);
		s1.dodajUkus(u2, 30);
		System.out.println(s1);
		s1.dodajUkus(u2, 50);
		System.out.println(s1);
		s1.dodajUkus(u2, 50);
		System.out.println(s1);
		
		Ukus u3 = new Ukus("malina", Color.red);
		Ukus u4 = new Ukus("banana", Color.yellow);
		
		Sladoled s2 = new Sladoled(100);
		System.out.println(s2);
		s2.dodajUkus(u3, 20);
		System.out.println(s2);
		s2.dodajUkus(u4, 20);
		System.out.println(s2);
		s2.dodajUkus(u4, 20);
		System.out.println(s2);
		s2.dodajUkus(u3, 20);
		System.out.println(s2);

	}

}
