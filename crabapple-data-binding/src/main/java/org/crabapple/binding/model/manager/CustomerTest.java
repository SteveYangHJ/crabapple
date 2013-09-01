package org.crabapple.binding.model.manager;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.crabapple.binding.model.Address;
import org.crabapple.binding.model.Customer;


public class CustomerTest {

	public static Customer getCustomer(){
		Customer customer = new Customer();
		customer.setName("Steve Yang");
		customer.setSports(new String[]{"football","basketball"});
		customer.setExist(true);
		customer.setHouseAddress(new Address());
		return customer;
	}
	
	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		CustomerManager man  = new CustomerManager();
		Map<String,Customer> customerMap = man.getCustomerMap();
		System.out.println("customerMap" + customerMap);
		
		List<Customer> customerList = man.getCustomersList();
		System.out.println("customerList : " + customerList);
		Customer customer = man.getCustomerByKey("Jack");
		System.out.println("customer : " + customer);
		
	}

}
