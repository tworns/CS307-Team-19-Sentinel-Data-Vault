package controllers;

import dataManagement.User;
import java.sql.*;

public class DatabaseManager {
	
	private String username = "cs307@purdue.edu";
	private String userPassword = "12345678";
	
	private static Connection connectToDatabase() {
		Connection connection = null;
		// Establish connection to the existing database
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:vault_database");
			connection.setAutoCommit(false);
		} catch (Exception e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage());
			//e.printStackTrace();
		}
		return connection;
	}
	
	public void addUserToDatabase(User newUser) {
		Connection DBconnection = connectToDatabase();
		try {
			Statement stmt = DBconnection.createStatement();
			
			/*
			 *  TODO add functionality to check that user does not already exist!
			 *  – what should the function return if this is the case???
			 */
			
			// Insert the user account into the "users" table
		    //String sql = "INSERT INTO users (user_email, password_hash, security_question, security_answer, last_login, high_security, account_wipe_set, backup_frequency, max_backup_size) " +
		    //      "VALUES (2, 'Becky', 31, 'New York', 30000.00);"; // TODO update the SQL insert statement for adding user
		    
			// TODO Need to verify SQL syntax for values of each type (i.e. TEXT, NUMERIC, BLOB, etc.)
			// TODO Find out the proper quotation for data types (i.e. "" or '' etc...)
			String sql =
		    		"INSERT INTO users (user_email, password_hash, security_question, security_answer, "
		    		+ "last_login, high_security, account_wipe_set, backup_frequency, max_backup_size) "
		    		+ "VALUES ("
		    			+ newUser.getUsername() + ", "
		    			+ newUser.getPasswordHash() + ", "
		    			+ newUser.getPasswordSalt() + ", "
		    			+ newUser.getDataKey() + ", "
		    			+ newUser.getSecurityQuestion() + ", "
		    			+ newUser.getSecurityAnswer() + ", "
		    			+ newUser.getLastLogin() + ", "
		    			+ newUser.isHighSecurity() + ", "
		    			+ newUser.isAccountWipeSet() + ", "
		    			+ newUser.getBackupFrequency() + ", "
		    			+ newUser.getMaxBackupSize()
		    		+ ");"
		    		;
			// Execute and commit database changes
		    stmt.executeUpdate(sql);
		    DBconnection.commit();
		    
		    // Disconnect from database
		    stmt.close();
		    DBconnection.close();
		    
		} catch (SQLException e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public String retrievePassword(String userEmail) {
		if (userEmail.equals(username)) {
			return userPassword;
		}
		else {
			return "FAILED";
		}
	}
	
	public String retrieveUsername() {		
		return username;
	}

}
