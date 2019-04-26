package codiceHusky.tamaGolem;
public class TamaGolem {
	private String[] pietre;
	private int vitaRimanente;
	private int ciclo;
	
	public TamaGolem(int P) {
		pietre = new String[P];
		vitaRimanente = TamaMain.VITA_TAMAGOLEM;
		ciclo = 0;
	}
	
	/**
	 * Metodo che viene usato per capire quale pietra lanciare
	 */
	private void cicla() {
		ciclo++;
		if(ciclo == pietre.length) {
			ciclo = 0;
		}
	}
	
	private void assegnaPietre() {
		
	}
	
	private String getPietraAttiva() {
		return pietre[ciclo];
	}
}
