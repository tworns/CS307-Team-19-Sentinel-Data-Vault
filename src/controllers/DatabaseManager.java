package controllers;

import dataManagement.User;
import dataManagement.DataEntry;
import java.sql.*;
import java.time.LocalDateTime;

public class DatabaseManager {
	
	private String username = "cs307@purdue.edu";
	private String userPassword = "12345678";
	
	/**
	 * Connects to the vault database and returns a Connection for two-way communication
	 * 
	 * @return active Connection to vault_database
	 */
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
	
	/**
	 * Adds a given user (account) to the vault database.
	 * 
	 * @param newUser	user object to add to the vault database
	 * @return			positive integer if user successfully added; negative if user already exists in the database
	 */
	public int addUserToDatabase(User newUser) {
		// Connect to the database
		Connection DBconnection = connectToDatabase();
		try {
			// Initialize a statement to execute
			Statement stmt = DBconnection.createStatement();
			
			// Check that user does not already exist
			ResultSet results = stmt.executeQuery("SELECT count(*) FROM users WHERE user_email = "
					+ "'" + newUser.getUsername() + "';");
			if(results.getInt(1) != 0) {
				// user exists, return failure value
				return -1;
			}

			// Construct the SQL INSERT statement
			String sql =
		    		"INSERT INTO users (user_email, password_hash, password_salt, data_key, security_question, security_answer, "
		    		+ "last_login, high_security, account_wipe_set, backup_frequency, max_backup_size) "
		    		+ "VALUES ("
		    			+ "'" + newUser.getUsername() + "', "
		    			+ "'" + newUser.getPasswordHash() + "', "
		    			+ "'" + newUser.getPasswordSalt() + "', "
		    			+ "'" + newUser.getDataKey() + "', "
		    			+ "'" + newUser.getSecurityQuestion() + "', "
		    			+ "'" + newUser.getSecurityAnswer() + "', "
		    			+ "'" + newUser.getLastLogin().toString() + "', "
		    			+ newUser.isHighSecurity() + ", "
		    			+ newUser.isAccountWipeSet() + ", "
		    			+ "'" + newUser.getBackupFrequency() + "', "
		    			+ newUser.getMaxBackupSize()
		    		+ ");"
		    		;
			
			// Execute the statement and commit database changes
		    stmt.executeUpdate(sql);
		    DBconnection.commit();
		    
		    // Disconnect from database
		    stmt.close();
		    DBconnection.close();
		    
		    // return a success value
		    return 1;
		    
		} catch (SQLException e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
			// return a failure value
			return -1;
		}
	}
	
	/**
	 * Deletes a given user from the vault database.
	 * 
	 * @param doomedUser	user object to delete from the vault database
	 * @return				positive integer if user successfully deleted; negative if unsuccessful
	 */
	public int deleteUserFromDatabase(User doomedUser) {
		// Connect to the database
		Connection DBconnection = connectToDatabase();
		
		try {
			// Initialize a statement to execute
			Statement stmt = DBconnection.createStatement();
			// Construct the SQL DELETE statement
			String sql = "DELETE FROM users WHERE user_email = "
					+ "'" + doomedUser.getUsername() + "';"
					;
			
			// Execute the statement and commit database changes
		    stmt.executeUpdate(sql);
		    DBconnection.commit();
		    
		    // Disconnect from database
		    stmt.close();
		    DBconnection.close();
			
			// return a success value
			return 1;
		} catch (SQLException e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
			// return a failure value
			return -1;
		}
	}
	
	/**
	 * Retrieves a stored user from the vault database
	 * 
	 * @param userEmail	username (email) of user to be retrieved from the vault database
	 * @return			User object containing that user's stored data
	 */
	public User retrieveUserFromDatabase(String userEmail) {
		// Connect to the database
		Connection DBconnection = connectToDatabase();
		try {	
			// Initialize a statement to execute
			Statement stmt = DBconnection.createStatement();
			// Construct the SQL DELETE statement
			String sql = "SELECT * FROM users WHERE user_email = "
					+ "'" + userEmail + "';"
					;
			
			// Execute the statement and commit database changes
		    ResultSet userInfoSet = stmt.executeQuery(sql);
		    // DBconnection.commit();
		    // while ( userInfoSet.next() ) {
	        // String id = userInfoSet.getString("user_email");
	        String passwordHash = userInfoSet.getString("password_hash");
	        String salt  = userInfoSet.getString("password_salt");
	        String datakey = userInfoSet.getString("data_key");
	        String question = userInfoSet.getString("security_question");
	        String answer = userInfoSet.getString("security_answer");
	        String lastLogin = userInfoSet.getString("last_login");
	        LocalDateTime l = LocalDateTime.parse(lastLogin);
	         
	        int isHigh = userInfoSet.getInt("high_security");
	        int wipeSet = userInfoSet.getInt("account_wipe_set");
	        String  backupFreq = userInfoSet.getString("backup_frequency");
	        int size = userInfoSet.getInt("max_backup_size");
	        // Reconstruct user
	        User user = new User(userEmail, passwordHash, salt, 
	        		 datakey, question, answer, l);
	        user.setDefaultHighSecurity(isHigh);
	        user.setAccountWipe(wipeSet);
	        user.setBackupFrequency(backupFreq);
	        user.setMaxBackupSize(size);
		         
		    // Disconnect from database
	        userInfoSet.close();
		    stmt.close();
		    DBconnection.close();
			
			// return a success value
			return user;
		} catch (SQLException e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
			// return a failure value
			return null;
		}
	}
	
	/**
	 * Modifies a TEXT (String) user field in the 'users' table of the vault database
	 * 
	 * @param user			user whose field is to be updated
	 * @param fieldName		name of user field to modify ('users' table column identifier)
	 * @param newTextData	new TEXT data to put into user field
	 */
	public void modifyUserField(User user, String fieldName, String newTextData) {
		// Connect to the database
		Connection DBconnection = connectToDatabase();
		
		try {
			// Initialize a statement to execute
			Statement stmt = DBconnection.createStatement();
			// Construct the SQL UPDATE statement
			String sql = "UPDATE users"
					+ "SET " + fieldName + " = '" + newTextData + "' "
					+ "WHERE user_email = '" + user.getUsername() + "';"
					;
			
			// Execute the statement and commit database changes
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
	
	/**
	 * Modifies a INTEGER (int) user field in the 'users' table of the vault database
	 * 
	 * @param user			user whose field is to be updated
	 * @param fieldName		name of user field to modify ('users' table column identifier)
	 * @param newIntData	new INTEGER data to put into user field
	 */
	public void modifyUserField(User user, String fieldName, int newIntData) {
		// Connect to the database
		Connection DBconnection = connectToDatabase();
		
		try {
			// Initialize a statement to execute
			Statement stmt = DBconnection.createStatement();
			// Construct the SQL UPDATE statement
			String sql = "UPDATE users"
					+ "SET " + fieldName + " = '" + String.valueOf(newIntData) + "' "
					+ "WHERE user_email = '" + user.getUsername() + "';"
					;
			
			// Execute the statement and commit database changes
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
	
	public String retrieveUsername() {		
		return username;
	}

	public int addEntryToDataBase() {
		return 1;
	}
}
