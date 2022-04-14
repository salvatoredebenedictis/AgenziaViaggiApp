package pacchetto;

public class PacchettoNonValidoException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PacchettoNonValidoException(int i) {
		super();
		if(i!=5 && i!=6) {
			System.out.println("Pacchetto non valido, il valore inserito per l'attributo " +i+" non è accettabile");
		}
		
		if(i==5 || i==6) {
			System.out.println("Pacchetto non valido, la data inserita di inizio o di fine vacanza (o entrambe) non è accettabile");
		}
		
		if(i==1) {
			System.out.println("Il codice potrebbe non rispettare il formato richiesto o essere già stato attribuito.");
		}
	}

}
