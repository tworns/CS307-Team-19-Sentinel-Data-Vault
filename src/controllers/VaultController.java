package controllers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Properties;

import javax.swing.JOptionPane;
import cryptography.SaltGenerator;
import cryptography.PasswordHasher;
import dataManagement.User;
import security.PasswordValidation;
import userInterface.MainView;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

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
			
			//TODO: ********default data key is a place holder*******
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
	
	public int loginCheck(String username, String password) throws NoSuchAlgorithmException {
		DatabaseManager d = new DatabaseManager();
		User user = d.retrieveUserFromDatabase(username);
		/******TODO: MainView should take in user OBJECT!**********/
		PasswordValidation p = new PasswordValidation(password);
		if(p.isValidPassword(user, password)){
			MainView window = new MainView(username);
			window.frmSentinelDataVault.setVisible(true);
			return 1;
		}
		else{
			JOptionPane.showMessageDialog(null,"Incorrect email/ password!");
			return 0;
		}
	}
	 
	public void sendEmail(String email){    
	      // Recipient's email ID needs to be mentioned.
	      String to = email;

	      // Sender's email ID needs to be mentioned
	      String from = "sentineldatavault@gmail.com";

	      // Assuming you are sending email from localhost
	      String host = "localhost";

	      // Get system properties
	      Properties properties = System.getProperties();

	      // Setup mail server
	      properties.setProperty("mail.smtp.host", host);

	      // Get the default Session object.
	      Session session = Session.getDefaultInstance(properties);

	      try{
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

	         // Set Subject: header field
	         message.setSubject("This is the Subject Line!");

	         // Now set the actual message
	         message.setText("This is actual message");

	         // Send message
	         Transport.send(message);
	         System.out.println("Sent message successfully....");
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	   }

}
