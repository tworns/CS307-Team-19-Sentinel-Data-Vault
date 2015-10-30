package dataManagement;

import java.time.LocalDateTime;
import java.util.List;

public class MultiFieldEntry extends DataEntry {

	private List<String> fieldDataList;

	public MultiFieldEntry(String entryName, String entryType, String encryptionKey, String owner, int highSecurity,
			LocalDateTime lastModified, List<String> fieldDataList) {
		super(entryName, entryType, encryptionKey, owner, highSecurity, lastModified);
		this.fieldDataList = fieldDataList;
	}

	public List<String> getFieldDataList() {
		return this.fieldDataList;
	}

	public void setFieldDataList(List<String> fieldDataList) {
		this.fieldDataList = fieldDataList;
	}
}
