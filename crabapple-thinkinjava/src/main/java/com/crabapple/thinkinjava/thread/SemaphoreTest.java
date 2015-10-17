package com.crabapple.thinkinjava.thread;

import com.crabapple.thinkinjava.thread.TestFrame.InvariantWatcher;

// SemaphoreTest类创建了一个线程,
// 此线程不断测试,以检查Semaphore对象是否可用,可用的话就获取它,然后释放-->run()方法
public class SemaphoreTest extends Thread {
	// volatile关键字:确保编译器不会对任何读取此值的操作进行优化
	private volatile Semaphore semaphore;

	// 构造函数
	public SemaphoreTest(Semaphore semaphore) {
		this.semaphore = semaphore;
		this.setDaemon(true);
		this.start();
	}

	// 重写
	@Override
	public void run() {
		while (true) {
			if (this.semaphore.available()) {
				Thread.yield(); // make it fail faster
				this.semaphore.acquire();
				Thread.yield();
				this.semaphore.release();
				Thread.yield();
			}
		}
	}

	// main()函数
	public static void main(String[] args) throws Exception {
		Semaphore sem = new Semaphore();

		// 建立了两个SemaphoreTest线程,会发生违反约束的事件
		new SemaphoreTest(sem);
		new SemaphoreTest(sem);
		new InvariantWatcher(sem).join(); // join()方法,将使程序一直运行,直到发生失败
	}

}
