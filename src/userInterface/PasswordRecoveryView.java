package userInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import controllers.DatabaseManager;
import controllers.VaultController;
import cryptography.SaltGenerator;
import dataManagement.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PasswordRecoveryView {

	public JFrame frmSentinelDataVault;
	private JTextField textField_1;
	private JTextField txtQuestion;
	private JTextField textField_2;
	private String securityCode;
	private User user = null;
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
		DatabaseManager overseer = new DatabaseManager();
		
		/*frmSentinelDataVault = new JFrame();
		frmSentinelDataVault.setTitle("Sentinel Data Vault Account Recovery");
		frmSentinelDataVault.setBounds(100, 100, 456, 303);
		frmSentinelDataVault.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmSentinelDataVault.getContentPane().setLayout(null);
		
		JLabel lblSentinelDataVault = new JLabel("Sentinel Data Vault Account Recovery");
		lblSentinelDataVault.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSentinelDataVault.setBounds(58, 24, 315, 16);
		frmSentinelDataVault.getContentPane().add(lblSentinelDataVault);
		
		textField_1 = new JTextField();
		textField_1.setBounds(52, 106, 116, 22);
		frmSentinelDataVault.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblSecurityQuestionAnswer = new JLabel("Security Question Answer");
		lblSecurityQuestionAnswer.setLabelFor(textField_1);
		lblSecurityQuestionAnswer.setBounds(204, 109, 154, 16);
		frmSentinelDataVault.getContentPane().add(lblSecurityQuestionAnswer);
		*/
		String name = JOptionPane.showInputDialog("Please input your username to begin the account recovery process.");
		user = overseer.retrieveUserFromDatabase(name); //TODO Make the username field appear by itself. Then pop up other fields as needed
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
