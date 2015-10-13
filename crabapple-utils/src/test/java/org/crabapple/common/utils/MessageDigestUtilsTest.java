
package org.crabapple.common.utils;

import java.security.NoSuchAlgorithmException;

import junit.framework.Assert;

import org.junit.Test;

/**
 * @ClassName: MessageDigestUtilsTest 
 * @Description: TODO
 * @author Yang, Wei-Peng
 * @email 244weipeng@163.com
 * @date Jun 11, 2013 10:41:21 AM 
 */
public class MessageDigestUtilsTest {
	
	@Test
	public void testEncodeByMD5ToHexString() throws NoSuchAlgorithmException{
		String sourceDigest = "14.33.127.34" + "TEST";
		String hexResult = MessageDigestUtils.encodeByMD5ToHexString(sourceDigest);
		Assert.assertNotNull(hexResult);
		String hexResult1 = MessageDigestUtils.encryptToHexString(sourceDigest, "MD5");
		Assert.assertNotNull(hexResult1);
		Assert.assertEquals(hexResult, hexResult1);
		
		// 2, 
		hexResult1 = MessageDigestUtils.encryptToHexString(sourceDigest, "SHA");
		Assert.assertFalse(hexResult.equals(hexResult1));
	}
	
	@Test(expected = NoSuchAlgorithmException.class)
	public void testEncodeByMD5ToHexString1() throws NoSuchAlgorithmException{
		MessageDigestUtils.encryptToHexString("Steve", "HAX");
	}
}
