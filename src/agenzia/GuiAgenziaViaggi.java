package agenzia;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.*;
import dipendente.*;
import vendita.*;
import pacchetto.*;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Window.Type;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JTextPane;
import java.awt.SystemColor;
import javax.swing.JTable;
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;

public class GuiAgenziaViaggi extends JFrame {

	private JPanel contentPane;
	private JTextPane textCodice;
	private JTextPane textNome;
	private JTextPane textCognome;
	private JTextPane textVenditeAnnuali;
	private JTextPane textVenditeAnnualiDisponibili;
	private JTextPane textVenditeGiornaliereDisponibili;
	private JPanel panelDipendente;
	private JComboBox comboBox;
	
	static TreeSet<Pacchetto> containerPacchetti =new TreeSet<Pacchetto>();
	static ArrayList <Vendita> containerVendite = new ArrayList<Vendita>();
	private static Set <Integer> codiciPacchetto= new HashSet<Integer>(); 
	private JTextPane textCostoPacchetto;
	private JTextPane textNomePacchetto;
	private JTextPane textDataInizioPacchetto;
	private JTextPane textDataFinePacchetto;
	private JTextPane textNPersone;
	private JTextPane textDescrizione;
	private JTable table;
	



	/**
	 * Create the frame.
	 */
	public GuiAgenziaViaggi(Dipendente dipendente, DatabaseAgenzia db) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\User\\Salvarki\\Desktop\\Studio\\Programmazione\\icons\\globe.png"));
		setTitle("Agenzia Viaggi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1230, 841);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
			
		JButton btnNewButton = new JButton("Modifica Pacchetto");
		btnNewButton.setBounds(17, 213, 220, 26);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnNewButton_1 = new JButton("Visualizza Catalogo Vendite");
		btnNewButton_1.setBounds(23, 283, 220, 26);
		
		JLabel lblPacchetto = new JLabel("Pacchetto");
		lblPacchetto.setBounds(17, 99, 226, 26);
		lblPacchetto.setForeground(Color.ORANGE);
		lblPacchetto.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));
		lblPacchetto.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblVendita = new JLabel("Vendita");
		lblVendita.setBounds(17, 251, 109, 26);
		lblVendita.setForeground(Color.ORANGE);
		lblVendita.setHorizontalAlignment(SwingConstants.LEFT);
		lblVendita.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));
		
		JLabel lblDipe = new JLabel("Dipendente");
		lblDipe.setBounds(17, 17, 201, 26);
		lblDipe.setHorizontalAlignment(SwingConstants.LEFT);
		lblDipe.setForeground(Color.ORANGE);
		lblDipe.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));
		
		JButton btnNewButton_2 = new JButton("Informazioni ");
		btnNewButton_2.setBounds(17, 55, 220, 26);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textCodice.setText(String.valueOf(dipendente.getCodiceDipendente()));
				textNome.setText(dipendente.getNome());
				textCognome.setText(dipendente.getCognome());
				textVenditeAnnuali.setText(String.valueOf(dipendente.getVenditeAnnualiMAX()));
				db.contaVendite(dipendente.getCodiceDipendente());
				db.contaVenditeAnnuali(dipendente.getCodiceDipendente());
				textVenditeAnnualiDisponibili.setText(String.valueOf(dipendente.getVenditeAnnualiMAX()- dipendente.venditeGiornaliere ));
				textVenditeGiornaliereDisponibili.setText(String.valueOf(dipendente.VenditeGiornaliereMAX- dipendente.venditeAnnuali));
				
				
			}
		});
		
		panelDipendente = new JPanel();
		panelDipendente.setBounds(249, 17, 417, 260);
		panelDipendente.setBackground(SystemColor.controlShadow);
		
		JLabel lblDipCodice = new JLabel("Codice");
		lblDipCodice.setBounds(12, 43, 81, 19);
		lblDipCodice.setForeground(SystemColor.textHighlight);
		lblDipCodice.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		lblDipCodice.setHorizontalAlignment(SwingConstants.LEFT);
		
		textCodice = new JTextPane();
		textCodice.setBounds(253, 37, 144, 25);
		textCodice.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		textCodice.setBackground(SystemColor.text);
		textCodice.setForeground(SystemColor.textHighlight);
		
		JLabel lblNomeDip = new JLabel("Nome");
		lblNomeDip.setBounds(12, 74, 81, 21);
		lblNomeDip.setHorizontalAlignment(SwingConstants.LEFT);
		lblNomeDip.setForeground(SystemColor.textHighlight);
		lblNomeDip.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		
		textNome = new JTextPane();
		textNome.setBounds(253, 74, 144, 25);
		textNome.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		textNome.setBackground(SystemColor.text);
		textNome.setForeground(SystemColor.textHighlight);
		
		JLabel lblCognomeDip = new JLabel("Cognome");
		lblCognomeDip.setBounds(12, 107, 81, 21);
		lblCognomeDip.setHorizontalAlignment(SwingConstants.LEFT);
		lblCognomeDip.setForeground(SystemColor.textHighlight);
		lblCognomeDip.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		
		textCognome = new JTextPane();
		textCognome.setBounds(253, 107, 144, 25);
		textCognome.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		textCognome.setBackground(SystemColor.text);
		textCognome.setForeground(SystemColor.textHighlight);
		
		JLabel lblVenditeAnnuali = new JLabel("Limite Vendite Annuali");
		lblVenditeAnnuali.setBounds(12, 140, 166, 21);
		lblVenditeAnnuali.setHorizontalAlignment(SwingConstants.LEFT);
		lblVenditeAnnuali.setForeground(SystemColor.textHighlight);
		lblVenditeAnnuali.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		
		textVenditeAnnuali = new JTextPane();
		textVenditeAnnuali.setBounds(253, 140, 144, 25);
		textVenditeAnnuali.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		textVenditeAnnuali.setBackground(SystemColor.text);
		textVenditeAnnuali.setForeground(SystemColor.textHighlight);
		
		textVenditeAnnualiDisponibili = new JTextPane();
		textVenditeAnnualiDisponibili.setBounds(253, 173, 144, 25);
		textVenditeAnnualiDisponibili.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		textVenditeAnnualiDisponibili.setBackground(SystemColor.text);
		textVenditeAnnualiDisponibili.setForeground(SystemColor.textHighlight);
		
		textVenditeGiornaliereDisponibili = new JTextPane();
		textVenditeGiornaliereDisponibili.setBounds(253, 206, 144, 25);
		textVenditeGiornaliereDisponibili.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		textVenditeGiornaliereDisponibili.setBackground(SystemColor.text);
		textVenditeGiornaliereDisponibili.setForeground(SystemColor.textHighlight);
		
		JLabel lblCognomeDip_1_1 = new JLabel("Vendite Annuali Disponibili");
		lblCognomeDip_1_1.setBounds(12, 173, 190, 25);
		lblCognomeDip_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblCognomeDip_1_1.setForeground(SystemColor.textHighlight);
		lblCognomeDip_1_1.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		
		JLabel lblCognomeDip_1_2 = new JLabel("Vendite Giornaliere Disponibili");
		lblCognomeDip_1_2.setBounds(12, 210, 229, 21);
		lblCognomeDip_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblCognomeDip_1_2.setForeground(SystemColor.textHighlight);
		lblCognomeDip_1_2.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		contentPane.setLayout(null);
		contentPane.add(lblDipe);
		contentPane.add(lblPacchetto);
		contentPane.add(btnNewButton_2);
		contentPane.add(btnNewButton);
		contentPane.add(btnNewButton_1);
		contentPane.add(lblVendita);
		contentPane.add(panelDipendente);
		panelDipendente.setLayout(null);
		panelDipendente.add(lblVenditeAnnuali);
		panelDipendente.add(lblCognomeDip_1_2);
		panelDipendente.add(lblCognomeDip_1_1);
		panelDipendente.add(lblCognomeDip);
		panelDipendente.add(lblNomeDip);
		panelDipendente.add(lblDipCodice);
		panelDipendente.add(textVenditeGiornaliereDisponibili);
		panelDipendente.add(textVenditeAnnualiDisponibili);
		panelDipendente.add(textCognome);
		panelDipendente.add(textVenditeAnnuali);
		panelDipendente.add(textCodice);
		panelDipendente.add(textNome);
		
		JLabel lblDipe_1 = new JLabel("Dipendente");
		lblDipe_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblDipe_1.setForeground(SystemColor.textHighlight);
		lblDipe_1.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));
		lblDipe_1.setBounds(12, 5, 201, 26);
		panelDipendente.add(lblDipe_1);
		
		JPanel panelVendite = new JPanel();
		panelVendite.setLayout(null);
		panelVendite.setBackground(SystemColor.controlShadow);
		panelVendite.setBounds(249, 289, 417, 483);
		contentPane.add(panelVendite);
		
		JLabel lblCognomeDip_1_1_1 = new JLabel("Cod. Pacchetto");
		lblCognomeDip_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblCognomeDip_1_1_1.setForeground(SystemColor.textHighlight);
		lblCognomeDip_1_1_1.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		lblCognomeDip_1_1_1.setBounds(12, 43, 190, 25);
		panelVendite.add(lblCognomeDip_1_1_1);
		
		JLabel lblPrezzo = new JLabel("Prezzo");
		lblPrezzo.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrezzo.setForeground(SystemColor.textHighlight);
		lblPrezzo.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		lblPrezzo.setBounds(12, 74, 81, 21);
		panelVendite.add(lblPrezzo);
		
		JTextPane textCognome_1 = new JTextPane();
		textCognome_1.setForeground(SystemColor.textHighlight);
		textCognome_1.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		textCognome_1.setBackground(Color.WHITE);
		textCognome_1.setBounds(253, 74, 144, 25);
		panelVendite.add(textCognome_1);
		
		JLabel lblVendite = new JLabel("Vendite");
		lblVendite.setHorizontalAlignment(SwingConstants.LEFT);
		lblVendite.setForeground(SystemColor.textHighlight);
		lblVendite.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));
		lblVendite.setBounds(12, 5, 201, 26);
		panelVendite.add(lblVendite);
		
		JButton btnInserisciVendita = new JButton("Inserisci Vendita");
		btnInserisciVendita.setBounds(12, 116, 220, 26);
		panelVendite.add(btnInserisciVendita);
		
		comboBox = new JComboBox();
		comboBox.setForeground(SystemColor.textHighlight);
		comboBox.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		comboBox.setBounds(253, 44, 144, 25);
		panelVendite.add(comboBox);
		
		JPanel panelPacchetti = new JPanel();
		panelPacchetti.setLayout(null);
		panelPacchetti.setBackground(SystemColor.controlShadow);
		panelPacchetti.setBounds(678, 17, 524, 755);
		contentPane.add(panelPacchetti);
		
		JLabel lblNomePacchetto = new JLabel("Nome Pacchetto");
		lblNomePacchetto.setHorizontalAlignment(SwingConstants.LEFT);
		lblNomePacchetto.setForeground(SystemColor.textHighlight);
		lblNomePacchetto.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		lblNomePacchetto.setBounds(12, 140, 166, 21);
		panelPacchetti.add(lblNomePacchetto);
		
		JLabel lblNPersone = new JLabel("N. Persone");
		lblNPersone.setHorizontalAlignment(SwingConstants.LEFT);
		lblNPersone.setForeground(SystemColor.textHighlight);
		lblNPersone.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		lblNPersone.setBounds(12, 247, 229, 21);
		panelPacchetti.add(lblNPersone);
		
		JLabel lblDataInizioVacanza = new JLabel("Data Inizio Vacanza");
		lblDataInizioVacanza.setHorizontalAlignment(SwingConstants.LEFT);
		lblDataInizioVacanza.setForeground(SystemColor.textHighlight);
		lblDataInizioVacanza.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		lblDataInizioVacanza.setBounds(12, 173, 190, 25);
		panelPacchetti.add(lblDataInizioVacanza);
		
		JLabel lblCostoPacchetto = new JLabel("Costo");
		lblCostoPacchetto.setHorizontalAlignment(SwingConstants.LEFT);
		lblCostoPacchetto.setForeground(SystemColor.textHighlight);
		lblCostoPacchetto.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		lblCostoPacchetto.setBounds(12, 107, 81, 21);
		panelPacchetti.add(lblCostoPacchetto);
		
		JLabel lblLocalitaPacchetto = new JLabel("Localita");
		lblLocalitaPacchetto.setHorizontalAlignment(SwingConstants.LEFT);
		lblLocalitaPacchetto.setForeground(SystemColor.textHighlight);
		lblLocalitaPacchetto.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		lblLocalitaPacchetto.setBounds(12, 74, 81, 21);
		panelPacchetti.add(lblLocalitaPacchetto);
		
		JLabel lblCodicePacchetto = new JLabel("Codice Pacchetto");
		lblCodicePacchetto.setHorizontalAlignment(SwingConstants.LEFT);
		lblCodicePacchetto.setForeground(SystemColor.textHighlight);
		lblCodicePacchetto.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		lblCodicePacchetto.setBounds(12, 43, 201, 19);
		panelPacchetti.add(lblCodicePacchetto);
		
		textDataInizioPacchetto = new JTextPane();
		textDataInizioPacchetto.setForeground(SystemColor.textHighlight);
		textDataInizioPacchetto.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		textDataInizioPacchetto.setBackground(Color.WHITE);
		textDataInizioPacchetto.setBounds(253, 173, 259, 25);
		panelPacchetti.add(textDataInizioPacchetto);
		
		textNPersone = new JTextPane();
		textNPersone.setForeground(SystemColor.textHighlight);
		textNPersone.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		textNPersone.setBackground(Color.WHITE);
		textNPersone.setBounds(253, 247, 259, 25);
		panelPacchetti.add(textNPersone);
		
		textCostoPacchetto = new JTextPane();
		textCostoPacchetto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!(Character.isDigit(c))) {
					if ((c!= KeyEvent.VK_PERIOD)||(c==KeyEvent.VK_BACK_SPACE) || (c== KeyEvent.VK_DELETE)) e.consume();
				}
				
				
				
			}
		});
		textCostoPacchetto.setForeground(SystemColor.textHighlight);
		textCostoPacchetto.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		textCostoPacchetto.setBackground(Color.WHITE);
		textCostoPacchetto.setBounds(253, 107, 259, 25);
		panelPacchetti.add(textCostoPacchetto);
		
		textNomePacchetto = new JTextPane();
		textNomePacchetto.setForeground(SystemColor.textHighlight);
		textNomePacchetto.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		textNomePacchetto.setBackground(Color.WHITE);
		textNomePacchetto.setBounds(253, 140, 259, 25);
		panelPacchetti.add(textNomePacchetto);
		
		JTextPane textCodicePacchetto = new JTextPane();
		textCodicePacchetto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isDigit(c)) || (c==KeyEvent.VK_BACK_SPACE) || (c== KeyEvent.VK_DELETE)) e.consume();
			}
		});
		textCodicePacchetto.setForeground(SystemColor.textHighlight);
		textCodicePacchetto.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		textCodicePacchetto.setBackground(Color.WHITE);
		textCodicePacchetto.setBounds(253, 37, 259, 25);
		panelPacchetti.add(textCodicePacchetto);
		
		JTextPane textLocalitaPacchetto = new JTextPane();
		textLocalitaPacchetto.setForeground(SystemColor.textHighlight);
		textLocalitaPacchetto.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		textLocalitaPacchetto.setBackground(Color.WHITE);
		textLocalitaPacchetto.setBounds(253, 74, 259, 25);
		panelPacchetti.add(textLocalitaPacchetto);
		
		JLabel lblPacchettiVacanza = new JLabel("Pacchetti Vacanza");
		lblPacchettiVacanza.setHorizontalAlignment(SwingConstants.LEFT);
		lblPacchettiVacanza.setForeground(SystemColor.textHighlight);
		lblPacchettiVacanza.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));
		lblPacchettiVacanza.setBounds(12, 5, 201, 26);
		panelPacchetti.add(lblPacchettiVacanza);
		
		JLabel lblDataFineVacanza = new JLabel("Data Fine Vacanza");
		lblDataFineVacanza.setHorizontalAlignment(SwingConstants.LEFT);
		lblDataFineVacanza.setForeground(SystemColor.textHighlight);
		lblDataFineVacanza.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		lblDataFineVacanza.setBounds(12, 210, 190, 25);
		panelPacchetti.add(lblDataFineVacanza);
		
		textDataFinePacchetto = new JTextPane();
		textDataFinePacchetto.setForeground(SystemColor.textHighlight);
		textDataFinePacchetto.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		textDataFinePacchetto.setBackground(Color.WHITE);
		textDataFinePacchetto.setBounds(253, 210, 259, 25);
		panelPacchetti.add(textDataFinePacchetto);
		
		textDescrizione = new JTextPane();
		textDescrizione.setForeground(SystemColor.textHighlight);
		textDescrizione.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		textDescrizione.setBackground(Color.WHITE);
		textDescrizione.setBounds(253, 280, 259, 157);
		panelPacchetti.add(textDescrizione);
		
		JLabel lblDescrizione = new JLabel("Descrizione");
		lblDescrizione.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescrizione.setForeground(SystemColor.textHighlight);
		lblDescrizione.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		lblDescrizione.setBounds(12, 280, 229, 21);
		panelPacchetti.add(lblDescrizione);
		
		JLabel lblPacchettiVacanza_1 = new JLabel("Pacchetti Vacanza");
		lblPacchettiVacanza_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblPacchettiVacanza_1.setForeground(SystemColor.textHighlight);
		lblPacchettiVacanza_1.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));
		lblPacchettiVacanza_1.setBounds(25, 445, 201, 26);
		panelPacchetti.add(lblPacchettiVacanza_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(12, 483, 500, 223);
		panelPacchetti.add(panel);
		
		table = new JTable();
		panel.add(table);
		
		JButton btnInserisciPacchetto = new JButton("Inserisci Pacchetto");
		btnInserisciPacchetto.setBounds(17, 175, 220, 26);
		contentPane.add(btnInserisciPacchetto);
		
		JButton btnVisualizzaCatalogoPacchetti = new JButton("Visualliza Catalogo Pacchetti");
		btnVisualizzaCatalogoPacchetti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				db.caricaContainerCodiciPacchetto();
				creaTabella(codiciPacchetto);
			}
		});
		btnVisualizzaCatalogoPacchetti.setBounds(17, 137, 220, 26);
		contentPane.add(btnVisualizzaCatalogoPacchetti);
		btnInserisciPacchetto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int codice;
				String localita;									/// località di destinazione del pacchetto
				double costo;										///prezzo di listino del pacchetto, potrà differire da quello di vendita
				String nome;										/// nome esplicativo del pacchetto
				Calendar dataInizioVacanza= Calendar.getInstance();	/// data di inizio della vacanza
			    Calendar dataFineVacanza=Calendar.getInstance();	/// data di fine della vacanza
				int numeroPersonePacchetto;							/// numero di persone comprese nella vacanza
				String descrizione;			
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				
				String codiceInput= textCodicePacchetto.getText();
				if (checkStringaForCodice(codiceInput)==true) {
					codice = Integer.parseInt(codiceInput);
					localita = textLocalitaPacchetto.getText();
					if(checkStringa(localita)==false)JOptionPane.showMessageDialog(null, "Valore inserito non valido: Località inserita non valida");
					
					String costoInput = textCostoPacchetto.getText();
					
					if (checkStringa(costoInput)==true) {
						costo = Double.parseDouble(costoInput);
						
						nome = textNomePacchetto.getText();
						if(checkStringa(nome)==false)JOptionPane.showMessageDialog(null, "Valore inserito non valido: Nome pacchetto non valido");
						
						
						String dataIn = textDataInizioPacchetto.getText();
						Date dataAcquisitaIn;
						try {
							dataAcquisitaIn = sdf.parse(dataIn);
							dataInizioVacanza=Calendar.getInstance();
							dataInizioVacanza.setTime(dataAcquisitaIn);
						} catch (ParseException e1) {
							JOptionPane.showMessageDialog(null, "Valore inserito non valido: Data non accettata");
						}
						
						String dataFine = textDataFinePacchetto.getText();
						Date dataAcquisitaFine;
						try {
							dataAcquisitaFine = sdf.parse(dataFine);
							dataFineVacanza = Calendar.getInstance();
							dataFineVacanza.setTime(dataAcquisitaFine);
						} catch (ParseException e1) {
							JOptionPane.showMessageDialog(null, "Valore inserito non valido: Data non accettata");
						}
						
						String nPersoneIn = textNPersone.getText();
						if(checkStringaForCodice(nPersoneIn)==true) {
							numeroPersonePacchetto = Integer.parseInt(nPersoneIn);
							descrizione = textDescrizione.getText();
							if(checkStringa(descrizione)==false)JOptionPane.showMessageDialog(null, "Valore inserito non valido");
							
							try {
								Pacchetto p = new Pacchetto(codice, localita, costo, nome, dataInizioVacanza, dataFineVacanza, numeroPersonePacchetto, descrizione);
								
								containerPacchetti.add(p);
								db.aggiungiPacchetto(p);
								JOptionPane.showMessageDialog(null, "Pacchetto inserito");
							} catch (PacchettoNonValidoException e1) {
								e1.printStackTrace();
							}
						}
						else {
							JOptionPane.showMessageDialog(null, "Valore inserito non valido: numero persone non valide");
						}
						
						
					}
					else {
						JOptionPane.showMessageDialog(null, "Valore inserito non valido: costo inserito non valido");
					}
					
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Valore inserito non valido: codice inserito non valido");
				}
			}
		});
		
		
		
		
	}
	/**
	 * Funzione boolean che prende in input un codice di tipo stringa, controlla i suoi valori e ritorna vero se è giusta, false altrimenti
	 * @param s codice di tipo stringa da controllare
	 * @return false se il valore è una stringa vuota o supera la lunghezza massima di codice
	 */
	private boolean checkStringaForCodice(String s) {
		if(s.equals(null)|| s.isEmpty()|| s.length()> 4) return false;
		else return true;
	}
	
	/**
	 * Funzione booleana che prende in input una stringa e controlla che non sia vuota
	 * @param s stringa da controllare 
	 * @return true se non è vuota , false altrimenti
	 */
	private boolean checkStringa(String s) {
		if(s.equals(null)|| s.isEmpty()) return false;
		else return true;
	}
	
	public void fillTable(DatabaseAgenzia db) {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("codice");
		String query = "select codice from pacchetto";
		try {
			while (db.rs.next()) {
				model.addRow(new Object[] {
				 DatabaseAgenzia.rs.getString("codice"),
				});
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		table.setModel(model);
		table.setAutoResizeMode(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
	}
	
	public static void stampaCatalogoPacchetti(TreeSet<Pacchetto> catalogo) {
		if(catalogo.isEmpty())System.out.println("Catalogo pacchetti vuoto ");
		else {
			 JOptionPane.showMessageDialog(null,"STAMPA DEL CATALOGO DEI PACCHETTI");
			for (Pacchetto element : catalogo) {
			element.printAllInformations();
			}
		}
	}
	
	public void creaTabella(Set <Integer> codiciPacchetto) {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("codice");
		for (int element : codiciPacchetto) {
			model.addRow(new Object[] {
					 String.valueOf(element),
					});
			}
	}
	
	
}
