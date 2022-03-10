package solution;

import java.awt.GridLayout;
import java.awt.Panel;

@SuppressWarnings("serial")
public abstract class Lista extends Panel {
	
	public Lista() {
		super(new GridLayout(0, 1, 0, 0));
	}
	
	public void dodajStavku(Stavka stavka) {
		this.add(stavka);
	}
	
}
