package controllers;

import java.util.List;
import java.sql.*;
import dataManagement.DataEntry;
import dataManagement.User;

public class BackupManager {
	
	/**
	 * Generates a new backup database file containing ONLY the current user and their associated data entries
	 * 
	 * @param user User object to create an account backup database file for
	 */
	public void createUserBackupDatabase(User user) {
		// Retrieve all of user's data entries
		DatabaseManager currentDBM = new DatabaseManager("vault_database");
		List<DataEntry> dataEntryList = currentDBM.retrieveUserDataEntries(user.getUsername());
		// Parse username from user_email to use as backup database name
		String username = user.getUsername().substring(0, user.getUsername().indexOf('@'));
		String backup_database_name = username + "_backup_database";
		// Create a new backup database to store
		// Create the users and data_entries tables in new database
		DatabaseManager backupDBM = new DatabaseManager(backup_database_name);
		backupDBM.createUsersTable();
		backupDBM.createDataEntriesTable();
		// Fill the new database with the user and their entries from the result set
		backupDBM.addUserToDatabase(user);
		for (DataEntry entry : dataEntryList) {
			backupDBM.addEntryToDatabase(user, entry);
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
