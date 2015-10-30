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
//	panel_west
		JPanel panel_west = new JPanel();
		panel_west.setBounds(8, 8, 335, 352); // margins between object 8 
		frmSentinelDataVault.getContentPane().add(panel_west);
		panel_west.setLayout(null);
		
		JLabel background_west = new JLabel("New label");
		background_west.setBounds(0, 0, 335, 352);
		panel_west.add(background_west);
		background_west.setIcon(new ImageIcon("/Users/Jiho/Desktop/사진/modified 6790918-free-background-wallpaper.png"));

		//	panel_east		
		JPanel panel_east = new JPanel();
		panel_east.setBounds(348, 8, 346, 352);
		frmSentinelDataVault.getContentPane().add(panel_east);
		panel_east.setLayout(null);
		
		JLabel background_east = new JLabel("");
		background_east.setIcon(new ImageIcon("/Users/Jiho/Desktop/사진/modified 6790918-free-background-wallpaper.png"));
		background_east.setBounds(0, 0, 346, 352);
		panel_east.add(background_east);
			
//		anel_south		
		JPanel panel_south = new JPanel();
		panel_south.setBounds(8, 368, 684, 78);			// margins between object 8 
		panel_south.setOpaque(false); // to set transparent panel
		frmSentinelDataVault.getContentPane().add(panel_south);
		panel_south.setLayout(new GridLayout(0, 3, 0, 0));
	
		JButton btnSignOut = new JButton("Sign Out");
		panel_south.add(btnSignOut);
		
		JButton btnSetting = new JButton("Setting");
		panel_south.add(btnSetting);
		
		JButton btnSecurity = new JButton("Security");
		panel_south.add(btnSecurity);
		

		
		JLabel background_main = new JLabel("");
		background_main.setBounds(0, 0, 700, 456);
		background_main.setIcon(new ImageIcon("/Users/Jiho/Desktop/사진/6790918-free-background-wallpaper.jpg"));
		frmSentinelDataVault.getContentPane().add(background_main);
		

		
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
