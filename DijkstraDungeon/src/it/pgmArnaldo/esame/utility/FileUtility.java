package it.pgmArnaldo.esame.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

import it.pgmArnaldo.esame.dungeon.Piano;


public class FileUtility {

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
	public static Piano leggiLivelloXML(String nomeFile) {
		String[][] mappa = null;
		int altezza = 0;
		int larghezza = 0;
		int riga = 0;
		int colonna = 0;
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
						switch (xmlr.getLocalName()) {
							case TAG_MAPPA:
								tagAttuale = TAG_MAPPA;
								 for (int i = 0; i < xmlr.getAttributeCount(); i++) {
									 switch (xmlr.getAttributeLocalName(i).trim()) {
										 case TAG_LARGHEZZA:
											 if (Utility.isNumeric(xmlr.getAttributeValue(i)))
												 larghezza = Integer.parseInt(xmlr.getAttributeValue(i));
											 break;
										 case TAG_ALTEZZA:
											 if (Utility.isNumeric(xmlr.getAttributeValue(i)))
												 altezza = Integer.parseInt(xmlr.getAttributeValue(i));
											 break;
									 }
								 }
								 if (altezza > 0 && larghezza > 0) {
									 mappa = new String[altezza][larghezza];
								 }
								 else return null;
								break;
							case TAG_CELL:
								tagAttuale = TAG_CELL; 
								break;
							case TAG_ROW:
								tagAttuale = TAG_ROW;
								break;
						}						
						break;
					case XMLStreamConstants.END_ELEMENT: 
						tagAttuale = "";
						if (xmlr.getLocalName().equals(TAG_ROW)) {
							riga++;
						}
						else if (xmlr.getLocalName().equals(TAG_CELL)) {
							colonna = (colonna+1)%larghezza;
						}
						break;														
					case XMLStreamConstants.CHARACTERS:
						if (tagAttuale.equals(TAG_CELL)) {
							if (xmlr.getText().trim().length() == 1) {
								mappa[riga][colonna] = xmlr.getText();
							}
							else {
								mappa[riga][colonna] = "."; //se non e un carattere valido lo considero vuoto
							}
						}
						break;
					}
				xmlr.next();
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("File " + nomeFile + "non trovato");
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(ERRORE_READER);
			System.out.println(e.getMessage());
		}
		if (mappa != null && altezza > 0 && larghezza > 0) {
			System.out.println("ok");
			return new Piano(mappa, larghezza, altezza);
		}
		return null;
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
