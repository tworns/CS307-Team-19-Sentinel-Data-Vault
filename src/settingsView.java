import javax.swing.JFrame;

public class settingsView {
  private settingsView instance;
  private int instanceCount = 0;
  private JFrame frame;
  
	private settingsView() {
		init();
	}
	
	public settingsView getInstance() { 
		if(instanceCount != 0) { 
			return instance;
		}
		else { 
			settingsView newInstance = new settingsView();
			instanceCount++;
			return newInstance;
		}
		
	}
	
	private void init(){ 
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
