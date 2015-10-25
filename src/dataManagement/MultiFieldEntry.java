package dataManagement;
import java.time.LocalDateTime;
import java.util.List;

public class MultiFieldEntry extends DataEntry {

	private List<String> fieldDataList;

	public MultiFieldEntry(String entryName, String entryType, String encryptionKey, String owner, Boolean highSecurity,
			LocalDateTime lastModified, List<String> fieldDataList) {
		super(entryName, entryType, encryptionKey, owner, highSecurity, lastModified);
		this.fieldDataList = fieldDataList;
	}

	public List<String> getEntryList() {
		return this.fieldDataList;
	}

	public void setEntryList(List<String> entryList) {
		this.fieldDataList = entryList;
	}
}
