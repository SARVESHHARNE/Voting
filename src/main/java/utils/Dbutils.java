package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbutils {
	private static Connection connection;
	
	public static void openConnection(String url,String uName,String pass) throws SQLException {
		connection=DriverManager.getConnection(url, uName, pass);
	}
	
	public static void closeConnection() throws SQLException {
		connection.close();
	}

	public static Connection getConnection() {
		return connection;
	}
}
