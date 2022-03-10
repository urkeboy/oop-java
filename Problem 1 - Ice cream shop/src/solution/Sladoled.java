package solution;

import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class Sladoled {

	private LinkedHashMap<Ukus, Integer> ukusi;
	private int velicinaCase;
	
	public Sladoled(int velicinaCase) {
		this.ukusi = new LinkedHashMap<>();
		this.velicinaCase = velicinaCase;
	}
	
	public int getSlobodnaZapremina() {
		int popunjenost = 0;
		for (int i : ukusi.values()) {
			popunjenost += i;
		}
		return velicinaCase - popunjenost;
	}
	
	public void dodajUkus(Ukus ukus, int kolicina) {
		kolicina = Math.min(kolicina, getSlobodnaZapremina());
		Iterator<Entry<Ukus, Integer>> it = ukusi.entrySet().iterator();
	    while (it.hasNext()) {
	    	HashMap.Entry<Ukus, Integer> kvPair = it.next();
	    	if (ukus.equals(kvPair.getKey())) {
	    		kvPair.setValue(kvPair.getValue() + kolicina);
	    		return;
	    	}
	    }
	    ukusi.put(ukus, kolicina);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Iterator<Entry<Ukus, Integer>> it = ukusi.entrySet().iterator();
	    while (it.hasNext()) {
	    	HashMap.Entry<Ukus, Integer> kvPair = it.next();
	    	sb.append(kvPair.getValue()).append("ml");
	    	sb.append("[").append(kvPair.getKey().getNaziv()).append("]");
	    	if (it.hasNext()) sb.append(" ");
	    }
		return sb.toString();
	}
	
}