package codiceHusky.tamaGolem;

public class Scontro {
	private Giocatore g1;
	private Giocatore g2;
	private TamaGolem t1;
	private TamaGolem t2;
	
	
	public Scontro(Giocatore g1, Giocatore g2) {
		this.g1 = g1;
		this.g2 = g2;
	}
	
	public boolean nuovoGolem(Giocatore g, SaccaPietre sacca) {
		TamaGolem golemEstratto = g.evocaGolem();
		if(golemEstratto != null) {
			g.setGolemAttivo(golemEstratto);
			golemEstratto.assegnaPietre(sacca);
			return true;
		} else {
			return false;
		}
	}
	
}
