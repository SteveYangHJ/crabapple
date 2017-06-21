package com.crabapple.sample.vm;

/**
 * 
 * SOF: JVM栈超出
 * 
 * @author SteveHJ
 *
 */
public class JavaVMStackSOF {
	
	private int stackLength = 1;
	public void stackLeak(){
		stackLength++;
		stackLeak();
	}

	/**
	 * VM args: -Xss128k
	 * @param args
	 */
	public static void main(String[] args) {
		JavaVMStackSOF oom = new JavaVMStackSOF();
		
		try{
			oom.stackLeak();
		}catch(Throwable t){
			System.out.println("stack length:" + oom.stackLength);
			throw t;
		}
		

	}

}
