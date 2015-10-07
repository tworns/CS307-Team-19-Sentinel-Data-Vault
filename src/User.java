
public class User {

	private String username;
	private String password;
	private String dataKey;
	private int securityQuestion;
	private String securityAnswer;
	private Boolean defultHighSecurity;

	public User(String username, String password, String dataKey, int securityQuestion, String securityAnswer) {
		this.username = username;
		this.password = password;
		this.dataKey = dataKey;
		this.securityQuestion = securityQuestion;
		this.securityAnswer = securityAnswer;
		this.defultHighSecurity = false;
	}

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	public String getDataKey() {
		return this.dataKey;
	}

	public int getSecurityQuestion() {
		return this.securityQuestion;
	}

	public String getSecurityAnswer() {
		return this.securityAnswer;
	}

	public Boolean isHighSecurity() {
		return this.defultHighSecurity;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void getDataKey(String dataKey) {
		this.dataKey = dataKey;
	}

	public void setSecurityQuestion(int securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

	public void setDefaultHighSecurity(Boolean defultHighSecurity) {
		this.defultHighSecurity = defultHighSecurity;
	}
}
