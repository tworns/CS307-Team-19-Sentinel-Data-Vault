package loginView;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import java.awt.Color;

public class Login {

	private JFrame frame;
	private JPasswordField passwordField;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 454, 312);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBounds(342, 108, 80, 30);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(46, 90, 72, 18);
		frame.getContentPane().add(lblEmail);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(161, 133, 150, 30);
		frame.getContentPane().add(passwordField);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(46, 139, 72, 18);
		frame.getContentPane().add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(161, 84, 150, 30);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	}
}
