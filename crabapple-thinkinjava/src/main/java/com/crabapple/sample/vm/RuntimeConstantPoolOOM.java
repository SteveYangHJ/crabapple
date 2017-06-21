package com.crabapple.sample.vm;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * OOM: 运行时常量池溢出
 * 
 * @author SteveHJ
 *
 */
public class RuntimeConstantPoolOOM {

	/**
	 * VM args: -XX:PermSize=10m -XX:MaxPermSize=10m
	 * @param args
	 */
	public static void main(String[] args) {

		// 使用List保持常量池引用，避免Full GC回收常量池行为
		List<String> list = new ArrayList<String>();
		
		// 10M的PermSize在Integer范围内足够产生OOM
		int i = 0;
		while(true){
			String result = String.valueOf("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM" + i++).intern();
			System.out.println(result);
			list.add(result);
		}
	}

}
