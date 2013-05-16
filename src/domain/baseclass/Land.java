package domain.baseclass;
/**
 * This class extends the implementation of GameSquare.
 * @author Thijs van der Burgt
 * @author Arno Schutijzer
 *
 */
public class Land extends GameSquare{
	private final int DEFAULTARMY = 2;
	private int army;
	private User user;
	private int continentNumber;
	/**
	 * Constructor calls the constructor of GameSquare and sets the default army and a continentnumber.
	 * @param name
	 * @param type
	 * @param color
	 * @param continentNumber
	 * @param dimX
	 * @param dimY
	 */
	public Land(String name, String type, String color,int continentNumber , int dimX, int dimY){
		super(name, type, color, dimX, dimY);
		this.setArmy(DEFAULTARMY);
		this.setContinentNumber(continentNumber);
	}
	/**
	 * Constructor calls the constructor of GameSquare and sets a specified army and a continentnumber.
	 * @param name
	 * @param trype
	 * @param color
	 * @param continentNumber
	 * @param dimX
	 * @param dimY
	 * @param army
	 */
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
	/**
	 * Returns the value of the name attribute of Land.
	 */
	public String toString(){
		return super.getName();
	}
}
