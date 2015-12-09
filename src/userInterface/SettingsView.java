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
	public HomeView h;
	public String avatar;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User s = new User(null, null, null, null, null, null, null); //If anything tries to run on this user it's going to throw NullPointerExceptions
					SettingsView window = new SettingsView(s, new HomeView(s.getUsername()));
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
	public SettingsView(User user, HomeView h) {
		this.currentUser = user;
		this.h = h;
		this.avatar = user.getDataKey();
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
		frmSettings.setBounds(100, 100, 431, 392);
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
		
		//box lets user decide when to be reminded of needing to change their password
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select Frequency", "Manually", "Hourly", "Daily", "Weekly", "Monthly", "Annually"}));
		System.out.println("Password Reminder every: " + currentUser.getBackupFrequency());
		if(currentUser.getBackupFrequency().equals("Manually")) { 
			comboBox.setSelectedIndex(1);
		}
		else if(currentUser.getBackupFrequency().equals("Hourly")){ 
			comboBox.setSelectedIndex(2);
		}
		else if(currentUser.getBackupFrequency().equals("Daily")){ 
			comboBox.setSelectedIndex(3);
		}
		else if(currentUser.getBackupFrequency().equals("Weekly")){ 
			comboBox.setSelectedIndex(4);
		}
		else if(currentUser.getBackupFrequency().equals("Monthly")){ 
			comboBox.setSelectedIndex(5);
		}
		else if(currentUser.getBackupFrequency().equals("Annually")){ 
			comboBox.setSelectedIndex(6);
		}
		else{comboBox.setSelectedIndex(0); }
		
		comboBox.setSelectedItem(currentUser.getBackupFrequency());
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { //GETS VALUE FROM COMBO BOX
				String s  = (String) comboBox.getSelectedItem();
				System.out.println(s);
				if(s.equals("Select Frequency")) {
					JOptionPane.showMessageDialog(null, "Must select a max back up file size.", "Settings", 0);
				}
				else{
				currentUser.setBackupFrequency(s); //Adds this value to user
				}
			}
		});
		comboBox.setToolTipText("This field sets how often all user data is backed up on disk.");

		
		comboBox.setBounds(228, 126, 156, 22);
		frmSettings.getContentPane().add(comboBox);
		
		JLabel lblBackupFrequency = new JLabel("Password Reminder:");
		lblBackupFrequency.setToolTipText("This field sets how often users are reminded to change their password.");
		lblBackupFrequency.setBounds(67, 129, 139, 16);
		frmSettings.getContentPane().add(lblBackupFrequency);
		
		JLabel lblUserSettings = new JLabel("User Settings");
		lblUserSettings.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUserSettings.setBounds(153, 26, 118, 25);
		frmSettings.getContentPane().add(lblUserSettings);
		
		//Lets user set the file size past which they'll be warned about file size. & tool tip
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Select Frequency", "Manually", "Hourly", "Daily", "Weekly", "Monthly", "Annually"}));
		//comboBox.setSelectedIndex(currentUser.getMaxBackupSize());
		if(currentUser.getMaxBackupSize() == 1) { //number corresponds to position in the comboBox_1 String []. 
												  // 1 = manually, 2 = hourly, 3= daily, 4 = weekly, 5 = monthly, 6 = annually.
			comboBox_1.setSelectedIndex(1);
		}
		else if(currentUser.getMaxBackupSize() == 2){ 
			comboBox_1.setSelectedIndex(2);
		}
		else if(currentUser.getMaxBackupSize() == 3){ 
			comboBox_1.setSelectedIndex(3);
		}
		else if(currentUser.getMaxBackupSize() == 4){ 
			comboBox_1.setSelectedIndex(4);
		}
		else if(currentUser.getMaxBackupSize() == 5){ 
			comboBox_1.setSelectedIndex(5);
		}
		else if(currentUser.getMaxBackupSize() == 6){ 
			comboBox_1.setSelectedIndex(6);
		}
		else{comboBox_1.setSelectedIndex(0); }

		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = (String) comboBox_1.getSelectedItem();
				int freq = 0;
				if(s.equals("Select Value")) { 
					JOptionPane.showMessageDialog(null, "Must select a max back up frequency.", "Settings", 0);
				}
				else if(s.equals("Manually")) { 
					freq = 1;
					currentUser.setMaxBackupSize(freq);//Adds back up freq to current user.
				}
				else if (s.equals("Hourly")) { 
					freq = 2;
					currentUser.setMaxBackupSize(freq);//Adds back up freq to current user.
				}
				else if (s.equals("Daily")){
					freq = 3;
					currentUser.setMaxBackupSize(freq);
				}
				else if (s.equals("Weekly")) { 
					freq = 4;
					currentUser.setMaxBackupSize(freq);
				}
				else if ( s.equals("Monthly")){ 
					freq = 5;
					currentUser.setMaxBackupSize(freq);
				}
				else if (s.equals("Annually")) {
					freq = 6;
					currentUser.setMaxBackupSize(freq);
				}
				
			}
		});
		comboBox_1.setToolTipText("This field is the frequency with which a user's data is backed up.");
		
		comboBox_1.setBounds(228, 159, 157, 22);
		frmSettings.getContentPane().add(comboBox_1);
		
		JLabel lblFileSizeLimit = new JLabel("Automatic Backups:");
		lblFileSizeLimit.setBounds(68, 162, 190, 16);
		lblFileSizeLimit.setToolTipText("This field is the frequency with which user data is backed up.");

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
		btnOk.setBounds(81, 325, 97, 25);
		frmSettings.getContentPane().add(btnOk);
		
		//closes window
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent clickCancel) {
				if(clickCancel.getActionCommand().equalsIgnoreCase("Cancel")) {
					DatabaseManager newVegas =  new DatabaseManager("vault_database");
					newVegas.modifyUserField(currentUser, "data_key", avatar);
					h.lblNewLabel.setIcon(new ImageIcon(HomeView.class.getResource(avatar)));
					frmSettings.dispose();
				}
			}
		});
		btnCancel.setBounds(248, 325, 97, 25);
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
		btnChangePassword.setBounds(218, 194, 139, 25);
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
		btnDeleteAccount.setBounds(151, 260, 129, 25);
		frmSettings.getContentPane().add(btnDeleteAccount);
		
		JButton btnChangeAvatar = new JButton("Change Avatar");
		btnChangeAvatar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AvatarView a = new AvatarView(currentUser, h);
				a.frame.setVisible(true);
			}
		});
		btnChangeAvatar.setBounds(67, 192, 116, 29);
		frmSettings.getContentPane().add(btnChangeAvatar);
	}
}
