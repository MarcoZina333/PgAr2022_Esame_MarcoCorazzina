package it.pgmArnaldo.esame.utility;

import java.util.ArrayList;

/*
Questa classe rappresenta un menu testuale generico a piu' voci
Si suppone che la voce per uscire sia sempre associata alla scelta 0 
e sia presentata in fondo al menu

*/
public class MyMenu
{
  final private static String CORNICE = "---------------------------------------------";
  final private static String RICHIESTA_INSERIMENTO = "Digita il numero dell'opzione desiderata > ";

  private String titolo;
  private String [] voci;
  private String voceUscita = "0\tPassa / Esci";

	
  public MyMenu (String titolo, String [] voci)
  {
	this.titolo = titolo;
	this.voci = voci;
  }
  
  public MyMenu (String titolo, ArrayList<String> voci)
  {
	this.titolo = titolo;
	this.voci = new String[voci.size()];
	this.voci = voci.toArray(this.voci);
  }
  
  public MyMenu (String titolo, String [] voci, String voceUscita)
  {
	this.titolo = titolo;
	this.voci = voci;
	this.voceUscita = "0\t" + voceUscita;
  }
  
  
  public MyMenu (String titolo, ArrayList<String> voci, String voceUscita)
  {
	this.titolo = titolo;
	this.voci = (String[]) voci.toArray();
	this.voceUscita = "0\t" + voceUscita;
  }

  public void setTitolo(String titolo) {
	  this.titolo = titolo;
  }
  
  public String getTitolo() {
	  return this.titolo;
  }
  
  public int scegli ()
  {
	stampaMenu();
	return InputDati.leggiIntero(RICHIESTA_INSERIMENTO, 0, voci.length);	 
  }
		
  public void stampaMenu ()
  {
	System.out.println(CORNICE);
	System.out.println(titolo);
	System.out.println(CORNICE);
    for (int i=0; i<voci.length; i++)
	 {
	  System.out.println( (i+1) + "\t" + voci[i]);
	 }
    System.out.println();
	System.out.println(voceUscita);
    System.out.println();
  }
		
}

