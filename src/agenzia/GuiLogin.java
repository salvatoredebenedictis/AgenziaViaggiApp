package agenzia;

import java.awt.EventQueue;
import database.*;
import dipendente.*;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Panel;
import java.awt.Button;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Window.Type;
import javax.swing.JPasswordField;

public class GuiLogin {
	 public Dipendente dip;
	public DatabaseAgenzia db ;
	private JFrame AgenziaViaggiApp;
	private JTextField textFieldCodice;
	private JTextField txtUSER_DB;
	private JPasswordField passwordFieldDB;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiLogin window = new GuiLogin();
					window.AgenziaViaggiApp.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,"Riprovare più tardi");
					
				}
			}
		});
	}

	
	public GuiLogin() {	
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		AgenziaViaggiApp = new JFrame();
		AgenziaViaggiApp.setResizable(false);
		AgenziaViaggiApp.setType(Type.UTILITY);
		AgenziaViaggiApp.setTitle("Agenzi Viaggi Login");
		AgenziaViaggiApp.setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\User\\Salvarki\\Desktop\\Studio\\Programmazione\\icons\\globe.png"));
		AgenziaViaggiApp.getContentPane().setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		AgenziaViaggiApp.getContentPane().setForeground(Color.WHITE);
		AgenziaViaggiApp.getContentPane().setBackground(Color.WHITE);
		
		Panel panel = new Panel();
		panel.setBounds(0, 0, 383, 473);
		panel.setBackground(Color.DARK_GRAY);
		
		Button Login = new Button("Login");
		Login.setBounds(435, 330, 199, 59);
		Login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				db = new DatabaseAgenzia(txtUSER_DB.getText(), String.valueOf(passwordFieldDB.getPassword()));
				
				int status = db.getStatus();
				
				switch(status) {
				case 0:JOptionPane.showMessageDialog(null,"DB in stato di default \n");
				break;
				case 1: JOptionPane.showMessageDialog(null,"Connessione avvenuta con successo \n");
				break;
				case 2: JOptionPane.showMessageDialog(null,"Errore: inserire il jar corretto");
				break;
				case 3: JOptionPane.showMessageDialog(null,"Errore: Impossibile connettersi al Database (DB, USER, PASSWORD Errati)");
				break;
				case 4: JOptionPane.showMessageDialog(null,"Errore: Problema con lo stmt");
				break;
				case 5: JOptionPane.showMessageDialog(null,"Nessun dipendente trovato");
				break;
				default: JOptionPane.showMessageDialog(null,"Errore non identificato");
				break;
				}
				if(status == 1) {
				try {
					String codice=textFieldCodice.getText();
					if(codice.equals(null)|| codice.isEmpty()|| codice.length()> 4) {
						JOptionPane.showMessageDialog(null, "Valore inserito non valido");
					}
					else {
						int codiceDipendente = Integer.parseInt(codice);
						dip = db.cercaDipendente(codiceDipendente);
						if ( dip == null) JOptionPane.showMessageDialog(null,"Nessun dipendente con codice " +codice+ " trovato");
						else {
							JOptionPane.showMessageDialog(null,"Accesso Eseguito");
							AgenziaViaggiApp.dispose();
							GuiAgenziaViaggi App= new GuiAgenziaViaggi(dip,db);
							App.setVisible(true);
						}
					}
				}
				catch (Exception e1) {
					e1.printStackTrace();
				}
				}
				else JOptionPane.showMessageDialog(null,"Riprova");
			}
		});
	
		Login.setBackground(SystemColor.textHighlight);
		
		textFieldCodice = new JTextField();
		textFieldCodice.setBounds(389, 261, 270, 42);
		textFieldCodice.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			char c = e.getKeyChar();
			if (!(Character.isDigit(c)) || (c==KeyEvent.VK_BACK_SPACE) || (c== KeyEvent.VK_DELETE)) e.consume();
			}
		});
		textFieldCodice.setColumns(10);
		
		JLabel lblCodiceDipendente = new JLabel("Codice Dipendente");
		lblCodiceDipendente.setBounds(389, 213, 270, 42);
		lblCodiceDipendente.setForeground(SystemColor.textHighlight);
		lblCodiceDipendente.setHorizontalAlignment(SwingConstants.LEFT);
		lblCodiceDipendente.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		
		JLabel lblUserDB = new JLabel("Username DB");
		lblUserDB.setBounds(389, 11, 270, 42);
		lblUserDB.setHorizontalAlignment(SwingConstants.LEFT);
		lblUserDB.setForeground(SystemColor.textHighlight);
		lblUserDB.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		
		txtUSER_DB = new JTextField();
		txtUSER_DB.setBounds(389, 59, 270, 42);
		txtUSER_DB.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtUSER_DB.setText("root");
		txtUSER_DB.setColumns(10);
		
		JLayeredPane layeredPane = new JLayeredPane();
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("D:\\User\\Salvarki\\Desktop\\Studio\\Programmazione\\Immagini\\dserwtyrewertui.jpg"));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, 376, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, 464, Short.MAX_VALUE)
		);
		GroupLayout gl_layeredPane = new GroupLayout(layeredPane);
		gl_layeredPane.setHorizontalGroup(
			gl_layeredPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_layeredPane.createSequentialGroup()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 408, GroupLayout.PREFERRED_SIZE)
					.addGap(0, 0, Short.MAX_VALUE))
		);
		gl_layeredPane.setVerticalGroup(
			gl_layeredPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_layeredPane.createSequentialGroup()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 483, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(0, Short.MAX_VALUE))
		);
		layeredPane.setLayout(gl_layeredPane);
		panel.setLayout(gl_panel);
		
		JLabel lblPasswordDB = new JLabel("Password DB");
		lblPasswordDB.setBounds(389, 112, 270, 42);
		lblPasswordDB.setHorizontalAlignment(SwingConstants.LEFT);
		lblPasswordDB.setForeground(SystemColor.textHighlight);
		lblPasswordDB.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		
		passwordFieldDB = new JPasswordField();
		passwordFieldDB.setBounds(389, 160, 270, 42);
		passwordFieldDB.setToolTipText("");
		passwordFieldDB.setText("root");
		AgenziaViaggiApp.getContentPane().setLayout(null);
		AgenziaViaggiApp.getContentPane().add(panel);
		AgenziaViaggiApp.getContentPane().add(lblUserDB);
		AgenziaViaggiApp.getContentPane().add(txtUSER_DB);
		AgenziaViaggiApp.getContentPane().add(lblCodiceDipendente);
		AgenziaViaggiApp.getContentPane().add(textFieldCodice);
		AgenziaViaggiApp.getContentPane().add(lblPasswordDB);
		AgenziaViaggiApp.getContentPane().add(passwordFieldDB);
		AgenziaViaggiApp.getContentPane().add(Login);
		AgenziaViaggiApp.setBackground(Color.WHITE);
		AgenziaViaggiApp.setBounds(100, 100, 699, 502);
		AgenziaViaggiApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
