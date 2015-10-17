package com.crabapple.sample.common;

import java.net.URL;

import org.apache.log4j.Logger;

public class ResourceSample {
	private static final Logger logger = Logger.getLogger(ResourceSample.class);

	/**
	 * @author Yang, Wei-Peng (244weipeng@gmail.com)
	 * @date Oct 12, 2015 5:55:27 PM
	 * @param args
	 */
	public static void main(String[] args) {
		ResourceSample sample = new ResourceSample();
		sample.showResourcePath();
	}
	
	public void showResourcePath(){
		
		// by Thread
		URL resourceUrl = Thread.class.getResource("");
		if(resourceUrl != null){
			logger.debug("Thread.class.getResource(\"\").getPath() = " + resourceUrl.getPath());
		}
		
		resourceUrl = Thread.class.getResource("/");
		logger.debug("Thread.class.getResource(\"\").getPath() = " + resourceUrl.getPath());
		logger.debug(Thread.currentThread().getClass().getResource("/").getPath());
		
		
		logger.debug(this.getClass().getResource("").getPath());
		logger.debug(ResourceSample.class.getResource("").getPath());
		logger.debug(this.getClass().getResource("/").getPath());
		logger.debug(ResourceSample.class.getResource("/").getPath());
		//String currentFilePath = 
	}

}
