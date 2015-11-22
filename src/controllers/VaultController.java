package controllers;


import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Properties;

import javax.swing.JOptionPane;

import com.sun.mail.smtp.SMTPTransport;

import cryptography.SaltGenerator;
import cryptography.Crypto;
import cryptography.PasswordHasher;
import dataManagement.DataEntry;
import dataManagement.User;
import security.PasswordValidation;
import userInterface.*;


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
			//JOptionPane.showMessageDialog(null,"You need to enter all the fields!");
			password1 = "";
			password2 = "";
			return 0;
		}
		
		//check is valid email address
		if(isValidEmailAddress(username)== false){
			//JOptionPane.showMessageDialog(null,"You need to enter a valid email address!");
			return -1;
		}
		
		//check password consistency
		if(!(password1.equals(password2))){
			//JOptionPane.showMessageDialog(null,"Your password doesn't match");
			password1 = "";
			password2 = "";
			return -2;
		}
		
		//minimum password length check
		PasswordValidation a = new PasswordValidation();
		//if(((password1.length() < 8) && (password1.length()>0)) || 
			//	((password2.length() < 8) && (password2.length()>0))){
		if(a.minStandard(password1) == false){
			//JOptionPane.showMessageDialog(null,"Your password needs to contain at least 8 characters and at least one of each\n"
			//		+ " of the following: uppercase letter, lowercase letter, number, special character!");
			password1 = "";
			password2 = "";
			return -3;
		}
		
		//security question check
		if(question.equals("")||question.equals("Please choose a security question below")){
			//JOptionPane.showMessageDialog(null,"Please choose one security question!");
			return -4;
		}
		
		//send user info to database
		if (password1.equals(password2) && !password1.equals("") && !username.equals("")
				&&!answer.equals("")){
			
			PasswordHasher ph = new PasswordHasher();
			
			SaltGenerator s = new SaltGenerator();
			String passwordSalt = s.generateSalt();
			
			String hashedPassword = ph.hashPassword(password1, passwordSalt);
			
			LocalDateTime createdtime = LocalDateTime.now();
			
			
			//Crypto c = new Crypto();
			//int defaultSecLvl = 0; // This is the default security level. Needed because of the way key gen works.
			String datakey = null; // c.randomDataKey(defaultSecLvl); //generates a random data key from the Crypto class.
			User newuser =  new User(username, hashedPassword, passwordSalt, datakey, question, answer, createdtime);

			DatabaseManager d = new DatabaseManager("vault_database");
			
			if(d.addUserToDatabase(newuser) == -1) {
				//JOptionPane.showMessageDialog(null,"The account already exists! Please Try another email!");
				return -5;
			}
			else{
				/*	Jiho Choi
				 * Add Sample Data Entry (Account Login, Credit Car)
				 * 
				 */
				LocalDateTime time = LocalDateTime.now();
				//Sample Account Login
				DataEntry newEntry = new DataEntry(username + " Sample Login", "Account Login", "key", username, 0, time);
				newEntry.addDataField("johnpurdue"); 	//1
				newEntry.addDataField("12345678");		//2
				newEntry.addDataField("google.com");	//3
				newEntry.addDataField("");				//4
				newEntry.addDataField("");				//5
				newEntry.addDataField("");				//6
				newEntry.addDataField("");				//7
				newEntry.addDataField("");				//8
				newEntry.addDataField("");				//9
				newEntry.addDataField("");				//10
				DatabaseManager m = new DatabaseManager("vault_database");
				m.addEntryToDatabase(newEntry);
				
				//Sample Credit/Debit Card
				DataEntry newEntry2 = new DataEntry(username + " Sample Card", "Credit/Debit Card", "key", username, 0, time);
				newEntry2.addDataField("VISA");					//1
				newEntry2.addDataField("0000 0000 0000 0000");	//2
				newEntry2.addDataField("John Purdue");			//3
				newEntry2.addDataField("000");					//4
				newEntry2.addDataField("10/20");				//5
				newEntry2.addDataField("");						//6
				newEntry2.addDataField("");						//7
				newEntry2.addDataField("");						//8
				newEntry2.addDataField("");						//9
				newEntry2.addDataField("");						//10
				
				m.addEntryToDatabase(newEntry2);

				
				JOptionPane.showMessageDialog(null,"You have successfully created your account!");
				
				return 1;
			}
		}
		return 0;
	}
	
	//*****TODO: When user successfully login, we need to update the last login time******
	public int loginCheck(String username, String password) throws NoSuchAlgorithmException {
		DatabaseManager d = new DatabaseManager("vault_database");
		User user =d.retrieveUserFromDatabase(username);
		if(user == null){
			//JOptionPane.showMessageDialog(null,"Incorrect email / password!");
			return 0;
		}
		/******TODO: MainView should take in user OBJECT!**********/
		PasswordValidation p = new PasswordValidation();
		if(p.isValidPassword(user, password)){
			HomeView window = new HomeView(username);
			//MainView window = new MainView(username);
			window.frmSentinelDataVault.setVisible(true);
			return 1;
		}
		else{
			//JOptionPane.showMessageDialog(null,"Incorrect email / password!");
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
