package it.pgmArnaldo.esame.dungeon;

public class EventoMovimento implements Evento{
	
	TipoEvento tipo;
	
	public EventoMovimento(TipoEvento tipo) {
		this.tipo = tipo;
	}


	@Override
	public TipoEvento getTipoEvento() {
		return tipo;
	}

}
