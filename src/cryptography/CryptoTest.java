package cryptography;

import java.security.NoSuchAlgorithmException;
import dataManagement.DataEntry;
import dataManagement.User;

public class CryptoTest {

	public CryptoTest() { 
		
		
	}
	public static void main(String[] args) { 
		Crypto test = new Crypto();
		int secLvl = 1;
		DataEntry testData = new DataEntry(null, null, null, null, 1, null);
		User u = new User(null, null, null, null, null, null, null);
		try{
		SaltGenerator twitch = new SaltGenerator();
		
			u.setPasswordSalt(twitch.generateSalt());
			u.setDefaultHighSecurity(secLvl);
		} catch (NoSuchAlgorithmException e1) {
		
			e1.printStackTrace();
		} 
 		testData.setEncryptionKey(test.randomDataKey(testData.isHighSecurity()));		
		System.out.println(testData.getEncryptionKey()+ "\n");
		testData.addDataField("This is SPARTA.");
		testData.addDataField("This too is a test.");
		testData.addDataField("TESTING!");
		testData.addDataField("ASDFASDLKJFALSDKJF!#@!#!@##@LRKAJSFLIAJ[]);;;''");
		testData.addDataField("You may have a mild case of... er.. severe brain damage.");
		
			test.encrypt(u, testData);
			System.out.println(testData.getFieldDataList());
			test.decrypt(u, testData);
			System.out.println(testData.getFieldDataList());
		
	}
	
	
}
