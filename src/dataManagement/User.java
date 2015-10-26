package dataManagement;

import java.time.LocalDateTime;

public class User {

	// TODO Finish adding all corresponding database fields!!!
	private String username;
	private String passwordHash;
	private String passwordSalt;
	private String dataKey;
	private String securityQuestion;
	private String securityAnswer;
	private LocalDateTime lastLogin;
	private Boolean defaultHighSecurity;
	private Boolean accountWipeSet;

	public User(String username, String passwordHash, String passwordSalt, String dataKey, String securityQuestion, String securityAnswer, LocalDateTime lastLogin) {
		this.username = username;
		this.passwordHash = passwordHash;
		this.passwordSalt = passwordSalt;
		this.dataKey = dataKey;
		this.securityQuestion = securityQuestion;
		this.securityAnswer = securityAnswer;
		this.lastLogin = lastLogin;
		this.defaultHighSecurity = false;
		this.accountWipeSet = false;
	}

	
	public String getUsername() {
		return this.username;
	}

	public String getPasswordHash() {
		return this.passwordHash;
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

	public LocalDateTime getLastLogin() {
		return this.lastLogin;
	}
	
	public Boolean isHighSecurity() {
		return this.defaultHighSecurity;
	}
	
	public Boolean isAccountWipeSet() {
		return this.accountWipeSet;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	
	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	public void setDataKey(String dataKey) {
		this.dataKey = dataKey;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}
	
	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}

	public void setDefaultHighSecurity(Boolean defaultHighSecurity) {
		this.defaultHighSecurity = defaultHighSecurity;
	}
	
	public void setAccountWipe(Boolean accountWipeSet) {
		this.accountWipeSet = accountWipeSet;
	}
}

