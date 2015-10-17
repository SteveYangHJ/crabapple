// Semaphore.java by Yang,2011-4-14

// Java中提供关键字synchronized 防止资源冲突,其行为类似于Semaphore类
// 当线程要执行Synchronized关键字守护的代码片段的时候,它检查信号量是否存在
// 然后获取信号量,执行代码,释放信号量
package com.crabapple.thinkinjava.thread;

import com.crabapple.thinkinjava.thread.TestFrame.Invariant;
import com.crabapple.thinkinjava.thread.TestFrame.InvariantFailure;
import com.crabapple.thinkinjava.thread.TestFrame.InvariantOK;
import com.crabapple.thinkinjava.thread.TestFrame.InvariantState;

// 模拟信号量类
public class Semaphore implements Invariant {
	private volatile int semaphore = 0;

	public boolean available() {
		return this.semaphore == 0;
	}

	public void acquire() {
		++this.semaphore;
	}

	public void release() {
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

}
