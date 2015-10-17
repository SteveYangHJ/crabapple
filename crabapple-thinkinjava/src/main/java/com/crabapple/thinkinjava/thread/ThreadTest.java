// write by Yang,2011年4月13
// 线程:涉及到:sleep(),join(),yield(),设置优先级,
// 1. 后台线程(如:main()函数)或守护线程--->方法:setDaemon()
// 2. join()方法:对其调用可被函数interrupt()方法中断
// 3. 共享受限资源
package com.crabapple.thinkinjava.thread;

public class ThreadTest {
	public static void main(String[] args) {
		// SimpleThread.test();
		// SleepingThread.test();
		PriorityThread.test();
	}
}

// SimpleThread类
class SimpleThread extends Thread {
	private int countDown = 5;
	private static int threadCount = 0;

	// 构造函数
	public SimpleThread() {
		super("" + (++threadCount)); // 保存线程名字(即传递的参数)
		start();
	}

	@Override
	public String toString() {
		return "#" + this.getName() + ":" + this.countDown;
	}

	// 在run(0方法返回的地点,将有线程机制终止此线程
	@Override
	public void run() {
		while (true) {
			System.out.println(this);
			if (--countDown == 0)
				return;

			/*
			 * // 暂停当前正在执行的线程对象,并执行其他线程-->让步 Thread.yield();
			 */

			// 使用sleep()方法,使线程停止一段时间
			try {
				sleep(190);
			} catch (InterruptedException e) {
				throw new RuntimeException();
			}
		}
	}

	// 测试SimpleThread类
	public static void test() {
		// 创建5个线程
		for (int i = 0; i < 5; i++) {
			new SimpleThread();
		}
	}
}

// SleepingThread类
class SleepingThread extends Thread {
	private int countDown = 5;
	private static int threadCount = 0;

	public SleepingThread() {
		super("" + (++threadCount)); // 保存线程名字(即传递的参数)
		start();
	}

	@Override
	public String toString() {
		return "#" + this.getName() + ":" + this.countDown;
	}

	// 在run(0方法返回的地点,将有线程机制终止此线程
	@Override
	public void run() {
		while (true) {
			System.out.println(this);
			if (--countDown == 0)
				return;

			try {
				sleep(190);
			} catch (InterruptedException e) {
				throw new RuntimeException();
			}
		}
	}

	// 测试SleepingThread类
	public static void test() {
		// 创建5个线程
		for (int i = 0; i < 5; i++) {
			// join()等待该线程终止,可以看到有无join()的区别
			// 有join()方法,main()会在继续执行之前等待线程结束
			try {
				new SleepingThread().join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

// PriorityThread类
// 测试优先级priority
class PriorityThread extends Thread {
	private int countDown = 5;
	// volatile关键字,确保不进行优化
	private volatile double d = 0; // No optimization(最优化)

	public PriorityThread(int priority) {
		this.setPriority(priority);
		start();
	}

	@Override
	public String toString() {
		// 打印线程的名称,优先权及线程所属的'线程组'
		return super.toString() + ":" + this.countDown;
	}

	// 在run(0方法返回的地点,将有线程机制终止此线程
	@Override
	public void run() {
		while (true) {
			// An expensive,interruptable operation
			for (int i = 0; i < 10000; i++) {
				d = d + (Math.PI + Math.E) / (double) i;
			}
			System.out.println(this);
			if (--countDown == 0)
				return;
		}
	}

	// 测试SimpleThread类
	public static void test() {
		new PriorityThread(Thread.MAX_PRIORITY);
		// 创建5个线程
		for (int i = 0; i < 5; i++) {
			new PriorityThread(Thread.MIN_PRIORITY);
		}
	}
}