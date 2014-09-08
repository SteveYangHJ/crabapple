/**   
 * @Title: TimeSpanProfiler.java 
 * @Package com.hp.wwops.ecommerce.service.util 
 * @Description: TODO
 * @author Yang, Wei-Peng
 * @email 244weipeng@163.com
 * @date 2012-12-17 PM. 07:40:45 
 * @version V1.0   
 */
 package org.crabapple.common.utils;
 /** 
 * @ClassName: TimeSpanProfiler 
 * @Description: get time interval of operation
 * @author Yang, Wei-Peng
 * @email 244weipeng@163.com
 * @date 2012-12-17 PM. 07:40:45 
 */
public class TimeSpanProfiler {
	long startTime = 0;
	long endTime = 0;
	public TimeSpanProfiler(){
	}
	
    public long beginProfile() {
        startTime = System.currentTimeMillis();    
        return startTime;
    }

    public long endProfile() {
    	endTime = System.currentTimeMillis();
        return endTime;
    }
    
    public long getTimeSpan(){
    	return endTime - startTime;
    }
}

 