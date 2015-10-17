// by Yang 2011-4-14
package com.crabapple.thinkinjava.thread;

// 不正确的访问资源
public class AlwaysEven {
	private int i;

	public void next() {
		i++;
		i++;
	}

	public int getValue() {
		return i;
	}

	public static void main(String[] args) {
		// 必须被声明为final,因为他要被继承自Thread的匿名内部类访问
		final AlwaysEven ae = new AlwaysEven();

		// 定义匿名类
		new Thread("Watcher") {
			public void run() {
				while (true) {
					int val = ae.getValue();
					if (val % 2 != 0) {
						System.out.println(val);
						System.exit(0);
					}
				}
			}
		}.start();

		while (true)
			ae.next();
	}

}
