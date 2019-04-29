package codiceHusky.tamaGolem;

import java.util.ArrayList;

public class TamaGolem {
	private ArrayList<String> pietre;
	private int vitaRimanente;
	private int ciclo;
	
	public TamaGolem() {

		vitaRimanente = TamaMain.VITA_TAMAGOLEM;
		ciclo = 0;
	}
	
	/**
	 * Metodo che viene usato per capire quale pietra lanciare
	 */
	private void cicla() {
		ciclo++;
		if(ciclo == pietre.size()) {
			ciclo = 0;
		}
	}
	
	public void assegnaPietra(String pietra) {
		pietre.add(pietra);
	}
	
	
	public String getPietraAttiva() {
		return pietre.get(ciclo);
	}

	public int getVitaRimanente() {
		return vitaRimanente;
	}
}
