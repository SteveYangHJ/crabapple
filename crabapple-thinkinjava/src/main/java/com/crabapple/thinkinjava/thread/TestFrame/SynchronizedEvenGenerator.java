package com.crabapple.thinkinjava.thread.TestFrame;

//加synchronized关键字,防止线程冲突(Thread Collisions)
// 关键: 每个访问关键共享资源的方法必须全部是Synchronized
public class SynchronizedEvenGenerator implements Invariant {
	private int i;

	//
	public synchronized void next() {
		i++;
		i++;
	}

	public synchronized int getValue() {
		return i;
	}

	// 实现方法 ---用于测试,故未同步synchronized
	// 必须把所有相关的值存放在局部变量,这样才能返回你真正测试的那个值
	// 否则返回的时候,这个值可能已经被别的线程改变
	public InvariantState invariant() {
		int val = this.i;
		if (val % 2 != 0) {
			return new InvariantOK();
		} else {
			return new InvariantFailure(new Integer(val));
		}
	}

	public static void main(String[] args) {
		SynchronizedEvenGenerator sgen = new SynchronizedEvenGenerator();
		new InvariantWatcher(sgen, 4000);
		while (true)
			sgen.next();

	}

}
