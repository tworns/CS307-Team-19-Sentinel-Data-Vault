package controllers;


import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

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
			String datakey = "/Avatars/Default Avatar.jpg"; //generates a random data key from the Crypto class.
			User newuser =  new User(username, hashedPassword, passwordSalt, datakey, question, answer, createdtime);

			DatabaseManager d = new DatabaseManager("vault_database");
			
			if(d.addUserToDatabase(newuser) == -1) {
				//JOptionPane.showMessageDialog(null,"The account already exists! Please Try another email!");
				return -5;
			}
			else{
				/*	Jiho Choi
				 * Add Sample Data Entry (Account Login, Credit Car) + all
				 * 
				 */
				
				ArrayList<String> sampleDataName = new ArrayList<String>();
				ArrayList<DataEntry> sampleData = new ArrayList<DataEntry>();
				
				sampleDataName.add("Account Login");
				sampleDataName.add("Confirmation Number");
				sampleDataName.add("Credit/Debit Card");
				sampleDataName.add("Entry Code");
				sampleDataName.add("Flight Ticket");
				sampleDataName.add("General Password");
				sampleDataName.add("ID Card");
				sampleDataName.add("License");
				sampleDataName.add("Passport");
				sampleDataName.add("Phone Number");
				sampleDataName.add("Serial Number");
				sampleDataName.add("Shipment Tracking Number");
				sampleDataName.add("SSN");
				sampleDataName.add("Wifi Network");
				
				DatabaseManager m = new DatabaseManager("vault_database");
				
				for (int i=0; i< sampleDataName.size(); i++){
					LocalDateTime time = LocalDateTime.now();
					DataEntry newEntry = new DataEntry("Sample "+ sampleDataName.get(i), sampleDataName.get(i), "key", username, 0, time);
					newEntry.addDataField(""); 	//1
					newEntry.addDataField("");	//2
					newEntry.addDataField("");	//3
					newEntry.addDataField("");	//4
					newEntry.addDataField("");	//5
					newEntry.addDataField("");	//6
					newEntry.addDataField("");	//7
					newEntry.addDataField("");	//8
					newEntry.addDataField("");	//9
					newEntry.addDataField("");	//10
					
					m.addEntryToDatabase(newuser, newEntry);	
				}
				
				/*
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

				
				//Sample Credit/Debit Card
				DataEntry newEntry2 = new DataEntry(username + " Sample Card", "Credit/Debit Card", "key", username, 0, time);
				newEntry2.addDataField("Visa");					//1
				newEntry2.addDataField("0000 0000 0000 0000");	//2
				newEntry2.addDataField("John Purdue");			//3
				newEntry2.addDataField("000");					//4
				newEntry2.addDataField("10/20");				//5
				newEntry2.addDataField("");						//6
				newEntry2.addDataField("");						//7
				newEntry2.addDataField("");						//8
				newEntry2.addDataField("");						//9
				newEntry2.addDataField("");						//10
				
				m.addEntryToDatabase(newuser, newEntry2);
				 */
				
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
		
		LocalDateTime fromDateTime = user.getLastLogin();
		LocalDateTime toDateTime = LocalDateTime.now();
		LocalDateTime tempDateTime = LocalDateTime.from( fromDateTime );
		
		long years = tempDateTime.until( toDateTime, ChronoUnit.YEARS);
		tempDateTime = tempDateTime.plusYears( years );
		
		long months = tempDateTime.until( toDateTime, ChronoUnit.MONTHS);
		tempDateTime = tempDateTime.plusMonths( months );
		
		long days = tempDateTime.until( toDateTime, ChronoUnit.DAYS);
		tempDateTime = tempDateTime.plusDays( days );
		
		long hours = tempDateTime.until( toDateTime, ChronoUnit.HOURS);
		tempDateTime = tempDateTime.plusHours( hours );
		
		long minutes = tempDateTime.until( toDateTime, ChronoUnit.MINUTES);
		tempDateTime = tempDateTime.plusMinutes( minutes );

		long seconds = tempDateTime.until( toDateTime, ChronoUnit.SECONDS);
		tempDateTime = tempDateTime.plusSeconds(seconds);
		
		String freq = user.getBackupFrequency();
		
		if(days>=7){
			JOptionPane.showMessageDialog(null,"It's been a week since the last time you changed your password!");
		}
		
		/******TODO: MainView should take in user OBJECT!**********/
		PasswordValidation p = new PasswordValidation();
		if (p.isValidPassword(user, password)) {
			HomeView window = new HomeView(username);
			//MainView window = new MainView(username);
			window.frmSentinelDataVault.setVisible(true);
			//TODO Perform an automatic backup check here
			checkForAutoBackup(user, user.getLastLogin());
			return 1;
		}
		else {
			return 0;
		}
	}
	
	/**
	 * Determine if a backup should be created. Create one if needed.
	 */
	private static void checkForAutoBackup(User user, LocalDateTime fromDateTime) {
		LocalDateTime toDateTime = LocalDateTime.now();
		LocalDateTime tempDateTime = LocalDateTime.from(fromDateTime);
		
		long yearsSinceLastLogin = tempDateTime.until(toDateTime, ChronoUnit.YEARS);
		long monthsSinceLastLogin = tempDateTime.until(toDateTime, ChronoUnit.MONTHS);
		long daysSinceLastLogin = tempDateTime.until(toDateTime, ChronoUnit.DAYS);
		long hoursSinceLastLogin = tempDateTime.until(toDateTime, ChronoUnit.HOURS);
		
		if (user.getMaxBackupSize() == 1) {
			return;
		}
		else if (user.getMaxBackupSize() == 2 && hoursSinceLastLogin >= 1) {
			if (JOptionPane.showConfirmDialog(null, "It has been over an hour since your last backup. Do you want to create one now?", "Automatic Backup", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
				performBackup(user);
			}
			else {
				return;
			}
		}
		else if (user.getMaxBackupSize() == 3 && daysSinceLastLogin >= 1) {
			if (JOptionPane.showConfirmDialog(null, "It has been over a day since your last backup. Do you want to create one now?", "Automatic Backup", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
				performBackup(user);
			}
			else {
				return;
			}
		}
		else if (user.getMaxBackupSize() == 4 && daysSinceLastLogin >= 7) {
			if (JOptionPane.showConfirmDialog(null, "It has been over a week since your last backup. Do you want to create one now?", "Automatic Backup", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
				performBackup(user);
			}
			else {
				return;
			}
		}
		else if (user.getMaxBackupSize() == 5 && monthsSinceLastLogin >= 1) {
			if (JOptionPane.showConfirmDialog(null, "It has been over a month since your last backup. Do you want to create one now?", "Automatic Backup", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
				performBackup(user);
			}
			else {
				return;
			}
		}
		else if (user.getMaxBackupSize() == 6 && yearsSinceLastLogin >= 1) {
			if (JOptionPane.showConfirmDialog(null, "It has been over a year since your last backup. Do you want to create one now?", "Automatic Backup", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
				performBackup(user);
			}
			else {
				return;
			}
		}
	}
	
	public static void performBackup(User currentUser) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Choose a location to save backup database file");
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // User can only select a directory to store a backup
		fileChooser.setAcceptAllFileFilterUsed(false);
		int result = fileChooser.showDialog(null, "Save Backup");
		if (result == JFileChooser.APPROVE_OPTION) {
			// Execute the backup
			File selectedBackupLocation = fileChooser.getSelectedFile();
			BackupManager bum = new BackupManager();
			System.out.println(selectedBackupLocation.getAbsolutePath());
			bum.createUserBackupDatabase(currentUser, selectedBackupLocation.getAbsolutePath());
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
