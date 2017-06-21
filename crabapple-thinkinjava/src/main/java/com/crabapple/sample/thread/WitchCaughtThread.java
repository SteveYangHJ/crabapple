package com.crabapple.sample.thread;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 线程中的异常处理
 * 
 * @author SteveHJ
 */
public class WitchCaughtThread {

	public static void main(String args[]) {
		// 1, 通过Thread回调机制
		threadRun();

		// 2, 但是在线程池处理中,无法捕获
		threadRunByExecutorExecute();

		// 3, 需要封装到Runnable或Callable
		runnableByExecutorExecute();

		// 4, 通过submit提交的线程无法捕获异常,只能通过Future.get获取
		runnableByExecutorSubmit();
		runnableByExecutorSubmitwithFuture();
	}

	//
	public static void threadRun() {
		Thread thread = new Thread(new Task());
		thread.setUncaughtExceptionHandler(new ExceptionHandler());
		thread.start();
	}

	public static void threadRunByExecutorExecute() {
		ExecutorService exec = Executors.newCachedThreadPool();
		Thread thread = new Thread(new Task());
		thread.setUncaughtExceptionHandler(new ExceptionHandler());
		exec.execute(thread);
		exec.shutdown();
	}

	public static void runnableByExecutorExecute() {
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new TaskWithUncaughtExceptionHandler());
		exec.shutdown();
	}

	public static void runnableByExecutorSubmit() {
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new TaskWithUncaughtExceptionHandler());
		exec.shutdown();
	}

	public static void runnableByExecutorSubmitwithFuture() {
		ExecutorService exec = Executors.newCachedThreadPool();
		Future<?> future = exec.submit(new TaskWithUncaughtExceptionHandler());
		exec.shutdown();
		try {
			future.get();
		} catch (InterruptedException | ExecutionException e) {
			System.out.println("Future - Exception: " + e.getMessage());
		}
	}
}

class Task implements Runnable {
	@Override
	public void run() {
		System.out.println(3 / 2);
		System.out.println(3 / 0);
	}
}

class TaskWithUncaughtExceptionHandler implements Runnable {
	@Override
	public void run() {
		Thread.currentThread().setUncaughtExceptionHandler(
				new ExceptionHandler());
		System.out.println(3 / 2);
		System.out.println(3 / 0);
	}
}

class ExceptionHandler implements UncaughtExceptionHandler {
	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("Exception: " + e.getMessage());
	}
}
