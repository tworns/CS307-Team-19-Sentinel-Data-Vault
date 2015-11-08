package cryptography;

import dataManagement.*;
import sun.misc.*;

import java.io.IOException;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

import java.util.ArrayList;
import java.util.List;
public class Crypto {
	
	public Crypto (){ 
	}
	public String randomDataKey() { //TODO Figure out what we're doing regarding security levels. 
		byte[] dataKey = new byte [16]; 
		byte[] secureDataKey = new byte[32]; //foundation for 256 bit data key in place as secureDataKey
		SecureRandom r = new SecureRandom();
		r.nextBytes(dataKey);
		r.nextBytes(secureDataKey); //gens the 256 bit data key
		String secureOutput = new String(secureDataKey);  //is the 256 bit data key.
		
		String output = new String(dataKey);
		return output;
	}
	// We're using AES encryption. It's symmetric (same key for encrypt/decrypt).
	public Key keyGen (User user) throws NoSuchAlgorithmException, NoSuchPaddingException { 
		//DataEntries created by the User will be given the key that the user has. 
		 //USER KEY GOES INTO THE SECRET KEY SPEC!
		Key key = new SecretKeySpec(user.getDataKey().getBytes(), "AES");
		
		return key;
	}
	public DataEntry encrypt(User user, DataEntry data) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException { //Return type is temporary
				
		try {
			List<String> dataList = new ArrayList<String>();
			Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
			Key key = keyGen(user); // makes a key from the user's data key.
			c.init(Cipher.ENCRYPT_MODE, key); //make a cipher.
			for(String entry : data.getFieldDataList()){	//NOTE: If there is a null entry in this list, you WILL get a NullPointerException
				byte[] encrypted = c.doFinal(entry.getBytes()); //loops through the list, encrypting as it goes
				BASE64Encoder k = new BASE64Encoder();
				String encryptedData = k.encode(encrypted);
				dataList.add(encryptedData);//adds the encrypted data to the temp list
			}
			data.setDataFields(dataList); //sets temp list into the dataEntry.
			
			
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	
	public DataEntry decrypt(User user, DataEntry data) throws IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException { //Return type is temporary
		try {
			Key key = keyGen(user);
			List<String> dataList = new ArrayList<String>();
			Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
			c.init(Cipher.DECRYPT_MODE, key);
			for(String entry : data.getFieldDataList()){	//NOTE: If there is a null entry in this list, you WILL get a NullPointerException
				BASE64Decoder k = new BASE64Decoder();
				byte[] decryptedBytes = k.decodeBuffer(entry);
				byte[] decryptedData = c.doFinal(decryptedBytes);
				String output = new String(decryptedData);
				dataList.add(output);
			}
			data.setDataFields(dataList);
			
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
	
		return data;
	}
	
	
}
