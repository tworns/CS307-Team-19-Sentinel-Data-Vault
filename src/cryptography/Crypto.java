package cryptography;

import dataManagement.*;

public class Crypto {
	
	public User user;
	public DataEntry data;
	
	public Crypto (User user, DataEntry data){ 
		this.user = user;
		this.data = data;
	}
	
	
	public DataEntry encrypt(User user, DataEntry data) { //Return type is temporary
		
		return data;
	}
	public DataEntry decrypt(User user, DataEntry data) { //Return type is temporary
		
		return data;
	}
	
	
}
