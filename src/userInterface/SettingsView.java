package userInterface;

import java.awt.EventQueue;
import javax.swing.*;

import controllers.DatabaseManager;
import controllers.VaultController;
import dataManagement.User;

import java.awt.Font;
import java.awt.Window;
import java.awt.event.*;

public class SettingsView {

	public JFrame frmSettings; // TO SET THIS: create new SettingsView object, then objectname.username = <string>;
	public User currentUser; //Returns the User object that's been modified by the GUI.
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User s = new User(null, null, null, null, null, null, null);
					SettingsView window = new SettingsView(s);
					window.frmSettings.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SettingsView(User user) {
		this.currentUser = user;
		initialize();
	}

	public User getUser() { 
		
		return currentUser;
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSettings = new JFrame();
		frmSettings.setResizable(false);
		frmSettings.setTitle("Settings");
		frmSettings.setBounds(100, 100, 439, 360);
		frmSettings.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmSettings.getContentPane().setLayout(null);
		
		//High security toggle & tool tip
		JCheckBox chckbxHighSecurityLevel = new JCheckBox("High Security Level ");
		chckbxHighSecurityLevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int secLvl;
				if(chckbxHighSecurityLevel.isSelected() ==true ) { 
					secLvl = 1;
				}
				else { secLvl = 0; } 
				currentUser.setDefaultHighSecurity(secLvl);  //Sets security level in current User.
			}
		});
		chckbxHighSecurityLevel.setToolTipText("Toggling this setting will change ALL user encryptions to the strongest possible.");
		chckbxHighSecurityLevel.setBounds(48, 50, 222, 25);
		frmSettings.getContentPane().add(chckbxHighSecurityLevel);
		
		//box lets user decide when to back up & tooltip
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { //GETS VALUE FROM COMBO BOX
				String s  = (String) comboBox.getSelectedItem();
				currentUser.setBackupFrequency(s); //Adds this value to user
			}
		});
		comboBox.setToolTipText("This field sets how often all user data is backed up on disk.");
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select Value", "Hourly", "Daily", "Weekly", "Monthly", "Annually"}));
		
		comboBox.setBounds(58, 126, 113, 22);
		frmSettings.getContentPane().add(comboBox);
		
		JLabel lblBackupFrequency = new JLabel("Backup Frequency");
		lblBackupFrequency.setToolTipText("This field sets how often all user data is backed up on disk.");
		lblBackupFrequency.setBounds(183, 126, 113, 16);
		frmSettings.getContentPane().add(lblBackupFrequency);
		
		JLabel lblUserSettings = new JLabel("User Settings");
		lblUserSettings.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUserSettings.setBounds(49, 13, 118, 25);
		frmSettings.getContentPane().add(lblUserSettings);
		
		//Lets user set the file size past which they'll be warned about file size. & tool tip
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = (String) comboBox_1.getSelectedItem();
				int size = 0;
				if(s.equals("10 MB")) { 
					size = 10;
				}
				else if (s.equals("50 MB")) { 
					size = 50;
				}
				else if (s.equals("100 MB")){
					size = 100;
				}
				else if (s.equals("512 MB")) { 
					size = 512;
				}
				else if ( s.equals("1 GB")){ 
					size = 1024;
				}
				else if (s.equals("2 GB")) {
					size = 2048;
				}
				else if (s.equals("3 GB")) { 
					size = 3072;
				}
				else if(s.equals("4 GB")) { 
					size = 4096;
				}
				else if (s.equals("5 GB")){ 
					size = 5120;
				}
				currentUser.setMaxBackupSize(size);//Adds max back up size to current user.
			}
		});
		comboBox_1.setToolTipText("This field is the maximum size a backup file can reach before the user is warned. ");
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Select Value", "10 MB", "50 MB", "100 MB", "512 MB", "1 GB", "2 GB", "3 GB", "4 GB", "5 GB"}));
		comboBox_1.setBounds(58, 160, 113, 22);
		frmSettings.getContentPane().add(comboBox_1);
		
		JLabel lblFileSizeLimit = new JLabel("File Size Limit Warning ");
		lblFileSizeLimit.setBounds(183, 163, 190, 16);
		lblFileSizeLimit.setToolTipText("This field is the maximum size a backup file can reach before the user is warned. ");


		frmSettings.getContentPane().add(lblFileSizeLimit);
		
		//Will eventually contain an action listener that saves preferences to User
		JButton btnOk = new JButton("Save");
		btnOk.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equalsIgnoreCase("Ok")) {
					VaultController newVegas =  new VaultController();
					//TODO Call VaultController's method to update existing user.
					frmSettings.dispose();
		}
			}
			
		
		});
		btnOk.setBounds(48, 276, 97, 25);
		frmSettings.getContentPane().add(btnOk);
		
		//closes window
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent clickCancel) {
				if(clickCancel.getActionCommand().equalsIgnoreCase("Cancel")) {
					frmSettings.dispose();
				}
			}
		});
		btnCancel.setBounds(219, 276, 97, 25);
		frmSettings.getContentPane().add(btnCancel);
		
		JCheckBox chckbxD = new JCheckBox("Turn on account wipe after 5 failed login attemps");
		chckbxD.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Boolean b = chckbxD.isSelected();
				int wipe;
				if(b == true) { 
					wipe = 1;
				}
				else { wipe = 0; } 
				currentUser.setAccountWipe(wipe);
			}
		});
		chckbxD.setToolTipText("Toggling this setting will enable wiping of all data after a specific number of failed login attempts.");
		chckbxD.setBounds(48, 92, 367, 25);
		frmSettings.getContentPane().add(chckbxD);
		
		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PasswordChangeView k = new PasswordChangeView(currentUser);
				k.frmChangePassword.setVisible(true); 
			}
		});
		btnChangePassword.setBounds(48, 220, 139, 25);
		frmSettings.getContentPane().add(btnChangePassword);
		
		JButton btnDeleteAccount = new JButton("Delete Account"); //Delete Account Button
		btnDeleteAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(JOptionPane.showConfirmDialog(null, "Are You Sure?", "Sign Out",JOptionPane.YES_NO_OPTION, JOptionPane.DEFAULT_OPTION) == 0) {
					//This is for the user answering yes.
					//TODO Actually delete stuff, close windows, etc.
					DatabaseManager d = new DatabaseManager();
					if (d.deleteUserFromDatabase(currentUser) == 1) {
						JOptionPane.showMessageDialog(null,"You have successfully deleted your account!");
						System.gc(); 
						for (Window window : Window.getWindows()){
					    	window.dispose();
						}
						LoginView l = new LoginView();
						l.frame.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null,"Oops! Something went wrong!");
					}
				}
			}
		});
		btnDeleteAccount.setBounds(219, 220, 129, 25);
		frmSettings.getContentPane().add(btnDeleteAccount);
		
	}
}
