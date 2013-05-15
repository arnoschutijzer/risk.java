/**
* @author Arno Schutijzer & Thijs van der Burgt
**/

package domain.baseclass;

public class Land extends GameSquare{
	private final int DEFAULTARMY = 2;
	private int army;
	private User user;
	private int continentNumber;
	
	public Land(String name, String type, String color,int continentNumber , int dimX, int dimY){
		super(name, type, color, dimX, dimY);
		this.setArmy(DEFAULTARMY);
		this.setContinentNumber(continentNumber);
	}
	
	public Land(String name, String trype, String color , int continentNumber, int dimX, int dimY, int army){
		super(name, trype, color, dimX, dimY);
		this.setArmy(army);
		this.setContinentNumber(continentNumber);
	}
	public int getArmy() {
		return army;
	}
	public void setArmy(int army) throws IllegalArgumentException{
		this.army = army;
	}
	public void addArmy(){
		this.army++;
	}
	public int getContinentNumber() {
		return continentNumber;
	}
	public void setContinentNumber(int continentNumber) {
		this.continentNumber = continentNumber;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) throws IllegalArgumentException{
		this.user = user;
	}
	public String toString(){
		return super.getName();
	}
}
