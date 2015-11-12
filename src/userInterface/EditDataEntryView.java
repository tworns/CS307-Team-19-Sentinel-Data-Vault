package userInterface;

import java.awt.EventQueue;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class EditDataEntryView {

	private JFrame frame;
	private DataEntry currentEntry;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DatabaseManager m = new DatabaseManager();
					DataEntry d = m.retrieveOneDataEntry("myvisa", "default", "Credit/Debit Card");
					EditDataEntryView window = new EditDataEntryView(d);
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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
					LocalDateTime createdtime = LocalDateTime.now();
					DataEntry newEntry = new DataEntry(textField.getText(), "SSN", "key", currentEntry.getOwner(), 0,
							createdtime);
					newEntry.addDataField(textField_1.getText());
					newEntry.addDataField(textField_2.getText());
					DatabaseManager m = new DatabaseManager();
					int result = m.updateEntry(currentEntry, newEntry);
					if (result == -1) {
						JOptionPane.showMessageDialog(null,
								"The Entry Name has already existed! Please Try another Entry Name!");
						return;
					} else {

						JOptionPane.showMessageDialog(null, "You have successfully Added DataEntry");
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
					LocalDateTime modifytime = LocalDateTime.now();
					DataEntry newEntry = new DataEntry(textField.getText(), "Credit/Debit Card", "key",
							currentEntry.getOwner(), 0, modifytime);
					newEntry.addDataField((String) cardType.getSelectedItem());
					newEntry.addDataField(textField_1.getText());
					newEntry.addDataField(textField_2.getText());
					newEntry.addDataField(textField_3.getText());
					newEntry.addDataField(((String) month.getSelectedItem()) + "/" + ((String) year.getSelectedItem()));
					DatabaseManager m = new DatabaseManager();
					int result = m.updateEntry(currentEntry, newEntry);
					if (result == -1) {
						JOptionPane.showMessageDialog(null,
								"The Entry Name has already existed! Please Try another Entry Name!");
						return;
					} else {
						frame.dispose();
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

		/*
		 * if (entryType.equals("Passport")) { JLabel label, label_1, label_2,
		 * label_3, label_4, label_5, label_6, label_7, label_8; JTextField
		 * textField, textField_1, textField_2, textField_3, textField_4,
		 * textField_5, textField_6, textField_7, textField_8;
		 * 
		 * label = new JLabel("Entry Name"); label.setBounds(40, 10, 150, 18);
		 * 
		 * label_1 = new JLabel("Name"); label_1.setBounds(40, 40, 150, 18);
		 * 
		 * label_2 = new JLabel("Passport Number"); label_2.setBounds(40, 70,
		 * 150, 18);
		 * 
		 * label_3 = new JLabel("Nationality"); label_3.setBounds(40, 100, 150,
		 * 18);
		 * 
		 * label_4 = new JLabel("Sex"); label_4.setBounds(40, 130, 150, 18);
		 * 
		 * label_5 = new JLabel("Date of Birth"); label_5.setBounds(40, 160,
		 * 150, 18);
		 * 
		 * label_6 = new JLabel("Place of Birth"); label_6.setBounds(40, 190,
		 * 150, 18);
		 * 
		 * label_7 = new JLabel("Issued Date"); label_7.setBounds(40, 220, 150,
		 * 18);
		 * 
		 * label_8 = new JLabel("Expired Date"); label_8.setBounds(40, 250, 150,
		 * 18);
		 * 
		 * textField = new JTextField(currentEntry.getEntryName());
		 * textField.setBounds(170, 10, 200, 18); textField.setColumns(10);
		 * 
		 * textField_1 = new JTextField(currentEntry.getFieldDataList().get(0));
		 * textField_1.setBounds(170, 40, 200, 18); textField_1.setColumns(10);
		 * 
		 * textField_2 = new JTextField(currentEntry.getFieldDataList().get(1));
		 * textField_2.setBounds(170, 70, 200, 18); textField_2.setColumns(10);
		 * 
		 * textField_3 = new JTextField(currentEntry.getFieldDataList().get(2));
		 * textField_3.setBounds(170, 100, 200, 18); textField_3.setColumns(10);
		 * 
		 * textField_4 = new JTextField(currentEntry.getFieldDataList().get(3));
		 * textField_4.setBounds(170, 130, 200, 18); textField_4.setColumns(10);
		 * 
		 * textField_5 = new JTextField(currentEntry.getFieldDataList().get(4));
		 * textField_5.setBounds(170, 160, 200, 18); textField_5.setColumns(10);
		 * 
		 * textField_6 = new JTextField(currentEntry.getFieldDataList().get(5));
		 * textField_6.setBounds(170, 190, 200, 18); textField_6.setColumns(10);
		 * 
		 * textField_7 = new JTextField(currentEntry.getFieldDataList().get(6));
		 * textField_7.setBounds(170, 220, 200, 18); textField_7.setColumns(10);
		 * 
		 * textField_8 = new JTextField(currentEntry.getFieldDataList().get(7));
		 * textField_8.setBounds(170, 250, 200, 18); textField_8.setColumns(10);
		 * 
		 * frame.getContentPane().setLayout(null);
		 * frame.getContentPane().add(label);
		 * frame.getContentPane().add(label_1);
		 * frame.getContentPane().add(label_2);
		 * frame.getContentPane().add(label_3);
		 * frame.getContentPane().add(label_4);
		 * frame.getContentPane().add(label_5);
		 * frame.getContentPane().add(label_6);
		 * frame.getContentPane().add(label_7);
		 * frame.getContentPane().add(label_8);
		 * frame.getContentPane().add(textField);
		 * frame.getContentPane().add(textField_1);
		 * frame.getContentPane().add(textField_2);
		 * frame.getContentPane().add(textField_3);
		 * frame.getContentPane().add(textField_4);
		 * frame.getContentPane().add(textField_5);
		 * frame.getContentPane().add(textField_6);
		 * frame.getContentPane().add(textField_7);
		 * frame.getContentPane().add(textField_8); }
		 */

		if (entryType.equals("License"))

		{
			JLabel label, label_1, label_2, label_3, label_4;
			JTextField textField, textField_1, textField_2, textField_3, textField_4;
			label = new JLabel("Entry Name");
			label.setBounds(40, 10, 150, 18);

			label_1 = new JLabel("Name");
			label_1.setBounds(40, 40, 150, 18);

			label_2 = new JLabel("Address");
			label_2.setBounds(40, 70, 150, 18);

			label_3 = new JLabel("Date of Birth");
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

			JButton btnSave = new JButton("Save");
			btnSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					LocalDateTime createdtime = LocalDateTime.now();
					DataEntry newEntry = new DataEntry(textField.getText(), "License", "key", currentEntry.getOwner(),
							0, createdtime);
					newEntry.addDataField(textField_1.getText());
					newEntry.addDataField(textField_2.getText());
					newEntry.addDataField(textField_3.getText());
					newEntry.addDataField(textField_4.getText());
					DatabaseManager m = new DatabaseManager();
					int result = m.updateEntry(currentEntry, newEntry);
					if (result == -1) {
						JOptionPane.showMessageDialog(null,
								"The Entry Name has already existed! Please Try another Entry Name!");
						return;
					} else {

						JOptionPane.showMessageDialog(null, "You have successfully Added DataEntry");
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
					LocalDateTime createdtime = LocalDateTime.now();
					DataEntry newEntry = new DataEntry(textField.getText(), "Account Login", "key",
							currentEntry.getOwner(), 0, createdtime);
					newEntry.addDataField(textField_1.getText());
					newEntry.addDataField(textField_2.getText());
					newEntry.addDataField(textField_3.getText());

					DatabaseManager m = new DatabaseManager();
					int result = m.updateEntry(currentEntry, newEntry);
					if (result == -1) {
						JOptionPane.showMessageDialog(null,
								"The Entry Name has already existed! Please Try another Entry Name!");
						return;
					} else {
						JOptionPane.showMessageDialog(null, "You have successfully Added DataEntry");
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
					LocalDateTime createdtime = LocalDateTime.now();
					DataEntry newEntry = new DataEntry(textField.getText(), "Wifi Network", "key",
							currentEntry.getOwner(), 0, createdtime);
					newEntry.addDataField(textField_1.getText());
					newEntry.addDataField(textField_2.getText());
					newEntry.addDataField(textField_3.getText());
					DatabaseManager m = new DatabaseManager();
					int result = m.updateEntry(currentEntry, newEntry);
					if (result == -1) {
						JOptionPane.showMessageDialog(null,
								"The Entry Name has already existed! Please Try another Entry Name!");
						return;
					} else {
						JOptionPane.showMessageDialog(null, "You have successfully Edit DataEntry");
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
					LocalDateTime createdtime = LocalDateTime.now();
					DataEntry newEntry = new DataEntry(textField.getText(), "Phone Number", "key",
							currentEntry.getOwner(), 0, createdtime);
					newEntry.addDataField(textField_1.getText());
					newEntry.addDataField(textField_2.getText());
					newEntry.addDataField(textField_3.getText());
					DatabaseManager m = new DatabaseManager();
					int result = m.updateEntry(currentEntry, newEntry);
					if (result == -1) {
						JOptionPane.showMessageDialog(null,
								"The Entry Name has already existed! Please Try another Entry Name!");
						return;
					} else {
						JOptionPane.showMessageDialog(null, "You have successfully Added DataEntry");
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
