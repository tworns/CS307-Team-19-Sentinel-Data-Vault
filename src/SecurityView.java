import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class SecurityView {

	private JFrame frame;
	private JTextField txtPasswordLength;
	private int instanceCount;
	private SecurityView instance; 
	/**
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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(186, 169, 117, 25);
		frame.getContentPane().add(btnCancel);
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.setBounds(42, 169, 117, 25);
		frame.getContentPane().add(btnGenerate);
		
		JCheckBox chckbxNoSpecialCharacters = new JCheckBox("No Special Characters");
		chckbxNoSpecialCharacters.setBounds(42, 62, 204, 23);
		frame.getContentPane().add(chckbxNoSpecialCharacters);
		
		JCheckBox chckbxNoUppercase = new JCheckBox("No Uppercase");
		chckbxNoUppercase.setBounds(42, 87, 129, 23);
		frame.getContentPane().add(chckbxNoUppercase);
		
		JCheckBox chckbxNoNumbers = new JCheckBox("No Numbers");
		chckbxNoNumbers.setBounds(42, 111, 129, 23);
		frame.getContentPane().add(chckbxNoNumbers);
		
		txtPasswordLength = new JTextField();
		txtPasswordLength.setText("Password Length");
		txtPasswordLength.setBounds(45, 138, 114, 19);
		frame.getContentPane().add(txtPasswordLength);
		txtPasswordLength.setColumns(10);
	}

}
