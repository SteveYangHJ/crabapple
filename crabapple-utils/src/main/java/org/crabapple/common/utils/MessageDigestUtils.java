/**
 * @Title: MessageDigestUtils.java 
 * @Package com.hp.it.wstax.adapter.common.utils 
 * @Description: TODO
 * @author Yang, Wei-Peng
 * @email 244weipeng@163.com
 * @date Jun 6, 2013 11:32:59 AM 
 * @version V1.0   
 */
package org.crabapple.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName: MessageDigestUtils 
 * @Description: TODO
 * @author Yang, Wei-Peng
 * @email 244weipeng@163.com
 * @date Jun 6, 2013 11:32:59 AM 
 */
public class MessageDigestUtils {
	
	/**
	 * @Fields ENCRYPTION_ALGORITHM_MD5 : MD5 encryption algorithm 
	 */
	public static final String ENCRYPTION_ALGORITHM_MD5 = "MD5";
	
	/**
	 * @Title: encodeByMD5ToHexString
	 * @Description: 
	 * 		encode the source through MD5 encryption algorithm to Hex String data
	 * @author Yang, Wei-Peng
	 * @email 244weipeng@163.com
	 * @date Jun 6, 2013 10:21:26 AM 
	 * @param sourceDigest, source data which need to be encrypted
	 * @return destDigest, the encrypted string data
	 * @throws NoSuchAlgorithmException
	 */
	public static String encodeByMD5ToHexString(String sourceDigest) throws NoSuchAlgorithmException{
		String algorithm = ENCRYPTION_ALGORITHM_MD5;
		return encryptToHexString(sourceDigest, algorithm);
	}
	
	/**
	 * @Title: encodeToHexString
	 * @Description: 
	 * 		encode the source through one encryption algorithm to Hex String data
	 * @author Yang, Wei-Peng
	 * @email 244weipeng@163.com
	 * @date Jun 6, 2013 1:29:55 PM 
	 * @param sourceDigest, source data which need to be encrypted
	 * @param algorithm, encryption algorithm, e.g. "MD5", "SHA"....
	 * @return destDigest, the encrypted string data
	 * @throws NoSuchAlgorithmException
	 */
	public static String encryptToHexString(String sourceDigest, String algorithm) throws NoSuchAlgorithmException{
		byte[] destDigest = null;
		// encrypte
		MessageDigest dig = MessageDigest.getInstance(algorithm);
		dig.reset();
		destDigest = dig.digest(sourceDigest.trim().getBytes());
		
		// parse to Hex String
        StringBuffer destDigestStr = new StringBuffer();  
        for (int i = 0; i < destDigest.length; ++i) {  
        	destDigestStr.append(Integer.toHexString((destDigest[i] & 0xFF) | 0x100).substring(1, 3));  
        }  
		return destDigestStr.toString();
	}

}
