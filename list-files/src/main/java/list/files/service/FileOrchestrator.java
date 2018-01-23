package list.files.service;

import java.io.File;
import java.util.List;

import list.files.pojo.FileMetadataPOJO;

public interface FileOrchestrator {
	
	/**
	 * Given a file directory of interest, this method scans for all the files having extension of ".txt".
	 * It returns the file name and its size encapsulated in the @see list.files.pojo.FileMetadataPOJO
	 * sorted by its size and name
	 * @param sourceDir
	 * @return
	 */
	List<FileMetadataPOJO> getFileMetadataSortedBySizeAndName(File sourceDir);
}
