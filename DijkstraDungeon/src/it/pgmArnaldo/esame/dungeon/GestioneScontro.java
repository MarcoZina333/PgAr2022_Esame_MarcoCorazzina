package it.pgmArnaldo.esame.dungeon;

public class GestioneScontro {
	
	public static int calcoloDanno(Personaggio attacco, Personaggio difesa) {
		double modificatore = Math.random() < 0.075 ? 1.5 : 1;
		return (int) (modificatore*(2.0*attacco.getArma().getPotenza()*attacco.getAttaccoAttuale()/ (25.0*difesa.getDifesaAttuale()) + 2));
	}
	
	public static Personaggio cicloScontro(Personaggio primo, Personaggio secondo) {
		System.out.println("Inizia lo scontro tra "+ primo.getNome() +" e "+ secondo.getNome());
		while(!primo.isMorto() && !secondo.isMorto()) {
			int danno = calcoloDanno(primo, secondo);
			secondo.subisciDanno(danno);
			System.out.println(primo.getNome() +" infligge "+ danno +" danni a "+ secondo.getNome());
			if (secondo.isMorto()) break;
			danno = calcoloDanno(secondo, primo);
			primo.subisciDanno(danno);
			System.out.println(secondo.getNome() +" infligge "+ danno +" danni a "+ primo.getNome());
		}
		Personaggio sconfitto = primo.isMorto() ? primo : secondo;
		if (sconfitto.getTipoEvento().equals(TipoEvento.MINIBOSS) || sconfitto.getTipoEvento().equals(TipoEvento.BOSS)) {
			System.out.println("Complimenti, hai battuto il boss di questo livello! Le scale sono state sbloccate");
		}
		return primo.isMorto() ? secondo : primo;
	}
	
	
}
