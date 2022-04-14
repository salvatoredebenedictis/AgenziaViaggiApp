/**
 * 
 */
package database;
import java.awt.Component;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.Date;
import dipendente.*;
import pacchetto.Pacchetto;
import pacchetto.PacchettoNonValidoException;
import vendita.Vendita;

/**
 * Classe che implementa la connessione con il database
 * @author salvatore de Benedictis
 * @author DiasparraGiuseppe
 *
 */
public class DatabaseAgenzia {
	
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String dbName = "cs_it_2021_gruppo10";
	static final String host = "localhost";
	static final int port = 3306;
	static final String url = "jdbc:mysql://"+ host + ":" + port + "/" + dbName + "?serverTimezone=UTC";
	
	/// Credenziali del Database
	String USER ;
	String PASS ;

	
	Connection conn = null;
	public static Statement stmt = null;
	public static ResultSet rs;
	/**
	 * Valori di status:
	 * 0 Stato di default :
	 * 1 Funzionante
	 * 2 Errore: Problema con il jar
	 * 3 Errore: Problema con la connessione con il  DB (url, user o password errati)
	 * 4 Errore: Problema con al connessione dello stmt
	 * 5 Errore: Problema con l'inserimento del dipendente
	 * 6 Errore: Problema con getDipendente
	 * 7 Errore: Problema nella creazione del catalogo dei pacchetti
	 * 8 Errore: Problema nella creazione della vendita
	 * 9 Errore: Problema nella creazione dell'elenco delle vendite
	 * 10 Errore: Problema nel conteggio delle vendite giornaliere
	 * 11 Errore: Problema nel conteggio delle vendite annuali
	 * 12 Errore: Problema con la insert dei dipendenti
	 * 13 Errore: Problema con la insert dei pacchetti
	 */
	int status = 0;
	
	/**
	 * 
	 * @param USER username dell'utente del db
	 * @param PASS password dell'utente del db
	 * 
	 * @author Salvatore de Benedictis
	 */
	public DatabaseAgenzia (String USER, String PASS) {
		status = 1;
		///Caricamento del JDBC Driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			status = 2;
			return;
		}
		
		///Connessione con il DB
		try {
			conn = DriverManager.getConnection(url, USER, PASS);
		} catch (SQLException e) {
			status = 3;
			return;
		}
		
