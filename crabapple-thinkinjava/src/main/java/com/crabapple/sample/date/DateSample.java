package com.crabapple.sample.date;

import java.util.*;

import org.apache.log4j.Logger;

public class DateSample {
	private static Logger logger = Logger.getLogger(DateSample.class);

	public static void main(String[] args) {
		Date date = new Date();
		Deque dequeue = new ArrayDeque();
		Queue queue = new PriorityQueue();
		List list = new LinkedList();
		Set set = new TreeSet();
	}

}
