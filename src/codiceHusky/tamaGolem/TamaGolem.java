package codiceHusky.tamaGolem;

import java.util.ArrayList;

public class TamaGolem {
	private ArrayList<String> pietre;
	private int vitaRimanente;
	private int ciclo;
	
	public TamaGolem() {
		pietre = new ArrayList<String>();
		vitaRimanente = TamaMain.VITA_TAMAGOLEM;
		ciclo = 0;
	}
	
	/**
	 * Metodo che viene usato per capire quale pietra lanciare
	 */
	public void cicla() {
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
	
	public void riceviDanno(int danno) {
		vitaRimanente -= danno;
	}
	public int numPietre() {
		return pietre.size();
	}
	public ArrayList<String> getPietre(){
		return pietre;
	}
}
