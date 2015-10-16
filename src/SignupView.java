import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;


public class SignupView extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField1;
	private JPasswordField passwordField2;
	private JPasswordField passwordField;

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
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		JButton btnNewButton = new JButton("Creat account!");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				String password1 = passwordField1.getText();
				String password2 = passwordField2.getText();
				if (password1.equals("") || password2.equals("")||username.equals("")){
					JOptionPane.showMessageDialog(null,"You need to enter all the fields!");
					password1 = "";
					password2 = "";
				}
				if(!(password1.equals(password2))){
					JOptionPane.showMessageDialog(null,"Your password doesn't match");
					password1 = "";
					password2 = "";
				}
				
				if (password1.equals(password2) && !password1.equals("") && !username.equals("")){
					JOptionPane.showMessageDialog(null,"You have successfully created your account!");
					dispose();
				}
			}
		});
		btnNewButton.setBounds(159, 230, 145, 27);
		contentPane.add(btnNewButton);
		
		JLabel lblSecurityQuestion = new JLabel("Security Question:");
		lblSecurityQuestion.setBounds(33, 143, 129, 18);
		contentPane.add(lblSecurityQuestion);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"What is your mother's name?", "What is your first pet's name?", "Where is your favorite city?"}));
		comboBox.setBounds(169, 140, 253, 27);
		contentPane.add(comboBox);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(169, 172, 160, 24);
		contentPane.add(passwordField);
		
		JLabel lblAnswer = new JLabel("Answer:");
		lblAnswer.setBounds(99, 173, 56, 18);
		contentPane.add(lblAnswer);
	}
}
