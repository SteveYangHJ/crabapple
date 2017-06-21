package com.crabapple.sample.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * 线程中的异常处理: 通过外部类捕获
 * @author SteveHJ
 *
 */
public class InitiativeCaught {

	public void threadDeal(Runnable r, Throwable t) {
		System.out.println("Exception: " + t.getMessage());
	}

	class InitialtiveThread implements Runnable {
		@Override
		public void run() {
			Throwable thrown = null;
			try {
				System.out.println(3 / 2);
				System.out.println(3 / 0);
				System.out.println(3 / 1);
			} catch (Throwable e) {
				thrown = e;
			} finally {
				threadDeal(this, thrown);
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(new InitiativeCaught().new InitialtiveThread());
		executorService.shutdown();
	}

}
