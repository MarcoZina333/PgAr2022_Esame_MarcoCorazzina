package it.pgmArnaldo.esame.dungeon;

import it.pgmArnaldo.esame.utility.Utility;

public class MostroBase extends Mostro {
	
	private final static int ATT_BASE = 5;
	private final static int DEF_BASE = 5;
	private final static int MIN_VITA = 15;
	private final static int MAX_VITA = 25;

	public MostroBase() {
		super(Utility.permutazioneRandomStringa(getNomeBoss()),
			  Utility.estraiIntero(MIN_VITA, MAX_VITA),
			  ATT_BASE,
			  DEF_BASE,
			  estraiArmaMostro());
	}

	@Override
	public TipoEvento getTipoEvento() {
		return TipoEvento.MOSTRO;
	}
	
	

}
