/**
* @author Arno Schutijzer
* @author Thijs van der Burgt
**/

package exceptions;

@SuppressWarnings("serial")
public class IllegalGameSquareException extends Exception{
	public IllegalGameSquareException(){
		super();
	}
	public IllegalGameSquareException(String argument){
		super(argument);
	}
}
