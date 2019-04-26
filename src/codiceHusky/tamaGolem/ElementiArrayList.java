package codiceHusky.tamaGolem;

import java.util.ArrayList;

public class ElementiArrayList extends ArrayList<String> {
	private static final long serialVersionUID = 4193461440714862171L;
	private Integer[][] mappaPotenza;
	
	public ElementiArrayList(String[] elementi) {
		super();
		for(String elemento : elementi) {
			this.add(elemento);
		}
		mappaPotenza = new Integer[TamaMain.NUMERO_ELEMENTI][TamaMain.NUMERO_ELEMENTI];
		for(int i=0;i<TamaMain.NUMERO_ELEMENTI;i++) {
			mappaPotenza[i][i] = 0;
			
		}
	}
	
	
	public void associaPotenza(String elementoSorgente, String elementoRicevente, int potenza) {
		int indexSorgente = this.indexOf(elementoSorgente);
		int indexRicevente = this.indexOf(elementoRicevente);
		
		mappaPotenza[indexSorgente][indexRicevente] = potenza;
		mappaPotenza[indexRicevente][indexSorgente] = 0;
	}
		
	public int getPotenza(String elementoSorgente, String elementoRicevente) {
		int indexSorgente = this.indexOf(elementoSorgente);
		int indexRicevente = this.indexOf(elementoRicevente);
		
		return mappaPotenza[indexSorgente][indexRicevente];
	}

}
