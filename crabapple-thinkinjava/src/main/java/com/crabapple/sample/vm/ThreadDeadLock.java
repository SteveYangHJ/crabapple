package com.crabapple.sample.vm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ThreadDeadLock {
	
	public static void createBusyThread(){
		Thread thread = new Thread(new Runnable(){
			
			public void run(){
				while(true)
					;
			}
		}, "testBusyThread");
		thread.start();
	}
	
	public static void createLockThread(final Object lock){
		Thread thread = new Thread(new Runnable(){
			public void run(){
				synchronized(lock){
					try{
						lock.wait();
					}catch(InterruptedException e){
						e.printStackTrace();
					}
				}
			}
		}, "testLockThread");
		thread.start();
	}
	
	public static void lock() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br.readLine();
		createBusyThread();
		br.readLine();
		Object obj = new Object();
		createLockThread(obj);
	}
	
	// 线程死锁等待演示
	static class SyncAddRunnable implements Runnable{
		int a, b;
		
		public SyncAddRunnable(int a, int b){
			this.a = a;
			this.b = b;
		}

		@Override
		public void run() {
			synchronized(Integer.valueOf(a)){
				synchronized(Integer.valueOf(b)){
					System.out.println( a + b);
				}
			}
		}
	}
	
	/**
	 * 死锁原因: Integer.valueOf()方法基于减少对象创建次数和节省内存的考虑,[-128,127]之间的数字会被缓存;
	 * 当valueOf()方法在这个范围之内传入参数,将直接返回缓存中的对象. 即代码中调用了200次Integer.valueOf()
	 * 方法,一共就返回了两个不同的对象
	 */
	public static void testDeadLock(){
		for(int i=0; i < 100 ;i++){
			new Thread(new SyncAddRunnable(1,2)).start();
			new Thread(new SyncAddRunnable(2,1)).start();
		}
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		testDeadLock();
	}

}
