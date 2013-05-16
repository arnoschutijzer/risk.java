package persistance;

import java.sql.*;
/**
 * This class maintains the connection between the application and the SQLite database.
 * @author Thijs van der Burgt
 * @author Arno Schutijzer
 *
 */
public class DatabaseConnection {
	private final static String JDBC = "org.sqlite.JDBC",
								SQLITEPATH = "jdbc:sqlite:db/RiskProject";
	private Connection connection;

	/**
	 * Default constructor for DatabaseConnection
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public DatabaseConnection() throws ClassNotFoundException, SQLException{
		Class.forName(JDBC);
		connection = DriverManager.getConnection(SQLITEPATH);
	}
	/**
	 * This method closes the connection between the application and the database.
	 * This primary to reduce the memory cost.
	 * @throws SQLException
	 */
	public void closeConnection() throws SQLException{
		connection.close();
	}
	
	/**
	 * This method return an existing connection. Otherwise it creates a new one.
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Connection getConnection() throws SQLException, ClassNotFoundException{
		if(connection == null || connection.isClosed()){
			Class.forName(JDBC);
			connection = DriverManager.getConnection(SQLITEPATH);
		} 
		return this.connection;
	}
	
}
