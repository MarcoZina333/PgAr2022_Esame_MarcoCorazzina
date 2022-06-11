package it.pgmArnaldo.esame.dungeon;


public class Piano {
	
	private class Posizione {
		public int i = 0;
		public int j = 0;
		public Posizione(int i, int j) {
			this.i = i;
			this.j = j;
		}
		
		
		
	}
	
	private final char MOV_SU = 'W';
	private final char MOV_GIU = 'S';
	private final char MOV_DX = 'D';
	private final char MOV_SX = 'A';
	private final char COM_AZIONE = 'E';

	private static final Evento CASELLA_VUOTA = new EventoMovimento(TipoEvento.LIBERA);

	private Evento[][] eventi;
	private Giocatore giocatore;
	private Posizione posizAttuale;
	
	
	
	
	public Piano(Evento[][] eventi) {
		super();
		this.eventi = eventi;
		findGiocatore();
	}
	
	public Piano(String[][] mappa, int larghezza, int altezza) {
		this(generaPianoDaMappaCaratteri(mappa, larghezza, altezza));
		findGiocatore();
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
				else {
					eventi[i][j] = new EventoMovimento(TipoEvento.LIBERA);
				}
			} 
		} 
		return eventi;
	}

	private boolean findGiocatore() {
		for (int i = 0; i < eventi.length; i++) {
			for (int j = 0; j < eventi[i].length; j++) {
				Evento attuale = eventi[i][j];
				if (attuale.getTipoEvento().equals(TipoEvento.GIOCATORE)) {
					posizAttuale = new Posizione(i, j);
					giocatore = (Giocatore) attuale;
					eventi[i][j] = CASELLA_VUOTA;
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean aggiornaPosizioneDopoSalita() {
		for (int i = 0; i < eventi.length; i++) {
			for (int j = 0; j < eventi[i].length; j++) {
				Evento attuale = eventi[i][j];
				if (attuale.getTipoEvento().equals(TipoEvento.SCALA_GIU)) {
					posizAttuale = new Posizione(i, j);
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean aggiornaPosizioneDopoDiscesa() {
		for (int i = 0; i < eventi.length; i++) {
			for (int j = 0; j < eventi[i].length; j++) {
				Evento attuale = eventi[i][j];
				if (attuale.getTipoEvento().equals(TipoEvento.SCALA_SU)) {
					posizAttuale = new Posizione(i, j);
					return true;
				}
			}
		}
		return false;
	}
	

	

	public String getStatoPiano() {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < eventi.length; i++) {
			for (int j = 0; j < eventi[i].length; j++) {
				if (i  == posizAttuale.i && j  == posizAttuale.j)
					result.append(TipoEvento.GIOCATORE.getParsing());
				else result.append(eventi[i][j].getTipoEvento().getParsing());
			}
			result.append('\n');
		}
		return result.toString();
	}
	
	public Evento getEventoAttuale() {
		return eventi[posizAttuale.i][posizAttuale.j].getTipoEvento().equals(TipoEvento.GIOCATORE) ? CASELLA_VUOTA : eventi[posizAttuale.i][posizAttuale.j];
	}
	
	public Evento setEventoAttuale(Evento newEvento) {
		return eventi[posizAttuale.i][posizAttuale.j] = newEvento;
	}
	
	public Evento eseguiAzione(char comando) {
		char comUp = Character.toUpperCase(comando);
		switch (comUp) {
			case MOV_SU:
				if ( !(posizAttuale.i - 1 < 0 || eventi[posizAttuale.i-1][posizAttuale.j].getTipoEvento().equals(TipoEvento.MURO)) )
					posizAttuale.i--;
				return getEventoAttuale();
			case MOV_GIU:
				if ( !(posizAttuale.i + 1 == eventi.length || eventi[posizAttuale.i+1][posizAttuale.j].getTipoEvento().equals(TipoEvento.MURO)) )
					posizAttuale.i--;
				return getEventoAttuale();
			case MOV_DX:
				if ( !(posizAttuale.j + 1 == eventi[0].length || eventi[posizAttuale.i][posizAttuale.j+1].getTipoEvento().equals(TipoEvento.MURO)) )
					posizAttuale.j++;
				return getEventoAttuale();
			case MOV_SX:
				if ( !(posizAttuale.j - 1 < 0 || eventi[posizAttuale.i][posizAttuale.j-1].getTipoEvento().equals(TipoEvento.MURO)) )
					posizAttuale.j--;
				return getEventoAttuale();
			case COM_AZIONE:
				if (getEventoAttuale().getTipoEvento().equals(TipoEvento.CHEST)) {
					return getEventoAttuale();
				}
				return CASELLA_VUOTA;
			default:
				return getEventoAttuale();
			}
	}
	
	
	
	public Giocatore getGiocatore() {
		return giocatore;
	}

	public void setGiocatore(Giocatore giocatore) {
		this.giocatore = giocatore;
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
