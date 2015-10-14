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

public class HomeView{

	public JFrame frmSentinelDataVault;
	private JTextField textField_1;
	private JTextField textField;

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
		frmSentinelDataVault.setBounds(100, 100, 650, 450);
		frmSentinelDataVault.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSentinelDataVault.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frmSentinelDataVault.getContentPane().add(panel, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("New Data");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnNewButton_3 = new JButton("Lock");
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_2 = new JButton("Data");
		panel.add(btnNewButton_2);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Edit Data");
		panel.add(btnNewButton_1);
		
		JPanel panel_1 = new JPanel();
		frmSentinelDataVault.getContentPane().add(panel_1, BorderLayout.WEST);
		
		JButton btnNewButton_4 = new JButton("New button");
		panel_1.add(btnNewButton_4);
		
		JPanel panel_2 = new JPanel();
		frmSentinelDataVault.getContentPane().add(panel_2, BorderLayout.EAST);
		panel_2.setLayout(new MigLayout("", "[130px,grow][128px]", "[26px][][][][][][][][][][]"));
		
		JLabel lblCreditCard = new JLabel("Debit Card");
		panel_2.add(lblCreditCard, "cell 0 0");
		
		JSpinner spinner = new JSpinner();
		panel_2.add(spinner, "cell 0 2");
		
		JLabel lblNewLabel = new JLabel("Card Number");
		panel_2.add(lblNewLabel, "cell 0 3");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		panel_2.add(textField_1, "cell 0 4 2 1,growx");
		
		JLabel lblNameOnCard = new JLabel("Name on Card");
		panel_2.add(lblNameOnCard, "cell 0 5");
		
		textField = new JTextField();
		panel_2.add(textField, "cell 0 6 2 1,growx");
		textField.setColumns(10);
		
		JLabel lblCvv = new JLabel("CVV");
		panel_2.add(lblCvv, "cell 0 7");
		
		JFormattedTextField frmtdtxtfldXxx = new JFormattedTextField();
		frmtdtxtfldXxx.setText("* * *");
		panel_2.add(frmtdtxtfldXxx, "cell 0 8,growx");
		
		JLabel lblExpiration = new JLabel("Expiration Date");
		panel_2.add(lblExpiration, "cell 0 9");
		
		JFormattedTextField frmtdtxtfldMmYy = new JFormattedTextField();
		frmtdtxtfldMmYy.setText("MM / YY");
		panel_2.add(frmtdtxtfldMmYy, "cell 0 10,growx");
		
		JPanel panel_3 = new JPanel();
		frmSentinelDataVault.getContentPane().add(panel_3, BorderLayout.SOUTH);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmSentinelDataVault.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JList list = new JList();
		tabbedPane.addTab("List", null, list, null);
		
		JDesktopPane desktopPane = new JDesktopPane();
		tabbedPane.addTab("Category", null, desktopPane, null);
		
		Choice choice = new Choice();
		tabbedPane.addTab("item", null, choice, null);
		
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
