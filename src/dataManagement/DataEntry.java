package dataManagement;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataEntry {

	private String entryName;
	private String entryType;
	private String encryptionKey;
	private String owner;
	private List<String> validUsers;
	private int highSecurity;
	private LocalDateTime lastModified;
	private List<String> fieldDataList;

	public DataEntry(String entryName, String entryType, String encryptionKey, String owner, int highSecurity, LocalDateTime lastModified) {
		this.entryName = entryName;
		this.entryType = entryType;
		this.encryptionKey = encryptionKey;
		this.owner = owner;
		this.validUsers = new ArrayList<String>();
		this.fieldDataList = new ArrayList<String>();
		this.highSecurity = highSecurity;
		this.lastModified = lastModified;
	}
	
	public DataEntry(String entryName, String entryType, String encryptionKey, String owner, List<String> validUsers, int highSecurity, LocalDateTime lastModified, List<String> fieldDataList) {
		this.entryName = entryName;
		this.entryType = entryType;
		this.encryptionKey = encryptionKey;
		this.owner = owner;
		this.validUsers = validUsers;
		this.highSecurity = highSecurity;
		this.lastModified = lastModified;
		this.fieldDataList = fieldDataList;
	}

	public List<String> getFieldDataList() {
		return this.fieldDataList;
	}

	public List<String> getValidUsers() {
		return this.validUsers;
	}
	
	public void setDataFields( List<String> fields) {
		fieldDataList = fields;
	}
	
	public void addDataField(String field) {
		this.fieldDataList.add(field);
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

	public List<String> getValidUsers(){
		return this.validUsers;
	}

	public LocalDateTime getLastModified() {
		return this.lastModified;
	}

	public int isHighSecurity() {
		return this.highSecurity;
	}
	
	public void setEntryName(String entryName) {
		this.entryName = entryName;
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
	
	public void setValidUsers(List<String> validUsers) {
		this.validUsers = validUsers;
	}
	
	public void setHighSecurity(int highSecurity) {
		this.highSecurity = highSecurity;
	}

	public void addValidUser(String user) {
		this.validUsers.add(user);
		Collections.sort(validUsers); // validUsers must always be sorted in order to perform binary search
	}

	public void removeValidUser(String user) {
		this.validUsers.remove(user);
	}
	
	public void removeAllValidUser(){
		this.validUsers.clear();
	}

	public Boolean checkValidUser(String user) {
		return this.validUsers.contains(user);
	}
	
	public String buildValidUsersString() {
		String validUsersString = "";
		
		// Construct a String of all valid users separated by " " (space) for later parsing
		boolean atLeastOneUser = false;
		for (String validUser : validUsers) {
			validUsersString += (validUser + " ");
			atLeastOneUser = true;
		}
		
		if (atLeastOneUser) {
			validUsersString = validUsersString.substring(0, validUsersString.length() - 1);
		}
		
		return validUsersString;
	}
}
