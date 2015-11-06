package userInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dataManagement.DataEntry;

public class EditDataEntryView {

	private JFrame frame;
	private static DataEntry currentEntry;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditDataEntryView window = new EditDataEntryView(currentEntry);
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
	public EditDataEntryView(DataEntry data) {
		currentEntry = data;
		initialize();
	}
	


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//if(currentEntry.getEntryType().equals("SSN")) {
			JLabel label, label_1, label_2;
			JTextField textField, textField_1, textField_2;
			label = new JLabel("Entry Name");
			label.setBounds(40, 10, 150, 18);

			label_1 = new JLabel("Name");
			label_1.setBounds(40, 40, 150, 18);

			label_2 = new JLabel("SSN");
			label_2.setBounds(40, 70, 150, 18);

			textField = new JTextField("SSN");
			textField.setBounds(110, 10, 200, 18);
			textField.setColumns(10);

			textField_1 = new JTextField();
			textField_1.setBounds(110, 40, 200, 18);
			textField_1.setColumns(10);

			textField_2 = new JTextField();
			textField_2.setBounds(110, 70, 200, 18);
			textField_2.setColumns(10);

			frame.getContentPane().setLayout(null);
			frame.getContentPane().add(label);
			frame.getContentPane().add(label_1);
			frame.getContentPane().add(label_2);
			frame.getContentPane().add(textField);
			frame.getContentPane().add(textField_1);
			frame.getContentPane().add(textField_2);
		//}
			
	}

}
