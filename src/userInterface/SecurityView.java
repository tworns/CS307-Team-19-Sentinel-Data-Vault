package userInterface;

import java.awt.EventQueue;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import security.PasswordGen;


public class SecurityView {

	public JFrame frame;
	private int instanceCount;
	private SecurityView instance; 
	private JPasswordField passwordField;
	public Object frmSentinelDataVault;
	private int spinLength;
	private boolean numbers;
	private boolean uppercase;
	private boolean lowercase;
	private boolean repeated;
	
	
	/*
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SecurityView window = new SecurityView();
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
	public SecurityView() {
		initialize();
	}

	
	public SecurityView getInstance() { 
		if(instanceCount != 0) { 
			return instance;
		}
		else { 
			SecurityView newInstance = new SecurityView();
			instanceCount = 1;
			return newInstance;
		}	
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//displays window
		frame = new JFrame();
		frame.setBounds(100, 100, 372, 384);
		frame.setTitle("Security View");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//display code for the tabs
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 354, 337);
		frame.getContentPane().add(tabbedPane);
		
		//label for password generator section
		JPanel panel = new JPanel();
		tabbedPane.addTab("Password Generator", null, panel, null);
		panel.setLayout(null);
		
		//toggle for special characters
		JCheckBox chckbxNoSpecialCharacters = new JCheckBox("At Least 1 Special Character");
		chckbxNoSpecialCharacters.setBounds(47, 126, 215, 25);

		chckbxNoSpecialCharacters.setToolTipText("Toggling this option will generate a password with at least 1 special character (!,@,#,$, etc.).");
		panel.add(chckbxNoSpecialCharacters);
		
		//toggle for uppercase letters
		JCheckBox chckbxNoUppercase = new JCheckBox("At Least 1 Uppercase Letter");
		chckbxNoUppercase.setBounds(47, 36, 208, 25);

		chckbxNoUppercase.setToolTipText("Toggling this option will generate a password with at least 1 uppercase letter.");

		panel.add(chckbxNoUppercase);
		
		//toggle for numbers
		JCheckBox chckbxNoNumbers = new JCheckBox("At Least 1 Number");
		chckbxNoNumbers.setBounds(47, 96, 227, 25);

		chckbxNoNumbers.setToolTipText("Toggling this option will generate a password with at least 1 number.");

		panel.add(chckbxNoNumbers);
		
		//length of password to be input
		JLabel lblLengthOfPassword = new JLabel("Password Length");
		lblLengthOfPassword.setBounds(104, 200, 118, 16);
		lblLengthOfPassword.setToolTipText("This field specifies the length of password to be generated");
		panel.add(lblLengthOfPassword);
		
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(47, 198, 45, 19);
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				spinLength = (Integer)spinner.getValue();
			}
		});
		spinner.setToolTipText("This field specifies the length of the password to be generated.");
		spinner.setModel(new SpinnerNumberModel(4, 4, 32, 1));
		panel.add(spinner);
		spinner.setAutoscrolls(true);
		
		//Displays the password that has been generated, values are currently hard coded because Password generator hasn't been finished.
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.setBounds(47, 242, 95, 25);
		panel.add(btnGenerate);
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent clickGenerate) {
				if(clickGenerate.getActionCommand().equalsIgnoreCase("Generate")) { 
					PasswordGen passGen = new PasswordGen();
					String password = passGen.generatePassword(true, true, true, true, true, 16);
					JTextArea displayPass = new JTextArea(1,1);
					displayPass.setText(password);
					displayPass.setEditable(false);
					JOptionPane.showMessageDialog(null, new JScrollPane(displayPass), "Password", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		//Action listener for the cancel button
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(154, 242, 85, 25);
		panel.add(btnCancel);
		
		JCheckBox chckbxAvoidRepeatedCharacters = new JCheckBox("Avoid Repeated Characters");
		chckbxAvoidRepeatedCharacters.setToolTipText("Toggling this option will generate a password that avoids repeating characters (aa, bb, 11, @@, etc.) as much as possible.");
		chckbxAvoidRepeatedCharacters.setBounds(47, 159, 183, 25);
		panel.add(chckbxAvoidRepeatedCharacters);
		
		JCheckBox chckbxAtLeast = new JCheckBox("At least 1 Lowercase Charcter");
		chckbxAtLeast.setToolTipText("Toggling this option will generate a password with at least 1 lowercase letter.");
		chckbxAtLeast.setBounds(47, 66, 215, 25);
		panel.add(chckbxAtLeast);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent clickCancel) {
				if(clickCancel.getActionCommand().equalsIgnoreCase("Cancel")) {
					frame.dispose();
				}
			}
		});
		
		//Strength checker
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Password Strength Checker", null, panel_1, null);
		panel_1.setLayout(null);
		
		//password entry field
		JLabel lblEnterPassword = new JLabel("Enter Password");
		lblEnterPassword.setBounds(22, 13, 106, 16);
		panel_1.add(lblEnterPassword);
		
		//button that actually sets off the checking of the password.
		JButton btnCheck = new JButton("Check");
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equalsIgnoreCase("Check")){
					String strength = "Your password is";
					String output = strength;
					char[] input = passwordField.getPassword();
					StringBuilder s = new StringBuilder();
					s.append(input);
					String password = s.toString();
					//Checks for one of each character type and length > 10
					if(password.length() >10 && password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*([!@#$%&*,.])).+$") == true){
						 output += " strong. \n";
					}
					//checks if password is less than length 9, only contains capital, lowercase, numbers, or special characters, and checks for repeated characters.
					else if(password.length() < 9 || ((password.matches("([a-z]+)") == true || password.matches("([A-Z]+)") == true || password.matches("([0-9]+)") == true || password.matches("([^a-zA-Z0-9]+)")) ==true || password.matches("([^a-zA-Z0-9 ])\\1+")) == true || password.matches("([A-Za-z0-9])\\1+") == true) { 
						 output += " weak. \n";
					}
					else { 
						//Anything else is adequate.
						 output += " adequate. \n";
					}
					JOptionPane.showMessageDialog(null, output, "Password", JOptionPane.INFORMATION_MESSAGE);
					
				}
			}
		});
		btnCheck.setBounds(21, 104, 97, 25);
		panel_1.add(btnCheck);
		
		//cancel button closes window
		JButton btnCancel_1 = new JButton("Cancel");
		btnCancel_1.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent clickCancel) {
				 if(clickCancel.getActionCommand().equalsIgnoreCase("Cancel")) {
						frame.dispose();
					}
			}
		});
		btnCancel_1.setBounds(142, 104, 97, 25);
		panel_1.add(btnCancel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(32, 41, 117, 19);
		panel_1.add(passwordField);

	}
}