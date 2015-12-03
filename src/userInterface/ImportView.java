package userInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ImportView {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private String[] credentials = new String [2];
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String name = null;
					String pass = null;
					String [] stuff = new String [2];
					ImportView window = new ImportView( name,  pass);
					window.initialize(name, pass);
					window.frame.setVisible(true);
					System.out.println(window.getUser()+ " " + window.getPass());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the application.
	 */
	public ImportView(String username, String password) {
		initialize(username, password);
		
	}
	public String getUser() { 
		return credentials[0];
	}
	public String getPass() { 
		return credentials[1];
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String username, String password) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(288, 103, 56, 16);
		frame.getContentPane().add(lblPassword);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(288, 74, 78, 16);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblEnterCredentialsFor = new JLabel("Enter credentials for account to be imported from");
		lblEnterCredentialsFor.setBounds(45, 13, 359, 16);
		frame.getContentPane().add(lblEnterCredentialsFor);
		
		textField = new JTextField();
		textField.setBounds(120, 71, 116, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(120, 100, 115, 22);
		frame.getContentPane().add(passwordField);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				credentials[0] = textField.getText();
				credentials[1] = new String(passwordField.getPassword());
			}
		});
		btnConfirm.setBounds(45, 185, 97, 25);
		frame.getContentPane().add(btnConfirm);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnCancel.setBounds(231, 185, 97, 25);
		frame.getContentPane().add(btnCancel);
	
	}
}
