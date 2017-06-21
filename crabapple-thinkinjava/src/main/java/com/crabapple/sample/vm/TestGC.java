package com.crabapple.sample.vm;

public class TestGC {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new TestGC();
		System.gc();
		System.runFinalization();
	}

}
