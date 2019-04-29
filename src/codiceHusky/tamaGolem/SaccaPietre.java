package codiceHusky.tamaGolem;

import java.util.ArrayList;

public class SaccaPietre extends ArrayList<String> {
	private static final long serialVersionUID = 6668925229983915099L;
	
	
	public SaccaPietre() {
		super();
		int pietrePerElemento = calcPietrePerElemento();
		for(int i=0; i<TamaMain.elemUtilizzati; i++) {
			for(int j=0; j<pietrePerElemento; j++) {
				this.add(TamaMain.ELEMENTI_PIETRE[i]);
			}
		}
	}
	
	
	/**
	 * Calcola il numero di pietre previste per ogni elemento
	 * @return Numero pietre
	 */
	private int calcPietrePerElemento() {
		int nPietre = ((TamaMain.elemUtilizzati + 1) / 3) + 1;
		int nGolem = ((TamaMain.elemUtilizzati - 1) * (TamaMain.elemUtilizzati - 2) / (nPietre * 2));
		return ((2 * nGolem * nPietre) / TamaMain.elemUtilizzati);
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