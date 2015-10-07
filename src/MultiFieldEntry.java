import java.time.LocalDateTime;
import java.util.List;

public class MultiFieldEntry extends DataEntry {

	private List<String> entryList;

	public MultiFieldEntry(String entryType, String encryptionKey, String owner, Boolean highSecurity,
			LocalDateTime lastModified, List<String> entryList) {
		super(entryType, encryptionKey, owner, highSecurity, lastModified);
		this.entryList = entryList;
	}

	public List<String> getEntryList() {
		return this.entryList;
	}

	public void setEntryList(List<String> entryList) {
		this.entryList = entryList;
	}
}
