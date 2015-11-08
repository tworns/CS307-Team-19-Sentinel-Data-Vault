package controllers;

import dataManagement.User;
import dataManagement.DataEntry;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DatabaseManager {

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
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			// e.printStackTrace();
		}
		return connection;
	}

	/**
	 * Adds a given user (account) to the vault database.
	 * 
	 * @param newUser 	user object to add to the vault database
	 * @return 			positive integer if user successfully added; negative if user already exists in the database
	 */
	public int addUserToDatabase(User newUser) {
		// Connect to the database
		Connection DBconnection = connectToDatabase();
		try {
			// Initialize a statement to execute
			Statement stmt = DBconnection.createStatement();

			// Check that user does not already exist
			ResultSet results = stmt.executeQuery(
					"SELECT count(*) FROM users WHERE user_email = " + "'" + newUser.getUsername() + "';");
			if (results.getInt(1) != 0) {
				// user exists, return failure value
				results.close();
				stmt.close();
				DBconnection.close();
				return -1;
			}

			// Construct the SQL INSERT statement
			String sql = "INSERT INTO users (user_email, password_hash, password_salt, data_key, security_question, security_answer, "
					+ "last_login, high_security, account_wipe_set, backup_frequency, max_backup_size) " + "VALUES ("
					+ "'" + newUser.getUsername() + "', " + "'" + newUser.getPasswordHash() + "', " + "'"
					+ newUser.getPasswordSalt() + "', " + "'" + newUser.getDataKey() + "', " + "'"
					+ newUser.getSecurityQuestion() + "', " + "'" + newUser.getSecurityAnswer() + "', " + "'"
					+ newUser.getLastLogin().toString() + "', " + newUser.isHighSecurity() + ", "
					+ newUser.isAccountWipeSet() + ", " + "'" + newUser.getBackupFrequency() + "', "
					+ newUser.getMaxBackupSize() + ");";

			// Execute the statement and commit database changes
			stmt.executeUpdate(sql);
			DBconnection.commit();

			// Disconnect from database
			stmt.close();
			DBconnection.close();

			// return a success value
			return 1;

		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
			// return a failure value
			return -1;
		}
	}

	/**
	 * Deletes a given user from the vault database.
	 * 
	 * @param doomedUser	user object to delete from the vault database
	 * @return 				positive integer if user successfully deleted; negative if unsuccessful
	 */
	public int deleteUserFromDatabase(User doomedUser) { // TODO Check for success/failure unnecessary
		// Connect to the database
		Connection DBconnection = connectToDatabase();

		try {
			// Initialize a statement to execute
			Statement stmt = DBconnection.createStatement();
			// Construct the SQL DELETE statement
			String sql = "DELETE FROM users WHERE user_email = " + "'" + doomedUser.getUsername() + "';";

			// Execute the statement and commit database changes
			stmt.executeUpdate(sql);
			DBconnection.commit();

			// Disconnect from database
			stmt.close();
			DBconnection.close();

			// return a success value
			return 1;
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
			// return a failure value
			return -1;
		}
	}

	/**
	 * Retrieves a stored user from the vault database
	 * 
	 * @param userEmail	username (email) of user to be retrieved from the vault database
	 * @return 			User object containing that user's stored data
	 */
	public User retrieveUserFromDatabase(String userEmail) {
		// Connect to the database
		Connection DBconnection = connectToDatabase();
		try {
			// Initialize a statement to execute
			Statement stmt = DBconnection.createStatement();
			
			// Construct the SQL DELETE statement
			String sql = "SELECT * FROM users WHERE user_email = " + "'" + userEmail + "';";

			// Execute the statement and commit database changes
			ResultSet userInfoSet = stmt.executeQuery(sql);
			// DBconnection.commit();
			// while ( userInfoSet.next() ) {
			// String id = userInfoSet.getString("user_email");
			String passwordHash = userInfoSet.getString("password_hash");
			String salt = userInfoSet.getString("password_salt");
			String datakey = userInfoSet.getString("data_key");
			String question = userInfoSet.getString("security_question");
			String answer = userInfoSet.getString("security_answer");
			String lastLogin = userInfoSet.getString("last_login");
			LocalDateTime loginLDT = LocalDateTime.parse(lastLogin);

			int isHigh = userInfoSet.getInt("high_security");
			int wipeSet = userInfoSet.getInt("account_wipe_set");
			String backupFreq = userInfoSet.getString("backup_frequency");
			int size = userInfoSet.getInt("max_backup_size");
			
			// Reconstruct user
			User user = new User(userEmail, passwordHash, salt, datakey, question, answer, loginLDT);
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
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
			// return a failure value
			return null;
		}
	}
	
	/*
	 * Retrieve DataEntry object from DB
	 * 
	 * Jiho Choi
	 */
	public List<DataEntry> retrieveAllDataEntries(String userEmail) {
		// Connect to the database
		Connection DBconnection = connectToDatabase();
		try {
			/*this is the list we are returning contains all dataentries belong
			 to certain user*/
			List<DataEntry> dataentries = new ArrayList<DataEntry>();
			
			// Initialize a statement to execute
			Statement stmt = DBconnection.createStatement();
			// Construct the SQL DELETE statement
			String sql = "SELECT * FROM data_entries WHERE owner = "
					+ "'" + userEmail + "';"
					;
			
			// Execute the statement and commit database changes
		    ResultSet dataEntryInfoSet = stmt.executeQuery(sql);
		    while(dataEntryInfoSet.next()){
		    	/*TODO construct Dataentry object and add each one 
		    	 to the List in the while loop here */
		    }
	        
		    /*Basically just move the codes below to the loop and 
		    ad the entry to the list*/
		    
		    /*String entryName = dataEntryInfoSet.getString("entry_name");
	        String entryType  = dataEntryInfoSet.getString("entry_type");
	        String encryptionKey = dataEntryInfoSet.getString("encryption_key");
	        String owner = dataEntryInfoSet.getString("owner"); 
	        int highSecurity = dataEntryInfoSet.getInt("secure_entry");
	        
	        String lastModified = dataEntryInfoSet.getString("last_modified");
			LocalDateTime modifiedLDT = LocalDateTime.parse(lastModified);
	        
	        int isHigh = dataEntryInfoSet.getInt("high_security");
	        int wipeSet = dataEntryInfoSet.getInt("account_wipe_set");
	        String backupFreq = dataEntryInfoSet.getString("backup_frequency");
	        int size = dataEntryInfoSet.getInt("max_backup_size");
	       
	        String datafield_1 = dataEntryInfoSet.getString("data_field_1");
	        String datafield_2 = dataEntryInfoSet.getString("data_field_2");
	        String datafield_3 = dataEntryInfoSet.getString("data_field_3");
	        String datafield_4 = dataEntryInfoSet.getString("data_field_4");
	        String datafield_5 = dataEntryInfoSet.getString("data_field_5");
	        String datafield_6 = dataEntryInfoSet.getString("data_field_6");
	        String datafield_7 = dataEntryInfoSet.getString("data_field_7");
	        String datafield_8 = dataEntryInfoSet.getString("data_field_8");
	        String datafield_9 = dataEntryInfoSet.getString("data_field_9");
	        String datafield_10 = dataEntryInfoSet.getString("data_field_10");
	        
	        List<String> fields = new ArrayList<String>();
	        fields.add(datafield_1);
	        fields.add(datafield_2);
	        fields.add(datafield_3);
	        fields.add(datafield_4);
	        fields.add(datafield_5);
	        fields.add(datafield_6);
	        fields.add(datafield_7);
	        fields.add(datafield_8);
	        fields.add(datafield_9);
	        fields.add(datafield_10);
	        
	        // Reconstruct DataEntry
	        DataEntry dataEntry = new DataEntry(entryName, entryType, encryptionKey, owner, highSecurity, modifiedLDT);
	        dataEntry.setDataFields(fields);
	        */
		    
		    // Disconnect from database
	        dataEntryInfoSet.close();
		    stmt.close();
		    DBconnection.close();
			
			// return a success value
			return dataentries;
		} catch (SQLException e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
			// return a failure value
			return null;
		}
	}
	
	public List<String> retrieveDataEntryNameList(String user_email) {
		List<String> entryNameList = new ArrayList<String>();
		
		// Connect to the database
		Connection DBconnection = connectToDatabase();
		try {
			// Initialize a statement to execute
			Statement stmt = DBconnection.createStatement();
			
			// Construct the SQL select statement
			String sql = "SELECT entry_name FROM data_entries WHERE owner = '" + user_email + "';";
			
			// Execute SQL statement and retrieve result set
			ResultSet entryNameSet = stmt.executeQuery(sql);
			
			// Construct list from result set
			while (entryNameSet.next()) {
				String entryName = entryNameSet.getString("entry_name");
				entryNameList.add(entryName);
			}
			
			// Disconnect and close database
			entryNameSet.close();
			stmt.close();
			DBconnection.close();
			
		} catch (SQLException e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
		}
		
		return entryNameList;
	}
	
	public List<String> retrieveDataEntryTypeList(String user_email) {
		List<String> entryTypeList = new ArrayList<String>();
		
		// Connect to the database
		Connection DBconnection = connectToDatabase();
		try {
			// Initialize a statement to execute
			Statement stmt = DBconnection.createStatement();
			
			// Construct the SQL select statement
			String sql = "SELECT entry_type FROM data_entries WHERE owner = '" + user_email + "';";
			
			// Execute SQL statement and retrieve result set
			ResultSet entryTypeSet = stmt.executeQuery(sql);
			
			// Construct list from result set
			while (entryTypeSet.next()) {
				String entryType = entryTypeSet.getString("entry_type");
				entryTypeList.add(entryType);
			}
			
			// Disconnect and close database
			entryTypeSet.close();
			stmt.close();
			DBconnection.close();
			
		} catch (SQLException e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
		}
		
		return entryTypeList;
	}
	
	/**
	 * Generates an alphabetical List of all a given user’s viewable shared data entries.
	 * NOTE: Requires that valid_users field in database contains a SORTED String of user_emails separated by " " (space)
	 * 
	 * @param user_email	User to find viewable shared entries for
	 * @return				Alphabetically-sorted List of all shared data entries a user has permission to view.
	 */
	public List<String> retrieveSharedEntryList(String user_email) {
		List<String> sharedEntryList = new ArrayList<String>();
		
		// Connect to the database
		Connection DBconnection = connectToDatabase();
		try {
			// Initialize a statement to execute
			Statement stmt = DBconnection.createStatement();
			
			// Construct the SQL select statement (gets ALL data entries)
			String sql = "SELECT entry_name, valid_users FROM data_entries;";
			
			// Execute SQL statement and retrieve result set
			ResultSet allDataEntries = stmt.executeQuery(sql);
			
			// Construct list of available shared entries from result set of ALL entries
			while (allDataEntries.next()) {
				// parse the valid_users STRING to get resulting LIST of valid users. Delimiter = ' '
				String validUsers = allDataEntries.getString("valid_users");
				String[] parsedValidUsers = validUsers.split(" ");
				// search the String array for user_email; add to SharedEntryList
				if (Arrays.binarySearch(parsedValidUsers, user_email) >= 0) {
					sharedEntryList.add(allDataEntries.getString("entry_name"));
				}
			}
			
			// Disconnect and close database
			allDataEntries.close();
			stmt.close();
			DBconnection.close();
			
		} catch (SQLException e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
		}
		
		Collections.sort(sharedEntryList);
		return sharedEntryList;
	}

	public DataEntry retrieveOneDataEntry(String entryname, String email, String type) {
		// Connect to the database
		Connection DBconnection = connectToDatabase();
		try {
			// Initialize a statement to execute
			Statement stmt = DBconnection.createStatement();
			// Construct the SQL select statement
			String sql = "SELECT * FROM data_entries WHERE owner = "
					+ "'" + email + "'" + " AND " + "entry_name = " +
					"'" + entryname + "'" +" AND " + "entry_type = " +
					"'" + type + "';"
					;
			
			// Execute the statement and commit database changes
		    ResultSet dataEntryInfoSet = stmt.executeQuery(sql);
		    
		    //reconstruct entry
		    String entryName = dataEntryInfoSet.getString("entry_name");
	        String entryType  = dataEntryInfoSet.getString("entry_type");
	        String encryptionKey = dataEntryInfoSet.getString("encryption_key");
	        String owner = dataEntryInfoSet.getString("owner"); 
	        int highSecurity = dataEntryInfoSet.getInt("secure_entry");
	        String lastModified = dataEntryInfoSet.getString("last_modified");
			LocalDateTime modifiedLDT = LocalDateTime.parse(lastModified);
			
	        String datafield_1 = dataEntryInfoSet.getString("data_field_1");
	        String datafield_2 = dataEntryInfoSet.getString("data_field_2");
	        String datafield_3 = dataEntryInfoSet.getString("data_field_3");
	        String datafield_4 = dataEntryInfoSet.getString("data_field_4");
	        String datafield_5 = dataEntryInfoSet.getString("data_field_5");
	        String datafield_6 = dataEntryInfoSet.getString("data_field_6");
	        String datafield_7 = dataEntryInfoSet.getString("data_field_7");
	        String datafield_8 = dataEntryInfoSet.getString("data_field_8");
	        String datafield_9 = dataEntryInfoSet.getString("data_field_9");
	        String datafield_10 = dataEntryInfoSet.getString("data_field_10");
	        
	        List<String> fields = new ArrayList<String>();
	        fields.add(datafield_1);
	        fields.add(datafield_2);
	        fields.add(datafield_3);
	        fields.add(datafield_4);
	        fields.add(datafield_5);
	        fields.add(datafield_6);
	        fields.add(datafield_7);
	        fields.add(datafield_8);
	        fields.add(datafield_9);
	        fields.add(datafield_10);
	        
	        // Reconstruct DataEntry
	        DataEntry dataEntry = new DataEntry(entryName, entryType, encryptionKey, owner, highSecurity, modifiedLDT);
	        
	        dataEntry.setHighSecurity(highSecurity);
	        dataEntry.setDataFields(fields);
	        
		    // Disconnect from database
	        dataEntryInfoSet.close();
		    stmt.close();
		    DBconnection.close();
			
			// return a success value
			return dataEntry;
		} catch (SQLException e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
			// return a failure value
			return null;
		}
	}	
	
	/**
	 * Modifies a TEXT (String) user field in the 'users' table of the vault
	 * database
	 * 
	 * @param user
	 *            user whose field is to be updated
	 * @param fieldName
	 *            name of user field to modify ('users' table column identifier)
	 * @param newTextData
	 *            new TEXT data to put into user field
	 */
	public void modifyUserField(User user, String fieldName, String newTextData) {
		// Connect to the database
		Connection DBconnection = connectToDatabase();

		try {
			// Initialize a statement to execute
			Statement stmt = DBconnection.createStatement();
			// Construct the SQL UPDATE statement

			String sql = "UPDATE users" + "SET " + fieldName + " = '" + newTextData + "' " + "WHERE user_email = '"
					+ user.getUsername() + "';";

			// Execute the statement and commit database changes
			stmt.executeUpdate(sql);
			DBconnection.commit();

			// Disconnect from database
			stmt.close();
			DBconnection.close();
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Modifies a INTEGER (int) user field in the 'users' table of the vault
	 * database
	 * 
	 * @param user
	 *            user whose field is to be updated
	 * @param fieldName
	 *            name of user field to modify ('users' table column identifier)
	 * @param newIntData
	 *            new INTEGER data to put into user field
	 */
	public void modifyUserField(User user, String fieldName, int newIntData) {
		// Connect to the database
		Connection DBconnection = connectToDatabase();

		try {
			// Initialize a statement to execute
			Statement stmt = DBconnection.createStatement();
			// Construct the SQL UPDATE statement

			String sql = "UPDATE users "
					+ "SET " + fieldName + " = " + String.valueOf(newIntData) + " "
					+ "WHERE user_email = '" + user.getUsername() + "';"
					;


/*
			String sql = "UPDATE users" + "SET " + fieldName + " = '" + String.valueOf(newIntData) + "' "
					+ "WHERE user_email = '" + user.getUsername() + "';";

*/

			// Execute the statement and commit database changes
			stmt.executeUpdate(sql);
			DBconnection.commit();

			// Disconnect from database
			stmt.close();
			DBconnection.close();
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void updateEntry(DataEntry oldEntry, DataEntry newEntry) {
		// Connect to the database
		Connection DBconnection = connectToDatabase();
		try {
			// Initialize a statement to execute
			Statement stmt = DBconnection.createStatement();

			// Construct the SQL INSERT statement
			int size_of_datafield = newEntry.getFieldDataList().size();
			String sql = "UPDATE data_entries SET entry_name='" + newEntry.getEntryName() + "', ";
			for (int i = 0; i < size_of_datafield; i++) {
				sql += "data_field_" + Integer.toString(i + 1) + "='" + newEntry.getFieldDataList().get(i) + "'";
				if (i != size_of_datafield - 1)
					sql+= ", ";
			}
			sql += "WHERE entry_name='" + oldEntry.getEntryName();
			System.out.println(sql);		
					
			// Execute the statement and commit database changes
			stmt.executeUpdate(sql);
			DBconnection.commit();

			// Disconnect from database
			stmt.close();
			DBconnection.close();

			// return a success value
			return;

		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
			// return a failure value
			return;
		}
	}
	
	
	public int addEntryToDatabase(DataEntry entry) {
		// Connect to the database
		Connection DBconnection = connectToDatabase();
		try {
			// Initialize a statement to execute
			Statement stmt = DBconnection.createStatement();

			//Check that entry_name does not already exist
			ResultSet results = stmt.executeQuery(
					"SELECT COUNT(*) FROM data_entries WHERE entry_name = " + "'" + entry.getEntryName() + "' "
					+ "AND owner = '" + entry.getOwner() + "';");
			if (results.getInt(1) != 0) {
				// entry_name exists, return failure value
				System.out.println("Entry name already exists!");
				results.close();
				stmt.close();
				DBconnection.close();						
				return -1;
			}

			// Construct the SQL INSERT statement
			int field_number = entry.getFieldDataList().size();
			String sql = "INSERT INTO data_entries(entry_name, entry_type, encryption_key, owner, valid_users, secure_entry, last_modified";
			for (int i = 0; i < field_number; i++) {
				sql = sql + ", ";
				sql = sql + "data_field_" + Integer.toString(i + 1);
			}
			sql = sql + ") VALUES ('" + entry.getEntryName() + "', "  + "'" + entry.getEntryType()  + "', " + "'"
					+ entry.getEncryptionKey()  + "', " + "'" + entry.getOwner()  + "', " + "'"
					+ entry.buildValidUsersString() + "', " + entry.isHighSecurity() + ", '" + entry.getLastModified().toString() + "', ";
			for (int j = 0; j < field_number; j++) {
				sql = sql + "'" + entry.getFieldDataList().get(j) + "'";
				if (j != field_number -1)
					sql = sql + ", ";
			}
			sql = sql + ");";
			System.out.println(sql);		
					
			// Execute the statement and commit database changes
			stmt.executeUpdate(sql);
			DBconnection.commit();

			// Disconnect from database
			stmt.close();
			DBconnection.close();

			// return a success value
			return 1;

		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
			// return a failure value
			return -1;
		}
	}
	
	public int deleteEntryFromDatabase(DataEntry entry) {
		// Connect to the database
				Connection DBconnection = connectToDatabase();
				try {
					// Initialize a statement to execute
					Statement stmt = DBconnection.createStatement();
					
					// Construct the SQL INSERT statement
					String sql = "DELETE FROM data_entries WHERE entry_name='" + entry.getEntryName() + "'" + " AND " +
								"owner='" + entry.getOwner() + "'";
					System.out.println(sql);		
							
					// Execute the statement and commit database changes
					stmt.executeUpdate(sql);
					DBconnection.commit();

					// Disconnect from database
					stmt.close();
					DBconnection.close();

					// return a success value
					return 1;

				} catch (SQLException e) {
					System.err.println(e.getClass().getName() + ": " + e.getMessage());
					e.printStackTrace();
					// return a failure value
					return -1;
				}
	}
	
	//******This method should always be called BEFORE we delete a user account*****
	//TODO 
	public int deleteAllEntriesFromDatabase(User doomeduser) {
		// Connect to the database
		Connection DBconnection = connectToDatabase();
		try {
			// Initialize a statement to execute
			Statement stmt = DBconnection.createStatement();

			// Construct the SQL INSERT statement
			String sql = "DELETE FROM data_entries WHERE owner='" + doomeduser.getUsername() + "'";
			System.out.println(sql);		
					
			// Execute the statement and commit database changes
			stmt.executeUpdate(sql);
			DBconnection.commit();

			// Disconnect from database
			stmt.close();
			DBconnection.close();

			// return a success value
			return 1;

		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
			// return a failure value
			return -1;
		}
	}
}
