package de.hermes.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import de.hermes.model.User;
import de.hermes.model.ListeUser;
import de.hermes.model.MyResult;




public class MySQL
{
	private final static String	URL				= "jdbc:mysql://dd28212.kasserver.com:3306/";
	private final static String	DATABASE		= "d023bc65";
	private final static String	TABLE_USER		= "user";
	private final static String	USER			= "d023bc65";
	private final static String	PASSWORD		= "GBpBzAab7YNuzKSQ";

	private static Connection	connection;

	private static void createConnection() throws SQLException, ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(URL, USER, PASSWORD);
		createDatabase();
		createTable();
	}
	
	private static void terminateConnection() throws SQLException
	{
		if (connection != null)
		{
			connection.close();
		}
	}

	private static void createDatabase() throws SQLException
	{
		Statement statement = connection.createStatement();
		statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DATABASE + ";");
		statement.executeUpdate("use " + DATABASE + ";");
		statement.close();
	}
	
	private static void createTable() throws SQLException
	{
		Statement statement = connection.createStatement();
		
		statement.executeUpdate("CREATE TABLE IF NOT EXISTS " + TABLE_USER + " ( " +
								"username 		VARCHAR(255) 	NOT NULL, " +
								"passwort 		VARCHAR(255) 	NOT NULL, " +
								"laengengrad 	DOUBLE		 	NOT NULL, " +
								"breitengrad 	DOUBLE		 	NOT NULL, " +
								"bemerkung	 	VARCHAR(255) 			, " +
								"zeitpunkt 		TIMESTAMP 		NOT NULL, " +
								"PRIMARY KEY (username));");
		
		statement.close();
	}
	
	public static MyResult insertUser (User user) throws ClassNotFoundException, SQLException
	{
		createConnection();
		
		String sqlinsert = "INSERT INTO " + TABLE_USER + "(username, passwort, laengengrad, breitengrad, bemerkung) VALUES (?, ?, ?, ? , ?);";

		PreparedStatement sqlStatement = connection.prepareStatement(sqlinsert);
		sqlStatement.setString	 (1, user.getUsername());
		sqlStatement.setString	 (2, user.getPasswort());
		sqlStatement.setDouble	 (3, user.getLaengengrad());
		sqlStatement.setDouble	 (4, user.getBreitengrad());
		sqlStatement.setString	 (5, user.getBemerkung());

		
		int rows = 0;
		try
		{
			rows = sqlStatement.executeUpdate();
		}
		catch (SQLException e)
		{
			if (e.getErrorCode() != 1062) // -> 1062 = username schon vorhanden
			{
				throw e;
			}
		}
		sqlStatement.close();
		
		terminateConnection();
		
		return new MyResult(rows);
	}
	
	public static MyResult deleteUser (String username) throws SQLException, ClassNotFoundException
	{
		createConnection();
		
		String sqlinsert = "DELETE FROM " + TABLE_USER + " WHERE username = ?;";

		PreparedStatement sqlStatement = connection.prepareStatement(sqlinsert);
		sqlStatement.setString(1, username);
		int rows = sqlStatement.executeUpdate();
		sqlStatement.close();
		
		terminateConnection();
		
		return new MyResult(rows);
	}

	public static MyResult updateUser (User user) throws SQLException, ClassNotFoundException
	{
		createConnection();
		
		String sqlupdate = 	  "UPDATE " + TABLE_USER + " SET "+ "laengengrad= ?, " 
			 				+ "breitengrad= ?, "+ "bemerkung= ? "+ "WHERE username = ?;";

		PreparedStatement sqlStatement = connection.prepareStatement(sqlupdate);
		sqlStatement.setDouble		(1, user.getLaengengrad());
		sqlStatement.setDouble		(2, user.getBreitengrad());
		sqlStatement.setString		(3, user.getBemerkung());
		sqlStatement.setString		(4, user.getUsername());
		int rows = sqlStatement.executeUpdate();
		sqlStatement.close();
		
		terminateConnection();
		
		return new MyResult(rows);
	}
	
	public static User selectUser (String username) throws SQLException, ClassNotFoundException
	{
		createConnection();
		
		
		String sqlselect = 	"SELECT * FROM " + TABLE_USER + " WHERE username = ?;";
		
		PreparedStatement sqlStatement = connection.prepareStatement(sqlselect);
		sqlStatement.setString(1, username);
		
		ResultSet result = sqlStatement.executeQuery();
		User user = null;

		while (result.next())
		{
			user = new User();
			user.setUsername(result.getString("username"));
			user.setPasswort(result.getString("passwort"));
			user.setLaengengrad(result.getDouble("laengengrad"));
			user.setBreitengrad(result.getDouble("breitengrad"));
			user.setBemerkung(result.getString("bemerkung"));
			user.setZeitpunkt(result.getTimestamp("zeitpunkt"));
		}


		terminateConnection();
		
		return user;
	}

	public static ListeUser selectAll () throws SQLException, ClassNotFoundException
	{
		createConnection();
		
		ListeUser listeUser = new ListeUser();

		String sqlselect = 	"SELECT * FROM " + TABLE_USER + ";";
		PreparedStatement sqlStatement = connection.prepareStatement(sqlselect);
		ResultSet result = sqlStatement.executeQuery();

		while (result.next())
		{
			User user = new User();
			user.setUsername(result.getString("username"));
			user.setPasswort(result.getString("passwort"));
			user.setLaengengrad(result.getDouble("laengengrad"));
			user.setBreitengrad(result.getDouble("breitengrad"));
			user.setBemerkung(result.getString("bemerkung"));
			user.setZeitpunkt(result.getTimestamp("zeitpunkt"));
			listeUser.addUser(user);
		}
		
		terminateConnection();
		
		return listeUser;
	}
}