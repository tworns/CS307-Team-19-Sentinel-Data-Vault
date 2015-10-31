package userInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextPane;
import dataManagement.User;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.awt.event.ActionEvent;
import cryptography.PasswordHasher;
import cryptography.SaltGenerator;
import security.PasswordValidation;
import javax.swing.JPasswordField;
import controllers.VaultController;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
public class PasswordChangeView {

	public JFrame frmChangePassword;
	private JTextField textField_3;
	
	
	private String oldPass;
	private String newPass1;
	private String newPass2;
	private String answer;
	private boolean passCheck; 
	/**
	 * Launch the application.
	 */
	public User currentUser;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
	private JTextField textField;
	public static void main(String[] args) { //Main for testing
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Make a  user for testing
					PasswordHasher l = new PasswordHasher();
					SaltGenerator twitchChat = new SaltGenerator();
					String salt = twitchChat.generateSalt();
					User u = new User("ben@purdue.edu", l.hashPassword("password", salt), salt, "This is my data key", "This is my sec question", "answer", null);
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
		frmChangePassword.setResizable(false);
		frmChangePassword.setTitle("Change Password");
		frmChangePassword.setBounds(100, 100, 413, 408);
		frmChangePassword.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmChangePassword.getContentPane().setLayout(null);
		
		JLabel lblOldPassword = new JLabel("Old Password");
		lblOldPassword.setBounds(165, 54, 86, 16);
		frmChangePassword.getContentPane().add(lblOldPassword);
		
		JLabel lblNewPassword = new JLabel("New Password");
		lblNewPassword.setBounds(165, 94, 88, 16);
		frmChangePassword.getContentPane().add(lblNewPassword);
		
		JLabel lblConfirmNewPassword = new JLabel("Confirm new Password");
		lblConfirmNewPassword.setBounds(165, 128, 144, 16);
		frmChangePassword.getContentPane().add(lblConfirmNewPassword);
		
		JLabel lblAnswerToSecurity = new JLabel("Answer to Security Question");
		lblAnswerToSecurity.setBounds(165, 189, 189, 16);
		frmChangePassword.getContentPane().add(lblAnswerToSecurity);
		
		textField_3 = new JTextField(); //Answer to security question
		textField_3.setBounds(37, 186, 116, 22);
		frmChangePassword.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton = new JButton("Change");//Confirm (change) button
		
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				oldPass = String.valueOf(passwordField.getPassword());
				newPass1 = String.valueOf(passwordField_1.getPassword());
				newPass2 = String.valueOf(passwordField_2.getPassword());
				answer = textField_3.getText();
				VaultController fallout = new VaultController();
				try {
					fallout.createAccountCheck(oldPass, newPass1, currentUser.getUsername(), currentUser.getSecurityQuestion(), answer);
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//Old password validation
				/*PasswordValidation a = new PasswordValidation(oldPass);
				try{  
					if(a.isValidPassword(currentUser, oldPass) == true) { 
						passCheck = true;
					}
					else { 
						passCheck = false;
					}
				}
				catch ( NoSuchAlgorithmException k){ 
					k.printStackTrace();
				}
				//Making sure the user puts stuff in.
				if(newPass1 == null || newPass2 == null || answer == null){
					JOptionPane.showMessageDialog(null, "One or more fields left empty", "Change Password", 0);
				}
				else if ( newPass1.equals(newPass2) && newPass2.equals(oldPass)) { 
					JOptionPane.showMessageDialog(null, "New password cannot match the old password.", "Change Password", 0);
				}
				else if (newPass1.equals(newPass2) == false) { 
					JOptionPane.showMessageDialog(null, "Check to make sure the new passwords match.", "Change Password", 0);
				}
				//Makes sure the new passwords match each other, the old password and security q answer is correct
				else if(newPass1.equals(newPass2) && passCheck ==true && a.minStandard(newPass2) == true && answer.equals(currentUser.getSecurityAnswer())){
				
					PasswordHasher p = null; // might have issues with the null initializations here.
					try {
						p = new PasswordHasher();
					} catch (NoSuchAlgorithmException e1) {
							e1.printStackTrace();
					}
					String newPass1 = p.hashPassword(newPass2, currentUser.getPasswordSalt() );
					currentUser.setPasswordHash(newPass1);
					frmChangePassword.dispose();
					
					//TODO Get the updated user to the database!
					
				}
				//Yells at user if the above if has a false in it
				else{ 
					JOptionPane.showMessageDialog(null, "Ensure your security question answer is correct.", "Change Password", 0);
				}*/
				}
		});
		btnNewButton.setBounds(63, 320, 97, 25);
		frmChangePassword.getContentPane().add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel"); //Cancel button
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmChangePassword.dispose();
			}
		});
		btnCancel.setBounds(198, 320, 97, 25);
		frmChangePassword.getContentPane().add(btnCancel);
		
		JTextPane txtpnThisIsWhere = new JTextPane(); //Field that displays currentUser's security question.
		txtpnThisIsWhere.setEditable(false);
		txtpnThisIsWhere.setText(currentUser.getSecurityQuestion());
		txtpnThisIsWhere.setBounds(37, 154, 309, 22);
		frmChangePassword.getContentPane().add(txtpnThisIsWhere);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(37, 48, 116, 22);
		frmChangePassword.getContentPane().add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(37, 91, 116, 22);
		frmChangePassword.getContentPane().add(passwordField_1);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(37, 125, 116, 22);
		frmChangePassword.getContentPane().add(passwordField_2);
		
		JLabel lblChangeSecurityQuestion = new JLabel("New Security Question");
		lblChangeSecurityQuestion.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblChangeSecurityQuestion.setBounds(101, 218, 194, 16);
		frmChangePassword.getContentPane().add(lblChangeSecurityQuestion);
		
		textField = new JTextField();
		textField.setBounds(37, 276, 116, 22);
		frmChangePassword.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("New Security Question Answer");
		lblNewLabel.setBounds(165, 279, 189, 16);
		frmChangePassword.getContentPane().add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Please choose a security question below", "What is the maiden name of your mother?", "What is name of your pet", "Where is your favorite city?"}));
		comboBox.setBounds(37, 241, 309, 22);
		frmChangePassword.getContentPane().add(comboBox);
		
		JLabel lblNewPassword_1 = new JLabel("New Password");
		lblNewPassword_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewPassword_1.setBounds(137, 23, 114, 16);
		frmChangePassword.getContentPane().add(lblNewPassword_1);
	}
}
