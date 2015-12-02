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
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.ComponentOrientation;

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
		setLocationRelativeTo(null);
		setTitle("Share with your friend");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 446, 265);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, -1, 440, 233);
		contentPanel.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Share", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblEmailAccountYou = new JLabel("Enter the email account you want to share with:");
		lblEmailAccountYou.setBounds(31, 65, 376, 18);
		panel.add(lblEmailAccountYou);
		
		JButton btnShareLocally = new JButton("Share locally");
		btnShareLocally.setBounds(31, 147, 137, 27);
		panel.add(btnShareLocally);
		
		JButton btnShareViaEmail = new JButton("Share via email");
		btnShareViaEmail.setBounds(182, 147, 153, 27);
		panel.add(btnShareViaEmail);
		
		textField = new JTextField();
		textField.setBounds(31, 96, 199, 24);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblTheDataYour = new JLabel("The data your are sharing is:");
		lblTheDataYour.setBounds(31, 13, 242, 18);
		panel.add(lblTheDataYour);
		
		JLabel lblNewLabel = new JLabel(oldData.getEntryName());
		lblNewLabel.setBounds(41, 34, 232, 18);
		panel.add(lblNewLabel);
		
		JButton btnStopSharing = new JButton("Stop sharing");
		btnStopSharing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sharingData.removeAllValidUser();
				DatabaseManager d = new DatabaseManager("vault_database");
				d.deleteEntryFromDatabase(entry);
				d.addEntryToDatabase(owner, sharingData);
				JOptionPane.showMessageDialog(null,
							"You have successfully stopped sharing your file!");
				dispose();
				
			}
		});
		btnStopSharing.setBounds(294, 30, 113, 27);
		panel.add(btnStopSharing);
		btnShareLocally.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DatabaseManager d = new DatabaseManager("vault_database");
				
				String user = textField.getText();
				if (d.retrieveUserFromDatabase(user) != null){
					List<String> validUsers = entry.getValidUsers();
					if(!validUsers.contains(user)){
				//now check if the target user actually exists
					sharingData.addValidUser(user);
					d.deleteEntryFromDatabase(entry);
					d.addEntryToDatabase(owner, sharingData);
					//d.updateEntry(owner, entry, sharingData);
					JOptionPane.showMessageDialog(null,
							"You have successfully shared your file with the target user!");
					dispose();
					}
					else{
						JOptionPane.showMessageDialog(null,
							"You are already sharing with this user!");
					}
				}
				else{
					JOptionPane.showMessageDialog(null,
							"The user email does not exist in our database. Please check if you type it correctly");
				}
			}
		});
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Decrypt", null, panel_1, null);
	}
}
