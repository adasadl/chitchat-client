package si.sadl.chitchat;

import java.awt.*;

import java.awt.event.*;

import javax.swing.*;

//import javax.swing.border.*;

//import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
//import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import org.apache.http.client.ClientProtocolException;

import java.awt.Container;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Insets;
import com.oracle.webservices.internal.api.EnvelopeStyle.Style;
//

public class ChatFrame extends JFrame implements ActionListener, KeyListener {
	
	/**
	 * 
	 */
	private JTextPane output;
	private JTextField input;
	private JTextField vnos;
	private JButton prijava;
	private JButton odjava;
	private JTextField prejemnik_input;
	private JTextArea navodila;
	public String uporabnik;
	public boolean prijavljen;
	private JButton rdeca_pisava;
	public boolean rdece;
	public Style style;
	public StyledDocument doc;
	private JButton velikost_pisave;
	private JButton poslji;
	private JButton privzeta_velikost_pisave;
	private JButton crna_pisava;
	
	

	public ChatFrame() {
		super();
		Container pane = this.getContentPane();
		pane.setLayout(new GridBagLayout());
		
		this.uporabnik = System.getProperty("user.name");
		this.prijavljen = false;
		this.rdece = false;
		
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //ko zapremo okno za klepetanje, se program neha izvajati
		
		//this.setSize(new Dimension(400, 400));
		
		addWindowListener(new WindowAdapter() { 		//ko zapremo okno nas sistem avtomatsko odjavi
          @Override
          public void windowClosing(WindowEvent e) {
          		if (prijavljen) {
                  App.odjava(uporabnik);
              }
              e.getWindow().dispose();
          }
		});
		
		//OUTPUT
		this.output = new JTextPane();
		this.doc = this.output.getStyledDocument();
     
		this.output.setEditable(false);
		JScrollPane scrollpane = new JScrollPane(output);
		GridBagConstraints outputConstraint = new GridBagConstraints();
		outputConstraint.weightx = 1.0;
		outputConstraint.weighty = 1.0;
		outputConstraint.fill = GridBagConstraints.BOTH;
		outputConstraint.gridx = 0;
		outputConstraint.gridy = 1;
		outputConstraint.insets = new Insets(0, 10, 0, 10);
		pane.add(scrollpane, outputConstraint);
		
		
		//INPUT
		this.input = new JTextField(40);
		GridBagConstraints inputConstraint = new GridBagConstraints();
		inputConstraint.weightx = 1.0;
		inputConstraint.weighty = 1.0;
		inputConstraint.gridx = 0;
		inputConstraint.gridy = 4;
		inputConstraint.fill = GridBagConstraints.HORIZONTAL;
		inputConstraint.insets = new Insets(0, 10, 0, 10);
		pane.add(input,  inputConstraint);
		input.addKeyListener(this);
		
		//VZDEVEK
		JPanel polje = new JPanel();
		JLabel oznaka = new JLabel("Vzdevek: ");
		this.vnos = new JTextField(20);
		polje.setLayout(new FlowLayout(FlowLayout.RIGHT));
		polje.add(oznaka);
		polje.add(vnos);
		
		GridBagConstraints vzdevek = new GridBagConstraints();
		vzdevek.gridx = 0;
		vzdevek.gridy = 0;
		vzdevek.fill = GridBagConstraints.HORIZONTAL;
		pane.add(polje, vzdevek);
		
		
		//PREJEMNIK		
		JPanel polje_prejemnika = new JPanel();
		JLabel oznaka_prejemnik = new JLabel("Prejemnik: ");
		this.prejemnik_input = new JTextField(20);
		polje_prejemnika.setLayout(new FlowLayout(FlowLayout.LEFT));
		polje_prejemnika.add(oznaka_prejemnik);
		polje_prejemnika.add(prejemnik_input);
		
		GridBagConstraints posiljatelj_input_gbc = new GridBagConstraints();
		posiljatelj_input_gbc.weightx = 1.0;
		posiljatelj_input_gbc.weighty = 1.0;
		posiljatelj_input_gbc.gridx = 0;
		posiljatelj_input_gbc.gridy = 2;
		posiljatelj_input_gbc.fill = GridBagConstraints.HORIZONTAL;
		posiljatelj_input_gbc.insets = new Insets (0, 10, 0, 10);
		pane.add(polje_prejemnika, posiljatelj_input_gbc);
		prejemnik_input.addKeyListener(this);
	
		
		//GUMB PRIJAVA
		this.prijava = new JButton("Prijava");
		GridBagConstraints gbc_gumbPrijava = new GridBagConstraints();
		gbc_gumbPrijava.insets = new Insets(5, 5, 5, 5);
		gbc_gumbPrijava.gridx = 1;
		gbc_gumbPrijava.gridy = 0;
		gbc_gumbPrijava.anchor = GridBagConstraints.WEST;
		pane.add(prijava, gbc_gumbPrijava);
		prijava.addActionListener(this);
		
		//GUMB ODJAVA
		this.odjava = new JButton("Odjava");
		GridBagConstraints gbc_gumbOdjava = new GridBagConstraints();
		gbc_gumbOdjava.insets = new Insets(10, 10, 10, 10);
		gbc_gumbOdjava.gridx = 1;
		gbc_gumbOdjava.gridy = 0;
		gbc_gumbOdjava.anchor = GridBagConstraints.EAST;
		pane.add(odjava, gbc_gumbOdjava);
		odjava.addActionListener(this);
		
		
		//GUMB POŠLJI
		this.poslji = new JButton("Pošlji");
		GridBagConstraints gbc_poslji = new GridBagConstraints();
		gbc_poslji.gridx = 1;
		gbc_poslji.gridy = 4;
		gbc_poslji.anchor = GridBagConstraints.WEST;
		pane.add(poslji, gbc_poslji);
		poslji.addActionListener(this);
		
		//ČRNA PISAVA
		this.crna_pisava = new JButton("Črna pisava");
		GridBagConstraints gbc_crna_pisava = new GridBagConstraints();
		gbc_crna_pisava.insets = new Insets(10, 10, 10, 10);
		gbc_crna_pisava.gridx = 0;
		gbc_crna_pisava.gridy = 5;
		gbc_crna_pisava.anchor = GridBagConstraints.WEST;
		pane.add(crna_pisava, gbc_crna_pisava);
		this.crna_pisava.setBackground(Color.YELLOW);
		crna_pisava.addActionListener(this);
		
		//RDEČA PISAVA
		this.rdeca_pisava = new JButton("Rdeča pisava");
		this.rdeca_pisava.setForeground(Color.RED);
		GridBagConstraints gbc_gumb_rdeca_pisava = new GridBagConstraints();
		gbc_gumb_rdeca_pisava.gridx = 0;
		gbc_gumb_rdeca_pisava.gridy = 6;
		gbc_gumb_rdeca_pisava.anchor = GridBagConstraints.WEST;
		gbc_gumb_rdeca_pisava.insets = new Insets(10, 10, 10, 10);
		pane.add(rdeca_pisava, gbc_gumb_rdeca_pisava);
		rdeca_pisava.addActionListener(this);
		
		//PRIVZETA PISAVA
		this.privzeta_velikost_pisave = new JButton("Privzeta velikost pisave");
		GridBagConstraints gbc_privzeta_velikost_pisave = new GridBagConstraints();
		gbc_privzeta_velikost_pisave.gridx = 0;
		gbc_privzeta_velikost_pisave.gridy = 7;
		gbc_privzeta_velikost_pisave.anchor = GridBagConstraints.WEST;
		gbc_privzeta_velikost_pisave.insets = new Insets(10, 10, 10, 10);
		pane.add(privzeta_velikost_pisave, gbc_privzeta_velikost_pisave);
		this.privzeta_velikost_pisave.setBackground(Color.YELLOW);
		privzeta_velikost_pisave.addActionListener(this);
		
		//VEČJA PISAVA
		this.velikost_pisave = new JButton("Večja pisava");
		GridBagConstraints gbc_velikost_pisave = new GridBagConstraints();
		gbc_velikost_pisave.gridx = 0;
		gbc_velikost_pisave.gridy = 8;
		gbc_velikost_pisave.anchor = GridBagConstraints.WEST;
		gbc_velikost_pisave.insets = new Insets(10, 10, 10, 10);
		pane.add(velikost_pisave, gbc_velikost_pisave);
		velikost_pisave.addActionListener(this);

		
		//NAVODILA
		this.navodila = new JTextArea(20,21);
		GridBagConstraints gbc_navodila = new GridBagConstraints();
		gbc_navodila.fill = GridBagConstraints.BOTH;
		gbc_navodila.weightx = 1.0;
		gbc_navodila.weighty = 1.0;
		gbc_navodila.gridx = 1;
		gbc_navodila.gridy = 1;
		gbc_navodila.insets = new Insets(10,10,10,10);
		
		JScrollPane drsnik_navodila = new JScrollPane(navodila);
		
		pane.add(drsnik_navodila, gbc_navodila);
		this.navodila.setText(" Dobrodošli v spletni klepetalnici! \n"
				+ "\n"
				+ " Niste prijavljeni."
				+ "\n"
				+ " Za pošiljanje sporočil se morate prijaviti.");
		
		Font font1 = new Font("SansSerif", Font.BOLD, 12);
		this.navodila.setFont(font1);		
	}
	
