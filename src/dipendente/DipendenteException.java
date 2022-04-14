/**
 * 
 */
package dipendente;

/**
 * Classe che lancia il messaggio di errore per ogni eccezione incontrata
 * @author salvatore de Benedictis
 *
 */
public class DipendenteException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Codice errore 1 = Errore sul codice del dipendente : il codice inserito supera il limite di codice minimo o massimo
	 * Codice errore 2 = Errore sul nome del dipendente: il nome inserito è una stringa vuota o nulla
	 * Codice errore 3 = Errore sul cognome del dipendente: il cognome inserito è una stringa vuota o nulla
	 * Codice errore 4 = Errore sulle vendite annuali del dipendente : il numero di vendite annuali sono inferiori alle vendite giornaliere
	 * @param errore codice di errore generato
	 */
	public DipendenteException (int errore) {
		switch(errore) {
		case 1: 
			System.out.println("Errore sul codice del dipendente : il codice ha superato il limite minimo o massimo \n");
			break;
		case 2:
			System.out.println("Errore sul nome del dipendente : il nome inserito non contiene valori validi \n");
			break;
		case 3:
			System.out.println("Errore sul cognome del dipendente : il cognome inserito non contiene valori validi \n");
			break;
		case 4:
			System.out.println("Errore sulle vendite annuali del dipendente : il valore inserito è inferiore al limite consentito \n");
			break;
		default:
			System.out.println("Errore generico sull'inserimento dei dati \n");
			break;
		}
		
	}
	

}
