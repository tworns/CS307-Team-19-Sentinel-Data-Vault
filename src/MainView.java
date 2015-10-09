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
		frmSentinelDataVault.setBounds(100, 100, 600, 400);
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
		
		JButton btnData = new JButton("Data");
		btnData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JList list = new JList();
		
		JButton btnNewButton = new JButton("New Data");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton button_3 = new JButton("Edit Data");
		
		JTextPane textPane = new JTextPane();
		
		JComboBox comboBox = new JComboBox();
		
		JButton btnCategory = new JButton("");
		btnCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout groupLayout = new GroupLayout(frmSentinelDataVault.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(label_3)
						.addComponent(label)
						.addComponent(label_1)
						.addComponent(label_2)
						.addComponent(list)
						.addComponent(label_4)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(207)
									.addComponent(comboBox, 0, 26, Short.MAX_VALUE)
									.addGap(185))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnData)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(6)
											.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(button_3, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnCategory, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)))
									.addGap(53)))
							.addGap(116)))
					.addGap(0))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(textPane, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
					.addGap(395))
				.addComponent(menuBar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(label_3)
						.addComponent(label)
						.addComponent(label_1)
						.addComponent(label_2)
						.addComponent(list)
						.addComponent(label_4)
						.addComponent(menuBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnData, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
						.addComponent(button_3, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
						.addComponent(btnCategory, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
						.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE))
					.addGap(40)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		JTree tree = new JTree();
		tabbedPane.addTab("New tab", null, tree, null);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("New tab", null, panel, null);
		frmSentinelDataVault.getContentPane().setLayout(groupLayout);

	/*
	 * MenuBar
	 * */
		
		
	}
}
