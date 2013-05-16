import java.sql.SQLException;
import gui.LanguageGUI;
import domain.DomainController;
/**
 * The startup class of the Risk game implementation.
 * @author Thijs van der Burgt
 * @author Arno Schutijzer
 */
public class StartUp {
	/**
	 * The main lass used for launching the application.
	 * @param args
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void main(String args[]) throws ClassNotFoundException, SQLException {
		new LanguageGUI(new DomainController());
	}
}