		///Creazione dello stmt
		
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			status = 4;
			return;
		}
		
	}
	
	/**
	 * Funzione che ritorna lo stato del DB
	 * @return lo stato del Database
	 * @author Salvatore de Benedictis
	 */
	public int getStatus() {
		return this.status;
	}
	
	/**
	 * Funzione che permette di ottenere un dipendente dal db
	 * @return un oggetto di tipo dipendente
	 * @author salvatore de Benedictis
	 */
	public Dipendente getDipendente() {
		Dipendente dipendente;
		try {
			dipendente = new Dipendente(rs.getInt("Codice"), rs.getString("Nome"), rs.getString("Cognome"),
					rs.getInt("VenditeAnnualiMAX"));
			return dipendente;
		} catch (DipendenteException e) {
			status = 5;
			return null;
		} catch (SQLException e) {
			status = 6;
			return null;
		}
	}
	
	/**
	 * Funzione che permette di cercare se esiste un dipendente dal DB, se esiste lo ritorna altrimenti ritorna null
	 * @param codice codice identificativo del dipendente da cercare
	 * @return un oggetto di tipo dipendente se lo trova altrimenti null
	 * @author salvatore de Benedictis
	 */
	public Dipendente cercaDipendente(int codice) {
		String query = "SELECT * FROM Dipendente WHERE codice = " + String.valueOf(codice);
		try {
			rs = stmt.executeQuery(query);
			rs.next();
		} catch (SQLException e) {
			status = 6;
			return null;
		}
		
		Dipendente dipendente = getDipendente();
		return dipendente;
	}
	
	

	
	/**
	 * Metodo che permette di ottenere tutte le informazioni di un pacchetto dal DB
	 * Crea un oggetto di tipo pacchetto utilizzando il secondo costruttore, cioè quello per i pacchetti creati precedentemente
	 * @return istanza di Pacchetto con i dati letti dal DB o null se ci sono problemi con il DB
	 * @author DiasparraGiuseppe
	 */
	public Pacchetto getPacchetto() {
		Pacchetto pacchetto;
		try {
			int cod=rs.getInt(1);
			String loc=rs.getString(2);
			double cost=rs.getDouble(3);
			String nome=rs.getString(4);
			
			Date dataIn=rs.getDate(5);
			Calendar dataInizio= Calendar.getInstance();
			dataInizio.setTime(dataIn);
			
			Date dataFin=rs.getDate(6);
			Calendar dataFine= Calendar.getInstance();
			dataFine.setTime(dataFin);
			
			int numpp=rs.getInt(7);
			String descr=rs.getString(8);
			
			pacchetto= new Pacchetto(descr,numpp,dataFine,dataInizio,nome,cost,loc,cod);
			return pacchetto;
		} catch (SQLException e) {
			status = 6;
			return null;
		}
	}
	
	
	
	/**
	 * Metodo che permette la ricerca tramite codice di un pacchetto vacanza nel DB
	 * @param codice del pacchetto che si sta cercando
	 * @return istanza di pacchetto o null se non esiste alcun pacchetto col codice specificato
	 * @author DiasparraGiuseppe
	 */
	public Pacchetto cercaPacchetto(int codice) {
		String query = "SELECT * FROM Pacchetto WHERE codice = " + String.valueOf(codice);
		try {
			rs = stmt.executeQuery(query);
			rs.next();
		} catch (SQLException e) {
			status = 6;
			return null;
		}
		
		Pacchetto pacchetto = getPacchetto();
		return pacchetto;
	}
	
	
	
	/**
	 * Metodo che permette di aggiungere al DB il pacchetto vacanza appena creato. 
	 * Non richiede ulteriori controlli perchè quelli necessari sono stati fatti nel costruttore al momento dell'istanziazione
	 * @param p istanza di pacchetto
	 * @author DiasparraGiuseppe
	 */
	public void aggiungiPacchetto(Pacchetto p) {
	
		try {
			
			/** attribuzione valori del pacchetto da inserire*/
			int cod=p.getCodice();
			String loc=p.getLocalita();
			Double cost=p.getCosto();
			String nom=p.getNome();
			java.sql.Date dataIn=new java.sql.Date(p.getDataInizioVacanza().getTimeInMillis());
			java.sql.Date dataFin=new java.sql.Date(p.getDataFineVacanza().getTimeInMillis());
			int npp=p.getNumeropersonepacchetto();
			String descr=p.getDescrizione();
			
			/** insert da eseguire sul DB*/
			String insert="insert into Pacchetto values ("+String.valueOf(cod)+",'"+loc+"',"+String.valueOf(cost)+",'"+nom+"','"+dataIn+"','"+dataFin+"',"+String.valueOf(npp)+",'"+descr+"','STANDARD')";
					
			/** esecuzione dell'istruzione. non usare executeQuery */
			stmt.executeUpdate(insert);
			

		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	/**
	 * Metodo che conta quanti sono i pacchetti presenti sul DB
	 * @return numCodiciPresenti numero dei pacchetti presenti sul DB
	 * @author DiasparraGiuseppe
	 */
	public int contaCodiciPacchettoPresenti() {
		/** effettuo conteggio di quanti sono i pacchetti presenti*/
		
		String queryConteggio = "SELECT count(codice) FROM Pacchetto";
		int numCodiciPresenti=-1;
		
		
		try {
			rs = stmt.executeQuery(queryConteggio);
			rs.next();
			numCodiciPresenti=rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return numCodiciPresenti;
	}
	
	
	
	
	/**
	 * Valorizza l'HashSet che contiene tutti i codici all'avvio del programma
	 * @author DiasparraGiuseppe
	 */
	public void caricaContainerCodiciPacchetto() {
		
		int numCodiciPresenti=contaCodiciPacchettoPresenti();
		
		if(numCodiciPresenti!=-1) {
			
			/** ottengo tutti i codici dei pacchetti presenti e li inserisco nell'HashSet*/	
			String query = "SELECT codice FROM Pacchetto";
			try {
				rs = stmt.executeQuery(query);
				
				for(int i=0;i<numCodiciPresenti;i++) {
					rs.next();
					Pacchetto.aggiungiAlContainer(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}else {
			System.out.print("Errore nella connessione al DB");
		}
	}
	
	
	/**
	 * Metodo che permette di ottenere il catalogo dei pacchetti.
	 * Valorizza il TreeSet immettendo i pacchetti letti dal Db e istanziati al momento
	 * @param catalogo il TreeSet creato nella classe principale (vuoto)
	 * @author DiasparraGiuseppe
	 */
	public void getElencoPacchetti(TreeSet<Pacchetto> catalogo) {
		
		String query ="select * from Pacchetto";
		catalogo.clear();	//elimino tutti i pacchetti presenti precedentemente per non avere problemi di stesso codice
		
		try {
			
			rs =stmt.executeQuery(query);
			while(rs.next()) {
				
				int cod=rs.getInt(1);
				String loc=rs.getString(2);
				double cost=rs.getDouble(3);
				String nome=rs.getString(4);
				
				Date dataIn=rs.getDate(5);
				Calendar dataInizio= Calendar.getInstance();
				dataInizio.setTime(dataIn);
				
				Date dataFin=rs.getDate(6);
				Calendar dataFine= Calendar.getInstance();
				dataFine.setTime(dataFin);
				
				int numpp=rs.getInt(7);
				String descr=rs.getString(8);
				
				Pacchetto p= new Pacchetto(descr,numpp,dataFine,dataInizio,nome,cost,loc,cod);
				catalogo.add(p);	
				
			}
		}catch(SQLException e) {
			status=7;
			catalogo=null;
		}	
	}
	
	/**
	 * Effettua la modifica sul Db 
	 * @param update query di aggiornamento creata precedentemente
	 * @author DiasparraGiuseppe
	 */
	public void modificaPacchettoSulDB(String update) {
		try {
			rs.next();
			stmt.executeUpdate(update);
		}catch(SQLException e) {
			status=7;
		}
		System.out.println("Modifica effettuata con successo");
	}

	
	
	/**
	 * @author michele di ruvo
	 * Metodo che permette di aggiungere al databse una istanza di una vendita
	 * I controlli sulla validità della vendita vengono effettuati nel costruttore
	 * @param v
	 */
	public void aggiungiVendita(Vendita v) {
		
		try {
			java.sql.Date data=new java.sql.Date(v.getDataVendita().getTimeInMillis());
			double prezzo=v.getPrezzoVendita();
			int codiceP=v.getPacchettoVenduto();
			int codiceD=v.getDipendenteVendita();
			
			String insert="insert into vendita (dataVendita,prezzoVendita,codicePacchetto,codiceDipendente) values ('"+data+"',"+String.valueOf(prezzo)+","+String.valueOf(codiceP)+","+String.valueOf(codiceD)+")";
			/**esecuzione query**/
			stmt.executeUpdate(insert);
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			status=8;
		}

	}
	

	
	/**
	 * @author michele di ruvo
	 * Metodo utilizzato per effettuare il conteggio delle vendite giornaliere del dipendente
	 * @param codice
	 */
	public void contaVendite(int codice) {
		int contaVendite=0;
		
		String query ="select count(codiceDipendente) as contatore from vendita where dataVendita=curdate() and codiceDipendente="+codice+";";
		
		try {
			/**eseguo query**/
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				contaVendite=rs.getInt("contatore");
			}
			/**passo il valore ottenuto al metodo che andrà aa verificare se la vendita potrà essere effettuata**/
			Vendita.Dativendita(contaVendite);
			Dipendente.VenditeGiornaliere(contaVendite);
		}catch (SQLException e) {
			e.printStackTrace();
			status=10;
		}
		
		
		
		
	}
	
	/**
	 * @author michele di ruvo
	 * Metodo che restituisce una stampa sotto forma di elenco di tutte le vendite presenti su database
	 * @param v
	 */
	public void  elencoVendite(ArrayList<Vendita> v) {
		
		
		String query =" select codiceVendita,dataVendita,prezzoVendita,codicePacchetto,codiceDipendente from vendita order by codiceVendita;";
		v.clear();
		
		try {
			/**esecuzione query**/
			rs =stmt.executeQuery(query);
			while(rs.next()) {
				int codice=rs.getInt("codiceVendita");
				Date data=rs.getDate("dataVendita");
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Calendar dataVendita= Calendar.getInstance();
				dataVendita.setTime(data);
				double prezzo=rs.getDouble("prezzoVendita");
				int codicePacchetto=rs.getInt("codicePacchetto");
				int codiceDipendente=rs.getInt("codiceDipendente");
				/**creazione di un nuovo oggetto usando un costruttore esente da controlli**/
				Vendita item = new Vendita(codice,codicePacchetto,prezzo,codiceDipendente);
				item.setDataVendita(dataVendita);
				/**aggiunta dell'oggetto all'arrayList**/
				v.add(item);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			status=9;
		}
		
		
		
	}
	
	/**
	 * @author michele di ruvo
	 * Metodo utilizzato per il conteggio delle vendite annuali per un determinato dipendente
	 * @param codice
	 */
	public void contaVenditeAnnuali(int codice) {
		
		int contaVendite=0;
		int maxVendite=0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Calendar dataInizio=Calendar.getInstance();
		Calendar dataFine=Calendar.getInstance();
		Date d= new Date();
		int year=d.getYear()+1900;
		dataInizio.set(year, 0, 1);
		dataFine.set(year, 11, 31);
	
		String query ="select count(codiceDipendente) as contatore from vendita where dataVendita between '"+sdf.format(dataInizio.getTime())+"' and '"+sdf.format(dataFine.getTime())+"' and codiceDipendente="+codice+";";
		try {
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				contaVendite=rs.getInt("contatore");
				
			}
			/**passaggio del valore della query al metodo che controllerà la validità dei dati passati come parametro**/
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		String query2="select VenditeAnnualiMax from dipendente where codice="+codice+";";
		try {
			rs = stmt.executeQuery(query2);
			while(rs.next()) {
				maxVendite=rs.getInt(1);
				
			}
			
			/**passaggio del valore della query al metodo che controllerà la validità dei dati passati come parametro**/
			Vendita.DatiVenditaAnno(contaVendite,maxVendite);
			Dipendente.VenditeAnnuali(contaVendite);
		}catch (SQLException e) {
			e.printStackTrace();
			status=11;
		}
		
	}
	

	
}
