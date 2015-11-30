package userInterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controllers.DatabaseManager;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import java.awt.Color;
import dataManagement.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShareView extends JFrame {

	private JPanel contentPanel;
	private DataEntry oldData;
	private DataEntry sharingData;
	private User owner;
	private JTextField textField;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					ShareView frame = new ShareView(owner, oldData);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public ShareView(User owner, DataEntry entry) {
		this.owner = owner;
		this.oldData = entry;
		this.sharingData = entry;
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		JButton btnShareLocally = new JButton("Share locally");
		btnShareLocally.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DatabaseManager d = new DatabaseManager("vault_database");
				
				String user = textField.getText();
				if (d.retrieveUserFromDatabase(user) != null){
				
				//now check if the target user actually exists
					sharingData.addValidUser(user);
					d.updateEntry(owner, oldData, sharingData);
					JOptionPane.showMessageDialog(null,
							"You have successfully shared your file with the target user!");
				}
				else{
					JOptionPane.showMessageDialog(null,
							"The user email does not exist in our database. Please check if you type it correctly");
				}
			}
		});
		
		btnShareLocally.setBounds(46, 186, 130, 27);
		contentPanel.add(btnShareLocally);
		
		JButton btnShareViaEmail = new JButton("Share via email");
		btnShareViaEmail.setBounds(241, 186, 130, 27);
		contentPanel.add(btnShareViaEmail);
		
		JLabel lblEmailAccountYou = new JLabel("Enter the email account you want to share with:");
		lblEmailAccountYou.setBounds(46, 46, 360, 18);
		contentPanel.add(lblEmailAccountYou);
		
		textField = new JTextField();
		textField.setBounds(46, 106, 210, 24);
		contentPanel.add(textField);
		textField.setColumns(10);
	}
}
