package pacchetto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import database.DatabaseAgenzia;

/**
 * classe che rappresenta la totalità dei pacchetti vacanza che verranno venduti nell' agenzia viaggi che stiamo realizzando
 * @author DiasparraGiuseppe
 *
 */
public class Pacchetto implements Comparable<Pacchetto> {
	private int codice;											/// codice identificativo del pacchetto
	private String localita;									/// località di destinazione del pacchetto
	private double costo;										///prezzo di listino del pacchetto, potrà differire da quello di vendita
	private String nome;										/// nome esplicativo del pacchetto
	private Calendar dataInizioVacanza= Calendar.getInstance();	/// data di inizio della vacanza
	private Calendar dataFineVacanza=Calendar.getInstance();	/// data di fine della vacanza
	private int numeroPersonePacchetto;							/// numero di persone comprese nella vacanza
	private String descrizione;									/// descrizione del pacchetto
	private final static TipoPacchetto tipo=TipoPacchetto.STANDARD;	/// tipologia del pacchetto, definita e fissa (per traccia)
	
	private final static double costoMinimo=80;				/// costo minimo di un pacchetto (secondo assunzione fatta)
	/**container dei codici di tutti i pacchetti istanziati. è static perchè è lo stesso per tutti i pacchetti*/
	private static Set <Integer> containerCodici= new HashSet<Integer>();
	
	Scanner leggi2=new Scanner(System.in);
	
	
	/**
	 * Costruttore di pacchetto, permette quindi la creazione di un nuovo pacchetto
	 * @param codice  codice del pacchetto inserito	
	 * @param localita	città di destinazione della vacanza
	 * @param costo	prezzo di listino del pacchetto
	 * @param nome nome breve ed esplicativo del pacchetto
	 * @param dataInizioVacanza	
	 * @param dataFineVacanza
	 * @param descrizione stringa che descrive la vacanza
	 * @throws PacchettoNonValidoException  eccezione che indica che i dati inseriti non sono validi per la creazione di un pacchetto
	 */
	public Pacchetto(int codice, String localita, double costo, String nome, Calendar dataInizioVacanza,
			Calendar dataFineVacanza, int numeroPersonePacchetto, String descrizione) throws PacchettoNonValidoException {
		
		/** se il codice è valido assegnalo altrimenti genera eccezione**/
		if(checkCodice(codice)) {
			this.codice = codice;
		}else {
			throw new PacchettoNonValidoException(1);
		}
		
		/** se la stringa inserita come località è valida assegnala altrimenti genera eccezione**/
		if(checkStringa(localita)) {
			this.localita = localita;		
		}else {
			throw new PacchettoNonValidoException(2);
		}
		
		
		/** se il costo è valido assegnalo altrimenti genera eccezione**/
		if(costo>costoMinimo) {
			this.costo = costo;				
		}else {
			throw new PacchettoNonValidoException(3);
		}
		
		
		/** se la stringa inserita come nome è valida assegnala altrimenti genera eccezione**/
		if(checkStringa(nome)) {
			this.nome = nome;				
		}else {
			throw new PacchettoNonValidoException(4);
		}
		
		
		/** se le date inserite sono valide assegnale altrimenti genera eccezione**/
		if(checkDate(dataInizioVacanza,dataFineVacanza)) {
			this.dataInizioVacanza = dataInizioVacanza;			
			this.dataFineVacanza = dataFineVacanza;
		}else {
			throw new PacchettoNonValidoException(5);
		}
		
		
		if(checkNumPersone(numeroPersonePacchetto)) {
			this.numeroPersonePacchetto=numeroPersonePacchetto;
		}else {
			throw new PacchettoNonValidoException(7);
		}
		
		
		
		/** se la stringa inserita come descrizione è valida assegnala altrimenti genera eccezione**/
		if(checkStringa(descrizione)) {
			this.descrizione = descrizione;						
		}else {
			throw new PacchettoNonValidoException(8);
		}
		
		/**se il codice è valido lo inserisco nell'insieme dei codici di pacchetti creati,
		 * lo faccio qui perché se l'esecuzione è arrivata qui il pacchetto verrà sicuramente istanziato
		 */
		containerCodici.add(codice);
	}
	
