package codiceHusky.tamaGolem;

import java.util.ArrayList;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;

public class TamaMain {
	private static final String DATO_NON_VALIDO = "Dato inserito non valido!";
	private static final String VUOI_CONTINUARE = "Vuoi continuare? (si\\no) ";
	private static final String RICHIESTA_PIETRE_DA_ASSEGNARE = "Scrivere il nome (o la lettera tra parentesi) della pietra da assengare: ";
	private static final String PIETRE_DISPONIBILI = "Pietre disponibili:\n";
	private static final String PIETRE_DA_ASSEGNARE = "\nPietre da assegnare: ";
	private static final String EQUILIBRIO_STABILITO = "L'equilibrio è stato stabilito";
	private static final int ELEM_MAX = 10;
	private static final int ELEM_MIN = 3;
	private static final String RICHIESTA_ELEMENTI = "Quanti elementi volete utilizzare? (" + ELEM_MIN + "-" + ELEM_MAX + ", [R]andom) ";
	public static final int VITA_TAMAGOLEM = 10;
	public static final String[] ELEMENTI_PIETRE = {"Acqua", "Vento", "Fuoco", "Terra", "Elettro", "Luce", "Buio", "Pianta", "Drago", "Metallo"};
	public static final String[] ELEMENTI_ABBR = {"A", "V", "F", "T", "E", "L", "B", "P", "D", "M"};
	public static int elemUtilizzati;
	public static MatriceElementi matriceElementi;
	public static Scontro scontro;
	
	
	public static SaccaPietre pietre;
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println(TITOLO);
		boolean continua = true;
		while(continua) {
			System.out.print("Giocatore 1 come ti chiami? ");
			Giocatore giocatore1 = new Giocatore(sc.nextLine());
			System.out.print("E tu Giocatore 2 come ti chiami? ");
			Giocatore giocatore2 = new Giocatore(sc.nextLine());
			if(giocatore1.getNome().equals(giocatore2.getNome())) giocatore2.nomeGiaEsistente();
			do {
				if(richiestaElementi()) break;
				System.out.println(DATO_NON_VALIDO);
			} while(true);
			pietre = new SaccaPietre();
			matriceElementi = new MatriceElementi();
			System.out.println(EQUILIBRIO_STABILITO);
			scontro = new Scontro(giocatore1,giocatore2,matriceElementi);
			scontro.getG1().assegnaGolem();
			scontro.getG1().setGolemAttivo();
			scontro.getG2().assegnaGolem();
			scontro.getG2().setGolemAttivo();
			
			barra();
			System.out.println("Ogni giocatore avrà a disposizione "+ scontro.getG1().numGolem()+" golem");
			barra();
			int x = -1;
			while(x!= 1 && x!=2) {//se x == 1 allora ha vinto il giocatore 1 altrimenti il 2
				x = -1;
				while(x== -1) {
					x = 0;
					while(true) {
						if(scontro.getG1().getGolemAttivo().numPietre()==0) {
							System.out.println("\n"+ giocatore1.getNome() +" assegna le pietre al tuo golem ");
							assegnaPietre(scontro.getG1().getGolemAttivo(), (int) (Math.ceil((elemUtilizzati + 1)/3) + 1));
						}
						boolean uguali = true;
						if(scontro.getG2().getGolemAttivo().numPietre()==0){
							System.out.println("\n"+ giocatore2.getNome() + " assegna le pietre al tuo golem ");
							assegnaPietre(scontro.getG2().getGolemAttivo(), (int) (Math.ceil((elemUtilizzati + 1)/3) + 1));
						}
						
						int nPietre =(int) (Math.ceil((elemUtilizzati + 1)/3) + 1);
            	   		for(int p = 0;p<nPietre;p++) {
            	   			if(scontro.getG1().getGolemAttivo().getPietre().get(p) != 
            	   			   scontro.getG2().getGolemAttivo().getPietre().get(p)) {
            	   				uguali = false;
            	   				break;
            	   			}
	               		}
            	   		if(uguali) {
            	   			System.out.println("I 2 giocatori hanno scelto le stesse pietre.\n"
            	   					+ "Bisogna riscieglierle");
            	   			ArrayList<String>pietreDaRimettere = new ArrayList<String>();
            	   			int memo = scontro.getG1().getGolemAttivo().numPietre();
            	   			for(int w = 0;w<memo;w++) {
            	   				pietreDaRimettere.add(scontro.getG1().getGolemAttivo().getPietre().get(0));
            	   				pietreDaRimettere.add(scontro.getG2().getGolemAttivo().getPietre().get(0));
            	   				scontro.getG1().getGolemAttivo().getPietre().remove(0);
            	   				scontro.getG2().getGolemAttivo().getPietre().remove(0);
            	   			}
            	   			String[] def = new String[pietreDaRimettere.size()];
            	   			def = pietreDaRimettere.toArray(def);
            	   			pietre.reinserisci(def);
            	   		}else {
            	   			break;
            	   		}
        	   		}
					x = scontro.letThemFight();
					if(x == 1) {
						System.out.println("Ha vinto "+ giocatore1.getNome());
						break;
					}else if(x == 2) {
						System.out.println("Ha vinto "+ giocatore2.getNome());
					}else {
						x = -1;
					}
				}
			}
			System.out.println("\nEcco qual'era l'equilibrio del sistema\n");
			visualizzaEquilibrio();
			continua = ricomincia();
		}
		sc.close();
		System.out.println("GRAZIE PER AVER GIOCATO");
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
				System.out.println("Sono stati selezionati " + elemUtilizzati + " elementi");
			} else return false;
		}
		return true;
	}
	
	
	/**
	 * Permette la scelta delle pietre di un golem
	 * @param golem TamGolem al quale assegnare le pietre
	 * @param numPietre Numero di pietre da assegnare
	 */
	public static void assegnaPietre(TamaGolem golem, int numPietre) {
		do {
			boolean assegnata = false;
			System.out.println(PIETRE_DA_ASSEGNARE + numPietre);
			System.out.print(PIETRE_DISPONIBILI + pietre.pietreToString());
			System.out.print(RICHIESTA_PIETRE_DA_ASSEGNARE);
			String input = sc.nextLine();
			
			if(input.length() == 1) {
				for(int i=0; i<elemUtilizzati; i++) {
					String abbr = ELEMENTI_ABBR[i];
					if(abbr.equalsIgnoreCase(Character.toString(input.charAt(0)))) {
						input = ELEMENTI_PIETRE[i];
						golem.assegnaPietra(pietre.estraiPietraDef(input));
						assegnata = true;
						break;
					}
				}
				
			} else {
				for(String elemDaSacca : pietre) {
					if(elemDaSacca.equalsIgnoreCase(input)) {
						golem.assegnaPietra(pietre.estraiPietraDef(input));
						assegnata = true;
						break;
					}
				}
			}
			if(!assegnata) {
				System.out.println(DATO_NON_VALIDO + "\n");
			} else numPietre--;
			
			
		} while(numPietre>0);
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		barra();
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
            barra();
            if(risp.equalsIgnoreCase("si") || risp.equalsIgnoreCase("sì")) return true;
            else if(risp.equalsIgnoreCase("no")) return false;
            else System.out.println(DATO_NON_VALIDO);
        } while(true);
	}
	
	public static void visualizzaEquilibrio() {

        int maxLength =3;
        for(int i = 0;i < elemUtilizzati; i++){
            if(ELEMENTI_PIETRE[i].length() > maxLength){
                maxLength = ELEMENTI_PIETRE[i].length();
            }
        }
        for(int i=-1;i<elemUtilizzati;i++){
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
        for(int i=0;i<elemUtilizzati;i++){
           for(int j=-1;j<elemUtilizzati;j++){
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
                    String memo = "" + matriceElementi.getMatrice()[i][j];
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
	
	public static void barra() {
		System.out.println("--------------------------------------------------------------------------");
	}
	
	private static final String TITOLO = "\n" + 
			"████████╗ █████╗ ███╗   ███╗ █████╗  ██████╗  ██████╗ ██╗     ███████╗███╗   ███╗\n" + 
			"╚══██╔══╝██╔══██╗████╗ ████║██╔══██╗██╔════╝ ██╔═══██╗██║     ██╔════╝████╗ ████║\n" + 
			"   ██║   ███████║██╔████╔██║███████║██║  ███╗██║   ██║██║     █████╗  ██╔████╔██║\n" + 
			"   ██║   ██╔══██║██║╚██╔╝██║██╔══██║██║   ██║██║   ██║██║     ██╔══╝  ██║╚██╔╝██║\n" + 
			"   ██║   ██║  ██║██║ ╚═╝ ██║██║  ██║╚██████╔╝╚██████╔╝███████╗███████╗██║ ╚═╝ ██║\n" + 
			"   ╚═╝   ╚═╝  ╚═╝╚═╝     ╚═╝╚═╝  ╚═╝ ╚═════╝  ╚═════╝ ╚══════╝╚══════╝╚═╝     ╚═╝\n" + 
			"   ___  _   _    ____ ____ ___  _ ____ ____    _  _ _  _ ____ _  _ _   _ \n" + 
			"   |__]  \\_/     |    |  | |  \\ | |    |___    |__| |  | [__  |_/   \\_/  \n" + 
			"   |__]   |      |___ |__| |__/ | |___ |___    |  | |__| ___] | \\_   |   \n" + 
			"__________________________________________________________________________________ \n" + 
			"                                                                      \n";
}
