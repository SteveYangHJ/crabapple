package com.crabapple.sample.collection;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import sun.misc.Hashing;



public class MapSample {
	private static Logger logger = Logger.getLogger(MapSample.class);

	public static void main(String[] args) {
		testPersonByHash();
	}

	// 4, Test Hashcode() in Object
	static class Person{
		private String name;
		Person(String name){
			this.name = name;
		}
		public int hashCode(){
			return 420;
		}
	}
	
	static enum Type{
		MAN,WOMAN;
	}
	
	/*
	 All Person Object have the same hashcode, when save several persons into HashSet or HashMap,
	 What will happen? 
	 Question: 
	 	1, 
	 */
	public static void testPersonByHash(){
		Person person1 = new Person("Person1");
		Person person2 = new Person("Person2");
		Person person3 = new Person("Person3");
		// Test HashMap
		Map<Person,String> personMap = new HashMap<Person,String>();
		System.out.println(personMap.put(person1, "person1"));
		personMap.put(person2, "person2");
		
		System.out.println(personMap.containsKey(person1));
		System.out.println(personMap.containsKey(person2));
		System.out.println(personMap.size());
		
		// Test HashSet
		Set<Person> personSet = new HashSet<Person>();
		personSet.add(person1);
		personSet.add(person2);
		System.out.println(personSet.size());
		
		// Test UserHashMap
		Map<Person,String> personUserMap = new UserHashMap<Person,String>();
		System.out.println("personUserMap.put(person1, \"person1\") = " + personUserMap.put(person1, "person1"));
		System.out.println("personUserMap.put(person1, \"person2\") = " + personUserMap.put(person2, "person2"));
		System.out.println("personUserMap.put(person1, \"person3\") = " + personUserMap.put(person3, "person3"));
		
		System.out.println(personUserMap.containsKey(person1));
		System.out.println(personUserMap.containsKey(person2));
		System.out.println(personUserMap.size());
		System.out.println("hash(person1) = " + hash(person1));
		System.out.println("hash(person2) = " + hash(person2));
		//System.out.println("indexFor(hash(person1), table.length) = " + indexFor(hash(person1), ));
		//System.out.println("indexFor(hash(person1), table.length) = " + hash(person2));
		
		// Test UserHashMap
		Map<Person,String> personUserMap16 = new UserHashMap16<Person,String>();
		System.out.println("personUserMap.put(person1, \"person1\") = " + personUserMap16.put(person1, "person1"));
		System.out.println("personUserMap.put(person1, \"person2\") = " + personUserMap16.put(person2, "person2"));
		System.out.println("personUserMap.put(person1, \"person3\") = " + personUserMap16.put(person3, "person3"));
		
		System.out.println(personUserMap16.containsKey(person1));
		System.out.println(personUserMap16.containsKey(person2));
		System.out.println(personUserMap16.size());
		System.out.println("hash(person1) = " + hash(person1));
		System.out.println("hash(person2) = " + hash(person2));
	}
	
	public static void testConcurrentHashMap(){
		Map<String, Person> personMap = new ConcurrentHashMap<String, Person>();
		Map<String, Person> personMap1 = new IdentityHashMap<String, Person>();
//		Map<String, Person> personMap2 = new EnumMap<String, Person>();
		personMap.put("person1", new Person("Person1"));
	}
	
	// method from HashMap
	 final static int hash(Object k) {
        int h = 0;
        if (0 != h && k instanceof String) {
            return Hashing.stringHash32((String) k);
        }

        h ^= k.hashCode();

        // This function ensures that hashCodes that differ only by
        // constant multiples at each bit position have a bounded
        // number of collisions (approximately 8 at default load factor).
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }
	 /**
     * Returns index for hash code h.
     */
    static int indexFor(int h, int length) {
        // assert Integer.bitCount(length) == 1 : "length must be a non-zero power of 2";
        return h & (length-1);
    }
}
