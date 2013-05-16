package domain.baseclass;
/**
 * This abstract class is used to define the base functionality and properties of a GameSquare.
 * @author Thijs van der Burgt
 * @author Arno Schutijzer
 *
 */
public abstract class GameSquare {
	private String name, type;
	private String color;
	private int dimX, dimY;
	/**
	 * Default constructor of a gamesquare.
	 * @param name
	 * @param type
	 * @param color
	 * @param dimX
	 * @param dimY
	 */
	public GameSquare(String name, String type, String color, int dimX, int dimY) {
		this.setName(name);
		this.setType(type);
		this.setColor(color);
		this.setDimX(dimX);
		this.setDimY(dimY);
	}
	/**
	 * 
	 * @return GameSquare name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Sets the name of a GameSquare.
	 * @param name
	 * @throws IllegalArgumentException
	 */
	public void setName(String name) throws IllegalArgumentException {
		this.name = name;
	}
	/**
	 * 
	 * @return GameSquare type
	 */
	public String getType() {
		return type;
	}
	/**
	 * Sets the type of a GameSquare.
	 * @param type
	 * @throws IllegalArgumentException
	 */
	public void setType(String type) throws IllegalArgumentException {
		this.type = type;
	}
	/**
	 * 
	 * @return GameSquare color (String)
	 */
	public String getColor() {
		return color;
	}
	/**
	 * Sets the color of a GameSquare.
	 * @param color
	 * @throws IllegalArgumentException
	 */
	public void setColor(String color) throws IllegalArgumentException {
		this.color = color;
	}
	/**
	 * 
	 * @return The horizontal position of a GameSquare.
	 */
	public int getDimX() {
		return dimX;
	}
	/**
	 * Sets the horizontal position of a GameSquare.
	 * @param dimX
	 * @throws IllegalArgumentException
	 */
	public void setDimX(int dimX) throws IllegalArgumentException{
		this.dimX = dimX;
	}
	/**
	 * 
	 * @return The vertical position of a GameSquare.
	 */
	public int getDimY() {
		return dimY;
	}
	/**
	 * Sets the vertical positon of a GameSquare.
	 * @param dimY
	 * @throws IllegalArgumentException
	 */
	public void setDimY(int dimY) throws IllegalArgumentException{
		this.dimY = dimY;
	}
	
}
