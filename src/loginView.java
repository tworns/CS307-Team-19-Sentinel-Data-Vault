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

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton btnSignUp;

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
		frame.setBounds(100, 100, 452, 294);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(74, 95, 56, 18);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(63, 132, 72, 18);
		
		textField = new JTextField();
		textField.setBounds(157, 92, 219, 24);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(157, 129, 219, 24);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBounds(237, 171, 89, 27);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textField.getText();
				String password = passwordField.getText();
				//Condition needs to be changed accordingly
				if(username.equals("cs307@purdue.edu") && password.equals("12345")) {
					HomeView regFace =new HomeView();
					regFace.frmSentinelDataVault.setVisible(true);
					frame.dispose();
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
		btnSignUp.setBounds(130, 171, 89, 27);
		frame.getContentPane().add(btnSignUp);
		frame.getContentPane().add(btnNewButton);
		frame.getContentPane().add(lblPassword);
		frame.getContentPane().add(lblEmail);
		frame.getContentPane().add(textField);
		frame.getContentPane().add(passwordField);
	}
}
