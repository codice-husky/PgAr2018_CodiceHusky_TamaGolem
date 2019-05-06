package codiceHusky.tamaGolem;

import java.util.ArrayList;

/**
 * Questa classe crea la matrice di collegamenti tra i vari elementi.
 * In pratica il funzionamento prevede che ciascun collegamento da e fa
 * danno, con valori opposti, ovvero se fuoco fa danno 5 ad aria, aria
 * fa danno -5 a fuoco, quindi lo riceve.
 * Nella matrice ad ogni passaggio vengono riempite la riga e la colonna
 * con lo stesso indice (al primo giro tutta la colonna con indice 0,e la
 * riga con inidice 0, poi la riga/colonna 1, ecc.),
 * 
 * 0 1 1 1 1
 * 1 0 2 2 2
 * 1 2 0 3 3 
 * 1 2 3 0 4
 * 1 2 3 4 0
 * 
 * La diagonale e' fissa a 0(fuoco fa danno 0 a fuoco), poi gli altri numeri
 * rappresentano in quale ciclo è stato creato l'elemento (1 il primo ciclo,
 * 2 il secondo ecc.)
 * */
public class MatriceElementi {
	Integer matrice[][];
	/**
	 * Costruttore della classe MatriceElementi
	 * */
	public MatriceElementi(){
		int vita = TamaMain.VITA_TAMAGOLEM;
		int elementi = TamaMain.ELEMENTI_PIETRE.length;
		matrice = creaMatrice(vita,elementi);
		/*in pratica tutta la matrice è generata con numeri randomici tranne
			nell'esempio di prima le celle 4, che sono date dalla somma degli
			elementi dell'ultima colonna (negata per bilanciare) e siccome quel
			numero deve essere minore della vita del golem cilo con questo while 
		  */
		while(matrice[elementi-2][elementi-1]< -vita ||matrice[elementi-2][elementi-1]>vita) {
			matrice = creaMatrice(vita,elementi);
		}
	}
	/**
	 * Getter della matrice
	 * @return 	la matrice di collegamenti tra i vari elementi
	 * */
	public Integer[][] getMatrice() {
		return matrice;
	}
	
	/**
	 * Metodo che calcola la matrice di collegamenti
	 * @param V		la vita del golem
	 * @param GRAND la grandezza dela matrice, ovvero il numero di elementi
	 * @return		la matrice generata
	 * */
	
