package domain.baseclass;
/**
 * This class is used to define a map.
 * @author Thijs van der Burgt
 * @author Arno Schutijzer
 *
 */
public class Map {
	private String name;
	private int number;
	
	public Map(int number, String name){
		this.setName(name);
		this.setNumber(number);
	}
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) throws IllegalArgumentException{
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) throws IllegalArgumentException{
		this.name = name;
	}
	
}
