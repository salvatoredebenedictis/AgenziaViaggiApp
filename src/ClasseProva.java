/*
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import pacchetto.Pacchetto;
import pacchetto.PacchettoNonValidoException;
import vendita.Vendita;
import vendita.VenditaException;
import dipendente.*;
import database.*;
public class ClasseProva {
	
	static DatabaseAgenzia db= new DatabaseAgenzia("root","diruvo00");
	static TreeSet<Pacchetto> containerPacchetti =new TreeSet<Pacchetto>();
	static ArrayList <Vendita> a = new ArrayList<Vendita>();
	
	public static void main (String[] args) {
		
	// inizio parte di prova della classe Pacchetto 
	 
	/* Parte di prova per Salvatore*/
		
	
	
	
	/* Prova crazione dei dipendenti Salvatore de Benedictis 
	
	try {
		//TEST creazione di un dipendente con attributi reali
		Dipendente dip = new Dipendente(1, "Mario", "Rossi", 50);
		dip.showAllInfo();
		///TEST VenditeAnnualiMAX < Vendite GiornaliereMAX
		Dipendente dip2 = new Dipendente(2, "Luigi", "Verdi", 2);
		dip2.showAllInfo();
		///TEST Cognome vuoto
		Dipendente dip3 = new Dipendente(3, "Luigi", "", 20);
		dip3.showAllInfo();
		///TEST Nome vuoto
		Dipendente dip4 = new Dipendente(2, "", "Verdi", 20);
		dip4.showAllInfo();
		///TEST Codice Superiore alla limite max
		Dipendente dip5 = new Dipendente(99991, "Luigi", "Verdi", 20);
		dip5.showAllInfo();
		
	} catch (DipendenteException e) {
	}	*/
	
	

	
	
		/*parte di prova per DiasparraGiuseppe*/
		
		//TEST 1 DiasparraGiuseppe  provo a istanziare un pacchetto con data non valida,  funziona perchè non lo fa creare
		
		/*
		Calendar dataErrataInizio=Calendar.getInstance();
		dataErrataInizio.set(2021,3,4);
		
		Calendar dataErrataFine=Calendar.getInstance();
		dataErrataFine.set(2021,3,4);
		
		try {
			Pacchetto p=new Pacchetto(1111,"Roma",150.50,"Vacanza a Roma",dataErrataInizio,dataErrataFine,3,"Una splendida vacanza a Roma");
			p.printAllInformations();
		}catch(PacchettoNonValidoException e) {}
			
		*/
		
		
		
		
		
		
		//TEST 2 DiasparraGiuseppe istanzio un pacchetto valido   ok funziona
		
		/*
		Calendar dataInizioValida=Calendar.getInstance();
		dataInizioValida.set(2021,1,15);
		
		Calendar dataFineValida=Calendar.getInstance();
		dataFineValida.set(2021,1,28);
		
		try {
			Pacchetto p=new Pacchetto(1111,"Roma",150.50,"Vacanza a Roma",dataInizioValida,dataFineValida,3,"Una splendida vacanza a Roma");
			p.printAllInformations();
		}catch(PacchettoNonValidoException e) {}
		*/
		
		
		
		
		
		
		
		
		//TEST 3  DiasparraGiuseppe istanzio un pacchetto con stesso codice del pacchetti p ok funziona perchè non lo fa creare
		
		/*
		try {
			Pacchetto p2=new Pacchetto(1111,"Parigi",199.99,"Vacanza Fantastica",dataInizioValida,dataFineValida,3,"Una splendida vacanza in Francia");
			p2.printAllInformations();
		}catch(PacchettoNonValidoException e) {}
		*/
		
		
		
		
		
		
		
		
		//TEST 4 DiasparraGiuseppe creo un altro pacchetto valido e aggiungo il pacchetto p e questo appena creato alla Struttura TreeSet
		//ok funziona perchè li stampa in ordine per codice pur avendoli inseriti in ordine diverso
		
		/*
		Calendar dataInizioValida=Calendar.getInstance();
		dataInizioValida.set(2021,1,15);
		
		Calendar dataFineValida=Calendar.getInstance();
		dataFineValida.set(2021,1,28);
		
		
		Set<Pacchetto> containerPacchetti =new TreeSet<Pacchetto>();
		
		try {
			Pacchetto p=new Pacchetto(1111,"Roma",150.50,"Vacanza a Roma",dataInizioValida,dataFineValida,3,"Una splendida vacanza a Roma");
			//p.printAllInformations();
			Pacchetto p3=new Pacchetto(3333,"Parigi",199.99,"Vacanza Fantastica",dataInizioValida,dataFineValida,3,"Una splendida vacanza in Francia");
			//p3.printAllInformations();
			
			System.out.println("STAMPA DI CONTAINERPACCHETTI");
			
			//aggiungo i pacchetti alla struttura containerpacchetti non in ordine, mi aspetto che da solo me li ordina per codice
			containerPacchetti.add(p3);
			containerPacchetti.add(p);
			
			for(Pacchetto pk:containerPacchetti) {
				pk.printAllInformations();
			}
			
		
		}catch(PacchettoNonValidoException e) {}
		*/
		
		
		
		
		
		
		//TEST 5 DiasparraGiuseppe istanzio un pacchetto valido e verifico che scrive su DB,  funziona
		
		/*
		Calendar dataInizioValida=Calendar.getInstance();
		dataInizioValida.set(2021,1,18);
		
		Calendar dataFineValida=Calendar.getInstance();
		dataFineValida.set(2021,1,28);
		
		try {
			Pacchetto p=new Pacchetto(1111,"Roma",150.50,"Vacanza a Roma",dataInizioValida,dataFineValida,3,"Una splendida vacanza a Roma");
			//p.printAllInformations();
		}catch(PacchettoNonValidoException e) {}
		*/
		
		
		
		
		

		
		
		
		
		
		//TEST PER LA CREAZIONE DI UNA VENDITA
		

		/*
		//INSERIMENTO DI UN CODICE VENDITA NON VALIDO
				try {
					Vendita v=new Vendita(11111,100.29,2222,1);
					v.PrintAllInformations();
				}catch(VenditaException er) {
					
				}
				
				//INSERIMENTO DI UN PREZZO VENDITA NON VALIDO
				try {
					Vendita v=new Vendita(1,10000.00,2222,1);
					v.PrintAllInformations();
				}catch(VenditaException er) {
					
				}
				
				//INSERIMENTO DI UN PACCHETTO NON VALIDO
				try {
					Vendita v=new Vendita(1,800.25,12,1);
					v.PrintAllInformations();
				}catch(VenditaException er) {
					
				}
				
				//INSERIMENTO DI UN DIPENDENTE NON VALIDO
				  try {
					Vendita v=new Vendita(1,800.25,2222,11111);
					v.PrintAllInformations();
				  }catch(VenditaException er) {
							
				  }
		*/
		
		
