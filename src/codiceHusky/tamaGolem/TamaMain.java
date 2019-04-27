package codiceHusky.tamaGolem;

import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;


public class TamaMain {
	private static final int ELEM_MAX = 10;
	private static final int ELEM_MIN = 1;
	public static final int VITA_TAMAGOLEM = 10;
	public static final String[] ELEMENTI_PIETRE = {"Acqua", "Aria", "Fuoco", "Terra", "Torta", "Fagiolo", "Budino", "Pianta", "Sale", "Pepe"};
	public static int elemUtilizzati;
	public static final SaccaPietre pietre = new SaccaPietre();
	
	public static void main(String[] args) {
	}
	
	public static boolean setup() {
		Scanner in = new Scanner(System.in);
		System.out.println("Quanti elementi vuoi utilizzare? (1-10, R)");
		String inputStr = in.nextLine();
		in.close();
		if(StringUtils.isNumeric(inputStr)) {
			int inputElem = Integer.parseInt(inputStr);
			if(inputElem >= ELEM_MIN && inputElem <= ELEM_MAX) {
				elemUtilizzati = inputElem;
				return true;
			} else return false;
		} else {
			if(inputStr.equalsIgnoreCase("R")) {
				elemUtilizzati = (int)Math.round((Math.random()*(10-ELEM_MIN))+ELEM_MIN);
				return true;
			} else return false;
		}
		
	}

}
