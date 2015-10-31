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
import java.awt.Font;

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
		panel_north.setBounds(6, 6, 688, 50);			// margins between object 8 
		panel_north.setOpaque(false); // to set transparent panel
		frmSentinelDataVault.getContentPane().add(panel_north);
		
			JButton btnSignOut = new JButton("Sign Out");
		
		JButton btnSetting = new JButton("Setting");
		btnSetting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnAddData = new JButton("Add Data");
		btnAddData.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		
		JButton btnSecurity = new JButton("Security");
		btnSecurity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnDeleteData = new JButton("Delete Data");
		btnDeleteData.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		
		JLabel lblEmpty_1 = new JLabel("     ");
		
		JLabel lblEmpty_2 = new JLabel("     ");
		GroupLayout gl_panel_north = new GroupLayout(panel_north);
		gl_panel_north.setHorizontalGroup(
			gl_panel_north.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_north.createSequentialGroup()
					.addComponent(btnSignOut, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(btnSetting, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblEmpty_1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAddData, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnDeleteData, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblEmpty_2, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSecurity, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
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
							.addComponent(lblEmpty_2)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_north.setLayout(gl_panel_north);
		
		JPanel panel_south = new JPanel();
		panel_south.setBounds(0, 55, 700, 395);
		frmSentinelDataVault.getContentPane().add(panel_south);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		GroupLayout gl_panel_south = new GroupLayout(panel_south);
		gl_panel_south.setHorizontalGroup(
			gl_panel_south.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_south.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel_south.setVerticalGroup(
			gl_panel_south.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_south.createSequentialGroup()
					.addGroup(gl_panel_south.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 394, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 395, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel_south.setLayout(gl_panel_south);
		

		
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
