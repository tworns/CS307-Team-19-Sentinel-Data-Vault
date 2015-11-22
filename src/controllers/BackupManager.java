package controllers;

import java.util.List;
import java.sql.*;
import dataManagement.DataEntry;
import dataManagement.User;

public class BackupManager {
	
	public void createUserBackupDatabase(User user) {
		// Retrieve all of user's data entries
		DatabaseManager oldDBM = new DatabaseManager("vault_database");
		List<DataEntry> dataEntryList = oldDBM.retrieveUserDataEntries(user.getUsername());
		// Parse username from user_email to use as backup database name
		String username = user.getUsername().substring(0, user.getUsername().indexOf('@'));
		String backup_database_name = username + "_backup_database";
		// Create a new backup database to store
		// Create the users and data_entries tables in new database
		DatabaseManager newDBM = new DatabaseManager(backup_database_name);
		newDBM.createUsersTable();
		newDBM.createDataEntriesTable();
		// Fill the new database with the user and their entries from the result set
		newDBM.addUserToDatabase(user);
		for (DataEntry entry : dataEntryList) {
			newDBM.addEntryToDatabase(user, entry);
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
