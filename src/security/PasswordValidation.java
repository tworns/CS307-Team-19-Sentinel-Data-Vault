package security;

import java.security.NoSuchAlgorithmException;
import cryptography.PasswordHasher;
import dataManagement.User;

public class PasswordValidation {

	public PasswordValidation(String password) { 
	}

	public int minStandard(String password){ 
	
	//TODO Check for length >12, at least one digit & special char, upper & lower case letters.
	
		return 0; //Fix to actually be a return;
	}
	
	public static Boolean isValidPassword(User user, String password) throws NoSuchAlgorithmException {
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