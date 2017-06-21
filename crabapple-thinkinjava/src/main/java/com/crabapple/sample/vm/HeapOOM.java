package com.crabapple.sample.vm;

import java.util.ArrayList;
import java.util.List;

public class HeapOOM {
	static class OOMObject{
		private int index;
		private String value;
		public int getIndex() {
			return index;
		}
		public void setIndex(int index) {
			this.index = index;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
	}

	/**
	 * VM args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
	 * @param args
	 */
	public static void main(String[] args) {
		List<OOMObject> heamOOMList = new ArrayList<OOMObject>();

		while(true){
			heamOOMList.add(new OOMObject());
		}
	}

}
