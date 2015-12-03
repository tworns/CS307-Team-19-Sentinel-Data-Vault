package userInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.oracle.webservices.internal.api.databinding.Databinding.Builder;

import controllers.DatabaseManager;
import dataManagement.User;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Window;

public class AvatarView { // ANY NEW AVATAR PHOTO SHOULD BE ~100 X ~75 (height X
							// width).

	public JFrame frame;
	private String [] avatars = { "/Avatars/Avatar1.jpg","/Avatars/Avatar2.jpg", "/Avatars/Avatar3.jpg","/Avatars/Avatar4.jpg",
			"/Avatars/Avatar5.jpg","/Avatars/Avatar6.jpg"
	};
	public User currentUser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User u = new User(null, null, null, null, null, null, null);
					AvatarView window = new AvatarView(u);
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
	public AvatarView(User user) {
		initialize();
		this.currentUser = user;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setLocationRelativeTo(null);
		frame.setBounds(100, 100, 711, 453);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// set user picture to string that'll be assigned in a global
				// array corresponding to the position on the gui.
				// Gui looks like:
				// | 1 | 2 | 3 |
				// | 4 | 5 | 6 | ...
				// can be expanded to fit needs, preferably continuing with rows
				// of 3.
				currentUser.setDataKey(avatars[0]);
				DatabaseManager v = new DatabaseManager("vault_database");
				v.modifyUserField(currentUser, "data_key", avatars[0]);
				System.gc();
				for (Window window : Window.getWindows()) {
					window.dispose();
				}

				frame.dispose();

				HomeView hv = new HomeView(currentUser.getUsername());
				hv.frmSentinelDataVault.setVisible(true);
			}
		});
		rdbtnNewRadioButton.setBounds(112, 173, 25, 25);
		frame.getContentPane().add(rdbtnNewRadioButton);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("");
		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentUser.setDataKey(avatars[1]);
				DatabaseManager v = new DatabaseManager("vault_database");
				v.modifyUserField(currentUser, "data_key", avatars[1]);
				System.gc();
				for (Window window : Window.getWindows()) {
					window.dispose();
				}

				frame.dispose();

				HomeView hv = new HomeView(currentUser.getUsername());
				hv.frmSentinelDataVault.setVisible(true);
			}
		});
		rdbtnNewRadioButton_1.setBounds(324, 173, 25, 25);
		frame.getContentPane().add(rdbtnNewRadioButton_1);

		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("");
		rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentUser.setDataKey(avatars[2]);
				DatabaseManager v = new DatabaseManager("vault_database");
				v.modifyUserField(currentUser, "data_key", avatars[2]);
				System.gc();
				for (Window window : Window.getWindows()) {
					window.dispose();
				}

				frame.dispose();

				HomeView hv = new HomeView(currentUser.getUsername());
				hv.frmSentinelDataVault.setVisible(true);
			}
		});
		rdbtnNewRadioButton_2.setBounds(566, 173, 25, 25);
		frame.getContentPane().add(rdbtnNewRadioButton_2);

		JRadioButton radioButton = new JRadioButton("");
		radioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentUser.setDataKey(avatars[3]);
				DatabaseManager v = new DatabaseManager("vault_database");
				v.modifyUserField(currentUser, "data_key", avatars[3]);
				System.gc();
				for (Window window : Window.getWindows()) {
					window.dispose();
				}

				frame.dispose();

				HomeView hv = new HomeView(currentUser.getUsername());
				hv.frmSentinelDataVault.setVisible(true);
			}
		});
		radioButton.setBounds(112, 348, 25, 25);
		frame.getContentPane().add(radioButton);

		JRadioButton radioButton_1 = new JRadioButton("");
		radioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentUser.setDataKey(avatars[4]);
				DatabaseManager v = new DatabaseManager("vault_database");
				v.modifyUserField(currentUser, "data_key", avatars[4]);
				System.gc();
				for (Window window : Window.getWindows()) {
					window.dispose();
				}

				frame.dispose();

				HomeView hv = new HomeView(currentUser.getUsername());
				hv.frmSentinelDataVault.setVisible(true);
			}
		});
		radioButton_1.setBounds(324, 348, 25, 25);
		frame.getContentPane().add(radioButton_1);

		JRadioButton radioButton_2 = new JRadioButton("");
		radioButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentUser.setDataKey(avatars[5]);
				DatabaseManager v = new DatabaseManager("vault_database");
				v.modifyUserField(currentUser, "data_key", avatars[5]);
				System.gc();
				for (Window window : Window.getWindows()) {
					window.dispose();
				}

				frame.dispose();

				HomeView hv = new HomeView(currentUser.getUsername());
				hv.frmSentinelDataVault.setVisible(true);
			}
		});
		radioButton_2.setBounds(566, 348, 25, 25);
		frame.getContentPane().add(radioButton_2);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(AvatarView.class.getResource("/Avatars/Avatar1.jpg")));
		lblNewLabel.setBounds(63, 44, 120, 120);
		frame.getContentPane().add(lblNewLabel);

		JLabel label = new JLabel("New label");
		label.setIcon(new ImageIcon(AvatarView.class.getResource("/Avatars/Avatar2.jpg")));
		label.setBounds(274, 44, 120, 120);
		frame.getContentPane().add(label);

		JLabel label_1 = new JLabel("New label");
		label_1.setIcon(new ImageIcon(AvatarView.class.getResource("/Avatars/Avatar3.jpg")));
		label_1.setBounds(519, 44, 120, 120);
		frame.getContentPane().add(label_1);

		JLabel label_2 = new JLabel("New label");
		label_2.setIcon(new ImageIcon(AvatarView.class.getResource("/Avatars/Avatar4.jpg")));
		label_2.setBounds(63, 219, 120, 120);
		frame.getContentPane().add(label_2);

		JLabel label_3 = new JLabel("New label");
		label_3.setIcon(new ImageIcon(AvatarView.class.getResource("/Avatars/Avatar5.jpg")));
		label_3.setBounds(274, 219, 120, 120);
		frame.getContentPane().add(label_3);

		JLabel label_4 = new JLabel("New label");
		label_4.setIcon(new ImageIcon(AvatarView.class.getResource("/Avatars/Avatar6.jpg")));
		label_4.setBounds(519, 219, 120, 120);
		frame.getContentPane().add(label_4);

		JLabel lblSelectYourAvatar = new JLabel("Select your avatar");
		lblSelectYourAvatar.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSelectYourAvatar.setBounds(262, 13, 153, 16);
		frame.getContentPane().add(lblSelectYourAvatar);
	}
}
