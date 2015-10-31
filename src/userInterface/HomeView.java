package userInterface;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.GridLayout;
import javax.swing.JTree;
import net.miginfocom.swing.MigLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import java.awt.Color;

public class HomeView {

	private JFrame frmSentinelDataVault;

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
		frmSentinelDataVault.setBounds(100, 100, 700, 500);
		frmSentinelDataVault.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSentinelDataVault.getContentPane().setLayout(null);
			
//		anel_south		
		JPanel panel_north = new JPanel();
		panel_north.setBounds(0, 0, 700, 50);			// margins between object 8 
		panel_north.setOpaque(false); // to set transparent panel
		frmSentinelDataVault.getContentPane().add(panel_north);
		
			JButton btnSignOut = new JButton("Sign Out");
		
		JButton btnSetting = new JButton("Setting");
		btnSetting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnAddData = new JButton("Add Data");
		
		JButton btnSecurity = new JButton("Security");
		btnSecurity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnDeleteData = new JButton("Delete");
		GroupLayout gl_panel_north = new GroupLayout(panel_north);
		gl_panel_north.setHorizontalGroup(
			gl_panel_north.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_north.createSequentialGroup()
					.addGap(0, 0, Short.MAX_VALUE)
					.addComponent(btnSignOut, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSetting, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAddData, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnDeleteData, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSecurity, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addGap(462))
		);
		gl_panel_north.setVerticalGroup(
			gl_panel_north.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_north.createSequentialGroup()
					.addGroup(gl_panel_north.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSignOut, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
						.addComponent(btnAddData, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
						.addComponent(btnSetting, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
						.addComponent(btnDeleteData, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
						.addComponent(btnSecurity, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
					.addGap(0))
		);
		panel_north.setLayout(gl_panel_north);
		

		
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
