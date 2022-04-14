package dipendente;
/**
 * 
 */

/**
 * Classe del dipendente che effettuerà la vendita
 * @author salvatore de Benedictis
 *
 */
public class Dipendente {
	 private int CodiceDipendente ;							///codice identificativo del dipendente
	 private String Nome;									///nome del dipendente
	 private String Cognome;								///cognome del dipendente
	 private int VenditeAnnualiMAX ; 						///vendite massime annuali del dipendente (varia da dipendente a dipendente)
	 public static final int VenditeGiornaliereMAX = 20;	///massimo numero di vendite giornaliere del dipendente (invariato tra i dipendenti)
	 private int ContaVenditeGiornaliere = 0;				///contatore di vendite giornaliere viene incrementato ad ogni vendita
	 private int ContaVenditeAnnuali = 0;					///contatore di vendite annuali, viene incrementato ad ogni vendita
	 public static int venditeGiornaliere=0;				///variabile contenente il valore delle vendite effettuate giornalmente
	 public static int venditeAnnuali=0;					///variabile contenente il valore delle vendite effettuate annualmente
	/**
	 * Costruttore della classe Dipendente permette di creare un oggetto di tipo dipendente
	 * 
	 * @param codiceDipendente  codice identificativo del dipendente
	 * @param nome nome del dipendente
	 * @param cognome cognome del dipendente
	 * @param venditeAnnualiMAX vendite massime annuali del dipendente
	 * @throws DipendenteException eccezione che indica che i dati inseriti non sono validi e ne genera un codice di errore
	 */
	public Dipendente(int codiceDipendente, String nome, String cognome, int venditeAnnualiMAX) throws DipendenteException {
		
		if (checkCodiceDipendente(codiceDipendente)==true)this.CodiceDipendente = codiceDipendente;
		else throw new DipendenteException(1);
		
		if (checkStringa(nome)==true)this.Nome = nome;
		else throw new DipendenteException(2);
		
		if (checkStringa(cognome)==true)this.Cognome = cognome;
		else throw new DipendenteException(3);
		
		if(checkVenditeAnnualiMin(venditeAnnualiMAX)==true)this.VenditeAnnualiMAX = venditeAnnualiMAX;
		else throw new DipendenteException(4);
	}

	/**
	 * Costruttore della classe Dipendente che utilizza tutti gli attributi del dipendente
	 * @param codiceDipendente codice identificativo del dipendente
	 * @param nome stringa contenente il nome del dipendente
	 * @param cognome stringa contenente il cognome del dipendente
	 * @param venditeAnnualiMAX vendite massime annuali del dipendente
	 * @param VenditeGiornaliereMAX vendite massime giornaliere del dipendente
	 * @param ContaVenditeGiornaliere contatore delle vendite giornaliere
	 * @param ContaVenditeAnnuali contatore delle vendite annuali
	 * @throws DipendenteException eccezione che indica che i dati inseriti non sono validi e ne genera un codice di errore
	 */
		public Dipendente(int codiceDipendente, String nome, String cognome, int venditeAnnualiMAX, int VenditeGiornaliereMAX,
		int ContaVenditeGiornaliere, int ContaVenditeAnnuali) throws DipendenteException {
		
		if (checkCodiceDipendente(codiceDipendente)==true)this.CodiceDipendente = codiceDipendente;
		else throw new DipendenteException(1);
		
		if (checkStringa(nome)==true)this.Nome = nome;
		else throw new DipendenteException(2);
		
		if (checkStringa(cognome)==true)this.Cognome = cognome;
		else throw new DipendenteException(3);
		
		if(checkVenditeAnnualiMin(venditeAnnualiMAX)==true)this.VenditeAnnualiMAX = venditeAnnualiMAX;
		else throw new DipendenteException(4);
	}
	
	

	/**
	 * permette di ottenere il codice del dipendente
	 * @return the codiceDipendente
	 */
	public int getCodiceDipendente() {
		return CodiceDipendente;
	}

	/**
	 * permette di ottenere il nome del dipendente
	 * @return the nome
	 */
	public String getNome() {
		return Nome;
	}

	/**
	 * permette di ottenere il cognome del dipendente
	 * @return the cognome
	 */
	public String getCognome() {
		return Cognome;
	}

