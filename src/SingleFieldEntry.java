import java.time.LocalDateTime;

public class SingleFieldEntry extends DataEntry {

	private String entryName;

	public SingleFieldEntry(String entryType, String entryName, String encryptionKey, String owner, Boolean highSecurity,
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
