package codiceHusky.tamaGolem;

public class Scontro {
	private Giocatore g1;
	private Giocatore g2;
	private MatriceElementi m;
	
	
	public Scontro(Giocatore g1, Giocatore g2, MatriceElementi m) {
		this.g1 = g1;
		this.g2 = g2;
		this.m = m;
	}
	
	
	//RESTITUISCE: 
		//0 nel caso in cui ci sia stato uno scontro normalissimo con un
		//  golem morto e l'altro vivo, potendo ancora giocare
		//1 se nello scontro ha vinto LA PARTITA il player 1
		//2 se nello scontro ha vinto LA PARTITA il player 2
		public int letThemFight() {
			TamaGolem t1 = g1.getGolemAttivo();
			TamaGolem t2 = g2.getGolemAttivo();
			
			while(t1.getVitaRimanente()>0 || t2.getVitaRimanente()>0) {
				
				
				int p1 = numPietra(t1.getPietraAttiva());
				int p2 = numPietra(t2.getPietraAttiva());
				
				System.out.println("\n"+g1.getNome()+" lancia una pietra "+t1.getPietraAttiva() +", "+ g2.getNome()+" lancia una pietra "+t2.getPietraAttiva());
				
				
					
				if(m.matrice[p1][p2]>0) {	//vince la pietra p1
					t2.riceviDanno(m.matrice[p1][p2]);
					System.out.println("Vince "+g1.getNome() +". Il golem di "+g2.getNome()+" subisce un danno di "+
					m.matrice[p1][p2]);
					if(t2.getVitaRimanente() <=0) {
						g2.eliminaGolem();
						System.out.println("Viene eliminato il golem di "+ g2.getNome());
						if(g2.numGolem()>0) {
							g2.setGolemAttivo(); 
							System.out.println(g2.getNome()+" ti rimangono "+ g2.numGolem() +" golem in squadra");
						}else return 1; //vince LA PARTITA il player 1
						return 0;
					}else System.out.println("Al golem di "+ g2.getNome()+" rimangono "+ t2.getVitaRimanente()+" PS");
				}else if(m.matrice[p1][p2]==0) {
					System.out.println("Le pietre dei 2 golem sono uguali");
				}else {						//vince la pietra p2
					t1.riceviDanno(m.matrice[p2][p1]);
					System.out.println("Vince "+g2.getNome() +". Il golem di "+g1.getNome()+" subisce un danno di "+
							m.matrice[p2][p1]);
					if(t1.getVitaRimanente() <=0) {
						g1.eliminaGolem();
						System.out.println("Viene eliminato il golem di "+ g1.getNome());
						if(g1.numGolem()>0) {
							g1.setGolemAttivo(); 
							System.out.println(g1.getNome()+" ti rimangono "+ g1.numGolem() +" golem in squadra");
						}else return 2; //vince LA PARTITA il player 2
						return 0;
					}else System.out.println("Al golem di "+ g1.getNome()+" rimangono "+ t1.getVitaRimanente()+" PS");
				}
				t1.cicla();
				t2.cicla();			
			}	
			return 0;
		}

	
	// metodo che cerca la pietra nel vettore ELEMENTI_PIETRE e ne restituisce la posizione
	
	private int numPietra(String pietra) {
		for (int i=0 ; i<TamaMain.ELEMENTI_PIETRE.length ; i++) {
			if (TamaMain.ELEMENTI_PIETRE[i].equals(pietra)) {
				return i;
			}
		}
		return -1;
	}


	public Giocatore getG1() {
		return g1;
	}


	public Giocatore getG2() {
		return g2;
	}
	
}