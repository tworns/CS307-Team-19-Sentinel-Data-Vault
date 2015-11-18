package userInterface;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import security.PasswordGen;
import security.StrengthChecker;
import java.awt.Font;
import java.awt.SystemColor;

public class SecurityView {

	public JFrame frame;
	private int instanceCount;
	private SecurityView instance; 
	private JTextField passwordField; //private JPasswordField passwordField
	public Object frmSentinelDataVault;
	private int spinLength = 0;
	private boolean numbers;
	private boolean uppercase;
	private boolean lowercase;
	private boolean specials;
	private boolean repeated;
	private JTextField textField;
	
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
		frame.setResizable(false);
		frame.setBounds(100, 100, 412, 382);
		frame.setTitle("Security View");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		//display code for the tabs
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 406, 347);
		frame.getContentPane().add(tabbedPane);
		
		//label for password generator section
		JPanel panel = new JPanel();
		tabbedPane.addTab("Password Generator", null, panel, null);
		panel.setLayout(null);
		
		//toggle for special characters
		JCheckBox chckbxNoSpecialCharacters = new JCheckBox("Include Special Characters");
		chckbxNoSpecialCharacters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				specials = chckbxNoSpecialCharacters.isSelected();
			}
		});
		chckbxNoSpecialCharacters.setBounds(87, 147, 215, 25);

		chckbxNoSpecialCharacters.setToolTipText("Password will contain at least one special character (!, @, #, $, etc.).");
		panel.add(chckbxNoSpecialCharacters);
		
		//toggle for uppercase letters
		JCheckBox chckbxNoUppercase = new JCheckBox("Include Uppercase Letters");
		chckbxNoUppercase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uppercase = chckbxNoUppercase.isSelected();
			}
		});
		chckbxNoUppercase.setBounds(87, 57, 208, 25);

		chckbxNoUppercase.setToolTipText("Password will contain at least one uppercase letter.");

		panel.add(chckbxNoUppercase);
		
		//toggle for numbers
		JCheckBox chckbxNoNumbers = new JCheckBox("Include Digits");
		chckbxNoNumbers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numbers = chckbxNoNumbers.isSelected();
			}
		});
		chckbxNoNumbers.setBounds(87, 117, 227, 25);

		chckbxNoNumbers.setToolTipText("Password will contain at least one digit (numbers 0-9).");

		panel.add(chckbxNoNumbers);
		
		//length of password to be input
		JLabel lblLengthOfPassword = new JLabel("Password Length");
		lblLengthOfPassword.setBounds(155, 221, 118, 16);
		lblLengthOfPassword.setToolTipText("Specify the password length in number of characters.");
		panel.add(lblLengthOfPassword);
		
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(87, 219, 45, 19);
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				spinLength = (Integer)spinner.getValue();
			}
		});
		spinner.setToolTipText("Specify the password length in number of characters.");
		spinner.setModel(new SpinnerNumberModel(4, 4, 32, 1));
		panel.add(spinner);
		spinner.setAutoscrolls(true);
		
		//Displays the password that has been generated, values are currently hard coded because Password generator hasn't been finished.
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.setBounds(87, 263, 95, 25);
		panel.add(btnGenerate);
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent clickGenerate) {
				if(spinLength == 0) { 
					spinLength =4;
				}
				if(numbers == false && specials == false && lowercase == false && uppercase == false) { 
					JOptionPane.showMessageDialog(null, "Password must include at least one of the character types.", "Change Password", 0);
				}
				else { 
					PasswordGen passGen = new PasswordGen();
					String password = passGen.generatePassword(uppercase, lowercase, numbers, specials, repeated, spinLength);
					JTextArea displayPass = new JTextArea(1,1); //Make a spot to display the password
					displayPass.setText(password); //Set the password in the display area
					displayPass.setEditable(false); //Make it so the user can't mess with stuff now.
					displayPass.setFont(new Font("Courier New",Font.PLAIN ,14)); //Change font so everything is ledgible
					JScrollPane scroll = new JScrollPane(displayPass); //make a scoll pane so the text area has something to sit in 
					
					Object[] arr = { //Make a label for this scoll pane
							new JLabel("Password"),
							scroll,
					};
					
					JOptionPane displayMessage = new JOptionPane(arr, JOptionPane.INFORMATION_MESSAGE); //Pack everything into a JOptionPane
					JDialog dialog = displayMessage.createDialog(frame, "Password"); //Make the dialog that displays the JOptionPane
					dialog.setResizable(false);// Properties of the dialog
					dialog.setSize(450, 150);
					dialog.setVisible(true); //User can see it now
					dialog.dispose(); //dispose on close
				}
			}
		});
		
		//Action listener for the cancel button
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(217, 263, 85, 25);
		panel.add(btnCancel);
		
		JCheckBox chckbxAvoidRepeatedCharacters = new JCheckBox("Avoid Sequential Letters and Digits");
		chckbxAvoidRepeatedCharacters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				repeated = chckbxAvoidRepeatedCharacters.isSelected();
			}
		});
		chckbxAvoidRepeatedCharacters.setToolTipText("Password will not contain sequential instances of letters or digits.");
		chckbxAvoidRepeatedCharacters.setBounds(87, 180, 276, 25);
		panel.add(chckbxAvoidRepeatedCharacters);
		
		JCheckBox chckbxAtLeast = new JCheckBox("Include Lowercase Letters");
		chckbxAtLeast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lowercase = chckbxAtLeast.isSelected();
			}
		});
		chckbxAtLeast.setToolTipText("Password will contain at least one lowercase letter.");
		chckbxAtLeast.setBounds(87, 87, 215, 25);
		panel.add(chckbxAtLeast);
		
		JLabel lblPasswordGenerationOptions = new JLabel("Password Generation Options");
		lblPasswordGenerationOptions.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPasswordGenerationOptions.setBounds(87, 24, 227, 16);
		panel.add(lblPasswordGenerationOptions);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent clickCancel) {
					frame.dispose();
			}
		});
		
		//Strength checker
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Password Strength Checker", null, panel_1, null);
		panel_1.setLayout(null);
		
		//password entry field
		JLabel lblEnterPassword = new JLabel("Enter Password:");
		lblEnterPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEnterPassword.setBounds(28, 16, 164, 17);
		panel_1.add(lblEnterPassword);
		
		//button that actually sets off the checking of the password.
		JButton btnCheck = new JButton("Check");
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StrengthChecker check = new StrengthChecker();
				String input = new String(passwordField.getText()); //new String(passwordField.getPassword())
				String output = check.checkStrength(input);
				textField.setText(output);
				Color bg = Color.ORANGE;
				if(output.equals("Strong")) { //color password strength text
					bg = Color.GREEN;
				}
				if(output.equals("Weak")){ 
					bg = Color.RED;
				}
				textField.setForeground(bg);
				
			}
		});
		btnCheck.setBounds(270, 40, 97, 27);
		panel_1.add(btnCheck);
		
		//cancel button closes window
		JButton btnCancel_1 = new JButton("Close");
		btnCancel_1.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent clickCancel) {
				frame.dispose();	
			}
		});
		btnCancel_1.setBounds(268, 267, 97, 25);
		panel_1.add(btnCancel_1);
		
		passwordField = new JTextField();//new JPasswordField
		passwordField.setBounds(25, 43, 235, 19);
		panel_1.add(passwordField);
		
		JLabel lblPasswordCreationTips = new JLabel("How to improve password strength:");
		lblPasswordCreationTips.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPasswordCreationTips.setBounds(31, 85, 336, 27);
		panel_1.add(lblPasswordCreationTips);
		
		textField = new JTextField();
		textField.setFocusable(false);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setBorder(null);
		textField.setSelectedTextColor(SystemColor.control);
		textField.setSelectionColor(SystemColor.control);
		textField.setOpaque(false);
		textField.setEditable(false);
		textField.setBounds(153, 13, 140, 22);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JTextPane txtpnTips = new JTextPane();
		txtpnTips.setBackground(SystemColor.window);
		txtpnTips.setEditable(false);
		txtpnTips.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		txtpnTips.setText("  – Include mixed-case letters, numbers, and symbols\n  – Include a variety of different characters\n  – Avoid sequential or reused characters\n  – Make the password longer\n  – Substitue complexity with length, if needed\n\t");
		txtpnTips.setBounds(31, 113, 336, 142);
		panel_1.add(txtpnTips);

	}
}