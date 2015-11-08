package cryptography;

import dataManagement.*;
import sun.misc.*;

import java.io.IOException;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.util.ArrayList;
import java.util.List;
public class Crypto {
	
	public Crypto (){ 
	}
	public String randomDataKey(int security) { //TODO Figure out what we're doing regarding security levels. 
		String output;
		byte[] dataKey = new byte [24]; 
		byte[] secureDataKey = new byte[16]; //foundation for 256 bit data key in place as secureDataKey
		SecureRandom r = new SecureRandom();
		r.nextBytes(dataKey);
		r.nextBytes(secureDataKey); //gens the 256 bit data key
		
		String secureOutput = new String(secureDataKey);  //is the 256 bit data key.
		String normalOutput = new String(dataKey);
		if( security == 1) { 
			output = secureOutput;
		}
		else { 
			output = normalOutput;
		}
		return output;
	}
	// We're using AES encryption. It's symmetric (same key for encrypt/decrypt).
	public Key keyGen (User user) throws NoSuchAlgorithmException, NoSuchPaddingException { 
		//DataEntries created by the User will be given the key that the user has. 
		 //USER KEY GOES INTO THE SECRET KEY SPEC!
		Key key;
		if(user.isHighSecurity() == 1) { 
		 key = new SecretKeySpec(user.getDataKey().getBytes(), "AES");
		}
		else{ 
			key = new SecretKeySpec(user.getDataKey().getBytes(), "DESede");
		}
		return key;
	}
	public byte[] ivGen(User user) { //Encryption was insecure, needed salt..
		String iv = user.getPasswordSalt();
		if(user.isHighSecurity() == 1) { //AES
		char[] salt16 = new char[16];
		iv.getChars(0, 16, salt16, 0);
		return new String(salt16).getBytes();
		}
		
		else { //3DES
		 char[] salt8 = new char[8];
		 iv.getChars(0, 8, salt8, 0);
		 return new String(salt8).getBytes();		
		 }
	}
	public DataEntry encrypt(User user, DataEntry data) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException { //Return type is temporary
				
		try {
			Cipher c;
			IvParameterSpec iv = new IvParameterSpec(ivGen(user));
			List<String> dataList = new ArrayList<String>();
			Key key = keyGen(user); // makes a key from the user's data key.
			if(user.isHighSecurity() == 1) { 
				 c = Cipher.getInstance("AES/CBC/PKCS5Padding");
				c.init(Cipher.ENCRYPT_MODE, key, iv); //make a cipher.
				System.out.println("More secure\n");
			}
			else { 
				 c = Cipher.getInstance("DESede/CBC/PKCS5Padding");
				c.init(Cipher.ENCRYPT_MODE, key, iv);
				System.out.println("Low security\n");
			}
			for(String entry : data.getFieldDataList()){	//NOTE: If there is a null entry in this list, you WILL get a NullPointerException
				byte[] encrypted = c.doFinal(entry.getBytes()); //loops through the list, encrypting as it goes
				BASE64Encoder k = new BASE64Encoder();
				String encryptedData = k.encode(encrypted);
				dataList.add(encryptedData);//adds the encrypted data to the temp list
			}
			data.setDataFields(dataList); //sets temp list into the dataEntry.
			
			
		} catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	
	public DataEntry decrypt(User user, DataEntry data) throws IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException { //Return type is temporary
		try {
			Key key = keyGen(user);
			Cipher c;
			List<String> dataList = new ArrayList<String>();
			
			if(user.isHighSecurity()==1) { 
				c = Cipher.getInstance("AES/CBC/PKCS5Padding");
				c.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(ivGen(user)));
			}
			else { 
				 c = Cipher.getInstance("DESede/CBC/PKCS5Padding");
				c.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(ivGen(user)));
			}
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
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
	
		return data;
	}
	
	
}
