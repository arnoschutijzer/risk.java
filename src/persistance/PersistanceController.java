/**
* @author Arno Schutijzer & Thijs van der Burgt
**/

package persistance;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import domain.baseclass.GameSquare;
import domain.baseclass.Map;
public class PersistanceController {
	private static PersistanceController persistanceController;
	private GameSquareMapper gameSquareMapper;
	private MapMapper mapMapper;
	private DatabaseConnection databaseConnection;
	
	public static PersistanceController getInstance() throws ClassNotFoundException, SQLException {
		if (persistanceController == null)
			persistanceController = new PersistanceController();
		return persistanceController;
	}
	
	private PersistanceController() throws ClassNotFoundException, SQLException{
		databaseConnection = new DatabaseConnection();
		gameSquareMapper = new GameSquareMapper();
		mapMapper= new MapMapper();
	}

	public GameSquare[][] generateMap(int mapNr, int dimX, int dimY) throws ClassNotFoundException, SQLException {
		return gameSquareMapper.generateMap(mapNr, dimX, dimY);
	}
	
	public List<Map> getMaps() throws ClassNotFoundException, SQLException{
		return mapMapper.getMaps();
	}
	
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		return databaseConnection.getConnection();
	}
	
	public void closeConnection() throws SQLException{
		databaseConnection.closeConnection();
	}
}
