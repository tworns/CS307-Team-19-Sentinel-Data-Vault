package userInterface;

import java.awt.EventQueue;
import java.awt.TextField;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import controllers.DatabaseManager;
import cryptography.SaltGenerator;
import dataManagement.User;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;


public class PasswordRecoveryView {

	public JFrame frmSentinelDataVault;
	private String securityCode;
	private User user = null;
	private JTextField textField;
	String username;
	private JTextField txtQuestion;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//User u = new User ("dave@purdue.edu",null,null, null,  "Sec Q", "Sec Ans", null);
					PasswordRecoveryView window = new PasswordRecoveryView();
					//window.frmSentinelDataVault.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the application.
	 */
	public PasswordRecoveryView() {
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSentinelDataVault = new JFrame();
		frmSentinelDataVault.setResizable(false);
		frmSentinelDataVault.setTitle("Password Recovery");
		frmSentinelDataVault.setBounds(100, 100, 431, 345);
		frmSentinelDataVault.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmSentinelDataVault.getContentPane().setLayout(null);
		frmSentinelDataVault.setLocationRelativeTo(null);
		DatabaseManager overseer = new DatabaseManager();
		textField = new JTextField();
		textField.setBounds(12, 55, 200, 18);
		textField.setColumns(10);
		 
		textField.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				username = textField.getText();
				user = overseer.retrieveUserFromDatabase(username); //TODO Make the username field appear by itself. Then pop up other fields as needed
			} 
			
		});
			
		frmSentinelDataVault.getContentPane().add(textField);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(244, 56, 99, 16);
		frmSentinelDataVault.getContentPane().add(lblUsername);
		
		txtQuestion = new JTextField();
		txtQuestion.setText(user.getSecurityQuestion());
		txtQuestion.setBounds(12, 97, 331, 22);
		frmSentinelDataVault.getContentPane().add(txtQuestion);
		txtQuestion.setColumns(10);
			
			
			
			
			
			
			
			
			
			
			
			
			
		
		
		
		if(user.getSecurityAnswer() != null){
		System.out.println(user.getSecurityAnswer());
		String ans =  JOptionPane.showInputDialog(user.getSecurityQuestion());
		if(ans.equals(user.getSecurityAnswer())){
		
		try {	
			SaltGenerator pepper = new SaltGenerator();
			 securityCode = pepper.generateSalt();
			 System.out.println(securityCode);	 
			/*VaultController.Send("sentineldatavault", "SENTINELDATA", user.getUsername(), 
					"Security Warning", "Dear user,\n\n To continue recovering your account password, please enter the code below into the Sentinel Data Vault.\n"
							+ "If you did not initiate this change, this e-mail can be safely disregarded.\n\n"+
							"Your security code is: " + securityCode +"\n\n" +
							"Sincerely,\nSentinel Data Vault Team");
							*/
			String code =	JOptionPane.showInputDialog("Please input the security code that was emailed to you");
			if(securityCode.equals(code)) { 
				System.out.println("WE DID IT REDDIT!");
				}
			else { 
				System.out.println("Incorrect code");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		}
		else  {
			System.out.println("Ya dun goofed up.");
		}		
		}
	}
}