package codiceHusky.tamaGolem;

import java.util.ArrayList;

public class ElementiArrayList extends ArrayList<String> {
	private static final long serialVersionUID = 1L;
	
	public ElementiArrayList(String[] elementi) {
		super();
		for(String elemento : elementi) {
			this.add(elemento);
		}
	}

}
