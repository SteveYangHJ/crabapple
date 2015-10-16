package com.crabapple.thinkinjava.sample;

import java.util.*;

import org.apache.log4j.Logger;

public class ListSample {
	private static Logger logger = Logger.getLogger(ListSample.class);

	public static void main(String[] args) {
		testList();
	}
	
	public static void testList(){
		List<String> list = new ArrayList<String>();
		logger.debug("list.contains(null) = " + list.contains(null));
		
		//
		list.add("string1");
		list.add("");
		list.add(null);
		logger.debug("After add(null), list.contains(null) = " + list.contains(null));
		
		int oldCapacity = 34;//list.size();
	    int newCapacity = oldCapacity + (oldCapacity >> 1);
	    logger.debug("oldCapacity: " + oldCapacity);
	    logger.debug("newCapacity: " + newCapacity);
	    
	    logger.debug(">>: " + (1 >> 1));
	    logger.debug(">>: " + (2 >> 1));
	    logger.debug(">>: " + (3 >> 1));
	    logger.debug(">>: " + (4 >> 1));
	    logger.debug(">>: " + (11 >> 1));
	}

}
