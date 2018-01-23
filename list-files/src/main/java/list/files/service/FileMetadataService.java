package list.files.service;

import java.io.File;
import java.util.List;

import list.files.pojo.FileMetadataPOJO;

public interface FileMetadataService {
	
	/**
	 * Returns list of files having extension of ".txt"
	 * @param sourceDir
	 * @return
	 */
	List<File> getFilesWithTxtExtension(File sourceDir);
	
	/**
	 * Converts file into POJOs having the file name and its size
	 * @param filesWithTxtExt
	 * @return
	 */
	List<FileMetadataPOJO> getListOfFileMetadataPOJO(List<File> filesWithTxtExt);
	
	/**
	 * Sorts files by its size and name.
	 * @param fileMetadataList
	 */
	void sortFileMetadataBySizeAndName(List<FileMetadataPOJO> fileMetadataList);
}
