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
		// Retrieve all of user's data entries into a list
		DatabaseManager dbm = new DatabaseManager("vault_database");
		List<DataEntry> dataEntryList = dbm.retrieveUserDataEntries(user.getUsername());
		// Parse username from user_email to use as backup database name
		String username = user.getUsername().substring(0, user.getUsername().indexOf('@'));
		String backup_database_name = username + "_backup_database";
		// Create a new backup database to store account data
		dbm.setWorkingDatabase(backup_database_name); // DatabaseManager dbm = new DatabaseManager(backup_database_name);
		// Create the users and data_entries tables in new database
		dbm.createUsersTable();
		dbm.createDataEntriesTable();
		// Fill the new database with the user and their entries from the List of user's data entries
		dbm.addUserToDatabase(user);
		for (DataEntry entry : dataEntryList) {
			dbm.addEntryToDatabase(user, entry);
		}
	}
	
	public void importEntriesFromBackup(User currentUser, User backupUser, String currentDatabaseName, String backupDatabaseName) {
		// Connect to backup database and retrieve all the entries into a List
		DatabaseManager dbm = new DatabaseManager(backupDatabaseName);
		List<DataEntry> importedDataEntryList = dbm.retrieveUserDataEntries(backupUser.getUsername());
		// Switch to the database of currentUser
		dbm.setWorkingDatabase(currentDatabaseName);
		// Put all entries from the List into the current database for the current user
		for (DataEntry entry : importedDataEntryList) {
			dbm.addEntryToDatabase(currentUser, entry);
		}
	}
	
	public boolean isValidBackupUser() {
		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
