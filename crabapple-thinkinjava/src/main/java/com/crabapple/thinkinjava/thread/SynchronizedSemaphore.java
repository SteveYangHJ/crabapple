// Semaphore.java by Yang,2011-4-14

// Java中提供关键字synchronized 防止资源冲突,其行为类似于Semaphore类
// 当线程要执行Synchronized关键字守护的代码片段的时候,它检查信号量是否存在
// 然后获取信号量,执行代码,释放信号量
package com.crabapple.thinkinjava.thread;

import com.crabapple.thinkinjava.thread.TestFrame.InvariantFailure;
import com.crabapple.thinkinjava.thread.TestFrame.InvariantOK;
import com.crabapple.thinkinjava.thread.TestFrame.InvariantState;
import com.crabapple.thinkinjava.thread.TestFrame.InvariantWatcher;

// 模拟信号量类
// 如果要对类中的某个方法进行同步,最好同步所有方法
public class SynchronizedSemaphore extends Semaphore {
	private volatile int semaphore = 0;

	public synchronized boolean available() {
		return this.semaphore == 0;
	}

	public synchronized void acquire() {
		++this.semaphore;
	}

	public synchronized void release() {
		--this.semaphore;
	}

	@Override
	public InvariantState invariant() {
		int val = this.semaphore;
		if (val == 0 || val == 1)
			return new InvariantOK();
		else
			return new InvariantFailure(new Integer(val));
	}

	// main()函数
	public static void main(String[] args) throws Exception {
		SynchronizedSemaphore sem = new SynchronizedSemaphore();

		// 建立了两个SemaphoreTest线程,会发生违反约束的事件
		new SemaphoreTest(sem);
		new SemaphoreTest(sem);
		new InvariantWatcher(sem).join(); // join()方法,将使程序一直运行,直到发生失败
	}

}
