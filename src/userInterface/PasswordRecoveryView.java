package userInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;

public class PasswordRecoveryView {

	private JFrame frmSentinelDataVault;
	private JTextField textField;

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
		frmSentinelDataVault = new JFrame();
		frmSentinelDataVault.setTitle("Sentinel Data Vault Account Recovery");
		frmSentinelDataVault.setBounds(100, 100, 450, 300);
		frmSentinelDataVault.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmSentinelDataVault.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(55, 66, 116, 22);
		frmSentinelDataVault.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(209, 66, 116, 22);
		frmSentinelDataVault.getContentPane().add(lblUsername);
		
		JLabel lblSentinelDataVault = new JLabel("Sentinel Data Vault Account Recovery");
		lblSentinelDataVault.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSentinelDataVault.setBounds(58, 24, 315, 16);
		frmSentinelDataVault.getContentPane().add(lblSentinelDataVault);
	}
}
