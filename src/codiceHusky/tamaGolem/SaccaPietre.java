package codiceHusky.tamaGolem;

import java.util.ArrayList;

public class SaccaPietre extends ArrayList<String> {
	private static final long serialVersionUID = 6668925229983915099L;
	
	
	public SaccaPietre(int numPietre) {
		super();
		int pietrePerElemento = calcPietrePerElemento();
		for(String elemento : TamaMain.ELEMENTI_PIETRE) {
			for(int i=0; i<pietrePerElemento; i++) {
				this.add(elemento);
			}
		}
	}
	
	
	/**
	 * Calcola il numero di pietre previste per ogni elemento
	 * @return Numero pietre
	 */
	private int calcPietrePerElemento() {
		int nPietre = ((TamaMain.ELEMENTI_PIETRE.length + 1) / 3) + 1;
		int nGolem = ((TamaMain.ELEMENTI_PIETRE.length - 1) * (TamaMain.ELEMENTI_PIETRE.length - 2) / (nPietre * 2));
		return ((2 * nGolem * nPietre) / TamaMain.ELEMENTI_PIETRE.length);
	}
	
	
	/**
	 * Estrae una pietra dal sacco. Ciò implica che la pietra verrà rimossa dal sacco e non sarà possibile pescarla una seconda volta
	 * @return Nome dell'elemento estratto, null se il sacco è vuoto
	 */
	public String estraiPietra() {
		if(this.size()>0) {
			int numElemScelto = (int)Math.round(Math.random() * this.size());
			String elemScelto = this.get(numElemScelto);
			this.remove(numElemScelto);
			return elemScelto;
		}
		else return null;
	}
	
}