/**
 * 
 */
package agenzia;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.TreeSet;

import database.*;
import pacchetto.*;
import dipendente.*;
import vendita.*;

/**
 * @author salvatore de Benedictis
 *
 */
public class AgenziaApp {
	

	 static TreeSet<Pacchetto> catalogoPacchetti ; // catalogo dei pacchetti 
	 static ArrayList <Vendita> catalogoVendite; // catalogo delle vendite
	 static Scanner leggi = new Scanner(System.in);
	 static DatabaseAgenzia db;
	 static Dipendente dipendente;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		catalogoPacchetti= new TreeSet<Pacchetto>();
		catalogoVendite = new ArrayList<Vendita>();	
		
		
		System.out.println("Benvenuto in Agenzia Viaggi \n");
		
		/** credenziali db **/
		
		String user ;
		String password;
		
		int statusDB = 0;
		do {
			System.out.println("Inserisci l'username del DB: ");
			user = leggi.nextLine();
			System.out.println("Inserisci la password del DB: ");
			password = leggi.nextLine();
			
			db = new DatabaseAgenzia(user,password);
			statusDB = db.getStatus();
			stampaStatusDB(statusDB);
			
			/** Carico il catalogo dei pacchetti dal db */
			if(db.getStatus()==1) {
				
				db.getElencoPacchetti(catalogoPacchetti);
				db.caricaContainerCodiciPacchetto();
			}
			
			}
		while (statusDB != 1);
		int controllo = 0;
		do {
			controllo = 0;
			int codice;
			System.out.println("Inserisci il codice del dipendente: ");
			try {
				codice = Integer.parseInt(leggi.nextLine());
				dipendente = db.cercaDipendente(codice);
				if(dipendente == null) {//se è null il dipendente con quel codice non esiste
					System.out.println("Codice dipendente non trovato! Inserire un codice dipendente valido ");	
					controllo = 1;
				}
				
			}catch(Exception e) {
				System.out.println("Valore non valido, inserisci un numero");
				controllo=1;
			}
		}
		while (controllo==1);
		
		
		int scelta = 0;
		int controllo2=0;
		
