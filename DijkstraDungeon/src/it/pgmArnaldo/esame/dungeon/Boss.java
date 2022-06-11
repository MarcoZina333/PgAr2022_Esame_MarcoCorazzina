package it.pgmArnaldo.esame.dungeon;

public class Boss extends Mostro {
	
	

	private static final int POT_ARMA_BOSS = 70;
	private static final int DEF_BOSS = 10;
	private static final int ATT_BOSS = 10;
	private static final int VITA_BOSS = 40;
	private static final Arma ARMA_BOSS = new Arma("ArmaBoss", "", POT_ARMA_BOSS);



	public Boss() {
		super(getNomeBoss(), VITA_BOSS, ATT_BOSS, DEF_BOSS, ARMA_BOSS);
		// TODO Auto-generated constructor stub
	}



	@Override
	public TipoEvento getTipoEvento() {
		return TipoEvento.BOSS;
	}}
