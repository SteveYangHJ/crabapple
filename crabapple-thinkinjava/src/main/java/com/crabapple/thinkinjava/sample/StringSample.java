package com.crabapple.thinkinjava.sample;

import java.util.StringTokenizer;

public class StringSample {

	/**
	 * @author Yang, Wei-Peng (244weipeng@gmail.com)
	 * @date Oct 13, 2015 5:37:58 PM
	 * @param args
	 */
	public static void main(String[] args) {
		// 1, 
//		testStringReserve();
		
		//
		testStringTokenizer();
		
		// 2,
		//testStringBuffer();
	}
	
	// Test1, String Reserve - Just use methods of String, not StringTokenizer
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
			// 互相替换
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
	
	public static void testStringTokenizer(){
		String str = "Aasdad2 SADSD fsfsd, j,fkls-1234djf\tsads\ns";
		StringTokenizer tokenizer = new StringTokenizer(str);
		System.out.println("Token Count: " + tokenizer.countTokens());
		while(tokenizer.hasMoreTokens()){
			System.out.println(tokenizer.nextToken());
		}
	}
	
	// , Test StringBuffer
	public static void testStringBuffer(){
		StringBuffer a = new StringBuffer("A");
		StringBuffer b = new StringBuffer("B");
		operate(a, b);
		System.out.println("After Operate(): " + a + "," + b);
		System.out.println("---------------------------------");
	
		// String
		String str1 = "A";
		String str2 = "B";
		operate(str1, str2);
		System.out.println("After Operate(): " + str1 + "," + str2);
		System.out.println("---------------------------------");
		
		// Long
		long l1 = 34;
		long l2 = 23;
		operate(l1, l2);
		System.out.println("After Operate(): " + l1 + "," + l2);
		Long l3 = l1;
		Long l4 = l2;
		operate(l3, l4);
		System.out.println("After Operate(): " + l3 + "," + l4);
		
		System.out.println("---------------------------------");
		
		// Test Object
		Test t1 = new Test("A", l3);
		Test t2 = new Test("A", l4);
		operate(t1,t2);
		System.out.println("Operate(Test, Test):" + t1.getL() + "," + t2.getL());
		System.out.println("Operate(Test, Test):" + t1.getS() + "," + t2.getS());
	}
	
	public static void operate(StringBuffer x, StringBuffer y) {
		x.append(y);
		y = x;
		System.out.println("Operate(StringBuffer, StringBuffer):" + x + "," + y);
	}
	
	public static void operate(String x, String y) {
		x.concat(y);
		y = x;
		System.out.println("Operate(String, String):" + x + "," + y);
	}
	
	public static void operate(long x, long y) {
		x += y;
		y = x;
		System.out.println("Operate(long, long):" + x + "," + y);
	}
	
	public static void operate(Long x, Long y) {
		x += y;
		y = x;
		System.out.println("Operate(Long, Long):" + x + "," + y);
	}
	
	public static void operate(Test x, Test y) {
		x.setL(x.getL() + y.getL());
		x.setS(x.getS() + y.getS());
		y = x;
		System.out.println("Operate(Test, Test):" + x.getL() + "," + y.getL());
		System.out.println("Operate(Test, Test):" + x.getS() + "," + y.getS());
	}
	
	static class Test{
		private String s;
		private Long l;
		Test(){}
		Test(String s, Long l){
			this.s = s;
			this.l = l;
		}
		
		public String getS() {
			return s;
		}
		public void setS(String s) {
			this.s = s;
		}
		public Long getL() {
			return l;
		}
		public void setL(Long l) {
			this.l = l;
		}
	}

}
