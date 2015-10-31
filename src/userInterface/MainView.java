package userInterface;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Panel;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JProgressBar;
import javax.swing.JTree;
import javax.swing.JDesktopPane;
import javax.swing.tree.DefaultTreeModel;
import dataManagement.User;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;

public class MainView {

	public JFrame frmSentinelDataVault;
	private JTextField txtNameOnCard;
	private JTextField txtUserId;
	private JTextField txtPassword;
	private JTextField txtUserName;
	private JTextField txtPm;
	public String username;
	public User currentUser;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String s = "username";
					MainView window = new MainView(s);
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
	public MainView(String username) {
		this.username = username;
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSentinelDataVault = new JFrame();
		frmSentinelDataVault.setTitle("Sentinel Data Vault");
		frmSentinelDataVault.setBounds(100, 100, 700, 450);
		frmSentinelDataVault.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSentinelDataVault.getContentPane().setLayout(new BoxLayout(frmSentinelDataVault.getContentPane(), BoxLayout.X_AXIS));
		
		
		
		JPanel panel = new JPanel();
		frmSentinelDataVault.getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		Panel panel_1 = new Panel();
		panel.add(panel_1, BorderLayout.NORTH);
		
		JButton btnLock = new JButton("Lock");
		btnLock.setToolTipText("Lock mode for data in but not data out");
		panel_1.add(btnLock);
		
		JButton btnNewDataEntry = new JButton("New Data Entry");
		btnNewDataEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewDataEntryView newDataEntry = new NewDataEntryView();
				newDataEntry.getJframe().setVisible(true);
			}
		});
		panel_1.add(btnNewDataEntry);
		
		JButton btnNewData = new JButton("Delete Entry");
		btnNewData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] options = { "OK", "CANCEL" };
				int result = JOptionPane.showOptionDialog(null,
						"Data Entry will be deleted permanently.\nAre you sure you want to delete the Entry?", "Warning", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
						null, options, options[1]);
				if (result == 0) {
					System.out.println("Delete Entry");
					//TODO: Delete Entry from database
				}
			}
		});
		panel_1.add(btnNewData);
		
		JButton btnEditData = new JButton("Edit Data");
		panel_1.add(btnEditData);
		
		JButton btnSecurity = new JButton("Security");
		btnSecurity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SecurityView regFace =new SecurityView();
				regFace.frame.setVisible(true);
				// frmSentinelDataVault.dispose();
			}
			
		});
		panel_1.add(btnSecurity);
		
		JButton btnSetting = new JButton("Setting");
		btnSetting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SettingsView frmSettings = new SettingsView(currentUser);
				frmSettings.currentUser = currentUser;
				frmSettings.frmSettings.setVisible(true);
			}
		});
		panel_1.add(btnSetting);
		
		
		/*	TabbedPane	*/
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel.add(tabbedPane, BorderLayout.EAST);
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("New Data", null, panel_5, null);
		panel_5.setLayout(new MigLayout("", "[]", "[][][][][][][][][]"));
		
		JButton btnNewLoginAccount = new JButton("new Login Account");
		panel_5.add(btnNewLoginAccount, "cell 0 0,growx");
		
		JButton btnNewCreditCard = new JButton("new Credit Card");
		panel_5.add(btnNewCreditCard, "cell 0 1,growx");
		
		JButton btnNewDebitCard = new JButton("new Debit Card");
		panel_5.add(btnNewDebitCard, "cell 0 2,growx");
		
		JButton btnNewLicense = new JButton("new License");
		panel_5.add(btnNewLicense, "cell 0 3,growx");
		
		JButton btnNewSerialNumber = new JButton("new Serial Number");
		panel_5.add(btnNewSerialNumber, "cell 0 4,growx");
		
		JButton btnExportAllData = new JButton("Export All Data");
		panel_5.add(btnExportAllData, "cell 0 8,growx");
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Debit Card", null, panel_4, null);
		panel_4.setLayout(new MigLayout("", "[78px,grow][85px]", "[29px][][][][][][][][][][]"));
		
		JLabel lblDebitCardType = new JLabel("Debit Card Type");
		panel_4.add(lblDebitCardType, "cell 0 0");
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"VISA", "Master", "Discovery", "AMERICAN EXPRESS"}));
		panel_4.add(comboBox, "cell 0 1");
		
		JLabel lblCardNumber = new JLabel("Card Number");
		panel_4.add(lblCardNumber, "cell 0 2");
		
		
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setText("**** **** **** ****");
		panel_4.add(formattedTextField, "cell 0 3,growx");
		
		JLabel lblNameOnCard = new JLabel("Name on Card");
		panel_4.add(lblNameOnCard, "cell 0 4");
		
		txtNameOnCard = new JTextField();
		txtNameOnCard.setText("Name on Card");
		panel_4.add(txtNameOnCard, "cell 0 5,growx");
		txtNameOnCard.setColumns(10);
		
		JLabel lblCvv = new JLabel("CVV");
		panel_4.add(lblCvv, "cell 0 6");
		
		JFormattedTextField formattedTextField_1 = new JFormattedTextField();
		formattedTextField_1.setText("* * *");
		panel_4.add(formattedTextField_1, "cell 0 7,growx");
		
		JLabel lblExpirationDate = new JLabel("Expiration Date");
		panel_4.add(lblExpirationDate, "cell 0 8");
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"01 ", "02 ", "03 ", "04 ", "05", "06 ", "07 ", "08 ", "09 ", "10", "11 ", "12"}));
		panel_4.add(comboBox_2, "flowx,cell 0 9");
		
		/*
		 * 
		 * comboBox Value
		 * */

		
		JButton btnShare = new JButton("Share");
		panel_4.add(btnShare, "flowx,cell 0 10,aligny top");
		
		JButton btnExport = new JButton("Export");
		panel_4.add(btnExport, "cell 0 10,alignx left,aligny top");
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"}));
		panel_4.add(comboBox_1, "cell 0 9");
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Account Login", null, panel_2, null);
		panel_2.setLayout(new MigLayout("", "[grow]", "[][][][][][][][][][][]"));
		
		JLabel lblDomainUrl = new JLabel("Domain URL");
		panel_2.add(lblDomainUrl, "cell 0 0");
		
		JFormattedTextField frmtdtxtfldGmailcom = new JFormattedTextField();
		frmtdtxtfldGmailcom.setText("gmail.com");
		panel_2.add(frmtdtxtfldGmailcom, "cell 0 1,growx");
		
		JLabel lblUserId = new JLabel("User ID");
		panel_2.add(lblUserId, "cell 0 2");
		
		txtUserId = new JTextField();
		txtUserId.setText("User ID");
		panel_2.add(txtUserId, "cell 0 3,growx");
		txtUserId.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		panel_2.add(lblPassword, "cell 0 4");
		
		txtPassword = new JTextField();
		txtPassword.setText("Password");
		panel_2.add(txtPassword, "cell 0 5,growx");
		txtPassword.setColumns(10);
		
		JButton btnShare_1 = new JButton("Share");
		panel_2.add(btnShare_1, "flowx,cell 0 10");
		
		JButton btnExport_1 = new JButton("Export");
		panel_2.add(btnExport_1, "cell 0 10");
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.WEST);
		panel_3.setLayout(new MigLayout("", "[170.00px,grow]", "[][][][][][][grow][]"));
		
		JButton btnSignOut = new JButton("Sign out");
		btnSignOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Locale locale = new Locale("EN", "US");
				JOptionPane.setDefaultLocale(locale);
				if(JOptionPane.showConfirmDialog(null, "Are You Sure?", "Sign Out",JOptionPane.YES_NO_OPTION, JOptionPane.DEFAULT_OPTION) == 0) {
					currentUser = null;
					LoginView frmLog = new LoginView();
					frmLog.frame.setVisible(true);
					frmSentinelDataVault.dispose();
				}
				
			}
		});
		panel_3.add(btnSignOut, "cell 0 0");
		
		JLabel lblUserName = new JLabel("Login Email");
		panel_3.add(lblUserName, "cell 0 1");
		
		txtUserName = new JTextField();
		txtUserName.setText(username);
		panel_3.add(txtUserName, "cell 0 2,growx");
		txtUserName.setColumns(10);
		
		JLabel lblLastLogin = new JLabel("Last Login");
		panel_3.add(lblLastLogin, "cell 0 3");
		
		txtPm = new JTextField();
		txtPm.setText("2015.10.15 PM 7:30");
		panel_3.add(txtPm, "cell 0 4,growx");
		txtPm.setColumns(10);
		
		JLabel lblDetails = new JLabel("Account Details");
		panel_3.add(lblDetails, "cell 0 5");
		
		JList list = new JList();
		panel_3.add(list, "flowx,cell 0 6,grow");
		
		JScrollBar scrollBar = new JScrollBar();
		panel_3.add(scrollBar, "cell 0 6");
		
		JButton btnDeleteAccount = new JButton("Delete Account");
		panel_3.add(btnDeleteAccount, "cell 0 7");
		
		JProgressBar progressBar = new JProgressBar();
		panel.add(progressBar, BorderLayout.SOUTH);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		panel.add(tabbedPane_1, BorderLayout.CENTER);
		
		JTree tree = new JTree();
		tree.setEditable(true);
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("User") {
				{
					DefaultMutableTreeNode node_1;
					node_1 = new DefaultMutableTreeNode("Login Account");
						node_1.add(new DefaultMutableTreeNode("Google"));
						node_1.add(new DefaultMutableTreeNode("iTunes"));
						node_1.add(new DefaultMutableTreeNode("Purdue"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Credit Card");
						node_1.add(new DefaultMutableTreeNode("Discovery"));
						node_1.add(new DefaultMutableTreeNode("Master"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Debit Card");
						node_1.add(new DefaultMutableTreeNode("Chase"));
						node_1.add(new DefaultMutableTreeNode("Purdue Federal Crdit Union"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("License");
						node_1.add(new DefaultMutableTreeNode("Driver's License"));
						node_1.add(new DefaultMutableTreeNode("SSN"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Serial Number");
						node_1.add(new DefaultMutableTreeNode("MS Office 2015"));
					add(node_1);
				}
			}
		));
		tabbedPane_1.addTab("List", null, tree, null);
		
		JDesktopPane desktopPane = new JDesktopPane();
		tabbedPane_1.addTab("Category", null, desktopPane, null);
		
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
