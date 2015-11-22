package userInterface;

import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.List;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import java.awt.Button;
import java.awt.TextField;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import java.time.LocalDateTime;

import javax.swing.JTextField;

import controllers.DatabaseManager;
import dataManagement.DataEntry;
import dataManagement.User;
import userInterface.HomeView;

public class NewDataEntryView {

	private JFrame frame;
	private static Choice choice;
	private static JPanel panel_1;
	private User currentUser;
	private String userName;

	public NewDataEntryView(User currentUser) {
		this.currentUser = currentUser;
		this.userName = currentUser.getUsername();
		initialize();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User d = new User("defult", null, null, null, null, null, null);
					NewDataEntryView window = new NewDataEntryView(d);
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
	public NewDataEntryView() {
		initialize();
	}

	public JFrame getJframe() {
		return this.frame;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Choose New Date Entry Type");
		frame.setBounds(150, 150, 300, 200);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);

		choice = new Choice();
		choice.addItem("Account Login");
		choice.addItem("Confirmation Number");
		choice.addItem("Credit/Debit Card");
		choice.addItem("Entry Code");
		choice.addItem("Flight ticket");
		choice.addItem("General Password");
		choice.addItem("ID Card");
		choice.addItem("License");
		choice.addItem("Passport");
		choice.addItem("Phone Number");
		choice.addItem("Serial Number");
		choice.addItem("Shipment Tracking Number");
		choice.addItem("SSN");
		choice.addItem("Wifi Network");

		panel.add(choice);

		panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		choice.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					int index = choice.getSelectedIndex();
					if (index == 0) {
						frame.setTitle("New Account Login");
						frame.getContentPane().removeAll();

						frame.revalidate();
						frame.repaint();
						frame.setBounds(150, 150, 550, 450);
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						frame.setLocationRelativeTo(null);
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

						textField = new JTextField();
						textField.setBounds(250, 10, 200, 18);
						textField.setColumns(10);

						textField_1 = new JTextField();
						textField_1.setBounds(250, 40, 200, 18);
						textField_1.setColumns(10);

						textField_2 = new JTextField();
						textField_2.setBounds(250, 70, 200, 18);
						textField_2.setColumns(10);

						textField_3 = new JTextField();
						textField_3.setBounds(250, 100, 200, 18);
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
						frame.setLocationRelativeTo(null);
						JButton btnSave = new JButton("Save");
						btnSave.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (textField.getText().equals("") || textField_1.getText().equals("")
										|| textField_2.getText().equals("") || textField_3.getText().equals("")) {
									JOptionPane.showMessageDialog(null, "Please fill all the field!");
									return;
								}
								LocalDateTime createdtime = LocalDateTime.now();
								DataEntry newEntry = new DataEntry(textField.getText(), "Account Login", "key",
										userName, 0, createdtime);
								newEntry.addDataField(textField_1.getText());
								newEntry.addDataField(textField_2.getText());
								newEntry.addDataField(textField_3.getText());
								newEntry.addDataField("");
								newEntry.addDataField("");
								newEntry.addDataField("");
								newEntry.addDataField("");
								newEntry.addDataField("");
								newEntry.addDataField("");
								newEntry.addDataField("");

								DatabaseManager m = new DatabaseManager();
								int result = m.addEntryToDatabase(currentUser, newEntry);
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

									HomeView hv = new HomeView(userName);
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

					// Confirmation Number
					else if (index == 1) {
						frame.setTitle("New Confirmation Number");
						frame.getContentPane().removeAll();

						frame.revalidate();
						frame.repaint();
						frame.setBounds(150, 150, 550, 450);
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						frame.setLocationRelativeTo(null);
						JLabel label, label_1, label_2;
						JTextField textField, textField_1, textField_2;
						label = new JLabel("Entry Name");
						label.setBounds(40, 10, 150, 18);

						label_1 = new JLabel("Confirmation Name");
						label_1.setBounds(40, 40, 150, 18);

						label_2 = new JLabel("Confirmation Number");
						label_2.setBounds(40, 70, 150, 18);

						textField = new JTextField();
						textField.setBounds(250, 10, 200, 18);
						textField.setColumns(10);

						textField_1 = new JTextField();
						textField_1.setBounds(250, 40, 200, 18);
						textField_1.setColumns(10);

						textField_2 = new JTextField();
						textField_2.setBounds(250, 70, 200, 18);
						textField_2.setColumns(10);

						frame.getContentPane().setLayout(null);
						frame.getContentPane().add(label);
						frame.getContentPane().add(label_1);
						frame.getContentPane().add(label_2);
						frame.getContentPane().add(textField);
						frame.getContentPane().add(textField_1);
						frame.getContentPane().add(textField_2);
						frame.setLocationRelativeTo(null);
						JButton btnSave = new JButton("Save");
						btnSave.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (textField.getText().equals("") || textField_1.getText().equals("")
										|| textField_2.getText().equals("")) {
									JOptionPane.showMessageDialog(null, "Please fill all the field!");
									return;
								}
								LocalDateTime createdtime = LocalDateTime.now();
								DataEntry newEntry = new DataEntry(textField.getText(), "Confirmation Number", "key",
										userName, 0, createdtime);
								newEntry.addDataField(textField_1.getText());
								newEntry.addDataField(textField_2.getText());

								DatabaseManager m = new DatabaseManager();
								int result = m.addEntryToDatabase(currentUser, newEntry);
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

									HomeView hv = new HomeView(userName);
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

					// Credit/Debit Card
					else if (index == 2) {
						frame.setTitle("New Credit/Debit Card");
						frame.getContentPane().removeAll();

						frame.revalidate();
						frame.repaint();
						frame.setBounds(150, 150, 550, 450);
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						frame.setLocationRelativeTo(null);
						JLabel label, label_1, label_2, label_3, label_4, label_5;
						JComboBox cardType;
						JTextField textField, textField_1, textField_2, textField_3, textField_4;

						label = new JLabel("Entry Name");
						label.setBounds(40, 10, 150, 18);

						label_1 = new JLabel("Name on Card");
						label_1.setBounds(40, 100, 150, 18);

						label_2 = new JLabel("Card Type");
						label_2.setBounds(40, 40, 150, 18);

						label_3 = new JLabel("Card Number");
						label_3.setBounds(40, 70, 150, 18);

						label_4 = new JLabel("Expiration Date");
						label_4.setBounds(40, 160, 150, 18);

						label_5 = new JLabel("CVV");
						label_5.setBounds(40, 130, 150, 18);

						cardType = new JComboBox();
						cardType.setModel(new DefaultComboBoxModel(
								new String[] { "VISA", "MasterCard", "Discovery", "AMERICAN EXPRESS" }));
						cardType.setBounds(250, 40, 200, 18);

						textField = new JTextField();
						textField.setBounds(250, 10, 200, 18);
						textField.setColumns(10);

						textField_1 = new JTextField();
						textField_1.setBounds(250, 70, 200, 18);
						textField_1.setColumns(10);

						textField_2 = new JTextField();
						textField_2.setBounds(250, 100, 200, 18);
						textField_2.setColumns(10);

						textField_3 = new JTextField();
						textField_3.setBounds(250, 130, 200, 18);
						textField_3.setColumns(10);

						JComboBox month = new JComboBox();
						month.setModel(new DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07",
								"08", "09", "10", "11", "12" }));
						month.setBounds(250, 160, 90, 18);
						;

						JComboBox year = new JComboBox();
						year.setModel(new DefaultComboBoxModel(new String[] { "15", "16", "17", "18", "19", "20", "21",
								"22", "23", "24", "25", "26", "27", "28", "29", "30" }));
						year.setBounds(340, 160, 90, 18);

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

						frame.getContentPane().add(cardType);
						frame.getContentPane().add(month);
						frame.getContentPane().add(year);
						frame.setLocationRelativeTo(null);
						JButton btnSave = new JButton("Save");
						btnSave.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (textField.getText().equals("") || textField_1.getText().equals("")
										|| textField_2.getText().equals("") || textField_3.getText().equals("")) {
									JOptionPane.showMessageDialog(null, "Please fill all the field!");
									return;
								}
								LocalDateTime createdtime = LocalDateTime.now();
								DataEntry newEntry = new DataEntry(textField.getText(), "Credit/Debit Card", "key",
										userName, 0, createdtime);
								newEntry.addDataField((String) cardType.getSelectedItem());
								newEntry.addDataField(textField_1.getText());
								newEntry.addDataField(textField_2.getText());
								newEntry.addDataField(textField_3.getText());
								newEntry.addDataField(
										((String) month.getSelectedItem()) + "/" + ((String) year.getSelectedItem()));
								newEntry.addDataField("");
								newEntry.addDataField("");
								newEntry.addDataField("");
								newEntry.addDataField("");
								newEntry.addDataField("");

								DatabaseManager m = new DatabaseManager();
								int result = m.addEntryToDatabase(currentUser, newEntry);
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

									HomeView hv = new HomeView(userName);
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
					else if (index == 3) {
						frame.setTitle("New Entry Code");
						frame.getContentPane().removeAll();

						frame.revalidate();
						frame.repaint();
						frame.setBounds(150, 150, 550, 450);
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						frame.setLocationRelativeTo(null);
						JLabel label, label_1, label_2;
						JTextField textField, textField_1, textField_2;
						label = new JLabel("Entry Name");
						label.setBounds(40, 10, 150, 18);

						label_1 = new JLabel("Code Name");
						label_1.setBounds(40, 40, 150, 18);

						label_2 = new JLabel("Code");
						label_2.setBounds(40, 70, 150, 18);

						textField = new JTextField();
						textField.setBounds(250, 10, 200, 18);
						textField.setColumns(10);

						textField_1 = new JTextField();
						textField_1.setBounds(250, 40, 200, 18);
						textField_1.setColumns(10);

						textField_2 = new JTextField();
						textField_2.setBounds(250, 70, 200, 18);
						textField_2.setColumns(10);

						frame.getContentPane().setLayout(null);
						frame.getContentPane().add(label);
						frame.getContentPane().add(label_1);
						frame.getContentPane().add(label_2);
						frame.getContentPane().add(textField);
						frame.getContentPane().add(textField_1);
						frame.getContentPane().add(textField_2);
						frame.setLocationRelativeTo(null);
						JButton btnSave = new JButton("Save");
						btnSave.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (textField.getText().equals("") || textField_1.getText().equals("")
										|| textField_2.getText().equals("")) {
									JOptionPane.showMessageDialog(null, "Please fill all the field!");
									return;
								}
								LocalDateTime createdtime = LocalDateTime.now();
								DataEntry newEntry = new DataEntry(textField.getText(), "Entry Code", "key", userName,
										0, createdtime);
								newEntry.addDataField(textField_1.getText());
								newEntry.addDataField(textField_2.getText());

								DatabaseManager m = new DatabaseManager();
								int result = m.addEntryToDatabase(currentUser, newEntry);
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

									HomeView hv = new HomeView(userName);
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
					else if (index == 4) {
						frame.setTitle("New Flight Ticket");
						frame.getContentPane().removeAll();

						frame.revalidate();
						frame.repaint();
						frame.setBounds(150, 150, 600, 450);
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						frame.setLocationRelativeTo(null);
						JLabel label, label_1, label_2, label_3, label_4, label_5, label_6, label_7, label_8, label_9,
								label_10;
						JTextField textField, textField_1, textField_2, textField_3, textField_4, textField_5,
								textField_6, textField_7, textField_8;
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

						textField = new JTextField();
						textField.setBounds(250, 10, 200, 18);
						textField.setColumns(10);

						textField_1 = new JTextField();
						textField_1.setBounds(250, 40, 200, 18);
						textField_1.setColumns(10);

						textField_2 = new JTextField();
						textField_2.setBounds(250, 70, 200, 18);
						textField_2.setColumns(10);

						textField_3 = new JTextField();
						textField_3.setBounds(250, 100, 200, 18);
						textField_3.setColumns(10);

						textField_4 = new JTextField();
						textField_4.setBounds(250, 130, 200, 18);
						textField_4.setColumns(10);

						textField_5 = new JTextField();
						textField_5.setBounds(250, 160, 200, 18);
						textField_5.setColumns(10);

						textField_6 = new JTextField();
						textField_6.setBounds(250, 190, 200, 18);
						textField_6.setColumns(10);

						DecimalFormat formatter = new DecimalFormat("00");

						String[] s = new String[60];
						for (int i = 0; i < 60; i++) {
							s[i] = formatter.format(i);
						}

						JComboBox hour = new JComboBox();
						hour.setModel(new DefaultComboBoxModel(
								new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
										"12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
						hour.setBounds(230, 220, 66, 18);

						JComboBox min = new JComboBox();
						min.setModel(new DefaultComboBoxModel(s));
						min.setBounds(297, 220, 66, 18);

						JLabel colon = new JLabel(":");
						colon.setBounds(296, 220, 5, 18);

						JComboBox date = new JComboBox();
						date.setModel(new DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07",
								"08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21",
								"22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
						date.setBounds(437, 220, 66, 18);
						;
						JComboBox month = new JComboBox();
						month.setModel(new DefaultComboBoxModel(new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun",
								"Jul", "Aug", "Sep", "Oct", "Nov", "Dec" }));
						month.setBounds(362, 220, 75, 18);
						;

						JComboBox year = new JComboBox();
						year.setModel(new DefaultComboBoxModel(
								new String[] { "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023",
										"2024", "2025", "2026", "2027", "2028", "2029", "2030" }));
						year.setBounds(503, 220, 85, 18);

						JComboBox hour_1 = new JComboBox();
						hour_1.setModel(new DefaultComboBoxModel(
								new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
										"12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
						hour_1.setBounds(230, 250, 66, 18);

						JComboBox min_1 = new JComboBox();
						min_1.setModel(new DefaultComboBoxModel(s));
						min_1.setBounds(297, 250, 66, 18);

						JLabel colon_1 = new JLabel(":");
						colon_1.setBounds(296, 250, 5, 18);

						JComboBox date_1 = new JComboBox();
						date_1.setModel(new DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06",
								"07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
								"21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
						date_1.setBounds(437, 250, 66, 18);
						;
						JComboBox month_1 = new JComboBox();
						month_1.setModel(new DefaultComboBoxModel(new String[] { "Jan", "Feb", "Mar", "Apr", "May",
								"Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" }));
						month_1.setBounds(362, 250, 75, 18);
						;

						JComboBox year_1 = new JComboBox();
						year_1.setModel(new DefaultComboBoxModel(
								new String[] { "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023",
										"2024", "2025", "2026", "2027", "2028", "2029", "2030" }));
						year_1.setBounds(503, 250, 85, 18);

						textField_7 = new JTextField();
						textField_7.setBounds(250, 280, 200, 18);
						textField_7.setColumns(10);

						textField_8 = new JTextField();
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
									JOptionPane.showMessageDialog(null, "Please fill all the field!");
									return;
								}
								LocalDateTime createdtime = LocalDateTime.now();
								DataEntry newEntry = new DataEntry(textField.getText(), "Flight Ticket", "key",
										userName, 0, createdtime);
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

								DatabaseManager m = new DatabaseManager();
								int result = m.addEntryToDatabase(currentUser, newEntry);
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

									HomeView hv = new HomeView(userName);
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

					// General Password
					else if (index == 5) {
						frame.setTitle("New General Password");
						frame.getContentPane().removeAll();

						frame.revalidate();
						frame.repaint();
						frame.setBounds(150, 150, 550, 450);
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						frame.setLocationRelativeTo(null);
						JLabel label, label_1, label_2;
						JTextField textField, textField_1, textField_2;
						label = new JLabel("Entry Name");
						label.setBounds(40, 10, 150, 18);

						label_1 = new JLabel("Password Name");
						label_1.setBounds(40, 40, 150, 18);

						label_2 = new JLabel("Password");
						label_2.setBounds(40, 70, 150, 18);

						textField = new JTextField();
						textField.setBounds(250, 10, 200, 18);
						textField.setColumns(10);

						textField_1 = new JTextField();
						textField_1.setBounds(250, 40, 200, 18);
						textField_1.setColumns(10);

						textField_2 = new JTextField();
						textField_2.setBounds(250, 70, 200, 18);
						textField_2.setColumns(10);

						frame.getContentPane().setLayout(null);
						frame.getContentPane().add(label);
						frame.getContentPane().add(label_1);
						frame.getContentPane().add(label_2);
						frame.getContentPane().add(textField);
						frame.getContentPane().add(textField_1);
						frame.getContentPane().add(textField_2);
						frame.setLocationRelativeTo(null);
						JButton btnSave = new JButton("Save");
						btnSave.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (textField.getText().equals("") || textField_1.getText().equals("")
										|| textField_2.getText().equals("")) {
									JOptionPane.showMessageDialog(null, "Please fill all the field!");
									return;
								}
								LocalDateTime createdtime = LocalDateTime.now();
								DataEntry newEntry = new DataEntry(textField.getText(), "General Password", "key",
										userName, 0, createdtime);
								newEntry.addDataField(textField_1.getText());
								newEntry.addDataField(textField_2.getText());

								DatabaseManager m = new DatabaseManager();
								int result = m.addEntryToDatabase(currentUser, newEntry);
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

									HomeView hv = new HomeView(userName);
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

					// ID Card
					else if (index == 6) {
						frame.setTitle("New Flight Ticket");
						frame.getContentPane().removeAll();

						frame.revalidate();
						frame.repaint();
						frame.setBounds(150, 150, 550, 450);
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						frame.setLocationRelativeTo(null);
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

						textField = new JTextField();
						textField.setBounds(250, 10, 200, 18);
						textField.setColumns(10);

						textField_1 = new JTextField();
						textField_1.setBounds(250, 40, 200, 18);
						textField_1.setColumns(10);

						textField_2 = new JTextField();
						textField_2.setBounds(250, 70, 200, 18);
						textField_2.setColumns(10);

						textField_3 = new JTextField();
						textField_3.setBounds(250, 100, 200, 18);
						textField_3.setColumns(10);

						textField_4 = new JTextField();
						textField_4.setBounds(250, 130, 200, 18);
						textField_4.setColumns(10);

						JComboBox date = new JComboBox();
						date.setModel(new DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07",
								"08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21",
								"22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
						date.setBounds(325, 160, 66, 18);
						;
						JComboBox month = new JComboBox();
						month.setModel(new DefaultComboBoxModel(new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun",
								"Jul", "Aug", "Sep", "Oct", "Nov", "Dec" }));
						month.setBounds(250, 160, 75, 18);
						;

						JComboBox year = new JComboBox();
						year.setModel(new DefaultComboBoxModel(
								new String[] { "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023",
										"2024", "2025", "2026", "2027", "2028", "2029", "2030" }));
						year.setBounds(392, 160, 85, 18);

						JComboBox date_1 = new JComboBox();
						date_1.setModel(new DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06",
								"07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
								"21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
						date_1.setBounds(325, 190, 66, 18);
						;
						JComboBox month_1 = new JComboBox();
						month_1.setModel(new DefaultComboBoxModel(new String[] { "Jan", "Feb", "Mar", "Apr", "May",
								"Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" }));
						month_1.setBounds(250, 190, 75, 18);
						;

						JComboBox year_1 = new JComboBox();
						year_1.setModel(new DefaultComboBoxModel(
								new String[] { "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023",
										"2024", "2025", "2026", "2027", "2028", "2029", "2030" }));
						year_1.setBounds(392, 190, 85, 18);

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
									JOptionPane.showMessageDialog(null, "Please fill all the field!");
									return;
								}
								LocalDateTime createdtime = LocalDateTime.now();
								DataEntry newEntry = new DataEntry(textField.getText(), "ID Card", "key", userName, 0,
										createdtime);
								newEntry.addDataField(textField_1.getText());
								newEntry.addDataField(textField_2.getText());
								newEntry.addDataField(textField_3.getText());
								newEntry.addDataField(textField_4.getText());
								newEntry.addDataField(month.getSelectedItem() + "/" + date.getSelectedItem() + "/"
										+ year.getSelectedItem());
								newEntry.addDataField(month_1.getSelectedItem() + "/" + date_1.getSelectedItem() + "/"
										+ year_1.getSelectedItem());

								DatabaseManager m = new DatabaseManager();
								int result = m.addEntryToDatabase(currentUser, newEntry);
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

									HomeView hv = new HomeView(userName);
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
					else if (index == 7) {
						frame.setTitle("New License");
						frame.getContentPane().removeAll();

						frame.revalidate();
						frame.repaint();
						frame.setBounds(150, 150, 550, 450);
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						frame.setLocationRelativeTo(null);
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

						label_5 = new JLabel("Expiration Date");
						label_5.setBounds(40, 160, 150, 18);

						textField = new JTextField();
						textField.setBounds(250, 10, 200, 18);
						textField.setColumns(10);

						textField_1 = new JTextField();
						textField_1.setBounds(250, 40, 200, 18);
						textField_1.setColumns(10);

						textField_2 = new JTextField();
						textField_2.setBounds(250, 70, 200, 18);
						textField_2.setColumns(10);

						textField_3 = new JTextField();
						textField_3.setBounds(250, 100, 200, 18);
						textField_3.setColumns(10);

						textField_4 = new JTextField();
						textField_4.setBounds(250, 130, 200, 18);
						textField_4.setColumns(10);

						textField_5 = new JTextField();
						textField_5.setBounds(250, 160, 200, 18);
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
						frame.setLocationRelativeTo(null);
						JButton btnSave = new JButton("Save");
						btnSave.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (textField.getText().equals("") || textField_1.getText().equals("")
										|| textField_2.getText().equals("") || textField_3.getText().equals("")
										|| textField_4.getText().equals("") || textField_5.getText().equals("")) {
									JOptionPane.showMessageDialog(null, "Please fill all the field!");
									return;
								}
								LocalDateTime createdtime = LocalDateTime.now();
								DataEntry newEntry = new DataEntry(textField.getText(), "License", "key", userName, 0,
										createdtime);
								newEntry.addDataField(textField_1.getText());
								newEntry.addDataField(textField_2.getText());
								newEntry.addDataField(textField_3.getText());
								newEntry.addDataField(textField_4.getText());
								newEntry.addDataField(textField_5.getText());
								newEntry.addDataField("");
								newEntry.addDataField("");
								newEntry.addDataField("");
								newEntry.addDataField("");
								newEntry.addDataField("");
								DatabaseManager m = new DatabaseManager();
								int result = m.addEntryToDatabase(currentUser, newEntry);
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

									HomeView hv = new HomeView(userName);
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

					// Passport
					else if (index == 8) {
						frame.setTitle("New Passport");
						frame.getContentPane().removeAll();

						frame.revalidate();
						frame.repaint();
						frame.setBounds(150, 150, 550, 450);
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						frame.setLocationRelativeTo(null);
						JLabel label, label_1, label_2, label_3, label_4, label_5, label_6, label_7, label_8;
						JTextField textField, textField_1, textField_2, textField_3, textField_4, textField_5,
								textField_6, textField_7, textField_8;

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
						textField.setBounds(250, 10, 200, 18);
						textField.setColumns(10);

						textField_1 = new JTextField();
						textField_1.setBounds(250, 40, 200, 18);
						textField_1.setColumns(10);

						textField_2 = new JTextField();
						textField_2.setBounds(250, 70, 200, 18);
						textField_2.setColumns(10);

						textField_3 = new JTextField();
						textField_3.setBounds(250, 100, 200, 18);
						textField_3.setColumns(10);

						textField_4 = new JTextField();
						textField_4.setBounds(250, 130, 200, 18);
						textField_4.setColumns(10);

						textField_5 = new JTextField();
						textField_5.setBounds(250, 160, 200, 18);
						textField_5.setColumns(10);

						textField_6 = new JTextField();
						textField_6.setBounds(250, 190, 200, 18);
						textField_6.setColumns(10);

						textField_7 = new JTextField();
						textField_7.setBounds(250, 220, 200, 18);
						textField_7.setColumns(10);

						textField_8 = new JTextField();
						textField_8.setBounds(250, 250, 200, 18);
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
						frame.setLocationRelativeTo(null);
						JButton btnSave = new JButton("Save");
						btnSave.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (textField.getText().equals("") || textField_1.getText().equals("")
										|| textField_2.getText().equals("") || textField_3.getText().equals("")
										|| textField_4.getText().equals("") || textField_5.getText().equals("")
										|| textField_6.getText().equals("") || textField_7.getText().equals("")
										|| textField_8.getText().equals("")) {
									JOptionPane.showMessageDialog(null, "Please fill all the field!");
									return;
								}
								LocalDateTime createdtime = LocalDateTime.now();
								DataEntry newEntry = new DataEntry(textField.getText(), "PassPort", "key", userName, 0,
										createdtime);
								newEntry.addDataField(textField_1.getText());
								newEntry.addDataField(textField_2.getText());
								newEntry.addDataField(textField_3.getText());
								newEntry.addDataField(textField_4.getText());
								newEntry.addDataField(textField_5.getText());
								newEntry.addDataField(textField_6.getText());
								newEntry.addDataField(textField_7.getText());
								newEntry.addDataField(textField_8.getText());
								DatabaseManager m = new DatabaseManager();
								int result = m.addEntryToDatabase(currentUser, newEntry);
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

									HomeView hv = new HomeView(userName);
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

					// Phone Number
					else if (index == 9) {
						frame.setTitle("New Phone Number");
						frame.getContentPane().removeAll();

						frame.revalidate();
						frame.repaint();
						frame.setBounds(100, 100, 450, 300);
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						frame.setLocationRelativeTo(null);
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
						textField.setBounds(250, 10, 200, 18);
						textField.setColumns(10);

						textField_1 = new JTextField();
						textField_1.setBounds(250, 40, 200, 18);
						textField_1.setColumns(10);

						textField_2 = new JTextField();
						textField_2.setBounds(250, 70, 200, 18);
						textField_2.setColumns(10);

						textField_3 = new JTextField();
						textField_3.setBounds(250, 100, 200, 18);
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
						frame.setLocationRelativeTo(null);
						JButton btnSave = new JButton("Save");
						btnSave.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (textField.getText().equals("") || textField_1.getText().equals("")
										|| textField_2.getText().equals("") || textField_3.getText().equals("")) {
									JOptionPane.showMessageDialog(null, "Please fill all the field!");
									return;
								}
								LocalDateTime createdtime = LocalDateTime.now();
								DataEntry newEntry = new DataEntry(textField.getText(), "Phone Number", "key", userName,
										0, createdtime);
								newEntry.addDataField(textField_1.getText());
								newEntry.addDataField(textField_2.getText());
								newEntry.addDataField(textField_3.getText());
								newEntry.addDataField("");
								newEntry.addDataField("");
								newEntry.addDataField("");
								newEntry.addDataField("");
								newEntry.addDataField("");
								newEntry.addDataField("");
								newEntry.addDataField("");
								DatabaseManager m = new DatabaseManager();
								int result = m.addEntryToDatabase(currentUser, newEntry);
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

									HomeView hv = new HomeView(userName);
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

					// Serial Number
					else if (index == 10) {
						frame.setTitle("New Serial Number");
						frame.getContentPane().removeAll();

						frame.revalidate();
						frame.repaint();
						frame.setBounds(150, 150, 550, 450);
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						frame.setLocationRelativeTo(null);
						JLabel label, label_1, label_2;
						JTextField textField, textField_1, textField_2;
						label = new JLabel("Entry Name");
						label.setBounds(40, 10, 150, 18);

						label_1 = new JLabel("Product Name");
						label_1.setBounds(40, 40, 150, 18);

						label_2 = new JLabel("Serial Number");
						label_2.setBounds(40, 70, 150, 18);

						textField = new JTextField();
						textField.setBounds(250, 10, 200, 18);
						textField.setColumns(10);

						textField_1 = new JTextField();
						textField_1.setBounds(250, 40, 200, 18);
						textField_1.setColumns(10);

						textField_2 = new JTextField();
						textField_2.setBounds(250, 70, 200, 18);
						textField_2.setColumns(10);

						frame.getContentPane().setLayout(null);
						frame.getContentPane().add(label);
						frame.getContentPane().add(label_1);
						frame.getContentPane().add(label_2);
						frame.getContentPane().add(textField);
						frame.getContentPane().add(textField_1);
						frame.getContentPane().add(textField_2);
						frame.setLocationRelativeTo(null);
						JButton btnSave = new JButton("Save");
						btnSave.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (textField.getText().equals("") || textField_1.getText().equals("")
										|| textField_2.getText().equals("")) {
									JOptionPane.showMessageDialog(null, "Please fill all the field!");
									return;
								}
								LocalDateTime createdtime = LocalDateTime.now();
								DataEntry newEntry = new DataEntry(textField.getText(), "Serial Number", "key",
										userName, 0, createdtime);
								newEntry.addDataField(textField_1.getText());
								newEntry.addDataField(textField_2.getText());

								DatabaseManager m = new DatabaseManager();
								int result = m.addEntryToDatabase(currentUser, newEntry);
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

									HomeView hv = new HomeView(userName);
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

					// Shipment Tracking Number
					else if (index == 11) {
						frame.setTitle("Shipment Tracking Number");
						frame.getContentPane().removeAll();

						frame.revalidate();
						frame.repaint();
						frame.setBounds(150, 150, 550, 450);
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						frame.setLocationRelativeTo(null);
						JLabel label, label_1, label_2;
						JTextField textField, textField_1, textField_2;
						label = new JLabel("Entry Name");
						label.setBounds(40, 10, 150, 18);

						label_1 = new JLabel("Company/Item Name");
						label_1.setBounds(40, 40, 150, 18);

						label_2 = new JLabel("Shipment Tracking Number");
						label_2.setBounds(40, 70, 200, 18);

						textField = new JTextField();
						textField.setBounds(250, 10, 200, 18);
						textField.setColumns(10);

						textField_1 = new JTextField();
						textField_1.setBounds(250, 40, 200, 18);
						textField_1.setColumns(10);

						textField_2 = new JTextField();
						textField_2.setBounds(250, 70, 200, 18);
						textField_2.setColumns(10);

						frame.getContentPane().setLayout(null);
						frame.getContentPane().add(label);
						frame.getContentPane().add(label_1);
						frame.getContentPane().add(label_2);
						frame.getContentPane().add(textField);
						frame.getContentPane().add(textField_1);
						frame.getContentPane().add(textField_2);
						frame.setLocationRelativeTo(null);
						JButton btnSave = new JButton("Save");
						btnSave.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (textField.getText().equals("") || textField_1.getText().equals("")
										|| textField_2.getText().equals("")) {
									JOptionPane.showMessageDialog(null, "Please fill all the field!");
									return;
								}
								LocalDateTime createdtime = LocalDateTime.now();
								DataEntry newEntry = new DataEntry(textField.getText(), "Shipment Tracking Number",
										"key", userName, 0, createdtime);
								newEntry.addDataField(textField_1.getText());
								newEntry.addDataField(textField_2.getText());

								DatabaseManager m = new DatabaseManager();
								int result = m.addEntryToDatabase(currentUser, newEntry);
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

									HomeView hv = new HomeView(userName);
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

					// SSN
					else if (index == 12) {
						frame.setTitle("New SSN");

						frame.getContentPane().removeAll();

						frame.revalidate();
						frame.repaint();
						frame.setBounds(150, 150, 550, 450);
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						frame.setLocationRelativeTo(null);
						JLabel label, label_1, label_2;
						JTextField textField, textField_1, textField_2;
						label = new JLabel("Entry Name");
						label.setBounds(40, 10, 150, 18);

						label_1 = new JLabel("Name");
						label_1.setBounds(40, 40, 150, 18);

						label_2 = new JLabel("SSN");
						label_2.setBounds(40, 70, 150, 18);

						textField = new JTextField();
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

						JButton btnSave = new JButton("Save");
						btnSave.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (textField.getText().equals("") || textField_1.getText().equals("")
										|| textField_2.getText().equals("")) {
									JOptionPane.showMessageDialog(null, "Please fill all the field!");
									return;
								}
								LocalDateTime createdtime = LocalDateTime.now();
								DataEntry newEntry = new DataEntry(textField.getText(), "SSN", "key", userName, 0,
										createdtime);
								newEntry.addDataField(textField_1.getText());
								newEntry.addDataField(textField_2.getText());
								newEntry.addDataField("");// 3
								newEntry.addDataField("");// 4
								newEntry.addDataField("");// 5
								newEntry.addDataField("");// 6
								newEntry.addDataField("");// 7
								newEntry.addDataField("");// 8
								newEntry.addDataField("");// 9
								newEntry.addDataField("");// 10
								DatabaseManager m = new DatabaseManager();
								int result = m.addEntryToDatabase(currentUser, newEntry);
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

									HomeView hv = new HomeView(userName);
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

					else {
						frame.setTitle("New Wifi Network");
						frame.getContentPane().removeAll();

						frame.revalidate();
						frame.repaint();
						frame.setBounds(150, 150, 550, 450);
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						frame.setLocationRelativeTo(null);
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
						textField.setBounds(250, 10, 200, 18);
						textField.setColumns(10);

						textField_1 = new JTextField();
						textField_1.setBounds(250, 40, 200, 18);
						textField_1.setColumns(10);

						textField_2 = new JTextField();
						textField_2.setBounds(250, 70, 200, 18);
						textField_2.setColumns(10);

						textField_3 = new JTextField();
						textField_3.setBounds(250, 100, 200, 18);
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
						frame.setLocationRelativeTo(null);
						JButton btnSave = new JButton("Save");
						btnSave.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (textField.getText().equals("") || textField_1.getText().equals("")
										|| textField_2.getText().equals("") || textField_3.getText().equals("")) {
									JOptionPane.showMessageDialog(null, "Please fill all the field!");
									return;
								}
								LocalDateTime createdtime = LocalDateTime.now();
								DataEntry newEntry = new DataEntry(textField.getText(), "Wifi Network", "key", userName,
										0, createdtime);
								newEntry.addDataField(textField_1.getText());
								newEntry.addDataField(textField_2.getText());
								newEntry.addDataField(textField_3.getText());
								newEntry.addDataField("");
								newEntry.addDataField("");
								newEntry.addDataField("");
								newEntry.addDataField("");
								newEntry.addDataField("");
								newEntry.addDataField("");
								newEntry.addDataField("");
								DatabaseManager m = new DatabaseManager();
								int result = m.addEntryToDatabase(currentUser, newEntry);
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

									HomeView hv = new HomeView(userName);
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

				} else {

				}
			}
		});

	}

}
