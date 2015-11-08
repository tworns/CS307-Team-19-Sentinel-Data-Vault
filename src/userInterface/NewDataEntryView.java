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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDateTime;

import javax.swing.JTextField;

import controllers.DatabaseManager;
import dataManagement.DataEntry;
import dataManagement.User;

public class NewDataEntryView {

	private JFrame frame;
	private static Choice choice;
	private static JPanel panel_1;
	private String userName;

	public NewDataEntryView(String userName) {
		this.userName = userName;
		initialize();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewDataEntryView window = new NewDataEntryView();
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

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);

		choice = new Choice();
		choice.addItem("SSN");
		choice.addItem("Credit/Debit Card");
		choice.addItem("Passport");
		choice.addItem("Driver's License");
		choice.addItem("Website Logins");
		choice.addItem("Wifi Network");
		choice.addItem("Phone Number");
		panel.add(choice);

		panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		choice.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					int index = choice.getSelectedIndex();
					if (index == 0) {
						frame.setTitle("New SSN");

						frame.getContentPane().removeAll();

						frame.revalidate();
						frame.repaint();
						frame.setBounds(150, 150, 550, 400);
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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

						JButton btnSave = new JButton("Save");
						btnSave.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								LocalDateTime createdtime = LocalDateTime.now();
								DataEntry newEntry = new DataEntry(textField.getText(), "SSN", null, userName, 0, createdtime);
								newEntry.addDataField(textField_1.getText());
								newEntry.addDataField(textField_2.getText());
								DatabaseManager m = new DatabaseManager();
								int result = m.addEntryToDataBase(newEntry);
								if (result == -1) {
									JOptionPane.showMessageDialog(null,"The Entry Name has already existed! Please Try another Entry Name!");
									return;
								}
								else {
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
					if (index == 1) {
						frame.setTitle("New Credit/Debit Card");
						frame.getContentPane().removeAll();

						frame.revalidate();
						frame.repaint();
						frame.setBounds(150, 150, 550, 400);
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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
						cardType.setModel(new DefaultComboBoxModel(new String[] { "VISA", "MasterCard",
								"American Express", "Diners Club", "Carte Blanche", "Discover", "JCB" }));
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

						JButton btnSave = new JButton("Save");
						btnSave.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								LocalDateTime createdtime = LocalDateTime.now();
								DataEntry newEntry = new DataEntry(textField.getText(), "Credit/Debit Card", null, null,
										0, createdtime);
								newEntry.addDataField(textField_1.getText());
								newEntry.addDataField((String) cardType.getSelectedItem());
								newEntry.addDataField(textField_2.getText());
								newEntry.addDataField(textField_3.getText());
								newEntry.addDataField(textField_4.getText());
								DatabaseManager m = new DatabaseManager();
								int result = m.addEntryToDataBase(newEntry);
								if (result == -1) {
									JOptionPane.showMessageDialog(null,"The Entry Name has already existed! Please Try another Entry Name!");
									return;
								}
								else {
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
					if (index == 2) {
						frame.setTitle("New Passport");
						frame.getContentPane().removeAll();

						frame.revalidate();
						frame.repaint();
						frame.setBounds(150, 150, 550, 400);
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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

						JButton btnSave = new JButton("Save");
						btnSave.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								LocalDateTime createdtime = LocalDateTime.now();
								DataEntry newEntry = new DataEntry(textField.getText(), "PassPort", null, null, 0,
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
								int result = m.addEntryToDataBase(newEntry);
								if (result == -1) {
									JOptionPane.showMessageDialog(null,"The Entry Name has already existed! Please Try another Entry Name!");
									return;
								}
								else {
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
					if (index == 3) {
						frame.setTitle("New Driver's License");
						frame.getContentPane().removeAll();

						frame.revalidate();
						frame.repaint();
						frame.setBounds(150, 150, 550, 400);
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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

						JButton btnSave = new JButton("Save");
						btnSave.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								LocalDateTime createdtime = LocalDateTime.now();
								DataEntry newEntry = new DataEntry(textField.getText(), "Driver's license", null, null,
										0, createdtime);
								newEntry.addDataField(textField_1.getText());
								newEntry.addDataField(textField_2.getText());
								newEntry.addDataField(textField_3.getText());
								newEntry.addDataField(textField_4.getText());
								DatabaseManager m = new DatabaseManager();
								int result = m.addEntryToDataBase(newEntry);
								if (result == -1) {
									JOptionPane.showMessageDialog(null,"The Entry Name has already existed! Please Try another Entry Name!");
									return;
								}
								else {
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
					if (index == 4) {
						frame.setTitle("New Website Login");
						frame.getContentPane().removeAll();

						frame.revalidate();
						frame.repaint();
						frame.setBounds(150, 150, 550, 400);
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

						JLabel label, label_1, label_2, label_3;
						JTextField textField, textField_1, textField_2, textField_3;
						label = new JLabel("Entry Name");
						label.setBounds(40, 10, 150, 18);

						label_1 = new JLabel("Login Account");
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

						JButton btnSave = new JButton("Save");
						btnSave.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								LocalDateTime createdtime = LocalDateTime.now();
								DataEntry newEntry = new DataEntry(textField.getText(), "Website Login", null, null, 0,
										createdtime);
								newEntry.addDataField(textField_1.getText());
								newEntry.addDataField(textField_2.getText());
								newEntry.addDataField(textField_3.getText());
								DatabaseManager m = new DatabaseManager();
								int result = m.addEntryToDataBase(newEntry);
								if (result == -1) {
									JOptionPane.showMessageDialog(null,"The Entry Name has already existed! Please Try another Entry Name!");
									return;
								}
								else {
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
					if (index == 5) {
						frame.setTitle("New Wifi Network");
						frame.getContentPane().removeAll();

						frame.revalidate();
						frame.repaint();
						frame.setBounds(150, 150, 550, 400);
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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

						JButton btnSave = new JButton("Save");
						btnSave.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								LocalDateTime createdtime = LocalDateTime.now();
								DataEntry newEntry = new DataEntry(textField.getText(), "Wifi Network", null, null, 0,
										createdtime);
								newEntry.addDataField(textField_1.getText());
								newEntry.addDataField(textField_2.getText());
								newEntry.addDataField(textField_3.getText());
								DatabaseManager m = new DatabaseManager();
								int result = m.addEntryToDataBase(newEntry);
								if (result == -1) {
									JOptionPane.showMessageDialog(null,"The Entry Name has already existed! Please Try another Entry Name!");
									return;
								}
								else {
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

					if (index == 6) {
						frame.setTitle("New Phone Number");
						frame.getContentPane().removeAll();

						frame.revalidate();
						frame.repaint();
						frame.setBounds(100, 100, 450, 300);
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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

						JButton btnSave = new JButton("Save");
						btnSave.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								LocalDateTime createdtime = LocalDateTime.now();
								DataEntry newEntry = new DataEntry(textField.getText(), "Phone Number", null, null, 0,
										createdtime);
								newEntry.addDataField(textField_1.getText());
								newEntry.addDataField(textField_2.getText());
								newEntry.addDataField(textField_3.getText());
								DatabaseManager m = new DatabaseManager();
								int result = m.addEntryToDataBase(newEntry);
								if (result == -1) {
									JOptionPane.showMessageDialog(null,"The Entry Name has already existed! Please Try another Entry Name!");
									return;
								}
								else {
									frame.dispose();
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
				} else {

				}
			}
		});

	}

}
