package list.files.pojo;

/**
 * 
 * @author singhraj
 * FileMetadataPOJO is a simple immutable POJO class that provides information about file name and its size
 */
public class FileMetadataPOJO {
	
	private String name;
	private long size;
	
	private FileMetadataPOJO() {
	}
	
	private FileMetadataPOJO(String fileName, long fileSize) {
		this.name = fileName;
		this.size = fileSize;
	}
	
	public static FileMetadataPOJO getInstance(String fileName, long fileSize) {
		return new FileMetadataPOJO(fileName, fileSize);
	}

	public String getName() {
		return name;
	}

	public long getSize() {
		return size;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FileMetadataDTO [")
			.append("name=")
			.append(name)
			.append(", size=")
			.append(size)
			.append("]");
		return builder.toString();
	}
}
