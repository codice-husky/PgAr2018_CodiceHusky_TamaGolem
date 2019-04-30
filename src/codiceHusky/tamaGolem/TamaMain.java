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
		boolean partita = true;
        while(partita){
            //qui si esegue tutta la parte di creazione della fase 1 
        	//e di tutta la partita
        	partita = ricomincia();
        }
		
		
		sc.close();
	}
	/**
	 * Metodo che chiede all'utente quanti elementi vuole utilizzare durante
	 * la partita, il valore può essere o un numero compreso tra il numero 
	 * minimo e quello massimo oppure R per un numero random
	 * @return		true se ha dato una risposta accettabilie
	 * */
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
	
	/**
	 * Permette la scelta delle pietre di un golem
	 * @param golem TamGolem al quale assegnare le pietre
	 * @param numPietre Numero di pietre da assegnare
	 */
	public static void assegnaPietre(TamaGolem golem, int numPietre) {
		do {
			System.out.println("Pietre da assegnare: " + numPietre);
			System.out.println("Pietre disponibili:\n" + pietre.pietreToString());
			System.out.print("Scrivere il nome (o l'iniziale) della pietra da assengare: ");
			String input = sc.nextLine();
			for(String elemDaSacca : pietre) {
				if(elemDaSacca.equalsIgnoreCase(input) || Character.toString(elemDaSacca.charAt(0)).equalsIgnoreCase(input)) {
					golem.assegnaPietra(pietre.estraiPietraDef(elemDaSacca));
				}
			}
			numPietre--;
		} while(numPietre>0);
	}
	
	/**
	 * Metodo che chiede all'utente se vuole ricominciare una nuova partita
	 * finché non inserice o si o no
	 * @return 		risposta dell'utente
	 * */
	public static boolean ricomincia(){
        do {
            System.out.print("Vuoi continuare? (si\\no) ");
            String risp = sc.nextLine();
            if(risp.equalsIgnoreCase("si") || risp.equalsIgnoreCase("s�")) return true;
            else if(risp.equalsIgnoreCase("no")) return false;
            else System.out.println("Risposta inserita non corretta!");
        } while(true);
    }
}
