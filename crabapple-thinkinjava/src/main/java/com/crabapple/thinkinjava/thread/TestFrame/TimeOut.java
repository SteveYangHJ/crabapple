//by Yang, 2011-4-14

package com.crabapple.thinkinjava.thread.TestFrame;

import java.util.*;

// set a time limit on the execution of a program
// Timer类,一种工具,线程用其安排以后在后台线程中执行的任务
// 可安排任务执行一次,或定期重复执行
public class TimeOut extends Timer {
	// delay为延迟的毫秒数
	public TimeOut(int delay, final String msg) {
		super(true); // 通过super(true),创建Daemon Thread

		// 继承TimerTask抽象类的内部匿名类
		// 安排在指定延迟后执行指定的任务
		schedule(new TimerTask() {
			public void run() // 必须实现
			{
				System.out.println(msg);
				System.exit(0);
			}
		}, delay);
	}
}
