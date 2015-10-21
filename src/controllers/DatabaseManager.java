package controllers;

public class DatabaseManager {
	
	private String username = "cs307@purdue.edu";
	private String userPassword = "12345678";
	
	public void handleRequest() {
		
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
