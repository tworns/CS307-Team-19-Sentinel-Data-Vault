package dataManagement;
import java.time.LocalDateTime;

public class SingleFieldEntry extends DataEntry {

	private String fieldData;

	public SingleFieldEntry(String entryName, String entryType, String fieldData, String encryptionKey, String owner, Boolean highSecurity,
			LocalDateTime lastModified) {
		super(entryName, entryType, encryptionKey, owner, highSecurity, lastModified);
		this.fieldData = fieldData;
	}

	public String getEntryName() {
		return this.fieldData;
	}

	public void setEntryName(String entryName) {
		this.fieldData = entryName;
	}
}
