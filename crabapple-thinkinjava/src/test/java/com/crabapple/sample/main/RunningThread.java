package com.crabapple.sample.main;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RunningThread {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Thread thread = new Thread(new Runnable(){

			@Override
			public void run() {
				int start = 0;
				Map<String, String> map = new HashMap<String,String>();
				while(true){
					String key = Thread.currentThread().getId() + "-" + start++;
					Random random = new Random();
					String value = Thread.currentThread().getId() + "-" + Math.abs(random.nextLong());
					map.put(key, value);
					System.out.println(String.format("{key,value}={%s,%s}", key,value));
					try {
						Thread.sleep(500); // just sleep 500ms
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		thread.start();
	}
}
