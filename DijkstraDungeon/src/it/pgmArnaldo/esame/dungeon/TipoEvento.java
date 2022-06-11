package it.pgmArnaldo.esame.dungeon;

public enum TipoEvento {
	
	MOSTRO("M"),
	//NEGOZIO("S"),
	VITTORIA("K"),
	CHEST("C"),
	MURO("#"),
	SCALA_SU("T"),
	SCALA_GIU("t"),
	MINIBOSS("B"),
	GIOCATORE("O"),
	LIBERA("."),
	BOSS("D");
	
	private String parsing;

	private TipoEvento(String parsing) {
		this.parsing = parsing;
	}

	public String getParsing() {
		return parsing;
	}
	
}
