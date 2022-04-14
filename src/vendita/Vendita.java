package vendita;

import java.util.Calendar;
import java.util.HashSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import pacchetto.Pacchetto;
import dipendente.Dipendente;


public class Vendita {
		
	private int codiceVendita;												 				///codice identificativo della vendita
	private Calendar dataVendita= Calendar.getInstance();					 				///data in cui la vendita viene effettuata
	private double prezzoVendita;											 				///prezzo di vendita del pacchetto
	private int pacchettoVenduto;										    			    ///istanza del pacchetto che è stato venduto
	private int dipendenteVendita;									         				///istanza del dipendente che ha effettuato la vendita	
	private static int venditeGiornaliere=0;								 				///contatore di vendite giornaliere effettuate
	private static int venditeAnnuali=0;								     				///contatore di vendite annuali

	private static int maxVenditeAnnuali=0;												///valore masssimo di vendite annuali che un dipendente può effettuare
	
	/**
	 * Costruttore di vednita, permette l'inizializzazione di tutti gli attributi di vendita, quindi la creazione di una nuova vendita
	 * @param dataVendita
	 * @param prezzoVendita
	 * @param dipendenteVendita
	 * @param pacchettoVenduto
	 * @throws VenditaException  eccezione che indica che i dati inseriti all'interno del costruttore non sono validi per la creazione di una vendita
	 */
	public Vendita(double prezzoVendita,int pacchettoVenduto,int dipendenteVendita) throws VenditaException {
		
		
		dataVendita.getTime();
		
		/**se il prezzo è valido viene assegnato, altrimenti genera eccezzione**/
		if(CheckPrezzoVendita(prezzoVendita)) {
			this.prezzoVendita=prezzoVendita;
		}else throw new VenditaException(2);
		
		/**se il codice è valido viene assegnato, altrimenti genera eccezzione**/
		if(CheckDipendente(dipendenteVendita)){
			
			this.dipendenteVendita=dipendenteVendita;
		}else throw new VenditaException();
		
		/**se il codice è valido viene assegnato, altrimenti genera eccezzione**/
		if(CheckPacchetto(pacchettoVenduto)){
			this.pacchettoVenduto=pacchettoVenduto;
		}else throw new VenditaException(1);
		
		System.out.println("Vendita effettuata con successo");
	}
	
	
	/**
	 * Costruttore utilizzato per istanziare pacchetti presenti sul DB che non rispettano controlli 
	 * I dati rappresentati da questo costruttore sono la rappresentazione di vendite già effettuate
	 * @param codiceVendita
	 * @param pacchettoVenduto
	 * @param prezzoVendita
	 * @param dipendenteVendita
	 */
	public Vendita(int codiceVendita,int pacchettoVenduto,double prezzoVendita,int dipendenteVendita) {
		this.codiceVendita=codiceVendita;
		this.prezzoVendita=prezzoVendita;
		this.pacchettoVenduto=pacchettoVenduto;
		this.dipendenteVendita=dipendenteVendita;
	}

	
	/**
	 * metodo che permette di ottenere il codice della vendita
	 * @return codice della vendita
	 */
	public int getCodiceVendita() {
		return codiceVendita;
	}

	/**
	 * metodo che permette di ottenere la data della vendita
	 * @return data di vendita
	 */
	public Calendar getDataVendita() {
		return dataVendita;
	}

	/**
	 * metodo che permette di ottenere il prezzo della vendita
	 * @return repzzo di vendita
	 */
	public double getPrezzoVendita() {
		return prezzoVendita;
	}

	/**
	 * metodo che permette di ottenere il codice del pacchettoa venduto
	 * @return codice del pacchetto
	 */
	public int getPacchettoVenduto() {
		return pacchettoVenduto;
	}

	/**
	 * metodo che permette di ottenere il codice del dipendente che ha venduto il pacchetto
	 * @return codice del dipendente
	 */
	public int getDipendenteVendita() {
		return dipendenteVendita;
	}

	/**
	 * metodo che permette di modificare la data. Utilizzato per assegnare la data tramite operazioni di casting
	 * @param dataVendita
	 */
	public void setDataVendita(Calendar dataVendita) {
		this.dataVendita = dataVendita;
	}


