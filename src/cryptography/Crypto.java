package cryptography;

import dataManagement.*;
import sun.misc.*;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

import java.util.ArrayList;
import java.util.Base64.Encoder;
import java.util.List;
public class Crypto {
	
	public Crypto (){ 
	}
	byte [] test = {'t', 'e','s', 't', };
	// We're using AES encryption. It's symmetric (same key for encrypt/decrypt).
	public Key keyGen () throws NoSuchAlgorithmException, NoSuchPaddingException { //TODO Figure out a return, need to return 2 keys, high security and lower security. Both genned here
		//DataEntries created by the User will be given the key that the user has. 
		int lowKey = 128;
		int secKey = 256;
		 
		Key key = new SecretKeySpec(test, "AES");
		return key;
		
	}
	public DataEntry encrypt(User user, DataEntry data) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException { //Return type is temporary
		Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
		Key key = keyGen(); //TODO GET THIS KEY IN A USER
		List<String> dataList = new ArrayList<String>();
		try {
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
	public DataEntry decrypt(User user, DataEntry data) { //Return type is temporary
		
		return data;
	}
	
	
}
