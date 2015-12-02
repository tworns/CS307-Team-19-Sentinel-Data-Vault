package userInterface;

import java.awt.EventQueue;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.time.LocalDateTime;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controllers.DatabaseManager;
import dataManagement.DataEntry;
import dataManagement.User;

public class EditDataEntryView {

	private JFrame frame;
	private DataEntry currentEntry;
	private User currentUser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DatabaseManager m = new DatabaseManager("vault_database");
					DataEntry d = m.retrieveOneDataEntry("myvisa", new User(null, null, null, null, null, null, null),
							"Credit/Debit Card");
					EditDataEntryView window = new EditDataEntryView(new User(null, null, null, null, null, null, null),
							d);
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
	public EditDataEntryView(User currentUser, DataEntry data) {
		this.currentUser = currentUser;
		currentEntry = data;
		initialize();
	}

	public EditDataEntryView() {
		initialize();
	}

	public JFrame getJframe() {
		return this.frame;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(150, 150, 550, 450);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		String entryType = currentEntry.getEntryType();

		// Confirmation Number
		if (entryType.equals("Confirmation Number")) {
			JLabel label, label_1, label_2;
			JTextField textField, textField_1, textField_2;

			label = new JLabel("Entry Name");
			label.setBounds(40, 10, 150, 18);

			label_1 = new JLabel("Confirmation Name");
			label_1.setBounds(40, 40, 150, 18);

			label_2 = new JLabel("Confirmation Number");
			label_2.setBounds(40, 70, 150, 18);

			textField = new JTextField(currentEntry.getEntryName());
			textField.setBounds(250, 10, 200, 18);
			textField.setColumns(10);

			textField_1 = new JTextField(currentEntry.getFieldDataList().get(1));
			textField_1.setBounds(250, 40, 200, 18);
			textField_1.setColumns(10);

			textField_2 = new JTextField(currentEntry.getFieldDataList().get(2));
			textField_2.setBounds(250, 70, 200, 18);
			textField_2.setColumns(10);

			frame.getContentPane().setLayout(null);
			frame.getContentPane().add(label);
			frame.getContentPane().add(label_1);
			frame.getContentPane().add(label_2);
			frame.getContentPane().add(textField);
			frame.getContentPane().add(textField_1);
			frame.getContentPane().add(textField_2);
			JButton btnSave = new JButton("Save");
			btnSave.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					if (textField.getText().equals("") || textField_1.getText().equals("")
							|| textField_2.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Please complete all the fields!");
						return;
					}
					LocalDateTime modifytime = LocalDateTime.now();
					DataEntry newEntry = new DataEntry(textField.getText(), "Confirmation Number",
							currentEntry.getEncryptionKey(), currentEntry.getOwner(), 0, modifytime);
					newEntry.addDataField(textField_1.getText());
					newEntry.addDataField(textField_2.getText());
					DatabaseManager m = new DatabaseManager("vault_database");
					int result = m.updateEntry(currentUser, currentEntry, newEntry);
					if (result == -1) {
						JOptionPane.showMessageDialog(null,
								"This entry name already exists! Please choose a different entry name!");
						return;
					} else {
						JOptionPane.showMessageDialog(null, "You have successfully modified this data entry!");
						System.gc();
						for (Window window : Window.getWindows()) {
							window.dispose();
						}

						frame.dispose();

						HomeView hv = new HomeView(currentEntry.getOwner());
						hv.frmSentinelDataVault.setVisible(true);
					}
				}
			});
			btnSave.setBounds(85, 307, 117, 29);
			frame.getContentPane().add(btnSave);

			JButton btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					frame.dispose();

				}
			});
			btnCancel.setBounds(305, 307, 117, 29);
			frame.getContentPane().add(btnCancel);
		}

		// SSN
		if (entryType.equals("SSN")) {
			JLabel label, label_1, label_2;
			JTextField textField, textField_1, textField_2;
			label = new JLabel("Entry Name");
			label.setBounds(40, 10, 150, 18);

			label_1 = new JLabel("Name");
			label_1.setBounds(40, 40, 150, 18);

			label_2 = new JLabel("SSN");
			label_2.setBounds(40, 70, 150, 18);

			textField = new JTextField(currentEntry.getEntryName());
			textField.setBounds(110, 10, 200, 18);
			textField.setColumns(10);

			textField_1 = new JTextField(currentEntry.getFieldDataList().get(0));
			textField_1.setBounds(110, 40, 200, 18);
			textField_1.setColumns(10);

			textField_2 = new JTextField(currentEntry.getFieldDataList().get(1));
			textField_2.setBounds(110, 70, 200, 18);
			textField_2.setColumns(10);

			frame.getContentPane().setLayout(null);
			frame.getContentPane().add(label);
			frame.getContentPane().add(label_1);
			frame.getContentPane().add(label_2);
			frame.getContentPane().add(textField);
			frame.getContentPane().add(textField_1);
			frame.getContentPane().add(textField_2);

			JButton btnSave = new JButton("Save");
			btnSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (textField.getText().equals("") || textField_1.getText().equals("")
							|| textField_2.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Please complete all the fields!");
						return;
					}
					LocalDateTime createdtime = LocalDateTime.now();
					DataEntry newEntry = new DataEntry(textField.getText(), "SSN", currentEntry.getEncryptionKey(),
							currentEntry.getOwner(), 0, createdtime);
					newEntry.addDataField(textField_1.getText());
					newEntry.addDataField(textField_2.getText());
					DatabaseManager m = new DatabaseManager("vault_database");
					int result = m.updateEntry(currentUser, currentEntry, newEntry);
					if (result == -1) {
						JOptionPane.showMessageDialog(null,
								"This entry name already exists! Please choose a different entry name!");
						return;
					} else {

						JOptionPane.showMessageDialog(null, "You have successfully modified this data entry!");
						System.gc();
						for (Window window : Window.getWindows()) {
							window.dispose();
						}

						frame.dispose();

						HomeView hv = new HomeView(currentEntry.getOwner());
						hv.frmSentinelDataVault.setVisible(true);

					}
				}
			});
			btnSave.setBounds(85, 307, 117, 29);
			frame.getContentPane().add(btnSave);

			JButton btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					frame.dispose();

				}
			});
			btnCancel.setBounds(305, 307, 117, 29);
			frame.getContentPane().add(btnCancel);

		}

		// Credit/Debit Card
		if (entryType.equals("Credit/Debit Card")) {
			JLabel label, label_1, label_2, label_3, label_4, label_5;
			JComboBox cardType;
			JTextField textField, textField_1, textField_2, textField_3, textField_4;

			label = new JLabel("Entry Name");
			label.setBounds(40, 10, 150, 18);

			label_1 = new JLabel("Card Type");
			label_1.setBounds(40, 40, 150, 18);

			label_2 = new JLabel("Card Number");
			label_2.setBounds(40, 70, 150, 18);

			label_3 = new JLabel("Name on Card");
			label_3.setBounds(40, 100, 150, 18);

			label_4 = new JLabel("CVV");
			label_4.setBounds(40, 130, 150, 18);

			label_5 = new JLabel("Expired Date");
			label_5.setBounds(40, 160, 150, 18);

			cardType = new JComboBox();
			cardType.setModel(
					new DefaultComboBoxModel(new String[] { "VISA", "Master", "AMERICAN EXPRESS", "Discover", }));
			cardType.setBounds(170, 40, 200, 18);
			cardType.setSelectedItem(currentEntry.getFieldDataList().get(0));

			JComboBox month = new JComboBox();
			month.setModel(new DefaultComboBoxModel(
					new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
			month.setBounds(170, 160, 90, 18);
			month.setSelectedItem(currentEntry.getFieldDataList().get(4).substring(0, 1));

			JComboBox year = new JComboBox();
			year.setModel(new DefaultComboBoxModel(new String[] { "15", "16", "17", "18", "19", "20", "21", "22", "23",
					"24", "25", "26", "27", "28", "29", "30" }));
			year.setBounds(270, 160, 90, 18);
			year.setSelectedItem(currentEntry.getFieldDataList().get(4).substring(3));

			textField = new JTextField(currentEntry.getEntryName());
			textField.setBounds(170, 10, 200, 18);
			textField.setColumns(10);

			textField_1 = new JTextField(currentEntry.getFieldDataList().get(1));
			textField_1.setBounds(170, 70, 200, 18);
			textField_1.setColumns(10);

			textField_2 = new JTextField(currentEntry.getFieldDataList().get(2));
			textField_2.setBounds(170, 100, 200, 18);
			textField_2.setColumns(10);

			textField_3 = new JTextField(currentEntry.getFieldDataList().get(3));
			textField_3.setBounds(170, 130, 200, 18);
			textField_3.setColumns(10);

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
			frame.getContentPane().add(month);
			frame.getContentPane().add(year);
			frame.getContentPane().add(cardType);
			JButton btnSave = new JButton("Save");
			btnSave.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					if (textField.getText().equals("") || textField_1.getText().equals("")
							|| textField_2.getText().equals("") || textField_3.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Please complete all the fields!");
						return;
					}
					LocalDateTime modifytime = LocalDateTime.now();
					DataEntry newEntry = new DataEntry(textField.getText(), "Credit/Debit Card",
							currentEntry.getEncryptionKey(), currentEntry.getOwner(), 0, modifytime);
					newEntry.addDataField((String) cardType.getSelectedItem());
					newEntry.addDataField(textField_1.getText());
					newEntry.addDataField(textField_2.getText());
					newEntry.addDataField(textField_3.getText());
					newEntry.addDataField(((String) month.getSelectedItem()) + "/" + ((String) year.getSelectedItem()));
					DatabaseManager m = new DatabaseManager("vault_database");
					int result = m.updateEntry(currentUser, currentEntry, newEntry);
					if (result == -1) {
						JOptionPane.showMessageDialog(null,
								"This entry name already exists! Please choose a different entry name!");
						return;
					} else {
						JOptionPane.showMessageDialog(null, "You have successfully modified this data entry!");
						System.gc();
						for (Window window : Window.getWindows()) {
							window.dispose();
						}

						frame.dispose();

						HomeView hv = new HomeView(currentEntry.getOwner());
						hv.frmSentinelDataVault.setVisible(true);
					}
				}
			});
			btnSave.setBounds(85, 307, 117, 29);
			frame.getContentPane().add(btnSave);

			JButton btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					frame.dispose();

				}
			});
			btnCancel.setBounds(305, 307, 117, 29);
			frame.getContentPane().add(btnCancel);

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

			textField = new JTextField(currentEntry.getEntryName());
			textField.setBounds(170, 10, 200, 18);
			textField.setColumns(10);

			textField_1 = new JTextField(currentEntry.getFieldDataList().get(0));
			textField_1.setBounds(170, 40, 200, 18);
			textField_1.setColumns(10);

			textField_2 = new JTextField(currentEntry.getFieldDataList().get(1));
			textField_2.setBounds(170, 70, 200, 18);
			textField_2.setColumns(10);

			textField_3 = new JTextField(currentEntry.getFieldDataList().get(2));
			textField_3.setBounds(170, 100, 200, 18);
			textField_3.setColumns(10);

			textField_4 = new JTextField(currentEntry.getFieldDataList().get(3));
			textField_4.setBounds(170, 130, 200, 18);
			textField_4.setColumns(10);

			textField_5 = new JTextField(currentEntry.getFieldDataList().get(4));
			textField_5.setBounds(170, 160, 200, 18);
			textField_5.setColumns(10);

			textField_6 = new JTextField(currentEntry.getFieldDataList().get(5));
			textField_6.setBounds(170, 190, 200, 18);
			textField_6.setColumns(10);

			textField_7 = new JTextField(currentEntry.getFieldDataList().get(6));
			textField_7.setBounds(170, 220, 200, 18);
			textField_7.setColumns(10);

			textField_8 = new JTextField(currentEntry.getFieldDataList().get(7));
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

			JButton btnSave = new JButton("Save");
			btnSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (textField.getText().equals("") || textField_1.getText().equals("")
							|| textField_2.getText().equals("") || textField_3.getText().equals("")
							|| textField_4.getText().equals("") || textField_5.getText().equals("")
							|| textField_6.getText().equals("") || textField_7.getText().equals("")
							|| textField_8.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Please complete all the fields!");
						return;
					}
					LocalDateTime createdtime = LocalDateTime.now();
					DataEntry newEntry = new DataEntry(textField.getText(), "PassPort", currentEntry.getEncryptionKey(),
							currentEntry.getOwner(), 0, createdtime);
					newEntry.addDataField(textField_1.getText());
					newEntry.addDataField(textField_2.getText());
					newEntry.addDataField(textField_3.getText());
					newEntry.addDataField(textField_4.getText());
					newEntry.addDataField(textField_5.getText());
					newEntry.addDataField(textField_6.getText());
					newEntry.addDataField(textField_7.getText());
					newEntry.addDataField(textField_8.getText());

					DatabaseManager m = new DatabaseManager("vault_database");
					int result = m.updateEntry(currentUser, currentEntry, newEntry);
					if (result == -1) {
						JOptionPane.showMessageDialog(null,
								"This entry name already exists! Please choose a different entry name!");
						return;
					} else {

						JOptionPane.showMessageDialog(null, "You have successfully modified DataEntry");
						System.gc();
						for (Window window : Window.getWindows()) {
							window.dispose();
						}

						frame.dispose();

						HomeView hv = new HomeView(currentEntry.getOwner());
						hv.frmSentinelDataVault.setVisible(true);

					}
				}
			});
			btnSave.setBounds(85, 347, 117, 29);
			frame.getContentPane().add(btnSave);

			JButton btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					frame.dispose();

				}
			});
			btnCancel.setBounds(305, 347, 117, 29);
			frame.getContentPane().add(btnCancel);

		}

		// Entry Code
		if (entryType.equals("Entry Code")) {
			JLabel label, label_1, label_2;
			JTextField textField, textField_1, textField_2;
			label = new JLabel("Entry Name");
			label.setBounds(40, 10, 150, 18);

			label_1 = new JLabel("Code Name");
			label_1.setBounds(40, 40, 150, 18);

			label_2 = new JLabel("Code");
			label_2.setBounds(40, 70, 150, 18);

			textField = new JTextField(currentEntry.getEntryName());
			textField.setBounds(170, 10, 200, 18);
			textField.setColumns(10);

			textField_1 = new JTextField(currentEntry.getFieldDataList().get(0));
			textField_1.setBounds(170, 40, 200, 18);
			textField_1.setColumns(10);

			textField_2 = new JTextField(currentEntry.getFieldDataList().get(1));
			textField_2.setBounds(170, 70, 200, 18);
			textField_2.setColumns(10);

			frame.getContentPane().setLayout(null);
			frame.getContentPane().add(label);
			frame.getContentPane().add(label_1);
			frame.getContentPane().add(label_2);
			frame.getContentPane().add(textField);
			frame.getContentPane().add(textField_1);
			frame.getContentPane().add(textField_2);

			JButton btnSave = new JButton("Save");
			btnSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (textField.getText().equals("") || textField_1.getText().equals("")
							|| textField_2.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Please complete all the fields!");
						return;
					}
					LocalDateTime createdtime = LocalDateTime.now();
					DataEntry newEntry = new DataEntry(textField.getText(), "Entry Code",
							currentEntry.getEncryptionKey(), currentEntry.getOwner(), 0, createdtime);
					newEntry.addDataField(textField_1.getText());
					newEntry.addDataField(textField_2.getText());

					DatabaseManager m = new DatabaseManager("vault_database");
					int result = m.updateEntry(currentUser, currentEntry, newEntry);
					if (result == -1) {
						JOptionPane.showMessageDialog(null,
								"This entry name already exists! Please choose a different entry name!");
						return;
					} else {
						JOptionPane.showMessageDialog(null, "You have successfully modified this data entry!");
						System.gc();
						for (Window window : Window.getWindows()) {
							window.dispose();
						}

						frame.dispose();

						HomeView hv = new HomeView(currentEntry.getOwner());
						hv.frmSentinelDataVault.setVisible(true);

					}
				}
			});
			btnSave.setBounds(85, 307, 117, 29);
			frame.getContentPane().add(btnSave);

			JButton btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					frame.dispose();

				}
			});
			btnCancel.setBounds(305, 307, 117, 29);
			frame.getContentPane().add(btnCancel);
		}

		// General Password
		if (entryType.equals("General Password")) {
			JLabel label, label_1, label_2;
			JTextField textField, textField_1, textField_2;
			label = new JLabel("Entry Name");
			label.setBounds(40, 10, 150, 18);

			label_1 = new JLabel("Password Name");
			label_1.setBounds(40, 40, 150, 18);

			label_2 = new JLabel("Password");
			label_2.setBounds(40, 70, 150, 18);

			textField = new JTextField(currentEntry.getEntryName());
			textField.setBounds(250, 10, 200, 18);
			textField.setColumns(10);

			textField_1 = new JTextField(currentEntry.getFieldDataList().get(0));
			textField_1.setBounds(250, 40, 200, 18);
			textField_1.setColumns(10);

			textField_2 = new JTextField(currentEntry.getFieldDataList().get(1));
			textField_2.setBounds(250, 70, 200, 18);
			textField_2.setColumns(10);

			frame.getContentPane().setLayout(null);
			frame.getContentPane().add(label);
			frame.getContentPane().add(label_1);
			frame.getContentPane().add(label_2);
			frame.getContentPane().add(textField);
			frame.getContentPane().add(textField_1);
			frame.getContentPane().add(textField_2);

			JButton btnSave = new JButton("Save");
			btnSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (textField.getText().equals("") || textField_1.getText().equals("")
							|| textField_2.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Please complete all the fields!");
						return;
					}
					LocalDateTime createdtime = LocalDateTime.now();
					DataEntry newEntry = new DataEntry(textField.getText(), "General Password",
							currentEntry.getEncryptionKey(), currentEntry.getOwner(), 0, createdtime);
					newEntry.addDataField(textField_1.getText());
					newEntry.addDataField(textField_2.getText());
					DatabaseManager m = new DatabaseManager("vault_database");
					int result = m.updateEntry(currentUser, currentEntry, newEntry);
					if (result == -1) {
						JOptionPane.showMessageDialog(null,
								"This entry name already exists! Please choose a different entry name!");
						return;
					} else {

						JOptionPane.showMessageDialog(null, "You have successfully modified this data entry!");
						System.gc();
						for (Window window : Window.getWindows()) {
							window.dispose();
						}

						frame.dispose();

						HomeView hv = new HomeView(currentEntry.getOwner());
						hv.frmSentinelDataVault.setVisible(true);

					}
				}
			});
			btnSave.setBounds(85, 307, 117, 29);
			frame.getContentPane().add(btnSave);

			JButton btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					frame.dispose();

				}
			});
			btnCancel.setBounds(305, 307, 117, 29);
			frame.getContentPane().add(btnCancel);

		}

		// Serial Number
		if (entryType.equals("Serial Number")) {
			JLabel label, label_1, label_2;
			JTextField textField, textField_1, textField_2;
			label = new JLabel("Entry Name");
			label.setBounds(40, 10, 150, 18);

			label_1 = new JLabel("Product Name");
			label_1.setBounds(40, 40, 150, 18);

			label_2 = new JLabel("Serial Number");
			label_2.setBounds(40, 70, 150, 18);

			textField = new JTextField(currentEntry.getEntryName());
			textField.setBounds(250, 10, 200, 18);
			textField.setColumns(10);

			textField_1 = new JTextField(currentEntry.getFieldDataList().get(0));
			textField_1.setBounds(250, 40, 200, 18);
			textField_1.setColumns(10);

			textField_2 = new JTextField(currentEntry.getFieldDataList().get(1));
			textField_2.setBounds(250, 70, 200, 18);
			textField_2.setColumns(10);

			frame.getContentPane().setLayout(null);
			frame.getContentPane().add(label);
			frame.getContentPane().add(label_1);
			frame.getContentPane().add(label_2);
			frame.getContentPane().add(textField);
			frame.getContentPane().add(textField_1);
			frame.getContentPane().add(textField_2);

			JButton btnSave = new JButton("Save");
			btnSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (textField.getText().equals("") || textField_1.getText().equals("")
							|| textField_2.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Please complete all the fields!");
						return;
					}
					LocalDateTime createdtime = LocalDateTime.now();
					DataEntry newEntry = new DataEntry(textField.getText(), "Serial Number",
							currentEntry.getEncryptionKey(), currentEntry.getOwner(), 0, createdtime);
					newEntry.addDataField(textField_1.getText());
					newEntry.addDataField(textField_2.getText());
					DatabaseManager m = new DatabaseManager("vault_database");
					int result = m.updateEntry(currentUser, currentEntry, newEntry);
					if (result == -1) {
						JOptionPane.showMessageDialog(null,
								"This entry name already exists! Please choose a different entry name!");
						return;
					} else {

						JOptionPane.showMessageDialog(null, "You have successfully modified this data entry!");
						System.gc();
						for (Window window : Window.getWindows()) {
							window.dispose();
						}

						frame.dispose();

						HomeView hv = new HomeView(currentEntry.getOwner());
						hv.frmSentinelDataVault.setVisible(true);

					}
				}
			});
			btnSave.setBounds(85, 307, 117, 29);
			frame.getContentPane().add(btnSave);

			JButton btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					frame.dispose();

				}
			});
			btnCancel.setBounds(305, 307, 117, 29);
			frame.getContentPane().add(btnCancel);
		}

		// Shipment Tracking Number
		if (entryType.equals("Shipment Tracking Number")) {
			JLabel label, label_1, label_2;
			JTextField textField, textField_1, textField_2;
			label = new JLabel("Entry Name");
			label.setBounds(40, 10, 150, 18);

			label_1 = new JLabel("Company/Item Name");
			label_1.setBounds(40, 40, 150, 18);

			label_2 = new JLabel("Shipment Tracking Number");
			label_2.setBounds(40, 70, 150, 18);

			textField = new JTextField(currentEntry.getEntryName());
			textField.setBounds(250, 10, 200, 18);
			textField.setColumns(10);

			textField_1 = new JTextField(currentEntry.getFieldDataList().get(0));
			textField_1.setBounds(250, 40, 200, 18);
			textField_1.setColumns(10);

			textField_2 = new JTextField(currentEntry.getFieldDataList().get(1));
			textField_2.setBounds(250, 70, 200, 18);
			textField_2.setColumns(10);

			frame.getContentPane().setLayout(null);
			frame.getContentPane().add(label);
			frame.getContentPane().add(label_1);
			frame.getContentPane().add(label_2);
			frame.getContentPane().add(textField);
			frame.getContentPane().add(textField_1);
			frame.getContentPane().add(textField_2);

			JButton btnSave = new JButton("Save");
			btnSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (textField.getText().equals("") || textField_1.getText().equals("")
							|| textField_2.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Please complete all the fields!");
						return;
					}
					LocalDateTime createdtime = LocalDateTime.now();
					DataEntry newEntry = new DataEntry(textField.getText(), "Shipment Tracking Number",
							currentEntry.getEncryptionKey(), currentEntry.getOwner(), 0, createdtime);
					newEntry.addDataField(textField_1.getText());
					newEntry.addDataField(textField_2.getText());
					DatabaseManager m = new DatabaseManager("vault_database");
					int result = m.updateEntry(currentUser, currentEntry, newEntry);
					if (result == -1) {
						JOptionPane.showMessageDialog(null,
								"This entry name already exists! Please choose a different entry name!");
						return;
					} else {

						JOptionPane.showMessageDialog(null, "You have successfully modified this data entry!");
						System.gc();
						for (Window window : Window.getWindows()) {
							window.dispose();
						}

						frame.dispose();

						HomeView hv = new HomeView(currentEntry.getOwner());
						hv.frmSentinelDataVault.setVisible(true);

					}
				}
			});
			btnSave.setBounds(85, 307, 117, 29);
			frame.getContentPane().add(btnSave);

			JButton btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					frame.dispose();

				}
			});
			btnCancel.setBounds(305, 307, 117, 29);
			frame.getContentPane().add(btnCancel);
		}

		// ID Card
		if (entryType.equals("ID Card")) {
			JLabel label, label_1, label_2, label_3, label_4, label_5, label_6;
			JTextField textField, textField_1, textField_2, textField_3, textField_4;
			label = new JLabel("Entry Name");
			label.setBounds(40, 10, 150, 18);

			label_1 = new JLabel("ID Card Name");
			label_1.setBounds(40, 40, 150, 18);

			label_2 = new JLabel("ID Card Number");
			label_2.setBounds(40, 70, 150, 18);

			label_3 = new JLabel("Cardholder Name");
			label_3.setBounds(40, 100, 150, 18);

			label_4 = new JLabel("Address");
			label_4.setBounds(40, 130, 150, 18);

			label_5 = new JLabel("Issue Date");
			label_5.setBounds(40, 160, 150, 18);

			label_6 = new JLabel("Expiration Date");
			label_6.setBounds(40, 190, 150, 18);

			textField = new JTextField(currentEntry.getEntryName());
			textField.setBounds(250, 10, 200, 18);
			textField.setColumns(10);

			textField_1 = new JTextField(currentEntry.getFieldDataList().get(0));
			textField_1.setBounds(250, 40, 200, 18);
			textField_1.setColumns(10);

			textField_2 = new JTextField(currentEntry.getFieldDataList().get(1));
			textField_2.setBounds(250, 70, 200, 18);
			textField_2.setColumns(10);

			textField_3 = new JTextField(currentEntry.getFieldDataList().get(2));
			textField_3.setBounds(250, 100, 200, 18);
			textField_3.setColumns(10);

			textField_4 = new JTextField(currentEntry.getFieldDataList().get(3));
			textField_4.setBounds(250, 130, 200, 18);
			textField_4.setColumns(10);

			JComboBox date = new JComboBox();
			date.setModel(new DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09",
					"10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25",
					"26", "27", "28", "29", "30", "31" }));
			date.setBounds(325, 160, 66, 18);
			date.setSelectedItem(currentEntry.getFieldDataList().get(4).substring(4, 6));

			JComboBox month = new JComboBox();
			month.setModel(new DefaultComboBoxModel(new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
					"Aug", "Sep", "Oct", "Nov", "Dec" }));
			month.setBounds(250, 160, 75, 18);
			month.setSelectedItem(currentEntry.getFieldDataList().get(4).substring(0, 3));

			JComboBox year = new JComboBox();
			year.setModel(new DefaultComboBoxModel(new String[] { "2015", "2016", "2017", "2018", "2019", "2020",
					"2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));
			year.setBounds(392, 160, 85, 18);
			year.setSelectedItem(currentEntry.getFieldDataList().get(4).substring(7));

			JComboBox date_1 = new JComboBox();
			date_1.setModel(new DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08",
					"09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24",
					"25", "26", "27", "28", "29", "30", "31" }));
			date_1.setBounds(325, 190, 66, 18);
			date_1.setSelectedItem(currentEntry.getFieldDataList().get(5).substring(4, 6));

			JComboBox month_1 = new JComboBox();
			month_1.setModel(new DefaultComboBoxModel(new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
					"Aug", "Sep", "Oct", "Nov", "Dec" }));
			month_1.setBounds(250, 190, 75, 18);
			month_1.setSelectedItem(currentEntry.getFieldDataList().get(5).substring(0, 3));

			JComboBox year_1 = new JComboBox();
			year_1.setModel(new DefaultComboBoxModel(new String[] { "2015", "2016", "2017", "2018", "2019", "2020",
					"2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));
			year_1.setBounds(392, 190, 85, 18);
			year_1.setSelectedItem(currentEntry.getFieldDataList().get(5).substring(7));

			frame.getContentPane().setLayout(null);
			frame.getContentPane().add(label);
			frame.getContentPane().add(label_1);
			frame.getContentPane().add(label_2);
			frame.getContentPane().add(label_3);
			frame.getContentPane().add(label_4);
			frame.getContentPane().add(label_5);
			frame.getContentPane().add(label_6);
			frame.getContentPane().add(textField);
			frame.getContentPane().add(textField_1);
			frame.getContentPane().add(textField_2);
			frame.getContentPane().add(textField_3);
			frame.getContentPane().add(textField_4);
			frame.getContentPane().add(date);
			frame.getContentPane().add(month);
			frame.getContentPane().add(year);
			frame.getContentPane().add(date_1);
			frame.getContentPane().add(month_1);
			frame.getContentPane().add(year_1);
			frame.setLocationRelativeTo(null);
			JButton btnSave = new JButton("Save");
			btnSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (textField.getText().equals("") || textField_1.getText().equals("")
							|| textField_2.getText().equals("") || textField_3.getText().equals("")
							|| textField_4.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Please complete all the fields!");
						return;
					}
					LocalDateTime createdtime = LocalDateTime.now();
					DataEntry newEntry = new DataEntry(textField.getText(), "ID Card", currentEntry.getEncryptionKey(),
							currentUser.getUsername(), 0, createdtime);
					newEntry.addDataField(textField_1.getText());
					newEntry.addDataField(textField_2.getText());
					newEntry.addDataField(textField_3.getText());
					newEntry.addDataField(textField_4.getText());
					newEntry.addDataField(
							month.getSelectedItem() + "/" + date.getSelectedItem() + "/" + year.getSelectedItem());
					newEntry.addDataField(month_1.getSelectedItem() + "/" + date_1.getSelectedItem() + "/"
							+ year_1.getSelectedItem());

					DatabaseManager m = new DatabaseManager("vault_database");
					int result = m.updateEntry(currentUser, currentEntry, newEntry);
					if (result == -1) {
						JOptionPane.showMessageDialog(null,
								"This entry name already exists! Please choose a different entry name!");
						return;
					} else {
						JOptionPane.showMessageDialog(null, "You have successfully modified DataEntry");
						System.gc();
						for (Window window : Window.getWindows()) {
							window.dispose();
						}

						frame.dispose();

						HomeView hv = new HomeView(currentUser.getUsername());
						hv.frmSentinelDataVault.setVisible(true);

					}
				}
			});
			btnSave.setBounds(85, 347, 117, 29);
			frame.getContentPane().add(btnSave);

			JButton btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					frame.dispose();

				}
			});
			btnCancel.setBounds(305, 347, 117, 29);
			frame.getContentPane().add(btnCancel);
		}

		// Flight Ticket
		if (entryType.equals("Flight Ticket")) {
			frame.setBounds(150, 150, 600, 450);
			JLabel label, label_1, label_2, label_3, label_4, label_5, label_6, label_7, label_8, label_9, label_10;
			JTextField textField, textField_1, textField_2, textField_3, textField_4, textField_5, textField_6,
					textField_7, textField_8;
			label = new JLabel("Entry Name");
			label.setBounds(40, 10, 150, 18);

			label_1 = new JLabel("Passenger");
			label_1.setBounds(40, 40, 150, 18);

			label_2 = new JLabel("Destination");
			label_2.setBounds(40, 70, 150, 18);

			label_3 = new JLabel("Airport (Origin)");
			label_3.setBounds(40, 100, 150, 18);

			label_4 = new JLabel("Airport (Dest.)");
			label_4.setBounds(40, 130, 150, 18);

			label_5 = new JLabel("Gate (Origin)");
			label_5.setBounds(40, 160, 150, 18);

			label_6 = new JLabel("Gate (Dest.)");
			label_6.setBounds(40, 190, 150, 18);

			label_7 = new JLabel("Departure");
			label_7.setBounds(40, 220, 150, 18);

			label_8 = new JLabel("Arrival");
			label_8.setBounds(40, 250, 150, 18);

			label_9 = new JLabel("Group");
			label_9.setBounds(40, 280, 150, 18);

			label_10 = new JLabel("Seat");
			label_10.setBounds(40, 310, 150, 18);

			textField = new JTextField(currentEntry.getEntryName());
			textField.setBounds(250, 10, 200, 18);
			textField.setColumns(10);

			textField_1 = new JTextField(currentEntry.getFieldDataList().get(0));
			textField_1.setBounds(250, 40, 200, 18);
			textField_1.setColumns(10);

			textField_2 = new JTextField(currentEntry.getFieldDataList().get(1));
			textField_2.setBounds(250, 70, 200, 18);
			textField_2.setColumns(10);

			textField_3 = new JTextField(currentEntry.getFieldDataList().get(2));
			textField_3.setBounds(250, 100, 200, 18);
			textField_3.setColumns(10);

			textField_4 = new JTextField(currentEntry.getFieldDataList().get(3));
			textField_4.setBounds(250, 130, 200, 18);
			textField_4.setColumns(10);

			textField_5 = new JTextField(currentEntry.getFieldDataList().get(4));
			textField_5.setBounds(250, 160, 200, 18);
			textField_5.setColumns(10);

			textField_6 = new JTextField(currentEntry.getFieldDataList().get(5));
			textField_6.setBounds(250, 190, 200, 18);
			textField_6.setColumns(10);

			DecimalFormat formatter = new DecimalFormat("00");

			String[] s = new String[60];
			for (int i = 0; i < 60; i++) {
				s[i] = formatter.format(i);
			}

			JComboBox hour = new JComboBox();
			hour.setModel(new DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08",
					"09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
			hour.setBounds(230, 220, 66, 18);
			hour.setSelectedItem(currentEntry.getFieldDataList().get(6).substring(0, 2));

			JComboBox min = new JComboBox();
			min.setModel(new DefaultComboBoxModel(s));
			min.setBounds(297, 220, 66, 18);
			min.setSelectedItem(currentEntry.getFieldDataList().get(6).substring(3, 5));

			JLabel colon = new JLabel(":");
			colon.setBounds(296, 220, 5, 18);

			JComboBox date = new JComboBox();
			date.setModel(new DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09",
					"10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25",
					"26", "27", "28", "29", "30", "31" }));
			date.setBounds(437, 220, 66, 18);
			date.setSelectedItem(currentEntry.getFieldDataList().get(6).substring(10,12));
			
			JComboBox month = new JComboBox();
			month.setModel(new DefaultComboBoxModel(new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
					"Aug", "Sep", "Oct", "Nov", "Dec" }));
			month.setBounds(362, 220, 75, 18);
			month.setSelectedItem(currentEntry.getFieldDataList().get(6).substring(6,9));

			JComboBox year = new JComboBox();
			year.setModel(new DefaultComboBoxModel(new String[] { "2015", "2016", "2017", "2018", "2019", "2020",
					"2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));
			year.setBounds(503, 220, 85, 18);
			year.setSelectedItem(currentEntry.getFieldDataList().get(6).substring(13));

			JComboBox hour_1 = new JComboBox();
			hour_1.setModel(new DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07",
					"08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
			hour_1.setBounds(230, 250, 66, 18);
			hour_1.setSelectedItem(currentEntry.getFieldDataList().get(7).substring(0, 2));


			JComboBox min_1 = new JComboBox();
			min_1.setModel(new DefaultComboBoxModel(s));
			min_1.setBounds(297, 250, 66, 18);
			min_1.setSelectedItem(currentEntry.getFieldDataList().get(7).substring(3, 5));

			JLabel colon_1 = new JLabel(":");
			colon_1.setBounds(296, 250, 5, 18);

			JComboBox date_1 = new JComboBox();
			date_1.setModel(new DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08",
					"09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24",
					"25", "26", "27", "28", "29", "30", "31" }));
			date_1.setBounds(437, 250, 66, 18);
			date_1.setSelectedItem(currentEntry.getFieldDataList().get(7).substring(10,12));

			JComboBox month_1 = new JComboBox();
			month_1.setModel(new DefaultComboBoxModel(new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
					"Aug", "Sep", "Oct", "Nov", "Dec" }));
			month_1.setBounds(362, 250, 75, 18);
			month_1.setSelectedItem(currentEntry.getFieldDataList().get(7).substring(6,9));

			JComboBox year_1 = new JComboBox();
			year_1.setModel(new DefaultComboBoxModel(new String[] { "2015", "2016", "2017", "2018", "2019", "2020",
					"2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));
			year_1.setBounds(503, 250, 85, 18);
			year_1.setSelectedItem(currentEntry.getFieldDataList().get(7).substring(13));
			
			textField_7 = new JTextField(currentEntry.getFieldDataList().get(8));
			textField_7.setBounds(250, 280, 200, 18);
			textField_7.setColumns(10);

			textField_8 = new JTextField(currentEntry.getFieldDataList().get(9));
			textField_8.setBounds(250, 310, 200, 18);
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
			frame.getContentPane().add(label_9);
			frame.getContentPane().add(label_10);
			frame.getContentPane().add(textField);
			frame.getContentPane().add(textField_1);
			frame.getContentPane().add(textField_2);
			frame.getContentPane().add(textField_3);
			frame.getContentPane().add(textField_4);
			frame.getContentPane().add(textField_5);
			frame.getContentPane().add(textField_6);
			frame.getContentPane().add(textField_7);
			frame.getContentPane().add(textField_8);
			frame.getContentPane().add(hour);
			frame.getContentPane().add(colon);
			frame.getContentPane().add(min);
			frame.getContentPane().add(date);
			frame.getContentPane().add(month);
			frame.getContentPane().add(year);
			frame.getContentPane().add(hour_1);
			frame.getContentPane().add(colon_1);
			frame.getContentPane().add(min_1);
			frame.getContentPane().add(date_1);
			frame.getContentPane().add(month_1);
			frame.getContentPane().add(year_1);

			frame.setLocationRelativeTo(null);
			
			JButton btnSave = new JButton("Save");
			btnSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (textField.getText().equals("") || textField_1.getText().equals("")
							|| textField_2.getText().equals("") || textField_3.getText().equals("")
							|| textField_4.getText().equals("") || textField_5.getText().equals("")
							|| textField_6.getText().equals("") || textField_7.getText().equals("")
							|| textField_8.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Please complete all the fields!");
						return;
					}
					LocalDateTime createdtime = LocalDateTime.now();
					DataEntry newEntry = new DataEntry(textField.getText(), "Flight Ticket", currentEntry.getEncryptionKey(),
							currentUser.getUsername(), 0, createdtime);
					newEntry.addDataField(textField_1.getText());
					newEntry.addDataField(textField_2.getText());
					newEntry.addDataField(textField_3.getText());
					newEntry.addDataField(textField_4.getText());
					newEntry.addDataField(textField_5.getText());
					newEntry.addDataField(textField_6.getText());
					newEntry.addDataField(hour.getSelectedItem() + ":" + min.getSelectedItem() + "/"
							+ month.getSelectedItem() + "/" + date.getSelectedItem() + "/"
							+ year.getSelectedItem());
					newEntry.addDataField(hour_1.getSelectedItem() + ":" + min_1.getSelectedItem() + "/"
							+ month_1.getSelectedItem() + "/" + date_1.getSelectedItem() + "/"
							+ year_1.getSelectedItem());
					newEntry.addDataField(textField_7.getText());
					newEntry.addDataField(textField_8.getText());

					DatabaseManager m = new DatabaseManager("vault_database");
					int result = m.updateEntry(currentUser, currentEntry, newEntry);
					if (result == -1) {
						JOptionPane.showMessageDialog(null,
								"This entry name already exists! Please choose a different entry name!");
						return;
					} else {
						JOptionPane.showMessageDialog(null, "You have successfully modified DataEntry");
						System.gc();
						for (Window window : Window.getWindows()) {
							window.dispose();
						}

						frame.dispose();

						HomeView hv = new HomeView(currentUser.getUsername());
						hv.frmSentinelDataVault.setVisible(true);

					}
				}
			});
			btnSave.setBounds(85, 347, 117, 29);
			frame.getContentPane().add(btnSave);

			JButton btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					frame.dispose();

				}
			});
			btnCancel.setBounds(305, 347, 117, 29);
			frame.getContentPane().add(btnCancel);
		}

		// License
		if (entryType.equals("License")) {
			JLabel label, label_1, label_2, label_3, label_4, label_5;
			JTextField textField, textField_1, textField_2, textField_3, textField_4, textField_5;
			label = new JLabel("Entry Name");
			label.setBounds(40, 10, 150, 18);

			label_1 = new JLabel("License Number");
			label_1.setBounds(40, 40, 150, 18);

			label_2 = new JLabel("Name");
			label_2.setBounds(40, 70, 150, 18);

			label_3 = new JLabel("Address");
			label_3.setBounds(40, 100, 150, 18);

			label_4 = new JLabel("Date of Birth");
			label_4.setBounds(40, 130, 150, 18);

			label_5 = new JLabel("Expired Date");
			label_5.setBounds(40, 160, 150, 18);

			textField = new JTextField(currentEntry.getEntryName());
			textField.setBounds(170, 10, 200, 18);
			textField.setColumns(10);

			textField_1 = new JTextField(currentEntry.getFieldDataList().get(0));
			textField_1.setBounds(170, 40, 200, 18);
			textField_1.setColumns(10);

			textField_2 = new JTextField(currentEntry.getFieldDataList().get(1));
			textField_2.setBounds(170, 70, 200, 18);
			textField_2.setColumns(10);

			textField_3 = new JTextField(currentEntry.getFieldDataList().get(2));
			textField_3.setBounds(170, 100, 200, 18);
			textField_3.setColumns(10);

			textField_4 = new JTextField(currentEntry.getFieldDataList().get(3));
			textField_4.setBounds(170, 130, 200, 18);
			textField_4.setColumns(10);

			textField_5 = new JTextField(currentEntry.getFieldDataList().get(4));
			textField_5.setBounds(170, 160, 200, 18);
			textField_5.setColumns(10);

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
			frame.getContentPane().add(textField_5);

			JButton btnSave = new JButton("Save");
			btnSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (textField.getText().equals("") || textField_1.getText().equals("")
							|| textField_2.getText().equals("") || textField_3.getText().equals("")
							|| textField_4.getText().equals("") || textField_5.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Please complete all the fields!");
						return;
					}
					LocalDateTime createdtime = LocalDateTime.now();
					DataEntry newEntry = new DataEntry(textField.getText(), "License", currentEntry.getEncryptionKey(),
							currentEntry.getOwner(), 0, createdtime);
					newEntry.addDataField(textField_1.getText());
					newEntry.addDataField(textField_2.getText());
					newEntry.addDataField(textField_3.getText());
					newEntry.addDataField(textField_4.getText());
					newEntry.addDataField(textField_5.getText());
					DatabaseManager m = new DatabaseManager("vault_database");
					int result = m.updateEntry(currentUser, currentEntry, newEntry);
					if (result == -1) {
						JOptionPane.showMessageDialog(null,
								"This entry name already exists! Please choose a different entry name!");
						return;
					} else {

						JOptionPane.showMessageDialog(null, "You have successfully modified this data entry!");
						System.gc();
						for (Window window : Window.getWindows()) {
							window.dispose();
						}

						frame.dispose();

						HomeView hv = new HomeView(currentEntry.getOwner());
						hv.frmSentinelDataVault.setVisible(true);

					}
				}
			});
			btnSave.setBounds(85, 307, 117, 29);
			frame.getContentPane().add(btnSave);

			JButton btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					frame.dispose();

				}
			});
			btnCancel.setBounds(305, 307, 117, 29);
			frame.getContentPane().add(btnCancel);

		}

		if (entryType.equals("Account Login"))

		{
			JLabel label, label_1, label_2, label_3;
			JTextField textField, textField_1, textField_2, textField_3;
			label = new JLabel("Entry Name");
			label.setBounds(40, 10, 150, 18);

			label_1 = new JLabel("User Name");
			label_1.setBounds(40, 40, 150, 18);

			label_2 = new JLabel("Password");
			label_2.setBounds(40, 70, 150, 18);

			label_3 = new JLabel("Web URL");
			label_3.setBounds(40, 100, 150, 18);

			textField = new JTextField(currentEntry.getEntryName());
			textField.setBounds(170, 10, 200, 18);
			textField.setColumns(10);

			textField_1 = new JTextField(currentEntry.getFieldDataList().get(0));
			textField_1.setBounds(170, 40, 200, 18);
			textField_1.setColumns(10);

			textField_2 = new JTextField(currentEntry.getFieldDataList().get(1));
			textField_2.setBounds(170, 70, 200, 18);
			textField_2.setColumns(10);

			textField_3 = new JTextField(currentEntry.getFieldDataList().get(2));
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

			JButton btnSave = new JButton("Save");
			btnSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (textField.getText().equals("") || textField_1.getText().equals("")
							|| textField_2.getText().equals("") || textField_3.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Please complete all the fields!");
						return;
					}
					LocalDateTime createdtime = LocalDateTime.now();
					DataEntry newEntry = new DataEntry(textField.getText(), "Account Login",
							currentEntry.getEncryptionKey(), currentEntry.getOwner(), 0, createdtime);
					newEntry.addDataField(textField_1.getText());
					newEntry.addDataField(textField_2.getText());
					newEntry.addDataField(textField_3.getText());

					DatabaseManager m = new DatabaseManager("vault_database");
					int result = m.updateEntry(currentUser, currentEntry, newEntry);
					if (result == -1) {
						JOptionPane.showMessageDialog(null,
								"This entry name already exists! Please choose a different entry name!");
						return;
					} else {
						JOptionPane.showMessageDialog(null, "You have successfully modified this data entry!");
						System.gc();
						for (Window window : Window.getWindows()) {
							window.dispose();
						}

						frame.dispose();

						HomeView hv = new HomeView(currentEntry.getOwner());
						hv.frmSentinelDataVault.setVisible(true);

					}
				}
			});
			btnSave.setBounds(85, 307, 117, 29);
			frame.getContentPane().add(btnSave);

			JButton btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					frame.dispose();

				}
			});
			btnCancel.setBounds(305, 307, 117, 29);
			frame.getContentPane().add(btnCancel);
		}

		if (entryType.equals("Wifi Network"))

		{
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

			textField = new JTextField(currentEntry.getEntryName());
			textField.setBounds(170, 10, 200, 18);
			textField.setColumns(10);

			textField_1 = new JTextField(currentEntry.getFieldDataList().get(0));
			textField_1.setBounds(170, 40, 200, 18);
			textField_1.setColumns(10);

			textField_2 = new JTextField(currentEntry.getFieldDataList().get(1));
			textField_2.setBounds(170, 70, 200, 18);
			textField_2.setColumns(10);

			textField_3 = new JTextField(currentEntry.getFieldDataList().get(2));
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

			JButton btnSave = new JButton("Save");
			btnSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (textField.getText().equals("") || textField_1.getText().equals("")
							|| textField_2.getText().equals("") || textField_3.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Please complete all the fields!");
						return;
					}
					LocalDateTime createdtime = LocalDateTime.now();
					DataEntry newEntry = new DataEntry(textField.getText(), "Wifi Network",
							currentEntry.getEncryptionKey(), currentEntry.getOwner(), 0, createdtime);
					newEntry.addDataField(textField_1.getText());
					newEntry.addDataField(textField_2.getText());
					newEntry.addDataField(textField_3.getText());
					DatabaseManager m = new DatabaseManager("vault_database");
					int result = m.updateEntry(currentUser, currentEntry, newEntry);
					if (result == -1) {
						JOptionPane.showMessageDialog(null,
								"This entry name already exists! Please choose a different entry name!");
						return;
					} else {
						JOptionPane.showMessageDialog(null, "You have successfully modified this data entry!");
						System.gc();
						for (Window window : Window.getWindows()) {
							window.dispose();
						}

						frame.dispose();

						HomeView hv = new HomeView(currentEntry.getOwner());
						hv.frmSentinelDataVault.setVisible(true);

					}
				}
			});
			btnSave.setBounds(85, 307, 117, 29);
			frame.getContentPane().add(btnSave);

			JButton btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					frame.dispose();

				}
			});
			btnCancel.setBounds(305, 307, 117, 29);
			frame.getContentPane().add(btnCancel);
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

			textField = new JTextField(currentEntry.getEntryName());
			textField.setBounds(170, 10, 200, 18);
			textField.setColumns(10);

			textField_1 = new JTextField(currentEntry.getFieldDataList().get(0));
			textField_1.setBounds(170, 40, 200, 18);
			textField_1.setColumns(10);

			textField_2 = new JTextField(currentEntry.getFieldDataList().get(1));
			textField_2.setBounds(170, 70, 200, 18);
			textField_2.setColumns(10);

			textField_3 = new JTextField(currentEntry.getFieldDataList().get(2));
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

			JButton btnSave = new JButton("Save");
			btnSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (textField.getText().equals("") || textField_1.getText().equals("")
							|| textField_2.getText().equals("") || textField_3.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Please complete all the fields!");
						return;
					}
					LocalDateTime createdtime = LocalDateTime.now();
					DataEntry newEntry = new DataEntry(textField.getText(), "Phone Number",
							currentEntry.getEncryptionKey(), currentEntry.getOwner(), 0, createdtime);
					newEntry.addDataField(textField_1.getText());
					newEntry.addDataField(textField_2.getText());
					newEntry.addDataField(textField_3.getText());
					DatabaseManager m = new DatabaseManager("vault_database");
					int result = m.updateEntry(currentUser, currentEntry, newEntry);
					if (result == -1) {
						JOptionPane.showMessageDialog(null,
								"This entry name already exists! Please choose a different entry name!");
						return;
					} else {
						JOptionPane.showMessageDialog(null, "You have successfully modified this data entry!");
						System.gc();
						for (Window window : Window.getWindows()) {
							window.dispose();
						}

						frame.dispose();

						HomeView hv = new HomeView(currentEntry.getOwner());
						hv.frmSentinelDataVault.setVisible(true);

					}
				}
			});
			btnSave.setBounds(85, 207, 117, 29);
			frame.getContentPane().add(btnSave);

			JButton btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					frame.dispose();
				}
			});
			btnCancel.setBounds(305, 207, 117, 29);
			frame.getContentPane().add(btnCancel);
		}

	}

}
