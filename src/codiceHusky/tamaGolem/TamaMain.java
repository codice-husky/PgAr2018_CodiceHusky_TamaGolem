package codiceHusky.tamaGolem;

import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;


public class TamaMain {
	private static final int ELEM_MAX = 10;
	private static final int ELEM_MIN = 3;
	public static final int VITA_TAMAGOLEM = 10;
	public static final String[] ELEMENTI_PIETRE = {"Acqua", "Aria", "Fuoco", "Terra", "Torta", "Fagiolo", "Budino", "Pianta", "Sale", "Pepe"};
	public static int elemUtilizzati;
	
	
	public static final SaccaPietre pietre = new SaccaPietre();
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println("---TAMAGOLEM CODICE HUSKY---");
		do {
			if(setup()) break;
			System.out.println("Dato inserito non valido!");
		} while(true);
		
		
		
		sc.close();
	}
	
	public static boolean setup() {
		System.out.print("Quanti elementi vuoi utilizzare? (" + ELEM_MIN + "-" + ELEM_MAX + ", [R]andom) ");
		String inputStr = sc.nextLine();
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
