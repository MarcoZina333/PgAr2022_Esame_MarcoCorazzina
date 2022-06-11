package it.pgmArnaldo.esame.dungeon;

public class Pozione implements Strumento {

	private String nome;
	private String descrizione;
	private int valore; //PERCENTUALE(!) di vita totale curata
	
	//private static final String DESCRIZIONE = "Pozione magica in grado di far rimarginare le ferite a tempo record!\nEffetti collaterali: puzza da far schifo";
	//private static final double VALORE = 50;  //percentuale
	
	public Pozione(String nome, String descrizione, int valore) {
		this.nome = nome;
		this.descrizione = descrizione;
		this.valore = valore;
	}
	
	
	public String getDescrizione() {
		return descrizione;
	}

	public int getValore() {
		return valore;
	}

	@Override
	public String getNome() {
		return nome;
	}

	
	@Override
	public TipoStrumento getTipo() {
		return TipoStrumento.POZIONE;
	}

}