	/**
	 * Metodo per il controllo di validita del prezzo
	 * @param prezzoVendita
	 * @return false se il prezzo non è corretto,altrimenti true
	 */
	private boolean CheckPrezzoVendita(double prezzoVendita) {
		 final  double PREZZOMIN=80;///prezzo minore che può essere assegnato ad una vendita	
		 final double PREZZOMAX = 9999.99;//prezzo massimo che può essere assegnato ad una vendita
		if(prezzoVendita>=PREZZOMIN && prezzoVendita<=PREZZOMAX) {  /**se il prezzo è valido entra nell'if**/
			return true;
		}else return false;
	}
	
	
	
	
	
	/**
	 * Metodo per il controllo di validita del pacchetto
	 * @param p
	 * @return false se il pacchetto non è corretto,altrimenti true
	 */
	private boolean CheckPacchetto(int p) {
		final  int MINCODICEPACCHETTO=1000;						///codice minimo di un pacchetto
		final  int MAXCODICEPACCHETTO=9999;						///codice massimo di un pacchetto

		/** se non rispetta i vinoli della condizione ritorna vero**/
		if((p<MINCODICEPACCHETTO || p>MAXCODICEPACCHETTO)) {
			return false;
		}else return true;
	}
	
	
	/**
	 * Metodo utilizzato per il controllo della validità del codice del dipendente e della validità delle opeazioni di vendita che il dipendente può eseguire
	 * @param d
	 * @return true se rispetta tutti i vincoli,quindi può effettuare la vendita
	 */
	private boolean CheckDipendente(int d) {

		final  int MINCODICEDIPENDENTE=0000;										///minimo codice di un dipendente
		final int MAXCODICEDIPENDENTE=9999;											///massimo codice di un dipendente
		final int MAXVENDITEGIORNALIERE=Dipendente.VenditeGiornaliereMAX;	///valore masssimo di vendite giornaliere che un dipendente può effettuare
		

		
		
		/**verifica che il codice del dipendente sia presente nella seguente condizione**/
		if((d<MINCODICEDIPENDENTE || d>MAXCODICEDIPENDENTE)) {		
			return false;
		}else  
		/**verifica che le vendite del dipendente siano minori di quelle annuali massime**/
		if(venditeAnnuali<maxVenditeAnnuali) {
			/**verifica che le vendite del dipendente siano minori di quelle giornaliere massime**/
			if(venditeGiornaliere<MAXVENDITEGIORNALIERE) {
				
				return true;
			}else {
				System.out.println("vendite giornaliere raggiunte");
				return false;
			}
		}else {
			
			System.out.println("Vendite annuali raggiunte");
			return false;
		}
		
	}

	
	
	
	
	
	/**
	 * meteodo per la stampa di tutte le informaizoni riguardanti la vendita
	 */
	public void PrintAllInformations() {
		/** oggetto che permette di stabilire il formato della visualizzazione della data**/
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.println("Informazioni riguardanti la seguente vendita:");
		System.out.println("CODICE:\t\t\t"+this.getCodiceVendita());
		System.out.println("DATA VENDITA:\t\t"+sdf.format(this.getDataVendita().getTime()));
		System.out.println("PREZZO VENDITA:\t\t"+this.getPrezzoVendita()+" €");
		System.out.println("CODICE DIPENDENTE:\t"+this.getDipendenteVendita());
		System.out.println("CODICE PACCHETTO:\t"+this.getPacchettoVenduto());

		System.out.println("------------------------------------------------------------------------------------------------\n");
	}
	
	/**
	 * Metodo che setta il valore delle vendite giornaliere
	 * @param a
	 */
	public static void Dativendita(int a) {
		venditeGiornaliere=a;
	}
	
	/**
	 * Metodo che setta il valore delle vendite giornaliere
	 * @param a
	 */
	public static void DatiVenditaAnno(int a,int b) {
		venditeAnnuali=a;
		maxVenditeAnnuali=b;
		
	}
	
	

}
