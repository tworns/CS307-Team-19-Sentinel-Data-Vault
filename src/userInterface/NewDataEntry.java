package userInterface;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.List;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import java.awt.Button;
import java.awt.TextField;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JTextField;

public class NewDataEntry {

	private JFrame frame;
	private static Choice choice;
	private static JPanel panel_1;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewDataEntry window = new NewDataEntry();
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
	public NewDataEntry() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		choice = new Choice();
		choice.addItem("SSN");
		choice.addItem("Credit/Debit Card");
		choice.addItem("Passport");
		choice.addItem("Drive License");
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
						frame.getContentPane().removeAll();
						
						frame.revalidate();
						frame.repaint();
						frame.setBounds(100, 100, 450, 300);
						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						
						JLabel label, label_1;
						JTextField textField, textField_1;
						label = new JLabel("Name");
						label.setBounds(40, 10, 150, 18);
						
						label_1 = new JLabel("SSN");
						label_1.setBounds(40, 40, 150, 18);
						
																
						textField = new JTextField();
						textField.setBounds(110, 10, 200, 18);
						textField.setColumns(10);
						
						textField_1 = new JTextField();
						textField_1.setBounds(110, 40, 200, 18);
						textField_1.setColumns(10);
						

						frame.getContentPane().setLayout(null);
						frame.getContentPane().add(label);
						frame.getContentPane().add(label_1);
						frame.getContentPane().add(textField);
						frame.getContentPane().add(textField_1);
					}
					if (index == 1) {
						frame.getContentPane().removeAll();
						
						frame.revalidate();
						frame.repaint();
						frame.setBounds(100, 100, 450, 300);
						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						
						JLabel label, label_1, label_2, label_3;
						JTextField textField, textField_1, textField_2, textField_3;
						label = new JLabel("Card holder name");
						label.setBounds(40, 10, 150, 18);
						
						label_1 = new JLabel("Card Number");
						label_1.setBounds(40, 40, 150, 18);
						
						label_2 = new JLabel("Expired Date");
						label_2.setBounds(40, 70, 150, 18);
						
						label_3 = new JLabel("CSV");
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
					if (index == 2) {
						frame.getContentPane().removeAll();
						
						frame.revalidate();
						frame.repaint();
						frame.setBounds(100, 100, 450, 300);
						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						
						JLabel label, label_1, label_2, label_3, label_4, label_5, label_6, label_7;
						JTextField textField, textField_1, textField_2, textField_3, textField_4, textField_5, textField_6, textField_7;
						label = new JLabel("Name");
						label.setBounds(40, 10, 150, 18);
						
						label_1 = new JLabel("Passport Number");
						label_1.setBounds(40, 40, 150, 18);
						
						label_2 = new JLabel("Nationality");
						label_2.setBounds(40, 70, 150, 18);
						
						label_3 = new JLabel("Sex");
						label_3.setBounds(40, 100, 150, 18);
						
						label_4 = new JLabel("Date of Birth");
						label_4.setBounds(40, 130, 150, 18);
						
						label_5 = new JLabel("Place of Birth");
						label_5.setBounds(40, 160, 150, 18);
						
						label_6 = new JLabel("Issued Date");
						label_6.setBounds(40, 190, 150, 18);
						
						label_7 = new JLabel("Expired Date");
						label_7.setBounds(40, 220, 150, 18);
						
																
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
						
						frame.getContentPane().setLayout(null);
						frame.getContentPane().add(label);
						frame.getContentPane().add(label_1);
						frame.getContentPane().add(label_2);
						frame.getContentPane().add(label_3);
						frame.getContentPane().add(label_4);
						frame.getContentPane().add(label_5);
						frame.getContentPane().add(label_6);
						frame.getContentPane().add(label_7);
						frame.getContentPane().add(textField);
						frame.getContentPane().add(textField_1);
						frame.getContentPane().add(textField_2);
						frame.getContentPane().add(textField_3);
						frame.getContentPane().add(textField_4);
						frame.getContentPane().add(textField_5);
						frame.getContentPane().add(textField_6);
						frame.getContentPane().add(textField_7);
					}
					if (index == 3) {
						frame.getContentPane().removeAll();
						
						frame.revalidate();
						frame.repaint();
						frame.setBounds(100, 100, 450, 300);
						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						
						JLabel label, label_1, label_2, label_3;
						JTextField textField, textField_1, textField_2, textField_3;
						label = new JLabel("Name");
						label.setBounds(40, 10, 150, 18);
						
						label_1 = new JLabel("Address");
						label_1.setBounds(40, 40, 150, 18);
						
						label_2 = new JLabel("Day of Birth");
						label_2.setBounds(40, 70, 150, 18);
						
						label_3 = new JLabel("Expired Date");
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
					if (index == 4) {
						frame.getContentPane().removeAll();
						
						frame.revalidate();
						frame.repaint();
						frame.setBounds(100, 100, 450, 300);
						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						
						JLabel label, label_1, label_2;
						JTextField textField, textField_1, textField_2;
						label = new JLabel("Login Account");
						label.setBounds(40, 10, 150, 18);
						
						label_1 = new JLabel("Password");
						label_1.setBounds(40, 40, 150, 18);
						
						label_2 = new JLabel("Website Address");
						label_2.setBounds(40, 70, 150, 18);
						
																
						textField = new JTextField();
						textField.setBounds(170, 10, 200, 18);
						textField.setColumns(10);
						
						textField_1 = new JTextField();
						textField_1.setBounds(170, 40, 200, 18);
						textField_1.setColumns(10);
						
						textField_2 = new JTextField();
						textField_2.setBounds(170, 70, 200, 18);
						textField_2.setColumns(10);
						
						frame.getContentPane().setLayout(null);
						frame.getContentPane().add(label);
						frame.getContentPane().add(label_1);
						frame.getContentPane().add(label_2);
						frame.getContentPane().add(textField);
						frame.getContentPane().add(textField_1);
						frame.getContentPane().add(textField_2);						
					}					
					if (index == 5) {
						frame.getContentPane().removeAll();
						
						frame.revalidate();
						frame.repaint();
						frame.setBounds(100, 100, 450, 300);
						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						
						JLabel label, label_1, label_2;
						JTextField textField, textField_1, textField_2;
						label = new JLabel("Wifi Network Name");
						label.setBounds(40, 10, 150, 18);
						
						label_1 = new JLabel("Password");
						label_1.setBounds(40, 40, 150, 18);
						
						label_2 = new JLabel("Security");
						label_2.setBounds(40, 70, 150, 18);
						
																
						textField = new JTextField();
						textField.setBounds(170, 10, 200, 18);
						textField.setColumns(10);
						
						textField_1 = new JTextField();
						textField_1.setBounds(170, 40, 200, 18);
						textField_1.setColumns(10);
						
						textField_2 = new JTextField();
						textField_2.setBounds(170, 70, 200, 18);
						textField_2.setColumns(10);
						
						frame.getContentPane().setLayout(null);
						frame.getContentPane().add(label);
						frame.getContentPane().add(label_1);
						frame.getContentPane().add(label_2);
						frame.getContentPane().add(textField);
						frame.getContentPane().add(textField_1);
						frame.getContentPane().add(textField_2);						
					}
			           
					if (index == 6) {
						frame.getContentPane().removeAll();
						
						frame.revalidate();
						frame.repaint();
						frame.setBounds(100, 100, 450, 300);
						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						
						JLabel label, label_1, label_2;
						JTextField textField, textField_1, textField_2;
						label = new JLabel("Name");
						label.setBounds(40, 10, 150, 18);
						
						label_1 = new JLabel("Phone Number");
						label_1.setBounds(40, 40, 150, 18);
						
						label_2 = new JLabel("Group");
						label_2.setBounds(40, 70, 150, 18);
						
																
						textField = new JTextField();
						textField.setBounds(170, 10, 200, 18);
						textField.setColumns(10);
						
						textField_1 = new JTextField();
						textField_1.setBounds(170, 40, 200, 18);
						textField_1.setColumns(10);
						
						textField_2 = new JTextField();
						textField_2.setBounds(170, 70, 200, 18);
						textField_2.setColumns(10);
						
						frame.getContentPane().setLayout(null);
						frame.getContentPane().add(label);
						frame.getContentPane().add(label_1);
						frame.getContentPane().add(label_2);
						frame.getContentPane().add(textField);
						frame.getContentPane().add(textField_1);
						frame.getContentPane().add(textField_2);						
					}
			        } else {
			        	
			        }
			}});
		
		
		
		
		
	}

}