	/**
	 * Costruttore alternativo, utilizzato per istanziare pacchetti presenti sul DB che non rispettano controlli sulle date in quanto creati precedentemente
	 * I pacchetti istanziati con questo costruttore sono pacchetti storici che rappresentano vacanze già avvenute o in corso
	 * @param descrizione
	 * @param numeroPersonePacchetto
	 * @param dataFineVacanza
	 * @param dataInizioVacanza
	 * @param nome
	 * @param costo
	 * @param localita
	 * @param codice
	 */
	public Pacchetto(String descrizione,int numeroPersonePacchetto,Calendar dataFineVacanza,Calendar dataInizioVacanza,String nome,double costo,
			String localita,int codice) {
		this.codice=codice;
		this.localita=localita;
		this.costo=costo;
		this.nome=nome;
		this.dataInizioVacanza=dataInizioVacanza;
		this.dataFineVacanza=dataFineVacanza;
		this.numeroPersonePacchetto=numeroPersonePacchetto;
		this.descrizione=descrizione;
	}
	


	/**
	 * Metodo per il controllo della validità del codice inserito, cioè se esiste già un pacchetto con quel codice 
	 * o se non rispetta lo standard designato ( 4 cifre)
	 * @param codice
	 * @return false se il codice non è valido, true altrimenti
	 */
	public static boolean checkCodice(int codice) {					
		final int codiceMinAccettabile=1000;
		final int codiceMaxAccettabile=9999;
		
		if(codice<codiceMinAccettabile || codice>codiceMaxAccettabile) {
			return false;
		}
		
		/** se il codice è già stato inserito il pacchetto non può essere istanziato*/
		if(containerCodici.contains(codice)) {
			
			return false;
			
		}
		return true;
	}
	
	
	
	/**
	 * Metodo per il controllo della validità di una stringa
	 * @param stringa
	 * @return false se la stringa non è valida, true altrimenti
	 */
	private boolean checkStringa(String stringa) {				
	
		if(stringa.equals(null) || stringa.isEmpty()) {
			return false;
		}else {
			return true;
		}
	}
	
	
	/**
	 * Metodo per il controllo della validità di due date
	 * @param dataInizio data inizio vacanza
	 * @param dataFine	data fine vacanza
	 * @return false se una delle due date (o entrambe) non è valida o se quella di fine precede quella di inizio, true altrimenti
	 */
	private boolean checkDate(Calendar dataInizio, Calendar dataFine) {				
		
		/** creazione variabili Calendar che conterranno la data minima e massima accettabile per una vacanza*/
		final Calendar minimaDataAccettabile=Calendar.getInstance();
		final Calendar massimaDataAccettabile=Calendar.getInstance();
		
		/** attribuisco i valori alle variabili precedentemente create
		 *  L'agenzia vende pacchetti vacanza con data di inizio vacanza dal giorno successivo a quello corrente, e data di fine 
		 *   uguale alla data corrente  più un mese e mezzo */
		minimaDataAccettabile.roll(Calendar.DATE,1);	///Data odierna più un giorno
		
		/** data odierna più un mese */
		massimaDataAccettabile.roll(Calendar.MONTH, 1);

		/** se la data inserita è antecedente a quella minima accettabile o successiva a quella massima accettabile non va bene**/
		if(dataInizio.compareTo(minimaDataAccettabile)<0 || dataInizio.compareTo(massimaDataAccettabile)>0) {
			return false;
		}
		
		if(dataFine.compareTo(minimaDataAccettabile)<0 || dataFine.compareTo(massimaDataAccettabile)>0) {
			return false;
		}
		
		/** se la data di fine antecede quella di inizio non è valido il pacchetto**/
		if(dataInizio.after(dataFine)) {
			return false;
		}
		return true;
	}
	
	
	/**
	 * Controllo se il numero di persone inserite è accettabile o meno
	 * @param numeroPersonePacchetto  numero delle persone comprese nel pacchetto vacanza
	 * @return	false se non è accettabile, true se lo è
	 */	
	private boolean checkNumPersone(int numeroPersonePacchetto) {	
		final int MINPERSONE=1;
		final int MAXPERSONE=6;
		
		if(numeroPersonePacchetto<MINPERSONE || numeroPersonePacchetto>MAXPERSONE) {
			return false;
		}
		return true;
	}

	/**
	 * metodo che permette di ottenere il codice del pacchetto
	 * @return codice del pacchetto 
	 */
	public int getCodice() {
		return codice;
	}

	/**
	 * metodo che permette di ottenere la località del pacchetto
	 * @return localita della vacanza 
	 */
	public String getLocalita() {
		return localita;
	}

	/***
	 * metodo che permette di ottenere il costo del pacchetto
	 * @return costo di listino del pacchetto
	 */
	public double getCosto() {
		return costo;
	}

