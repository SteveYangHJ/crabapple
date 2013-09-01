/**
 * @Title: wstax-connector
 * @Package com.hp.wstax.adapter.smartlabor
 * @Description: TODO
 * @author Ye, Jie-Bao(Barry, HPIT-DS)
 * @email jie-bao.ye@hp.com
 * @date 2013-2-5PM. 12:59:00
 * @version V1.0
 */

package org.crabapple.common.utils;

import org.junit.Assert;
import org.junit.Test;

/** 
 * @ClassName: TimeSpanProfilerTest <br/> 
 * @Description: TODO
 * @author Ye, Jie-Bao(Barry, HPIT-DS)
 * @email jie-bao.ye@hp.com
 * @date 2013-2-5 PM. 12:59:00 <br/>
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

