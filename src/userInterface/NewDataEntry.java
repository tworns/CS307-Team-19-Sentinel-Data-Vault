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
						
						JLabel label, label_1, label_2;
						JTextField textField, textField_1, textField_2;
						label = new JLabel("First Name");
						label.setBounds(40, 10, 70, 18);
						
						label_1 = new JLabel("Last Name");
						label_1.setBounds(40, 40, 70, 18);
						
						label_2 = new JLabel("SSN");
						label_2.setBounds(40, 70, 70, 18);
						
																
						textField = new JTextField();
						textField.setBounds(110, 10, 100, 18);
						textField.setColumns(10);
						
						textField_1 = new JTextField();
						textField_1.setBounds(110, 40, 100, 18);
						textField_1.setColumns(10);
						
						textField_2 = new JTextField();
						textField_2.setBounds(110, 70, 100, 18);
						textField_2.setColumns(10);
						
						frame.getContentPane().setLayout(null);
						frame.getContentPane().add(label);
						frame.getContentPane().add(label_1);
						frame.getContentPane().add(label_2);
						frame.getContentPane().add(textField);
						frame.getContentPane().add(textField_1);
						frame.getContentPane().add(textField_2);

					}
					System.out.println(index);
			        //Need to revalidate and repaint, or else the label
			        //will probably be drawn in the wrong place.
			            
			        } else {
			        	System.out.println(2);
			        }
			}});
		
		
		
		
		
	}

}
