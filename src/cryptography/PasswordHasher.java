package cryptography;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHasher {

	private static final String PEPPER = "5e628496e9809df976f84c86d692eda6f9ea82f2";
	
	private static MessageDigest md;
	
	public PasswordHasher() throws NoSuchAlgorithmException {
		md = MessageDigest.getInstance("SHA-512");
	}
	
	private static String getHash(String hashee) {
		// Update with String to be hashed (in byte form)
		md.update(hashee.getBytes());
		
		// Hash the digest
		byte byteData[] = md.digest();
		// Convert the hash to hex
		StringBuffer hexBuff = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			String hex = Integer.toHexString(0xff & byteData[i]);
			if (hex.length() == 1) {
				hexBuff.append('0');
			}
			hexBuff.append(hex);
		}
		String hash = hexBuff.toString();
		
		// Reset the digest for later use
		md.reset();
		
		return hash;
	}
	
	public String hashPassword(String password, String salt) {		
		return getHash(getHash(password + PEPPER) + salt);
	}
	
}
