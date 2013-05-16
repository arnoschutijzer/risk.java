package domain.baseclass;
/**
 * This class extends the implementation of GameSquare.
 * @author Thijs van der Burgt
 * @author Arno Schutijzer
 *
 */
public class Water extends GameSquare{
	/**
	 * Default constructor for water.
	 * @param name
	 * @param type
	 * @param color
	 * @param dimX
	 * @param dimY
	 */
	public Water(String name, String type, String color, int dimX, int dimY){
		super(name, type, color, dimX, dimY);
	}
}