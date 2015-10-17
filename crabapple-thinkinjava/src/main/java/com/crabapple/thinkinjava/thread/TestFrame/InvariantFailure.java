package com.crabapple.thinkinjava.thread.TestFrame;

public class InvariantFailure implements InvariantState {
	// 表示了有关是失败的信息
	public Object value;

	public InvariantFailure(Object value) {
		this.value = value;
	}
}// /:~
