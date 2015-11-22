package controllers;

import dataManagement.User;

public class BackupManager {
	
	public void createUserBackupDatabase(User user) {
		// Connect to current database
		// Get a result set of all user's data entries
		DatabaseManager DBM = new DatabaseManager();
		
		// Create a new backup database to store
		//		- create the users and data_entries tables in new database
		// Fill the new database with the user and their entries from the result set
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
