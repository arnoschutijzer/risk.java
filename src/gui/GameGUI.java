/**
* @author Arno Schutijzer & Thijs van der Burgt
**/

package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import domain.DomainController;
import exceptions.IllegalGameSquareException;
import exceptions.InsufficientArmy;

@SuppressWarnings("serial")
public class GameGUI extends JFrame implements MouseListener, ActionListener {
	
	private int attackX= -1, attackY= -1, defendX= -1, defendY= -1; //default
	private int currentUser= 0;
	private boolean conquered= false;
	private String[] squareColors = {"yellow", "black", "green", "red", "blue", "white", "water"};
	private JPanel attPanel, defPanel, hiscorePanel;
	private GameBoardJPanel mapPanel = new GameBoardJPanel();
	private JLabel lblAttCountryName = new JLabel(), lblAttUserName = new JLabel(), lblAttArmy = new JLabel(),
			lblAttContinent = new JLabel(), lblDefCountryName = new JLabel(), lblDefUserName = new JLabel(), lblDefArmy = new JLabel(), lblDefContinent = new JLabel();
	private JButton btnAttack, btnExchange, btnEndTurn;
	private DomainController domainController;
	private JLabel lblUserTurn = new JLabel(), lblArmiesToPlace = new JLabel();
	private Messages messages;
	
	private JLabel lblAttDice = new JLabel(), lblDefDice = new JLabel();
	private JTextField txtAttDice = new JTextField(), txtDefDice = new JTextField();
	
	public GameGUI(Messages messages, DomainController domainController){
		this.domainController = domainController;
		this.messages = messages;
		
		
		currentUser= domainController.calcTurn();
		
		domainController.getUser(currentUser).setTurn(true);
		domainController.getUser(currentUser).addUnassignedArmy(domainController.calcArmy(domainController.getUser(currentUser)));
		refreshLabels();
		
		try {
			this.paintMap();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, messages.getString("criticalError"));
		}
		
		mapPanel.addMouseListener(this);
		mapPanel.setBorder(new LineBorder(Color.black, 2));
		attPanel = this.paintAttDefPanel(messages.getString("attacker")+ ": ", lblAttCountryName, lblAttUserName, lblAttArmy, lblAttContinent);
		defPanel = this.paintAttDefPanel(messages.getString("defender") + ": ", lblDefCountryName, lblDefUserName, lblDefArmy, lblDefContinent);
		hiscorePanel = this.paintScores();
		
		//String param is used as ActionCommand in ActionListener
		btnAttack = this.paintButton("attack");
		btnEndTurn = this.paintButton("endTurn");
		btnExchange = this.paintButton("exchange");
		
		lblAttDice.setText("Att count");
		lblDefDice.setText("Def count");
		
		txtAttDice.setMaximumSize(new Dimension(100, JTextField.HEIGHT));
		txtDefDice.setMaximumSize(new Dimension(100, JTextField.HEIGHT));
		
