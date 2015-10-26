package dataManagement;
public class User {


	
	private String username;
	private String password;
	private String passwordSalt;
	private String dataKey;
	private String securityQuestion;
	private String securityAnswer;
	private Boolean defultHighSecurity;

	public User(String username, String password, String passwordSalt, String dataKey, String securityQuestion, String securityAnswer) {
		this.username = username;
		this.password = password;
		this.passwordSalt = passwordSalt;
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
	
	public String getPasswordSalt() {
		return this.passwordSalt;
	}

	public String getDataKey() {
		return this.dataKey;
	}

	public String getSecurityQuestion() {
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

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

	public void setDefaultHighSecurity(Boolean defultHighSecurity) {
		this.defultHighSecurity = defultHighSecurity;
	}


}