		do {		
			do {
				System.out.println("------------------------------------------------------------------------------------------------\n");
				System.out.println("Effettuare una scelta");
				System.out.println("[1] Visualizza Informazioni Dipendente");//ok
				System.out.println("[2] Inserisci Un Nuovo Pacchetto Vacanza");//ok
				System.out.println("[3] Visualizza catalogo Pacchetti");//ok
				System.out.println("[4] Modifica Pacchetto");
				System.out.println("[5] Visualizza Storico Vendite");
				System.out.println("[6] Effettua Una Nuova Vendita");
				System.out.println("[7] Termina Programma");//ok
				System.out.print("Inserisci scelta: ");
				
				do {
					controllo2=0;
					try {
						scelta=Integer.parseInt(leggi.nextLine());
					}catch(Exception e) {
						System.out.println("Valore non valido, inserisci un numero");
						controllo2=1;
					}				
				}while(controllo2==1);

			}while(scelta<1 || scelta>7);
			
			switch(scelta) {
			case 1:{
				System.out.println("Informazione del dipendente \n");
				db.contaVendite(dipendente.getCodiceDipendente());
				db.contaVenditeAnnuali(dipendente.getCodiceDipendente());
				dipendente.showAllInfo();
			}
			break;
			case 2:{
				db.getElencoPacchetti(catalogoPacchetti);
				db.caricaContainerCodiciPacchetto();
				creaPacchetto();
			}
			break;
			case 3:{
				db.getElencoPacchetti(catalogoPacchetti);
				db.caricaContainerCodiciPacchetto();
				stampaCatalogoPacchetti(catalogoPacchetti);
			}
			break;
			case 4:{
				int controlloCod=0;
				int codicePacchettoCercato;
				
				db.getElencoPacchetti(catalogoPacchetti);
				db.caricaContainerCodiciPacchetto();
				System.out.print("Inserisci codice del pacchetto da modificare:");
				
				do {
					controlloCod=0;
					try {
						codicePacchettoCercato= Integer.parseInt(leggi.nextLine());
						Pacchetto p=db.cercaPacchetto(codicePacchettoCercato);
						if(p!=null) {
							p.modificaPacchetto(db);
						}else {
							System.out.println("Non esiste alcun pacchetto col codice inserito");
						}
					}catch(Exception e) {
						System.out.println("Valore non valido, inserisci un numero");
						controlloCod=1;
					}
				}while(controlloCod==1);
				
				
				

				db.getElencoPacchetti(catalogoPacchetti);
				db.caricaContainerCodiciPacchetto();
				
			}
			break;
			case 5:{
				//System.out.println("Visualizza Storico vendite");
				stampaCatalogoVendite(catalogoVendite);
			}
			break;
			case 6:{
				//System.out.println("Effettua Vendita");
				creaVendita();
			}
			break;
			case 7: {
				System.out.print("Terminazione programma...");
				System.exit(0);
			}
			break;
			default:System.out.println("Scelta non valida");
			break;
			}
		}
		while(scelta !=7);
	

	}
	
	/**
	 * Procedura void che prende in input lo stato del db e ne restituisce il messaggio corrispondente allo stato
	 * @param stato del DB
	 */
	private static void stampaStatusDB(int stato) {
		switch(stato) {
		case 0: System.out.println("DB in stato di default");
		break;
		case 1:System.out.println("Connessione avvenuta con successo!\n");
		break;
		case 2:System.out.println("Errore: Problema con il jar");
		break;
		case 3:System.out.println("Errore: Problema con la connessione con il  DB (URL, USER, PASSWORD errati) ");
		break;
		case 4:System.out.println("Errore: Problema con al connessione dello stmt");
		break;
		case 5:System.out.println("Errore: Problema con l'inserimento del dipendente");
		break;
		case 6:System.out.println("Errore: Problema con getDipendente");
		break;
		case 7: System.out.print("Errore nella creazione del catalogo dei Pacchetti");
		break;
		case 8:System.out.println("Errore nella creazione di una vendita");
		break;
		case 9:System.out.println("Errore nella creazione dell'elenco di vendite");
		break;
		case 10:System.out.println("Errore nel conteggio delle vendite giornaliere");
		break;
		case 11:System.out.println("Errore nel conteggio delle vendite annuali");
		break;
		case 12:System.out.println("Errore sql nell'inserimento dei dipendenti");
		break;
		case 13:System.out.println("Problema con la insert dei pacchetti"); 
		break;
		default:System.out.println("Errore non identificato");
		break;
		}
		
	}
	
	/**
	 * Metodo di creazione di una istanza di pacchetto con valori inseriti in input dall'utente.
	 * Si richiama il costruttore di Pacchetto e i controlli sulla validità dei dati è rimandato ad esso.
	 * @author DiasparraGiuseppe
	 */
	private static void creaPacchetto() {
		
		Scanner leggi=new Scanner(System.in);
		
		int codice=0;
		String localita;									/// località di destinazione del pacchetto
		double costo=0;										///prezzo di listino del pacchetto, potrà differire da quello di vendita
		String nome;										/// nome esplicativo del pacchetto
		Calendar dataInizioVacanza= Calendar.getInstance();	/// data di inizio della vacanza
	    Calendar dataFineVacanza=Calendar.getInstance();	/// data di fine della vacanza
		int numeroPersonePacchetto=0;							/// numero di persone comprese nella vacanza
		String descrizione;									/// descrizione del pacchetto
		
		int controllo=0;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		System.out.println("--------------------------------------------------------------------");
		System.out.println("CREAZIONE NUOVO PACCHETTO ");
		System.out.println("1)Inserisci codice [nel formato xxxx]: ");
		do {
			controllo=0;
			try {
				codice=Integer.parseInt(leggi.nextLine());
			}catch(Exception e) {
				System.out.println("Valore non valido, inserisci un numero");
				controllo=1;
			}				
		}while(controllo==1);
		
		
		
		System.out.println("2)Inserisci localita: ");
		localita=leggi.nextLine();
		
		
		
		System.out.println("3)Inserisci costo [Accettabile da 80€ in su, utilizzare il . per separare i centesimi]: ");		
		do {
			controllo=0;
			try {
				costo=Double.parseDouble(leggi.nextLine());
			}catch(Exception e) {
				System.out.println("Valore non valido, inserisci un numero");
				controllo=1;
			}			
		}while(controllo==1);
		
		
		
		System.out.println("4)Inserisci nome: ");
		nome=leggi.nextLine();
		
		
		System.out.println("Sezione Date -INFO ");
		System.out.println("* Data di inzio vacanza minima accettabile: Data di domani.");
		System.out.println("* Data di fine vacanza massima accettabile: Data di oggi + un mese.");
		System.out.println("5)Inserisci data di inizio vacanza nel formato gg/mm/aaaa: ");
		do {
			controllo=0;
			String dataLettaIn=leggi.nextLine();	
			try {
				Date dataAcquisitaIn= sdf.parse(dataLettaIn);
				dataInizioVacanza=Calendar.getInstance();
				dataInizioVacanza.setTime(dataAcquisitaIn);
	
			}catch (ParseException e) {
				System.out.println("Valore non valido, inserisci una data nel formato gg/mm/aaaa");
				controllo=1;
			}
			catch(Exception e) {
				System.out.println("Valore non valido, inserisci una data nel formato gg/mm/aaaa");
				controllo=1;	
			}	
		}while(controllo==1);
		
		
		
		System.out.println("6)Inserisci data di fine vacanza nel formato gg/mm/aaaa: ");		
		do {
			controllo=0;
			String dataLettaFin=leggi.nextLine();	
			try {
				Date dataAcquisitaFin= sdf.parse(dataLettaFin);
				dataFineVacanza=Calendar.getInstance();
				dataFineVacanza.setTime(dataAcquisitaFin);
	
			}catch (ParseException e) {
				System.out.println("Valore non valido, inserisci una data nel formato gg/mm/aaaa");
				controllo=1;
			}
			catch(Exception e) {
				System.out.println("Valore non valido, inserisci una data nel formato gg/mm/aaaa");
				controllo=1;	
			}	
		}while(controllo==1);
		
		
		
		
		
		System.out.println("7)Inserisci numero persone pacchetto [Accettabile da 1 a 6 compresi]: ");		
		do {
			controllo=0;
			try {
				numeroPersonePacchetto=Integer.parseInt(leggi.nextLine());
			}catch(Exception e) {
				System.out.println("Valore non valido, inserisci un numero");
				controllo=1;
			}
		}while(controllo==1);
			
		System.out.println("8)Inserisci descrizione: ");
		descrizione=leggi.nextLine();
		
		
		
		
		try {
			Pacchetto p= new Pacchetto(codice,localita,costo,nome,dataInizioVacanza,dataFineVacanza,numeroPersonePacchetto,descrizione);
			catalogoPacchetti.add(p);
			db.aggiungiPacchetto(p);
		} catch (PacchettoNonValidoException e) {
			System.out.println("Pacchetto non istanziabile, i valori inseriti non sono validi");
			controllo=1;
		}
		
		if(controllo==0) {
			System.out.println("Pacchetto creato con successo");
		}
		
		System.out.println("--------------------------------------------------------------------");

		
	}
	
	/**
	 * Stampa di tutti i pacchetti del catalogo prelevati precedentemente dal DB
	 * @param catalogo insieme di tutti i pacchetti prelevati dal DB, è un TreeSet di pacchetti
	 */
	public static void stampaCatalogoPacchetti(TreeSet<Pacchetto> catalogo) {
		if(catalogo.isEmpty())System.out.println("Catalogo pacchetti vuoto ");
		else {
			System.out.println("STAMPA DEL CATALOGO DEI PACCHETTI");
			for (Pacchetto element : catalogo) {
			element.printAllInformations();
			}
		}
	}
	
	/**
	 * Stampa tutto lo storico delle vendite inserito su DB
	 * @author michele di ruvo
	 * @param catalogo
	 */
	public static void stampaCatalogoVendite(ArrayList<Vendita> catalogo) {
		db.elencoVendite(catalogo);
		if(catalogo.isEmpty()) {
			System.out.println("Catalogo vendite vuoto ");
		}
		else {
			System.out.println("STAMPA DEL CATALOGO DELLE VENDITE");
			
			for(Vendita item:catalogo) {
				item.PrintAllInformations();
			}
		}
	}
	
	
	/**
	 * Metodo che permette di inserire una nuova vendita
	 * @author michele di ruvo
	 */
	private static void creaVendita() {
		/**inizializzo la lettura da tastiera**/
		Scanner leggi=new Scanner(System.in);
	
		
		db.caricaContainerCodiciPacchetto();
		double prezzoVendita = 0;
		int codicePacchetto = 0;
		int controllo=0;
		
		
		
		System.out.println("Inserisci il codice del pacchetto da vendere [il codice è sempre un numero a 4 cifre: xxxx]");
		do {
			controllo=0;
			try {
				codicePacchetto=Integer.parseInt(leggi.nextLine());
			}catch(Exception e) {
				System.out.println("Valore non valido, inserisci un numero");
				controllo=1;
			}			
		}while(controllo==1);
		System.out.println("Inserisci il prezzo di vendita [Accettabile da 80€ in su, utilizzare il . per separare i centesimi, il prezzo massimo è 9999.99]");
		do {
			controllo=0;
			try {
				prezzoVendita=Double.parseDouble(leggi.nextLine());
			}catch(Exception e) {
				System.out.println("Valore non valido, inserisci un numero");
				controllo=1;
			}			
		}while(controllo==1);
		
		db.caricaContainerCodiciPacchetto();
		
		/**controllo che il codice inserito sia esistente**/
		if((Pacchetto.checkCodice(codicePacchetto))==false){
			try {
				db.contaVenditeAnnuali(dipendente.getCodiceDipendente());
				db.contaVendite(dipendente.getCodiceDipendente());
				/**istanzio una vendita**/
				Vendita v= new Vendita(prezzoVendita,codicePacchetto,dipendente.getCodiceDipendente());
				catalogoVendite.add(v);
				db.aggiungiVendita(v);
			} catch (VenditaException e) {
				System.out.println("Valori di vendita non validi");
			}
			}else {
				System.out.println("Il pacchetto non esiste");
		}
	}
}
