/**   
 * @Title: FileUtilsTest.java 
 * @Package com.hp.it.wstax.common.utils 
 * @Description: TODO
 * @author Yang, Wei-Peng(Steve, HPIT-DS)
 * @email wei-peng.yang@hp.com
 * @date 2013-1-28 PM. 4:32:37 
 * @version V1.0   
 */
package org.crabapple.common.utils.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import javax.xml.transform.Source;

import org.apache.commons.io.IOUtils;
import org.crabapple.common.utils.file.CommonFileUtils;
import org.junit.Test;


import junit.framework.Assert;

/** 
 * @ClassName: FileUtilsTest 
 * @Description: TODO
 * @author Yang, Wei-Peng(Steve, HPIT-DS)
 * @email wei-peng.yang@hp.com
 * @date 2013-1-28 PM. 4:32:37 
 */
public class CommonFileUtilsTest {
	public static final String EXISTS_FILE_PATH = "adapter/test.xml";
	public static final String EMPTY_FILE_PATH = "";
	public static final String NOEXISTS_FILE_PATH = "c:/opt/test_not_exist.xml";
	public static final String FILE_NOEXISTS_FILE_PATH2 = "c:/opt/test.xml";
	public static final String FILE_PARENT_NOEXISTS_FILE_PATH2 = "c:/opt/test/test.xml";
	public static final String NULL_FILE_PATH = null;
	
	@Test
	public void testGetContentFromFile() throws IOException{
		// 1, filePath is empty 
		String content = CommonFileUtils.getContentFromFile(EMPTY_FILE_PATH);
		Assert.assertNull(content);
		
		// 2, file exists, 
		content = CommonFileUtils.getContentFromFile(EXISTS_FILE_PATH);	
		Assert.assertNotNull("The content we got from the file: " + EXISTS_FILE_PATH + " is null!", content);
		Assert.assertNotSame("The content we got from the file: " + EXISTS_FILE_PATH + " is null!","",content.trim());
	
				
		// 4, filepath is null
		content = CommonFileUtils.getContentFromFile(NULL_FILE_PATH);
		Assert.assertNull("The content we got from the file: " + NULL_FILE_PATH + " is null!", content);
		
	}

	@Test(expected=IOException.class)
	public void testGetContentFromFile2() throws IOException{
		// 3, file do not exist, 
		String content = CommonFileUtils.getContentFromFile(NOEXISTS_FILE_PATH);
		Assert.assertNull("The content we got from the file: " + NOEXISTS_FILE_PATH + " is null!", content);

	}
	
	@Test
	public void testGetFileByFilePath() throws IOException{
		// 1,
		File file = CommonFileUtils.getFileByFilePath(EMPTY_FILE_PATH);
		Assert.assertNull(file);
		
		// 2,
		file = CommonFileUtils.getFileByFilePath(NOEXISTS_FILE_PATH);
		Assert.assertNotNull(file);
		
		// 3,
		file = CommonFileUtils.getFileByFilePath(EXISTS_FILE_PATH);
		Assert.assertNotNull(file);
		
		// 4, 
		CommonFileUtils utils = new CommonFileUtils();
		Assert.assertNotNull(utils);
	}
	
	@Test
	public void testGetResourceURL() throws IOException{
		// 1, 
		URL url = CommonFileUtils.getResourceURL(EMPTY_FILE_PATH);
		Assert.assertNull(url);
		
		// 2, 
		url = CommonFileUtils.getResourceURL(EXISTS_FILE_PATH);
		Assert.assertNotNull(url);
		
		// 3, 
		url = CommonFileUtils.getResourceURL(NOEXISTS_FILE_PATH);
		Assert.assertNull(url);
		
		// 4, 
		url = CommonFileUtils.getResourceURL(NULL_FILE_PATH);
		Assert.assertNull(url);
	}
	
	@Test
	public void testGetSource() throws IOException{
		// 1, 
		Source source = CommonFileUtils.getSource(EMPTY_FILE_PATH);
		Assert.assertNull(source);
		
		// 2, 
		source = CommonFileUtils.getSource(EXISTS_FILE_PATH);
		Assert.assertNotNull(source);
		
		// 3, 
		source = CommonFileUtils.getSource(NOEXISTS_FILE_PATH);
		Assert.assertNull(source);
		
		// 4, 
		source = CommonFileUtils.getSource(NULL_FILE_PATH);
		Assert.assertNull(source);
	}
	
	@Test
	public void testCreateNewFile() throws IOException{
		// 1, 
		File file = CommonFileUtils.createNewFile(EMPTY_FILE_PATH);
		Assert.assertNull(file);
		
		// 2, 
		file = CommonFileUtils.createNewFile(EXISTS_FILE_PATH);
		Assert.assertNotNull(file);
		Assert.assertTrue(file.isFile());
		
		// 3, 
		file = CommonFileUtils.createNewFile(FILE_NOEXISTS_FILE_PATH2);
		Assert.assertNotNull(file);
		Assert.assertTrue(file.isFile());
		file.delete();
		
		// 4, 
		file = CommonFileUtils.createNewFile(FILE_PARENT_NOEXISTS_FILE_PATH2);
		Assert.assertNotNull(file);
		Assert.assertTrue(file.isFile());
		file.delete();
		
		// 4, 
		file = CommonFileUtils.createNewFile(NULL_FILE_PATH);
		Assert.assertNull(file);
	}
	
	@Test
	public void testGetInputStreamByFilePath() throws IOException{
		// 1, 
		InputStream is = CommonFileUtils.getInputStreamByFilePath(EMPTY_FILE_PATH);
		Assert.assertNull(is);
		
		// 2, 
		is = CommonFileUtils.getInputStreamByFilePath(EXISTS_FILE_PATH);
		Assert.assertNotNull(is);
		
		// 3, 
		is = CommonFileUtils.getInputStreamByFilePath(FILE_NOEXISTS_FILE_PATH2);
		Assert.assertNull(is);
		
		// 4, 
		is = CommonFileUtils.getInputStreamByFilePath(FILE_PARENT_NOEXISTS_FILE_PATH2);
		Assert.assertNull(is);
		
		// Close the Stream
		IOUtils.closeQuietly(is);
	}
	
	@Test
	public void testgetExtensionOfFileName(){
		// 1, 
		String ext = CommonFileUtils.getExtensionOfFileName("name.txt", ".");
		Assert.assertEquals(".txt", ext);
		
		// 2, 
		ext = CommonFileUtils.getExtensionOfFileName("name.", ".");
		Assert.assertNull(ext);
		
		// 3,
		ext = CommonFileUtils.getExtensionOfFileName("name", ".");
		Assert.assertNull(ext);
		
		// 4,
		ext = CommonFileUtils.getExtensionOfFileName("name.txt", " ");
		Assert.assertEquals(".txt", ext);
		
		// 5,
		ext = CommonFileUtils.getExtensionOfFileName(" ", " ");
		Assert.assertNull(ext);
	}
	
	@Test
	public void testWriteContentIntoFile() throws IOException{
		String filePath = null;
		String fileName = "utils-test.txt";
		if(isWindowsOS()){
			filePath = "G:/GitHub/crabapple/crabapple-utils/" + fileName;
		}else{
			filePath = "/home/root/test/" + fileName;
		}
		File file = CommonFileUtils.writeContentIntoFile("content", filePath, true);
		Assert.assertNotNull(file);
		Assert.assertTrue(file.exists());
		
		file.delete();
	}
	
	private static boolean isWindowsOS() {
		Properties prop = System.getProperties();
		String os = prop.getProperty("os.name");
		return os.startsWith("win") || os.startsWith("Win");
	}
}

 