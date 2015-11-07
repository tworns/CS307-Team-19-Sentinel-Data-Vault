package controllers;


import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Properties;

import javax.swing.JOptionPane;

import com.sun.mail.smtp.SMTPTransport;

import cryptography.SaltGenerator;
import cryptography.PasswordHasher;
import dataManagement.User;
import security.PasswordValidation;
import userInterface.MainView;

import javax.mail.*;
import javax.mail.internet.*;

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
	 
    public static void Send(final String username, final String password, String recipientEmail, String title, String message) throws AddressException, MessagingException {
        Send(username, password, recipientEmail, "", title, message);
    }

    /**
     * Send email using GMail SMTP server.
     *
     * @param username GMail username
     * @param password GMail password
     * @param recipientEmail TO recipient
     * @param ccEmail CC recipient. Can be empty if there is no CC recipient
     * @param title title of the message
     * @param message message to be sent
     * @throws AddressException if the email address parse failed
     * @throws MessagingException if the connection is dead or not in the connected state or if the message is not a MimeMessage
     */
    public static void Send(final String username, final String password, String recipientEmail, String ccEmail, String title, String message) throws AddressException, MessagingException {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

        // Get a Properties object
        Properties props = System.getProperties();
        props.setProperty("mail.smtps.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.setProperty("mail.smtps.auth", "true");

        /*
        If set to false, the QUIT command is sent and the connection is immediately closed. If set 
        to true (the default), causes the transport to wait for the response to the QUIT command.

        ref :   http://java.sun.com/products/javamail/javadocs/com/sun/mail/smtp/package-summary.html
                http://forum.java.sun.com/thread.jspa?threadID=5205249
                smtpsend.java - demo program from javamail
        */
        props.put("mail.smtps.quitwait", "false");

        Session session = Session.getInstance(props, null);

        // -- Create a new message --
        final MimeMessage msg = new MimeMessage(session);

        // -- Set the FROM and TO fields --
        msg.setFrom(new InternetAddress(username + "@gmail.com"));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail, false));

        if (ccEmail.length() > 0) {
            msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccEmail, false));
        }

        msg.setSubject(title);
        msg.setText(message, "utf-8");
        msg.setSentDate(new Date());

        SMTPTransport t = (SMTPTransport)session.getTransport("smtps");

        t.connect("smtp.gmail.com", username, password);
        t.sendMessage(msg, msg.getAllRecipients());      
        t.close();
    }
}
