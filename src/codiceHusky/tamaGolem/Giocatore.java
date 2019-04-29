package codiceHusky.tamaGolem;

import java.util.ArrayList;

public class Giocatore {
	
	private ArrayList<TamaGolem> squadra;
	private TamaGolem golemAttivo;
	
	public Giocatore() {
	}

	public void aggiungiGolem(TamaGolem golem) {
		squadra.add(golem);
		
	}
	
	public TamaGolem getGolemAttivo() {
		return golemAttivo;
	}


	public void setGolemAttivo(TamaGolem golemAttivo) {
		this.golemAttivo = golemAttivo;
	}


	public TamaGolem evocaGolem() {
		if(squadra.size()>0) {
			TamaGolem golem = null;
			int i = 0;
			do {
				try {
					golem = squadra.get(i);
				} catch (ArrayIndexOutOfBoundsException e) {
					return null;
				}
				i++;
			} while(golem.getVitaRimanente()<=0);
			return golem;
		}
		return null;
	}
	
	
	public void assegnaGolem() {
		int numGolem = (TamaMain.elemUtilizzati-1)*(TamaMain.elemUtilizzati-2)/(2*((TamaMain.elemUtilizzati+1)/3)+1);
		for(int i=0; i<numGolem; i++) {
			squadra.add(new TamaGolem());
		}
	}
	
}
