package persistance;

import java.sql.*;

import domain.baseclass.GameSquare;
import domain.baseclass.Land;
import domain.baseclass.Water;
/**
 * This class maps the records from the database to a GameSquare object.
 * @author Thijs van der Burgt
 * @author Arno Schutijzer
 *
 */
public class GameSquareMapper {
	/**
	 * This method queries the database with a specified ID and maps the view to GameSquare objects.
	 * @param mapNr
	 * @param dimX
	 * @param dimY
	 * @return A two dimensional array of GameSquare.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public GameSquare[][] generateMap(int mapNr, int dimX, int dimY) throws ClassNotFoundException, SQLException {
		GameSquare[][] map = new GameSquare[dimX][dimY];
		Statement statement;
		
		Connection connection = PersistanceController.getInstance().getConnection();
		statement = connection.createStatement();
		
		ResultSet result = statement.executeQuery("SELECT * FROM GameSquare WHERE MapID = " + mapNr);
		
		while(result.next()){
			GameSquare objGameSquare;
			String name;
			int continentNumber, x, y;
			String color;
			
			name = result.getString("Name");
			continentNumber = result.getInt("Continentnr");
			x = result.getInt("DimX");
			y = result.getInt("DimY");
			
			color = result.getString("Color");
			
			
			if(result.getString("IsLand").equals("True")){
				objGameSquare = new Land(name, "Land", color, continentNumber, dimX, dimY);
			}else{
				objGameSquare = new Water(name, "Water", color, dimX, dimY);
			}
			
			map[x][y] = objGameSquare;
		}
		result.close();
		PersistanceController.getInstance().closeConnection();
		return map;
	}
	
}
