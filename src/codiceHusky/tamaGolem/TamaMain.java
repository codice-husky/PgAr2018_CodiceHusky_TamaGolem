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
	public static Scontro scontro;
	
	
	public static final SaccaPietre pietre = new SaccaPietre();
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println("---TAMAGOLEM CODICE HUSKY---");
		setup();
		boolean partita = true;
        while(partita) {
        	do {
    			if(richiestaElementi()) break;
    			System.out.println(DATO_NON_VALIDO);
    		} while(true);
        	scontro = new Scontro(new Giocatore(), new Giocatore(), matriceElementi);
        	scontro.getG1().assegnaGolem();
        	scontro.getG1().setGolemAttivo();
        	scontro.getG2().assegnaGolem();
        	
        	int x = -1;
            while (x!= 1 && x!= 2) { //1 se vince la partita il giocatore 1, 2 il giocatore 2
               x=-1;
               while (x == -1){
            	   x = 0;
	               scontro.getG2().setGolemAttivo();
	               if(scontro.getG1().getGolemAttivo().numPietre()==0) {
	            	   System.out.println("Giocatore 1: assegna le pietre al tuo golem");
	            	   assegnaPietre(scontro.getG1().getGolemAttivo(), (int) (Math.ceil((elemUtilizzati + 1)/3) + 1));
	               }
	               if(scontro.getG2().getGolemAttivo().numPietre()==0){
	            	   System.out.println("Giocatore 2: assegna le pietre al tuo golem");
	            	   assegnaPietre(scontro.getG2().getGolemAttivo(), (int) (Math.ceil((elemUtilizzati + 1)/3) + 1));
	               }
               }
               
               x = scontro.letThemFight();
            }
            
            if(x==1) System.out.println("Il giocatore 1 ha vinto!");
            else if(x==2) System.out.println("Il giocatore 2 ha vinto!");
        	//RICORDARSI CHE DOPO AVER AGGIUNTO I GOLEM AI GIOCATORE
        	//DI CHIAMARE IL METODO setGolemAttivo() nella classe giocatore
            //qui si esegue tutta la parte di creazione della fase 1 
        	//e di tutta la partita
        	partita = ricomincia();
        }
		System.out.println("\n L'equilibrio era: \n");
		visualizzaEquilibrio();
		
		sc.close();
	}
	
	/**
	 * Chiede all'utente con quanti elementi vuole giocare
	 * @return true se il dato inserito è accettabile, false altrimenti
	 */
	public static boolean richiestaElementi() {
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
		return true;
	}
	
	/**
	 * Inizializza la matrice delle forze
	 * */
	public static void setup() {
		matriceElementi = new MatriceElementi();
		System.out.println(EQUILIBRIO_STABILITO);
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
	
	public static void visualizzaEquilibrio() {
        int maxLength =3;
        for(int i = 0;i < ELEMENTI_PIETRE.length; i++){
            if(ELEMENTI_PIETRE[i].length() > maxLength){
                maxLength = ELEMENTI_PIETRE[i].length();
            }
        }
        for(int i=-1;i<ELEMENTI_PIETRE.length;i++){
            if(i == -1){
                System.out.print("|");
                for(int j=0;j<maxLength;j++){
                    System.out.print(" ");
                }
                System.out.print("|");
            }else{
                int spazi = maxLength-ELEMENTI_PIETRE[i].length();
                int dopo = spazi / 2,prima = spazi - dopo;
                for(int j=0;j<prima;j++){
                    System.out.print(" ");
                }
                System.out.print(ELEMENTI_PIETRE[i]);
                for(int j=0;j<dopo;j++){
                    System.out.print(" ");
                }
                System.out.print("|");
            }
        }
        System.out.println("");
        for(int i=0;i<ELEMENTI_PIETRE.length;i++){
           for(int j=-1;j<ELEMENTI_PIETRE.length;j++){
                if(j== -1){
                    System.out.print("|");
                    int spazi = maxLength - ELEMENTI_PIETRE[i].length();
                    int dopo = spazi/2,prima = spazi-dopo;
                    for(int k=0;k < prima;k++){
                       System.out.print(" ");
                    }
                    System.out.print(ELEMENTI_PIETRE[i]);
                    for(int k=0;k<dopo;k++){
                        System.out.print(" ");
                    }
                    System.out.print("|");
                }else{
                    String memo = ""+matriceElementi.getMatrice()[i][j];
                    int spazi = maxLength - memo.length();
                    int dopo = spazi / 2, prima = spazi - dopo;
                    for(int k = 0;k < prima; k++){
                        System.out.print(" ");
                    }
                    System.out.print(memo);
                    for(int k = 0; k < dopo; k++){
                        System.out.print(" ");
                    }
                    System.out.print("|");
                }
           } 
            System.out.println("");
        }
        System.out.println("\n\n");
	}
}
