import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HelpView {

	/*
	 * HelpView
	 * 2015 10 01 ~ 
	 * 1st Sprint
	 * Jiho Choi
	 * 
	 */
	
	
	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HelpView window = new HelpView();
					window.frame.setVisible(true);
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
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[151px][264.00px]", "[246px]"));
		
		JTree tree = new JTree();
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("Help") {
				{
					DefaultMutableTreeNode node_1;
					node_1 = new DefaultMutableTreeNode("Creating Account");
						node_1.add(new DefaultMutableTreeNode("blue"));
						node_1.add(new DefaultMutableTreeNode("violet"));
						node_1.add(new DefaultMutableTreeNode("red"));
						node_1.add(new DefaultMutableTreeNode("yellow"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Creating New Data");
						node_1.add(new DefaultMutableTreeNode("basketball"));
						node_1.add(new DefaultMutableTreeNode("soccer"));
						node_1.add(new DefaultMutableTreeNode("football"));
						node_1.add(new DefaultMutableTreeNode("hockey"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Setting");
						node_1.add(new DefaultMutableTreeNode("hot dogs"));
						node_1.add(new DefaultMutableTreeNode("pizza"));
						node_1.add(new DefaultMutableTreeNode("ravioli"));
						node_1.add(new DefaultMutableTreeNode("bananas"));
					add(node_1);
				}
			}
		));
		frame.getContentPane().add(tree, "flowy,cell 0 0,alignx left,growy");
		
		JTextPane txtpnHelp = new JTextPane();
		txtpnHelp.setText("Help");
		frame.getContentPane().add(txtpnHelp, "flowx,cell 1 0,alignx left,aligny center");
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		frame.getContentPane().add(btnNewButton, "cell 0 0");
	}

}
