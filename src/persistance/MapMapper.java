package persistance;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import domain.baseclass.Map;
/**
 * This class maps the records from the database to objects of Map
 * @author Thijs van der Burgt
 * @author Arno Schutijzer
 *
 */
public class MapMapper {
	/**
	 * 
	 * @return An array of Map objects.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Map> getMaps() throws ClassNotFoundException, SQLException{
		List<Map> maps = new ArrayList<>();
		
		Statement statement;
		Connection connection = PersistanceController.getInstance().getConnection();
		statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM Map");
		
		while(resultSet.next()){
			int number = resultSet.getInt("MapID");
			String nameString = resultSet.getString("Name");
			Map map = new Map(number, nameString);
			maps.add(map);
		}
		
		resultSet.close();
		PersistanceController.getInstance().closeConnection();
		return maps;
	}
}
