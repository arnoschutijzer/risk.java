/**
* @author Arno Schutijzer & Thijs van der Burgt
**/

package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameBoardJPanel extends JPanel{
	private BufferedImage image;
	private final int COLUMNS = 10, ROWS = 10, SQUAREWIDTH = 48;
	
	public GameBoardJPanel() {
		this.setDimensions();
	}

	public void setDimensions() {
        Dimension size = new Dimension(SQUAREWIDTH*COLUMNS, SQUAREWIDTH*ROWS);
        this.setPreferredSize(size);
        this.setMaximumSize(size);
        this.setMinimumSize(size);
        this.setSize(size);
        this.repaint();
	}	
	
	public int getSquareWidth(){
		return this.SQUAREWIDTH;
	}
	
	public int getColumns(){
		return this.COLUMNS;
	}
	
	public int getRows(){
		return this.ROWS;
	}
	
	public void setImage(BufferedImage image){
		this.image = image;
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(image, 0 , 0 , null);
	}
	
	public Dimension getDimension(){
		return new Dimension(this.getWidth(), this.getHeight());
	}
}
