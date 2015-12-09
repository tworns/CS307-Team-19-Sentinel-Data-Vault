package userInterface;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import dataManagement.*;
import controllers.BackupManager;
import controllers.DatabaseManager;
import controllers.VaultController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Window;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.event.TreeSelectionListener;
import javax.swing.JList;
import javax.swing.ImageIcon;
import net.miginfocom.swing.MigLayout;

public class HomeView {

	public JFrame frmSentinelDataVault;
	private JTextField txtUsername;
	private JTextField textField_1;
	public String username;
	public String lastlogin;
	public User currentUser;
	public DataEntry currentEntry;
	public List<DataEntry> currentAllDataEntries;
	public List<String> currentDataEntryNameList;
	public List<String> currentDataEntryTypeList;
	public List<String> currentSharedDataEntryNameList;
	public List<String> currentSharedDataEntryTypeList;
	public List<String> currentSharedDataEntryOwnerList;
	private JTextField txtSearch;
	private HomeView h;
	public JLabel lblNewLabel;
	public int buttonIndex_1 = 0;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String s = "username";
					HomeView window = new HomeView(s);
					window.frmSentinelDataVault.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HomeView(String username) {
		h = this;
		this.username = username;
		lastlogin = "last login time";

		DatabaseManager dbmanger = new DatabaseManager("vault_database");
		currentUser = dbmanger.retrieveUserFromDatabase(username);

		currentDataEntryNameList = dbmanger.retrieveDataEntryNameList(username);
		currentDataEntryTypeList = dbmanger.retrieveDataEntryTypeList(username);
		currentSharedDataEntryNameList = dbmanger.retrieveSharedEntryNameList(username);
		currentSharedDataEntryTypeList = dbmanger.retrieveSharedEntryTypeList(username);
		currentSharedDataEntryOwnerList= dbmanger.retrieveSharedEntryOwnerList(username);
		initialize();	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSentinelDataVault = new JFrame();
		frmSentinelDataVault.setResizable(false);
		frmSentinelDataVault.setTitle("Sentinel Data Vault");
		frmSentinelDataVault.setBounds(100, 100, 700, 500);
		frmSentinelDataVault.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSentinelDataVault.getContentPane().setLayout(null);
		frmSentinelDataVault.setLocationRelativeTo(null);
		//		anel_south		
		JPanel panel_north = new JPanel();
		panel_north.setBounds(6, 6, 688, 65);			// margins between object 8 
		panel_north.setOpaque(false); // to set transparent panel
		frmSentinelDataVault.getContentPane().add(panel_north);

		JButton btnSignOut = new JButton("Sign out");
		btnSignOut.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

		btnSignOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Locale locale = new Locale("EN", "US");
				JOptionPane.setDefaultLocale(locale);

				if(JOptionPane.showConfirmDialog(null, "Are you sure you want to sign out? You will be returned to Sign In.", "Sign Out",JOptionPane.YES_NO_OPTION, JOptionPane.DEFAULT_OPTION) == 0) {

					currentUser.setLastLogin(LocalDateTime.now());
					DatabaseManager d = new DatabaseManager("vault_database");
					currentUser.getLastLogin();
					String time = currentUser.getLastLogin().toString();
					d.modifyUserField(currentUser, "last_login", time );
					currentUser = null;
					LoginView frmLog = new LoginView();
					frmLog.frmSignIn.setVisible(true);
					frmSentinelDataVault.dispose();
				}
			}
		});

		JButton btnSetting = new JButton("Settings");
		btnSetting.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

		btnSetting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SettingsView frmSettings = new SettingsView(currentUser, h);
				frmSettings.currentUser = currentUser;
				frmSettings.frmSettings.setVisible(true);
			}
		});

		JButton btnAddData = new JButton("New Entry");
		btnAddData.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

		btnAddData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewDataEntryView newDataEntry = new NewDataEntryView(currentUser);
				//NewDataView newDataEntry = new NewDataView(currentUser);

				newDataEntry.getJframe().setVisible(true);
			}
		});

		JButton btnSecurity = new JButton("Security");
		btnSecurity.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

		btnSecurity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SecurityView regFace =new SecurityView();
				regFace.frame.setVisible(true);
				// frmSentinelDataVault.dispose();
			}
		});

		btnSecurity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		JButton btnDeleteData = new JButton("Delete Entry");
		btnDeleteData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentEntry == null) {
					JOptionPane.showMessageDialog(null, "Please choose an entry!");
					return;
				}
				System.out.println(currentEntry.getEntryName());
				Object[] options = { "OK", "CANCEL" };
				int result = JOptionPane.showOptionDialog(null, "Are you sure you want to permanently delete this data entry?", "Warning", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
						null, options, options[1]);
				if (result == 0) {
					System.out.println("Delete Entry");
					DatabaseManager m = new DatabaseManager("vault_database");
					m.deleteEntryFromDatabase(currentEntry);


					JOptionPane.showMessageDialog(null, "You have successfully deleted the data entry.");
					System.gc(); 
					for (Window window : Window.getWindows()){
						window.dispose();
					}

					frmSentinelDataVault.dispose();

					HomeView hv = new HomeView(username);
					hv.frmSentinelDataVault.setVisible(true);


				}
			}
		});
		btnDeleteData.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

		JLabel lblEmpty_1 = new JLabel("     ");

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);

		GroupLayout gl_panel_north = new GroupLayout(panel_north);
		gl_panel_north.setHorizontalGroup(
				gl_panel_north.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_north.createSequentialGroup()
						.addComponent(btnSignOut, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnSetting, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblEmpty_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnAddData, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnDeleteData, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnSecurity, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
						.addGap(61)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
				);
		gl_panel_north.setVerticalGroup(
				gl_panel_north.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_north.createSequentialGroup()
						.addGroup(gl_panel_north.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnSignOut, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
								.addGroup(gl_panel_north.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnSetting, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
										.addComponent(btnAddData, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnDeleteData, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblEmpty_1)
										.addComponent(btnSecurity, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)))
						.addGap(15))
				.addGroup(Alignment.LEADING, gl_panel_north.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(19, Short.MAX_VALUE))
				);
		panel_1.setLayout(null);

		txtSearch = new JTextField();
		txtSearch.setBounds(6, 6, 114, 30);
		txtSearch.setText("Search");
		panel_1.add(txtSearch);
		txtSearch.setColumns(10);

		JButton btnSearch = new JButton("");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String inputText = txtSearch.getText();
				SearchResultView regFace =new SearchResultView( inputText, currentUser );
				regFace.frame.setVisible(true);

			}
		});

		btnSearch.setBounds(122, 6, 38, 30);
		btnSearch.setIcon(new ImageIcon(HomeView.class.getResource("/Avatars/search.png")));
		panel_1.add(btnSearch);
		panel_north.setLayout(gl_panel_north);

		JPanel panel_south = new JPanel();
		panel_south.setBounds(0, 69, 700, 375);
		frmSentinelDataVault.getContentPane().add(panel_south);

		JPanel panel_west = new JPanel();
		panel_west.setBackground(UIManager.getColor("Button.background"));

		JTabbedPane panel_center = new JTabbedPane();
		panel_center.setBackground(Color.WHITE);

		JTabbedPane panel_east = new JTabbedPane();
		panel_east.setBackground(Color.WHITE);

		GroupLayout gl_panel_south = new GroupLayout(panel_south);
		gl_panel_south.setHorizontalGroup(
				gl_panel_south.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_south.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel_west, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_center, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_east, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
				);
		gl_panel_south.setVerticalGroup(
				gl_panel_south.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_south.createSequentialGroup()
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_panel_south.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_center, GroupLayout.PREFERRED_SIZE, 375, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel_west, GroupLayout.PREFERRED_SIZE, 375, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel_east, GroupLayout.PREFERRED_SIZE, 375, GroupLayout.PREFERRED_SIZE))
						.addContainerGap())
				);

		//panel_west		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		txtUsername.setBounds(6, 167, 169, 26);
		txtUsername.setText(username);
		txtUsername.setColumns(10);
		txtUsername.setEditable(false);

		JLabel lblLastLogin = new JLabel("Last Login");
		lblLastLogin.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblLastLogin.setBounds(5, 200, 175, 16);

		JLabel lblUserEmail = new JLabel("User Email");
		lblUserEmail.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblUserEmail.setBounds(5, 151, 165, 16);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		textField_1.setBounds(6, 218, 169, 26);
		textField_1.setText(lastlogin);
		textField_1.setEditable(false);
		if(currentUser != null){
			if (currentUser.getLastLogin().toString() != null){
				textField_1.setText(currentUser.getLastLogin().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
			}
		}
		textField_1.setColumns(10);

		JButton btnCreatebackup = new JButton("Create Backup");
		btnCreatebackup.setBounds(-1, 278, 181, 29);
		btnCreatebackup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				// Launch file choose to determine backup file location
//				JFileChooser fileChooser = new JFileChooser();
//				fileChooser.setDialogTitle("Choose a location to save backup database file");
//				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // User can only select a directory to store a backup
//				fileChooser.setAcceptAllFileFilterUsed(false);
//				int result = fileChooser.showDialog(frmSentinelDataVault, "Save Backup");
//				if (result == JFileChooser.APPROVE_OPTION) {
//					// Execute the backup
//					File selectedBackupLocation = fileChooser.getSelectedFile();
//					BackupManager bum = new BackupManager();
//					System.out.println(selectedBackupLocation.getAbsolutePath());
//					bum.createUserBackupDatabase(currentUser, selectedBackupLocation.getAbsolutePath());
//				}
				
				VaultController.performBackup(currentUser);
			}
		});

		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(30, 8, 120, 120);
		lblNewLabel.setIcon(new ImageIcon(HomeView.class.getResource(currentUser.getDataKey())));
		panel_west.setLayout(null);
		panel_west.add(btnCreatebackup);
		panel_west.add(textField_1);
		panel_west.add(lblLastLogin);
		panel_west.add(txtUsername);
		panel_west.add(lblUserEmail);
		panel_west.add(lblNewLabel);

		JButton btnImport = new JButton("Import Entries");
		btnImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Choose a backup file to import");
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY); // User can only select a backup file to import from
				int result = fileChooser.showDialog(frmSentinelDataVault, "Import");
				if (result == JFileChooser.APPROVE_OPTION) {
					// Execute the import
					File selectedBackupFile = fileChooser.getSelectedFile();
					String importLocation = selectedBackupFile.getAbsolutePath();
					// Need to get the user info of the backup file to import from
					// TODO retrieve these user-entered credentials from an input window
					String backupUserEmail = JOptionPane.showInputDialog(null, "Enter the username for the account you're trying to import from"); // ***TESTING PURPOSES ONLY***
					String backupUserPassword = JOptionPane.showInputDialog(null, "Enter the password for the account you're trying to import from"); // ***TESTING PURPOSES ONLY***
					// Need to validate that current user has valid access privileges to import file
					BackupManager bum = new BackupManager();
					if (bum.isValidBackupUser(backupUserEmail, backupUserPassword, importLocation)) {
						DatabaseManager dbm = new DatabaseManager(importLocation);
						User backupUser = dbm.retrieveUserFromDatabase(backupUserEmail);
						bum.importEntriesFromBackup(currentUser, backupUser, "vault_database", importLocation);
						// refresh MainView
						JOptionPane.showMessageDialog(null, "Entries have been imported into your account!", "Import Success", JOptionPane.INFORMATION_MESSAGE);
						Refresh rf = new Refresh(username);
						rf.refresh();
					}
					else {
						JOptionPane.showMessageDialog(null, "Invalid Email/Password. Please try again.", "Unauthorized Backup Access", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnImport.setBounds(-1, 302, 181, 29);
		panel_west.add(btnImport);
		panel_south.setLayout(gl_panel_south);

		//panel_east		
		//Where to pass the all the data
		// 
		/*update to pass DataEntry Object*/

		/*
		JPanel panel = new JPanel();
		DataEntryPanel dataPanel = new DataEntryPanel();
		DataEntry currentdata = new DataEntry(null, null, null, null, 0, null);
		String panelName = null;
		 */



		// if the data object is Account Login 
		// panel = dataPanel.getAccountLoginPanel(currentdata);
		// panelName = "Account Login";

		// if the data object is Credit Card
		//	panel = dataPanel.getCreditCardPanel(currentdata);
		//	panelName = "Credit Card";

		// if the data object is License  
		//	panel = dataPanel.getDriversLicensePanel(data);
		//	panelName = "Driver's License  ";



		//	panel_east.addTab(panelName, null, panel, null);




		//panel_center	> Category(JTree) + List(JList) 



		/* ************************************************************************** */ 
		/* 	TREE Constructor														  */
		/*																			  */
		/* ************************************************************************** */		
		//Tree		

		JTree tree = new JTree();

		DatabaseManager dbmanger = new DatabaseManager("vault_database");
		currentUser = dbmanger.retrieveUserFromDatabase(username);

		currentDataEntryNameList = dbmanger.retrieveDataEntryNameList(username);
		currentDataEntryTypeList = dbmanger.retrieveDataEntryTypeList(username);
		currentSharedDataEntryNameList = dbmanger.retrieveSharedEntryNameList(username);
		currentSharedDataEntryTypeList = dbmanger.retrieveSharedEntryTypeList(username);
		currentSharedDataEntryOwnerList= dbmanger.retrieveSharedEntryOwnerList(username);

		//String selectedNode = null;

		DefaultMutableTreeNode node_1 = new DefaultMutableTreeNode("Account Login");
		DefaultMutableTreeNode node_2 = new DefaultMutableTreeNode("Confirmation Number");
		DefaultMutableTreeNode node_3 = new DefaultMutableTreeNode("Credit/Debit Card");
		DefaultMutableTreeNode node_4 = new DefaultMutableTreeNode("Entry Code");
		DefaultMutableTreeNode node_5 = new DefaultMutableTreeNode("Flight Ticket");
		DefaultMutableTreeNode node_6 = new DefaultMutableTreeNode("General Password");
		DefaultMutableTreeNode node_7 = new DefaultMutableTreeNode("ID Card");
		DefaultMutableTreeNode node_8 = new DefaultMutableTreeNode("License");
		DefaultMutableTreeNode node_9 = new DefaultMutableTreeNode("Passport");
		DefaultMutableTreeNode node_10 = new DefaultMutableTreeNode("Phone Number");
		DefaultMutableTreeNode node_11 = new DefaultMutableTreeNode("Serial Number");
		DefaultMutableTreeNode node_12 = new DefaultMutableTreeNode("Shipment Tracking Number");
		DefaultMutableTreeNode node_13 = new DefaultMutableTreeNode("SSN");
		DefaultMutableTreeNode node_14 = new DefaultMutableTreeNode("Wifi Network");
		DefaultMutableTreeNode node_15 = new DefaultMutableTreeNode("Shared Data Entry");

		DefaultMutableTreeNode sharednode_1 = new DefaultMutableTreeNode("Account Login");
		DefaultMutableTreeNode sharednode_2 = new DefaultMutableTreeNode("Confirmation Number");
		DefaultMutableTreeNode sharednode_3 = new DefaultMutableTreeNode("Credit/Debit Card");
		DefaultMutableTreeNode sharednode_4 = new DefaultMutableTreeNode("Entry Code");
		DefaultMutableTreeNode sharednode_5 = new DefaultMutableTreeNode("Flight Ticket");
		DefaultMutableTreeNode sharednode_6 = new DefaultMutableTreeNode("General Password");
		DefaultMutableTreeNode sharednode_7 = new DefaultMutableTreeNode("ID Card");
		DefaultMutableTreeNode sharednode_8 = new DefaultMutableTreeNode("License");
		DefaultMutableTreeNode sharednode_9 = new DefaultMutableTreeNode("Passport");
		DefaultMutableTreeNode sharednode_10 = new DefaultMutableTreeNode("Phone Number");
		DefaultMutableTreeNode sharednode_11 = new DefaultMutableTreeNode("Serial Number");
		DefaultMutableTreeNode sharednode_12 = new DefaultMutableTreeNode("Shipment Tracking Number");
		DefaultMutableTreeNode sharednode_13 = new DefaultMutableTreeNode("SSN");
		DefaultMutableTreeNode sharednode_14 = new DefaultMutableTreeNode("Wifi Network");


		tree.setModel(new DefaultTreeModel(
				new DefaultMutableTreeNode("Data Entry") {
					{

						for (int i=0; i < currentDataEntryNameList.size(); i++) {

							DataEntry cd = new DataEntry(null, null, null, null, 0, null);
							DatabaseManager db = new DatabaseManager("vault_database");
							cd = db.retrieveOneDataEntry(currentDataEntryNameList.get(i), currentUser, currentDataEntryTypeList.get(i));


							System.out.println("here");

							if (cd.getOwner().equals(currentUser.getUsername())) {

								if(currentDataEntryTypeList.contains("Account Login")) {
									add(node_1);
								}
								if(currentDataEntryTypeList.contains("Confirmation Number")) {
									add(node_2);
								}
								if(currentDataEntryTypeList.contains("Credit/Debit Card")) {
									add(node_3);
								}
								if(currentDataEntryTypeList.contains("Entry Code")) {
									add(node_4);
								}
								if(currentDataEntryTypeList.contains("Flight Ticket")) {
									add(node_5);
								}
								if(currentDataEntryTypeList.contains("General Password")) {
									add(node_6);
								}
								if(currentDataEntryTypeList.contains("ID Card")) {
									add(node_7);
								}
								if(currentDataEntryTypeList.contains("License")) {
									add(node_8);
								}
								if(currentDataEntryTypeList.contains("Passport")) {
									add(node_9);
								}
								if(currentDataEntryTypeList.contains("Phone Number")) {
									add(node_10);
								}
								if(currentDataEntryTypeList.contains("Serial Number")) {
									add(node_11);
								}
								if(currentDataEntryTypeList.contains("Shipment Tracking Number")) {
									add(node_12);
								}
								if(currentDataEntryTypeList.contains("SSN")) {
									add(node_13);
								}	
								if(currentDataEntryTypeList.contains("Wifi Network")) {
									add(node_14);
								}
							}
						}


						List<String> owners = new ArrayList<String>();
						DefaultMutableTreeNode ownerNode = new DefaultMutableTreeNode();

						for (int i=0; i < currentSharedDataEntryNameList.size(); i++) {
							add(node_15);

							DatabaseManager db = new DatabaseManager("vault_database");
							DataEntry currentDE = db.retrieveOneDataEntry(currentSharedDataEntryNameList.get(i),db.retrieveUserFromDatabase(currentSharedDataEntryOwnerList.get(i)),currentSharedDataEntryTypeList.get(i));
							DefaultMutableTreeNode child = new DefaultMutableTreeNode(currentSharedDataEntryNameList.get(i));
							// listModel.addElement(currentSharedDataEntryNameList.get(i));



							// new owner > add node
							if ( !owners.contains(currentDE.getOwner().toString()) ) {
								owners.add(currentDE.getOwner().toString());
								DefaultMutableTreeNode newOwnerNode = new DefaultMutableTreeNode(currentDE.getOwner().toString());
								ownerNode = newOwnerNode;

							}/*
							else{
								int ci = 0;
								while (true) {

									if(node_15.getChildAt(ci).toString().equals(currentDE.getOwner().toString())){
										break;
									}
									ci++;
								}
								ownerNode = (DefaultMutableTreeNode) node_15.getChildAt(ci);
								node_15.add(ownerNode);
							}*/

							node_15.add(ownerNode);

							if(currentSharedDataEntryTypeList.contains("Account Login")) {
								ownerNode.add(sharednode_1);
							}
							if(currentSharedDataEntryTypeList.contains("Confirmation Number")) {
								ownerNode.add(sharednode_2);
							}
							if(currentSharedDataEntryTypeList.contains("Credit/Debit Card")) {
								ownerNode.add(sharednode_3);
							}
							if(currentSharedDataEntryTypeList.contains("Entry Code")) {
								ownerNode.add(sharednode_4);
							}
							if(currentSharedDataEntryTypeList.contains("Flight Ticket")) {
								ownerNode.add(sharednode_5);
							}
							if(currentSharedDataEntryTypeList.contains("General Password")) {
								ownerNode.add(sharednode_6);
							}
							if(currentSharedDataEntryTypeList.contains("ID Card")) {
								ownerNode.add(sharednode_7);
							}
							if(currentSharedDataEntryTypeList.contains("License")) {
								ownerNode.add(sharednode_8);
							}
							if(currentSharedDataEntryTypeList.contains("Passport")) {
								ownerNode.add(sharednode_9);
							}
							if(currentSharedDataEntryTypeList.contains("Phone Number")) {
								ownerNode.add(sharednode_10);
							}
							if(currentSharedDataEntryTypeList.contains("Serial Number")) {
								ownerNode.add(sharednode_11);
							}
							if(currentSharedDataEntryTypeList.contains("Shipment Tracking Number")) {
								ownerNode.add(sharednode_12);
							}
							if(currentSharedDataEntryTypeList.contains("SSN")) {
								ownerNode.add(sharednode_13);
							}	
							if(currentSharedDataEntryTypeList.contains("Wifi Network")) {
								ownerNode.add(sharednode_14);
							}
						}

					}
				}
				));

		//	
		/*		
		DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) model.getRoot();
		DefaultMutableTreeNode child = (DefaultMutableTreeNode) node.getRoot();
		node.add(child);
		model.reload(node);
		 */		
		//node_1 = new DefaultMutableTreeNode("Account Login");
		//node_1.add(new DefaultMutableTreeNode("Google"));
		//node_1.add(new DefaultMutableTreeNode("Apple"));
		//node_1.add(new DefaultMutableTreeNode("Purdue"));
		//root.add(node_1);

		DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		listModel.setSize(currentDataEntryNameList.size());

		DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();

		System.out.println("currentDataEntryNameList :" + currentDataEntryNameList.size());
		System.out.println("currentDataEntryTypeList :" + currentDataEntryTypeList.size());
		System.out.println("currentSharedDataEntryNameList :" + currentSharedDataEntryNameList.size());
		System.out.println("currentSharedDataEntryTypeList :" + currentSharedDataEntryTypeList.size());
		System.out.println("currentSharedDataEntryOwnerList :" + currentSharedDataEntryOwnerList.size());
		System.out.println("number of root :" + root.getChildCount());


		for (int i=0; i < currentDataEntryTypeList.size(); i++) {
			DefaultMutableTreeNode child = new DefaultMutableTreeNode(currentDataEntryNameList.get(i));

			listModel.addElement(currentDataEntryNameList.get(i));

			if (currentDataEntryTypeList.get(i).equals("Account Login")) {
				//System.out.println(currentDataEntryTypeList.get(i));
				node_1.add(child);
			}
			else if (currentDataEntryTypeList.get(i).equals("Confirmation Number")) {
				//System.out.println(currentDataEntryTypeList.get(i));
				node_2.add(child);	
			}
			else if (currentDataEntryTypeList.get(i).equals("Credit/Debit Card")) {
				//System.out.println(currentDataEntryTypeList.get(i));
				node_3.add(child);	
			}
			else if (currentDataEntryTypeList.get(i).equals("Entry Code")) {
				//System.out.println(currentDataEntryTypeList.get(i));
				node_4.add(child);	
			}
			else if (currentDataEntryTypeList.get(i).equals("Flight Ticket")) {
				//System.out.println(currentDataEntryTypeList.get(i));
				node_5.add(child);	
			}
			else if (currentDataEntryTypeList.get(i).equals("General Password")) {
				//System.out.println(currentDataEntryTypeList.get(i));
				node_6.add(child);	
			}
			else if (currentDataEntryTypeList.get(i).equals("ID Card")) {
				//System.out.println(currentDataEntryTypeList.get(i));
				node_7.add(child);	
			}
			else if (currentDataEntryTypeList.get(i).equals("License")) {
				//System.out.println(currentDataEntryTypeList.get(i));
				node_8.add(child);	
			}
			else if (currentDataEntryTypeList.get(i).equals("Passport")) {
				//System.out.println(currentDataEntryTypeList.get(i));
				node_9.add(child);	
			}
			else if (currentDataEntryTypeList.get(i).equals("Phone Number")) {
				//System.out.println(currentDataEntryTypeList.get(i));
				node_10.add(child);	
			}
			else if (currentDataEntryTypeList.get(i).equals("Serial Number")) {
				//System.out.println(currentDataEntryTypeList.get(i));
				node_11.add(child);	
			}
			else if (currentDataEntryTypeList.get(i).equals("Shipment Tracking Number")) {
				//System.out.println(currentDataEntryTypeList.get(i));
				node_12.add(child);	
			}
			else if (currentDataEntryTypeList.get(i).equals("SSN")) {
				//System.out.println(currentDataEntryTypeList.get(i));
				node_13.add(child);	
			}
			else if (currentDataEntryTypeList.get(i).equals("Wifi Network")) {
				//System.out.println(currentDataEntryTypeList.get(i));
				node_14.add(child);	
			}

		}


		for (int i=0; i < currentSharedDataEntryNameList.size(); i++) {

			DefaultMutableTreeNode child = new DefaultMutableTreeNode(currentSharedDataEntryNameList.get(i));
			// listModel.addElement(currentSharedDataEntryNameList.get(i));

			if (currentSharedDataEntryTypeList.get(i).equals("Account Login")) {
				sharednode_1.add(child);
			}
			else if (currentSharedDataEntryTypeList.get(i).equals("Confirmation Number")) {
				sharednode_2.add(child);	
			}
			else if (currentSharedDataEntryTypeList.get(i).equals("Credit/Debit Card")) {
				sharednode_3.add(child);	
			}
			else if (currentSharedDataEntryTypeList.get(i).equals("Entry Code")) {
				sharednode_4.add(child);	
			}
			else if (currentSharedDataEntryTypeList.get(i).equals("Flight Ticket")) {
				sharednode_5.add(child);	
			}
			else if (currentSharedDataEntryTypeList.get(i).equals("General Password")) {
				sharednode_6.add(child);	
			}
			else if (currentSharedDataEntryTypeList.get(i).equals("ID Card")) {
				sharednode_7.add(child);	
			}
			else if (currentSharedDataEntryTypeList.get(i).equals("License")) {
				sharednode_8.add(child);	
			}
			else if (currentSharedDataEntryTypeList.get(i).equals("Passport")) {
				sharednode_9.add(child);	
			}
			else if (currentSharedDataEntryTypeList.get(i).equals("Phone Number")) {
				sharednode_10.add(child);	
			}
			else if (currentSharedDataEntryTypeList.get(i).equals("Serial Number")) {
				sharednode_11.add(child);	
			}
			else if (currentSharedDataEntryTypeList.get(i).equals("Shipment Tracking Number")) {
				sharednode_12.add(child);	
			}
			else if (currentSharedDataEntryTypeList.get(i).equals("SSN")) {
				sharednode_13.add(child);	
			}
			else if (currentSharedDataEntryTypeList.get(i).equals("Wifi Network")) {
				sharednode_14.add(child);	
			}

		}


		/* ************************************************************************** */ 
		/* 	TREE LISTENER															  */
		/*																			  */
		/* ************************************************************************** */

		tree.addTreeSelectionListener(new TreeSelectionListener() {

			public void valueChanged(TreeSelectionEvent e) {

				JTree tree = (JTree) e.getSource();

				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if (selectedNode == null) {
					currentEntry = null;
					return;
				}
				String selectedNodeName = selectedNode.toString();

				panel_east.removeAll();

				JPanel panel = new JPanel();
				DataEntryPanel dataPanel = new DataEntryPanel();
				DataEntry currentdata = new DataEntry(null, null, null, null, 0, null);


				// Error: if user deleted all the data entries  folder become leaf 
				// everytime leaf is clicked new pane called



				String panelName = null;

				System.out.println(selectedNodeName);

				//if data item

				if (selectedNode.isLeaf()) {

					DatabaseManager dm = new DatabaseManager("vault_database");
					User owner = currentUser;

					if (!selectedNode.getParent().getParent().toString().equals("Data Entry")) {
						owner = dm.retrieveUserFromDatabase(selectedNode.getParent().getParent().toString());
					}

					DataEntry selectedDataEntry = dm.retrieveOneDataEntry(selectedNodeName, owner, selectedNode.getParent().toString());

					currentEntry = selectedDataEntry;

					ArrayList<String> indexList = new ArrayList<String>();


					System.out.println("selectedNodeName 					: "+ selectedNodeName);
					System.out.println("username							: "+ username);
					System.out.println("selectedNode.getParent().toString() : "+ selectedNode.getParent().toString());
					System.out.println("owner"+selectedNode.getParent().getParent().toString());

					if(selectedNode.getParent().toString() == "Account Login") {
						panel = dataPanel.getAccountLoginPanelWithData(selectedDataEntry);
					}
					else if(selectedNode.getParent().toString() == "Confirmation Number") {
						indexList.add("Confirmation Name");
						indexList.add("Confirmation Number");

						panel = dataPanel.getGeneralPanelWithData(indexList, selectedDataEntry);
					}
					else if(selectedNode.getParent().toString() == "Credit/Debit Card") {
						panel = dataPanel.getCreditCardPanelWithData(selectedDataEntry);
					}
					else if(selectedNode.getParent().toString() == "Entry Code") {
						indexList.add("Code Name");
						indexList.add("Code");

						panel = dataPanel.getGeneralPanelWithData(indexList, selectedDataEntry);
					}
					else if(selectedNode.getParent().toString() == "Flight Ticket") {
						indexList.add("Passenger");
						indexList.add("Destination");
						indexList.add("Airport (Origin)");
						indexList.add("Airport (Dest.)");
						indexList.add("Gate (Origin)");
						indexList.add("Gate (Dest.)");
						indexList.add("Departure");
						indexList.add("Arrival");
						indexList.add("Group");
						indexList.add("Seat");


						panel = dataPanel.getGeneralPanelWithData(indexList, selectedDataEntry);
					}
					else if(selectedNode.getParent().toString() == "General Password") {

						indexList.add("Password Name");
						indexList.add("Password");

						panel = dataPanel.getGeneralPanelWithData(indexList, selectedDataEntry);
					}
					else if(selectedNode.getParent().toString() == "ID Card") {

						indexList.add("ID Card Name");
						indexList.add("ID Card Number");
						indexList.add("Cardholder Name");
						indexList.add("Address");
						indexList.add("Issue Date");
						indexList.add("Expiration Date");

						panel = dataPanel.getGeneralPanelWithData(indexList, selectedDataEntry);
					}
					else if(selectedNode.getParent().toString() == "License") {

						panel = dataPanel.getLicensePaneWithData(selectedDataEntry);
						/*
						indexList.add("License Name");
						indexList.add("PCardholder Name");
						indexList.add("License Number");
						indexList.add("Address");
						indexList.add("Date of Birth");
						indexList.add("Expiration Date");
						indexList.add("Class");
						indexList.add("Restrictions");	
						panel = dataPanel.getGeneralPanelWithData(indexList, selectedDataEntry);
						 */

					}
					else if(selectedNode.getParent().toString() == "Passport") {
						indexList.add("Name");
						indexList.add("Passport Number");
						indexList.add("Nationality");
						indexList.add("Sex");
						indexList.add("Date of Birth");
						indexList.add("Place of Birth");
						indexList.add("Issued Date");
						indexList.add("Expiration Date");


						panel = dataPanel.getGeneralPanelWithData(indexList, selectedDataEntry);
					}
					else if(selectedNode.getParent().toString() == "Phone Number") {
						panel = dataPanel.getPhoneNumberPaneWithData(selectedDataEntry);
						/*
						indexList.add("Name");
						indexList.add("Phone Number");
						indexList.add("Group");
						panel = dataPanel.getGeneralPanelWithData(indexList, selectedDataEntry);
						 */
					}
					else if(selectedNode.getParent().toString() == "Serial Number") {
						indexList.add("Product Name");
						indexList.add("Serial Number");

						panel = dataPanel.getGeneralPanelWithData(indexList, selectedDataEntry);
					}
					else if(selectedNode.getParent().toString() == "Shipment Tracking Number") {
						indexList.add("Company/Item Name");
						indexList.add("Shipment Tracking Number");

						panel = dataPanel.getGeneralPanelWithData(indexList, selectedDataEntry);
					}
					else if(selectedNode.getParent().toString() == "SSN") {
						panel = dataPanel.getSSNWithData(selectedDataEntry);
						/*
						indexList.add("Name");
						indexList.add("SSN");
						panel = dataPanel.getGeneralPanelWithData(indexList, selectedDataEntry);
						 */
					}
					else if(selectedNode.getParent().toString() == "Wifi Network") {
						//panel = dataPanel.getWifiNetworkWithData(selectedDataEntry);
						indexList.add("Network Name");
						indexList.add("Password");
						indexList.add("Security");
						panel = dataPanel.getGeneralPanelWithData(indexList, selectedDataEntry);

					}

					panelName = selectedNode.getParent().toString();


					JScrollPane scroll = new JScrollPane(panel);
					panel_east.addTab(panelName, null, scroll, null);


					//panel_east.addTab(panelName, null, panel, null);

					System.out.println("Leaf: " + selectedNodeName);
					indexList.clear();
				}
				else {
					currentEntry = null;
				}

				return;
			}
			/*
			private DataEntry retrieveOneDataEntry(String selectedNodeName, User currentUser, String string) {

				return null;

			}*/
		});


		JScrollPane jsp = new JScrollPane(tree);
		panel_center.addTab("Category", null, jsp, null);

		JScrollPane scrollPane = new JScrollPane();
		panel_center.addTab("Item List", null, scrollPane, null);

		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);

		List<DataEntry> allData = new ArrayList<DataEntry>();
		DatabaseManager dm = new DatabaseManager("vault_database");
		allData = dm.retrieveDataEntryList(currentUser);
		List<DataEntry> sortedTimeData = allData;
		List<DataEntry> sortedNameData = allData;

		int numOfData = allData.size();
		System.out.println("numOfData :" + numOfData);

		/*
		 *  sorting
		 *  sort by name
		 *  
		 * */
		JList<String> list = new JList();
		list.setBounds(6, 53, 208, 266);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JButton btnSortByEntry = new JButton();
		btnSortByEntry.setLayout(new BorderLayout());
		JLabel label1 = new JLabel("Sort by");
		JLabel label2 = new JLabel("Entry Name");
		btnSortByEntry.add(BorderLayout.NORTH,label1);
		btnSortByEntry.add(BorderLayout.SOUTH,label2);
		
		btnSortByEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				buttonIndex_1 = 1;
				
				panel_east.removeAll();
				
				listModel.clear();
				
				for(int i=0; i< numOfData;i++) {
					listModel.add(i, sortedNameData.get(i).getEntryName());
					//sortedAllData.set(i, allData.get(i));
				}
				
				
				list.setModel(listModel);
				panel.repaint();
				panel_center.repaint();
				scrollPane.repaint();
			}
		});
		btnSortByEntry.setBounds(6, 6, 94, 44);
		panel.add(btnSortByEntry);

		JButton button = new JButton();


		button.setLayout(new BorderLayout());
		JLabel label3 = new JLabel("Sort by");
		JLabel label4 = new JLabel("Modified Time");
		button.add(BorderLayout.NORTH,label3);
		button.add(BorderLayout.SOUTH,label4);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				buttonIndex_1 = 2;
				
				panel_east.removeAll();
				listModel.clear();
				
				Collections.sort(sortedTimeData);
				
				for(int i=0; i< numOfData;i++) {
					
					listModel.add(i, sortedTimeData.get(sortedTimeData.size() - i -1).getEntryName());
					//Collections.sort(sortedAllData, sortedAllData.get(i).getLastModified());
					//sortedAllData.set(i, allData.get(i));
				}
				list.setModel(listModel);

				panel.repaint();
				panel_center.repaint();
				scrollPane.repaint();
			}
		});
		button.setBounds(102, 6, 112, 44);
		panel.add(button);

		
		
		
		listModel.clear();
		
		/*
		for(int i=0; i< currentDataEntryNameList.size();i++) {
			listModel.add(i, currentDataEntryTypeList.get(i));
		}*/
		
		panel.repaint();


		list.addListSelectionListener( new ListSelectionListener() {
			
			public void valueChanged(ListSelectionEvent e) {

				JList list = (JList) e.getSource();

				System.out.println(e.getSource().toString());
				
				
				
				panel_east.removeAll();
				
				int num = list.getSelectedIndex();

				System.out.println(num);
				if(num == -1){
					panel_east.removeAll();
					return;
				}
				DataEntry selectedDataEntry = null;
				if(buttonIndex_1 == 1){
					selectedDataEntry = sortedNameData.get(num);
				}
				else if(buttonIndex_1 == 2) {
					selectedDataEntry = sortedTimeData.get(num);
				}
				
				System.out.println(selectedDataEntry.getEntryName());
				System.out.println(selectedDataEntry.getOwner());
				System.out.println(selectedDataEntry.getEntryType());
				System.out.println("HEREHERE");
				System.out.println("");

				currentEntry = selectedDataEntry;

				ArrayList<String> indexList = new ArrayList<String>();

				DataEntryPanel dataPanel = new DataEntryPanel();
				JPanel panel = new JPanel();
				
				if(selectedDataEntry.getEntryType().toString().equals("Account Login")) {
					panel = dataPanel.getAccountLoginPanelWithData(selectedDataEntry);
				}
				else if(selectedDataEntry.getEntryType().toString().equals("Confirmation Number")) {
					indexList.add("Confirmation Name");
					indexList.add("Confirmation Number");

					panel = dataPanel.getGeneralPanelWithData(indexList, selectedDataEntry);
				}
				else if(selectedDataEntry.getEntryType().toString().equals("Credit/Debit Card")) {
					panel = dataPanel.getCreditCardPanelWithData(selectedDataEntry);
				}
				else if(selectedDataEntry.getEntryType().toString().equals("Entry Code")) {
					indexList.add("Code Name");
					indexList.add("Code");

					panel = dataPanel.getGeneralPanelWithData(indexList, selectedDataEntry);
				}
				else if(selectedDataEntry.getEntryType().toString().equals("Flight Ticket")) {
					indexList.add("Passenger");
					indexList.add("Destination");
					indexList.add("Airport (Origin)");
					indexList.add("Airport (Dest.)");
					indexList.add("Gate (Origin)");
					indexList.add("Gate (Dest.)");
					indexList.add("Departure");
					indexList.add("Arrival");
					indexList.add("Group");
					indexList.add("Seat");


					panel = dataPanel.getGeneralPanelWithData(indexList, selectedDataEntry);
				}
				else if(selectedDataEntry.getEntryType().toString().equals("General Password")) {

					indexList.add("Password Name");
					indexList.add("Password");

					panel = dataPanel.getGeneralPanelWithData(indexList, selectedDataEntry);
				}
				else if(selectedDataEntry.getEntryType().toString().equals("ID Card")) {

					indexList.add("ID Card Name");
					indexList.add("ID Card Number");
					indexList.add("Cardholder Name");
					indexList.add("Address");
					indexList.add("Issue Date");
					indexList.add("Expiration Date");

					panel = dataPanel.getGeneralPanelWithData(indexList, selectedDataEntry);
				}
				else if(selectedDataEntry.getEntryType().toString().equals("License")) {

					panel = dataPanel.getLicensePaneWithData(selectedDataEntry);
					/*
						indexList.add("License Name");
						indexList.add("PCardholder Name");
						indexList.add("License Number");
						indexList.add("Address");
						indexList.add("Date of Birth");
						indexList.add("Expiration Date");
						indexList.add("Class");
						indexList.add("Restrictions");	
						panel = dataPanel.getGeneralPanelWithData(indexList, selectedDataEntry);
					 */

				}
				else if(selectedDataEntry.getEntryType().toString().equals("Passport") ){
					indexList.add("Name");
					indexList.add("Passport Number");
					indexList.add("Nationality");
					indexList.add("Sex");
					indexList.add("Date of Birth");
					indexList.add("Place of Birth");
					indexList.add("Issued Date");
					indexList.add("Expiration Date");


					panel = dataPanel.getGeneralPanelWithData(indexList, selectedDataEntry);
				}
				else if(selectedDataEntry.getEntryType().toString().equals("Phone Number") ){
					panel = dataPanel.getPhoneNumberPaneWithData(selectedDataEntry);
					/*
						indexList.add("Name");
						indexList.add("Phone Number");
						indexList.add("Group");
						panel = dataPanel.getGeneralPanelWithData(indexList, selectedDataEntry);
					 */
				}
				else if(selectedDataEntry.getEntryType().toString().equals("Serial Number")) {
					indexList.add("Product Name");
					indexList.add("Serial Number");

					panel = dataPanel.getGeneralPanelWithData(indexList, selectedDataEntry);
				}
				else if(selectedDataEntry.getEntryType().toString().equals("Shipment Tracking Number")) {
					indexList.add("Company/Item Name");
					indexList.add("Shipment Tracking Number");

					panel = dataPanel.getGeneralPanelWithData(indexList, selectedDataEntry);
				}
				else if(selectedDataEntry.getEntryType().toString() == "SSN") {
					panel = dataPanel.getSSNWithData(selectedDataEntry);
					/*
						indexList.add("Name");
						indexList.add("SSN");
						panel = dataPanel.getGeneralPanelWithData(indexList, selectedDataEntry);
					 */
				}
				else if(selectedDataEntry.getEntryType().toString().equals("Wifi Network") ){
					//panel = dataPanel.getWifiNetworkWithData(selectedDataEntry);
					indexList.add("Network Name");
					indexList.add("Password");
					indexList.add("Security");
					panel = dataPanel.getGeneralPanelWithData(indexList, selectedDataEntry);

				}


				JScrollPane scroll = new JScrollPane(panel);
				panel_east.addTab(selectedDataEntry.getEntryType().toString(), null, scroll, null);


				//panel_east.addTab(panelName, null, panel, null);

				indexList.clear();
				
			}


		});

		panel.add(list);
		
		

		// MenuBar
		JMenuBar menuBar = new JMenuBar();
		frmSentinelDataVault.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmProperies = new JMenuItem("Properies");
		mnFile.add(mntmProperies);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);

		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);

		JMenu mnUser = new JMenu("User");
		menuBar.add(mnUser);

		JMenuItem mntmAccountDeletion = new JMenuItem("Account Deletion");
		mnUser.add(mntmAccountDeletion);

		JMenu mnSearch = new JMenu("Search");
		menuBar.add(mnSearch);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmHelp = new JMenuItem("Help");
		mntmHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HelpView regFace2 =new HelpView();
				regFace2.frmHelp.setVisible(true);
			}
		});
		mnHelp.add(mntmHelp);

		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
	}
}
