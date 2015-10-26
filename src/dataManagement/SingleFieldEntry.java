package dataManagement;
import java.time.LocalDateTime;

public class SingleFieldEntry extends DataEntry {

	private String fieldData;

	public SingleFieldEntry(String entryName, String entryType, String fieldData, String encryptionKey, String owner, Boolean highSecurity,
			LocalDateTime lastModified) {
		super(entryName, entryType, encryptionKey, owner, highSecurity, lastModified);
		this.fieldData = fieldData;
	}

	public String getFieldData() {
		return this.fieldData;
	}

	public void setFieldData(String fieldData) {
		this.fieldData = fieldData;
	}
}
