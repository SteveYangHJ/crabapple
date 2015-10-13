package org.crabapple.common.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Yang, Wei-Peng (244weipeng@gmail.com)
 * @date Oct 12, 2015 5:09:52 PM
 */
public class TimeSpanProfilerTest {

	@Test
	public void testBeginProfile(){
		TimeSpanProfiler timeSpanProfiler = new TimeSpanProfiler();
		long startTime = timeSpanProfiler.beginProfile();
		Assert.assertNotNull(startTime);
	}

	@Test
	public void testEndProfile(){
		TimeSpanProfiler timeSpanProfiler = new TimeSpanProfiler();
		long endTime = timeSpanProfiler.endProfile();
		Assert.assertNotNull(endTime);
	}

	@Test
	public void testGetTimeSpan(){
		TimeSpanProfiler timeSpanProfiler = new TimeSpanProfiler();
		long startTime = timeSpanProfiler.beginProfile();
		long endTime = timeSpanProfiler.endProfile();
		Assert.assertNotNull(startTime);
		
		long timeSpan = timeSpanProfiler.getTimeSpan();
		Assert.assertNotNull(timeSpan);
		Assert.assertEquals((endTime - startTime), timeSpan);
	}

}

