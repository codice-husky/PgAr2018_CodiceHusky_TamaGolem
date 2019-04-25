package codiceHusky.tamaGolem;

import java.util.ArrayList;

public class ElementiArrayList extends ArrayList<String> {
	private static final long serialVersionUID = 1L;
	private Integer[][] mappaPotenza;
	
	public ElementiArrayList(String[] elementi) {
		super();
		for(String elemento : elementi) {
			this.add(elemento);
		}
		mappaPotenza = new Integer[TamaMain.NUMERO_ELEMENTI][TamaMain.NUMERO_ELEMENTI];
	}

}
