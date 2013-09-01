/**   
 * @Title: PropertiesUtils.java 
 * @Package com.hp.it.wstax.common.utils 
 * @Description: TODO
 * @author Yang, Wei-Peng(Steve, HPIT-DS)
 * @email wei-peng.yang@hp.com
 * @date Mar 4, 2013 4:37:54 PM 
 * @version V1.0   
 */
package org.crabapple.common.utils;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.junit.Assert;
import org.junit.Test;

/** 
 * @ClassName: PropertiesUtils 
 * @Description: TODO
 * @author Yang, Wei-Peng(Steve, HPIT-DS)
 * @email wei-peng.yang@hp.com
 * @date Mar 4, 2013 4:37:54 PM 
 */
public class PropertiesUtilsTest {
	@Test
	public void testGetInstances(){
		//1,
		String propertiesFilePath = "adapter/adapter.properties";
		PropertiesUtils propertiesUtils = new PropertiesUtils(propertiesFilePath);
		Assert.assertNotNull(propertiesUtils);
		//2, 
		propertiesUtils = null;
		Assert.assertNull(propertiesUtils);
		//3, file is not properties file
		propertiesFilePath = "test.xml";
		propertiesUtils = new PropertiesUtils(propertiesFilePath);
		Assert.assertNotNull(propertiesUtils);
		Assert.assertNull(propertiesUtils.getValuesByKey("SMARTLABOR_ADAPTER_URL"));
	}
	
	@Test
	public void testGetValuesByKey(){
		//1,
		String propertiesFilePath = "adapter/adapter.properties";
		PropertiesUtils propertiesUtils = new PropertiesUtils(propertiesFilePath);
		Assert.assertNotNull(propertiesUtils.getValuesByKey("SMARTLABOR_ADAPTER_URL"));
	}
	
	@Test
	public void testSetConfig() throws ConfigurationException{
		String propertiesFilePath = "adapter/adapter.properties";
		Configuration config = new PropertiesConfiguration(propertiesFilePath);
		Assert.assertNotNull(config.getProperty("SMARTLABOR_ADAPTER_URL"));
	}
	
	// 测试发现, PropertiesUtils不能设为单例,
	// 初始化PropertiesUtils时需要加载properties文件,如果加载不同的文件,则可能出错
	@Test
	public void testGetValuesByKeyMultiThread() throws InterruptedException{
//		String[] propFilePaths = new String[]{"adapter/adapter.properties",""};
		for(int i = 0; i < 20; i++){
			Thread thread = new Thread(new Runnable(){	
				public void run(){
					String[] propFilePaths = new String[]{"adapter/adapter.properties",
							"adapter/adapter1.properties", "adapter/adapter2.properties"};
					for(int j = 0; j < propFilePaths.length; j++){
						PropertiesUtils propertiesUtils = new PropertiesUtils(propFilePaths[j]);
						System.out.println("file: " + propFilePaths[j] + 
								", Thread Id = " + Thread.currentThread().getId() + 
								", URL = " + propertiesUtils.getValuesByKey("SMARTLABOR_ADAPTER_URL") + 
								", HTTP_READ_TIME_OUT = " + propertiesUtils.getValuesByKey("HTTP_READ_TIME_OUT"));
						
						try {
							Thread.currentThread();
							Thread.sleep(10);
						} catch (InterruptedException e) {
							System.out.println("Details: " + e.getMessage());
						}
					}
				}
			});
			thread.start();
		}
		
		//1,
		Thread.currentThread();
		Thread.sleep(6000);
		
	}
	@Test
	public void testGetKeys(){
	    String propertiesFilePath = "adapter/adapter.properties";
        PropertiesUtils propertiesUtils = new PropertiesUtils(propertiesFilePath);
        Assert.assertNotNull(propertiesUtils.getKeys());
	}
}

 