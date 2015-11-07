package userInterface;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.GridLayout;
import javax.swing.JTree;
import net.miginfocom.swing.MigLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import controllers.DatabaseManager;
import dataManagement.DataEntry;
import userInterface.DataEntryPanel;


import javax.swing.JList;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.AbstractListModel;
import javax.swing.event.TreeSelectionListener;

public class HomeView {

	private JFrame frmSentinelDataVault;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeView window = new HomeView();
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
	public HomeView() {
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

		//		anel_south		
		JPanel panel_north = new JPanel();
		panel_north.setBounds(6, 6, 688, 50);			// margins between object 8 
		panel_north.setOpaque(false); // to set transparent panel
		frmSentinelDataVault.getContentPane().add(panel_north);

		JButton btnSignOut = new JButton("Sign Out");
		btnSignOut.setFont(new Font("Lucida Grande", Font.PLAIN, 11));

		JButton btnSetting = new JButton("Setting");
		btnSetting.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		btnSetting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		JButton btnAddData = new JButton("Add Data");
		btnAddData.setFont(new Font("Lucida Grande", Font.PLAIN, 11));

		JButton btnSecurity = new JButton("Security");
		btnSecurity.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		btnSecurity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		JButton btnDeleteData = new JButton("Delete Data");
		btnDeleteData.setFont(new Font("Lucida Grande", Font.PLAIN, 11));

		JLabel lblEmpty_1 = new JLabel("     ");

		JLabel lblEmpty_2 = new JLabel("     ");

		JButton btnEditData = new JButton("Edit Data");
		btnEditData.setFont(new Font("Lucida Grande", Font.PLAIN, 11));

		GroupLayout gl_panel_north = new GroupLayout(panel_north);
		gl_panel_north.setHorizontalGroup(
				gl_panel_north.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_north.createSequentialGroup()
						.addComponent(btnSignOut, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
						.addGap(6)
						.addComponent(btnSetting, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblEmpty_1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnAddData, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnDeleteData, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblEmpty_2, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnEditData, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnSecurity, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
						.addGap(326))
				);
		gl_panel_north.setVerticalGroup(
				gl_panel_north.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_north.createSequentialGroup()
						.addGroup(gl_panel_north.createParallelGroup(Alignment.LEADING)
								.addComponent(btnSignOut, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_north.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnSetting, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnAddData, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnDeleteData, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnSecurity, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblEmpty_1)
										.addComponent(lblEmpty_2)
										.addComponent(btnEditData, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
		panel_north.setLayout(gl_panel_north);

		JPanel panel_south = new JPanel();
		panel_south.setBounds(0, 55, 700, 395);
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
				.addGroup(gl_panel_south.createSequentialGroup()
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
								.addComponent(panel_west, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
								.addComponent(panel_east, GroupLayout.PREFERRED_SIZE, 394, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel_center, GroupLayout.PREFERRED_SIZE, 395, GroupLayout.PREFERRED_SIZE))
						.addContainerGap())
				);

		//panel_west		
		textField = new JTextField();
		textField.setText("<dynamic>");
		textField.setColumns(10);

		JLabel lblLastLogin = new JLabel("Last Login");

		JLabel lblUserEmail = new JLabel("User Email");

		textField_1 = new JTextField();
		textField_1.setText("2015.10.15 PM 7:30");
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
		String selectedNode = null;

		tree.addTreeSelectionListener(new TreeSelectionListener() {


			public void valueChanged(TreeSelectionEvent e) {
				JTree tree = (JTree) e.getSource();
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree
						.getLastSelectedPathComponent();
				String selectedNodeName = selectedNode.toString();

				JPanel panel = new JPanel();
				DataEntryPanel dataPanel = new DataEntryPanel();
				DataEntry currentdata = new DataEntry(null, null, null, null, 0, null);
				String panelName = null;
				
				if (selectedNodeName.equals("Account Login")) {
					panel = dataPanel.getAccountLoginPanel(currentdata);
					panelName = "Account Login";
					panel_east.addTab(panelName, null, panel, null);
				}
				if (selectedNodeName.equals("Credit Card")) {
					panel = dataPanel.getCreditCardPanel(currentdata);
					panelName = "Credit Card";
					panel_east.addTab(panelName, null, panel, null);
				}
				
				
				
				System.out.println(selectedNodeName);
				
				panel.disable();
				
				/*				
				// if the data object is Account Login 
				if (selectedNodeName.equals("Account Login")) {
				panel = dataPanel.getAccountLoginPanel(currentdata);
				 panelName = "Account Login";
				}


				// if the data object is Credit Card
				if (selectedNodeName.equals("Credit Card")) {
					panel = dataPanel.getCreditCardPanel(currentdata);
					panelName = "Credit Card";
				}
				 */				
				// if the data object is License  
				//	panel = dataPanel.getDriversLicensePanel(data);
				//	panelName = "Driver's License  ";


				//if data item
				if (selectedNode.isLeaf()) {

					System.out.println(selectedNodeName);

				}

				return;

			}
		});

		tree.setModel(new DefaultTreeModel(
				new DefaultMutableTreeNode("User") {
					{
						DefaultMutableTreeNode node_1;
						node_1 = new DefaultMutableTreeNode("Account Login");

						node_1.add(new DefaultMutableTreeNode("Google"));
						node_1.add(new DefaultMutableTreeNode("Apple"));
						node_1.add(new DefaultMutableTreeNode("Purdue"));
						add(node_1);
						node_1 = new DefaultMutableTreeNode("Credit Card");
						node_1.add(new DefaultMutableTreeNode("Visa"));
						node_1.add(new DefaultMutableTreeNode("Master"));
						add(node_1);
						node_1 = new DefaultMutableTreeNode("Driver's License");
						node_1.add(new DefaultMutableTreeNode("Driver's Lisence"));
						add(node_1);
					}
				}
				));



		panel_center.addTab("Category", null, tree, null);









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
