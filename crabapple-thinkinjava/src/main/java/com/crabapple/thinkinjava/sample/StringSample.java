package com.crabapple.thinkinjava.sample;

public class StringSample {

	/**
	 * @author Yang, Wei-Peng (244weipeng@gmail.com)
	 * @date Oct 13, 2015 5:37:58 PM
	 * @param args
	 */
	public static void main(String[] args) {
		testStringReserve();

	}
	
	public static void testStringReserve(){
		String s = "Greetings big   world";
		System.out.println(reserveString2(s));
		System.out.println(reserveWords(s));
	}
	
	// Pass in “Greetings big world” and returns “dlrow gib sgniteerG”
	public static String reserveString(String s){
		char[] destChars = new char[s.length()];
		for(int i = 0; i < s.length(); i++){
			destChars[i] = s.charAt(s.length() - 1 - i);
		}
		
		return String.copyValueOf(destChars);
	}
	
	// Pass in “Greetings big world” and returns “dlrow gib sgniteerG”
	public static String reserveString2(String s){
		char[] destChars = new char[s.length()];
		for(int i = 0; i < (s.length() + 1)/2; i++){
			// reserver
			destChars[i] = s.charAt(s.length() - 1 - i);
			destChars[s.length() -i - 1] = s.charAt(i);
		}
		
		return String.copyValueOf(destChars);
	}
	
	// Pass in “Greetings big world” and returns “sgniteerG gib dlrow”
	// 分而治之
	public static String reserveWords(String s){
		String[] splitStr = s.split(" ");
//		// 如何统计word之间空格数量?? - No need
//		int[] spaceLength = new int[splitStr.length];

		// Replace old word with new word
		String dest = s;
		for(int i = 0; i < splitStr.length; i++){
			dest = dest.replace(splitStr[i], reserveString(splitStr[i]));
		}
		return dest;
	}

}
