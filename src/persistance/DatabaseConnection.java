/**
* @author Arno Schutijzer & Thijs van der Burgt
**/

package persistance;

import java.sql.*;

public class DatabaseConnection {
	private final static String JDBC = "org.sqlite.JDBC",
								SQLITEPATH = "jdbc:sqlite:db/RiskProject";
	private Connection connection;

	public DatabaseConnection() throws ClassNotFoundException, SQLException{
		Class.forName(JDBC);
		connection = DriverManager.getConnection(SQLITEPATH);
	}
	
	public void closeConnection() throws SQLException{
		connection.close();
	}
	
	public Connection getConnection() throws SQLException, ClassNotFoundException{
		if(connection == null || connection.isClosed()){
			Class.forName(JDBC);
			connection = DriverManager.getConnection(SQLITEPATH);
		} 
		return this.connection;
	}
	
}
