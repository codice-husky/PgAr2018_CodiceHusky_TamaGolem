package codiceHusky.tamaGolem;

import java.util.ArrayList;

public class SaccaPietre extends ArrayList<String> {
	private static final long serialVersionUID = 6668925229983915099L;
	
	
	public SaccaPietre(int numPietre) {
		super();
		for(int i=0; i<numPietre; i++) this.add(pietraRandom());
	}
	/**
	 * Sceglie un elemento random tra quelli disponibili. Un elemento può essere estratto più volte
	 * @return Nome dell'elemento
	 */
	private String pietraRandom() {
		int elemScelto = (int)Math.round(Math.random() * TamaMain.ELEMENTI_PIETRE.length);
		return TamaMain.ELEMENTI_PIETRE[elemScelto];
	}
	
	
	
	/**
	 * Estrae una pietra dal sacco. Ciò implica che la pietra verrà rimossa dal sacco e non sarà possibile pescarla una seconda volta
	 * @return Nome dell'elemento estratto
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