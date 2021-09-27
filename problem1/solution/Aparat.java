package solution;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

@SuppressWarnings("serial")
public class Aparat extends Panel {
	
	private Panel panelUkusi, panelProdaja, panelCenter, panelSouth;
	private Label labelSladoled, labelSladoledVrednost;
	private Button buttonProdaj;
	
	private Mesto mesto;
	private LinkedList<Ukus> ukusi = new LinkedList<>();
	private static int jednoTocenje = 20;
	
	public Aparat() {
		super(new BorderLayout());
		popuniProzor();
	}
	
	private void popuniProzor() {
		panelUkusi = new Panel(new GridLayout(1, 1));
		popuniPanelUkusi();
		
		panelProdaja = new Panel(new GridLayout(2, 1));
		popuniPanelProdaja();
		
		panelCenter = new Panel(new GridLayout(1, 2));
		popuniPanelCenter();
		
		panelSouth = new Panel(new FlowLayout(FlowLayout.LEFT));
		popuniPanelSouth();
		
		this.add(panelCenter, BorderLayout.CENTER);
		this.add(panelSouth, BorderLayout.SOUTH);
	}
	
	private void popuniPanelUkusi() {
		panelUkusi.setBackground(Color.decode("#D8D8D8"));
	}
	
	private void popuniPanelProdaja() {
		buttonProdaj = new Button("Prodaj");
		buttonProdaj.addActionListener((ae) -> {
			System.out.println(mesto.getSladoled().toString());
			mesto.resetujTocenje();
			azurirajSladoled();
			buttonProdaj.setEnabled(false);
			zavrsiNitBezbedno(); // zavrsi nit
		});
		buttonProdaj.setEnabled(false);
		panelProdaja.add(buttonProdaj);
		
		mesto = new Mesto(this);
		panelProdaja.add(mesto);
	}
	
	private void popuniPanelCenter() {
		panelCenter.add(panelUkusi);
		panelCenter.add(panelProdaja);
	}
	
	private void popuniPanelSouth() {
		panelSouth.setBackground(Color.LIGHT_GRAY);
		
		labelSladoled = new Label("Sladoled:");
		labelSladoled.setFont(new Font("Arial", Font.BOLD, 16));
		panelSouth.add(labelSladoled);
		
		labelSladoledVrednost = new Label(mesto.getSladoled().toString());
		panelSouth.add(labelSladoledVrednost);
	}
	
	public void dodajUkus(String naziv, Color boja) {
		ukusi.add(new Ukus(naziv, boja));
		Button dugme = new Button(naziv);
		dugme.setBackground(boja);
		dugme.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mesto.pokreniTocenje(new Ukus(naziv, boja));
			}
			public void mouseReleased(MouseEvent e) {
				mesto.pauzirajTocenje();
			}
		});
		brojDostupnihUkusa++;
		panelUkusi.add(dugme);
		if (brojDostupnihUkusa == 2) {
			panelUkusi.setLayout(new GridLayout(2, 1));
		}
		else if (brojDostupnihUkusa > 2) {
			panelUkusi.setLayout(new GridLayout(0, 2));
		}
	}
	
	public void azurirajSladoled() {
		labelSladoledVrednost.setText(mesto.getSladoled().toString());
		labelSladoledVrednost.revalidate();
	}
	
	public static int getJednoTocenje() {
		return Aparat.jednoTocenje;
	}
	
	public void omoguciProdaju() {
		buttonProdaj.setEnabled(true);
	}
	
	private int brojDostupnihUkusa = 0;
	public int getBrojDostupnihUkusa() {
		return brojDostupnihUkusa;
	}
	
	public void zavrsiNitBezbedno() {
		mesto.prekiniTocenje();
		while (mesto.tocenjeUToku() == true) {}
//		System.out.println("SIGURNO ZAVRSENA NIT!");
	}
	
}