package it.pgmArnaldo.esame.dungeon;

public abstract class Personaggio implements Evento {
	
	private String nome;
	private int vitaAttuale;
	private int attaccoAttuale;
	private int difesaAttuale;
	
	public Personaggio(String nome, int vitaAttuale, int attaccoAttuale, int difesaAttuale) {
		this.nome = nome;
		this.vitaAttuale = vitaAttuale;
		this.attaccoAttuale = attaccoAttuale;
		this.difesaAttuale = difesaAttuale;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getVitaAttuale() {
		return vitaAttuale;
	}

	public void setVitaAttuale(int vitaAttuale) {
		this.vitaAttuale = vitaAttuale;
	}

	public int getAttaccoAttuale() {
		return attaccoAttuale;
	}

	public void setAttaccoAttuale(int attaccoAttuale) {
		this.attaccoAttuale = attaccoAttuale;
	}

	public int getDifesaAttuale() {
		return difesaAttuale;
	}

	public void setDifesaAttuale(int difesaAttuale) {
		this.difesaAttuale = difesaAttuale;
	}
	
	public abstract Arma getArma();
	
	public void subisciDanno(int danno) {
		vitaAttuale -= danno;
	}
	
	public boolean isMorto() {
		return vitaAttuale <= 0;
	}
	
}
