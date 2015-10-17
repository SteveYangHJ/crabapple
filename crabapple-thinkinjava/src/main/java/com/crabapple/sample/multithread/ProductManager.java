package com.crabapple.sample.multithread;

// write 2011-4-13, by yang
// 生产者与消费者模型中，要保证以下几点：
// 1 同一时间内只能有一个生产者生产
// 2 同一时间内只能有一个消费者消费
// 3 生产者生产的同时消费者不能消费
// 4 消息队列满时生产者不能继续生产
// 5 消息队列空时消费者不能继续消费

import java.util.*;

public class ProductManager {
	// main函数
	public static void main(String[] args) {
		Products pros = new Products();
		Producer producer = new Producer(pros);
		Producer producer1 = new Producer(pros);
		Consumer consumer = new Consumer(pros);
		producer.start();
		producer1.start();
		consumer.start();
	}
}

// 产品列表缓冲区类
class Products {
	// 缓冲区即产品列表
	private List<Products> productList = new ArrayList<Products>();
	// 缓冲区大小
	private int count;

	// 构造函数
	public Products() {
		// 默认缓冲区大小
		this.setCount(10);
	}

	public Products(List<Products> list, int count) {
		this.productList = list;
		this.setCount(count);
	}

	// getter/setter方法
	public void setCount(int count) {
		this.count = count;
	}

	// 使用产品,从产品列表中减少一个
	public synchronized void use() {
		// 如果产品列表中含有产品,则去掉第一个产品
		while (this.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		notifyAll();
		this.productList.remove(0);
	}

	// 向产品列表中添加一个产品
	public synchronized void add(Products product) {
		while (this.productList == null || this.isFull()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		notifyAll();
		this.productList.add(product);
	}

	// 判断缓冲区是否已满
	public boolean isFull() {
		return this.count < this.productList.size();
	}

	// 判断缓冲区是否为空,即无产品,
	public boolean isEmpty() {
		return !(this.productList.size() > 0);
	}

	// 获取产品数量
	public int getRealCount() {
		return this.productList.size();
	}
}

// 生产者类
class Producer extends Thread {
	private Products products;

	// 构造函数
	public Producer(Products pros) {
		this.products = pros;
	}

	@Override
	public void run() {
		this.produce();
	}

	// 生产一个产品
	private void produce() {
		Products product = new Products();
		for (int i = 0; i < 10; i++) {
			try {
				// 等待一定时间
				Thread.sleep((int) Math.random() * 3000);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// 如果缓冲区不满,则生产
			this.products.add(product);
			System.out.println("生产者生产了一个产品,此时产品数为:"
					+ this.products.getRealCount());
		}
	}
}

// 消费者类
class Consumer extends Thread {
	private Products products;

	// 构造函数
	public Consumer(Products products) {
		this.products = products;
	}

	//
	@Override
	public void run() {
		this.consume();
	}

	// 消费者消费一个产品
	private void consume() {
		for (int i = 0; i < 10; i++) {
			try {
				// 等待一定时间
				Thread.sleep((int) Math.random() * 3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// 如果缓冲区不空,则消费
			this.products.use();
			System.out.println("消费者消费了一个产品,此时产品数为:"
					+ this.products.getRealCount());
		}
	}
}
