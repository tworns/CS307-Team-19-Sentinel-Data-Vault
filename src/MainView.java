import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JTree;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JDesktopPane;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class MainView {

	/*
	 * 
	 * MainView
	 * 2015 10 01 ~ 
	 * 1st Sprint
	 * Jiho Choi
	 * 
	 */
	
	private JFrame frmSentinelDataVault;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView window = new MainView();
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
	public MainView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSentinelDataVault = new JFrame();
		frmSentinelDataVault.setResizable(false);
		frmSentinelDataVault.getContentPane().setBackground(Color.WHITE);
		frmSentinelDataVault.setBackground(Color.WHITE);
		frmSentinelDataVault.setTitle("Sentinel Data Vault");
		frmSentinelDataVault.setBounds(100, 100, 700, 450);
		frmSentinelDataVault.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		
				JMenu file = new JMenu("File");
				JMenu edit = new JMenu("Edit");
				JMenu view = new JMenu("View");
				JMenu help = new JMenu("Help");
				JMenuItem newMenuItem = new JMenuItem("New");
				JMenuItem openMenuItem = new JMenuItem("Open");
				JMenuItem viewMenuItem = new JMenuItem("View");
				JMenuItem exitMenuItem = new JMenuItem("Exit");
				JMenuItem properties = new JMenuItem("Properties");
				JMenuItem setting = new JMenuItem("Setting");
				JMenuItem search = new JMenuItem("Search");
				
				menuBar.add(file);
				file.add(newMenuItem);
				file.add(openMenuItem);
				file.add(viewMenuItem);
				file.add(exitMenuItem);
				
				menuBar.add(edit);
				edit.add(properties);
				edit.add(setting);
				
				menuBar.add(view);
				
				menuBar.add(help);
				help.add(search);
		
		JLabel label = new JLabel("");
		
		JLabel label_1 = new JLabel("");
		
		JLabel label_2 = new JLabel("");
		
		JLabel label_3 = new JLabel("");
		
		JLabel label_4 = new JLabel("");
		
		JList list = new JList();
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("44px"),
				ColumnSpec.decode("75px"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("29px"),}));
		
		JButton btnData = new JButton("Data");
		panel_1.add(btnData, "2, 2, left, center");
		btnData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnNewData = new JButton("New Data");
		btnNewData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_1.add(btnNewData, "4, 2, left, top");
		
		JButton button_3 = new JButton("Edit Data");
		panel_1.add(button_3, "6, 2, left, top");
		frmSentinelDataVault.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("1px"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("208px:grow"),
				ColumnSpec.decode("331px"),},
			new RowSpec[] {
				RowSpec.decode("22px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("39px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(188dlu;default):grow"),}));
		frmSentinelDataVault.getContentPane().add(label_3, "1, 1, left, top");
		frmSentinelDataVault.getContentPane().add(label, "1, 1, left, top");
		frmSentinelDataVault.getContentPane().add(label_1, "1, 1, left, top");
		frmSentinelDataVault.getContentPane().add(label_2, "1, 1, left, top");
		frmSentinelDataVault.getContentPane().add(list, "1, 1, left, top");
		frmSentinelDataVault.getContentPane().add(label_4, "1, 1, left, top");
		frmSentinelDataVault.getContentPane().add(menuBar, "1, 1, 4, 1, fill, top");
		frmSentinelDataVault.getContentPane().add(panel_1, "3, 3, 2, 2, fill, top");
		
		JButton btnSort = new JButton("Sort");
		panel_1.add(btnSort, "8, 2, left, top");
		
		JButton btnNewButton = new JButton("Lock");
		panel_1.add(btnNewButton, "10, 2, left, top");
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		frmSentinelDataVault.getContentPane().add(tabbedPane_1, "3, 5, fill, fill");
		
		JPanel panel = new JPanel();
		tabbedPane_1.addTab("New tab", null, panel, null);
		
		JPanel panel_2 = new JPanel();
		tabbedPane_1.addTab("New tab", null, panel_2, null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		JTree tree = new JTree();
		tabbedPane.addTab("List", null, tree, null);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Category", null, panel_3, null);
		frmSentinelDataVault.getContentPane().add(tabbedPane, "4, 5, fill, fill");
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Item", null, panel_4, null);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

	/*
	 * MenuBar
	 * */
		
		
	}
}
