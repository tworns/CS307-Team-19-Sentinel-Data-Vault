package userInterface;

import java.awt.EventQueue;
import java.awt.TextField;


import javax.swing.JFrame;
import controllers.DatabaseManager;
import controllers.VaultController;
import cryptography.SaltGenerator;
import dataManagement.User;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.mail.MessagingException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.awt.event.ActionEvent;
import java.awt.Color;



public class PasswordRecoveryView {

	public JFrame frmSentinelDataVault;
	private JTextField textField;
	User user;
	String username;
	private JTextField invalidUser;
	private JTextField securityQuestion;
	private JLabel lblNewLabel;
	private JTextField securityQuestionAnswer;
	private JButton btnValidate_1;
	private JTextField securityCode;
	private JLabel lblSecurityCode;
	private JButton btnValidate_2;
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
		frmSentinelDataVault = new JFrame();
		frmSentinelDataVault.setResizable(false);
		frmSentinelDataVault.setTitle("Password Recovery");
		frmSentinelDataVault.setBounds(100, 100, 431, 345);
		frmSentinelDataVault.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmSentinelDataVault.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(22, 35, 116, 22);
		frmSentinelDataVault.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(149, 38, 79, 16);
		frmSentinelDataVault.getContentPane().add(lblUsername);
		
		JButton btnValidate = new JButton("Validate");
		btnValidate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				user = overseer.retrieveUserFromDatabase(textField.getText());
				if(user == null) { 
				 invalidUser.setText("Invalid User");
				}
				else{ 
					securityQuestion.setText(user.getSecurityQuestion());
					securityQuestion.setVisible(true);
					securityQuestionAnswer.setVisible(true);
					btnValidate.setVisible(false);
					btnValidate_1.setVisible(true);
				}
			}
		});
		btnValidate.setBounds(283, 34, 97, 25);
		frmSentinelDataVault.getContentPane().add(btnValidate);
		
		invalidUser = new JTextField();
		invalidUser.setForeground(Color.RED);
		invalidUser.setEditable(false);
		invalidUser.setFocusTraversalKeysEnabled(false);
		invalidUser.setFocusable(false);
		invalidUser.setRequestFocusEnabled(false);
		invalidUser.setOpaque(false);
		invalidUser.setBorder(null);
		invalidUser.setBounds(140, 13, 116, 22);
		frmSentinelDataVault.getContentPane().add(invalidUser);
		invalidUser.setColumns(10);
		
		securityQuestion = new JTextField();
		securityQuestion.setBorder(null);
		securityQuestion.setEditable(false);
		securityQuestion.setFocusTraversalKeysEnabled(false);
		securityQuestion.setFocusable(false);
		securityQuestion.setRequestFocusEnabled(false);
		securityQuestion.setOpaque(false);
		securityQuestion.setVisible(false);
		securityQuestion.setBounds(22, 72, 230, 22);
		frmSentinelDataVault.getContentPane().add(securityQuestion);
		securityQuestion.setColumns(10);
		
		lblNewLabel = new JLabel("Security Answer");
		lblNewLabel.setVisible(false);
		lblNewLabel.setBounds(149, 113, 122, 16);
		frmSentinelDataVault.getContentPane().add(lblNewLabel);
		
		securityQuestionAnswer = new JTextField();
		securityQuestionAnswer.setVisible(false);
		securityQuestionAnswer.setBounds(22, 110, 116, 22);
		frmSentinelDataVault.getContentPane().add(securityQuestionAnswer);
		securityQuestionAnswer.setColumns(10);
		
		btnValidate_1 = new JButton("Validate");
		btnValidate_1.setVisible(false);
		btnValidate_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(user.getSecurityAnswer().equals(securityQuestionAnswer.getText())) { 
					btnValidate_1.setVisible(false);
					btnValidate_2.setVisible(true);
					securityCode.setVisible(true);
					lblSecurityCode.setVisible(true);
					try {
						SaltGenerator chat = new SaltGenerator();
						String code = chat.generateSalt();
						VaultController.Send("sentineldatavault", "SENTINELDATA", user.getUsername(), 
								"Security Warning", "Dear user,\n\n To continue recovering your account password, please enter the code below into the Sentinel Data Vault.\n"
										+ "If you did not initiate this change, this e-mail can be safely disregarded.\n\n"+
										"Your security code is: " + code +"\n\n" +
										"Sincerely,\nSentinel Data Vault Team");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
									
					if(securityCode.equals(securityCode.getText())) { 
						System.out.println("WE DID IT REDDIT!");
						}
					else { 
						System.out.println("Incorrect code");
					}
					JOptionPane.showMessageDialog(null, "An email containing your security code has been sent to you.\n"
							+ " If you do not find this email in your inbox, please check your spam folder", "Change Password", 0);
				}
			}
		});
		btnValidate_1.setBounds(283, 109, 97, 25);
		frmSentinelDataVault.getContentPane().add(btnValidate_1);
		
		securityCode = new JTextField();
		securityCode.setVisible(false);
		securityCode.setBounds(22, 155, 116, 22);
		frmSentinelDataVault.getContentPane().add(securityCode);
		securityCode.setColumns(10);
		
		lblSecurityCode = new JLabel("Security Code");
		lblSecurityCode.setVisible(false);
		lblSecurityCode.setBounds(149, 158, 107, 16);
		frmSentinelDataVault.getContentPane().add(lblSecurityCode);
		
		btnValidate_2 = new JButton("Validate");
		btnValidate_2.setVisible(false);
		btnValidate_2.setBounds(283, 154, 97, 25);
		frmSentinelDataVault.getContentPane().add(btnValidate_2);
		frmSentinelDataVault.setLocationRelativeTo(null);
		frmSentinelDataVault.setVisible(true);
	
		
	}
}


