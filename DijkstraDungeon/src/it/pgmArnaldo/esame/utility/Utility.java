package it.pgmArnaldo.esame.utility;

import java.util.Random;

public class Utility {
	private static Random estRandom = new Random();
	
	public static String permutazioneRandomStringa(String str) {
		if (str.length() == 0) return str;
		
		char[] lettere = str.toCharArray();
		 for (int i = 0; i < lettere.length; i++)
	        {
	            int j = i + estRandom.nextInt(lettere.length - i);
	            char c = lettere[i];
	            lettere[i] = lettere[j];
	            lettere[j] = c;
	        }
		 StringBuffer result = new StringBuffer();
		 for (int i = 0; i < lettere.length; i++) {
			 result.append(lettere[i]);
		 }
		return result.toString();
		
	}
	
	public static int estraiIntero(int min, int max)
	{
		 int range = max + 1 - min;
		 int casual = estRandom.nextInt(range);
		 return casual + min;
	}
	
	public static boolean isNumeric (String str) {
		try{
			Double.parseDouble(str);
			return true;
		}catch (NumberFormatException e) {
			return false;
		}
	}
	/**Genera 70 spaziature sulla console
	 */
	public final static void clearConsole(){
	    for (int i = 0; i<70; i++){
	        System.out.println();
	     }
	}	
	
}
