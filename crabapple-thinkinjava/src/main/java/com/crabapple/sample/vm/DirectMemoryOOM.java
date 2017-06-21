package com.crabapple.sample.vm;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

/**
 * 
 * OOM: 本机直接内存溢出
 * 
 * @author SteveHJ
 *
 */
public class DirectMemoryOOM {
	
	private static final int _1MB = 1024 * 1024;

	/**
	 * VM Args: -Xms20m -XX:MaxDirectMemorySize=10M 
	 * @param args
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
//		Unsafe类JDK7后不再有用, JDK6前可用
//		Field unsafeField  = Unsafe.class.getDeclaredFields()[0];
//		unsafeField.setAccessible(true);
//		Unsafe unsafe = (Unsafe)unsafeField.get(null);
//		while(true){
//			unsafe.allocateMemory(_1MB);
//		}
	}

}
