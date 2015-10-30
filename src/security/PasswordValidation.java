package security;

import java.security.NoSuchAlgorithmException;
import cryptography.PasswordHasher;
import dataManagement.User;

public class PasswordValidation {

	public PasswordValidation(String password) { 
	}

	public boolean minStandard(String password){ // little b booelan is the primitave, big B boolean is the object
	
	//TODO Check for length >12, at least one digit & special char, upper & lower case letters.
	
		return true; //Fix to actually be a return;
	}
	
	public Boolean isValidPassword(User user, String password) throws NoSuchAlgorithmException {
		// Hash the given password
		PasswordHasher ph = new PasswordHasher();
		String givenHash = ph.hashPassword(password, user.getPasswordSalt());
		
		// Compare user's stored password hash with given password hash
		if (user.getPasswordHash().equals(givenHash)) {
			return true;
		}
		else {
			return false;
		}
	}
}