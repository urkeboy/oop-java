package solution;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class Igra extends Frame {
	
	private Panel panelGlavni, panelSouth, panelEast;
	private Label labelBalansVrednost, labelKvotaVrednost, labelDobitakVrednost, labelBrojevi;
	private TextField textFieldUlog;
	private Button buttonIgraj;
	
	private Mreza mreza;
	private double balans = 0;
	private double ulog = 100;
	private double kvota;
	private double dobitak;
	
	public Mreza getMreza() {
		return mreza;
	}

	public Igra() {
		super();
		stilizujProzor();
		popuniProzor();
		pack();
		
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
//		this.requestFocus();
		this.setFocusable(true);
		this.toFront();
		this.requestFocus();
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				System.out.println("Dugme");
				if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					System.out.println("ESC");
					dispose();
				}
				super.keyPressed(e);
			}
		});
		
		this.setVisible(true);
	}
	
	private void stilizujProzor() {
		this.setBounds(700, 200, 600, 400);
		this.setResizable(true);
		this.setTitle("Igra");
	}
	
	private void popuniProzor() {
		panelGlavni = new Panel(new BorderLayout());
		
		panelSouth = new Panel(new FlowLayout(FlowLayout.LEFT));
		popuniPanelSouth();
		panelEast = new Panel(new GridLayout(0, 1));
		popuniPanelEast();
		mreza = new Mreza(this);
		
		panelGlavni.add(panelSouth, BorderLayout.SOUTH);
		panelGlavni.add(panelEast, BorderLayout.EAST);
		panelGlavni.add(mreza, BorderLayout.CENTER);
		
		this.add(panelGlavni);
	}
	
	private void popuniPanelEast() {
		panelEast.setBackground(Color.LIGHT_GRAY);
		
		Panel panelBalans = new Panel(new FlowLayout(FlowLayout.LEFT));
		Label labelBalans = new Label("Balans:");
		labelBalansVrednost = new Label("" + balans);
		panelBalans.add(labelBalans);
		panelBalans.add(labelBalansVrednost);
		
		Panel panelUlog = new Panel(new FlowLayout(FlowLayout.LEFT));
		Label labelUlog = new Label("Ulog:");
		textFieldUlog = new TextField("100", 7);
		textFieldUlog.addTextListener(new TextListener() {
			@Override
			public void textValueChanged(TextEvent e) {
				Igra.this.azurirajKvotuIDobitak();
			}
		});
		panelUlog.add(labelUlog);
		panelUlog.add(textFieldUlog);
		
		Panel panelKvota = new Panel(new FlowLayout(FlowLayout.LEFT));
		Label labelKvota = new Label("Kvota:");
		labelKvotaVrednost = new Label("" + kvota);
		panelKvota.add(labelKvota);
		panelKvota.add(labelKvotaVrednost);
		
		Panel panelDobitak = new Panel(new FlowLayout(FlowLayout.LEFT));
		Label labelDobitak = new Label("Dobitak:");
		labelDobitakVrednost = new Label("" + dobitak);
		panelDobitak.add(labelDobitak);
		panelDobitak.add(labelDobitakVrednost);
		
		Panel panelIgraj = new Panel(new FlowLayout(FlowLayout.RIGHT));
		buttonIgraj = new Button("Igraj");
		buttonIgraj.setEnabled(false);
		buttonIgraj.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				igraj();
				super.mouseClicked(e);
			}
		});
		panelIgraj.add(buttonIgraj);
		
		panelEast.add(panelBalans);
		panelEast.add(panelUlog);
		panelEast.add(panelKvota);
		panelEast.add(panelDobitak);
		panelEast.add(panelIgraj);
	}
	
	private void popuniPanelSouth() {
		panelSouth.setBackground(Color.GRAY);
		
		labelBrojevi = new Label("");
		labelBrojevi.setFont(new Font("Arial", Font.BOLD, 14));
		panelSouth.add(labelBrojevi);
	}
	
	protected void azurirajKvotuIDobitak() {
		if (textFieldUlog.getText().length() > 0) {
			int brojIzabranihPolja = mreza.dohvatiListuIzabranihPolja().size();
			if (brojIzabranihPolja != 0) {
				kvota = mreza.dohvatiBrojPolja() * 1.0 / brojIzabranihPolja;
				// TODO: Exception kada je prazan string?
				ulog = Integer.parseInt(textFieldUlog.getText());
				dobitak = ulog * kvota;
			}
			else {
				kvota = dobitak = 0;
			}
			// Azuriraj kvotu i dobitak
			azurirajKvotu();
			azurirajDobitak();
		}
	}
	
	private void azurirajKvotu() {
		labelKvotaVrednost.setText(String.format("%.2f", kvota));
		labelKvotaVrednost.revalidate();
	}
	
	private void azurirajDobitak() {
		labelDobitakVrednost.setText(String.format("%.2f", dobitak));
		labelDobitakVrednost.revalidate();
	}
	
	private void azurirajBalans() {
		labelBalansVrednost.setText(String.format("%.2f", balans));
		labelBalansVrednost.revalidate();
	}
	
	private void azurirajStatusnuTraku(int broj, Color boja) {
		labelBrojevi.setText(labelBrojevi.getText() + " " + broj);
		labelBrojevi.setBackground(boja);
		panelSouth.setBackground(boja);
		labelBrojevi.revalidate();
	}
	
	private void igraj() {
		// Generisi nasumican broj
		Generator generator = new Generator(0, mreza.dohvatiBrojPolja());
		int broj = generator.slucajanBroj();
		// Izracunaj konacni balans
		balans -= ulog;
		if (mreza.dohvatiSetIzabranihVrednosti().contains(broj)) {
			balans += dobitak;
			azurirajStatusnuTraku(broj, Color.GREEN);
		}
		else {
			azurirajStatusnuTraku(broj, Color.RED);
		}
		// Azuriraj balans
		azurirajBalans();
	}
	
	public void setButtonIgraj (boolean igraj) {
		buttonIgraj.setEnabled(igraj);
	}
	
	public static void main(String[] args) {
		new Igra();
	}
	
}
