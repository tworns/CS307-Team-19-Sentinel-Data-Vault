package cryptography;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import dataManagement.DataEntry;
import dataManagement.User;

public class CryptoTest {

	public CryptoTest() { 
		
		
	}
	public static void main(String[] args) { 
		Crypto test = new Crypto();
		int secLvl = 0;
		DataEntry testData = new DataEntry(null, null, null, null, 0, null);
		User u = new User(null, null, null, null, null, null, null);
		try{
		SaltGenerator twitch = new SaltGenerator();
		
			u.setPasswordSalt(twitch.generateSalt());
			u.setDefaultHighSecurity(secLvl);
		} catch (NoSuchAlgorithmException e1) {
		
			e1.printStackTrace();
		} 
 		u.setDataKey(test.randomDataKey(u.isHighSecurity()));
		testData.setEncryptionKey(u.getDataKey());		
		System.out.println(u.getDataKey()+ "\n");
		testData.addDataField("This is a test string.");
		testData.addDataField("This too is a test.");
		testData.addDataField("TESTING!");
		testData.addDataField("ASDFASDLKJFALSDKJF!#@!#!@##@LRKAJSFLIAJ[]);;;''");
		testData.addDataField("You may have a mild case of... er.. severe brain damage.");
		try {
			test.encrypt(u, testData);
			System.out.println(testData.getFieldDataList());
			test.decrypt(u, testData);
			System.out.println(testData.getFieldDataList());
		} catch (NoSuchAlgorithmException e) {
		
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			
			e.printStackTrace();
		} catch (BadPaddingException e) {
			
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
		
			e.printStackTrace();
		}
	}
	
	
}
