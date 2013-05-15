/**
* @author Arno Schutijzer & Thijs van der Burgt
**/

package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.*;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import domain.DomainController;
import exceptions.UserAlreadyExistsException;

@SuppressWarnings("serial")
public class InitGUI extends JFrame implements ActionListener{
	private final int MAXPLAYERS = 6;
	private JButton btnStart;
	private JList<String> mapList;
	
	private JTextField[] txtUsers= new JTextField[MAXPLAYERS];
	private JLabel[] lblLabels= new JLabel[MAXPLAYERS];
	private JTextField txtMapName;
	
	private DomainController domainController;
	
	private Messages messages;
	
	public InitGUI(Messages messages, DomainController domainController) throws ClassNotFoundException, SQLException{
		this.domainController = domainController;
		this.messages = messages;
		
		String player = messages.getString("player") + " ";
		String mapName = messages.getString("gameName") + ": ";
		String start = messages.getString("start");
		
		JLabel lblMapName = new JLabel(mapName),
				lblList = new JLabel(messages.getString("map"));
		txtMapName = new JTextField();
			
		for(int i= 0; i < 6; i++){
			txtUsers[i]= new JTextField();
			lblLabels[i] = new JLabel(player + (i+1) + ":");
		}
		String[] data = domainController.getMapNames();
		for(int i = 0; i<data.length; i++){
			String u = messages.getString(data[i]);
			data[i] = u;
		}
		mapList = new JList<String>(data);
		mapList.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 1,false));
		
		btnStart = new JButton(start);
		btnStart.setMinimumSize(new Dimension(150, JButton.HEIGHT));
		btnStart.addActionListener(this);
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
		
		layout.linkSize(SwingConstants.HORIZONTAL, txtUsers[0], txtUsers[1], txtUsers[2], txtUsers[3], txtUsers[4], txtUsers[5]);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(Alignment.CENTER)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup()
										.addGroup(layout.createSequentialGroup()
												.addGroup(layout.createParallelGroup()
														.addComponent(lblMapName)
														.addComponent(lblLabels[0])
														.addComponent(lblLabels[1])
														.addComponent(lblLabels[2])
														.addComponent(lblLabels[3])
														.addComponent(lblLabels[4])
														.addComponent(lblLabels[5])
												)
												.addGroup(layout.createParallelGroup()
														.addComponent(txtMapName, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
														.addComponent(txtUsers[0], GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
														.addComponent(txtUsers[1])
														.addComponent(txtUsers[2])
														.addComponent(txtUsers[3])
														.addComponent(txtUsers[4])
														.addComponent(txtUsers[5])
												)
										)
								)
								.addGap(50)
								.addGroup(layout.createParallelGroup()
										.addComponent(lblList)
										.addComponent(mapList, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
								)
						)
						.addGroup(layout.createSequentialGroup()
								.addComponent(btnStart)
						)
				)
		);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(Alignment.TRAILING)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblMapName)
										.addComponent(txtMapName)
										.addComponent(lblList)
								)
								.addGap(20)
								.addGroup(layout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblLabels[0])
										.addComponent(txtUsers[0])
								)
								.addGroup(layout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblLabels[1])
										.addComponent(txtUsers[1])
								)
								.addGroup(layout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblLabels[2])
										.addComponent(txtUsers[2])
								)
								.addGroup(layout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblLabels[3])
										.addComponent(txtUsers[3])
								)
								.addGroup(layout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblLabels[4])
										.addComponent(txtUsers[4])
								)
								.addGroup(layout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblLabels[5])
										.addComponent(txtUsers[5])
								)
						)
						.addComponent(mapList, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
				)
				.addGap(20)
				.addGroup(layout.createParallelGroup()
						.addComponent(btnStart)
				)
		);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setTitle(messages.getString("initialisation"));
		pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		List<String> users= new ArrayList<>();
				
		try {
			
			if(e.getSource() == btnStart){
				
				if(txtMapName.getText().isEmpty() || txtMapName.getText().equals(null))
					throw new IllegalStateException("mapNameException");
				else
					domainController.startGame(txtMapName.getText());
				
				for(int i= 0; i < 6; i++){
					if(!(txtUsers[i].getText().isEmpty() || txtUsers[i].getText().equals("")))
						users.add(txtUsers[i].getText());
				}
				
				//clear users with every call
				domainController.clearUsers();
				if(users.size() >= 2){
					domainController.clearUsers();
					for(int i= 0; i < users.size(); i++)
						domainController.createUser(users.get(i));
				}
				else
					throw new IllegalStateException("userAmountException");
				
				int map= mapList.getSelectedIndex();
				
				if(map != -1){
					domainController.generateMap(map+1);
					domainController.divideMap();
				} else
					throw new IllegalStateException("selectMapException");
				
				System.out.print(domainController.displayMap());
				new GameGUI(this.messages, this.domainController);
				this.dispose();
			}
			
			
		} catch (IllegalStateException e1) { 
			JOptionPane.showMessageDialog(null, messages.getString(e1.getMessage()), "Error!", JOptionPane.ERROR_MESSAGE);
			domainController.clearUsers();
		} catch (ClassNotFoundException e1) {
			JOptionPane.showMessageDialog(null, messages.getString(e1.getMessage()), "Error!", JOptionPane.ERROR_MESSAGE);
			domainController.clearUsers();
		} catch (UserAlreadyExistsException e1) {
			JOptionPane.showMessageDialog(null, messages.getString(e1.getMessage()), "Error!", JOptionPane.ERROR_MESSAGE);
			domainController.clearUsers();
		} catch(IllegalArgumentException e1){
			JOptionPane.showMessageDialog(null, messages.getString(e1.getMessage()), "Error!", JOptionPane.ERROR_MESSAGE);
			domainController.clearUsers();
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, messages.getString(e1.getMessage()), "Error!", JOptionPane.ERROR_MESSAGE);
			domainController.clearUsers();
		}
	}
}
