package solution;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;

@SuppressWarnings("serial")
public class Telefon extends Panel {
	
	private Panel panelNorth, panelCenter, panelSouth;
	private Button buttonDodajKontakt, buttonIskljuciTelefon;
	private Label labelBroj, labelBrojUnos, labelImeUnos;

	private Broj broj;
	private Color boja;
	private Imenik imenik;
	private Tastatura tastatura;
	private boolean status = true;
	
	public Telefon(Broj broj, Color boja) {
		super(new BorderLayout());
		this.broj = broj;
		this.boja = boja;
		this.setBackground(boja);
		popuniPanel();
	}
	
	private void popuniPanel() {
		panelNorth = new Panel(new BorderLayout());
		popuniPanelNorth();
		
		panelCenter = new Panel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		popuniPanelCenter();
		
		panelSouth = new Panel(new BorderLayout());
		popuniPanelSouth();
		
		this.add(panelNorth, BorderLayout.NORTH);
		this.add(panelCenter, BorderLayout.CENTER);
		this.add(panelSouth, BorderLayout.SOUTH);
	}
	
	private void popuniPanelNorth() {
		Panel panelLabele = new Panel(new GridLayout(2, 1));
		labelBrojUnos = new Label("");
		labelImeUnos = new Label("");
		panelLabele.add(labelBrojUnos);
		panelLabele.add(labelImeUnos);
		panelNorth.add(panelLabele, BorderLayout.NORTH);
		
		tastatura = new Tastatura(labelBrojUnos);
		panelNorth.add(tastatura, BorderLayout.CENTER);
	}
	
	private void popuniPanelCenter() {
		imenik = new Imenik();
		panelCenter.add(imenik);
	}
	
	private void popuniPanelSouth() {
		Panel panelDugmici = new Panel(new GridLayout(1, 2));
		buttonDodajKontakt = new Button("Dodaj kontakt");
		buttonDodajKontakt.addActionListener((ae) -> {
			if (tastatura.getRezim() == Tastatura.Rezim.BROJEVI) {
				// predji u mod unosa imena
				tastatura.promeniRezim();
				tastatura.setNatpis(labelImeUnos);
			}
			else {
				// predji u mod unosa brojeva i sacuvaj kontakt u imenik
				sacuvajKontakt();
				tastatura.promeniRezim();
				tastatura.setNatpis(labelBrojUnos);
			}
		});
		panelDugmici.add(buttonDodajKontakt);
		
		buttonIskljuciTelefon = new Button("Iskljuci telefon");
		buttonIskljuciTelefon.setBackground(Color.GRAY);
		buttonIskljuciTelefon.addActionListener((ae) -> {
			if (status == true) {
				iskljuciTelefon();
			}
			else {
				ukljuciTelefon();
			}
		});
		panelDugmici.add(buttonIskljuciTelefon);
		
		labelBroj = new Label(broj.toString());
		labelBroj.setFont(new Font("Arial", Font.BOLD, 14));
		labelBroj.setAlignment(Label.CENTER);
		
		panelSouth.add(panelDugmici, BorderLayout.CENTER);
		panelSouth.add(labelBroj, BorderLayout.SOUTH);
	}
	
	public Broj getBroj() {
		return broj;
	}
	
	public Color getBoja() {
		return boja;
	}
	
	private void sacuvajKontakt() {
		imenik.dodajStavku(new Kontakt(labelImeUnos.getText(), new Broj(labelBrojUnos.getText())));
		labelBrojUnos.setText("");
		labelImeUnos.setText("");
		imenik.revalidate();
	}
	
	public void iskljuciTelefon() {
		buttonIskljuciTelefon.setLabel("Ukljuci telefon");
		buttonIskljuciTelefon.setBackground(Color.RED);
		status = false;
	}
	
	public void ukljuciTelefon() {
		buttonIskljuciTelefon.setLabel("Iskljuci telefon");
		buttonIskljuciTelefon.setBackground(Color.GRAY);
		status = true;
	}
	
//	public static void main(String[] args) {
//	public class Telefoni extends Frame {
//		
//		private Panel panelGlavni;
//		private Telefon telefon1, telefon2;
//		
//		public Telefoni() {
//			super();
//			stilizujProzor();
//			popuniProzor();
////			pack();
//			
//			// TODO: private class WindowEventHandler extends WindowAdapter
//			this.addWindowListener(new WindowAdapter() {
//				@Override
//				public void windowClosing(WindowEvent e) {
//					dispose();
//					super.windowClosing(e);
//				}
//			});
//			// TODO: addComponentListener ComponentAdapter componentResized
//			this.requestFocus();
//			this.addKeyListener(new KeyAdapter() {
//				@Override
//				public void keyPressed(KeyEvent e) {
//					if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
//						dispose();
//					}
//					super.keyPressed(e);
//				}
//			});
//			
//			this.setVisible(true);
//		}
//		
//		private void stilizujProzor() {
//			this.setBounds(700, 200, 500, 400);
//			this.setResizable(false);
//			this.setTitle("Telefoni");
//		}
//		
//		private void popuniProzor() {
//			panelGlavni = new Panel(new GridLayout(1, 2, 0, 0));
//			
//			telefon1 = new Telefon(new Broj("+38160123"), Color.GREEN);
//			telefon2 = new Telefon(new Broj("+38161123"), Color.YELLOW);
//			
//			panelGlavni.add(telefon1);
//			panelGlavni.add(telefon2);
//			
//			this.add(panelGlavni);
////			Menu meni1 = new Menu("File");
////			Menu submeni1 = new Menu("Meni 1");
////			MenuItem op1 = new MenuItem("opcija 1"); // addActionListener
////			submeni1.add(op1);
////			meni1.add(submeni1);
////			meni1.addSeparator();
////			MenuItem quitMenu = new MenuItem("Quit", new MenuShortcut(KeyEvent.VK_Q));
////			quitMenu.addActionListener((ae) -> {dispose();});
////			meni1.add(quitMenu);
////			MenuBar menuBar = new MenuBar();
////			menuBar.add(meni1);
////			setMenuBar(menuBar);
////			Dialog d = new Dialog(this, ModalityType.APPLICATION_MODAL);
////			d.setTitle("Naslov");
////			d.add(new Label("Tekst", Label.CENTER));
////			d.setBounds(700, 200, 100, 100);
////			d.setResizable(false);
////			d.addWindowListener(new WindowAdapter() {
////				public void windowClosing(WindowEvent e) {
////					d.dispose();
////				}
////			});
////			d.setVisible(true);
//		}
//		new Telefoni();
//
//	}
//	}
	
}
