package userInterface;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.tree.*;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;


public class HelpView {

	/*
	 * HelpView
	 * 2015 10 01 ~ 
	 * 1st Sprint
	 * Jiho Choi
	 * 
	 */
	
	
	public JFrame frmHelp;
	private JTextArea textPane;
	public String signingUp = 
			"To create a new account in the\n"
			+ "Sentinel Data Vault from the login screen:\n"
			+ "Click on the \"Create new Account Button\",\n"
			+ "When the account creation screen appears,\n"
			+ "fill in all the fields that are displayed,\n"
			+ "Keep in mind that passwords MUST include\n"
			+ "at least one of ALL of the following:\n"
			+ "Uppercase letter, lowercase letter,\n"
			+ "special character, and a Number.\n"
			+ "The username is the email address you\n"
			+ "wish to associate with the account.\n"
			+ "To create your account, click the\n"
			+ "\"Create my account\" button.\n"
			+ "Any fields that contain errors or remain\n"
			+ "empty will keep the account from being created\n"
			+ "until such errors are corrected.\n";
	
	public String loggingIn = 
			"To login to the Sentinel Data Vault (SDV):\n"
			+ "After starting the SDV desktop application,\n"
			+ "a login screen will appear.\n"
			+ "The login screen will prompt\n"
			+ "you for a username and password.\n"
			+ "If you have previously created anaccount\n"
			+ "in the SDV, enter your username and password.\n"
			+ "If you have not created an account,\n"
			+ "follow the Account creation tutorial.";
	
	public String recoveringPassword = "recover";
	
	public String newEntry = "newEntry";
	
	public String existingEntry= "existing Entry";
	
	public String changingPassword= "changePass";
	
	public String changingAvatar = "changAvi"; 
	
	public String localShare = "local share";
	
	public String emailShare ="mailshare";
	
	public String backup = "backup";


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HelpView window = new HelpView();
					window.frmHelp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HelpView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHelp = new JFrame();
		frmHelp.setTitle("Help");
		frmHelp.setBounds(100, 100, 540, 369);
		frmHelp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmHelp.setLocationRelativeTo(null);
		JTree tree = new JTree();
		tree.setRootVisible(false);
		tree.setBounds(7, 7, 177, 260);
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				JTree tree = (JTree) e.getSource();

				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

				String selectedNodeName = selectedNode.toString();
				System.out.println(selectedNodeName);
				if(selectedNodeName.equals("Signing Up") && selectedNode.isLeaf()) { 
					System.out.println(signingUp);
					textPane.setText(signingUp);
					frmHelp.repaint();
				}
				else if ( selectedNodeName.equals("Logging In")) { 
					textPane.setText(loggingIn);
					frmHelp.repaint();
				}
				else if ( selectedNodeName.equals("Recovering Password")) { 
					textPane.setText(recoveringPassword);
					frmHelp.repaint();
				}
				else if ( selectedNodeName.equals("Making a New Entry")) { 
					textPane.setText(newEntry);
					frmHelp.repaint();
				}
				else if ( selectedNodeName.equals("Modifying an Existing Entry")) { 
					textPane.setText(existingEntry);
					frmHelp.repaint();
				}
				else if ( selectedNodeName.equals("Changing Password")) { 
					textPane.setText(changingPassword);
					frmHelp.repaint();
				}
				else if ( selectedNodeName.equals("Changing Avatar")) { 
					textPane.setText(changingAvatar);
					frmHelp.repaint();
				}
				else if ( selectedNodeName.equals("Sharing Entries Locally")) { 
					textPane.setText(localShare);
					frmHelp.repaint();
				}
				else if ( selectedNodeName.equals("Sharing Entries Via Email")) { 
					textPane.setText(emailShare);
					frmHelp.repaint();
				}
				else if ( selectedNodeName.equals("Backing up files")) { 
					textPane.setText(backup);
					frmHelp.repaint();
				}

			}
		});
		frmHelp.getContentPane().setLayout(null);
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("FAQ") {
				{
					DefaultMutableTreeNode node_1;
					node_1 = new DefaultMutableTreeNode("Creating Account");
						node_1.add(new DefaultMutableTreeNode("Signing Up"));
						node_1.add(new DefaultMutableTreeNode("Logging In"));
						node_1.add(new DefaultMutableTreeNode("Recovering Password"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Creating New Data");
						node_1.add(new DefaultMutableTreeNode("Making a New Entry"));
						node_1.add(new DefaultMutableTreeNode("Modifying an Existing Entry"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Settings");
						node_1.add(new DefaultMutableTreeNode("Changing Password"));
						node_1.add(new DefaultMutableTreeNode("Changing Avatar"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Sharing and Backups");
						node_1.add(new DefaultMutableTreeNode("Sharing Entries Locally"));
						node_1.add(new DefaultMutableTreeNode("Sharing Entries Via Email"));
						node_1.add(new DefaultMutableTreeNode("Backing up files"));
					add(node_1);
				}
			}
		));
		
		frmHelp.getContentPane().add(tree);
		
		JButton btnNewButton = new JButton("Close");
		btnNewButton.setBounds(213, 284, 127, 25);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmHelp.dispose();
			}
		});
		
		textPane = new JTextArea();
		textPane.setWrapStyleWord(true);
		textPane.setEditable(false);
		textPane.setBounds(214, 7, 298, 260);
		frmHelp.getContentPane().add(textPane);
		frmHelp.getContentPane().add(btnNewButton);
	}
}
