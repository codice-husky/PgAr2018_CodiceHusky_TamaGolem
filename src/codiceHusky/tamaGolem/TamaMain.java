package codiceHusky.tamaGolem;

import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;


public class TamaMain {
	private static final String DATO_NON_VALIDO = "Dato inserito non valido!";
	private static final String VUOI_CONTINUARE = "Vuoi continuare? (si\\no) ";
	private static final String RICHIESTA_PIETRE_DA_ASSEGNARE = "Scrivere il nome (o la lettera tra parentesi) della pietra da assengare: ";
	private static final String PIETRE_DISPONIBILI = "Pietre disponibili:\n";
	private static final String PIETRE_DA_ASSEGNARE = "Pietre da assegnare: ";
	private static final String EQUILIBRIO_STABILITO = "L'equilibrio è stato stabilito";
	private static final int ELEM_MAX = 10;
	private static final int ELEM_MIN = 3;
	private static final String RICHIESTA_ELEMENTI = "Quanti elementi vuoi utilizzare? (" + ELEM_MIN + "-" + ELEM_MAX + ", [R]andom) ";
	public static final int VITA_TAMAGOLEM = 10;
	public static final String[] ELEMENTI_PIETRE = {"Acqua", "Aria", "Fuoco", "Terra", "Torta", "Fagiolo", "Budino", "Pianta", "Sale", "Pepe"};
	public static final String[] ELEMENTI_ABBR = {"Q", "A", "F", "T", "O", "L", "B", "I", "S", "P"};
	public static int elemUtilizzati;
	private static final String ELEMENTI_SELEZIONATI_RANDOM = "Sono stati selezionati " + elemUtilizzati + " elementi";
	public static MatriceElementi matriceElementi;
	
	
	public static final SaccaPietre pietre = new SaccaPietre();
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println("---TAMAGOLEM CODICE HUSKY---");
		do {
			if(setup()) break;
			System.out.println(DATO_NON_VALIDO);
		} while(true);
		boolean partita = true;
        while(partita) {
        	//RICORDARSI CHE DOPO AVER AGGIUNTO I GOLEM AI GIOCATORE
        	//DI CHIAMARE IL METODO setGolemAttivo() nella classe giocatore
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
		System.out.print(RICHIESTA_ELEMENTI);
		String inputStr = sc.nextLine();
		if(StringUtils.isNumeric(inputStr)) {
			int inputElem = Integer.parseInt(inputStr);
			if(inputElem >= ELEM_MIN && inputElem <= ELEM_MAX) {
				elemUtilizzati = inputElem;
			} else return false;
		} else {
			if(inputStr.equalsIgnoreCase("R")) {
				elemUtilizzati = (int)Math.round((Math.random()*(10-ELEM_MIN))+ELEM_MIN);
				System.out.println(ELEMENTI_SELEZIONATI_RANDOM);
			} else return false;
		}
		matriceElementi = new MatriceElementi();
		System.out.println(EQUILIBRIO_STABILITO);
		return true;
	}
	
	/**
	 * Permette la scelta delle pietre di un golem
	 * @param golem TamGolem al quale assegnare le pietre
	 * @param numPietre Numero di pietre da assegnare
	 */
	public static void assegnaPietre(TamaGolem golem, int numPietre) {
		do {
			System.out.println(PIETRE_DA_ASSEGNARE + numPietre);
			System.out.println(PIETRE_DISPONIBILI + pietre.pietreToString());
			System.out.print(RICHIESTA_PIETRE_DA_ASSEGNARE);
			String input = sc.nextLine();
			
			if(input.length() == 1) {
				for(int i=0; i<ELEMENTI_ABBR.length; i++) {
					String abbr = ELEMENTI_ABBR[i];
					if(abbr.equalsIgnoreCase(Character.toString(input.charAt(0)))) {
						input = ELEMENTI_PIETRE[i];
						golem.assegnaPietra(pietre.estraiPietraDef(input));
						break;
					}
				}
			} else {
				for(String elemDaSacca : pietre) {
					if(elemDaSacca.equalsIgnoreCase(input)) {
						golem.assegnaPietra(pietre.estraiPietraDef(input));
						break;
					}
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
            System.out.print(VUOI_CONTINUARE);
            String risp = sc.nextLine();
            if(risp.equalsIgnoreCase("si") || risp.equalsIgnoreCase("sì")) return true;
            else if(risp.equalsIgnoreCase("no")) return false;
            else System.out.println(DATO_NON_VALIDO);
        } while(true);
    }
}
