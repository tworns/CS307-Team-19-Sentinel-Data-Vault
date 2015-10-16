import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class LoginView {

	public JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton btnSignUp;
	private JButton btnForgotPassword;

	/**
	 * Launch the application.
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
		textField.setBounds(139, 92, 176, 24);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(139, 129, 176, 24);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBounds(336, 92, 72, 61);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textField.getText();
				String password = String.valueOf(passwordField.getPassword()); // getText() is deprecated; changed to getPassword()
				//Condition needs to be changed accordingly
				if(username.equals("cs307@purdue.edu") && password.equals("12345")) {
					
					MainView window =new MainView();
					window.frmSentinelDataVault.setVisible(true);
					frame.dispose();
					
					/*
					HomeView regFace =new HomeView();
					regFace.frmSentinelDataVault.setVisible(true);
					frame.dispose();
					*/
					
				}
				else{
					JOptionPane.showMessageDialog(null,"Wrong Password / Username");
					textField.setText("");
					passwordField.setText("");
					textField.requestFocus();
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
		btnSignUp.setBounds(118, 174, 143, 27);
		frame.getContentPane().add(btnSignUp);
		frame.getContentPane().add(btnNewButton);
		frame.getContentPane().add(lblPassword);
		frame.getContentPane().add(lblEmail);
		frame.getContentPane().add(textField);
		frame.getContentPane().add(passwordField);
		
		btnForgotPassword = new JButton("Forgot Password?");
		btnForgotPassword.setBounds(258, 173, 151, 29);
		frame.getContentPane().add(btnForgotPassword);
	}
}
