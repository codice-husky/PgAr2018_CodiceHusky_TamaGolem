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

	public void setGolemAttivo() {
		this.golemAttivo = squadra.get(0);
	}
	
	public void assegnaGolem() {
		int numGolem = (TamaMain.elemUtilizzati-1)*(TamaMain.elemUtilizzati-2)/(2*((TamaMain.elemUtilizzati+1)/3)+1);
		for(int i=0; i<numGolem; i++) {
			squadra.add(new TamaGolem());
		}
	}
	
	
    //da mettere nel giocatore
    public void eliminaGolem(){
    	golemAttivo = null;
    	squadra.set(0, null);
    }
	
    public int numGolem(){
    	return squadra.size();
    }
}
