package it.pgmArnaldo.esame.dungeon;

import it.pgmArnaldo.esame.utility.Utility;

public class MiniBoss extends Mostro {
	private static final int VITA_MINIBOSS = 30;
	private final static int ATT_BASE = 7;
	private final static int DEF_BASE = 5;

	public MiniBoss() {
		super(Utility.permutazioneRandomStringa(getNomeBoss()),
			  VITA_MINIBOSS,
			  ATT_BASE,
			  DEF_BASE,
			  estraiArmaMostro());
	}

	@Override
	public TipoEvento getTipoEvento() {
		return TipoEvento.MINIBOSS;
	}
	
	

}
