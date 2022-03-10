package solution;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Mesto extends Canvas implements Runnable {
	
	private Aparat aparat;
	private Sladoled sladoled;
	
	private Ukus trenutniUkus;
	private ArrayList<Ukus> sipaniUkusi;
	
	private static long sleepTime = 500;
	private static int velicinaCase = 200;
	private static int maxBrojTocenja = Mesto.velicinaCase / Aparat.getJednoTocenje();
	
	public Mesto(Aparat aparat) {
		this.aparat = aparat;
		this.sladoled = new Sladoled(velicinaCase);
	}
	
	public Sladoled getSladoled() {
		return sladoled;
	}
	
	@Override
	public void paint(Graphics g) {
		int sirinaPlatna = this.getWidth();
		int visinaPlatna = this.getHeight();
		int jednoTocenje = (int) (Aparat.getJednoTocenje() * 1.0 / velicinaCase * visinaPlatna);
		int y = visinaPlatna - jednoTocenje;
		if (sipaniUkusi != null) {
			Color oldColor = g.getColor();
			for (Ukus ukus : sipaniUkusi) {
				g.setColor(ukus.getBoja());
				g.fillRect(0, y, sirinaPlatna, jednoTocenje);
				y -= jednoTocenje;
			}
//			if (sipaniUkusi.size() == maxBrojTocenja && y + jednoTocenje != 0) {
//				if (y < 0) g.fillRect(0, 0, sirinaPlatna, y + jednoTocenje);
//				else g.fillRect(0, 0, sirinaPlatna, y);
//			}
			if (sipaniUkusi.size() == maxBrojTocenja && maxBrojTocenja * jednoTocenje != visinaPlatna) {
				g.fillRect(0, 0, sirinaPlatna, visinaPlatna - maxBrojTocenja * jednoTocenje);
			}
			g.setColor(oldColor);
		}
//		super.paint(g);
	}
	
	private boolean work;
	private Thread threadTocenje;
	
	@Override
	public void run() {
//		System.out.println("RUN - START");
		try {
			while(!(threadTocenje.isInterrupted())) {
				synchronized (this) {
					while(!work){
						wait();
					}
					// <-- radi nesto
					azurirajCanvas();
					// -->
					wait(sleepTime);
				}
//				System.out.println("Wait zavrsen...");
			}
		} catch(InterruptedException e) {} // null/notify end
//		System.out.println("RUN - END");
	}
	
	public synchronized void pokreniTocenje(Ukus ukus) {
		if (threadTocenje == null) {
			threadTocenje = new Thread(this);
			threadTocenje.start();
			sipaniUkusi = new ArrayList<>();
			sladoled = new Sladoled(velicinaCase);
		}
		nastaviTocenje(ukus);
	}
	
	public synchronized void nastaviTocenje(Ukus ukus) {
//		System.out.println("NASTAVAK TOCENJA");
		// <-- synchronized: go()
		work = true;
		this.notify();
		// -->
		trenutniUkus = ukus;
	}
	
	public synchronized void pauzirajTocenje() {
		// <-- synchronized: pause()
		work = false;
		// -->
		trenutniUkus = null;
		this.notify();
	}
	
	public synchronized void prekiniTocenje() {
		if (threadTocenje != null) {
			threadTocenje.interrupt();
		}
		work = false;
	}
	
	public synchronized void resetujTocenje() {
		threadTocenje = null;
		// sladoled = new Sladoled(velicinaCase);
		sipaniUkusi.clear();
		sipaniUkusi = null;
		this.repaint();
	}
	
	private synchronized void azurirajCanvas() {
		if (sipaniUkusi.size() < Mesto.maxBrojTocenja) {
//			System.out.println("Sipan ukus: "  + trenutniUkus);
			sipaniUkusi.add(trenutniUkus);
			sladoled.dodajUkus(trenutniUkus, Aparat.getJednoTocenje());
			this.repaint();
			aparat.azurirajSladoled();
			if (sipaniUkusi.size() == Mesto.maxBrojTocenja) {
//				System.out.println("NAPUNJEN SLADOLED!!!");
				prekiniTocenje();
				aparat.omoguciProdaju();
			}
		}
	}
	
	public synchronized boolean tocenjeUToku() {
		if (threadTocenje != null)
			return threadTocenje.isAlive();
		return false;
	}
	
	public Thread getThreadTocenje() {
		return threadTocenje;
	}
	
}
