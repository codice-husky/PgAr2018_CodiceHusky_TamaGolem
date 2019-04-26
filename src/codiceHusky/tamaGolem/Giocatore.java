package codiceHusky.tamaGolem;

import java.util.ArrayList;

public class Giocatore {
	
	private String nome;
	private ArrayList<TamaGolem> squadra;
	private TamaGolem golemAttivo;
	
	

	public void aggiungiGolem(TamaGolem golem) {
		squadra.add(golem);
		
	}
	
	
	
	public TamaGolem getGolemAttivo() {
		return golemAttivo;
	}


	public void setGolemAttivo(TamaGolem golemAttivo) {
		this.golemAttivo = golemAttivo;
	}
	
	
	
	
}
