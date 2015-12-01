package userInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.oracle.webservices.internal.api.databinding.Databinding.Builder;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AvatarView { //ANY NEW AVATAR PHOTO SHOULD BE ~100 X ~75 (height X width).

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AvatarView window = new AvatarView();
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
	public AvatarView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 747, 418);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//set user picture to string that'll be assigned in a global array corresponding to the position on the gui.
				// Gui looks like: 
				//| 1 | 2 | 3 | 
				//| 4 | 5 | 6 | ...  
				// can be expanded to fit needs, preferably continuing with rows of 3. 
				frame.dispose();
				
			}
		});
		rdbtnNewRadioButton.setBounds(115, 144, 25, 25);
		frame.getContentPane().add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("");
		rdbtnNewRadioButton_1.setBounds(327, 144, 25, 25);
		frame.getContentPane().add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("");
		rdbtnNewRadioButton_2.setBounds(569, 144, 25, 25);
		frame.getContentPane().add(rdbtnNewRadioButton_2);
		
		JRadioButton radioButton = new JRadioButton("");
		radioButton.setBounds(115, 319, 25, 25);
		frame.getContentPane().add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("");
		radioButton_1.setBounds(327, 319, 25, 25);
		frame.getContentPane().add(radioButton_1);
		
		JRadioButton radioButton_2 = new JRadioButton("");
		radioButton_2.setBounds(569, 319, 25, 25);
		frame.getContentPane().add(radioButton_2);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(AvatarView.class.getResource("/Avatars/Yep.jpg")));
		lblNewLabel.setBounds(94, 26, 75, 100);
		frame.getContentPane().add(lblNewLabel);
	}
}
