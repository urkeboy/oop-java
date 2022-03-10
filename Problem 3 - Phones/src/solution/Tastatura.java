package solution;

import java.awt.Button;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;

@SuppressWarnings("serial")
public class Tastatura extends Panel implements Runnable {
	
	enum Rezim {
		BROJEVI,
		SLOVA;
		Rezim promeni() {
			if (this == BROJEVI)
				return SLOVA;
			return BROJEVI;
		}
	}
	private Rezim rezim = Rezim.BROJEVI;
	
	private Label labelNatpis;
	
	private static String[][] oznakeBROJEVI = {{"1", "2", "3"},{"4", "5", "6"},{"7", "8", "9"},{"*", "0", "+"}};
	private static String[][] oznakeSLOVA = {{"", "ABC", "DEF"},{"GHI", "JKL", "MNO"},{"PQRS", "TUV", "WXYZ"},{"", "_", ""}};
	private Button[][] dugmici = new Button[4][3];
	
	private static long sleepTime = 1000;
	private Thread thread = null;
	private int redAktivno, kolAktivno, brojKlikova;
	private Button buttonAktivno;
	private boolean opetKliknuto = false;
	
	public Tastatura(Label natpis) {
		super(new GridLayout(4, 3));
		this.labelNatpis = natpis;
		popuniPanel();
	}
	
	private void popuniPanel() {
		inicijalizujDugmice();
		obeleziDugmice();
//		super(new BorderLayout());
//		private Panel panelDugmici;
//		panelDugmici = new Panel(new GridLayout(4, 3));
//		this.add(panelDugmici, BorderLayout.CENTER);
//		panelDugmici.add(dugmici[r][c]);
	}
	
	private void inicijalizujDugmice() {
		Font font = new Font("Arial", Font.BOLD, 20);
		for (int r = 0; r < 4 ; r++) {
			for (int c = 0; c < 3; c++) {
				final int red = r;
				final int kol = c;
				dugmici[r][c] = new Button();
				dugmici[r][c].setFont(font);
				dugmici[r][c].addActionListener((ae) -> {
					opetKliknuto = false;
					switch(rezim) {
					case BROJEVI:
						dodajSlovo(oznakeBROJEVI[red][kol].charAt(0));
//						labelNatpis.setText(labelNatpis.getText() + oznakeBROJEVI[red][kol]);
						break;
					case SLOVA:
						if (red == 0 & kol == 0) {
							// prvo dugme (ne radi nista)
						}
						else if (red != 3) {
							// normalni dugmici sa slovima
							if (dugmici[red][kol] == buttonAktivno) {
								// ovde si ako se ponovio klik
//								System.out.println("Ponovljen klik dugmeta!");
								synchronized (buttonAktivno) {
									brojKlikova++;
									promeniPoslednjeSlovo(oznakeSLOVA[redAktivno][kolAktivno].charAt(brojKlikova % oznakeSLOVA[redAktivno][kolAktivno].length()));
									dugmici[red][kol].notifyAll();
									opetKliknuto = true;
								}
							}
							else {
								// ovde si ako je pritisnuto novo dugme
//								System.out.println("Novo dugme kliknuto!");
								brojKlikova = 0;
								if (thread != null) {
									zaustaviNit();
//									System.out.println("Zaustavljanje prethodne niti!");
								}
								pokreniNit();
								redAktivno = red;
								kolAktivno = kol;
								buttonAktivno = dugmici[red][kol];
								dodajSlovo(oznakeSLOVA[redAktivno][kolAktivno].charAt(brojKlikova % 3));
							}
//							System.out.println("Brojac: " + brojKlikova);
						}
						else if (kol == 1) {
							// dugme za razmak
							dodajSlovo(' ');
						}
						break;
					default:
						break;
					}
				});
				this.add(dugmici[r][c]);
			}
		}
	}
	
	private void obeleziDugmice() {
		for (int r = 0; r < 4 ; r++) {
			for (int c = 0; c < 3; c++) {
				switch(rezim) {
				case BROJEVI:
					dugmici[r][c].setLabel(oznakeBROJEVI[r][c]);
					break;
				case SLOVA:
					dugmici[r][c].setLabel(oznakeSLOVA[r][c]);
					break;
				default:
					break;
				}
			}
		}
	}
	
	public String getNatpis() {
		return labelNatpis.getText();
	}
	
	public void setNatpis(Label natpis) {
		labelNatpis = natpis;
	}
	
	private void dodajSlovo(char c) {
		labelNatpis.setText(labelNatpis.getText() + c);
	}
	
	private void promeniPoslednjeSlovo(char c) {
		if (labelNatpis.getText().length() == 0) return;
		labelNatpis.setText(labelNatpis.getText().substring(0, labelNatpis.getText().length() - 1) + c);
	}
	
	public Rezim getRezim() {
		return rezim;
	}
	
	public void promeniRezim() {
		rezim = rezim.promeni();
		obeleziDugmice();
	}
	
/* ################## RAD SA NITIMA ##################################


 */
	
	@Override
	public void run() {
//		System.out.println("RUN - START");
		try {
			while(!(thread.isInterrupted())) {
				boolean ponovi = false;
				synchronized (buttonAktivno) {
					buttonAktivno.wait(sleepTime);
					// ovde si svakako
					ponovi = opetKliknuto;
					opetKliknuto = false;
				}
				if (ponovi == false) {
					// ovde si ako je istekao tajmer
					thread.interrupt();
//					System.out.println("Timeout!");
				}
				else {
					// ovde si ako se ponovio klik
//					System.out.println("TAJMER RESETOVAN");
				}
			}
		} catch (InterruptedException e) {
			// ovde si ako je pritisnuto neko drugo dugme
//			System.out.println("Interrupted!");
		}
		buttonAktivno = null;
		thread = null;
//		System.out.println("RUN - END");
	}
	
	private void pokreniNit() {
		thread = new Thread(this);
		thread.start();
//		System.out.println("Nit pokrenuta...");
	}
	
	private void zaustaviNit() {
		thread.interrupt();
		try {
			thread.join();
		} catch (InterruptedException e) {}
//		System.out.println("Nit zavrsena...");
	}
	
}
