package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	
	private static final String DATABASE_NAME = "cinema.db";

	private static final String FILE_SEPARATOR = System.getProperty("file.separator");
	private static final String WINDOWS_PATH = "C:" + FILE_SEPARATOR + "Temp" + FILE_SEPARATOR + "Cinema_DB_Script" + FILE_SEPARATOR + DATABASE_NAME;
	//private static final String LINUX_PATH = "SQLite" + FILE_SEPARATOR + DATABASE_NAME;

	private static final String PATH = WINDOWS_PATH;
	
	
	private static Connection connection;

	public static void open() {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:" + PATH); // folder-i u stazi moraju biti kreirani, ali .db datoteka ne mora
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		
		return connection;
	}

	public static void close() {
		try {
			connection.close();
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}

}
