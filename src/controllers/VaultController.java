package controllers;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;
import cryptography.SaltGenerator;
import cryptography.PasswordHasher;
import dataManagement.User;
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
}
