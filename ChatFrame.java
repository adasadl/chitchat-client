import java.awt.Container;
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

public class ChatFrame extends JFrame implements ActionListener, KeyListener {
	
	private JTextArea output;
	private JTextField input;
	private JTextField vnos;

	public ChatFrame() {
		super();
		Container pane = this.getContentPane();
		pane.setLayout(new GridBagLayout());
		

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
		
		
		this.input = new JTextField(40);
		GridBagConstraints inputConstraint = new GridBagConstraints();
		inputConstraint.weightx = 1.0;
		inputConstraint.weighty = 0.0;
		inputConstraint.fill = GridBagConstraints.HORIZONTAL;
		inputConstraint.gridx = 0;
		inputConstraint.gridy = 2;
		pane.add(input, inputConstraint);
		input.addKeyListener(this);
		
		JPanel polje = new JPanel();
		JLabel oznaka = new JLabel("Vzdevek: ");
		this.vnos = new JTextField(15);
		polje.setLayout(new FlowLayout(FlowLayout.LEFT));
		polje.add(oznaka);
		polje.add(vnos);
		
		
		GridBagConstraints vzdevek = new GridBagConstraints();
		vzdevek.gridx = 0;
		vzdevek.gridy = 0;
		vzdevek.fill = GridBagConstraints.HORIZONTAL;
		pane.add(polje, vzdevek);
		
	}
	

	/**
	 * @param person - the person sending the message
	 * @param message - the message content
	 */
	public void addMessage(String person, String message) {
		String chat = this.output.getText();
		this.output.setText(chat + person + ": " + message + "\n");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == this.input) {
			if (e.getKeyChar() == '\n') {
				this.addMessage(System.getProperty("user.name"), this.input.getText());
				this.input.setText("");
			}
		}		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
