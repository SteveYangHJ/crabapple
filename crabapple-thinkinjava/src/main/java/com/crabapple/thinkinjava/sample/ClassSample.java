package com.crabapple.thinkinjava.sample;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ClassSample {

	/**
	 * @author Yang, Wei-Peng (244weipeng@gmail.com)
	 * @date Oct 13, 2015 4:45:05 PM
	 * @param args
	 */
	public static void main(String[] args) {
		ClassSample sample = new ClassSample();
		//sample.test();
		//testSixties();
		sample.testPersonByHash();
		
		//
		sample.go();
		sample.go1();
		sample.go2();
		
		// TODO:
		testInteger();
	}
	
	// 1, Test StringBuffer
	public static void testStringBuffer(){
		StringBuffer a = new StringBuffer("A");
		StringBuffer b = new StringBuffer("B");
		operate(a, b);
		System.out.println(a + "," + b);
	}
	
	public static void operate(StringBuffer x, StringBuffer y) {
		x.append(y);
		y = x;
		System.out.println(x + "," + y);
	}
	
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
	
	// 4, Test Hashcode() 
	class Person{
		private String name;
		Person(String name){
			this.name = name;
		}
		public int hashCode(){
			return 420;
		}
	}
	
	public void testPersonByHash(){
		Person person1 = new Person("Person1");
		Person person2 = new Person("Person2");
		// Test HashMap
		Map<Person,String> personMap = new HashMap<Person,String>();
		System.out.println(personMap.put(person1, "person1"));
		personMap.put(person2, "person2");
		System.out.println(personMap.containsKey(person1));
		System.out.println(personMap.containsKey(person2));
		System.out.println(personMap.size());
		
		// Test HashSet
		Set<Person> personSet = new HashSet<Person>();
		personSet.add(person1);
		personSet.add(person2);
		System.out.println(personSet.size());
		
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
	
	public static void testInteger(){
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
	}

}
