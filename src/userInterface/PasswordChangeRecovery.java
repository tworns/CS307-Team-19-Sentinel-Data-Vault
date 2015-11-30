package userInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import cryptography.PasswordHasher;
import dataManagement.User;
import security.PasswordValidation;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JPasswordField;

public class PasswordChangeRecovery {

	private JFrame frmPasswordRecovery;
	private JPasswordField textField;
	private JPasswordField textField_1;
	private User currentUser;
	private JTextField textField_2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User user = new User(null, null, null, null, null, null, null);
					PasswordChangeRecovery window = new PasswordChangeRecovery(user);
					window.frmPasswordRecovery.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PasswordChangeRecovery(User user) {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPasswordRecovery = new JFrame();
		frmPasswordRecovery.setTitle("Password Recovery");
		frmPasswordRecovery.setBounds(100, 100, 450, 300);
		frmPasswordRecovery.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPasswordRecovery.getContentPane().setLayout(null);
		frmPasswordRecovery.setLocationRelativeTo(null);
		frmPasswordRecovery.setVisible(true);
		JLabel lblNewPassword = new JLabel("New Password");
		lblNewPassword.setBounds(254, 54, 116, 16);
		frmPasswordRecovery.getContentPane().add(lblNewPassword);
		
		textField = new JPasswordField();
		textField.setBounds(75, 51, 155, 22);
		frmPasswordRecovery.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JPasswordField();
		textField_1.setBounds(75, 89, 155, 22);
		frmPasswordRecovery.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblConfirmNewPassword = new JLabel("Confirm Password");
		lblConfirmNewPassword.setBounds(254, 92, 148, 16);
		frmPasswordRecovery.getContentPane().add(lblConfirmNewPassword);
		
		JLabel lblRememberThatPassword = new JLabel("Remember that password must be longer than 8 characters ");
		lblRememberThatPassword.setBounds(38, 186, 388, 22);
		frmPasswordRecovery.getContentPane().add(lblRememberThatPassword);
		
		JLabel lblPasswordReset = new JLabel("Password Reset");
		lblPasswordReset.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPasswordReset.setBounds(146, 13, 140, 16);
		frmPasswordRecovery.getContentPane().add(lblPasswordReset);
		
		JButton btnResetPassword = new JButton("Reset Password");
		btnResetPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String pass1 = new String(textField.getPassword());
				String pass2 = new String(textField_1.getPassword());
				PasswordValidation v = new PasswordValidation();
				if(pass1.trim().equals(pass2.trim()) && v.minStandard(pass1) ) { 
					PasswordHasher p = new PasswordHasher();
					String passwordHash = p.hashPassword(pass1, currentUser.getPasswordSalt());
					currentUser.setPasswordHash(passwordHash);
					frmPasswordRecovery.dispose();
				}
				
				else if(pass1.trim().equals(pass2.trim()) != true) { 
					textField_2.setText("Passwords must match. Please try again.");
				}
				else if(v.minStandard(pass1) != true){ 
					textField_2.setText("Password must contain at least 8 characters, an uppercase and lowercase letter, a number, and a symbol.");
				}
				else { 
					textField_2.setText("Unidentified input");
				}
			}
		});
		btnResetPassword.setBounds(52, 240, 135, 25);
		frmPasswordRecovery.getContentPane().add(btnResetPassword);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmPasswordRecovery.dispose();
			}
		});
		btnCancel.setBounds(239, 240, 135, 25);
		frmPasswordRecovery.getContentPane().add(btnCancel);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.ITALIC, 12));
		textField_2.setOpaque(false);
		textField_2.setFocusTraversalKeysEnabled(false);
		textField_2.setFocusable(false);
		textField_2.setBorder(null);
		textField_2.setForeground(new Color(220, 20, 60));
		textField_2.setEditable(false);
		textField_2.setBounds(78, 116, 324, 22);
		frmPasswordRecovery.getContentPane().add(textField_2);
		textField_2.setColumns(10);
	}
}
