package solution;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;

@SuppressWarnings("serial")
public class Mreza extends Panel {
	
	private Igra igra;
	private int redovi;
	private int kolone;
	private static int razmak = 3;
	private ArrayList<Polje> izabranaPolja = new ArrayList<>();
	
	public Mreza(Igra igra) {
		this(igra, 4, 5);
	}
	
	public Mreza(Igra igra, int redovi, int kolone) {
		super(new GridLayout(redovi, kolone, razmak, razmak));
		this.igra = igra;
		this.redovi = redovi;
		this.kolone = kolone;
		stilizujMrezu();
		popuniMrezu();
	}
	
	private void stilizujMrezu() {
		this.setBackground(Color.BLACK);
	}
	
	private void popuniMrezu() {
		for (int i = 0; i < redovi; i++) {
			for (int j = 0; j < kolone; j++) {
				int vrednost = i * kolone + j;
				Polje polje = new Polje(this, vrednost);
				polje.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO: promeniStatus() vraca boolean po uslovu zadatka, ali se to ne koristi?
						Polje.Status status = polje.promeniStatus();
						if (status == Polje.Status.IZABRANO) {
							izabranaPolja.add(polje);
							igra.setButtonIgraj(true);
//							igra.buttonIgraj.setEnabled(true);
						}
						else {
							izabranaPolja.remove(polje);
							if (izabranaPolja.size() <= 0) {
								igra.setButtonIgraj(false);
//								igra.buttonIgraj.setEnabled(false);
							}
						}
						igra.azurirajKvotuIDobitak();
						super.mouseClicked(e);
					}
				});
				// TODO: nit za btn ne radi mozda dodati try/catch InterruptedException e
//				while(!(thread.isInterrupted())) {
//					synchronized (this) { while(!isActive) { this.wait(); } }
//					// radi foo(); ili sleep(m)?
//					isActive = true; notify();
//					thread.join();
//					if (isActive) isActive = false;
//				}
				this.add(polje);
			}
		}
	}
	
	public ArrayList<Polje> dohvatiListuIzabranihPolja() {
		return izabranaPolja;
	}
	
	public HashSet<Integer> dohvatiSetIzabranihVrednosti() {
		HashSet<Integer> brojevi = new HashSet<>();
		for (Polje polje : izabranaPolja) {
			brojevi.add(polje.getVrednost());
		}
		return brojevi;
	}
	
	public int dohvatiBrojPolja() {
		return redovi * kolone;
	}
	
}