	/**
	 * metodo che permette di ottenere il nome del pacchetto
	 * @return nome del pacchetto
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * metodo che permette di ottenere la data di inzio della vacanza del pacchetto
	 * @return dataInizioVacanza data di inizio della vacanza
	 */
	public Calendar getDataInizioVacanza() {
		return dataInizioVacanza;
	}

	/**
	 * metodo che permette di ottenere la data di fine della vacanza del pacchetto
	 * @return dataFineVacanza data di fine della vacanza
	 */
	public Calendar getDataFineVacanza() {
		return dataFineVacanza;
	}

	/**
	 * metodo che permette di ottenere il numero di persone previste dal pacchetto per la vacanza
	 * @return numeroPersonePacchetto numero di persone per la vacanza
	 */
	public int getNumeropersonepacchetto() {
		return numeroPersonePacchetto;
	}

	/**
	 * metodo che permette di ottenere la descrizione dal pacchetto 
	 * @return descrizione stringa contenente una breve spiegazione della vacanza
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * metodo che permette di ottenere la tipologia dal pacchetto 
	 * @return tipo del pacchetto che di default è STANDARD
	 */
	public static TipoPacchetto getTipo() {
		return tipo;
	}
	

	public static Set <Integer> getcontainerCodici(){
		return containerCodici;
	}
	/**
	 * metodo di stampa di tutte le informazioni riguardanti il pacchetto.
	 */
	public void printAllInformations() {
		/** oggetto che permette di stabilire il formato di visualizzazione di una data**/
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("Informazioni riguardanti il pacchetto selezionato");
		System.out.println("CODICE:\t\t\t\t"+this.getCodice());
		System.out.println("NOME:\t\t\t\t"+this.getNome());
		System.out.println("COSTO:\t\t\t\t"+this.getCosto()+'€');
		System.out.println("LOCALITA':\t\t\t"+this.getLocalita());
		System.out.println("DESCRIZIONE:\t\t\t"+this.getDescrizione());
		System.out.println("NUMERO PERSONE PACCHETTO:\t"+this.getNumeropersonepacchetto());
		System.out.println("TIPO PACCHETTO:\t\t\t"+this.getTipo());
		System.out.println("DATA INIZIO VACANZA:\t\t"+sdf.format(this.getDataInizioVacanza().getTime()));
		System.out.println("DATA FINE VACANZA:\t\t"+sdf.format(this.getDataFineVacanza().getTime()));
		System.out.println("---------------------------------------------------------------------------------");
	
	}
	
	/**
	 * Metodo di modifica di un pacchetto
	 * Presenta un menu nel quale è possibile scegliere quali attributi modificare, modifica l'istanza e chiama il metodo di modifica sul DB
	 * passando come parametro il numero corrispondente al valore da modificare
	 */
	public void modificaPacchetto(DatabaseAgenzia db) {
		int sceltaModifica=0;
		int nuovoNpp=0;	// variabile che conterrà il nuovo valore del numero di persone previste
		String nuovaDescrizione;
		int ctrlScelta=0;
		int ctrlNpp=0;
		int ctrlDes=0;
		
		
	
		System.out.println("Scegli quale campo modificare");
		System.out.println("[1] NUMERO DI PERSONE");
		System.out.println("[2] DESCRIZIONE ");
		System.out.println("[3] ANNULLA OPERAZIONE ");
		System.out.print("Inserisci scelta: ");
		do {
			ctrlScelta=0;
			try {
				sceltaModifica=Integer.parseInt(leggi2.nextLine());
			}catch(Exception e) {
				System.out.println("Valore non valido, inserisci un numero compreso tra 1 e 3");
				ctrlScelta=1;
			}
			if(sceltaModifica<0 || sceltaModifica>3) {
				ctrlScelta=1;
			}
			
		}while(ctrlScelta==1);
				
		switch(sceltaModifica) {
		case 1:{
			do {ctrlNpp=0;
				System.out.print("Inserisci il nuovo valore del numero di persone [Accettabile da 1 a 6 compresi]:");
				try {
					nuovoNpp=Integer.parseInt(leggi2.nextLine());
				}catch(Exception e) {
					System.out.println("Valore non valido, inserisci un numero [Accettabile da 1 a 6 compresi]");
					ctrlNpp=1;
				}
				if(checkNumPersone(nuovoNpp)==false) {
					ctrlNpp=1;
				}
			}while(ctrlNpp==1);
			this.numeroPersonePacchetto=nuovoNpp;
			this.modificaSulDatabase(1,db);
			break;
		}
		
		case 2:{
			do {	
				ctrlDes=0;
				System.out.print("Inserisci la nuova descrizione: ");
				nuovaDescrizione=leggi2.nextLine();
				if(checkStringa(nuovaDescrizione)==false) {
					ctrlDes=1;
				}
				
			}while(ctrlDes==1);
			this.descrizione=nuovaDescrizione;
			this.modificaSulDatabase(2,db);
			break;
		}
		}
	}
	
	
		
	
	/**
	 * Crea la query in base all'attributo da modificare scelto e chiama il metodo di modifica presente nella classe DataBaseAgenzia
	 * @param attributoDaModificare 1 se si vuole modificare il numero di persone del pacchetto, 2 per la descrizione
	 */
	private void modificaSulDatabase(int attributoDaModificare,DatabaseAgenzia db) {

		
		if(attributoDaModificare==1) {
			String updateNpp="UPDATE Pacchetto set numeroPersonePacchetto="+String.valueOf(this.numeroPersonePacchetto)+
					" where codice="+String.valueOf(this.codice)+";";
			db.modificaPacchettoSulDB(updateNpp);
		}else {
			String updateDescrizione="UPDATE Pacchetto set descrizione='"+this.descrizione+
					"' where codice="+String.valueOf(this.codice)+";";
			db.modificaPacchettoSulDB(updateDescrizione);
		}
		
	}
	


