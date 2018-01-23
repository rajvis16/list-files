package list.files.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import list.files.pojo.FileMetadataPOJO;
import list.files.service.FileMetadataService;

@Service
public class FileMetadataServiceImpl implements FileMetadataService {
	
	private static final String FILE_EXTENSION = ".txt";

	@Override
	public List<File> getFilesWithTxtExtension(File sourceDir) {
		
		if (sourceDir == null || !sourceDir.isDirectory()) {
			return Collections.EMPTY_LIST;
		}
		
		List<File> filesWithTxtExt = Arrays.asList(sourceDir.listFiles(
				(dir, fileName) -> {
					return fileName.toLowerCase().endsWith(FILE_EXTENSION);
			}));
			return filesWithTxtExt;
		}

	@Override
	public List<FileMetadataPOJO> getListOfFileMetadataPOJO(List<File> filesWithTxtExt) {
		List<FileMetadataPOJO> fileMetadataList = new ArrayList<FileMetadataPOJO>(filesWithTxtExt.size());
		filesWithTxtExt.forEach(file -> {
			String fileName = file.getName();
			long fileSize = file.length();
			fileMetadataList.add(FileMetadataPOJO.getInstance(fileName, fileSize));
		});
		return fileMetadataList;
	}

	@Override
	public void sortFileMetadataBySizeAndName(List<FileMetadataPOJO> fileMetadataList) {
		Collections.sort(fileMetadataList, new Comparator<FileMetadataPOJO>() {

			@Override
			public int compare(FileMetadataPOJO pojo1, FileMetadataPOJO pojo2) {
				if (pojo1.getSize() !=  pojo2.getSize() ) {
					return Long.valueOf(pojo1.getSize()).compareTo(Long.valueOf(pojo2.getSize()));
				}
				
				return pojo1.getName().compareTo(pojo2.getName());
			}
		});
	}
}
