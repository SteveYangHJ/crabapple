package org.crabapple.binding.model.manager;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.crabapple.binding.model.Address;
import org.crabapple.binding.model.Article;
import org.crabapple.binding.model.Author;
import org.crabapple.binding.model.Customer;


/**
 * CustomerUtil,
 * @author Steve Yang, 2012-7-25 AM. 11:20:37
 */
public class ModelInitializer {
	private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd hh:mm:ss Z";
	
	/**
	 * Get List
	 * @return
	 * @throws ParseException
	 */
	public static List<Customer> getCustomersList() throws ParseException{
		List<Customer> customerList = new ArrayList<Customer>();
		// First Customer
		Customer customer = new Customer();
		customer.setName("Jack");
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse("1990-03-22 12:23:23 +0800"));	
		customer.setBirthdayCalendar(cal);
//		customer.setBirthday(sdf.parse("1990-03-22 12:23:23 +0800"));
		customer.setBirthday(cal.getTime());
		customer.setExist(true);
		customer.setHeight(178.20d);
		customer.setSports(new String[]{"football","basketball","USA","China"});
		customer.setHouseAddress(new Address(1,"海淀","北京市","北京","中国","010000"));
		List<Integer> inlist = new ArrayList<Integer>();
		inlist.add(new Integer(2));
		inlist.add(new Integer(5));
		customer.setList(inlist);
		
		// Second Customer
		Customer customer2 = new Customer();
		customer2.setName("Nash");
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(sdf.parse("1993-03-22 12:23:23 +0800"));
		customer2.setBirthdayCalendar(cal2);
//		customer2.setBirthday(sdf.parse("1993-03-22 12:23:23 +0800"));
		customer.setBirthday(cal.getTime());
		customer2.setExist(false);
		customer2.setHeight(184.20d);
		customer2.setSports(new String[]{"Football","Basketball","Tennis","Badminton"});
		customer2.setHouseAddress(new Address(2,"滨海新区","天津市","天津市","中国","032000"));
		
		// Add to List
		customerList.add(customer);
		customerList.add(customer2);
		return customerList;
	}

	/**
	 * Get Map
	 * @return
	 * @throws ParseException
	 */
	public static HashMap<String,Customer> getCustomerMap() throws ParseException{	
		// Get the Customer List
		List<Customer> customerList = getCustomersList();	
		return constructMapByList(customerList);
	}
	
	/**
	 * @param map
	 * @return
	 */
	public static HashMap<String,Customer> setAddressMap(HashMap<String,Customer> map){
		return map;
	}
	
	/**
	 * Create Map by List for the Customers
	 * @param map
	 * @return
	 */
	public static HashMap<String,Customer> constructMapByList(List<Customer> customerList){
		HashMap<String,Customer> customerMap = null;
		if(null != customerList && customerList.size()>0){
			customerMap = new HashMap<String,Customer>(customerList.size());
			for(Customer customer:customerList){
				customerMap.put(customer.getName(), customer);
			}
		}
		return customerMap;
	}
	
	public static Article initializeArticle(){
		Article article = new Article();
		article.setAuthor(new Author("Steve","King"));
		article.setTitle("The Stand");
		article.setBody("Body");
		article.setId(1);
		article.setSummary("This is the way the world ends: with a nanosecond of computer error in a Defense Department laboratory and a million casual contacts that form the links in a chain letter of death. \r\n And here is the bleak new world of the day after: a world stripped of its institutions and emptied of 99 percent of its people. A world in which a handful of panicky survivors choose sides ");
		article.setPublishDate(new Date());
		article.setComments(new String[]{"You know what’s really scary? Getting sick while you’re reading the first part of The Stand",
				"Whatever I say below, I still have non-gut-wrenching memories of reading this"});
		return article;
	}
}
