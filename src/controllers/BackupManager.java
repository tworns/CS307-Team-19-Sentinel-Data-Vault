package controllers;

import java.util.List;
import dataManagement.DataEntry;
import dataManagement.User;
import security.PasswordValidation;
import java.io.*;

public class BackupManager {
	
	/**
	 * Generates a new backup database file containing ONLY the current user and their associated data entries
	 * 
	 * @param user User object to create an account backup database file for
	 */
	public void createUserBackupDatabase(User user) {
		// Parse username from user_email to use as backup database name
		String username = user.getUsername().substring(0, user.getUsername().indexOf('@'));
		String backup_database_name = username + "_backup_database";
		// Retrieve all of user's data entries into a list
		DatabaseManager dbm = new DatabaseManager("vault_database");
		List<DataEntry> dataEntryList = dbm.retrieveUserDataEntries(user.getUsername());
		// Create a new backup database to store account data
		dbm.setWorkingDatabase(backup_database_name); // DatabaseManager dbm = new DatabaseManager(backup_database_name);
		// If backup does not already exist, create the users and data_entries tables in new database, and add the current user.
		File databaseFile = new File(backup_database_name);
		if (!databaseFile.exists()) { // New backup; prepare a new backup database
			dbm.createUsersTable();
			dbm.createDataEntriesTable();
			dbm.addUserToDatabase(user);
		}
		else {
			dbm.deleteAllEntriesFromDatabase(user); // Backup already exists; delete old entries
		}
		// Fill the new backup database with the user and their entries from the List of user's data entries
		for (DataEntry entry : dataEntryList) {
			dbm.addEntryToDatabase(user, entry);
		}
	}
	
	/**
	 * Imports data entries from a database backup file into the current user's account
	 * 
	 * @param currentUser			Current user who will receive entries from the backup
	 * @param backupUser			User of the backup file whose entries will be added to the current user
	 * @param currentDatabaseName	Name of the current working database to receive entries from the backup
	 * @param backupDatabaseName	Name of the backup database whose entries will be added to the current database
	 */
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
	
	/**
	 * Determines if the current user has correct privileges to access a backup database file
	 * 
	 * @param	backupUserEmail		Username (email) of the user who owns the backup database file
	 * @param	backupUserPassword	Entered password to validate against the actual password of the backup database's owner
	 * @param	backupDatabaseName	Name of the backup database being attempted to access
	 * @return	true if the given credentials of the backup database file are valid; false if not 
	 */
	public boolean isValidBackupUser(String backupUserEmail, String backupUserPassword, String backupDatabaseName) {
		DatabaseManager dbm = new DatabaseManager(backupDatabaseName);
		User backupUser = dbm.retrieveUserFromDatabase(backupUserEmail);
		PasswordValidation pv = new PasswordValidation();
		if (pv.isValidPassword(backupUser, backupUserPassword)) {
			return true;
		}
		else {
			return false;
		}
	}

	public static void main(String[] args) {
//		File databaseFile = new File("README.md");
//		if (databaseFile.exists()) {
//			System.out.println("File exists.");
//		}
//		else {
//			System.out.println("File does not exist.");
//		}

	}

}
