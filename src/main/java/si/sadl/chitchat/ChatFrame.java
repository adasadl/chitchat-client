package si.sadl.chitchat;

import java.awt.Container;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Insets;


public class ChatFrame extends JFrame implements ActionListener, KeyListener {
	
	private JTextArea output;
	private JTextField input;
	private JTextField vnos;
	private JButton prijava;
	private JButton odjava;
	private JTextField prejemnik_input;
	private JTextArea uporabniki;
	private JLabel napis_ob_prijavi;
	public String uporabnik;
	public boolean prijavljen;
	
	

	public ChatFrame() {
		super();
		Container pane = this.getContentPane();
		pane.setLayout(new GridBagLayout());
		
		this.uporabnik = System.getProperty("user.name");
		this.prijavljen = false;
		
		//OUTPUT
		this.output = new JTextArea(20, 40);
		this.output.setEditable(false);
		JScrollPane scrollpane = new JScrollPane(output);
		GridBagConstraints outputConstraint = new GridBagConstraints();
		outputConstraint.weightx = 1.0;
		outputConstraint.weighty = 1.0;
		outputConstraint.fill = GridBagConstraints.BOTH;
		outputConstraint.gridx = 0;
		outputConstraint.gridy = 1;
		pane.add(scrollpane, outputConstraint);
		
		//VZDEVEK
		JPanel polje = new JPanel();
		JLabel oznaka = new JLabel("Vzdevek: ");
		this.vnos = new JTextField(30);
		polje.setLayout(new FlowLayout(FlowLayout.LEFT));
		polje.add(oznaka);
		polje.add(vnos);
		oznaka.setForeground(Color.red);
		
		GridBagConstraints vzdevek = new GridBagConstraints();
		vzdevek.gridx = 0;
		vzdevek.gridy = 0;
		vzdevek.fill = GridBagConstraints.HORIZONTAL;
		pane.add(polje, vzdevek);
		
		//JPanel polje_vnosa = new JPanel();
		//JLabel ime_oznaka = new JLabel (this.vnos.getText() + ": ");
		
		//polje_vnosa.setLayout(new FlowLayout(FlowLayout.LEFT));
		//polje_vnosa.add(ime_oznaka);
		//polje_vnosa.add(input);
		
		//GridBagConstraints ime = new GridBagConstraints();
		//ime.gridx = 0;
		//ime.gridy = 3;
		//ime.fill = GridBagConstraints.HORIZONTAL;
		//pane.add(polje_vnosa, ime);
		
		//INPUT
		this.input = new JTextField(40);
		GridBagConstraints inputConstraint = new GridBagConstraints();
		inputConstraint.weightx = 1.0;
		inputConstraint.weighty = 0.0;
		inputConstraint.fill = GridBagConstraints.HORIZONTAL;
		inputConstraint.gridx = 0;
		inputConstraint.gridy = 3;
		pane.add(input, inputConstraint);
		input.addKeyListener(this);
		

		
		//GUMB PRIJAVA
		this.prijava = new JButton("Prijava");
		GridBagConstraints gbc_gumbPrijava = new GridBagConstraints();
		//gbc_gumbPrijava.insets = new Insets(0, 0, 5, 5);
		gbc_gumbPrijava.gridx = 1;
		gbc_gumbPrijava.gridy = 0;
		pane.add(prijava, gbc_gumbPrijava);
		prijava.addActionListener(this);
		
		this.napis_ob_prijavi = new JLabel("Niste prijavljeni. Za pošiljanje sporočil se morate prijaviti.");
		GridBagConstraints napis_gbc = new GridBagConstraints();
		napis_gbc.gridx = 0;
		napis_gbc.gridy = 2;
		napis_gbc.insets = new Insets (10, 10, 10, 10);
		pane.add(napis_ob_prijavi, napis_gbc);
		
		//this.napis_ob_prijavi.setForeground(Color.red);
		
		//GUMB ODJAVA
		this.odjava = new JButton("Odjava");
		GridBagConstraints gbc_gumbOdjava = new GridBagConstraints();
		//gbc_gumbOdjava.insets = new Insets(0, 0, 5, 0);
		gbc_gumbOdjava.gridx = 1;
		gbc_gumbOdjava.gridy = 2;
		pane.add(odjava, gbc_gumbOdjava);
		odjava.addActionListener(this);
		
		//PREJEMNIK
		this.prejemnik_input = new JTextField(10);
		GridBagConstraints posiljatelj_input_gbc = new GridBagConstraints();
		posiljatelj_input_gbc.gridx = 1;
		posiljatelj_input_gbc.gridy = 4;
		posiljatelj_input_gbc.insets = new Insets (10, 10, 10, 10);
		pane.add(prejemnik_input, posiljatelj_input_gbc);
		prejemnik_input.addKeyListener(this);
		
		JLabel prejemnik_label = new JLabel("Prejemnik:");
		GridBagConstraints prejemnik_label_gbc = new GridBagConstraints();
		prejemnik_label_gbc.gridx = 0;
		prejemnik_label_gbc.gridy = 4;
		prejemnik_label_gbc.insets = new Insets (10, 10, 10, 10);
		pane.add(prejemnik_label, prejemnik_label_gbc);
		
		//UPORABNIKI
		this.uporabniki = new JTextArea(20,20);
		GridBagConstraints uporabniki_gbc = new GridBagConstraints();
		uporabniki_gbc.fill = GridBagConstraints.BOTH;
		uporabniki_gbc.weightx = 1.0;
		uporabniki_gbc.weighty = 1.0;
		uporabniki_gbc.gridx = 3;
		uporabniki_gbc.gridy = 1;
		uporabniki_gbc.insets = new Insets(10,10,10,10);
		
		JScrollPane drsnik_uporabniki = new JScrollPane(uporabniki);
		
		pane.add(drsnik_uporabniki, uporabniki_gbc);
		
		JLabel uporabniki_label = new JLabel("Prisotni uporabniki:");
		GridBagConstraints uporabniki_label_gbc = new GridBagConstraints();
		uporabniki_label_gbc.gridx = 3;
		uporabniki_label_gbc.gridy = 0;
		uporabniki_label_gbc.insets = new Insets (10, 10, 10, 10);
		pane.add(uporabniki_label, uporabniki_label_gbc);
		uporabniki_label.setForeground(Color.blue);
		
		//this.input.requestFocus();
		//this.input.requestFocusInWindow();
		
	}
	

