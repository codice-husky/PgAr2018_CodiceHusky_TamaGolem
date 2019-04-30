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
	
	public boolean nuovoGolem(Giocatore g, SaccaPietre sacca) {
		TamaGolem golemEstratto = g.evocaGolem();
		if(golemEstratto != null) {
			g.setGolemAttivo(golemEstratto);
			return true;
		} else {
			return false;
		}
	}
	
	
	public void letThemFight() {
		
		TamaGolem t1 = g1.getGolemAttivo();
		TamaGolem t2 = g2.getGolemAttivo();
		
		while(t1.getVitaRimanente()>0 || t2.getVitaRimanente()>0) {
			
			int p1 = t1.getPietraAttiva();
			int p2 = t2.getPietraAttiva();
			
			
			if(m.matrice[p1][p2]==0) ;		//pari
			else {
				if(m.matrice[p1][p2]>0) {	//vince p1
					t2.riceviDanno(m.matrice[p1][p2]);
				}
				else {						//vince p2
					t1.riceviDanno(-(m.matrice[p1][p2]));
				}
			}
			
			t1.cicla();
			t2.cicla();
			
			
			
		}
		
		
	}
	
	
}
