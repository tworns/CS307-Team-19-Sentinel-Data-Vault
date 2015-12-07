package userInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

import controllers.DatabaseManager;
import dataManagement.User;

public class SearchResultView {

	
	public JFrame frame;
	public String text;
	User currentUser;
	String username;
	
	public List<String> currentDataEntryNameList;
	public List<String> currentDataEntryTypeList;
	
	/**
	 *	Jiho Choi
	 * 
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchResultView window = new SearchResultView();
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
	public SearchResultView() {
		initialize();
	}

	public SearchResultView(String text, User currentUser) {
		this.text = text;
		this.currentUser = currentUser;
		this.username = currentUser.getUsername();
		DatabaseManager dbmanger = new DatabaseManager("vault_database");

		currentDataEntryNameList = dbmanger.retrieveDataEntryNameList(username);
		currentDataEntryTypeList = dbmanger.retrieveDataEntryTypeList(username);
		
		initialize();
	}
	
	public String SearchEntryResult (String text) {
		
		String resultText = "";
		int numOfResult = 0;
		
		resultText = "We Found \"" + numOfResult +"\" item(s) related to \""+ text +"\". \n" + "\n";
		
		for(int i=0; i < currentDataEntryNameList.size(); i++ ) {
			if(currentDataEntryNameList.get(i).contains(text)) {
				resultText = resultText + 
						"<"+ currentDataEntryNameList.get(i) + "> is founded under <" +currentDataEntryTypeList.get(i) + ">.\n";
			}
		}
		
		
		text = resultText;
		return text;
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 50, 400, 185);
		frame.getContentPane().add(scrollPane);
		
		JTextPane txtpnSearchResultText = new JTextPane();
		txtpnSearchResultText.setText(SearchEntryResult(text));
		
		scrollPane.setViewportView(txtpnSearchResultText);
		
		JLabel lblResult = new JLabel("Search Result");
		lblResult.setBounds(175, 8, 100, 30);
		frame.getContentPane().add(lblResult);
		
		JButton btnClose = new JButton("Close");
		btnClose.setBounds(175, 243, 117, 29);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		frame.getContentPane().add(btnClose);
	}
}