	public static Integer[][] creaMatrice(int V,int GRAND){
        Integer[][]matr = new Integer[GRAND][GRAND];
        for(int i=0;i<GRAND;i++){/*setto la diagonale a 0 (fuoco fa danno
            0 a fuoco)*/
            for(int j = 0;j<GRAND;j++){
                if(i==j){
                    matr[i][j] = 0;
                }
            }
        }
 
        for(int i=0;i<GRAND-2;i++){
            int sommaRiga = 0, sommaColonna = 0;
            /*conto quanti danni applicano gli 
            elementi prima di quello attuale, in modo da evitare uno 0
            nell'ultima cella della riga
            */
            for(int j=0;j<GRAND;j++){ 
                if(matr[i][j]!=null){
                    sommaRiga = sommaRiga + matr[i][j];
                }
                else break;
            }
            /*stessa cosa con i danni in quella colonna prima dell'elemento*/
            for(int j=i+1;j<GRAND-1;j++){
                for(int s = 0;s<GRAND;s++){
                    if(matr[s][j]!=null) sommaColonna+=matr[s][j];
                    else break;
                }
                boolean errore = false;
                /*finché non è un valore accettabile, cicla; deve essere
                 * compreso tra 1 e la vita del golem*/
                while(!errore){
                    errore = true;
                    int valore = (int)((Math.random()*V)+1);
                    int isPos = (int)Math.round(Math.random());
                    /*serve per capire se da/riceve danno
                    e associa ad esempio ad [1][2] danno 5 e a
                    [2][1] -5 poiché sono opposti
                    */
                    if(isPos == 0) valore = -valore;
                    if(valore!=(-sommaRiga) &&
                    		valore!= (-sommaColonna)&&
                    		(sommaRiga+valore <=10)&&
                    		(sommaRiga+valore >=(-10))){
                        matr[i][j] = valore;
                        matr[j][i]=-valore;
                        sommaRiga+=valore;
                    }else{
                        errore = false;
                    }
                    
                }
            }/*aggiunge l'ultimo elemento della riga, che è l'opposto
               della somma dei precedenti e l'ultimo elemento della colonna*/
            matr[i][GRAND-1] = -sommaRiga;
            matr[GRAND-1][i] = sommaRiga;
        }
        //aggiunge gli elementi rimanenti (basandosi sul disegno all'inizio,
        //i valori 4 e 4) sempre in base ai valori precedenti
        int last = 0;
        for(int j=0;j<GRAND-2;j++){
            last+=matr[GRAND-2][j];
        }
        matr[GRAND-2][GRAND-1] = -last;
        matr[GRAND-1][GRAND-2] = last;
        if(matr[GRAND-2][GRAND-1]<-V || matr[GRAND-2][GRAND-1]>V){
            ArrayList<Integer> elementi = new ArrayList<Integer>();
            for(int k = 0;k<(GRAND-2);k++){
                elementi.add(matr[GRAND-2][k]);
            }
            
            if(matr[GRAND-2][GRAND-1]<-V){
                ArrayList<Integer> memo = new ArrayList<>(elementi);
                int differenzaTot = matr[GRAND-2][GRAND-1]-(-V);
                int differenza = 1;
                while(differenzaTot < 0){
                    int colonnaRandom = (int)(Math.random()*memo.size());
                    if( controllo(matr[GRAND-2][colonnaRandom]-differenza, V) &&
                        controllo(matr[GRAND-1][colonnaRandom]+differenza,V)){

                        matr[GRAND-2][colonnaRandom] -= differenza;
                        matr[colonnaRandom][GRAND-2] += differenza;

                        matr[GRAND-1][colonnaRandom] += differenza;
                        matr[colonnaRandom][GRAND-1] -= differenza;

                        matr[GRAND-2][GRAND-1] += differenza;
                        matr[GRAND-1][GRAND-2] -= differenza;

                        differenzaTot += differenza; 

                        memo = new ArrayList<>(elementi);
                        differenza = 1;
                    }else{
                        memo.remove(colonnaRandom);
                        if(memo.isEmpty()){
                            differenza++;
                            memo = new ArrayList<>(elementi);
                        }
                    }

                }
            }else{
                ArrayList<Integer> memo = new ArrayList<>(elementi);
                int differenzaTot = matr[GRAND-2][GRAND-1]-V;
                int differenza = 1;
                while(differenzaTot > 0){
                    int colonnaRandom = (int)(Math.random()*memo.size());
                    if( controllo(matr[GRAND-2][colonnaRandom]+differenza, V) &&
                        controllo(matr[GRAND-1][colonnaRandom]-differenza,V)){

                        matr[GRAND-2][colonnaRandom] += differenza;
                        matr[colonnaRandom][GRAND-2] -= differenza;

                        matr[GRAND-1][colonnaRandom] -= differenza;
                        matr[colonnaRandom][GRAND-1] += differenza;

                        matr[GRAND-2][GRAND-1] -= differenza;
                        matr[GRAND-1][GRAND-2] += differenza;

                        differenzaTot -= differenza; 

                        memo = new ArrayList<>(elementi);
                        differenza = 1;
                    }else{
                        memo.remove(colonnaRandom);
                        if(memo.isEmpty()){
                            differenza++;
                            memo = new ArrayList<>(elementi);
                        }
                    }                
                }
            }
        }
        return matr;
    }
	/**
	 * Metodo che controlla la validità di un valore ovvero, se
	 * è diverso da 0, e compreso tra -vita del golem e +vita
	 * del golem
	 * @param x  	il valore da controllare
	 * @param v		la vita del tamagolem
	 * @return		true se è un valore accettabile, altrimenti false
	 * */
    public static boolean controllo(Integer x,int v){
        if(x>=(-v) && x<=v && x!=0) return true;
        return false;
    }
    

	
}
