package list.files.service.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import list.files.pojo.FileMetadataPOJO;
import list.files.service.FileMetadataService;
import list.files.service.FileOrchestrator;

@Service
public class FileOrchestratorImpl implements FileOrchestrator {
	
	@Autowired
	private FileMetadataService fileMetadataService;

	@Override
	public List<FileMetadataPOJO> getFileMetadataSortedBySizeAndName(File sourceDir) {
		List<File> filesWithTxtExt = fileMetadataService.getFilesWithTxtExtension(sourceDir);
		List<FileMetadataPOJO> fileMetadataList = fileMetadataService.getListOfFileMetadataPOJO(filesWithTxtExt);
		fileMetadataService.sortFileMetadataBySizeAndName(fileMetadataList);
		
		return fileMetadataList;
	}

}
