package com.crabapple.sample.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CharCounter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String testStr = "ASADKLS34asdasdsadsdsadasdasdajsdlksdfdsfdASASJLSJljsASLJaljdasldjassdfdsuiofjeiorrASKLAKLDJLJFFhsldjfds" + 
				"sdasdsa243ASKAS:kasdasdwpdmewdmfl;erkeksdkasl;dkqwopkwqdkodcskal;dKZLD-sajdlksjdslfjdsAUOIWUEIOWYEOWYRIORYWIORY" + 
				"OryiI;sdfdsfdsfjlgkdfkgjdfklgsdferpieptASKADJDIsdfASJAJSAJDPQKPMLCMNCBVJVBZBXMCBWSHDSJKDPOIPOEIEPJOJWPOJEHICHSH" + 
				"JASKSdjusopfuwefjewfjewiofsdhashdhaskdhwedhewuiryewuiouf2349-4583490584058459a8u2893123982392-392-5849058490584" +
				"9584593480203932049324932-4932-49345690895jfjsdlfkjewasmwwwmwomendoushi gogchanfzhuyijiebanrenmeiyoasdsdhjkjdsj" +
				"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaafhdfhdskfhdoefwoehfoewuwwowomedelixiangyurognsufsfhsdu" +
				"ifdshfhsfasdjalsdjasldjasldjasldalsadsdsadsdsadsdasdwerwrewrewrdfdddgdfgrtryrtytrytrytryhgfhgfhtr";
		long startTime = System.currentTimeMillis();
		showMaxCountofChar(testStr);
		long endTime = System.currentTimeMillis();
		System.out.println("Time:" + (endTime - startTime));
		
		startTime = System.currentTimeMillis();
		showMaxCountofChar1(testStr);
		endTime = System.currentTimeMillis();
		System.out.println("Time:" + (endTime - startTime));
		
		startTime = System.currentTimeMillis();
		showMaxCountofChar2(testStr);
		endTime = System.currentTimeMillis();
		System.out.println("Time:" + (endTime - startTime));
		
		startTime = System.currentTimeMillis();
		showMaxCountofChar2(testStr);
		endTime = System.currentTimeMillis();
		System.out.println("Time:" + (endTime - startTime));
		
		compareChar();
	}
	
	public static void compareChar(){
		System.out.println('a' == 'a');
		System.out.println('a' < 'b');
		System.out.println('a' > 'A');
		
		char[] ster = new char[5];
		System.out.println("ster[0]=" + ster[0] + ";");
	}
	
	public static void showMaxCountofChar(String testStr){
		Map<String, Integer> charCounter = new HashMap<String, Integer>();
		char[] charArray = testStr.toCharArray();
		for(int i = 0; i < charArray.length; i++){
			String key = String.valueOf(charArray[i]);
			if(charCounter.containsKey(key)){
				charCounter.put(key, Integer.valueOf(charCounter.get(key) + 1));
			}else{
				charCounter.put(key, Integer.valueOf(1));
			}
		}
		Set<String> keySet = charCounter.keySet();
		String maxKey = keySet.toArray(new String[0])[0];
		for(String key : keySet){
			if(charCounter.get(maxKey) < charCounter.get(key)){
				maxKey = key;
			}
		}
		
		System.out.println("count(" + maxKey + ")=" + charCounter.get(maxKey));
	}
	
	public static void showMaxCountofChar1(String testStr){
		char[] charArray = testStr.toCharArray();
		char[] charArrayforCount = new char[charArray.length];
		int[] charCountArray = new int[charArrayforCount.length];
		
		for(int i = 0; i < charArray.length; i++){
			char c = charArray[i];
			int count = 0;
			for(int j = 0; j < charArray.length; j++){
				if(c == charArray[j]){
					count += 1;
				}
			}
			charArrayforCount[i] = charArray[i];
			charCountArray[i] = count;
		}
		
		// 
		char max = charArrayforCount[0];
		int maxCount = charCountArray[0];
		for(int i = 0; i < charArrayforCount.length; i++){
			if(maxCount < charCountArray[i]){
				max = charArrayforCount[i];
				maxCount = charCountArray[i];
			}
		}
		
		System.out.println("count(" + max + ")=" + maxCount);
	}

	public static void showMaxCountofChar2(String testStr){
		int[] counts = new int[testStr.length()];
		for(int i = 0; i < testStr.length(); i++){
			char c = testStr.charAt(i);
			for(int j = 0; j < testStr.length(); j++){
				if(c == testStr.charAt(j)){
					counts[i] += 1;
				}
			}
		}
		
		// get the index of maxcount char
		int maxCount = counts[0];
		int maxIndex = 0;
		for(int i = 0; i < counts.length; i++){
			if(maxCount < counts[i]){
				maxCount = counts[i]; 
				maxIndex = i;
			}
		}
		
		System.out.println("count(" + testStr.charAt(maxIndex) + ")=" + maxCount);
	}
	
	public static void showMaxCountofChar3(String string) {
        //ArrayList: save the chars
        ArrayList<Character> list = new ArrayList<Character>(string.length());
        for(int i = 0; i < string.length(); i++){
            // If the char is not in list, save it
            if(!list.contains(string.charAt(i))){
                list.add(string.charAt(i));
            }
        } 
        
        int[] count = new int[list.size()];
        for(int i=0;i<list.size();i++){
            char mid = list.get(i);
            for(int j=0;j<string.length();j++){
                if(mid==string.charAt(j)){
                    count[i]++;
                }
            }
        }
        int index = 0;
        int max = count[0];
        for (int i = 0; i < count.length; i++) {
            if (max < count[i]) {
                max = count[i];
                index = i;
            }
        }
        System.out.println("字符串中出现的字符：");
        for(int k=0;k<list.size();k++){
            System.out.print(list.get(k)+" ");
        }
        System.out.println();
        System.out.println("字符" + list.get(index)+"出现的次数最多");
        System.out.println("共出现次数为： " + count[index]);
    }
	
	/*
	 * CSDN shine333 提供，堪称经典(至少我没想到根据char去找对应的count值）
	 */
   public static String printmaxcountcharASII(String str){
	   int[] count = new int[128];
	    for (int i = str.length(); i-- > 0; count[str.charAt(i)]++);
	    int maxIndex = 0;
	    for (int i = 1; i < count.length; i++) {
	      if (count[i] > count[maxIndex]) {
	        maxIndex = i;
	      }
	    }
	   return (char)maxIndex+":"+count[maxIndex];

   }
    /*
     * CSDN a276202460 菜鸟提供 
     * 缺点排序浪费时间,和串长度成正比
     */
   public static String printmaxcountcharArray(String str){
	   char[] chars = str.toCharArray();
       Arrays.sort(chars);
       int max = 0;
       int count = 0;
       int maxchar = 0;
       int oldchar = 0 ;
       for(int i = 0 ;i <= chars.length;i++){
           if(i == 0 || i == chars.length || oldchar != chars[i]  ){
                max = max > count?max:count;
                maxchar = max > count?maxchar:oldchar;
                if(i == chars.length){
                    break;
                }
                oldchar = chars[i];
                count = 0;
             }
           count++;
           
       }
       return (char)maxchar+":"+max;

   }
}
