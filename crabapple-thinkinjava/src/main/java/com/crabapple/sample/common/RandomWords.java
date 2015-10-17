package com.crabapple.sample.common;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Random;
import java.util.Scanner;

/**
 * <P>Description: implementing a interface to conform a method</P>
 * <p>Copy from Thinking in java 4td Edition, Chapter Nine,9.6</p>
 * @author Steve Yang,2011-11-24 
 */
public class RandomWords implements Readable {

	private static Random rand = new Random(47);
	private static final char[] capitals = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	private static final char[] lowers = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	private static final char[] vowels="asdsd".toCharArray();
	private int count;
	
	public RandomWords(int count){
		this.count = count;
	}
	
	/* (non-Javadoc)
	 * @author Steve Yang
	 * @see java.lang.Readable#read(java.nio.CharBuffer)
	 */
	@Override
	public int read(CharBuffer cb) throws IOException {
		if(count--==0){
			return -1;
		}else{
			cb.append(capitals[rand.nextInt(capitals.length)]);
			for(int i=0;i<4;i++){
				cb.append(lowers[rand.nextInt(lowers.length)]);
				cb.append(vowels[rand.nextInt(vowels.length)]);
			}
			cb.append(' ');
			return 10;  // 返回追加的字符个数
		}
	}

	public static void main(String[] args){
		Scanner scan = new Scanner(new RandomWords(10));
		while(scan.hasNext()){
			System.out.println(scan.next());
		}
	}
}

