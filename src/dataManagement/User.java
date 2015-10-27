package dataManagement;

import java.time.LocalDateTime;

public class User {
	
	private static final int DEFAULT_HIGH_SECURITY = 0;	// By default, use normal security (0 = false)
	private static final int DEFAULT_ACCOUNT_WIPE = 0;	// By default, account wipe turned OFF (0 = false)
	private static final String DEFAULT_BACKUP_FREQ = "Weekly";	// By default, backup frequency is weekly
	private static final int DEFAULT_MAX_BACKUP_SIZE = 1000;	// In megabytes; default is 1 GB

	private String username;
	private String passwordHash;
	private String passwordSalt;
	private String dataKey;
	private String securityQuestion;
	private String securityAnswer;
	private LocalDateTime lastLogin;
	private int defaultHighSecurity;
	private int accountWipeSet;
	private String backupFrequency;
	private int maxBackupSize;

	public User(String username, String passwordHash, String passwordSalt, String dataKey, String securityQuestion, String securityAnswer, LocalDateTime lastLogin) {
		this.username = username;
		this.passwordHash = passwordHash;
		this.passwordSalt = passwordSalt;
		this.dataKey = dataKey;
		this.securityQuestion = securityQuestion;
		this.securityAnswer = securityAnswer;
		this.lastLogin = lastLogin;
		this.defaultHighSecurity = DEFAULT_HIGH_SECURITY;
		this.accountWipeSet = DEFAULT_ACCOUNT_WIPE;
		this.backupFrequency = DEFAULT_BACKUP_FREQ;
		this.maxBackupSize = DEFAULT_MAX_BACKUP_SIZE; 
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
	
	public int isHighSecurity() {
		return this.defaultHighSecurity;
	}
	
	public int isAccountWipeSet() {
		return this.accountWipeSet;
	}
	
	public String getBackupFrequency() {
		return this.backupFrequency;
	}

	public int getMaxBackupSize() {
		return this.maxBackupSize;
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

	public void setDefaultHighSecurity(int defaultHighSecurity) {
		this.defaultHighSecurity = defaultHighSecurity;
	}
	
	public void setAccountWipe(int accountWipeSet) {
		this.accountWipeSet = accountWipeSet;
	}
	
	public void setBackupFrequency(String backupFrequency) {
		this.backupFrequency = backupFrequency;
	}
	
	public void setMaxBackupSize(int maxBackupSize) {
		this.maxBackupSize = maxBackupSize;
	}
}

