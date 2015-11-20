package security;

import java.security.NoSuchAlgorithmException;
import cryptography.PasswordHasher;
import dataManagement.User;

public class PasswordValidation {

	public PasswordValidation() { 
	}
	/**
	* @param password 		password in need of checking
	*/
	public boolean minStandard(String password){ // little b boolean is the primitive, big B boolean is the object
	
	//TODO Check for length >8, at least one digit & special char, upper & lower case letters.
	if(password.length() < 8 && (password.equals("") || password == null)) {
		return false;
	}
	if(password.matches("(?=.*[a-z])(?=.*[A-Z])(?=.*([0-9]))(?=.*([!@#$%&*,.])).*") == false) { 
		return false;
	}
	else { 
		return true;
	}
	}
	
	/**
	 * @param user		Whose password you want to check
	 * @param password	Given password being tested if it matches the User's actual password
	 * @return			True if given password matches User's actual password
	 */
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