package it.pgmArnaldo.esame.dungeon;

import it.pgmArnaldo.esame.utility.Utility;

public abstract class Mostro extends Personaggio {
	private static final String NOME_BOSS = "DIJKSTRA";
	private Arma impugnata;
	private final static int MIN_POT_ARMA = 35;
	private final static int MAX_POT_ARMA = 55;

	public Mostro(String nome, int vitaAttuale, int attaccoAttuale, int difesaAttuale, Arma impugnata) {
		super(nome, attaccoAttuale, attaccoAttuale, attaccoAttuale);
		this.impugnata = impugnata;
	}

	public Arma getArma() {
		return impugnata;
	}
	
	public static Arma estraiArmaMostro() {
		return new Arma("Spada", "", Utility.estraiIntero(MIN_POT_ARMA, MAX_POT_ARMA));
	}

	public static String getNomeBoss() {
		return NOME_BOSS;
	}
	
	
}
