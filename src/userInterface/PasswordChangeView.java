package userInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextPane;
import dataManagement.User;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class PasswordChangeView {

	private JFrame frmChangePassword;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	
	
	private String oldPass;
	private String newPass1;
	private String newPass2;
	private String answer;
	/**
	 * Launch the application.
	 */
	public User currentUser;
	public static void main(String[] args) { //Main for testing
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Make a null user for testing
					User u = new User("Ben", "Password Hash", "Password Salt", "This is my data key", "This is my sec question", "This is my answer", null);
					PasswordChangeView window = new PasswordChangeView(u);
					window.frmChangePassword.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PasswordChangeView(User user) {
		this.currentUser = user; //get the user from SettingsView
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		frmChangePassword = new JFrame();
		frmChangePassword.setTitle("Change Password");
		frmChangePassword.setBounds(100, 100, 450, 300);
		frmChangePassword.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmChangePassword.getContentPane().setLayout(null);
		
		textField = new JTextField(); //Text field for old password entry
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO HASH HERE
				oldPass = textField.getText(); //will eventually hash the old password for direct comparison later
			}
		});
		textField.setBounds(35, 37, 116, 22);
		frmChangePassword.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.addActionListener(new ActionListener() { //1st new password entry
			public void actionPerformed(ActionEvent e) {
				//TODO Hash here
				newPass1 = textField_1.getText();				
			}
		});
		textField_1.setBounds(35, 77, 116, 22);
		frmChangePassword.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();//2nd new password entry
		textField_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newPass2 = textField_2.getText();
				//TODO HASH HERE
				
			}
		});
		textField_2.setBounds(35, 111, 116, 22);
		frmChangePassword.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblOldPassword = new JLabel("Old Password");
		lblOldPassword.setBounds(163, 40, 86, 16);
		frmChangePassword.getContentPane().add(lblOldPassword);
		
		JLabel lblNewPassword = new JLabel("New Password");
		lblNewPassword.setBounds(163, 80, 88, 16);
		frmChangePassword.getContentPane().add(lblNewPassword);
		
		JLabel lblConfirmNewPassword = new JLabel("Confirm new Password");
		lblConfirmNewPassword.setBounds(163, 114, 144, 16);
		frmChangePassword.getContentPane().add(lblConfirmNewPassword);
		
		JLabel lblAnswerToSecurity = new JLabel("Answer to Security Question");
		lblAnswerToSecurity.setBounds(163, 175, 189, 16);
		frmChangePassword.getContentPane().add(lblAnswerToSecurity);
		
		textField_3 = new JTextField(); //Answer to security question
		textField_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				answer = textField_3.getText();
			}
		});
		textField_3.setBounds(35, 172, 116, 22);
		frmChangePassword.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton = new JButton("Change");//Confirm (change) button
		btnNewButton.setBounds(54, 220, 97, 25);
		frmChangePassword.getContentPane().add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel"); //Cancel button
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmChangePassword.dispose();
			}
		});
		btnCancel.setBounds(189, 220, 97, 25);
		frmChangePassword.getContentPane().add(btnCancel);
		
		JTextPane txtpnThisIsWhere = new JTextPane(); //Field that displays currentUser's security question.
		txtpnThisIsWhere.setText(currentUser.getSecurityQuestion());
		txtpnThisIsWhere.setBounds(35, 140, 309, 22);
		frmChangePassword.getContentPane().add(txtpnThisIsWhere);
	}
}
