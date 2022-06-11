package it.pgmArnaldo.esame.dungeon;


public class Piano {

	private Evento[][] eventi;
	
	
	
	
	public Piano(Evento[][] eventi) {
		super();
		this.eventi = eventi;
	}
	
	public Piano(String[][] mappa, int larghezza, int altezza) {
		this(generaPianoDaMappaCaratteri(mappa, larghezza, altezza));
	}

	public static Evento[][] generaPianoDaMappaCaratteri(String[][] mappa, int larghezza, int altezza) {
		Evento[][] eventi = new Evento[altezza][larghezza];
		for (int i = 0; i < mappa.length; i++) {
			for (int j = 0; j < mappa[i].length; j++) {
				String attuale = mappa[i][j];
				if (attuale.equals(TipoEvento.MOSTRO.getParsing())) {
					eventi[i][j] = new MostroBase();
				}
				else if (attuale.equals(TipoEvento.VITTORIA.getParsing())) {
					eventi[i][j] = new EventoMovimento(TipoEvento.VITTORIA);
				}
				else if (attuale.equals(TipoEvento.CHEST.getParsing())) {
					eventi[i][j] = new Chest();
				}
				else if (attuale.equals(TipoEvento.MURO.getParsing())) {
					eventi[i][j] = new EventoMovimento(TipoEvento.MURO);
				}
				else if (attuale.equals(TipoEvento.SCALA_GIU.getParsing())) {
					eventi[i][j] = new EventoMovimento(TipoEvento.SCALA_GIU);
				}
				else if (attuale.equals(TipoEvento.SCALA_SU.getParsing())) {
					eventi[i][j] = new EventoMovimento(TipoEvento.SCALA_SU);
				}
				else if (attuale.equals(TipoEvento.MINIBOSS.getParsing())) {
					eventi[i][j] = new MiniBoss();
				}
				else if (attuale.equals(TipoEvento.BOSS.getParsing())) {
					eventi[i][j] = new Boss();
				}
				else if (attuale.equals(TipoEvento.LIBERA.getParsing())) {
					eventi[i][j] = new EventoMovimento(TipoEvento.LIBERA);
				}
				else if (attuale.equals(TipoEvento.GIOCATORE.getParsing())) {
					eventi[i][j] = new Giocatore();
				}
			} 
		} 
		return eventi;
	}


	public String getStatoPiano() {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < eventi.length; i++) {
			for (int j = 0; j < eventi[i].length; j++) {
				result.append(eventi[i][j].getTipoEvento().getParsing());
			}
			result.append('\n');
		}
		return result.toString();
	}
	
	
	public boolean scaleSbloccate() {
		for (int i = 0; i < eventi.length; i++) {
			for (int j = 0; j < eventi[i].length; j++) {
				if (eventi[i][j].getTipoEvento().equals(TipoEvento.BOSS) || eventi[i][j].getTipoEvento().equals(TipoEvento.MINIBOSS)) {
					return false;
					
				}
			}
		}
		return true;
	}
}
