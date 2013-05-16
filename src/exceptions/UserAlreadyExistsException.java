/**
* @author Arno Schutijzer 
* @author Thijs van der Burgt
**/

package exceptions;

@SuppressWarnings("serial")
public class UserAlreadyExistsException extends Exception {
	
	public UserAlreadyExistsException(){
		super("User already exists!");
	}
	public UserAlreadyExistsException(String exception){
		super(exception);
	}
}
