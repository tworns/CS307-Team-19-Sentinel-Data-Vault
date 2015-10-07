import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
//import java.awt.EventQueue;

public class securityView {
	private securityView instance;
	 int instanceCount = 0;
	private JFrame frame;
		private securityView() {
			init();
		}
		
		public securityView getInstance() { 
			if(instanceCount != 0) { 
				return instance;
			}
			else { 
				securityView newInstance = new securityView();
				instanceCount++;
				return newInstance;
			}
			
		}
	
	private void init(){ 
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(213, 235, 117, 25);
		frame.getContentPane().add(btnCancel);
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.setBounds(84, 235, 117, 25);
		frame.getContentPane().add(btnGenerate);
		
		JCheckBox chckbxNoSpecialCharacters = new JCheckBox("No Special Characters");
		chckbxNoSpecialCharacters.setBounds(42, 60, 204, 23);
		frame.getContentPane().add(chckbxNoSpecialCharacters);
		
		JCheckBox chckbxNoUppercase = new JCheckBox("No Uppercase");
		chckbxNoUppercase.setBounds(52, 87, 129, 23);
		frame.getContentPane().add(chckbxNoUppercase);
		
		JCheckBox chckbxNoNumbers = new JCheckBox("No Numbers");
		chckbxNoNumbers.setBounds(41, 119, 129, 23);
		frame.getContentPane().add(chckbxNoNumbers);
	}
}
