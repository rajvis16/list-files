package list.files;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import list.files.pojo.FileMetadataPOJO;
import list.files.service.FileOrchestrator;

@ContextConfiguration(locations = { "classpath:beans.xml" })
public class FileOrchestratorImplTest extends AbstractTestNGSpringContextTests {
	
	private static final String SOURCE_DIRECTORY_PATH = System.getProperty("java.io.tmpdir");
	
	@Autowired
	private FileOrchestrator fileOrchestrator;
	
	@BeforeClass
	public void init() {
		String test1txt = "test1.txt";
		try (FileOutputStream outputStream = new FileOutputStream(new File(SOURCE_DIRECTORY_PATH+File.separator+test1txt));) {
			String data = "This is a test file";
			outputStream.write(data.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		File file = new File(SOURCE_DIRECTORY_PATH+File.separator+test1txt);
		Assert.assertTrue(file.exists());
		
		String test2txt = "test2.txt";
		try (FileOutputStream outputStream = new FileOutputStream(new File(SOURCE_DIRECTORY_PATH+File.separator+test2txt));) {
			String data = "This is a test file with more data";
			outputStream.write(data.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		file = new File(SOURCE_DIRECTORY_PATH+File.separator+test2txt);
		Assert.assertTrue(file.exists());
		
		String retesttxt = "retest.txt";
		try (FileOutputStream outputStream = new FileOutputStream(new File(SOURCE_DIRECTORY_PATH+File.separator+retesttxt));) {
			String data = "This is a test file with more data";
			outputStream.write(data.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		file = new File(SOURCE_DIRECTORY_PATH+File.separator+retesttxt);
		Assert.assertTrue(file.exists());
		
		String test3txt = "test3.txt";
		try (FileOutputStream outputStream = new FileOutputStream(new File(SOURCE_DIRECTORY_PATH+File.separator+test3txt));) {
			String data = "This is a test file with more data, even with more data";
			outputStream.write(data.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		file = new File(SOURCE_DIRECTORY_PATH+File.separator+test3txt);
		Assert.assertTrue(file.exists());
		
		String invalid = "test4.invalidext";
		try (FileOutputStream outputStream = new FileOutputStream(new File(SOURCE_DIRECTORY_PATH+File.separator+invalid));) {
			String data = "This file must not be picked up";
			outputStream.write(data.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		file = new File(SOURCE_DIRECTORY_PATH+File.separator+invalid);
		Assert.assertTrue(file.exists());
		
		String test4txt = "test4.txt";
		try (FileOutputStream outputStream = new FileOutputStream(new File(SOURCE_DIRECTORY_PATH+File.separator+test4txt));) {
			String data = "This is a test file with more data, even with more data";
			outputStream.write(data.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		file = new File(SOURCE_DIRECTORY_PATH+File.separator+test4txt);
		Assert.assertTrue(file.exists());
	}
	
	@Test
	public void getFileMetadataSortedBySizeAndName() {
		List<FileMetadataPOJO> listOfMetadatafile = fileOrchestrator.getFileMetadataSortedBySizeAndName(new File(SOURCE_DIRECTORY_PATH));
		Assert.assertEquals(listOfMetadatafile.size(), 5);
		
		FileMetadataPOJO pojo = listOfMetadatafile.get(0);
		Assert.assertEquals(pojo.getSize(), 19);
		Assert.assertEquals(pojo.getName(), "test1.txt");
		
		pojo = listOfMetadatafile.get(1);
		Assert.assertEquals(pojo.getSize(), 34);
		Assert.assertEquals(pojo.getName(), "retest.txt");
		
		pojo = listOfMetadatafile.get(2);
		Assert.assertEquals(pojo.getSize(), 34);
		Assert.assertEquals(pojo.getName(), "test2.txt");
		
		pojo = listOfMetadatafile.get(3);
		Assert.assertEquals(pojo.getSize(), 55);
		Assert.assertEquals(pojo.getName(), "test3.txt");
		
		pojo = listOfMetadatafile.get(4);
		Assert.assertEquals(pojo.getSize(), 55);
		Assert.assertEquals(pojo.getName(), "test4.txt");
	}
	
	
	@Test
	public void getFileMetadataSortedBySizeAndName_WithInvalidSourceDir() {
		List<FileMetadataPOJO> listOfMetadatafile = fileOrchestrator.getFileMetadataSortedBySizeAndName(new File(SOURCE_DIRECTORY_PATH+File.pathSeparator+"invalid"));
		Assert.assertTrue(listOfMetadatafile.size() == 0);
	}
	
	@Test
	public void getFileMetadataSortedBySizeAndName_WithSourceDirAsNull() {
		List<FileMetadataPOJO> listOfMetadatafile = fileOrchestrator.getFileMetadataSortedBySizeAndName(null);
		Assert.assertTrue(listOfMetadatafile.size() == 0);
	}
	
	@AfterClass
	public void destroy() {
		File file = new File(SOURCE_DIRECTORY_PATH+File.separator+"test1.txt");
		file.delete();
		Assert.assertFalse(file.exists());
		
		file = new File(SOURCE_DIRECTORY_PATH+File.separator+"test2.txt");
		file.delete();
		Assert.assertFalse(file.exists());
		
		file = new File(SOURCE_DIRECTORY_PATH+File.separator+"retest.txt");
		file.delete();
		Assert.assertFalse(file.exists());
		
		file = new File(SOURCE_DIRECTORY_PATH+File.separator+"test3.txt");
		file.delete();
		Assert.assertFalse(file.exists());
		
		file = new File(SOURCE_DIRECTORY_PATH+File.separator+"test4.invalidext");
		file.delete();
		Assert.assertFalse(file.exists());
		
		file = new File(SOURCE_DIRECTORY_PATH+File.separator+"test4.txt");
		file.delete();
		Assert.assertFalse(file.exists());
		
	}

}