/*
		Calendar dataInizioValida=Calendar.getInstance();
		dataInizioValida.set(2021,1,18);
		
		Calendar dataFineValida=Calendar.getInstance();
		dataFineValida.set(2021,1,28);
		
		try {
			Pacchetto p=new Pacchetto(9999,"Roma",150.50,"Vacanza a Roma",dataInizioValida,dataFineValida,3,"Una splendida vacanza a Roma");
			p.printAllInformations();
		}catch(PacchettoNonValidoException e) {}
		  
		*/
		
		//INSERIMENTO VENDITA VALIDA E CARICAMENTO DATI SU DB
		/*
		  try {
			DatabaseAgenzia db = new DatabaseAgenzia();
			db.caricaContainerCodiciVendita();
			db.contaVendite(2);
			Vendita v=new Vendita(6,800.25,1111,2);
			db.aggiungiVendita(v);
			v.PrintAllInformations();
			
		  }catch(VenditaException er) {
					
		  }*/
		
	
		 /*
		try {
			DatabaseAgenzia db = new DatabaseAgenzia();
			
			
			db.contaVenditeAnnuali(1);
			db.contaVendite(1);
			
			Vendita v1=new Vendita(800.25,1111,1);
			db.aggiungiVendita(v1);
			
			
			ArrayList <Vendita> a = new ArrayList<Vendita>();
			db.elencoVendite(a);
			
			
			for(Vendita item:a) {
				item.PrintAllInformations();
			}
			
			
			
		}catch(Exception e) {
			
		}
		  */
		  
		
		
		
		
		//TEST 6 DiasparraGiuseppe scrittura pacchetto su DB  funziona
	/*	DatabaseAgenzia db = new DatabaseAgenzia(); 
		
		Calendar dataInizioValida=Calendar.getInstance();
		dataInizioValida.set(2021,1,20);
		
		Calendar dataFineValida=Calendar.getInstance();
		dataFineValida.set(2021,1,28);
		
		try {
			Pacchetto p=new Pacchetto(7630,"Palermo",150.50,"Vacanza a Palermo",dataInizioValida,dataFineValida,3,"Una splendida vacanza a Palermo");
			db.aggiungiPacchetto(p);

		}catch(PacchettoNonValidoException e) {}*/
	
		
		
		
		
		// TEST 7 DiasparraGiuseppe  carico i codici nel container all'avvio del programma e provo a istanziare un pacchetto con codice
		//uguale a uno gia presente sul DB. Mi deve il messaggio di errore di pacchetto non istanziabile intercettato nel costruttore
		
		//funziona
		
		/*
		DatabaseAgenzia db = new DatabaseAgenzia(); 
		
		db.caricaContainerCodiciPacchetto();
		
			
		Calendar dataInizioValida=Calendar.getInstance();
		dataInizioValida.set(2021,1,20);
		
		Calendar dataFineValida=Calendar.getInstance();
		dataFineValida.set(2021,1,28);
		
		try {
			Pacchetto p=new Pacchetto(7630,"Palermo",150.50,"Vacanza a Palermo",dataInizioValida,dataFineValida,3,"Una splendida vacanza a Palermo");
			db.aggiungiPacchetto(p);

		}catch(PacchettoNonValidoException e) {}
		*/
		
		
		
		
		//TEST 8 DiasparraGiuseppe stampa del catalogo
		/*
		
		DatabaseAgenzia db = new DatabaseAgenzia();
		
		TreeSet<Pacchetto> containerPacchetti =new TreeSet<Pacchetto>();
		
		db.getElencoPacchetti(containerPacchetti);
		
		Pacchetto.stampaCatalogoPacchetti(containerPacchetti);*/
		
		

		
		
		
		//TEST creazione di una istanza di DB e gestione dei vari stati  Salvatore de Benedictis 
		
		/*
		String user = "root";
		String pass = "root";
		DatabaseAgenzia db = new DatabaseAgenzia(user, pass); 

		int status = db.getStatus();
		
		switch(status) {
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
		case 6:System.out.println("Errore: Problema con l'SQL");
		break;
		default:System.out.println("Errore non identificato");
		break;
		}
		//TEST su dipendente con codice 1
		
		int codice = 1;
		Dipendente dip = db.cercaDipendente(codice);
		if (dip == null) System.out.println("Nessun dipendente con codice: "+ codice + ". Inserire un codice valido");
		else dip.showAllInfo();
		
		//TEST su dipendente con codice 2
		
		codice = 2;
		dip = db.cercaDipendente(codice);
		if (dip == null) System.out.println("Nessun dipendente con codice: "+ codice + ". Inserire un codice valido");
		else dip.showAllInfo();
		
		
		//TEST su dipendente con codice non esistente nel DB
		
		codice = 3246;
		dip = db.cercaDipendente(codice);
		if (dip == null) System.out.println("Nessun dipendente con codice: "+ codice + ". Inserire un codice valido");

		else dip.showAllInfo();
		*/
		
		
		
		
		
		/*
		 * DiasparraGiuseppe
		 * 
		String user = "root";
		String pass = "Zen_XK#67";
		DatabaseAgenzia db = new DatabaseAgenzia(user, pass);
		
		int status = db.getStatus();
		
		switch(status) {
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
		case 6:System.out.println("Errore: Problema con l'SQL");
		break;
		case 7: System.out.print("Errore nella creazione del catalogo dei Pacchetti");
		break;
		default:System.out.println("Errore non identificato");
		break;
		}
		
		Calendar dataInizioValida=Calendar.getInstance();
		dataInizioValida.set(2021,1,20);
		
		Calendar dataFineValida=Calendar.getInstance();
		dataFineValida.set(2021,1,28);
	
		try {
			Pacchetto p=new Pacchetto(1848,"Palermo",150.50,"Vacanza a Palermo",dataInizioValida,dataFineValida,3,"Una splendida vacanza a Palermo");
			db.aggiungiPacchetto(p);
			p.modificaPacchetto(db);

		}catch(Exception e) {
			e.printStackTrace();
		}*/
		
		
		//creaPacchetto();
		
		
		/*
		
		int status = db.getStatus();
		
		switch(status) {
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
		case 6:System.out.println("Errore: Problema con l'SQL");
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
		default:System.out.println("Errore non identificato");
		break;
		}
		
		creaVendita();*/
	
	//}
	
	
	
	
	
	
	
	/**
	 * Metodo di creazione di una istanza di pacchetto con valori inseriti in input dall'utente.
	 * Si richiama il costruttore di Pacchetto e i controlli sulla validità dei dati è rimandato ad esso.
	 * @author DiasparraGiuseppe
	 */
