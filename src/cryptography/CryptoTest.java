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
		DataEntry testData = new DataEntry(null, null, null, null, 0, null);
		User u = new User(null, null, null, null, null, null, null);
		try{
		SaltGenerator twitch = new SaltGenerator();
		
			u.setPasswordSalt(twitch.generateSalt());
			u.setDefaultHighSecurity(secLvl);
		} catch (NoSuchAlgorithmException e1) {
		
			e1.printStackTrace();
		} 
 		testData.setEncryptionKey(test.randomDataKey(u.isHighSecurity()));
		//testData.setEncryptionKey(u.getDataKey());		
		System.out.println(u.getDataKey()+ "\n");
		testData.addDataField("This is a test string.");
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
