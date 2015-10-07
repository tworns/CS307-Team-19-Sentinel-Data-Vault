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
	private JTextField textField_1;
	private JTextField textField_2;

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
		frmSentinelDataVault.setTitle("Sentinel Data Vault");
		frmSentinelDataVault.setBounds(100, 100, 541, 386);
		frmSentinelDataVault.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnNewButton = new JButton("New Data");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		frmSentinelDataVault.getContentPane().setLayout(new GridLayout(0, 6, 0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		
				JMenu file = new JMenu("File");
				JMenu edit = new JMenu("Edit");
				JMenu help = new JMenu("Help");
				JMenuItem newMenuItem = new JMenuItem("New");
				JMenuItem openMenuItem = new JMenuItem("Open");
				JMenuItem exitMenuItem = new JMenuItem("Exit");
				JMenuItem properties = new JMenuItem("Properties");
				JMenuItem setting = new JMenuItem("Setting");
				JMenuItem search = new JMenuItem("Search");
				
				menuBar.add(file);
				file.add(newMenuItem);
				file.add(openMenuItem);
				file.add(exitMenuItem);
				
				menuBar.add(edit);
				edit.add(properties);
				edit.add(setting);
				
				menuBar.add(help);
				help.add(search);
				
				frmSentinelDataVault.getContentPane().add(menuBar);
		
		JLabel label = new JLabel("");
		frmSentinelDataVault.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("");
		frmSentinelDataVault.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("");
		frmSentinelDataVault.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("");
		frmSentinelDataVault.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("");
		frmSentinelDataVault.getContentPane().add(label_4);
		frmSentinelDataVault.getContentPane().add(btnNewButton);
		
		JButton btnEdit = new JButton("Edit Data");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		frmSentinelDataVault.getContentPane().add(btnEdit);
		
		JLabel label_5 = new JLabel("");
		frmSentinelDataVault.getContentPane().add(label_5);
		
		JButton button_1 = new JButton("Edit Data");
		frmSentinelDataVault.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("Edit Data");
		frmSentinelDataVault.getContentPane().add(button_2);
		
		JList list = new JList();
		frmSentinelDataVault.getContentPane().add(list);
		
		textField_1 = new JTextField();
		frmSentinelDataVault.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label_6 = new JLabel("");
		frmSentinelDataVault.getContentPane().add(label_6);
		
		JTree tree = new JTree();
		frmSentinelDataVault.getContentPane().add(tree);
		
		JLabel label_7 = new JLabel("");
		frmSentinelDataVault.getContentPane().add(label_7);
		
		JLabel label_8 = new JLabel("");
		frmSentinelDataVault.getContentPane().add(label_8);
		
		JLabel label_9 = new JLabel("");
		frmSentinelDataVault.getContentPane().add(label_9);
		
		JButton button = new JButton("New button");
		frmSentinelDataVault.getContentPane().add(button);
		
		textField_2 = new JTextField();
		frmSentinelDataVault.getContentPane().add(textField_2);
		textField_2.setColumns(10);

	/*
	 * MenuBar
	 * */
		
		
	}
}
