package solution;

import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class Telefoni extends Frame {
	
	private Panel panelGlavni;
	private Telefon telefon1, telefon2;
	
	public Telefoni() {
		super();
		stilizujProzor();
		popuniProzor();
//		pack();
		
		// TODO: private class WindowEventHandler extends WindowAdapter
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
//				Dialog d = new Dialog(this, ModalityType.APPLICATION_MODAL);
//				d.setTitle("Naslov");
//				d.add(new Label("Tekst", Label.CENTER));
//				d.setBounds(700, 200, 100, 100);
//				d.setResizable(false);
//				d.addWindowListener(new WindowAdapter() {
//					public void windowClosing(WindowEvent e) {
//						d.dispose();
//						this.dispose();
//					}
//				});
//				d.setVisible(true);
				super.windowClosing(e);
			}
		});
		// TODO: ne radi probaj addComponentListener?
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
		this.setBounds(700, 200, 500, 400);
		this.setResizable(false);
		this.setTitle("Telefoni");
	}
	
	private void popuniProzor() {
		panelGlavni = new Panel(new GridLayout(1, 2, 0, 0));
		
		telefon1 = new Telefon(new Broj("+38160123"), Color.GREEN);
		telefon2 = new Telefon(new Broj("+38161123"), Color.YELLOW);
		
		panelGlavni.add(telefon1);
		panelGlavni.add(telefon2);
		
		this.add(panelGlavni);
	}

	public static void main(String[] args) {
		new Telefoni();
	}

}
