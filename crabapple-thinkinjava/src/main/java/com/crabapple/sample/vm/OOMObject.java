package com.crabapple.sample.vm;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 内存占位符对象,一个OOMObject大约占64k
 * VM Args: -Xms100m -Xmx100m -XX:UseSerialGC
 * 考虑: 
 * (1), 没有指定-Xmn参数,新生代有多个?
 * (2), System.gc()后老年代柱状图仍显示为峰值
 * @author SteveHJ
 *
 */
public class OOMObject {
	
	public byte[] placeholder = new byte[64 * 1024];
	
	public static void fillHeap(int num) throws InterruptedException{
		List<OOMObject> list = new ArrayList<OOMObject>();
		for(int i = 0; i < num; i++){
			Thread.sleep(50);
			list.add(new OOMObject());
		}
		
		System.gc();
	}

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {

		// 以64kb/50ms的速度往Java内存中填充数据, 填充1000次
		fillHeap(1000);
	}

}
