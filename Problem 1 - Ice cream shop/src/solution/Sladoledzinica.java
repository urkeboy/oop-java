package solution;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class Sladoledzinica extends Frame {
	
	private Panel panelGlavni, panelSouth;
	private TextField textFieldNaziv, textFieldBoja;
	private Button buttonDodajUkus;
	
	private Aparat aparat;
	
	public Sladoledzinica() {
		super();
		stilizujProzor();
		popuniProzor();
//		pack();
		
		// TODO: private class WindowEventHandler extends WindowAdapter
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				aparat.zavrsiNitBezbedno();
				dispose();
//				Dialog d = new Dialog(this, ModalityType.APPLICATION_MODAL);
//				d.setTitle("Naslov");
//				d.add(new Label("Tekst", Label.CENTER));
//				d.setBounds(700, 200, 100, 100);
//				d.setResizable(false);
//				d.addWindowListener(new WindowAdapter() {
//					public void windowClosing(WindowEvent e) {
//						d.dispose();
//					}
//				});
//				d.setVisible(true);
				super.windowClosing(e);
			}
		});
		// TODO: addComponentListener?
		this.requestFocus();
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					dispose();
				}
				super.keyPressed(e);
			}
		});
		
		this.setVisible(true);
	}
	
	private void stilizujProzor() {
		this.setBounds(700, 200, 400, 400); // 394
		this.setResizable(true);
		this.setTitle("Sladoledzinica");
	}
	
	private void popuniProzor() {
		panelGlavni = new Panel(new BorderLayout());
		
		panelSouth = new Panel(new FlowLayout(FlowLayout.LEFT));
		popuniPanelSouth();
		
		aparat = new Aparat();
		panelGlavni.add(aparat, BorderLayout.CENTER);
		panelGlavni.add(panelSouth, BorderLayout.SOUTH);
		
		this.add(panelGlavni);
	}
	
	private void popuniPanelSouth() {
		panelSouth.setBackground(Color.CYAN);
		Font font = new Font("Arial", Font.BOLD, 16);
		int textFieldWidth = 7;
		
		Label labelNaziv = new Label("Naziv:");
		labelNaziv.setFont(font);
		textFieldNaziv = new TextField(textFieldWidth);
		textFieldNaziv.setText(""); // Cokolada, Vanila, Jagoda
		panelSouth.add(labelNaziv);
		panelSouth.add(textFieldNaziv);
		
		Label labelBoja = new Label("Boja:");
		labelBoja.setFont(font);
		textFieldBoja = new TextField(textFieldWidth);
		textFieldBoja.setText(""); // 664000, FFFFDF, F9D0DD
		panelSouth.add(labelBoja);
		panelSouth.add(textFieldBoja);
		
		buttonDodajUkus = new Button("Dodaj ukus");
		buttonDodajUkus.addActionListener((ae) -> {
			String naziv = textFieldNaziv.getText();
			Color boja = Color.decode("#" + textFieldBoja.getText());
			aparat.dodajUkus(naziv, boja);
			aparat.revalidate();
//			textFieldNaziv.setText("");
//			textFieldBoja.setText("");
		});
		panelSouth.add(buttonDodajUkus);
	}
	
	public static void main(String[] args) {
		new Sladoledzinica();
	}
	
}
