//InvariantWatcher.java
// by Yang, 2011-4-14

package com.crabapple.thinkinjava.thread.TestFrame;

//Repeatedly checks to ensure invariant(不变量) is not violated(违反,侵犯)
public class InvariantWatcher extends Thread {
	private Invariant invariant;

	// 构造函数
	public InvariantWatcher(Invariant invariant) {
		this.invariant = invariant;
		this.setDaemon(true);
		this.start(); // 启动线程
	}

	// stop everything after awhile
	public InvariantWatcher(Invariant invariant, final int timeOut) {
		this(invariant);
		new TimeOut(timeOut, "Time out without violating invariant!");
	}

	//
	public void run() {
		while (true) {
			// 获取并测试当前的InvariantState
			InvariantState state = this.invariant.invariant();
			// 若有违例,打印违例信息
			if (state instanceof InvariantFailure) {
				System.out.println("Invariant violated:"
						+ ((InvariantFailure) state).value);
				System.exit(0);
			}
		}
	}
}
