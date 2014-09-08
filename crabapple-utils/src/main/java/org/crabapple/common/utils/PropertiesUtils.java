/**   
 * @Title: PropertiesUtils.java 
 * @Package com.hp.it.wstax.common.utils 
 * @Description: TODO
 * @author Yang, Wei-Peng
 * @email 244weipeng@163.com
 * @date 2013-1-29 PM. 6:12:42 
 * @version V1.0   
 */
package org.crabapple.common.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.crabapple.common.utils.file.CommonFileUtils;


/** 
 * @ClassName: PropertiesUtils 
 * @Description: TODO
 * @author Yang, Wei-Peng
 * @email 244weipeng@163.com
 * @date 2013-1-29 PM. 6:12:42 
 */
public class PropertiesUtils {
	private static final Logger logger =  Logger.getLogger(PropertiesUtils.class);
	public static final String DEFAULT_FILE_EXTENSION = ".properties";
	private String fileExtension = DEFAULT_FILE_EXTENSION;
	
	private Configuration config = null;
	
	// Constructor
	public PropertiesUtils(String propertiesFilePath){
		this.load(propertiesFilePath);
	}
	
	/**
	 * @Title: load
	 * @Description: 
	 * 		Load properties file by its file path which it could be absolutely path or classpath
	 *      
	 * @author Yang, Wei-Peng
	 * @email 244weipeng@163.com
	 * @date Mar 8, 2013 11:25:20 AM 
	 * @param propertiesFilePath, property file path, 
	 */
	private void load(String propertiesFilePath){
		String fileExt = CommonFileUtils.getExtensionOfFileName(propertiesFilePath, ".");
		if(StringUtils.equals(fileExtension, fileExt)){
			try {
				this.setConfig(new PropertiesConfiguration(propertiesFilePath));
			} catch (ConfigurationException e) {
				logger.error("Could not find the properties file: " + propertiesFilePath);
				System.err.println("Could not find the properties file: " + propertiesFilePath);
			}
		}
	}
	
	/**
	 * @Title: getValuesByKey
	 * @Description: 
	 * 		get value by the key configured in properties file
	 * @author Yang, Wei-Peng
	 * @email 244weipeng@163.com
	 * @date 2013-1-29 PM. 6:25:15 
	 * @param key
	 * @return
	 */
	public String getValuesByKey(String key){
		if(null == config){
			return null;
		}
		return config.getString(key);
	}
	
	public Long getLongValueByKey(String key){
		if(null == config){
			return null;
		}
		return Long.parseLong(config.getString(key));
	}
	
	// Setter/Getter
	private void setConfig(Configuration config) {
	
		this.config = config;
	}

	public String getFileExtension() {
		
		 return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		
		 this.fileExtension = fileExtension;
	}
	/**
	 * Get all keys of a properties file.
	 * @return
	 */
	public List<String> getKeys(){
	    List<String> keys = new ArrayList<String>();
	    if(null == config){
            return null;
        } else {
            Iterator<String> keyIter = getKeysIterraor();
            while(keyIter.hasNext()){
                keys.add(keyIter.next());
            }
            return keys;
        }
	    
	}
	/**
	 * get iterator of properties file.
	 * @return iterator of properties file.
	 */
	public Iterator<String> getKeysIterraor(){
        return config.getKeys();
    }
	/**
	 * create a new key or update exist key value.
	 * @param key
	 * @param value
	 */
	public void storeProperties(String key, String value){
	    config.setProperty(key, value);
	}

}

 