package userInterface;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 
import sun.security.provider.MD2;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import controllers.DatabaseManager;
import cryptography.SaltGenerator;

public class LoginView {

	public JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton btnSignUp;
	private JButton btnForgotPassword;
	public String username = "";
	private JLabel lblSentinelDataVault;

	/**
	 * Launch the application.
	 *    
	 * APIs
	 *    
	 * EmailValidator Class   
	 * 	https://commons.apache.org/proper/commons-validator/apidocs/org/apache/commons/validator/routines/EmailValidator.html
	 *    
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView window = new LoginView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Login");
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(81, 95, 56, 18);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(65, 132, 72, 18);
		
		textField = new JTextField();
		textField.setBounds(139, 95, 176, 24);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(139, 129, 176, 24);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBounds(327, 95, 72, 58);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				username = textField.getText();
				String password = String.valueOf(passwordField.getPassword()); // getText() is deprecated; changed to getPassword()
				
				/*
				 * SHA implementation to validate password
				 */
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
						frame.dispose();
						
						/*
						HomeView regFace =new HomeView();
						regFace.frmSentinelDataVault.setVisible(true);
						frame.dispose();
						*/
						
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
			}
		});
		frame.getContentPane().setLayout(null);
		
		btnSignUp = new JButton("Sign up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignupView signup = new SignupView();
				signup.setVisible(true);
			}
		});
		btnSignUp.setBounds(91, 174, 124, 27);
		frame.getContentPane().add(btnSignUp);
		frame.getContentPane().add(btnNewButton);
		frame.getContentPane().add(lblPassword);
		frame.getContentPane().add(lblEmail);
		frame.getContentPane().add(textField);
		frame.getContentPane().add(passwordField);
		
		btnForgotPassword = new JButton("Forgot Password");
		btnForgotPassword.setBounds(229, 174, 140, 27);
		frame.getContentPane().add(btnForgotPassword);
		
		lblSentinelDataVault = new JLabel("Sentinel Data Vault");
		lblSentinelDataVault.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblSentinelDataVault.setBounds(139, 33, 189, 27);
		frame.getContentPane().add(lblSentinelDataVault);
		frame.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblEmail, textField, lblPassword, passwordField, btnNewButton, btnSignUp, btnForgotPassword}));
	}
}
