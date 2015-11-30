package userInterface;

import java.awt.*;
import java.awt.event.*;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.swing.*;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import controllers.DatabaseManager;
import controllers.VaultController;
import dataManagement.User;

public class LoginView {

	public JFrame frmSignIn;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton btnSignUp;
	private JButton btnForgotPassword;
	public String username = "";
	private JLabel lblSentinelDataVault;
	private int failedattempt = 0;
	private JTextField txtWarning;

	/**
	 * Launch the application.
	 * 
	 * APIs
	 * 
	 * EmailValidator Class
	 * https://commons.apache.org/proper/commons-validator/apidocs/org/apache/
	 * commons/validator/routines/EmailValidator.html
	 * 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView window = new LoginView();
					window.frmSignIn.setVisible(true);
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
		frmSignIn = new JFrame("Login");
		frmSignIn.setTitle("Sign in");
		frmSignIn.setResizable(false);
		frmSignIn.setBounds(100, 100, 450, 357);
		frmSignIn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSignIn.setLocationRelativeTo(null);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(73, 108, 56, 18);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(57, 145, 72, 18);

		textField = new JTextField();
		textField.setBounds(130, 108, 184, 24);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(130, 142, 184, 24);
		
		char a = passwordField.getEchoChar();

		JCheckBox chckbxShowPassword = new JCheckBox("Show Password");
		chckbxShowPassword.setBounds(130, 176, 128, 23);
		frmSignIn.getContentPane().add(chckbxShowPassword);

		chckbxShowPassword.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					passwordField.setEchoChar((char)0);
				} else {
					passwordField.setEchoChar(a); // TODO Get this to something that actually looks good
				}
			}
		});

		JButton btnNewButton = new JButton("Sign in");
		btnNewButton.setBounds(325, 106, 72, 62);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				username = textField.getText();
				String password = String.valueOf(passwordField.getPassword()); // getText()
																				// is
																				// deprecated;
																				// changed
																				// to
																				// getPassword()

				/*
				 * SHA implementation to validate password
				 */

				VaultController v = new VaultController();
				int result = 0;
				try {
					result = v.loginCheck(username, password);
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (result == 1) {
					failedattempt = 0;
					frmSignIn.dispose();

				} else {
					txtWarning.setText("The Email and/or Password is incorrect. Please try again.");
					failedattempt++;
				}
				// TODO migrate failcheck to Vault controller!
				if (failedattempt > 1 && failedattempt < 5) {
					try {
						VaultController.Send("sentineldatavault", "SENTINELDATA", username, "Security Warning",
								"Dear user,\n\nYou have multiple failed login attempts for your account.\n"
										+ "If it is not you, please change your password immediately.\n\n"
										+ "Sincerely,\nSentinel Data Vault Team");
					} catch (AddressException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (MessagingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (failedattempt == 5) {
					DatabaseManager d = new DatabaseManager("vault_database");
					User u = d.retrieveUserFromDatabase(username);
					d.deleteAllEntriesFromDatabase(u);
					d.deleteUserFromDatabase(u);
					try {
						JOptionPane.showMessageDialog(null,
								"Your account data has been deleted due to multiple failed login attempts");
						VaultController.Send("sentineldatavault", "SENTINELDATA", username, "Security Warning",
								"Dear user,\n\nWe have deleted your account.\n" + "Have a nice day.\n\n"
										+ "Sincerely,\nSentinel Data Vault Team");
					} catch (AddressException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (MessagingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});
		frmSignIn.getContentPane().setLayout(null);

		btnSignUp = new JButton("Create new account");
		btnSignUp.setToolTipText("Click here to create a new Sentinel Data Vault account!");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignupView signup = new SignupView();
				signup.setVisible(true);
			}
		});
		btnSignUp.setBounds(57, 264, 158, 27);
		frmSignIn.getContentPane().add(btnSignUp);
		frmSignIn.getContentPane().add(btnNewButton);
		frmSignIn.getContentPane().add(lblPassword);
		frmSignIn.getContentPane().add(lblEmail);
		frmSignIn.getContentPane().add(textField);
		frmSignIn.getContentPane().add(passwordField);

		btnForgotPassword = new JButton("I forgot my password");
		btnForgotPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmSignIn.setVisible(false);
				PasswordRecoveryView p = new PasswordRecoveryView();
			}
		});
		btnForgotPassword.setToolTipText("Click here to reset your account password");
		btnForgotPassword.setBounds(227, 264, 170, 27);
		frmSignIn.getContentPane().add(btnForgotPassword);

		lblSentinelDataVault = new JLabel("Sentinel Data Vault");
		lblSentinelDataVault.setFont(new Font("Dialog", Font.PLAIN, 22));
		lblSentinelDataVault.setBounds(119, 33, 206, 27);
		frmSignIn.getContentPane().add(lblSentinelDataVault);

		txtWarning = new JTextField();
		txtWarning.setForeground(new Color(220, 20, 60));
		txtWarning.setBorder(null);
		txtWarning.setOpaque(false);
		txtWarning.setFocusable(false);
		txtWarning.setEditable(false);
		txtWarning.setFont(new Font("Tahoma", Font.ITALIC, 12));
		txtWarning.setBackground(SystemColor.window);
		txtWarning.setBounds(57, 196, 340, 26);
		frmSignIn.getContentPane().add(txtWarning);
		txtWarning.setColumns(10);

		frmSignIn.getContentPane().setFocusTraversalPolicy(
				new FocusTraversalOnArray(new Component[] { textField, passwordField, btnNewButton }));
		frmSignIn.setFocusTraversalPolicy(
				new FocusTraversalOnArray(new Component[] { textField, passwordField, btnNewButton }));
	}
}
