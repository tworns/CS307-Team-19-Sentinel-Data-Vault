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

	public String getFieldData1() {
		return this.fieldData1;
	}

	public String getFieldData2() {
		return this.fieldData2;
	}

	public void setFieldData1(String fieldData1) {
		this.fieldData1 = fieldData1;
	}

	public void setFieldData2(String fieldData2) {
		this.fieldData2 = fieldData2;
	}
}