	/**
	 * @param person - the person sending the message
	 * @param message - the message content
	 */
	
	public void addMessage(boolean global, String person, String message)throws BadLocationException {
		App.SendMessage(global, person, this.prejemnik_input.getText(), message);
		SimpleAttributeSet set = new SimpleAttributeSet();
		if (this.rdece){
	    StyleConstants.setForeground(set, Color.red);
		} else {
			StyleConstants.setForeground(set, Color.black);
		}
	    this.doc.insertString(doc.getLength(), person + ": " + message + "\n", set);
	}
	
	public void addMessageRobot(String person, String message)throws BadLocationException{
		SimpleAttributeSet set = new SimpleAttributeSet();
	    StyleConstants.setForeground(set, Color.blue);
		this.doc.insertString(doc.getLength(), person + ": " + message + "\n", set);
	}
	
	public void receiveMessage() throws ClientProtocolException, URISyntaxException, IOException {
		for (Message s : App.getMessages(this.uporabnik)){
			try {
				addMessageRobot(s.getSender(),s.toString());
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.velikost_pisave){
			Font font1 = new Font("SansSerif", Font.PLAIN, 20);
			this.input.setFont(font1);
			this.output.setFont(font1);
			this.velikost_pisave.setBackground(Color.YELLOW);
			this.privzeta_velikost_pisave.setBackground(null);
		}if (e.getSource() == this.crna_pisava){
			this.input.setForeground(Color.BLACK);
			this.rdece = false;
			this.rdeca_pisava.setBackground(null);
			this.crna_pisava.setBackground(Color.YELLOW);
		}if (e.getSource() == this.privzeta_velikost_pisave){
			Font font1 = new Font("SansSerif", Font.PLAIN, 12);
			this.input.setFont(font1);
			this.output.setFont(font1);
			this.velikost_pisave.setBackground(null);
			this.privzeta_velikost_pisave.setBackground(Color.YELLOW);
		}  if (e.getSource() == this.prijava && this.vnos.getText().equals("")) { //se prijaviš brez vzdevka
			if (this.prijavljen == false){
				Font font1 = new Font("SansSerif", Font.PLAIN, 12);
				this.input.setFont(font1);
				this.output.setFont(font1);
				this.input.setForeground(Color.BLACK);
				this.rdece = false;
				this.velikost_pisave.setBackground(null);
				this.privzeta_velikost_pisave.setBackground(Color.YELLOW);
				this.rdeca_pisava.setBackground(null);
				this.crna_pisava.setBackground(Color.YELLOW);
				App.prijava(this.uporabnik);
				this.prijavljen = true;
				this.navodila.setText(" Dobrodošli v spletni klepetalnici! \n \n Prijavljen je: " + this.uporabnik + ".");
			}
		} else if (e.getSource() == this.prijava) {	//se prijaviš z vzdevkom
			if (this.prijavljen == false){
				Font font1 = new Font("SansSerif", Font.PLAIN, 12);
				this.input.setFont(font1);
				this.output.setFont(font1);
				this.input.setForeground(Color.BLACK);
				this.rdece = false;
				this.velikost_pisave.setBackground(null);
				this.privzeta_velikost_pisave.setBackground(Color.YELLOW);
				this.rdeca_pisava.setBackground(null);
				this.crna_pisava.setBackground(Color.YELLOW);
				this.uporabnik = this.vnos.getText();
				App.prijava(this.uporabnik);
				this.prijavljen = true;
				this.navodila.setText(" Dobrodošli v spletni klepetalnici! \n \n Prijavljen je: " + this.uporabnik + ".");
			}
		} else if (e.getSource() == this.odjava) { 		//se odjaviš
			Font font1 = new Font("SansSerif", Font.PLAIN, 12);
			this.input.setFont(font1);
			this.output.setFont(font1);
			this.input.setForeground(Color.BLACK);
			this.rdece = false;
			this.velikost_pisave.setBackground(null);
			this.privzeta_velikost_pisave.setBackground(Color.YELLOW);
			this.rdeca_pisava.setBackground(null);
			this.crna_pisava.setBackground(Color.YELLOW);
			App.odjava(this.uporabnik);
			this.prijavljen = false;
			this.vnos.setText("");
			this.uporabnik = System.getProperty("user.name");
			this.prejemnik_input.setText("");
			this.output.setText("");
			this.navodila.setText(" Dobrodošli v spletni klepetalnici! \n \n Niste prijavljeni. \n Za pošiljanje sporočil se morate prijaviti.");
		} else if (e.getSource() == this.rdeca_pisava) {
			this.input.setForeground(Color.RED);
			this.rdece = true;
			this.rdeca_pisava.setBackground(Color.YELLOW);
			this.crna_pisava.setBackground(null);
		} else if (e.getSource() == this.poslji){
			if (this.prijavljen == true){		//če nisi prijavljen ne moreš pošiljati sporočil
				if (e.getSource() == this.vnos) {
						this.uporabnik = this.vnos.getText();
					}
			if (this.prejemnik_input.getText().equals("")) { //ni določenega prejemnika
				try {
					this.addMessage(true, this.uporabnik, this.input.getText());
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				this.input.setText("");
			} else {	//je določen prejemnik
				try {
					this.addMessage(false, this.uporabnik, this.input.getText());
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				this.input.setText("");
			}
		}
		}
	}

	
	public void keyTyped(KeyEvent e) {
		if (this.prijavljen == true){		//če nisi prijavljen ne moreš pošiljati sporočil
		if (e.getSource() == this.vnos) {
			if (e.getKeyChar() == '\n') {
				this.uporabnik = this.vnos.getText();
			}
		} else if (e.getSource() == this.input) {
			if (e.getKeyChar() == '\n') {
				if (this.prejemnik_input.getText().equals("")) { //ni določenega prejemnika
					try {
						this.addMessage(true, this.uporabnik, this.input.getText());
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					this.input.setText("");
				} else {	//je določen prejemnik
					try {
						this.addMessage(false, this.uporabnik, this.input.getText());
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					this.input.setText("");
				}
			}
		}
	}
	}

	
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}