		//Initialize Frame
		GroupLayout layout = new GroupLayout(this.getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
		
		//Horizontal group
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup()
							.addComponent(mapPanel))
						.addGroup(layout.createParallelGroup()
								.addComponent(lblUserTurn)
								.addGap(20)
								.addComponent(lblArmiesToPlace)
							.addGroup(layout.createSequentialGroup()
								.addComponent(attPanel)
								.addComponent(defPanel))
							.addGroup(layout.createSequentialGroup()
									.addComponent(hiscorePanel)
									.addGroup(layout.createSequentialGroup()
											.addGroup(layout.createParallelGroup()
													.addComponent(lblAttDice)
													.addComponent(lblDefDice))
											.addGroup(layout.createParallelGroup()
													.addComponent(txtAttDice)
													.addComponent(txtDefDice))))))
				.addGroup(layout.createSequentialGroup()
						.addComponent(btnAttack)
						.addComponent(btnExchange)
						.addComponent(btnEndTurn)));
		
		//Vertical group
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(mapPanel)
						.addGroup(layout.createSequentialGroup()
								.addComponent(lblUserTurn)
								.addGap(20)
								.addComponent(lblArmiesToPlace)
							.addGroup(layout.createParallelGroup()
								.addComponent(attPanel)
								.addComponent(defPanel))
							.addGroup(layout.createParallelGroup()
									.addComponent(hiscorePanel)
									.addGroup(layout.createSequentialGroup()
											.addGap(20)
											.addGroup(layout.createParallelGroup(Alignment.BASELINE)
													.addComponent(lblAttDice)
													.addComponent(txtAttDice))
											.addGroup(layout.createParallelGroup(Alignment.BASELINE)
													.addComponent(lblDefDice)
													.addComponent(txtDefDice))))))
				.addGroup(layout.createParallelGroup()
						.addComponent(btnAttack)
						.addComponent(btnExchange)
						.addComponent(btnEndTurn)));
		
		//Visualisation options
		this.setVisible(true);
		this.setTitle("Risk Revenge - "+this.domainController.getGameName());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		this.setLocationRelativeTo(null);
	}
	
	private JPanel paintScores(){
		
		JPanel pane= new JPanel();
		pane.setBorder(new LineBorder(Color.black, 1, false));
		
		int users= domainController.getUsers().size();
		List<JLabel> lblImages= new ArrayList<>();
		JLabel[] lblUsers= new JLabel[users];
		ImageIcon[] images= new ImageIcon[users];
		
		for(int i= 0; i< users; i++){
			images[i]= new ImageIcon("lib/images/icons/"+domainController.getUser(i).getSymbol().getSymbol()+".png");
			lblUsers[i]= new JLabel();
		}
		
		for(int i= 0; i< users; i++){
			lblImages.add(new JLabel(images[i]));
		}
		
		for(int i= 0; i< users; i++){
			lblUsers[i].setText(domainController.getUser(i).toString());
		}
		
		GroupLayout layout= new GroupLayout(pane);
		pane.setLayout(layout);
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
		
		ParallelGroup pGroup= layout.createParallelGroup();
		SequentialGroup sGroup= layout.createSequentialGroup();
		
		ParallelGroup pGroupUsernames= layout.createParallelGroup();
		SequentialGroup sGroupUsernames= layout.createSequentialGroup();
		
		int index= 0;
		for(JLabel lbl: lblImages){
			pGroup.addComponent(lbl);
			sGroup.addComponent(lbl);
			sGroup.addGap(15);
			
			pGroupUsernames.addComponent(lblUsers[index]);
			sGroupUsernames.addComponent(lblUsers[index]);
			sGroupUsernames.addGap(32);
			index++;
		}
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(pGroup)
				.addGroup(pGroupUsernames));
		
		layout.setVerticalGroup(layout.createParallelGroup()
				.addGroup(sGroup)
				.addGroup(sGroupUsernames));
		
		pane.setVisible(true);
		Dimension dim = new Dimension(210, 10);
		pane.setMinimumSize(dim);
		return pane;
	}
	
	private JPanel paintAttDefPanel(String userType, JLabel countryName, JLabel userName, JLabel armyCount, JLabel continentNr){
		JPanel pane = new JPanel();
		pane.setBorder(new LineBorder(Color.black, 1, false));
		JLabel lblType = new JLabel(userType), lblUser = new JLabel(messages.getString("player")+ ": "), lblArmy = new JLabel(messages.getString("army") + ": "), lblContinent = new JLabel(messages.getString("continent") + ": ");
		
		GroupLayout layout = new GroupLayout(pane);
		pane.setLayout(layout);
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
		
		//Horizontal
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(lblType)
						.addComponent(lblUser)
						.addComponent(lblArmy)
						.addComponent(lblContinent))
				.addGroup(layout.createParallelGroup()
						.addComponent(countryName)
						.addComponent(userName)
						.addComponent(armyCount)
						.addComponent(continentNr)));
		//Vertical
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(lblType)
						.addComponent(countryName))
				.addGroup(layout.createParallelGroup()
						.addComponent(lblUser)
						.addComponent(userName))
				.addGroup(layout.createParallelGroup()
						.addComponent(lblArmy)
						.addComponent(armyCount))
				.addGroup(layout.createParallelGroup()
						.addComponent(lblContinent)
						.addComponent(continentNr)));
		
		pane.setVisible(true);
		Dimension dim = new Dimension(210,120);
		pane.setMinimumSize(dim);
		pane.setMaximumSize(dim);
		pane.setPreferredSize(dim);
		return pane;
	}
	
	private JButton paintButton(String text){
		Dimension dim = new Dimension(180,50);
		//Construct new button using messages.getString(text)
		JButton button= new JButton(messages.getString(text));
		
		button.setMaximumSize(dim);
		button.setMinimumSize(dim);
		button.addActionListener(this);
		button.setActionCommand(text);
		return button;
	}
	
	private void paintMap() throws IOException{
		int rows = mapPanel.getRows(), cols = mapPanel.getColumns(), width = mapPanel.getSquareWidth();
		int mapSize = rows*width;
		final int SYMBOLMARGIN = 8;
		BufferedImage image = new BufferedImage(mapSize, mapSize, BufferedImage.TYPE_INT_RGB);
        BufferedImage[] colors = new BufferedImage[7];
        
        for(int i = 0; i<colors.length; i++){
        	colors[i] = ImageIO.read(new File("lib/images/" + squareColors[i] + ".png"));
        }
        
        String[][] squareColorArray = domainController.gameSquareColorsStringArray();
        Graphics2D g = (Graphics2D) image.createGraphics();  
        BufferedImage gsColor = null;

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 480, 480);

        for(int i = 0; i < cols; i++){
        	for(int a = 0 ; a < rows; a++){
        		switch(squareColorArray[a][i]){
        		case "yellow":
        			gsColor = colors[0];
        			break;
        		case "black":
        			gsColor = colors[1];
        			break;
        		case "green":
        			gsColor = colors[2];
        			break;
        		case "red":
        			gsColor = colors[3];
        			break;
        		case "blue":
        			gsColor = colors[4];
        			break;
        		case "white": 
        			gsColor = colors[5];
        			break;
        		case "water":
        			gsColor = colors[6];
        			break;
        		}
        		g.drawImage(gsColor, a*width, i*width, null);
        	}
        	
        }
        
        //drawSymbols
        BufferedImage symbol = null;
        String[][] gsSymbols = domainController.gameSquareSymbolStringArray();
        for(int i = 0; i<cols; i++){
        	for(int a=0; a<rows; a++){
        		if(!gsSymbols[a][i].equals("")){
        			symbol = ImageIO.read(new File("lib/images/icons/" + gsSymbols[a][i] + ".png"));
        			g.drawImage(symbol, SYMBOLMARGIN+a*width, SYMBOLMARGIN+i*width, null);
        		}
        	}
        }
        
        
        //drawcols
        g.setColor(Color.black);
        for (int i = 1; i < cols; i++) {
            g.fillRect(i * width, 0, 2, 480);
        }

        //drawrows
        g.setColor(Color.black);
        for (int i = 1; i < rows; i++) {
            g.fillRect(0, i * width, 480, 2);
        }


        mapPanel.setDimensions();
        mapPanel.setImage(image);
        
	}
	
	//only called when Attack-button is clicked and attack-method has ended
	//or attackX/Y && defendX/Y != -1
	public void refreshAttDefPanels(){
		lblAttArmy.setText(""+domainController.getArmy(attackX, attackY));
		lblAttUserName.setText(domainController.getGameSquareRepository().getUser(attackX, attackY).toString());
		
		lblDefArmy.setText(""+domainController.getArmy(defendX, defendY));
		lblDefUserName.setText(domainController.getGameSquareRepository().getUser(defendX, defendY).toString());
	}
	
	public void resetAttDefPanels(){
		lblAttArmy.setText("");
		lblAttUserName.setText("");
		lblAttCountryName.setText("");
		lblAttContinent.setText("");
		
		lblDefArmy.setText("");
		lblDefUserName.setText("");
		lblDefCountryName.setText("");
		lblDefContinent.setText("");
	}
	
	//can be called any time
	public void refreshLabels(){
		lblUserTurn.setText(messages.getString("userTurn")+domainController.getUser(currentUser));
		lblArmiesToPlace.setText(messages.getString("armiesToPlace")+domainController.getUser(currentUser).getUnassignedArmy());
	}
	
	
	public int strengthenContinent(){
		int[] countriesPerContinent= {12, 11, 15, 15, 15, 9};
		int[] countriesPerContinent2= {12, 11, 15, 15, 15, 9};
		int continentNr, ret= 0;

		for(int i= 0; i< domainController.getGameSquareRepository().getDIMY(); i++)
			for(int j= 0; j< domainController.getGameSquareRepository().getDIMX(); j++)
				if(domainController.getLand(j, i) != null)
					if( domainController.getUser(j, i)== domainController.getUser(currentUser) ){
						if(domainController.getContinentNumbr(i, j) != -1){
							continentNr= domainController.getContinentNumbr(i, j);
							countriesPerContinent[continentNr]--;
						}
					}

		for(int i= 0; i< countriesPerContinent.length; i++)
			if(countriesPerContinent[i] == 0)
				ret+= countriesPerContinent2[i];

		return Math.max(3,ret/3);
	}
	
	public boolean checkWin(){
		int countries= 0;
		
		for(int i= 0; i< domainController.getGameSquareRepository().getDIMY(); i++)
			for(int j= 0; j< domainController.getGameSquareRepository().getDIMX(); j++)
				if(domainController.getLand(j, i) != null)
					if (domainController.getUser(j, i) == domainController.getUser(currentUser) )
						countries++;
		
		if(countries<  76)
			return false;
		else
			return true;
	}
	
	public void mouseReleased(MouseEvent e) {
		
		try{
			if(domainController.getUser(currentUser).getUnassignedArmy() != 0){

				int x= e.getX()/mapPanel.getSquareWidth();
				int y= e.getY()/mapPanel.getSquareWidth();

				if( domainController.getUser(x, y).isTurn() ){
					domainController.addArmy(x, y);
					domainController.getUser(currentUser).addUnassignedArmy(-1);

					lblUserTurn.setText(messages.getString("userTurn") + domainController.getUser(currentUser));
					lblArmiesToPlace.setText(messages.getString("armiesToPlace") + domainController.getUser(currentUser).getUnassignedArmy());
				}
				else
					throw new IllegalGameSquareException("countryNotOwnedException");
			}

			if( domainController.getGameSquare(e.getX()/mapPanel.getSquareWidth(), e.getY()/mapPanel.getSquareWidth()).getType().equals("Land") && domainController.getGameSquareRepository().getUser(e.getX()/mapPanel.getSquareWidth(), e.getY()/mapPanel.getSquareWidth()).isTurn() ){
				attackX= e.getX()/mapPanel.getSquareWidth();
				attackY= e.getY()/mapPanel.getSquareWidth();

				lblAttCountryName.setText(messages.getString(domainController.getGameSquare(attackX, attackY).getName() ));
				lblAttUserName.setText(domainController.getGameSquareRepository().getUser(attackX, attackY).toString() );
				lblAttArmy.setText(""+domainController.getArmy(attackX, attackY));
				lblAttContinent.setText(messages.getString("continent"+domainController.getContinentNumber(attackX, attackY)));

				this.attPanel.repaint();
			}

			if( domainController.getGameSquare(e.getX()/mapPanel.getSquareWidth(), e.getY()/mapPanel.getSquareWidth()).getType().equals("Land") && !( domainController.getGameSquareRepository().getUser(e.getX()/mapPanel.getSquareWidth(), e.getY()/mapPanel.getSquareWidth()).isTurn() ) ){
				defendX= e.getX()/mapPanel.getSquareWidth();
				defendY= e.getY()/mapPanel.getSquareWidth();

				lblDefCountryName.setText(messages.getString(domainController.getGameSquare(defendX, defendY).getName() ));
				lblDefUserName.setText(domainController.getGameSquareRepository().getUser(defendX, defendY).toString() );
				lblDefArmy.setText(""+domainController.getArmy(defendX, defendY));
				lblDefContinent.setText(messages.getString("continent"+domainController.getContinentNumber(defendX, defendY)));

				this.defPanel.repaint();
			}
		}
		catch(IllegalGameSquareException ex){
			JOptionPane.showMessageDialog(null, messages.getString(ex.getMessage()));
		}
		
	}

	public void mouseExited(MouseEvent e) {
		((JComponent) e.getComponent()).setBorder(new LineBorder(Color.black, 1, false));
	}
	
	public void mouseEntered(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}

	//actionPerformed for exchange-, attack-, endTurn-buttons
	public void actionPerformed(ActionEvent a) {
				
		try{
			switch(a.getActionCommand()){
			case "attack":

				if(attackX== -1 || attackY== -1 || defendY== -1 || defendX== -1)
					throw new IllegalStateException();
				if(domainController.getUser(attackX, attackY) == domainController.getUser(defendX, defendY))
					throw new IllegalStateException("attackSelfException");
				else{
					int aArmy= domainController.getArmy(attackX, attackY);
					int dArmy= domainController.getArmy(defendX, defendY);
					int attDice, defDice;

					if(aArmy-1 > 3)
						attDice= 3;
					else if(aArmy == 1)
						attDice= 1;
					else
						attDice= aArmy-1;

					if(dArmy-1 > 2)
						defDice= 2;
					else if(dArmy== 1)
						defDice= 1;
					else
						defDice= dArmy-1;

					
					conquered= domainController.attack(attackX, attackY, defendX, defendY, attDice, defDice);

					refreshAttDefPanels();
					
					if(conquered){
						paintMap();
						if(checkWin())
							JOptionPane.showMessageDialog(null, "winning");
							//screen to notify user has won
					}
				}
				break;

			case "exchange": 

				if(! domainController.getUser(currentUser).getCards().isEmpty())
					new ExchangeGUI(domainController, messages, currentUser);
				else
					JOptionPane.showMessageDialog(null, messages.getString("noSymbolCards"));	
				
				refreshLabels();
				break;

			case "endTurn":
				
				if(domainController.getUser(currentUser).getUnassignedArmy() == 0){
					if(domainController.getUser(currentUser).getCards().size()>= 5)
						throw new IllegalArgumentException("tooManyCardsException");
					
					int nextUser= currentUser+1;
					
					if(nextUser== domainController.getUsers().size()){
						nextUser= 0;
					}
					
					domainController.getUser(currentUser).setTurn(false);
					domainController.getUser(nextUser).setTurn(true);
					domainController.getUser(currentUser).addUnassignedArmy(this.strengthenContinent());
					
					if(conquered)
						domainController.getUser(currentUser).addSymbolCard();
					
					currentUser= nextUser;
					
					//only after first turn of every user -> no-one receives army in their first turn
					domainController.getUser(currentUser).addUnassignedArmy(domainController.calcArmy(domainController.getUser(currentUser)));
					System.out.print(strengthenContinent());
					conquered= false;
					attackX= -1; attackY= -1; defendX= -1; defendY= -1;
					
					refreshLabels();
					resetAttDefPanels();
				}
				else
					throw new IllegalStateException("endTurnUnassignedArmyException");
				break;
			}
			
		}
		catch(IllegalStateException | ClassCastException | InsufficientArmy | IllegalGameSquareException | NullPointerException | IllegalArgumentException e){
			JOptionPane.showMessageDialog(null, this.messages.getString(e.getMessage()));
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getCurrentUser(){
		return currentUser;
	}
	
}