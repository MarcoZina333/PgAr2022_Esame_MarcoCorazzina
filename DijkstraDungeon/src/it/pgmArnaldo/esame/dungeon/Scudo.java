package it.pgmArnaldo.esame.dungeon;

public class Scudo implements Strumento {
	
	private String nome;
	private int valore;
	private String descrizione;

	
	public Scudo(String nome, String descrizione, int valore) {
		this.nome = nome;
		this.descrizione = descrizione;
		this.valore = valore;
	}
	
	

	public String getDescrizione() {
		return descrizione;
	}



	public int getValoreScudo() {
		return valore;
	}


	@Override
	public String getNome() {
		return nome;
	}

	@Override
	public TipoStrumento getTipo() {
		return TipoStrumento.SCUDO;
	}

	
	
}
