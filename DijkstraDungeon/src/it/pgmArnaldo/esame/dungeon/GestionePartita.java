package it.pgmArnaldo.esame.dungeon;

import java.util.ArrayList;

import it.pgmArnaldo.esame.utility.FileUtility;
import it.pgmArnaldo.esame.utility.InputDati;
import it.pgmArnaldo.esame.utility.MyMenu;
import it.pgmArnaldo.esame.utility.Utility;

public class GestionePartita {

	private static final String PATH_LIV1 = "livello1.xml";
	private static final String PATH_LIV2 = "livello2.xml";
	private static final String PATH_LIV3 = "livello3.xml";
	private static final String PATH_LIV4B = "livello4Boss.xml";
	private static final String PATH_LIV4P = "livello4Princess.xml";
	private static final String[] PATHS_LIVELLI = {PATH_LIV1, PATH_LIV2, PATH_LIV3, PATH_LIV4B, PATH_LIV4P};
	private static final Evento CASELLA_VUOTA = new EventoMovimento(TipoEvento.LIBERA);
	
	private static Giocatore giocatore; 
	public static ArrayList<Piano> piani = new ArrayList<Piano>();
	
	public static void inizzializzaMappaDaFile() {
		piani.clear();
		for (String string : PATHS_LIVELLI) {
			Piano p = FileUtility.leggiLivelloXML(string);
			if (p != null) {
				piani.add(p);
			}
		}
	}
	
	public static void newGame(String nomeGiocatore) {
		inizzializzaMappaDaFile();
		if (piani.size() == 0 || piani == null) return;
		int iPiano = 0;
		boolean finito = false;
		boolean chest = false;
		Evento evAttuale = piani.get(iPiano).getEventoAttuale();
		giocatore = piani.get(iPiano).getGiocatore();
		giocatore.setNome(nomeGiocatore);
		MyMenu menuPrincipale = new MyMenu("Scegli un opzione", new String[]{"Apri inventario", "Visualizza le tue statistiche" }, "Esci dalla partita");
		while (!evAttuale.getTipoEvento().equals(TipoEvento.VITTORIA) && !giocatore.isMorto() && !finito) {
			System.out.println("\n\n\n");
			System.out.println(piani.get(iPiano).getStatoPiano());
			char comando = InputDati.leggiUpperChar("Inserisci un comando: ", "WASDEM");
			if (comando == 'M') {
				int sceltaInt =menuPrincipale.scegli();
				switch (sceltaInt) {
				case 1:
					azioneDaInventario(giocatore);
					break;
				case 2:
					System.out.println(giocatore.toString());
					break;
				default:
					finito = true;
					break;
				}
			}
			else evAttuale = piani.get(iPiano).eseguiAzione(comando);
			
			switch (evAttuale.getTipoEvento()) {
			case MOSTRO:
				GestioneScontro.cicloScontro(giocatore, (Personaggio)evAttuale);
				piani.get(iPiano).setEventoAttuale(CASELLA_VUOTA);
				break;
			case CHEST:
				if (chest) {
					giocatore.aggiungiStrumentoAInventario(((Chest)evAttuale).getStrumento());
					chest = false;
				}
				else chest = true;
				break;
			case SCALA_GIU:
				if (piani.get(iPiano).scaleSbloccate()) {
					iPiano--;
					piani.get(iPiano).aggiornaPosizioneDopoDiscesa();
					piani.get(iPiano).setGiocatore(giocatore);
				}
				else System.out.println("Scale non ancora sbloccate");
				
				break;
			case SCALA_SU:
				if (piani.get(iPiano).scaleSbloccate()) {
					iPiano++;
					piani.get(iPiano).aggiornaPosizioneDopoSalita();
					piani.get(iPiano).setGiocatore(giocatore);
				}
				else System.out.println("Scale non ancora sbloccate");
				break;
			default:
				break;
			}
		}
		if (finito) {
			System.out.println("alla prossima");
		}
		else if (giocatore.isMorto()) {
			System.out.println("Hai perso");
		}
		else System.out.println("Complimenti hai vinto");
		
	}
	
	
	public static Strumento scegliStrumentoDaInventario(Giocatore g) {
		Strumento[] strumenti = g.getInventario();
		ArrayList<String> voci = new ArrayList<>();
		for (int i = 0; i < strumenti.length; i++) {
			voci.add(strumenti[i].getNome());
		}
		if (voci.size() == 0) return null;
		MyMenu menuInventario = new MyMenu("Scegli un oggetto", voci, "Torna alla partita");
		int scelta = menuInventario.scegli();
		return scelta == 0 ? null : strumenti[scelta-1];
	}
	
	public static void azioneDaInventario(Giocatore g) {
		Strumento attuale = scegliStrumentoDaInventario(g);
		if (attuale == null) {
			System.out.println("Non hai oggetti nell'inventario");
			return;
		}
		MyMenu menuAzione = new MyMenu("Cosa vuoi fare", new String[]{"Vedi descrizione", "Utilizza/equipaggia" }, "Torna alla partita");
		int scelta = menuAzione.scegli();
		switch (scelta) {
			case 1:
				System.out.println(attuale.getDescrizione());
			break;
			case 2:
				g.usaOggettoInventario(attuale.getNome());
				break;
		default:
			break;
		}
		
		
	}
}
