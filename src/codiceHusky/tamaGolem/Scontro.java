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
	//1 se nello scontro ha vinto il golem del player 1
	//2 se nello scontro ha vinto il golem del player 2
	//3 se c'è stato un eventuale pareggio dovuto alle pietre uguali
	public int letThemFight() {
		TamaGolem t1 = g1.getGolemAttivo();
		TamaGolem t2 = g2.getGolemAttivo();
		int controllo = 0; //verifica che non ci siano 3 pietre uguali
		
		while(t1.getVitaRimanente()>0 || t2.getVitaRimanente()>0) {
			
			int p1 = t1.getPietraAttiva();
			int p2 = t2.getPietraAttiva();
			
			
			if(m.matrice[p1][p2]==0) {//pari
				controllo ++;
				if(controllo == 3)return 3; //3 pietre uguali = pareggio
			}else {
				controllo = 0;
				if(m.matrice[p1][p2]>0) {	//vince p1
					t2.riceviDanno(m.matrice[p1][p2]);
					if(t2.getVitaRimanente() <=0) {
						g2.eliminaGolem();
						if(g2.numGolem()>0)
							g2.setGolemAttivo(); 
						else return 1;
					}
				}else {						//vince p2
					t1.riceviDanno(m.matrice[p2][p1]);
					if(t1.getVitaRimanente() <=0) {
						g1.eliminaGolem();
						if(g1.numGolem()>0)
							g1.setGolemAttivo(); 
						else return 2;
						
					}
				}
			}	
			t1.cicla();
			t2.cicla();	
		}
	}
}