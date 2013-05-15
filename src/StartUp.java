/**
* @author Arno Schutijzer & Thijs van der Burgt
**/

import java.sql.SQLException;

import gui.LanguageGUI;
import domain.DomainController;

public class StartUp {
	public static void main(String args[]) throws ClassNotFoundException, SQLException {
		new LanguageGUI(new DomainController());
	}
}