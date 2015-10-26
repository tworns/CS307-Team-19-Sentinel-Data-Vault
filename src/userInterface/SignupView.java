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

public class SignupView extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField1;
	private JPasswordField passwordField2;
	private JTextField answerField;
	private String question;

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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("E-mail:");
		lblNewLabel.setBounds(99, 53, 56, 18);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setBounds(83, 83, 72, 18);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblReenter = new JLabel("Re-enter:");
		lblReenter.setBounds(83, 113, 72, 18);
		contentPane.add(lblReenter);
		
		usernameField = new JTextField();
		usernameField.setBounds(169, 50, 160, 24);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		passwordField1 = new JPasswordField();
		passwordField1.setBounds(169, 80, 160, 24);
		contentPane.add(passwordField1);
		
		passwordField2 = new JPasswordField();
		passwordField2.setBounds(169, 110, 160, 24);
		contentPane.add(passwordField2);
		
		JButton btnNewButton = new JButton("Create account!");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				String password1 = String.valueOf(passwordField1.getPassword());
				String password2 = String.valueOf(passwordField2.getPassword());
				String answer = answerField.getText();
				VaultController v = new VaultController();
				//checker will return 4 if succeeded
				
				if(question == (null)){
					JOptionPane.showMessageDialog(null,"Please choose one security question!");
				}
				else{
					int result;
					try {
						result = v.createAccountCheck(password1, password2, username,question, answer);
						if (result == 4){
							dispose();
						}
					} catch (NoSuchAlgorithmException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				/*//check user enter all fields
				if (password1.equals("") || password2.equals("")||username.equals("")||answer.equals("")){
					JOptionPane.showMessageDialog(null,"You need to enter all the fields!");
					password1 = "";
					password2 = "";
				}
				//check password consistency
				if(!(password1.equals(password2))){
					JOptionPane.showMessageDialog(null,"Your password doesn't match");
					password1 = "";
					password2 = "";
				}
				
				//minimum password length check
				if(((password1.length() < 8) && (password1.length()>0)) || 
						((password2.length() < 8) && (password2.length()>0))){
					JOptionPane.showMessageDialog(null,"Your password needs to contain at least 8 characters!");
					password1 = "";
					password2 = "";
				}
				
				//send user info to database
				if (password1.equals(password2) && !password1.equals("") && !username.equals("")
						&&!answer.equals("")){
					JOptionPane.showMessageDialog(null,"You have successfully created your account!");
					dispose();
				}*/
			}
		});
		btnNewButton.setBounds(169, 208, 129, 27);
		contentPane.add(btnNewButton);
		
		JLabel lblSecurityQuestion = new JLabel("Security Question:");
		lblSecurityQuestion.setBounds(33, 143, 129, 18);
		contentPane.add(lblSecurityQuestion);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				question  = (String) comboBox.getSelectedItem();
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Please choose a security question below", "What is your mother's name?", "What is your first pet's name?", "Where is your favorite city?"}));
		comboBox.setBounds(169, 140, 253, 27);
		contentPane.add(comboBox);
		
		JLabel lblAnswer = new JLabel("Answer:");
		lblAnswer.setBounds(99, 173, 56, 18);
		contentPane.add(lblAnswer);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			
			}
		});
		btnCancel.setBounds(305, 207, 117, 29);
		contentPane.add(btnCancel);
		
		answerField = new JTextField();
		answerField.setBounds(169, 170, 160, 24);
		contentPane.add(answerField);
		answerField.setColumns(10);
	}
}