	/**
	 * Metodo statico per aggiungere all'HashSet dei codici il codice appena letto dal DB
	 * @param codice appena letto dal DB
	 */
	public static void aggiungiAlContainer(int codice) {
		containerCodici.add(codice);
	}
	
	
	
	
	
	
	@Override
	public int compareTo(Pacchetto p) {	
		if(this.getCodice()>p.codice) {
			return 1;
		}else if(this.getCodice()<p.codice){
			return -1;			
		}else {
			return 0;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codice;
		long temp;
		temp = Double.doubleToLongBits(costo);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((dataFineVacanza == null) ? 0 : dataFineVacanza.hashCode());
		result = prime * result + ((dataInizioVacanza == null) ? 0 : dataInizioVacanza.hashCode());
		result = prime * result + ((descrizione == null) ? 0 : descrizione.hashCode());
		result = prime * result + ((localita == null) ? 0 : localita.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + numeroPersonePacchetto;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pacchetto other = (Pacchetto) obj;
		if (codice != other.codice)
			return false;
		if (Double.doubleToLongBits(costo) != Double.doubleToLongBits(other.costo))
			return false;
		if (dataFineVacanza == null) {
			if (other.dataFineVacanza != null)
				return false;
		} else if (!dataFineVacanza.equals(other.dataFineVacanza))
			return false;
		if (dataInizioVacanza == null) {
			if (other.dataInizioVacanza != null)
				return false;
		} else if (!dataInizioVacanza.equals(other.dataInizioVacanza))
			return false;
		if (descrizione == null) {
			if (other.descrizione != null)
				return false;
		} else if (!descrizione.equals(other.descrizione))
			return false;
		if (localita == null) {
			if (other.localita != null)
				return false;
		} else if (!localita.equals(other.localita))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (numeroPersonePacchetto != other.numeroPersonePacchetto)
			return false;
		return true;
	}
	
	public void printAllInformationsGUI() {
		/** oggetto che permette di stabilire il formato di visualizzazione di una data**/
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		
		/*System.out.println("---------------------------------------------------------------------------------");
		System.out.println("Informazioni riguardanti il pacchetto selezionato");
		System.out.println("CODICE:\t\t\t\t"+this.getCodice());
		System.out.println("NOME:\t\t\t\t"+this.getNome());
		System.out.println("COSTO:\t\t\t\t"+this.getCosto()+'€');
		System.out.println("LOCALITA':\t\t\t"+this.getLocalita());
		System.out.println("DESCRIZIONE:\t\t\t"+this.getDescrizione());
		System.out.println("NUMERO PERSONE PACCHETTO:\t"+this.getNumeropersonepacchetto());
		System.out.println("TIPO PACCHETTO:\t\t\t"+this.getTipo());
		System.out.println("DATA INIZIO VACANZA:\t\t"+sdf.format(this.getDataInizioVacanza().getTime()));
		System.out.println("DATA FINE VACANZA:\t\t"+sdf.format(this.getDataFineVacanza().getTime()));
		System.out.println("---------------------------------------------------------------------------------");*/
	
	}
	
	
}
