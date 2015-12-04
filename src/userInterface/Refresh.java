package userInterface;

import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;

public class Refresh {

	public JFrame frame;
	public String username;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Refresh window = new Refresh();
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
	public Refresh() {
		initialize();
	}
	public Refresh(String username) {
		this.username = username;
		initialize();
	}
		

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(3000, 3000, 1, 1);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		System.gc();
		for (Window window : Window.getWindows()) {
			window.dispose();
		}
		frame.dispose();
		HomeView hv = new HomeView(username);
		hv.frmSentinelDataVault.setVisible(true);
		
		
		
	}
	public void refresh(){
		System.gc();
		for (Window window : Window.getWindows()) {
			window.dispose();
		}
		frame.dispose();
		HomeView hv = new HomeView(username);
		hv.frmSentinelDataVault.setVisible(true);
	}

}
