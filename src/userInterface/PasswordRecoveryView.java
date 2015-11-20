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
import javax.swing.JPanel;

import java.awt.Font;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.awt.event.ActionEvent;

public class PasswordRecoveryView {

	public JFrame frmSentinelDataVault;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField txtQuestion;
	private JTextField textField_2;
	private String securityCode;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PasswordRecoveryView window = new PasswordRecoveryView();
					window.frmSentinelDataVault.setVisible(true);
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
		frmSentinelDataVault.setTitle("Sentinel Data Vault Account Recovery");
		frmSentinelDataVault.setBounds(100, 100, 447, 347);
		frmSentinelDataVault.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmSentinelDataVault.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(55, 66, 116, 22);
		frmSentinelDataVault.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setLabelFor(textField);
		lblUsername.setBounds(209, 66, 116, 22);
		frmSentinelDataVault.getContentPane().add(lblUsername);
		
		JLabel lblSentinelDataVault = new JLabel("Sentinel Data Vault Account Recovery");
		lblSentinelDataVault.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSentinelDataVault.setBounds(58, 24, 315, 16);
		frmSentinelDataVault.getContentPane().add(lblSentinelDataVault);
		
		textField_1 = new JTextField();
		textField_1.setBounds(55, 137, 116, 22);
		frmSentinelDataVault.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblSecurityQuestionAnswer = new JLabel("Security Question Answer");
		lblSecurityQuestionAnswer.setLabelFor(textField_1);
		lblSecurityQuestionAnswer.setBounds(209, 140, 154, 16);
		frmSentinelDataVault.getContentPane().add(lblSecurityQuestionAnswer);
		

		User u = overseer.retrieveUserFromDatabase(textField.getText()); //TODO Make the username field appear by itself. Then pop up other fields as needed
		if(u != null){
			txtQuestion.setText(u.getSecurityQuestion());
		}
		JButton btnRecover = new JButton("Recover");
		btnRecover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(u.getSecurityAnswer().equals(textField_1)){
					try {
						SaltGenerator pepper = new SaltGenerator();
						 securityCode = pepper.generateSalt();
						VaultController.Send("sentineldatavault", "SENTINELDATA", u.getUsername(), 
								"Security Warning", "Dear user,\n\nYour account password has been changed.\n"
										+ "If you did not initiate this change, this e-mail can be safely disregarded.\n\n"+
										"Your security code is: " + securityCode +"\n\n" +
										"Sincerely,\nSentinel Data Vault Team");
					} catch (AddressException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (MessagingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NoSuchAlgorithmException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				String inputCode = JOptionPane.showInputDialog("Please input the security code that was emailed to you, if the email is \nnot in your inbox, please check your spam filter");
				if(securityCode.equals(inputCode)){ 
					//PasswordRecoveryChangeView k = new PasswordRecoveryChangeView(); //TODO implement PasswordRecoveryChangeView
				}
			}
		});
		btnRecover.setBounds(55, 216, 97, 25);
		frmSentinelDataVault.getContentPane().add(btnRecover);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmSentinelDataVault.dispose();
			}
		});
		btnCancel.setBounds(207, 216, 97, 25);
		frmSentinelDataVault.getContentPane().add(btnCancel);
		
		textField_2 = new JTextField();
		textField_2.setBounds(55, 172, 116, 22);
		frmSentinelDataVault.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblSecurityCode = new JLabel("Security Code");
		lblSecurityCode.setBounds(217, 187, 97, 16);
		frmSentinelDataVault.getContentPane().add(lblSecurityCode);
		

		
	}
}
