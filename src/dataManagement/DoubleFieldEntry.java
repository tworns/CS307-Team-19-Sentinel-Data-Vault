package dataManagement;
import java.time.LocalDateTime;

public class DoubleFieldEntry extends DataEntry {

	private String fieldData1;
	private String fieldData2;

	public DoubleFieldEntry(String entryName, String entryType, String encryptionKey, String owner, Boolean highSecurity,
			LocalDateTime lastModified, String fieldData1, String fieldData2) {
		super(entryName, entryType, encryptionKey, owner, highSecurity, lastModified);
		this.fieldData1 = fieldData1;
		this.fieldData2 = fieldData2;
	}

	public String getEntryName1() {
		return this.fieldData1;
	}

	public String getEntryName2() {
		return this.fieldData2;
	}

	public void setEntryName1(String entryName1) {
		this.fieldData1 = entryName1;
	}

	public void setEntryName2(String entryName2) {
		this.fieldData2 = entryName2;
	}
}
