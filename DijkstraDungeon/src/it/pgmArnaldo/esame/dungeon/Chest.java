package it.pgmArnaldo.esame.dungeon;

import it.pgmArnaldo.esame.utility.Utility;

public class Chest implements Evento {
	
	private static final Strumento[] POOL_ARMI = new Arma[] {
			new Arma("Spada Arrugginita", "", 15),
			new Arma("Fioretto", "", 25),
			new Arma("Sciabola", "", 30),
			new Arma("Lancia", "", 35),
			new Arma("Claymore", "", 45),
			new Arma("Zweihander", "", 50),
			new Arma("Katana Perfetta", "", 55),
			new Arma("Alabarda benedetta", "", 70)
			
	};
	
	private static final Strumento[] POOL_SCUDI = new Scudo[] {
			new Scudo("Scudo scassato", "", 5),
			new Scudo("Scudo di assi", "", 5),
			new Scudo("Scudo da guerriero", "", 10),
			new Scudo("Porta robusta", "", 10),
			new Scudo("Scudo del sole", "", 20),
			new Scudo("Scudo benedetto", "", 30)
			
	};
	
	private static final Strumento[] POOL_POZIONI = new Pozione[] {
			new Pozione("Pozione puzzolente", "\"Pozione magica in grado di far rimarginare le ferite a tempo record!\\nEffetti collaterali: puzza da far schifo\"", 50),
			new Pozione("Pozione lenitiva", "", 50),
			new Pozione("Antidolorifici", "", 50)
			
	};
	
	private Strumento strumento;
	
	
	public Chest() {
		this.strumento = estraiStrumentoRandom();
	}


	private static Strumento estraiStrumentoRandom() {
		double estrazione = Math.random();
		if (estrazione < 0.4) {
			return POOL_ARMI[Utility.estraiIntero(0, POOL_ARMI.length-1)];
		}
		else if (estrazione >= 0.4 && estrazione <= 0.7) {
			return POOL_SCUDI[Utility.estraiIntero(0, POOL_SCUDI.length-1)];
		}
		else {
			return POOL_POZIONI[Utility.estraiIntero(0, POOL_POZIONI.length-1)];
		}
	}
	
	
	@Override
	public TipoEvento getTipoEvento() {
		return TipoEvento.CHEST;
	}


	public Strumento getStrumento() {
		return strumento;
	}

}
