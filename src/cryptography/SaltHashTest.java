package cryptography;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class SaltHashTest {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		
		System.out.println("Choose a password:");
		Scanner in = new Scanner(System.in);
		
		String storedPassword = in.nextLine();
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
		
		// Reset the MessageDigest
		md.reset();
		
		System.out.println("\nEnter your password:");
		String testPassword = in.nextLine();
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
				
		if (hexString.toString().equals(hexString2.toString())) {
			System.out.println("\nLogin successful!\n");
		}
		else {
			System.out.println("\nIncorrect password!\n");
		}
		
		System.out.println("Stored Hex Hash:\t" + hexString.toString());
		System.out.println("Entered Hex Hash:\t" + hexString2.toString());

		in.close();
		
	}

}
