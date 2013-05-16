package persistance;
import java.sql.Connection;


import java.sql.SQLException;
import java.util.*;

import domain.baseclass.GameSquare;
import domain.baseclass.Map;

/**
 * This class delegates everything between the application layer and the persitance layer.
 * @author Thijs van der Burgt
 * @author Arno Schutijzer
 */
public class PersistanceController {
	private static PersistanceController persistanceController;
	private GameSquareMapper gameSquareMapper;
	private MapMapper mapMapper;
	private DatabaseConnection databaseConnection;
	/**
	 * This method creates an instance of PersistanceController.
	 * @return An object of PersistanceController
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static PersistanceController getInstance() throws ClassNotFoundException, SQLException {
		if (persistanceController == null)
			persistanceController = new PersistanceController();
		return persistanceController;
	}
	/**
	 * Default constructor for PersistanceController
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private PersistanceController() throws ClassNotFoundException, SQLException{
		databaseConnection = new DatabaseConnection();
		gameSquareMapper = new GameSquareMapper();
		mapMapper= new MapMapper();
	}
	/**
	 * This method calls the method generateMap from GameSquareMapper.
	 * @param mapNr
	 * @param dimX
	 * @param dimY
	 * @return A twodimension array of GameSquare
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public GameSquare[][] generateMap(int mapNr, int dimX, int dimY) throws ClassNotFoundException, SQLException {
		return gameSquareMapper.generateMap(mapNr, dimX, dimY);
	}
	
	public List<Map> getMaps() throws ClassNotFoundException, SQLException{
		return mapMapper.getMaps();
	}
	
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		return databaseConnection.getConnection();
	}
	/**
	 * Closes the database connection.
	 * @throws SQLException
	 */
	public void closeConnection() throws SQLException{
		databaseConnection.closeConnection();
	}
}