/*
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
		System.out.println("1)Inserisci codice: ");
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
		
		
		
		System.out.println("3)Inserisci costo: ");		
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
		
		
		
		System.out.println("6)Inserisci data di fine vacanza: ");		
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
		
		
		
		
		
		System.out.println("7)Inserisci numero persone pacchetto: ");		
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
			containerPacchetti.add(p);
		} catch (PacchettoNonValidoException e) {
			System.out.println("Pacchetto non istanziabile, i valori inseriti non sono validi");
			controllo=1;
		}
		
		
		
		if(controllo==0) {
			System.out.println("Pacchetto creato con successo");
		}
		
		System.out.println("--------------------------------------------------------------------");

		
	}*/
	
	/**
	 * Metodo che permette di inserire una nuova vendita
	 * @author michele di ruvo
	 */
	/*
	private static void creaVendita() {
		//inizializzo la lettura da tastiera
		Scanner leggi=new Scanner(System.in);
	
		
		db.caricaContainerCodiciPacchetto();
		double prezzoVendita=0;
		int codicePacchetto;
		
		
		
		System.out.println("Inserisci il prezzo di vendita");
		prezzoVendita=leggi.nextDouble();
		System.out.println("Inserisci il codice del pacchetto venduto");
		codicePacchetto=leggi.nextInt();
		db.caricaContainerCodiciPacchetto();
		
		//controllo che il codice inserito sia esistente
		if((Pacchetto.checkCodice(codicePacchetto))==false){
			try {
				db.contaVenditeAnnuali(1);
				db.contaVendite(1);
				//istanzio una vendita
				Vendita v= new Vendita(prezzoVendita,codicePacchetto,1);
				db.aggiungiVendita(v);
			} catch (VenditaException e) {
				System.out.print("Valori di vendita non validi");
			}
			}else {
				System.out.println("Il pacchetto non esiste");
		}
	}
	*/
//}

