import java.awt.EventQueue;
import java.awt.event.*;
import javax.swing.*;

public class SecurityView {

	private JFrame frame;
	private int instanceCount;
	private SecurityView instance; 
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
	private SecurityView() {
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
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setTitle("Security View");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 432, 255);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Password Generator", null, panel, null);
		panel.setLayout(null);
		
		JCheckBox chckbxNoSpecialCharacters = new JCheckBox("No Special Characters");
		chckbxNoSpecialCharacters.setBounds(28, 94, 155, 25);
		panel.add(chckbxNoSpecialCharacters);
		
		JCheckBox chckbxNoUppercase = new JCheckBox("No Uppercase");
		chckbxNoUppercase.setBounds(28, 34, 107, 25);
		panel.add(chckbxNoUppercase);
		
		JCheckBox chckbxNoNumbers = new JCheckBox("No Numbers");
		chckbxNoNumbers.setBounds(28, 64, 99, 25);
		panel.add(chckbxNoNumbers);
		
		JLabel lblLengthOfPassword = new JLabel("Password Length");
		lblLengthOfPassword.setBounds(88, 136, 110, 16);
		panel.add(lblLengthOfPassword);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(8, 8, 32, 1));
		spinner.setBounds(31, 134, 45, 19);
		panel.add(spinner);
		spinner.setAutoscrolls(true);
		

		JButton btnGenerate = new JButton("Generate");
		btnGenerate.setBounds(14, 187, 95, 25);
		panel.add(btnGenerate);
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent clickGenerate) {
				if(clickGenerate.getActionCommand().equalsIgnoreCase("Generate")) { 
					//System.out.println("This is a new password, shut up");
					PasswordGen k = new PasswordGen(4,0,0,0);
					String password = k.genny(4,0,0,0);
					JTextArea displayPass = new JTextArea(1,1);
					displayPass.setText(password);
					displayPass.setEditable(false);
					JOptionPane.showMessageDialog(null, new JScrollPane(displayPass), "Password", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(121, 187, 85, 25);
		panel.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent clickCancel) {
				if(clickCancel.getActionCommand().equalsIgnoreCase("Cancel")) {
					frame.dispose();
				}
			}
		});
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Password Strength Checker", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblEnterPassword = new JLabel("Enter Password");
		lblEnterPassword.setBounds(22, 13, 96, 16);
		panel_1.add(lblEnterPassword);
		
		textField = new JTextField();
		textField.setBounds(22, 42, 116, 22);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JButton btnCheck = new JButton("Check");
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equalsIgnoreCase("Check")){
					System.out.println("Yep, that's a password.");
				}
			}
		});
		btnCheck.setBounds(21, 104, 97, 25);
		panel_1.add(btnCheck);
		
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

	}
}
