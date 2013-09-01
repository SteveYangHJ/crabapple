/**   
 * @Title: FileUtils.java 
 * @Package com.hp.it.wstax.common.utils 
 * @Description: TODO
 * @author Yang, Wei-Peng(Steve, HPIT-DS)
 * @email wei-peng.yang@hp.com
 * @date 2013-1-28 PM. 4:23:00 
 * @version V1.0   
 */
package org.crabapple.common.utils.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/** 
 * @ClassName: FileUtils 
 * @Description: TODO
 * @author Yang, Wei-Peng(Steve, HPIT-DS)
 * @email wei-peng.yang@hp.com
 * @date 2013-1-28 PM. 4:23:00 
 */
public class CommonFileUtils {
	private static final Logger logger = LoggerFactory.getLogger(CommonFileUtils.class);
	
	/**
	 * @Fields DEFAULT_FILE_EXTENSION_SPLIT : default file name extension split
	 */
	public static final String DEFAULT_FILE_EXTENSION_SPLIT = ".";

	/**
	 * @Title: getContentFromFile
	 * @Description: 
	 * 		Get the content of the file
	 * @author Yang, Wei-Peng(Steve, HPIT-DS)
	 * @email wei-peng.yang@hp.com
	 * @date 2013-1-28 PM. 4:23:00  
	 * @param filePath, file path, could be located relative path in classapth or absolute path
	 * @return content, if file is not exist, content is null
	 * @throws IOException 
	 */
	public static String getContentFromFile(String filePath) throws IOException{
		File file = CommonFileUtils.getFileByFilePath(filePath);
		if(null == file){
			return null;
		}
		String content = FileUtils.readFileToString(file, Charset.forName("UTF-8"));
		return content;
	}
	
	/**
	 * @Title: getFileByFilePath
	 * @Description: 
	 * 		Get the File object according to the file path
	 * @author Yang, Wei-Peng(Steve, HPIT-DS)
	 * @email wei-peng.yang@hp.com
	 * @date 2013-1-30  PM. 4:19:28 
	 * @param filePath, file path, could be located relative path in classapth or absolute path
	 * @return File object, if file path is empty, then file is null,
	 * @throws IOException 
	 */
	public static File getFileByFilePath(String filePath) throws IOException{
		if(null == filePath || filePath.trim().isEmpty()){
			return null;
		}
		File file = new File(filePath);
			
		if(null== file || !file.exists()){
			Resource resource = new ClassPathResource(filePath);
			if(resource.exists()){
				file = resource.getFile();
			}
		}

		return file;
	}

	/**
	 * @Title: getResourceURL
	 * @Description: 
	 * 		Get the File URL according to the file path
	 * @author Yang, Wei-Peng(Steve, HPIT-DS)
	 * @email wei-peng.yang@hp.com
	 * @date Feb 22, 2013 11:09:09 AM 
	 * @param filePath, file path, could be located relative path in classapth
	 * @return URL, if file path is empty or not exists, then URL is null,
	 * @throws IOException 
	 */
	public static URL getResourceURL(String filePath) throws IOException{
		URL fileUrl = null;
		if(null != filePath && !filePath.trim().isEmpty()){
			Resource resource = new ClassPathResource(filePath);
			if(resource.exists()){
				fileUrl = resource.getURL();
			}
		}	

		return fileUrl;
	}
	
	/**
	 * @Title: getSource
	 * @Description: 
	 * 		Get the File source according to the file path
	 * @author Yang, Wei-Peng(Steve, HPIT-DS)
	 * @email wei-peng.yang@hp.com
	 * @date Feb 22, 2013 11:06:53 AM 
	 * @param filePath, file path, could be located relative path in classapth
	 * @return Source, if file path is empty or not exists, then Source is null,
	 * @throws IOException 
	 */
	public static Source getSource(String filePath) throws IOException{
		Source source = null;
		if(null != filePath && !filePath.trim().isEmpty()){
			Resource resource = new ClassPathResource(filePath);
			if(resource.exists()){
				source = new StreamSource(resource.getInputStream());
			}
		}
		
		return source;
	}
	
	/**
	 * @Title: createNewFile
	 * @Description:
	 * 		Create new file by the file path
	 * @author Yang, Wei-Peng(Steve, HPIT-DS)
	 * @email wei-peng.yang@hp.com
	 * @date Feb 28, 2013 1:47:15 PM 
	 * @param file
	 * @throws IOException
	 */
	public static File createNewFile(String filePath) throws IOException{
		File file = null;
		if(null != filePath && !filePath.trim().isEmpty()){
			file = new File(filePath);
			if(!file.exists()){
				// If the directory of the file is not exist, create it.
				File parent = new File(file.getParent());
				if(!parent.exists()){
					parent.mkdirs();
				}
				// then, create the new file
				file.createNewFile();
			}
		}
		
		return file;
		
	}
	
	/**
	 * @Title: getInputStreamByFilePath
	 * @Description: 
	 * 		Get InputStream by filePath
	 * @author Yang, Wei-Peng(Steve, HPIT-DS)
	 * @email wei-peng.yang@hp.com
	 * @date Mar 5, 2013 9:02:34 PM 
	 * @param filePath
	 * @return
	 * @throws IOException 
	 */
	public static InputStream getInputStreamByFilePath(String filePath) throws IOException{
		if(null == filePath || filePath.trim().isEmpty()){
			return null;
		}
		File file = new File(filePath);
		InputStream is = null;
			
		if(null== file || !file.exists()){
			Resource resource = new ClassPathResource(filePath);
			if(resource.exists()){
				is = resource.getInputStream();
			}
		}else{
			is = new FileInputStream(file);
		}

		return is;
	}
	
	/**
	 * @Title: getExtensionOfFileName
	 * @Description: 
	 * 		Get the extension of the file. If have extension, the function return extension;else return null
	 * 		Have three case, e.g.
	 * 		1, fileName = "example.txt", extension is '.txt'
	 * 		2, fileName = "example", extension is null
	 * 		3, fileName = "example", extension is null
	 * @author Yang, Wei-Peng(Steve, HPIT-DS)
	 * @email wei-peng.yang@hp.com
	 * @date Mar 8, 2013 12:38:44 PM 
	 * @param fileName
	 * @param split, default is '.'
	 * @return
	 */
	public static String getExtensionOfFileName(String fileName, String split){
		if(StringUtils.isBlank(fileName)){
			return null;
		}
		int index = fileName.lastIndexOf(StringUtils.isBlank(split)? CommonFileUtils.DEFAULT_FILE_EXTENSION_SPLIT: split);
        int leg = fileName.length();
        return (index > 0 ? ( (index + 1) == leg ? null : fileName.substring(index, leg) ): null);
	}
	
	/**
	 * @Title: writeIntoFile
	 * @Description: TODO
	 * @author Yang, Wei-Peng(Steve, HPIT-DS)
	 * @email wei-peng.yang@hp.com
	 * @date Jun 11, 2013 11:15:30 AM 
	 * @param content, the data need to be writed to file
	 * @param filePath, file absolutely full path
	 * @param append, boolean if true, then data will be written to the end of the file rather than the beginning.
	 * @throws IOException
	 */
	public static File writeContentIntoFile(String content, String filePath, boolean append) throws IOException{
		BufferedWriter bw = null;
		File file = null;
		try {
			file = new File(filePath);
			if(!file.exists()){
				file = createNewFile(filePath);
			}
			bw = new BufferedWriter(new FileWriter(file, append));
			// witer the content into file
			bw.append(content);
			return file;
		}finally{
			IOUtils.closeQuietly(bw);
		}
	}
}

 