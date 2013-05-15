/**
* @author Arno Schutijzer & Thijs van der Burgt
* @deprecated
**/

package testui;

import java.sql.SQLException;

import domain.DomainController;
import exceptions.IllegalGameSquareException;
import exceptions.InsufficientArmy;
import exceptions.UserAlreadyExistsException;

public class TestUI {
	public TestUI(DomainController domainController){
		domainController.startGame("please");
		try {
			domainController.createUser("Jossh");
			domainController.createUser("Mario");
			domainController.createUser("Luigi");
		} catch (UserAlreadyExistsException e) {
			System.out.println("User already exists");
		}
		System.out.println(domainController.getUsers());
		try {
			domainController.generateMap(1);
			domainController.divideMap();
			System.out.println(domainController.displayMap());
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			domainController.attack(2,3,2,4,1,2);
		} catch (ClassCastException | IllegalGameSquareException | InsufficientArmy e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
