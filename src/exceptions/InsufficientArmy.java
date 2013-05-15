/**
* @author Arno Schutijzer & Thijs van der Burgt
**/

package exceptions;

@SuppressWarnings("serial")
public class InsufficientArmy extends Exception {
	public InsufficientArmy(){
		super();
	}
	
	public InsufficientArmy(String argument){
		super(argument);
	}
}
