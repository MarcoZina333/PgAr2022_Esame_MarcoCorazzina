package it.pgmArnaldo.esame.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import it.pgmArnaldo.esame.dungeon.Piano;
import it.unibs.progettoArnaldo.codFiscali.CodiceFiscale;
import it.unibs.progettoArnaldo.codFiscali.Data;
import it.unibs.progettoArnaldo.codFiscali.Persona;
import it.unibs.progettoArnaldo.rovine.Citta;
import it.unibs.progettoArnaldo.rovine.MappaCitta;
import it.unibs.progettoArnaldo.rovine.PercorsoCitta;
import it.unibs.progettoArnaldo.rovine.Posizione;


public class FileUtility {

	private static final String ERRORE_LETTURA = "Errore di lettura";
	private static final String TAG_MAPPA = "mappa";
	private static final String TAG_ALTEZZA = "height";
	private static final String TAG_LARGHEZZA = "width";
	private static final String TAG_ROW = "row";
	private static final String TAG_CELL = "cell";
	private static final String ERRORE_READER = "Errore nell'inizializzazione del reader:";
	
	
	
	
	

	/**Lettura del file passato come argomento e creazione di una MappaCitta contenente le informazioni ricavate 
	 * @param nomeFile : String contenente la directory del file da leggere
	 * @return MappaCitta contenente tutte le citta presenti nel file e relativi collegamenti
	 */
	public static Piano leggiXMLCitta(String nomeFile) {
		String[][] mappa;
		String attuale = null;
		int altezza;
		int larghezza;
		//stringa utile a discriminare il tag in cui si è all'interno
		String tagAttuale = "";

		XMLInputFactory xmlif = null;
		XMLStreamReader xmlr = null;
		try {
			xmlif = XMLInputFactory.newInstance();
			FileInputStream fis = new FileInputStream(nomeFile);
			xmlr = xmlif.createXMLStreamReader(nomeFile, fis);
			while (xmlr.hasNext()) { 													
				switch (xmlr.getEventType()) { 											
					case XMLStreamConstants.START_DOCUMENT: 
						System.out.println("Lettura file "+ nomeFile +" ...");
						break;
					case XMLStreamConstants.START_ELEMENT: 
						if (xmlr.getLocalName().equalsIgnoreCase(TAG_CODICE)) {
							System.out.println("Attenzione file "+ nomeFile +" non valido per la lettura delle persone");
							return persone;
						}							
						switch (xmlr.getLocalName()) {
							case TAG_PERSONE:
								tagAttuale = "";
								break;
							case TAG_PERSONA:
								tagAttuale = TAG_PERSONA; 
								break;
							case TAG_NOME:
								tagAttuale = TAG_NOME;
								break;
							case TAG_COGNOME:
								tagAttuale = TAG_COGNOME; 
								break;
							case TAG_COMUNE_NASCITA:
								tagAttuale = TAG_COMUNE_NASCITA; 
								break;
							case TAG_DATA_NASCITA:
								tagAttuale = TAG_DATA_NASCITA; 
								break;
							case TAG_SESSO:
								tagAttuale = TAG_SESSO;
								break;
						}						
						break;
					case XMLStreamConstants.END_ELEMENT: 
						tagAttuale = "";
						if (xmlr.getLocalName().equals(TAG_PERSONA)) {
							Persona nuovaPersona = new Persona(nome, cognome, sesso, dataNascita, comuneNascita);
							CodiceFiscale.generaCodiceFiscale(cognome, nome , dataNascita, sesso , comuneNascita);
							persone.add(nuovaPersona);
						}
						break;														
					case XMLStreamConstants.CHARACTERS: 								
						if (xmlr.getText().trim().length() > 0) {
							switch (tagAttuale) {
								case TAG_NOME:
									nome = xmlr.getText().trim();
									break;
								case TAG_COGNOME:
									cognome = xmlr.getText().trim();
									break;
								case TAG_COMUNE_NASCITA:
									comuneNascita = xmlr.getText().trim();
									break;
								case TAG_DATA_NASCITA:
									dataNascita = new Data(xmlr.getText().trim());
									break;
								case TAG_SESSO:
									sesso = xmlr.getText().trim().charAt(0);
									break;
							}
						}
						break;
				}
				xmlr.next();
			}
		} catch (FileNotFoundException e) {
			System.out.println("File " + nomeFile + "non trovato");
			System.out.println(MSG_FILE_NOT_FOUND+ nomeFile );
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(MSG_ERRORE_READER_XML);
			System.out.println(e.getMessage());
		}
		return persone;
	}
	
	
	/**Restituisce un ArrayList di file contenuti nella directory passata come argomento
	 * @param cartella : File directory in cui cercare i file
	 * @return ArrayList di File contenenti i file, se esistono, contenuti nella cartella specificata
	 */
	public static ArrayList<File> fileXMLInCartella(File cartella) {
		ArrayList<File> nomiFile = new ArrayList<File>();
		if (cartella.isDirectory()) {
			for (File file : cartella.listFiles()) {
				if (!file.isDirectory() && file.getName().contains(".xml")) {
					nomiFile.add(file);
				} 
			}
		}
		return nomiFile;
		
	}
	
	/**Metodo per scegliere un file tra quelli presenti in una cartella
	 * @param cartella : File directory in cui cercare i file
	 * @return String la directory del file scelto
	 */
	public static String scegliXMLDaCartella(File cartella) {
		ArrayList<File> files = fileXMLInCartella(cartella);
		ArrayList<String> nomiFile = new ArrayList<String>();
		for (int i = 0; i < files.size();i++) {
			nomiFile.add(files.get(i).getName());
		}
		MyMenu menu = null;
		if (nomiFile == null || nomiFile.size() == 0) {
			System.out.println("Nessun file presente nella directory: "+ cartella.getAbsolutePath());
			return null;
		}
		else {
			menu = new MyMenu("Quale file vuoi elaborare?", nomiFile);
			int scelta = menu.scegli()-1;
			if (scelta == -1)
				return null;
			return files.get(scelta).getAbsolutePath();
		}
	}
}
