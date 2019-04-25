package codiceHusky.tamaGolem;
public class TamaGolem {
	private String[] pietre;
	int ciclo;
	
	public TamaGolem(int P) {
		pietre = new String[P];
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
}
