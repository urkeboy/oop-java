package solution;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;

@SuppressWarnings("serial")
public abstract class Stavka extends Panel {

	private Label labelNaslov, labelTekst;

	public Stavka(String naslov, String tekst) {
		super(new GridLayout(2, 1));
		
		labelNaslov = new Label(naslov);
		labelNaslov.setFont(new Font("Arial", Font.BOLD, 12));
		
		labelTekst = new Label(tekst);
		labelTekst.setFont(new Font("Arial", Font.PLAIN, 12));
		
		this.add(labelNaslov);
		this.add(labelTekst);
	}
	
	public void setNaslov(String naslov) {
		this.labelNaslov.setText(naslov);
	}
	
	public String getNaslov() {
		return labelNaslov.getText();
	}
	
	public String getTekst() {
		return labelTekst.getText();
	}
	
}
