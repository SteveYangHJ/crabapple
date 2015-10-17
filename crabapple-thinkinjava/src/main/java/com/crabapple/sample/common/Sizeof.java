package com.crabapple.sample.common;


/**
 * 自定义Sizeof类,模拟C中的函数sizeof(),测试数据类型在内村中的大小
 * 这个例子正好说明了java中基本类型封装对象所占内存的大小. 
 * 	1.简单的Object对象要占用8个字节的内存空间，因为每个实例都至少必须包含一些最基本操作,比如:wait()/notify(),equals(),   hashCode()等 
 * 	2.使用Integer对象占用了16个字节,而int占用4个字节,说了封装了之后内存消耗大了4倍 
 * 	3.那么Long看起来比Integer对象应该使用更多空间,结果Long所占的空间也是16个字节. 
 * 那么就正好说明了JVM的对于基本类型封装对象的内存分配的规则是如下: 
 * 		Object所占内存(8个字节) + 最大基本类型(long)所占内存(8个字节)   =   16字节. 
 *  	JVM强制使用8个字节作为边界. 所以所有基本类型封装对象所占内存的大小都是16字节.
 *  但是还是有区别,比如:Integer对象虽然占用了16个字节的内存,但是只是利用了Object所占内存(8个字节)+int所占内存(4个字节)   =   12字节.还有4个字节根本没有被使用.
 * @author SteveHJ
 *
 */
public class Sizeof {
	private static final Runtime s_runtime = Runtime.getRuntime();

	//
	private static void runGC() throws Exception {
		// It helps to call Runtime.gc()
		// using several method calls:
		for (int r = 0; r < 4; ++r) {
			_runGC();
		}
	}

	//
	private static void _runGC() throws Exception {
		long usedMem1 = usedMemory(), usedMem2 = Long.MAX_VALUE;

		for (int i = 0; (usedMem1 < usedMem2) && (i < 500); ++i) {
			s_runtime.runFinalization();
			s_runtime.gc();

			Thread.yield();

			usedMem2 = usedMem1;
			usedMem1 = usedMemory();
		}
	}

	//
	private static long usedMemory() {
		return s_runtime.totalMemory() - s_runtime.freeMemory();
	}

	public static void main(String[] args) throws Exception {
		// Warm up all classes/methods we will use
		runGC();
		usedMemory();

		// Array to keep strong references to allocated objects
		final int count = 100000;
		Object[] objects = new Object[count];
		long heap1 = 0;

		// Allocate count+1 objects, discard the first one
		for (int i = -1; i < count; ++i) {
			Object object = null;

			/* Instantiate your data here and assign it to object */
			// object = new Object ();
			object = new Integer(i); // ？？？？
			// object = new Long (i); //？？？？
			// object = new String ();
			// object = new Byte( (byte) 0); //？？？？
			// object = new Float( 0.2f); //？？？？
			// object = new Double( 0); //？？？？

			if (i >= 0)
				objects[i] = object;
			else {
				object = null; // Discard the warm up object
				runGC();
				heap1 = usedMemory(); // Take a before heap snapshot
			}
		}

		runGC();
		long heap2 = usedMemory(); // Take an after heap snapshot:

		final int size = (int) Math.round(((double) (heap2 - heap1)) / count);
		System.out
				.println(" 'before'heap: " + heap1 + ",'after'heap: " + heap2);
		System.out.println("heap delta:" + (heap2 - heap1) + ",{"
				+ objects[0].getClass() + "} size = " + size + "bytes ");

		for (int i = 0; i < count; ++i) {
			objects[i] = null;
		}
		objects = null;
	}

} // End of class
