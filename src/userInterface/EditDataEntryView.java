package userInterface;

import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
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
		frame.setLocationRelativeTo(null);
		String entryType = currentEntry.getEntryType();
		if (entryType.equals("SSN")) {
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
		}

		if (entryType.equals("Credit/Debit Card")) {
			JLabel label, label_1, label_2, label_3, label_4, label_5;
			JComboBox cardType;
			JTextField textField, textField_1, textField_2, textField_3, textField_4;

			label = new JLabel("Entry Name");
			label.setBounds(40, 10, 150, 18);

			label_1 = new JLabel("Card holder name");
			label_1.setBounds(40, 40, 150, 18);

			label_2 = new JLabel("Card Type");
			label_2.setBounds(40, 70, 150, 18);

			label_3 = new JLabel("Card Number");
			label_3.setBounds(40, 100, 150, 18);

			label_4 = new JLabel("Expired Date");
			label_4.setBounds(40, 130, 150, 18);

			label_5 = new JLabel("CSV");
			label_5.setBounds(40, 160, 150, 18);

			cardType = new JComboBox();
			cardType.setModel(new DefaultComboBoxModel(new String[] { "VISA", "MasterCard", "American Express",
					"Diners Club", "Carte Blanche", "Discover", "JCB" }));
			cardType.setBounds(170, 70, 200, 18);

			textField = new JTextField();
			textField.setBounds(170, 10, 200, 18);
			textField.setColumns(10);

			textField_1 = new JTextField();
			textField_1.setBounds(170, 40, 200, 18);
			textField_1.setColumns(10);

			textField_2 = new JTextField();
			textField_2.setBounds(170, 100, 200, 18);
			textField_2.setColumns(10);

			textField_3 = new JTextField();
			textField_3.setBounds(170, 130, 200, 18);
			textField_3.setColumns(10);

			textField_4 = new JTextField();
			textField_4.setBounds(170, 160, 200, 18);
			textField_4.setColumns(10);

			frame.getContentPane().setLayout(null);
			frame.getContentPane().add(label);
			frame.getContentPane().add(label_1);
			frame.getContentPane().add(label_2);
			frame.getContentPane().add(label_3);
			frame.getContentPane().add(label_4);
			frame.getContentPane().add(label_5);
			frame.getContentPane().add(textField);
			frame.getContentPane().add(textField_1);
			frame.getContentPane().add(textField_2);
			frame.getContentPane().add(textField_3);
			frame.getContentPane().add(textField_4);
			frame.getContentPane().add(cardType);

		}

		if (entryType.equals("Passport")) {
			JLabel label, label_1, label_2, label_3, label_4, label_5, label_6, label_7, label_8;
			JTextField textField, textField_1, textField_2, textField_3, textField_4, textField_5, textField_6,
					textField_7, textField_8;

			label = new JLabel("Entry Name");
			label.setBounds(40, 10, 150, 18);

			label_1 = new JLabel("Name");
			label_1.setBounds(40, 40, 150, 18);

			label_2 = new JLabel("Passport Number");
			label_2.setBounds(40, 70, 150, 18);

			label_3 = new JLabel("Nationality");
			label_3.setBounds(40, 100, 150, 18);

			label_4 = new JLabel("Sex");
			label_4.setBounds(40, 130, 150, 18);

			label_5 = new JLabel("Date of Birth");
			label_5.setBounds(40, 160, 150, 18);

			label_6 = new JLabel("Place of Birth");
			label_6.setBounds(40, 190, 150, 18);

			label_7 = new JLabel("Issued Date");
			label_7.setBounds(40, 220, 150, 18);

			label_8 = new JLabel("Expired Date");
			label_8.setBounds(40, 250, 150, 18);

			textField = new JTextField();
			textField.setBounds(170, 10, 200, 18);
			textField.setColumns(10);

			textField_1 = new JTextField();
			textField_1.setBounds(170, 40, 200, 18);
			textField_1.setColumns(10);

			textField_2 = new JTextField();
			textField_2.setBounds(170, 70, 200, 18);
			textField_2.setColumns(10);

			textField_3 = new JTextField();
			textField_3.setBounds(170, 100, 200, 18);
			textField_3.setColumns(10);

			textField_4 = new JTextField();
			textField_4.setBounds(170, 130, 200, 18);
			textField_4.setColumns(10);

			textField_5 = new JTextField();
			textField_5.setBounds(170, 160, 200, 18);
			textField_5.setColumns(10);

			textField_6 = new JTextField();
			textField_6.setBounds(170, 190, 200, 18);
			textField_6.setColumns(10);

			textField_7 = new JTextField();
			textField_7.setBounds(170, 220, 200, 18);
			textField_7.setColumns(10);

			textField_8 = new JTextField();
			textField_8.setBounds(170, 250, 200, 18);
			textField_8.setColumns(10);

			frame.getContentPane().setLayout(null);
			frame.getContentPane().add(label);
			frame.getContentPane().add(label_1);
			frame.getContentPane().add(label_2);
			frame.getContentPane().add(label_3);
			frame.getContentPane().add(label_4);
			frame.getContentPane().add(label_5);
			frame.getContentPane().add(label_6);
			frame.getContentPane().add(label_7);
			frame.getContentPane().add(label_8);
			frame.getContentPane().add(textField);
			frame.getContentPane().add(textField_1);
			frame.getContentPane().add(textField_2);
			frame.getContentPane().add(textField_3);
			frame.getContentPane().add(textField_4);
			frame.getContentPane().add(textField_5);
			frame.getContentPane().add(textField_6);
			frame.getContentPane().add(textField_7);
			frame.getContentPane().add(textField_8);
		}

		if (entryType.equals("Driver's License")) {
			JLabel label, label_1, label_2, label_3, label_4;
			JTextField textField, textField_1, textField_2, textField_3, textField_4;
			label = new JLabel("Entry Name");
			label.setBounds(40, 10, 150, 18);

			label_1 = new JLabel("Name");
			label_1.setBounds(40, 40, 150, 18);

			label_2 = new JLabel("Address");
			label_2.setBounds(40, 70, 150, 18);

			label_3 = new JLabel("Day of Birth");
			label_3.setBounds(40, 100, 150, 18);

			label_4 = new JLabel("Expired Date");
			label_4.setBounds(40, 130, 150, 18);

			textField = new JTextField();
			textField.setBounds(170, 10, 200, 18);
			textField.setColumns(10);

			textField_1 = new JTextField();
			textField_1.setBounds(170, 40, 200, 18);
			textField_1.setColumns(10);

			textField_2 = new JTextField();
			textField_2.setBounds(170, 70, 200, 18);
			textField_2.setColumns(10);

			textField_3 = new JTextField();
			textField_3.setBounds(170, 100, 200, 18);
			textField_3.setColumns(10);

			textField_4 = new JTextField();
			textField_4.setBounds(170, 130, 200, 18);
			textField_4.setColumns(10);

			frame.getContentPane().setLayout(null);
			frame.getContentPane().add(label);
			frame.getContentPane().add(label_1);
			frame.getContentPane().add(label_2);
			frame.getContentPane().add(label_3);
			frame.getContentPane().add(label_4);
			frame.getContentPane().add(textField);
			frame.getContentPane().add(textField_1);
			frame.getContentPane().add(textField_2);
			frame.getContentPane().add(textField_3);
			frame.getContentPane().add(textField_4);
		}

		if (entryType.equals("Website Logins")) {
			JLabel label, label_1, label_2, label_3;
			JTextField textField, textField_1, textField_2, textField_3;
			label = new JLabel("Entry Name");
			label.setBounds(40, 10, 150, 18);

			label_1 = new JLabel("Login AccountPassword");
			label_1.setBounds(40, 40, 150, 18);

			label_2 = new JLabel("Password");
			label_2.setBounds(40, 70, 150, 18);

			label_3 = new JLabel("Website Address");
			label_3.setBounds(40, 100, 150, 18);

			textField = new JTextField();
			textField.setBounds(170, 10, 200, 18);
			textField.setColumns(10);

			textField_1 = new JTextField();
			textField_1.setBounds(170, 40, 200, 18);
			textField_1.setColumns(10);

			textField_2 = new JTextField();
			textField_2.setBounds(170, 70, 200, 18);
			textField_2.setColumns(10);

			textField_3 = new JTextField();
			textField_3.setBounds(170, 100, 200, 18);
			textField_3.setColumns(10);

			frame.getContentPane().setLayout(null);
			frame.getContentPane().add(label);
			frame.getContentPane().add(label_1);
			frame.getContentPane().add(label_2);
			frame.getContentPane().add(label_3);
			frame.getContentPane().add(textField);
			frame.getContentPane().add(textField_1);
			frame.getContentPane().add(textField_2);
			frame.getContentPane().add(textField_3);
		}

		if (entryType.equals("Wifi Network")) {
			JLabel label, label_1, label_2, label_3;
			JTextField textField, textField_1, textField_2, textField_3;
			label = new JLabel("Entry Name");
			label.setBounds(40, 10, 150, 18);

			label_1 = new JLabel("Network Name(SSID)");
			label_1.setBounds(40, 40, 150, 18);

			label_2 = new JLabel("Password");
			label_2.setBounds(40, 70, 150, 18);

			label_3 = new JLabel("Security Mode");
			label_3.setBounds(40, 100, 150, 18);

			textField = new JTextField();
			textField.setBounds(170, 10, 200, 18);
			textField.setColumns(10);

			textField_1 = new JTextField();
			textField_1.setBounds(170, 40, 200, 18);
			textField_1.setColumns(10);

			textField_2 = new JTextField();
			textField_2.setBounds(170, 70, 200, 18);
			textField_2.setColumns(10);

			textField_3 = new JTextField();
			textField_3.setBounds(170, 100, 200, 18);
			textField_3.setColumns(10);

			frame.getContentPane().setLayout(null);
			frame.getContentPane().add(label);
			frame.getContentPane().add(label_1);
			frame.getContentPane().add(label_2);
			frame.getContentPane().add(label_3);
			frame.getContentPane().add(textField);
			frame.getContentPane().add(textField_1);
			frame.getContentPane().add(textField_2);
			frame.getContentPane().add(textField_3);
		}

		if (entryType.equals("Phone Number")) {
			JLabel label, label_1, label_2, label_3;
			JTextField textField, textField_1, textField_2, textField_3;
			label = new JLabel("Entry Name");
			label.setBounds(40, 10, 150, 18);

			label_1 = new JLabel("Name");
			label_1.setBounds(40, 40, 150, 18);

			label_2 = new JLabel("Phone Number");
			label_2.setBounds(40, 70, 150, 18);

			label_3 = new JLabel("Group");
			label_3.setBounds(40, 100, 150, 18);

			textField = new JTextField();
			textField.setBounds(170, 10, 200, 18);
			textField.setColumns(10);

			textField_1 = new JTextField();
			textField_1.setBounds(170, 40, 200, 18);
			textField_1.setColumns(10);

			textField_2 = new JTextField();
			textField_2.setBounds(170, 70, 200, 18);
			textField_2.setColumns(10);

			textField_3 = new JTextField();
			textField_3.setBounds(170, 100, 200, 18);
			textField_3.setColumns(10);

			frame.getContentPane().setLayout(null);
			frame.getContentPane().add(label);
			frame.getContentPane().add(label_1);
			frame.getContentPane().add(label_2);
			frame.getContentPane().add(label_3);
			frame.getContentPane().add(textField);
			frame.getContentPane().add(textField_1);
			frame.getContentPane().add(textField_2);
			frame.getContentPane().add(textField_3);

		}

	}

}
