package userInterface;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.awt.event.ActionEvent;
import controllers.DatabaseManager;
import controllers.VaultController;
import cryptography.SaltGenerator;
import javax.mail.*;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;

public class SignupView extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField1;
	private JPasswordField passwordField2;
	private JTextField answerField;
	private String question = "";
	private JTextPane txtpnWarning;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignupView frame = new SignupView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SignupView() {
		super("Create your account");
		setTitle("Create Your Account");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 451, 434);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Email:");
		lblNewLabel.setBounds(32, 131, 123, 18);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setBounds(32, 161, 123, 18);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblReenter = new JLabel("Confirm Password:");
		lblReenter.setBounds(32, 190, 127, 18);
		contentPane.add(lblReenter);
		
		usernameField = new JTextField();
		usernameField.setBounds(169, 128, 240, 24);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		passwordField1 = new JPasswordField();
		passwordField1.setBounds(169, 158, 240, 24);
		contentPane.add(passwordField1);
		
		passwordField2 = new JPasswordField();
		passwordField2.setBounds(169, 188, 240, 24);
		contentPane.add(passwordField2);
		
		JButton btnNewButton = new JButton("Create my account!");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				String password1 = String.valueOf(passwordField1.getPassword());
				String password2 = String.valueOf(passwordField2.getPassword());
				String answer = answerField.getText();
				VaultController v = new VaultController();
				//checker will return 4 if succeeded, then dispose signup frame

				int result;
				try {
					result = v.createAccountCheck(password1, password2, username,question, answer);
					if (result == 1){
							dispose();
					}
					else if (result == 0) {
						txtpnWarning.setText("Please complete all entry fields!");
					}
					else if (result == -1) {
						txtpnWarning.setText("Please enter a valid email address!");
					}
					else if (result == -2) {
						txtpnWarning.setText("Please ensure the passwords match!");
					}
					else if (result == -3) {
						txtpnWarning.setText("Password must contain at least 8 characters, an uppercase and lowercase letter, a number, and a symbol.");
					}
					else if (result == -4) {
						txtpnWarning.setText("Please select a security question!");
					}
					else if (result == -5) {
						txtpnWarning.setText("This account already exists! Please try another email.");
					}
				} catch (NoSuchAlgorithmException e1) {
					
						e1.printStackTrace();
				}
				
				
			}
		});
		btnNewButton.setBounds(25, 355, 153, 27);
		contentPane.add(btnNewButton);
		
		JLabel lblSecurityQuestion = new JLabel("Security Question:");
		lblSecurityQuestion.setBounds(32, 221, 129, 18);
		contentPane.add(lblSecurityQuestion);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				question  = (String) comboBox.getSelectedItem();
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Choose a security question...", "What is the maiden name of your mother?", "What is name of your pet", "Where is your favorite city?"}));
		comboBox.setBounds(169, 218, 240, 27);
		contentPane.add(comboBox);
		
		JLabel lblAnswer = new JLabel("Answer:");
		lblAnswer.setBounds(32, 251, 121, 18);
		contentPane.add(lblAnswer);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			
			}
		});
		btnCancel.setBounds(319, 355, 90, 29);
		contentPane.add(btnCancel);
		
		answerField = new JTextField();
		answerField.setBounds(169, 248, 240, 24);
		contentPane.add(answerField);
		answerField.setColumns(10);
		
		JTextPane txtpnWelcome = new JTextPane();
		txtpnWelcome.setEditable(false);
		txtpnWelcome.setOpaque(false);
		txtpnWelcome.setBackground(SystemColor.window);
		txtpnWelcome.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		txtpnWelcome.setText("Welcome to Sentinel Data Vault!");
		txtpnWelcome.setBounds(109, 6, 232, 18);
		contentPane.add(txtpnWelcome);
		
		JTextPane txtpnInstructions = new JTextPane();
		txtpnInstructions.setEditable(false);
		txtpnInstructions.setOpaque(false);
		txtpnInstructions.setBackground(SystemColor.window);
		txtpnInstructions.setText("Enter the information for your new account below.\n\nPasswords must contain 8 or more characters with at least one upper and lower case, number, and symbol.");
		txtpnInstructions.setBounds(29, 36, 377, 64);
		contentPane.add(txtpnInstructions);
		
		txtpnWarning = new JTextPane();
		txtpnWarning.setForeground(new Color(220, 20, 60));
		txtpnWarning.setEditable(false);
		txtpnWarning.setBorder(null);
		txtpnWarning.setOpaque(false);
		txtpnWarning.setFocusable(false);
		txtpnWarning.setFont(new Font("Tahoma", Font.ITALIC, 12));
		txtpnWarning.setBackground(SystemColor.window);
		txtpnWarning.setBounds(173, 281, 236, 62);
		contentPane.add(txtpnWarning);
	}
}
