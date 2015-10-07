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
		frmSentinelDataVault.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(237, 88, 117, 29);
		frmSentinelDataVault.getContentPane().add(btnNewButton);
		//btnNewButton.
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(126, 56, 117, 29);
		frmSentinelDataVault.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setBounds(6, 52, 117, 29);
		frmSentinelDataVault.getContentPane().add(btnNewButton_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(6, 50, 166, 102);
		frmSentinelDataVault.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(6, 152, 166, 206);
		frmSentinelDataVault.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JList list = new JList();
		list.setBounds(311, 57, 224, 301);
		frmSentinelDataVault.getContentPane().add(list);
		
		JTree tree = new JTree();
		tree.setBounds(174, 56, 131, 301);
		frmSentinelDataVault.getContentPane().add(tree);

	/*
	 * MenuBar
	 * */
		
		JMenuBar menuBar = new JMenuBar();

		JMenu file = new JMenu("File");
		JMenu edit = new JMenu("Edit");
		JMenu help = new JMenu("Help");
		JMenuItem newMenuItem = new JMenuItem("New");
		JMenuItem openMenuItem = new JMenuItem("Open");
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		
		menuBar.add(file);
			file.add(newMenuItem);
			file.add(openMenuItem);
			file.add(exitMenuItem);
		menuBar.add(edit);
		menuBar.add(help);
		
		
		menuBar.setBounds(10, 10, 120, 20);
		
		frmSentinelDataVault.getContentPane().add(menuBar);
		
		
	}
}
