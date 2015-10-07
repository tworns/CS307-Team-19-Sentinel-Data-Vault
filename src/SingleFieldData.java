import java.time.LocalDateTime;

public class SingleFieldData extends DataEntry {

	private String entryName;

	public SingleFieldData(String entryType, String entryName, String encryptionKey, String owner, Boolean highSecurity,
			LocalDateTime lastModified) {
		super(entryType, encryptionKey, owner, highSecurity, lastModified);
		this.entryName = entryName;
	}

	public String getEntryName() {
		return this.entryName;
	}

	public void setEntryName(String entryName) {
		this.entryName = entryName;
	}
}
