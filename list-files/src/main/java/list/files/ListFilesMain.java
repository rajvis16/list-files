package list.files;

import java.io.File;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import list.files.pojo.FileMetadataPOJO;
import list.files.service.FileOrchestrator;

public class ListFilesMain {

	private static final String SOURCE_DIRECTORY_PATH = System.getProperty("java.io.tmpdir");
	
	/**
	 * Entry point for the JAR file. The main() method can be run with or without any argument. If no argument is
	 * given then the code will look into operating system /temp folder and then scan for all the *.txt files. The command to
	 * run without argument is "java -jar list-files-1.0.0-jar-with-dependencies.jar"
	 * 
	 * If any argument is provided then it must be the absolute path of the directory where you want the code to scan for *.txt files.
	 *  For example; if the command "java -jar list-files-1.0.0-jar-with-dependencies.jar /private/var/folders" is given the code will 
	 *  look into all the *.txt files under the directory /private/var/folders 
	 * 
	 * Also, check out list.files.FileOrchestratorImplTest file to go through the test cases.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		FileOrchestrator orchestrator = (FileOrchestrator) context.getBean("fileOrchestrator");
		List<FileMetadataPOJO> sortedList = null;
		
		if (args != null && args.length > 0) {
			sortedList = orchestrator.getFileMetadataSortedBySizeAndName(new File(args[0]));
		} else {
			sortedList = orchestrator.getFileMetadataSortedBySizeAndName(new File(SOURCE_DIRECTORY_PATH));
		}
		
		if (sortedList.isEmpty()) {
			System.out.println("******");
			System.out.println("******Empty List found. No *.txt files were scanned. Please make sure that "
					+ "the path of the directory provided is correct in the argument. If no path provided then default /temp directory "
					+ "will be picked up by default.");
			System.out.println("******");
		} else {
		
			sortedList.forEach(pojo -> {
				System.out.println("Filename="+pojo.getName()+", size="+pojo.getSize());
			});
		}
	}

}
