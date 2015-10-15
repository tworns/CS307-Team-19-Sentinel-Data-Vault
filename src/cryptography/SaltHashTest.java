package cryptography;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SaltHashTest {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		// TODO Auto-generated method stub

		String storedPassword = "password1234";
		String salt = "voodooshit"; // Needs to be randomly-generated, long, and unique to each user; will be stored along with the hash
		String saltedPassword = salt + storedPassword;
		
		// Update the digest with the password in byte form
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(saltedPassword.getBytes());
		
		// Hash the digest
		byte byteData[] = md.digest();
		
		// Convert the hash to hex for storage
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			String hex = Integer.toHexString(0xff & byteData[i]);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		
		System.out.println("Stored Hex Hash:\t" + hexString.toString());
		
		// Reset the MessageDigest
		md.reset();
		
		// Enter a password to test against
		String testPassword = "password1234";
		String saltedTestPassword = salt + testPassword;
		
		// Update the digest with the test password in byte form
		md.update(saltedTestPassword.getBytes());
		
		// Hash the digest
		byte byteData2[] = md.digest();
		
		// Convert the hash to hex for storage
		StringBuffer hexString2 = new StringBuffer();
		for (int i = 0; i < byteData2.length; i++) {
			String hex2 = Integer.toHexString(0xff & byteData2[i]);
			if (hex2.length() == 1) {
				hexString2.append('0');
			}
			hexString2.append(hex2);
		}
		
		System.out.println("Entered Hex Hash:\t" + hexString2.toString());
		
	}

}