	/**
	 * @param person - the person sending the message
	 * @param message - the message content
	 */
	public void addMessage(boolean global, String person, String message) {
		String chat = this.output.getText();
		App.SendMessage(global, person, this.prejemnik_input.getText(), message);
		this.output.setText(chat + person + ": " + message + "\n");
	}
	
	public void receiveMessage(String person, String message) {
		String chat = this.output.getText();
		this.output.setText(chat + person + ": " + message + "\n");
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.prijava && this.vnos.getText().equals("")) { //se prijaviš brez vzdevka
			App.prijava(this.uporabnik);
			this.prijavljen = true;
			this.napis_ob_prijavi.setText("Prijavljen je: " + this.uporabnik + ".");
		} else if (e.getSource() == this.prijava) {	//se prijaviš z vzdevkom
			this.uporabnik = this.vnos.getText();
			App.prijava(this.uporabnik);
			this.prijavljen = true;
			this.napis_ob_prijavi.setText("Prijavljen je: " + this.uporabnik + ".");
		} else if (e.getSource() == this.odjava) { //se odjaviš
			App.odjava(this.uporabnik);
			this.prijavljen = false;
			this.vnos.setText("");
			this.uporabnik = System.getProperty("user.name");
			this.napis_ob_prijavi.setText("Niste prijavljeni. Za pošiljanje sporočil se morate prijaviti.");
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
					this.addMessage(true, this.uporabnik, this.input.getText());
					this.input.setText("");
				} else {	//je določen prejemnik
					this.addMessage(false, this.uporabnik, this.input.getText());
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
