package cryptography;

import java.security.SecureRandom;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class for generating secure, random salts (and pepper) for password hashing
 */
public class SaltGenerator {
	
	private SecureRandom prng;
	
	public SaltGenerator() throws NoSuchAlgorithmException {
		prng = SecureRandom.getInstance("SHA1PRNG");
	}
	
	private static String hexEncode(byte[] aInput){
	    StringBuilder hexResult = new StringBuilder();
	    char[] hexDigits = {'0', '1', '2', '3', '4','5','6','7','8','9','a','b','c','d','e','f'};
	    for (int i = 0; i < aInput.length; ++i) {
	    	byte b = aInput[i];
	    	hexResult.append(hexDigits[(b&0xf0) >> 4]);
	    	hexResult.append(hexDigits[b&0x0f]);
	    }
	    return hexResult.toString();
	}
	
	public String generateSalt() throws NoSuchAlgorithmException {
	    // Generate a secure random number
	    String randomNum = new Integer(prng.nextInt()).toString();

	    // Get its digest
	    MessageDigest md = MessageDigest.getInstance("SHA-1");
	    byte[] byteResult =  md.digest(randomNum.getBytes());
	    
	    // Return the hex salt as a String
	    return hexEncode(byteResult);
	}
	
}
