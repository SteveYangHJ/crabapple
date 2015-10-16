package com.crabapple.thinkinjava.sample;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

public class ClassSample {	
	private static Logger logger = Logger.getLogger(ClassSample.class);
	
	// 2, Test Object
	public void test() {
		TestClass para1 = new TestClass();
		para1.setTest(new Integer(10));
		TestClass result1 = test1(para1);
		TestClass para2 = new TestClass();
        		para2.setTest(new Integer(10));
        		TestClass result2 = test2(para2);
		System.out.println("para1   = " + para1.getTest());
		System.out.println("para2   = " + para2.getTest());
	}
	public TestClass test1(TestClass t) {
		t = new TestClass();
		t.setTest(new Integer(20));
		return t;
	}
	public TestClass test2(TestClass t) {
		t.setTest(new Integer(20));
		return t;
	}
	class TestClass {
		Integer test = null;
		public void setTest(Integer i) {
			test = i;
		}
		public Integer getTest() {
			return test;
		}
	}

	// 3, Test %
	public static void testSixties(){
		int x = 5;  int y = 7 ;
	    System.out.print(((y * 2) % x));
	    System.out.print(" " + (y % x));
	}
	
	// 5, Test Synchronized
	public synchronized void go() { /* code here */ }
	void go1() {
		synchronized(Object.class) { /* code here */ }
	}
	void go2() {
		Object o = new Object();
		synchronized(o) { /* code here */ }
	}
	
	// 6, Test Integer, 变量初始化
	public static void testInteger(){
		
		// 1, 数组(基本数据类型 + 包装类)初始化
		Integer[] integerArray = new Integer[5];
		int[] intArray = new int[5];
		Byte[] byteArray = new Byte[5];
		byte[] byteArray1 = new byte[5];
		char[] charArray = new char[5];
		float[] floatArray = new float[5];
		double[] doubleArray = new double[5];
		System.out.println("Integer[0] = " + integerArray[0]);
		System.out.println("int[0] = " + intArray[0]);
		System.out.println("ByteArray[0] = " + byteArray[0]);
		System.out.println("byteArray[0] = " + byteArray1[0]);
		System.out.println("charArray[0] = " + charArray[0]);
		System.out.println("doubleArray[0] = " + doubleArray[0]);
		System.out.println("floatArray[0] = " + floatArray[0]);
		
		// 2, 
		Integer integer1 = new Integer(3);
		Integer integer2 = 3; 
		System.out.println(integer1.equals(integer2));
	}
	
	// Test: Overload main() and equals 
	public static void testOverload(){
		main(null, "main()");
		ClassSample s1 = new ClassSample();
		ClassSample s2 = new ClassSample();
		System.out.println(s1.equals(s2));
		System.out.println(s1.equals());
	}
	
	public static void main(String[] args, String param) {
		System.out.println("Overload the main() method");
	}
	
	public boolean equals(){
		return false;
	}
	
	public static void main(String[] args) {
		ClassSample sample = new ClassSample();
//		sample.test();
//		testSixties();
		//
//		sample.go();
//		sample.go1();
//		sample.go2();
		
		// TODO:
//		testInteger();
//		testOverload();
		logger.debug("0^0 = " + (0^0));
		logger.debug("1^2 = " + (1^2));
		logger.debug("1^3 = " + (1^3));
		logger.debug("2^3 = " + (2^3));
		logger.debug("3^1 = " + (3^1));
		logger.debug("43673435^353454656 = " + (43673435^353454656));
	}

}
