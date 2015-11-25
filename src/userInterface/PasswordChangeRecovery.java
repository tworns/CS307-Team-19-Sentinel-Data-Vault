package userInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;

public class PasswordChangeRecovery {

	private JFrame frmPasswordRecovery;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PasswordChangeRecovery window = new PasswordChangeRecovery();
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
	public PasswordChangeRecovery() {
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
		
		JLabel lblNewPassword = new JLabel("New Password");
		lblNewPassword.setBounds(254, 60, 116, 16);
		frmPasswordRecovery.getContentPane().add(lblNewPassword);
		
		textField = new JTextField();
		textField.setBounds(75, 57, 155, 22);
		frmPasswordRecovery.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(75, 109, 155, 22);
		frmPasswordRecovery.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblConfirmNewPassword = new JLabel("Confirm New Password");
		lblConfirmNewPassword.setBounds(254, 112, 148, 16);
		frmPasswordRecovery.getContentPane().add(lblConfirmNewPassword);
		
		JLabel lblRememberThatPassword = new JLabel("Remember that password must be longer than 8 characters ");
		lblRememberThatPassword.setBounds(38, 148, 366, 16);
		frmPasswordRecovery.getContentPane().add(lblRememberThatPassword);
		
		JLabel lblPasswordReset = new JLabel("Password Reset");
		lblPasswordReset.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPasswordReset.setBounds(146, 13, 140, 16);
		frmPasswordRecovery.getContentPane().add(lblPasswordReset);
		
		JButton btnResetPassword = new JButton("Reset Password");
		btnResetPassword.setBounds(52, 188, 135, 25);
		frmPasswordRecovery.getContentPane().add(btnResetPassword);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(239, 188, 135, 25);
		frmPasswordRecovery.getContentPane().add(btnCancel);
	}
}
