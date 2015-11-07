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
	byte [] test = {'t', 'e','s', 't', }; //TODO Generate a real key
	public String randomDataKey() { 
		//TODO make a string of random junk to be put into the user.Datakey field.
		byte[] dataKey = new byte [16]; 
		SecureRandom r = new SecureRandom();
		r.nextBytes(dataKey);
		String output = new String(dataKey);
		return output;
	}
	// We're using AES encryption. It's symmetric (same key for encrypt/decrypt).
	public Key keyGen (User user) throws NoSuchAlgorithmException, NoSuchPaddingException { //TODO Figure out a return, need to return 2 keys, high security and lower security. Both genned here
		//DataEntries created by the User will be given the key that the user has. 

		
		 //USER KEY GOSE INTO THE SECRET KEY SPEC!
		Key key = new SecretKeySpec(user.getDataKey().getBytes(), "AES");
		
		return key;
	}
	public DataEntry encrypt(User user, DataEntry data) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException { //Return type is temporary
				
		try {
			List<String> dataList = new ArrayList<String>();
			Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
			Key key = keyGen(user); // makes a key from the user's data key.
			c.init(Cipher.ENCRYPT_MODE, key);
			for(String entry : data.getFieldDataList()){	
				byte[] encrypted = c.doFinal(entry.getBytes());
				BASE64Encoder k = new BASE64Encoder();
				String encryptedData = k.encode(encrypted);
				dataList.add(encryptedData);
			}
			data.setDataFields(dataList);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	public DataEntry decrypt(User user, DataEntry data) throws IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException { //Return type is temporary
		try {
			Key key = keyGen(user);
			List<String> dataList = new ArrayList<String>();
			Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
			c.init(Cipher.ENCRYPT_MODE, key);
			for(String entry : data.getFieldDataList()){	
				BASE64Decoder k = new BASE64Decoder();
				byte[] decryptedBytes = k.decodeBuffer(entry);
				byte[] decryptedData = c.doFinal(decryptedBytes);
				String output = new String(decryptedData);
				dataList.add(output);
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data;
	}
	
	
}
