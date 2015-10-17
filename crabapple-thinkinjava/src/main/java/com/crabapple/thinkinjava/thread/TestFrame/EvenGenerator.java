// AlwaysEven.java using the invariant tester
package com.crabapple.thinkinjava.thread.TestFrame;

public class EvenGenerator implements Invariant {
	private int i;

	public void next() {
		i++;
		i++;
	}

	public int getValue() {
		return i;
	}

	// 实现方法
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
		EvenGenerator gen = new EvenGenerator();
		new InvariantWatcher(gen);
		while (true)
			gen.next();
	}
}
