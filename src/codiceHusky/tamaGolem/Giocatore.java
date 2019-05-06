package codiceHusky.tamaGolem;

import java.util.ArrayList;

public class Giocatore {
	
	private ArrayList<TamaGolem> squadra;
	private TamaGolem golemAttivo;
	
	public Giocatore() {
		squadra = new ArrayList<TamaGolem>();
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
		int numGolem = (int) Math.ceil((TamaMain.elemUtilizzati-1)*(TamaMain.elemUtilizzati-2)/(2*(Math.ceil((TamaMain.elemUtilizzati+1)/3)+1)));
		for(int i=0; i<numGolem; i++) {
			squadra.add(new TamaGolem());
		}
	}
	
	
    //da mettere nel giocatore
    public void eliminaGolem(){
    	squadra.remove(golemAttivo);
    }
	
    public int numGolem(){
    	return squadra.size();
    }
}
