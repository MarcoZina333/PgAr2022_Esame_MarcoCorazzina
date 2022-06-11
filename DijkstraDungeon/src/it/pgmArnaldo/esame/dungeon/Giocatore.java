package it.pgmArnaldo.esame.dungeon;

public class Giocatore extends Personaggio {
	
	
	private static final String DESCR_PUGNI = "Le mani di un eroe che e disposto a tutto per ritrovare la sua principessa";
	private final static int VITA_BASE = 20;
	private final static int ATT_BASE = 5;
	private final static int DEF_BASE = 5;
	private final static int SIZE_INVENTARIO = 6;
	
	
	private final static Arma ARMA_BASE = new Arma("Pugni", DESCR_PUGNI, 1);
	private Strumento impugnato;
	private Strumento[] inventario;
	private int qtyOggetti;
	private int fattoreSpostamento;
	
	
	
	public Giocatore(String nome) {
		super(nome, VITA_BASE, ATT_BASE, DEF_BASE);
		impugnato = ARMA_BASE;
		inventario = new Strumento[SIZE_INVENTARIO];
		qtyOggetti = 0;
		fattoreSpostamento = 1;
	}
	
	public Giocatore() {
		super("", VITA_BASE, ATT_BASE, DEF_BASE);
		impugnato = ARMA_BASE;
		inventario = new Strumento[SIZE_INVENTARIO];
		qtyOggetti = 0;
		fattoreSpostamento = 1;
	}
	
	
	public boolean aggiungiStrumentoAInventario(Strumento str) {
		if (qtyOggetti < 6) {
			inventario[qtyOggetti] = str;
			return true;
		}
		return false;
	}
	
	public boolean sostituisciStrumentoInv(Strumento nuovo, Strumento daSostituire) {
		for (int i = 0; i < inventario.length; i++) {
			if (inventario[i].equals(daSostituire)) {
				inventario[i] = nuovo;
				return true;
			}
		}
		return false;
	}
	
	public boolean sostituisciStrumentoInv(Strumento nuovo, String daSostituire) {
		for (int i = 0; i < inventario.length; i++) {
			if (inventario[i].getNome().equals(daSostituire)) {
				inventario[i] = nuovo;
				return true;
			}
		}
		return false;
	}
	
	
	public Strumento getImpugnato() {
		return impugnato;
	}




	public void setImpugnato(Strumento impugnato) {
		this.impugnato = impugnato;
	}
	
	public Arma getArma() {
		return impugnato != null && impugnato.getTipo().equals(TipoStrumento.ARMA) ? (Arma) impugnato : ARMA_BASE;
	}


	@Override
	public TipoEvento getTipoEvento() {
		return TipoEvento.GIOCATORE;
	}


	public int getFattoreSpostamento() {
		return fattoreSpostamento;
	}
	
	
}
