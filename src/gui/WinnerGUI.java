package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 * This class extends JFrame and warns the players if one has won the game.
 * @author Thijs van der Burgt
 * @author Arno Schutijzer
 */
@SuppressWarnings("serial")
public class WinnerGUI extends JFrame implements ActionListener {

	private JLabel lblUsername= new JLabel();
	private JButton btnConfirm= new JButton();
	@SuppressWarnings("unused")
	private Messages messages;
	/**
	 * Default constructor for WinnerGUI.
	 * This method creates a new JFrame.
	 * @param username
	 * @param messages
	 */
	public WinnerGUI(String username, Messages messages){
		this.messages= messages;

		GroupLayout layout= new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
		
		lblUsername.setText(messages.getString("winner") + username);
		lblUsername.setFont(new Font("Serif", Font.BOLD, 20));
		
		btnConfirm.setText(messages.getString("quit"));
		btnConfirm.addActionListener(this);
		
		JLabel lblImage = new JLabel(new ImageIcon("lib/images/winnar.png"));
		
		SequentialGroup sGroup= layout.createSequentialGroup();
		ParallelGroup pGroup= layout.createParallelGroup();
		
		SequentialGroup slblGroup= layout.createSequentialGroup();
		ParallelGroup plblGroup= layout.createParallelGroup();
		
		sGroup.addComponent(lblImage);
		pGroup.addComponent(lblImage);
		
		slblGroup.addComponent(lblUsername);
		plblGroup.addComponent(lblUsername);
		
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGroup(sGroup)
				.addGroup(slblGroup)
				.addGroup(layout.createSequentialGroup()
						.addComponent(btnConfirm)));
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(pGroup)
				.addGroup(plblGroup)
				.addGap(30)
				.addGroup(layout.createParallelGroup()
						.addComponent(btnConfirm)));
		
		setTitle(messages.getString("quit"));
		setVisible(true);
		pack();
		setLocationRelativeTo(null);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		System.exit(0);
	}

}
