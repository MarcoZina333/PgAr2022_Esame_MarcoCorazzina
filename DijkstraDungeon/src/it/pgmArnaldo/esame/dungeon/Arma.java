package it.pgmArnaldo.esame.dungeon;

public class Arma implements Strumento{
	private String nome;
	private String descrizione;
	private int potenza;
	
	
	public Arma(String nome, String descrizione, int potenza) {
		this.nome = nome;
		this.descrizione = descrizione;
		this.potenza = potenza;
	}


	public String getNome() {
		return nome;
	}


	public String getDescrizione() {
		return descrizione;
	}


	public int getPotenza() {
		return potenza;
	}
	
	public boolean equals(Arma altra) {
		return nome.equals(altra.nome) && potenza == altra.potenza;
	}


	@Override
	public TipoStrumento getTipo() {
		return TipoStrumento.ARMA;
	}
	
	
}
