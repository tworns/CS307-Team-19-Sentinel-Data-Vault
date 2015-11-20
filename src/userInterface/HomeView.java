package userInterface;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import dataManagement.*;
import controllers.DatabaseManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.event.TreeSelectionListener;
import javax.swing.JList;

public class HomeView {

	public JFrame frmSentinelDataVault;
	private JTextField textField;
	private JTextField textField_1;
	public String username;
	public String lastlogin;
	public User currentUser;
	public DataEntry currentEntry;
	public List<DataEntry> currentAllDataEntries;
	public List<String> currentDataEntryNameList;
	public List<String> currentDataEntryTypeList;

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
		this.username = username;
		lastlogin = "last login time";

		DatabaseManager dbmanger = new DatabaseManager();
		currentUser = dbmanger.retrieveUserFromDatabase(username);

		currentDataEntryNameList = dbmanger.retrieveDataEntryNameList(username);
		currentDataEntryTypeList = dbmanger.retrieveDataEntryTypeList(username);

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
				if(JOptionPane.showConfirmDialog(null, "Are You Sure?", "Sign Out",JOptionPane.YES_NO_OPTION, JOptionPane.DEFAULT_OPTION) == 0) {
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
				SettingsView frmSettings = new SettingsView(currentUser);
				frmSettings.currentUser = currentUser;
				frmSettings.frmSettings.setVisible(true);
			}
		});

		JButton btnAddData = new JButton("New Entry");
		btnAddData.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

		btnAddData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//NewDataEntryView newDataEntry = new NewDataEntryView(currentUser);
				NewDataView newDataEntry = new NewDataView(currentUser);
				
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
				System.out.println(currentEntry.getEntryName());
				Object[] options = { "OK", "CANCEL" };
				int result = JOptionPane.showOptionDialog(null, "Data Entry will be deleted permanently.\nAre you sure you want to delete the Entry?", "Warning", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
						null, options, options[1]);
				if (result == 0) {
					System.out.println("Delete Entry");
					DatabaseManager m = new DatabaseManager();
					m.deleteEntryFromDatabase(currentEntry);
					
					
					JOptionPane.showMessageDialog(null,"You have successfully Deleted DataEntry");
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

		JLabel lblEmpty_2 = new JLabel("     ");

		JButton btnEditData = new JButton("Edit Entry");
		btnEditData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditDataEntryView editDataEntry = new EditDataEntryView(currentEntry);
				editDataEntry.getJframe().setVisible(true);
			}
		});
		btnEditData.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

		GroupLayout gl_panel_north = new GroupLayout(panel_north);
		gl_panel_north.setHorizontalGroup(
				gl_panel_north.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_north.createSequentialGroup()
						.addComponent(btnSignOut, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnSetting, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblEmpty_1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnAddData, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnDeleteData, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblEmpty_2, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnEditData, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnSecurity, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
						.addGap(326))
				);
		gl_panel_north.setVerticalGroup(
				gl_panel_north.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_north.createSequentialGroup()
						.addGroup(gl_panel_north.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnSignOut, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
								.addGroup(gl_panel_north.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnSetting, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
										.addComponent(btnAddData, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnDeleteData, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnSecurity, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
										.addComponent(lblEmpty_1)
										.addComponent(lblEmpty_2)
										.addComponent(btnEditData, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)))
						.addGap(15))
				);
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
				gl_panel_south.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_south.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel_west, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
						.addComponent(panel_center, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_east, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
				);
		gl_panel_south.setVerticalGroup(
				gl_panel_south.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_south.createSequentialGroup()
						.addGroup(gl_panel_south.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_east, GroupLayout.PREFERRED_SIZE, 375, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel_center, GroupLayout.PREFERRED_SIZE, 375, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel_west, GroupLayout.PREFERRED_SIZE, 375, GroupLayout.PREFERRED_SIZE))
						.addContainerGap())
				);

		//panel_west		
		textField = new JTextField();
		textField.setText(username);
		textField.setColumns(10);

		JLabel lblLastLogin = new JLabel("Last Login");

		JLabel lblUserEmail = new JLabel("User Email");

		textField_1 = new JTextField();
		textField_1.setText(lastlogin);
		if(currentUser != null){
			if (currentUser.getLastLogin().toString() != null){
				textField_1.setText(currentUser.getLastLogin().toString());
			}
		}
		textField_1.setColumns(10);
		GroupLayout gl_panel_west = new GroupLayout(panel_west);
		gl_panel_west.setHorizontalGroup(
				gl_panel_west.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_west.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel_west.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblLastLogin)
								.addComponent(lblUserEmail)
								.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
								.addComponent(textField))
						.addContainerGap(24, Short.MAX_VALUE))
				);
		gl_panel_west.setVerticalGroup(
				gl_panel_west.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_west.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblUserEmail)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblLastLogin)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(287, Short.MAX_VALUE))
				);
		panel_west.setLayout(gl_panel_west);
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




		//Tree		

		JTree tree = new JTree();
		
		DatabaseManager dbmanger = new DatabaseManager();
		currentUser = dbmanger.retrieveUserFromDatabase(username);

		currentDataEntryNameList = dbmanger.retrieveDataEntryNameList(username);
		currentDataEntryTypeList = dbmanger.retrieveDataEntryTypeList(username);
		

		//String selectedNode = null;

		tree.addTreeSelectionListener(new TreeSelectionListener() {

			public void valueChanged(TreeSelectionEvent e) {

				JTree tree = (JTree) e.getSource();

				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

				String selectedNodeName = selectedNode.toString();

				panel_east.removeAll();

				JPanel panel = new JPanel();
				DataEntryPanel dataPanel = new DataEntryPanel();
				DataEntry currentdata = new DataEntry(null, null, null, null, 0, null);

				String panelName = null;
/*
				if (selectedNodeName.equals("Account Login")) {
					panel = dataPanel.getAccountLoginPanel(currentdata);
					panelName = "Account Login";
					panel_east.addTab(panelName, null, panel, null);
				}
				if (selectedNodeName.equals("Credit/Debit Card")) {

					panel = dataPanel.getCreditCardPanel(currentdata);
					panelName = "Credit/Debit Card";
					panel_east.addTab(panelName, null, panel, null);
				}
				if (selectedNodeName.equals("License")) {

					panel = dataPanel.getLicensePanel(currentdata);
					panelName = "License";
					panel_east.addTab(panelName, null, panel, null);
				}
				if (selectedNodeName.equals("Phone Number")) {

					panel = dataPanel.getLicensePanel(currentdata);
					panelName = "Phone Number";
					panel_east.addTab(panelName, null, panel, null);
				}
				if (selectedNodeName.equals("SSN")) {

					panel = dataPanel.getLicensePanel(currentdata);
					panelName = "SSN";
					panel_east.addTab(panelName, null, panel, null);
				}
				if (selectedNodeName.equals("Wifi Network")) {

					panel = dataPanel.getLicensePanel(currentdata);
					panelName = "Wifi Network";
					panel_east.addTab(panelName, null, panel, null);
				}
*/			

				System.out.println(selectedNodeName);

				//if data item
				if (selectedNode.isLeaf()) {
					DatabaseManager dm = new DatabaseManager();

					DataEntry selectedDataEntry = dm.retrieveOneDataEntry(selectedNodeName, username, selectedNode.getParent().toString());
					currentEntry = selectedDataEntry;
//					System.out.println("Here");

					
					System.out.println(selectedNodeName);
					System.out.println(username);
					System.out.println(selectedNode.getParent().toString());

/*
					System.out.println(selectedDataEntry.getFieldDataList().get(0).toString());
					System.out.println(selectedDataEntry.getFieldDataList().get(1).toString());
					System.out.println(selectedDataEntry.getFieldDataList().get(2).toString());
					System.out.println(selectedDataEntry.getFieldDataList().get(3).toString());
*/
					//retrieveOneDataEntry (selectedNodeName, currentUser, selectedNode.getParent().toString());

					if(selectedNode.getParent().toString() == "Account Login") {
						panel = dataPanel.getAccountLoginPanelWithData(selectedDataEntry);
						panelName = "Account Login";
					}
					else if(selectedNode.getParent().toString() == "Credit/Debit Card") {
						panel = dataPanel.getCreditCardPanelWithData(selectedDataEntry);
						panelName = "Credit/Debit Card";
					}
					else if(selectedNode.getParent().toString() == "License") {
						panel = dataPanel.getLicensePaneWithData(selectedDataEntry);
						panelName = "License";
					}
					else if(selectedNode.getParent().toString() == "Phone Number") {
						panel = dataPanel.getPhoneNumberPaneWithData(selectedDataEntry);
						panelName = "Phone Number";
					}
					else if(selectedNode.getParent().toString() == "SSN") {
						panel = dataPanel.getSSNWithData(selectedDataEntry);
						panelName = "SSN";
					}
					else if(selectedNode.getParent().toString() == "Wifi Network") {
						panel = dataPanel.getWifiNetworkData(selectedDataEntry);
						panelName = "Wifi Network";
					}


					panel_east.addTab(panelName, null, panel, null);


					System.out.println("Leaf: " + selectedNodeName);
				}
				return;
			}
			/*
			private DataEntry retrieveOneDataEntry(String selectedNodeName, User currentUser, String string) {
				// TODO Auto-generated method stub
				return null;

			}*/
		});

		currentUser = dbmanger.retrieveUserFromDatabase(username);

		currentDataEntryNameList = dbmanger.retrieveDataEntryNameList(username);
		currentDataEntryTypeList = dbmanger.retrieveDataEntryTypeList(username);
		
		
		DefaultMutableTreeNode node_0 = new DefaultMutableTreeNode("Account Login");
		DefaultMutableTreeNode node_1 = new DefaultMutableTreeNode("Credit/Debit Card");
		DefaultMutableTreeNode node_2 = new DefaultMutableTreeNode("License");
		DefaultMutableTreeNode node_3 = new DefaultMutableTreeNode("Passport");
		DefaultMutableTreeNode node_4 = new DefaultMutableTreeNode("Phone Number");
		DefaultMutableTreeNode node_5 = new DefaultMutableTreeNode("SSN");
		DefaultMutableTreeNode node_6 = new DefaultMutableTreeNode("Wifi Network");


		tree.setModel(new DefaultTreeModel(
				new DefaultMutableTreeNode("Data Entry") {
					{
						if(currentDataEntryTypeList.contains("Account Login")) {
							add(node_0);
						}
						if(currentDataEntryTypeList.contains("Credit/Debit Card")) {
							add(node_1);
						}
						if(currentDataEntryTypeList.contains("License")) {
							add(node_2);
						}
						//add(node_3);
						if(currentDataEntryTypeList.contains("Phone Number")) {
							add(node_4);
						}	
						if(currentDataEntryTypeList.contains("SSN")) {
							add(node_5);
						}	
						if(currentDataEntryTypeList.contains("Wifi Network")) {
							add(node_6);
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
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();

		System.out.println("currentDataEntryNameList :" + currentDataEntryNameList.size());
		System.out.println("currentDataEntryTypeList :" + currentDataEntryTypeList.size());
		System.out.println("number of root :" + root.getChildCount());


		for (int i=0; i < currentDataEntryTypeList.size(); i++) {
			DefaultMutableTreeNode child = new DefaultMutableTreeNode(currentDataEntryNameList.get(i));

			if (currentDataEntryTypeList.get(i).equals("Account Login")) {
				System.out.println(currentDataEntryTypeList.get(i));
				node_0.add(child);
			}
			else if (currentDataEntryTypeList.get(i).equals("Credit/Debit Card")) {
				System.out.println(currentDataEntryTypeList.get(i));
				node_1.add(child);	
			}
			else if (currentDataEntryTypeList.get(i).equals("License")) {
				System.out.println(currentDataEntryTypeList.get(i));
				node_2.add(child);	
			}/*
			else if (currentDataEntryTypeList.get(i).equals("Passport")) {
				System.out.println(currentDataEntryTypeList.get(i));
				node_3.add(child);	
			}*/
			else if (currentDataEntryTypeList.get(i).equals("Phone Number")) {
				System.out.println(currentDataEntryTypeList.get(i));
				node_4.add(child);	
			}
			else if (currentDataEntryTypeList.get(i).equals("SSN")) {
				System.out.println(currentDataEntryTypeList.get(i));
				node_5.add(child);	
			}
			else if (currentDataEntryTypeList.get(i).equals("Wifi Network")) {
				System.out.println(currentDataEntryTypeList.get(i));
				node_6.add(child);	
			}

		}

		panel_center.addTab("Category", null, tree, null);
		
		JList list = new JList();
		panel_center.addTab("Item List", null, list, null);

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
