package userInterface;

import java.awt.EventQueue;
import javax.swing.*;

import controllers.DatabaseManager;
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
					User s = new User(null, null, null, null, null, null, null); //If anything tries to run on this user it's going to throw NullPointerExceptions
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
		frmSettings.setBounds(100, 100, 431, 345);
		frmSettings.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmSettings.getContentPane().setLayout(null);
		frmSettings.setLocationRelativeTo(null);
		
		//High security toggle & tool tip
		JCheckBox chckbxHighSecurityLevel = new JCheckBox("High Security Level ");
		if(currentUser.isHighSecurity() == 1) {
		chckbxHighSecurityLevel.setSelected(true);
		}
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
		chckbxHighSecurityLevel.setBounds(58, 62, 222, 25);
		frmSettings.getContentPane().add(chckbxHighSecurityLevel);
		
		//box lets user decide when to back up & tooltip
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select Value", "Manually", "Hourly", "Daily", "Weekly", "Monthly", "Annually"}));
		if(currentUser.getBackupFrequency().equals("Manually")) { 
			comboBox.setSelectedIndex(1);
		}
		if(currentUser.getBackupFrequency().equals("Hourly")){ 
			comboBox.setSelectedIndex(2);
		}
		if(currentUser.getBackupFrequency().equals("Daily")){ 
			comboBox.setSelectedIndex(3);
		}
		if(currentUser.getBackupFrequency().equals("Weekly")){ 
			comboBox.setSelectedIndex(4);
		}
		if(currentUser.getBackupFrequency().equals("Monthly")){ 
			comboBox.setSelectedIndex(5);
		}
		if(currentUser.getBackupFrequency().equals("Annually")){ 
			comboBox.setSelectedIndex(6);
		}
		else{comboBox.setSelectedIndex(-1); }
		
		comboBox.setSelectedItem(currentUser.getBackupFrequency());
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { //GETS VALUE FROM COMBO BOX
				String s  = (String) comboBox.getSelectedItem();
				if(s.equals("Select Value")) {
					JOptionPane.showMessageDialog(null, "Must select a max back up file size.", "Settings", 0);
				}
				else{
				currentUser.setBackupFrequency(s); //Adds this value to user
				}
			}
		});
		comboBox.setToolTipText("This field sets how often all user data is backed up on disk.");

		
		comboBox.setBounds(228, 126, 129, 22);
		frmSettings.getContentPane().add(comboBox);
		
		JLabel lblBackupFrequency = new JLabel("Backup/Reminder Frequency:");
		lblBackupFrequency.setToolTipText("This field sets how often all user data is backed up on disk.");
		lblBackupFrequency.setBounds(14, 129, 200, 16);
		frmSettings.getContentPane().add(lblBackupFrequency);
		
		JLabel lblUserSettings = new JLabel("User Settings");
		lblUserSettings.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUserSettings.setBounds(153, 26, 118, 25);
		frmSettings.getContentPane().add(lblUserSettings);
		
		//Lets user set the file size past which they'll be warned about file size. & tool tip
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Select Value", "10 MB", "50 MB", "100 MB", "512 MB", "1 GB", "2 GB", "3 GB", "4 GB", "5 GB"}));
		String current = "";
		if(currentUser.getMaxBackupSize() < 1024){ //sets combo box to user's selected value
		 current += currentUser.getMaxBackupSize() + " MB";
		}
		if(currentUser.getMaxBackupSize() == 1024) { 
			current+=1 + " GB";
		}
		if(currentUser.getMaxBackupSize() == 2048) { 
			current+=2 + " GB";
		}
		if(currentUser.getMaxBackupSize() == 3072){
			current+=3 + " GB";
		}
		if(currentUser.getMaxBackupSize() == 4096){
			current+=4 + " GB";
		}
		if(currentUser.getMaxBackupSize() == 5120){
			current += 5 + " GB";
		}
		else{ comboBox_1.setSelectedIndex(0); }

		comboBox_1.setSelectedItem(current);
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = (String) comboBox_1.getSelectedItem();
				int size = 0;
				if(s.equals("Select Value")) { 
					JOptionPane.showMessageDialog(null, "Must select a max back up file size.", "Settings", 0);
				}
				else if(s.equals("10 MB")) { 
					size = 10;
					currentUser.setMaxBackupSize(size);//Adds max back up size to current user.
				}
				else if (s.equals("50 MB")) { 
					size = 50;
					currentUser.setMaxBackupSize(size);//Adds max back up size to current user.
				}
				else if (s.equals("100 MB")){
					size = 100;
					currentUser.setMaxBackupSize(size);//Adds max back up size to current user.
				}
				else if (s.equals("512 MB")) { 
					size = 512;
					currentUser.setMaxBackupSize(size);//Adds max back up size to current user.
				}
				else if ( s.equals("1 GB")){ 
					size = 1024;
					currentUser.setMaxBackupSize(size);//Adds max back up size to current user.
				}
				else if (s.equals("2 GB")) {
					size = 2048;
					currentUser.setMaxBackupSize(size);//Adds max back up size to current user.
				}
				else if (s.equals("3 GB")) { 
					size = 3072;
					currentUser.setMaxBackupSize(size);//Adds max back up size to current user.
				}
				else if(s.equals("4 GB")) { 
					size = 4096;
					currentUser.setMaxBackupSize(size);//Adds max back up size to current user.
				}
				else if (s.equals("5 GB")){ 
					size = 5120;
					currentUser.setMaxBackupSize(size);//Adds max back up size to current user.
				}
				
			}
		});
		comboBox_1.setToolTipText("This field is the maximum size a backup file can reach before the user is warned. ");
		
		comboBox_1.setBounds(228, 159, 129, 22);
		frmSettings.getContentPane().add(comboBox_1);
		
		JLabel lblFileSizeLimit = new JLabel("Backup Size Limit:");
		lblFileSizeLimit.setBounds(68, 162, 190, 16);
		lblFileSizeLimit.setToolTipText("This field is the maximum size a backup file can reach before the user is warned. ");

		frmSettings.getContentPane().add(lblFileSizeLimit);
		
		JButton btnOk = new JButton("Save");
		btnOk.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
					DatabaseManager newVegas =  new DatabaseManager("vault_database");
	
					newVegas.modifyUserField(currentUser, "high_security", currentUser.isHighSecurity());
					newVegas.modifyUserField(currentUser, "backup_frequency", currentUser.getBackupFrequency());
					newVegas.modifyUserField(currentUser, "account_wipe_set", currentUser.isAccountWipeSet());
					newVegas.modifyUserField(currentUser, "max_backup_size", currentUser.getMaxBackupSize());
					frmSettings.dispose();
				
			}
		});
		btnOk.setBounds(81, 276, 97, 25);
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
		btnCancel.setBounds(248, 276, 97, 25);
		frmSettings.getContentPane().add(btnCancel);
		
		JCheckBox chckbxD = new JCheckBox("Turn on account wipe after 6 failed login attemps");
		if(currentUser.isAccountWipeSet() ==1){
			chckbxD.setSelected(true);
		}
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
		chckbxD.setBounds(58, 92, 367, 25);
		frmSettings.getContentPane().add(chckbxD);
		
		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PasswordChangeView k = new PasswordChangeView(currentUser);
				k.frmChangePassword.setVisible(true); 
				frmSettings.dispose();
			}
		});
		btnChangePassword.setBounds(58, 231, 139, 25);
		frmSettings.getContentPane().add(btnChangePassword);
		
		JButton btnDeleteAccount = new JButton("Delete Account"); //Delete Account Button
		btnDeleteAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(JOptionPane.showConfirmDialog(frmSettings, "Are You Sure?", "Sign Out",JOptionPane.YES_NO_OPTION, JOptionPane.DEFAULT_OPTION) == 0) {
					//This is for the user answering yes.
					// Actually deletes stuff, closes windows, etc.
					DatabaseManager d = new DatabaseManager("vault_database");
					d.deleteAllEntriesFromDatabase(currentUser);
					if (d.deleteUserFromDatabase(currentUser) == 1) {
						JOptionPane.showMessageDialog(null,"You have successfully deleted your account!");
						System.gc(); 
						for (Window window : Window.getWindows()){
					    	window.dispose();
						}
						LoginView l = new LoginView();
						l.frmSignIn.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null,"Oops! Something went wrong!");
					}
				}
			}
		});
		btnDeleteAccount.setBounds(228, 231, 129, 25);
		frmSettings.getContentPane().add(btnDeleteAccount);
		
		JButton btnChangeAvatar = new JButton("Change Avatars Picture");
		btnChangeAvatar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AvatarView a = new AvatarView(currentUser);
				a.frame.setVisible(true);
			}
		});
		btnChangeAvatar.setBounds(137, 189, 169, 29);
		frmSettings.getContentPane().add(btnChangeAvatar);
	}
}
