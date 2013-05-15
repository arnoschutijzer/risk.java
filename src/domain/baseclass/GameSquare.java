/**
* @author Arno Schutijzer & Thijs van der Burgt
**/

package domain.baseclass;

public abstract class GameSquare {
	private String name, type;
	private String color;
	private int dimX, dimY;

	public GameSquare(String name, String type, String color, int dimX, int dimY) {
		this.setName(name);
		this.setType(type);
		this.setColor(color);
		this.setDimX(dimX);
		this.setDimY(dimY);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws IllegalArgumentException {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) throws IllegalArgumentException {
		this.type = type;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) throws IllegalArgumentException {
		this.color = color;
	}

	public int getDimX() {
		return dimX;
	}

	public void setDimX(int dimX) throws IllegalArgumentException{
		this.dimX = dimX;
	}

	public int getDimY() {
		return dimY;
	}

	public void setDimY(int dimY) throws IllegalArgumentException{
		this.dimY = dimY;
	}
	
}
