import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JList;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JDesktopPane;
import java.awt.Choice;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JFormattedTextField;
import javax.swing.JProgressBar;
import javax.swing.JTree;
import javax.swing.SpinnerListModel;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.FlowLayout;
import java.awt.Window;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;

public class HomeView{

	public JFrame frmSentinelDataVault;
	private JTextField textField_1;
	private JTextField textField;
	private JTextField txtDataamount;
	private JTextField textField_3;

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
		frmSentinelDataVault.setTitle("Sentinel Data Vault");
		frmSentinelDataVault.setBounds(100, 100, 700, 450);
		frmSentinelDataVault.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSentinelDataVault.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel_North = new JPanel();
		frmSentinelDataVault.getContentPane().add(panel_North, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("New Data");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnNewButton_3 = new JButton("Lock");
		panel_North.add(btnNewButton_3);
		
		JButton btnNewButton_2 = new JButton("Data");
		panel_North.add(btnNewButton_2);
		panel_North.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Edit Data");
		panel_North.add(btnNewButton_1);
		
		JButton btnShareData = new JButton("Share Data");
		btnShareData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel_North.add(btnShareData);
		
		JButton btnSecurity = new JButton("Security");
		
		btnSecurity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SecurityView regFace =new SecurityView();
				((Window) regFace.frmSentinelDataVault).setVisible(true);
				
			}
		});
		
		panel_North.add(btnSecurity);
		
		JPanel panel_West = new JPanel();
		frmSentinelDataVault.getContentPane().add(panel_West, BorderLayout.WEST);
		panel_West.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("16px"),
				ColumnSpec.decode("117px:grow"),},
			new RowSpec[] {
				RowSpec.decode("29px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		txtDataamount = new JTextField();
		txtDataamount.setText("DataAmount");
		txtDataamount.setColumns(10);
		panel_West.add(txtDataamount, "2, 3, fill, default");
		
		textField_3 = new JTextField();
		textField_3.setText("Username");
		textField_3.setColumns(10);
		panel_West.add(textField_3, "2, 5, fill, default");
		
		JPanel panel_South = new JPanel();
		frmSentinelDataVault.getContentPane().add(panel_South, BorderLayout.SOUTH);
		
		JProgressBar progressBar = new JProgressBar();
		panel_South.add(progressBar);
		
		//JProgressBar progressBar = new JProgressBar();
		//panel_3.add(progressBar);
		
		JTabbedPane tabbedPane_East = new JTabbedPane(JTabbedPane.TOP);
		frmSentinelDataVault.getContentPane().add(tabbedPane_East, BorderLayout.EAST);
		
		JPanel panel_DebitCard = new JPanel();
		tabbedPane_East.addTab("Debit Card", null, panel_DebitCard, null);
		panel_DebitCard.setLayout(new MigLayout("", "[130px,grow][128px,grow]", "[26px][][][][][][][][][][]"));
		
		JLabel lblCreditCard = new JLabel("Debit Card Type");
		panel_DebitCard.add(lblCreditCard, "cell 0 0");
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerListModel(new String[] {"VISA", "MasterCard", "AMERICAN EXPRESS", "DISCOVER", "PayPal"}));
		panel_DebitCard.add(spinner, "cell 0 1, growx");
		
		JLabel lblNewLabel = new JLabel("Card Number");
		panel_DebitCard.add(lblNewLabel, "cell 0 2");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		panel_DebitCard.add(textField_1, "cell 0 3 2 1,growx");
		
		JLabel lblNameOnCard = new JLabel("Name on Card");
		panel_DebitCard.add(lblNameOnCard, "cell 0 4");
		
		textField = new JTextField();
		panel_DebitCard.add(textField, "cell 0 5 2 1,growx");
		textField.setColumns(10);
		
		JLabel lblCvv = new JLabel("CVV");
		panel_DebitCard.add(lblCvv, "cell 0 6");
		
		JFormattedTextField frmtdtxtfldXxx = new JFormattedTextField();
		frmtdtxtfldXxx.setText("* * *");
		panel_DebitCard.add(frmtdtxtfldXxx, "cell 0 7,growx");
		
		JLabel lblExpiration = new JLabel("Expiration Date");
		panel_DebitCard.add(lblExpiration, "cell 0 8");
		
		JLabel lblPin = new JLabel("PIN");
		panel_DebitCard.add(lblPin, "cell 1 8");
		
		JFormattedTextField frmtdtxtfldMmYy = new JFormattedTextField();
		frmtdtxtfldMmYy.setText("MM / YY");
		panel_DebitCard.add(frmtdtxtfldMmYy, "cell 0 9,growx");
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setText("* * * *");
		panel_DebitCard.add(formattedTextField, "cell 1 9,growx");
		
		JButton btnExport = new JButton("Export");
		panel_DebitCard.add(btnExport, "cell 1 10,alignx right");
		
		JTabbedPane tabbedPane_Center = new JTabbedPane(JTabbedPane.TOP);
		frmSentinelDataVault.getContentPane().add(tabbedPane_Center, BorderLayout.CENTER);
		
		JTree tree = new JTree();
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("User Name") {
				{
					DefaultMutableTreeNode node_1;
					node_1 = new DefaultMutableTreeNode("Login Account ");
						node_1.add(new DefaultMutableTreeNode("Google"));
						node_1.add(new DefaultMutableTreeNode("Purdue"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Credit Card");
						node_1.add(new DefaultMutableTreeNode("Bank of America"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Debit Card");
						node_1.add(new DefaultMutableTreeNode("Chase"));
						node_1.add(new DefaultMutableTreeNode("Purdue Federal Credit Union\t\t"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Serial Number");
						node_1.add(new DefaultMutableTreeNode("MS Office "));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("License");
						node_1.add(new DefaultMutableTreeNode("Driver's License"));
					add(node_1);
				}
			}
		));
		tabbedPane_Center.addTab("List", null, tree, null);
		
		Choice choice = new Choice();
		tabbedPane_Center.addTab("Category", null, choice, null);
		
		
		
		JMenuBar menuBar = new JMenuBar();
		frmSentinelDataVault.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Exit");
		mnFile.add(mntmNewMenuItem);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenu mnSearch = new JMenu("Search");
		menuBar.add(mnSearch);
		
		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
	}
}
