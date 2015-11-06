package userInterface;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.TreeSelectionEvent;

import dataManagement.DataEntry;

import net.miginfocom.swing.MigLayout;



public class DataEntryPanel {
	private JPanel panel = new JPanel();
	private JLabel info = new JLabel("Select Data Entry");

	public DataEntryPanel() {
		
		initialize();
		// getCreditCardPanel();
		
	}
	
	public void initialize() {
		
	}
	

	public JPanel getAccountLoginPanel(DataEntry data){
			
		panel.removeAll();	
		panel.setLayout(new MigLayout("", "[grow]", "[][][][][][][][][][][][]"));
		
		String title = "Account Login";
		
		JLabel label_0 = new JLabel(title);
		panel.add(label_0, "cell 0 0");
		
		JLabel label_1 = new JLabel("Web URL");
		panel.add(label_1, "cell 0 1");
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setText("https://www.google.com");
		panel.add(formattedTextField, "cell 0 2,growx");
		
		JLabel label_2 = new JLabel("User Name");
		panel.add(label_2, "cell 0 3");
		
		JFormattedTextField formattedTextField1 = new JFormattedTextField();
		formattedTextField1.setText("username@gmail.com");
		panel.add(formattedTextField1, "cell 0 4,growx");
		
		JLabel label_3 = new JLabel("Password");
		panel.add(label_3, "cell 0 5");
		
		JFormattedTextField formattedTextField_1 = new JFormattedTextField();
		formattedTextField_1.setText("password");
		panel.add(formattedTextField_1, "cell 0 6,growx");
		
		
		
		
		
		return panel;
	}
	
	public JPanel getCreditCardPanel(DataEntry data) {
		panel.removeAll();	
		panel.setLayout(new MigLayout("", "[grow]", "[][][][][][][][][][][][]"));
		
		String title = "Card Name";
		
		JLabel label_0 = new JLabel(title);
		panel.add(label_0, "cell 0 0");
		
		JLabel label_1 = new JLabel("Card Type");
		panel.add(label_1, "cell 0 1");
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"VISA", "Master", "Discovery", "AMERICAN EXPRESS"}));
		panel.add(comboBox, "cell 0 2,growx");
		
		JLabel label_2 = new JLabel("Card Number");
		panel.add(label_2, "cell 0 3");
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setText("**** **** **** ****");
		panel.add(formattedTextField, "cell 0 4,growx");
		
		JLabel label_3 = new JLabel("Name on Card");
		panel.add(label_3, "cell 0 5");
		
		JFormattedTextField formattedTextField_1 = new JFormattedTextField();
		formattedTextField_1.setText("Name");
		panel.add(formattedTextField_1, "cell 0 6,growx");
		
		JLabel label_4 = new JLabel("CVV");
		panel.add(label_4, "cell 0 7");
		
		JFormattedTextField formattedTextField_2 = new JFormattedTextField();
		formattedTextField_2.setText("* * *");
		panel.add(formattedTextField_2, "cell 0 8,growx");
		
		JLabel label_5 = new JLabel("Expiration Date");
		panel.add(label_5, "cell 0 9");
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"01 ", "02 ", "03 ", "04 ", "05", "06 ", "07 ", "08 ", "09 ", "10", "11 ", "12"}));
		panel.add(comboBox_1, "cell 0 10,growx");
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"}));
		panel.add(comboBox_2, "cell 0 10,growx");
		
		
		
		return panel;
		
	}
	
	
	public JPanel getDriversLicensePanel(DataEntry data){
		panel.removeAll();	
		panel.setLayout(new MigLayout("", "[grow]", "[][][][][][][][][][][][]"));
		
		String title = "Driver's License";
		
		JLabel label_0 = new JLabel(title);
		panel.add(label_0, "cell 0 0");
		
		JLabel label_1 = new JLabel("Name");
		panel.add(label_1, "cell 0 1");
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setText("Name");
		panel.add(formattedTextField, "cell 0 2,growx");
		
		JLabel label_2 = new JLabel("Licnese Number");
		panel.add(label_2, "cell 0 3");
		
		JFormattedTextField formattedTextField1 = new JFormattedTextField();
		formattedTextField1.setText("0000-00-0000");
		panel.add(formattedTextField1, "cell 0 4,growx");
		
		JLabel label_3 = new JLabel("Date of Birth");
		panel.add(label_3, "cell 0 5");
		
		JLabel label_4 = new JLabel("Date of Birth");
		panel.add(label_4, "cell 0 7");
		
		JLabel label_5 = new JLabel("Date of Birth");
		panel.add(label_5, "cell 0 9");
		
		JFormattedTextField formattedTextField_1 = new JFormattedTextField();
		formattedTextField_1.setText("password");
		panel.add(formattedTextField_1, "cell 0 6,growx");
		
		
		
		
		
		return panel;
	}
	
	public void valueChanged(TreeSelectionEvent tsl) {
		
		if(tsl.getNewLeadSelectionPath() != null) {
			info.setText(tsl.getNewLeadSelectionPath().getLastPathComponent().toString());
		}
	}
	
	
	
	
	
}