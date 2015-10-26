package controllers;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;
import cryptography.SaltGenerator;
import dataManagement.User;

public class VaultController {
	
	public int createAccountCheck(String password1, String password2, String username,String question, String answer) throws NoSuchAlgorithmException{
		//ToDo: **********Need to add valid email address check***********
		
		//check user enter all fields
		if (password1.equals("") || password2.equals("")||username.equals("")||answer.equals("")){
			JOptionPane.showMessageDialog(null,"You need to enter all the fields!");
			password1 = "";
			password2 = "";
			return 1;
		}
		//check password consistency
		if(!(password1.equals(password2))){
			JOptionPane.showMessageDialog(null,"Your password doesn't match");
			password1 = "";
			password2 = "";
			return 2;
		}
		
		//minimum password length check
		if(((password1.length() < 8) && (password1.length()>0)) || 
				((password2.length() < 8) && (password2.length()>0))){
			JOptionPane.showMessageDialog(null,"Your password needs to contain at least 8 characters!");
			password1 = "";
			password2 = "";
			return 3;
		}
		
		if(question.equals(null)||question.equals("")||question.equals("Please choose a security question below")){
			JOptionPane.showMessageDialog(null,"Please choose one security question!");
			return 6;
		}
		
		//send user info to database
		if (password1.equals(password2) && !password1.equals("") && !username.equals("")
				&&!answer.equals("")){
			SaltGenerator s = new SaltGenerator();
			String passwordSalt = s.generateSalt();
			
			LocalDateTime createdtime = LocalDateTime.now();
			User newuser =  new User(username, password1, passwordSalt, "default datakey", question, answer, createdtime);

			DatabaseManager d = new DatabaseManager();
			d.addUserToDatabase(newuser);
			JOptionPane.showMessageDialog(null,"You have successfully created your account!");
			return 4;
		}
		return 5;
	}
}
