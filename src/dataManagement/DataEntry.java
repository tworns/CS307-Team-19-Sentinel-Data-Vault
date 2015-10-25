package dataManagement;
import java.time.LocalDateTime;
import java.util.List;

public abstract class DataEntry {

	private String entryName;
	private String entryType;
	private String encryptionKey;
	private String owner;
	private List<String> validUsers;
	private Boolean highSecurity;
	private LocalDateTime lastModified;

	public DataEntry(String entryName, String entryType, String encryptionKey, String owner, Boolean highSecurity,
			LocalDateTime lastModified) {
		this.entryName = entryName;
		this.entryType = entryType;
		this.encryptionKey = encryptionKey;
		this.owner = owner;
		this.highSecurity = highSecurity;
		this.lastModified = lastModified;
	}

	public String getEntryName() {
		return this.entryName;
	}
	
	public String getEntryType() {
		return this.entryType;
	}

	public String getEncryptionKey() {
		return this.encryptionKey;
	}

	public String getOwner() {
		return this.owner;
	}

	public LocalDateTime getLastModified() {
		return this.lastModified;
	}

	public Boolean isHighSecurity() {
		return this.highSecurity;
	}

	public void setEntryType(String entryType) {
		this.entryType = entryType;
	}

	public void setEncryptionKey(String encryptionKey) {
		this.encryptionKey = encryptionKey;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public void addValidUser(String user) {
		this.validUsers.add(user);
	}

	public void removeValidUser(String user) {
		this.validUsers.remove(user);
	}

	public Boolean checkValidUser(String user) {
		return this.validUsers.contains(user);
	}
}
