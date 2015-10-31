package controllers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;
import cryptography.SaltGenerator;
import cryptography.PasswordHasher;
import dataManagement.User;
import userInterface.MainView;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class VaultController {
	//helper function to check email address validity
	private static boolean isValidEmailAddress(String email) {
		   boolean result = true;
		   try {
		      InternetAddress emailAddr = new InternetAddress(email);
		      emailAddr.validate();
		   } catch (AddressException ex) {
		      result = false;
		   }
		   return result;
	}
	
	
	//function called by signupview
	public int createAccountCheck(String password1, String password2, String username,String question, String answer) throws NoSuchAlgorithmException{
		
		//check user enter all fields
		if (password1.equals("") || password2.equals("")||username.equals("")||answer.equals("")){
			JOptionPane.showMessageDialog(null,"You need to enter all the fields!");
			password1 = "";
			password2 = "";
			return 0;
		}
		
		//check is valid email address
		if(isValidEmailAddress(username)== false){
			JOptionPane.showMessageDialog(null,"You need to enter a valid email address!");
			return 0;
		}
		
		//check password consistency
		if(!(password1.equals(password2))){
			JOptionPane.showMessageDialog(null,"Your password doesn't match");
			password1 = "";
			password2 = "";
			return 0;
		}
		
		//minimum password length check
		if(((password1.length() < 8) && (password1.length()>0)) || 
				((password2.length() < 8) && (password2.length()>0))){
			JOptionPane.showMessageDialog(null,"Your password needs to contain at least 8 characters!");
			password1 = "";
			password2 = "";
			return 0;
		}
		
		//security question check
		if(question.equals("")||question.equals("Please choose a security question below")){
			JOptionPane.showMessageDialog(null,"Please choose one security question!");
			return 0;
		}
		
		
		
		//send user info to database
		if (password1.equals(password2) && !password1.equals("") && !username.equals("")
				&&!answer.equals("")){
			
			PasswordHasher ph = new PasswordHasher();
			
			SaltGenerator s = new SaltGenerator();
			String passwordSalt = s.generateSalt();
			
			String hashedPassword = ph.hashPassword(password1, passwordSalt);
			
			LocalDateTime createdtime = LocalDateTime.now();
			
			//TO DO: ********default data key is a place holder*******
			User newuser =  new User(username, hashedPassword, passwordSalt, "default datakey", question, answer, createdtime);

			DatabaseManager d = new DatabaseManager();
			
			if(d.addUserToDatabase(newuser) == -1) {
				JOptionPane.showMessageDialog(null,"The account has already existed! Please Try another email!");
				return 0;
			}
			else{
				JOptionPane.showMessageDialog(null,"You have successfully created your account!");
				return 1;
			}
		}
		return 0;
	}
	
	public int loginCheck(String username, String password) {
		try {
			// Generate some tasty salt
			SaltGenerator seasoner = new SaltGenerator();
			String salt = seasoner.generateSalt(); // TODO actual user salt will be retrieved from database
			String saltedPassword = salt + password;
			// Update the digest with the password in byte form
			MessageDigest md = MessageDigest.getInstance("SHA-256"); // TODO algorithm should depend on user's security level setting
			md.update(saltedPassword.getBytes());
			// Hash the digest
			byte byteData[] = md.digest();
			// Convert the hash to hex for storage/comparison
			StringBuffer hexPasswordBuff = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				String hex = Integer.toHexString(0xff & byteData[i]);
				if (hex.length() == 1) {
					hexPasswordBuff.append('0');
				}
				hexPasswordBuff.append(hex);
			}
			String hexPassword = hexPasswordBuff.toString();
			// Reset the MessageDigest
			md.reset();
			// Get the user's actual password 
			// This actual password hash will eventually be retrieved from database
			DatabaseManager dataManager = new DatabaseManager();
			String truePassword = dataManager.retrievePassword(username);
			String saltedTruePassword = salt + truePassword;
			// Update the digest with the test password in byte form
			md.update(saltedTruePassword.getBytes());
			// Hash the digest
			byte byteData2[] = md.digest();
			// Convert the hash to hex for storage
			StringBuffer hexPasswordBuff2 = new StringBuffer();
			for (int i = 0; i < byteData2.length; i++) {
				String hex2 = Integer.toHexString(0xff & byteData2[i]);
				if (hex2.length() == 1) {
					hexPasswordBuff2.append('0');
				}
				hexPasswordBuff2.append(hex2);
			}
			String hexTruePassword = hexPasswordBuff2.toString();
			// Test that entered password hash matches user's stored password hash
			// Condition needs to be changed accordingly
			if (username.equals(dataManager.retrieveUsername()) && hexPassword.equals(hexTruePassword)) {
				
				MainView window = new MainView(username);
				window.frmSentinelDataVault.setVisible(true);
				
			}
			else {
				JOptionPane.showMessageDialog(null,"Wrong Password / Username");
				textField.setText("");
				passwordField.setText("");
				textField.requestFocus();
			}
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return 1;
	}
}
