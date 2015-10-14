import java.awt.EventQueue;
import javax.swing.*;
import java.awt.Font;
import java.awt.event.*;

public class SettingsView {

	private JFrame frmSettings;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SettingsView window = new SettingsView();
					window.frmSettings.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SettingsView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSettings = new JFrame();
		frmSettings.setTitle("Settings");
		frmSettings.setBounds(100, 100, 450, 300);
		frmSettings.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSettings.getContentPane().setLayout(null);
		
		//High security toggle & tool tip
		JCheckBox chckbxHighSecurityLevel = new JCheckBox("High Security Level ");
		chckbxHighSecurityLevel.setToolTipText("Toggling this setting will change ALL user encryptions to the strongest possible.");
		chckbxHighSecurityLevel.setBounds(48, 53, 148, 25);
		frmSettings.getContentPane().add(chckbxHighSecurityLevel);
		
		//Allows user to set lock out time up to max
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 7, 1));
		spinner.setBounds(48, 87, 30, 22);
		frmSettings.getContentPane().add(spinner);
		
		JLabel lblMaxLockoutTime = new JLabel("Max Lockout Time (Days)");
		lblMaxLockoutTime.setBounds(90, 90, 159, 16);
		frmSettings.getContentPane().add(lblMaxLockoutTime);
		
		//box lets user decide when to back up
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Weekly", "Monthly", "Every 3 Months", "Every 6 Months", "Yearly"}));
		comboBox.setBounds(47, 127, 113, 22);
		frmSettings.getContentPane().add(comboBox);
		
		JLabel lblBackupFrequency = new JLabel("Backup Frequency");
		lblBackupFrequency.setBounds(176, 130, 113, 16);
		frmSettings.getContentPane().add(lblBackupFrequency);
		
		JLabel lblUserSettings = new JLabel("User Settings");
		lblUserSettings.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUserSettings.setBounds(49, 13, 118, 25);
		frmSettings.getContentPane().add(lblUserSettings);
		//Lets user set the file size past which they'll be warned about file size.
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"10 MB", "50 MB", "100 MB", "512 MB", "1 GB", "2 GB", "3 GB", "4 GB", "5 GB"}));
		comboBox_1.setBounds(48, 162, 113, 22);
		frmSettings.getContentPane().add(comboBox_1);
		
		JLabel lblFileSizeLimit = new JLabel("File Size Limit Warning ");
		lblFileSizeLimit.setBounds(176, 165, 142, 16);
		frmSettings.getContentPane().add(lblFileSizeLimit);
		
		//Will eventually contain an action listener that saves preferences to User
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equalsIgnoreCase("Ok")) {
			//TODO SAVE SETTINGS TO USER CLASS
					frmSettings.dispose();
		}
			}
			
		
		});
		btnOk.setBounds(65, 217, 97, 25);
		frmSettings.getContentPane().add(btnOk);
		
		//closes window
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent clickCancel) {
				if(clickCancel.getActionCommand().equalsIgnoreCase("Cancel")) {
					frmSettings.dispose();
				}
			}
		});
		btnCancel.setBounds(191, 217, 97, 25);
		frmSettings.getContentPane().add(btnCancel);
	}
}
