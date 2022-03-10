package solution;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

@SuppressWarnings("serial")
public class Polje extends Canvas {
	
	enum Status {
		SLOBODNO,
		IZABRANO;
		Status promeni() {
			if (this == SLOBODNO)
				return IZABRANO;
			return SLOBODNO;
		}
	}
	
	@SuppressWarnings("unused")
	private Mreza mreza;
	private int vrednost;
	private Status status = Status.SLOBODNO;
	
	private int sirina = 75;
	private int visina = 75;
	private Color bojaPozadine = Color.ORANGE;

	public Polje(Mreza mreza, int vrednost) {
		super();
		this.mreza = mreza;
		this.vrednost = vrednost;
		this.setBackground(bojaPozadine);
		this.setPreferredSize(new Dimension(sirina, visina));
	}

	public int getVrednost() {
		return vrednost;
	}
	
	// TODO: odluciti da li koristimo vrednost ili natpis (labelu)
//	public Label getNatpis() {
//		Label natpis = new Label("" + vrednost, Label.CENTER);
//		int velicina = Math.min(visina, sirina);
//		int x = (int) (sirina - velicina) / 2;
//		int y = (int) (visina - velicina) / 2;
//		natpis.setBounds(x, y, velicina, velicina);
//		natpis.setBounds(0, 0, 50, 50);
//		return natpis;
//	}

	@Override
	public void paint(Graphics g) {
		Color oldColor = g.getColor();
		// <--
		int w = this.getWidth();
		int h = this.getHeight();
		if (status == Status.IZABRANO) {
			g.setColor(Color.BLUE);
			g.fillOval(0, 0, w, h);
			g.setColor(Color.WHITE);
		}
		else {
			g.setColor(Color.BLACK);
		}
		int fontSize = Math.min(w, h)/3;
		g.setFont(new Font("Arial", Font.BOLD, fontSize));
		int dw = g.getFontMetrics().stringWidth("" + vrednost) / 2;
		int dh = fontSize / 2;
		g.drawString("" + vrednost, w/2 - dw, h/2 + dh);
		// -->
		g.setColor(oldColor);
//		super.paint(g);
	}
	
	// TODO: Polje treba da "prosledi informaciju" Mrezi da se status promenio???
	public Status promeniStatus() {
		status = status.promeni();
		this.repaint();
		return status;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}

}