	/**
	 * permette di ottenere il numero di vendite annuali che può eseguire il dipendente
	 * @return the venditeAnnualiMAX
	 */
	public int getVenditeAnnualiMAX() {
		return VenditeAnnualiMAX;
	}

	/**
	 * permette di ottenere il numero di vendite giornaliere già effettuate
	 * @return the contaVenditeGiornaliere
	 */
	public int getContaVenditeGiornaliere() {
		return ContaVenditeGiornaliere;
	}

	/**
	 * permette di ottenere il numero di vendite annuali già effettuate
	 * @return the contaVenditeAnnuali
	 */
	public int getContaVenditeAnnuali() {
		return ContaVenditeAnnuali;
	}
	
	/**
	 * Metodo per stampare sulla console tutte le informazioni del dipendente
	 */
	public void showAllInfo() {
		System.out.println("Informazioni del dipendente selezionato");
		System.out.println("CODICE DIPENDENTE: "+ this.getCodiceDipendente());
		System.out.println("NOME DIPENDENTE: "+ this.getNome());
		System.out.println("COGNOME DIPENDENTE: "+ this.getCognome());
		System.out.println("LIMITE VENDITE ANNUALI: "+ this.getVenditeAnnualiMAX());
		System.out.println("VENDITE ANNUALI ANCORA DISPONIBILI: "+ (this.VenditeAnnualiMAX - venditeGiornaliere));
		System.out.println("LIMITE VENDITE GIORNALIERE: "+ VenditeGiornaliereMAX);
		System.out.println("VENDITE GIORNALIERE ANCORA DISPONIBILI: "+ (VenditeGiornaliereMAX - venditeAnnuali));
		System.out.println("------------------------------------------------------------------------------------------------\n");
	}

	/**
	 * Funzione booleana per controllare che il codice del dipendente sia in un range valido
	 * @param codice codice del dipendente da verificare
	 * @return falso se il codice non è valido, vero altrimenti
	 */
	private boolean checkCodiceDipendente(int codice) {
		final int codiceMIN = 0000;
		final int codiceMAX = 9999;
		
		if(codice < codiceMIN || codice > codiceMAX) return false;
		else return true;
	}
	
	/**
	 * Funzione booleana che controlla che la stringa sia valida (non sia nulla o vuota)
	 * @param str stringa che deve essere controllata
	 * @return falso se la stringa non è valida, vero altrimenti
	 */
	private boolean checkStringa (String str) {
		if (str.equals(null)|| str.isEmpty()) return false;
		else return true;
	}
	
	/**
	 * Funzione booleana che controlla che le vendite annuali siano almeno pari alle vendite giornaliere massime
	 * @param venditeAnnuali il numero di vendte annuali che il dipendente può effettuare
	 * @return falso se il numero di vendite annuali non è valido, vero altrimenti
	 */
	private boolean checkVenditeAnnualiMin(int venditeAnnuali) {
		if (venditeAnnuali < VenditeGiornaliereMAX) return false;
		else return true;
	}

	/**
	 * funzione booleana che controlla che il numero di vendite annuali siano inferiori rispetto al limite di vendite annuali
	 * del dipendente
	 * 
	 * @return vero se le vendite effettuate sono minori o uguali del massimo numero di vendite annuali, falso altrimenti
	 */
	public boolean checkVenditeAnnuali() {
		 if (this.VenditeAnnualiMAX >= this.ContaVenditeAnnuali) return true;
		 else return false;
		}
	 
	 /**
	  * funzione booleana che controlla che il numero di vendite giornaliere siano inferiori rispetto al limite di vendite giornaliere
	  * del dipendente
	  * 
	  * @return vero se le vendite effettuate sono minori o uguali del massimo numero di vendite giornaliere, falso altrimenti
	  */
	 public boolean checkVenditeGiornaliere() {
		 if (VenditeGiornaliereMAX >= this.ContaVenditeGiornaliere) return true;
		 else return false;
	 }
	 
	 
	 /**
	  * Metodo che avvallora la variabile venditeGiornaliere
	  * @param valore
	  */
	 public static void VenditeGiornaliere(int valore) {
		 venditeGiornaliere=valore;
	 }
	 
	 
	 /**
	  * Metodo che avvallora la variabile venditeAnnuali
	  * @param valore
	  */
	 public static void VenditeAnnuali(int valore) {
		 venditeAnnuali=valore;
	 }
}


