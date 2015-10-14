import java.time.LocalDateTime;

public class DoubleFieldEntry extends DataEntry {

	private String entryName1;
	private String entryName2;

	public DoubleFieldEntry(String entryType, String encryptionKey, String owner, Boolean highSecurity,
			LocalDateTime lastModified, String entryName1, String entryName2) {
		super(entryType, encryptionKey, owner, highSecurity, lastModified);
		this.entryName1 = entryName1;
		this.entryName2 = entryName2;
	}

	public String getEntryName1() {
		return this.entryName1;
	}

	public String getEntryName2() {
		return this.entryName2;
	}

	public void setEntryName1(String entryName1) {
		this.entryName1 = entryName1;
	}

	public void setEntryName2(String entryName2) {
		this.entryName2 = entryName2;
	}
}
