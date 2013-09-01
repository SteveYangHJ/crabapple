package org.crabapple.binding.model.manager;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.crabapple.binding.model.Customer;



/**
 * CustomerManager, provide List and Map for web service
 * @author Steve Yang, 2012-7-25 PM. 13:31:37
 */
public class CustomerManager {
	/**
	 * @return
	 * @throws ParseException
	 */
	public List<Customer> getCustomersList() throws ParseException{
		return new ModelInitializer().getCustomersList();
	}
	
	/**
	 * @return
	 * @throws ParseException
	 */
	public HashMap<String,Customer> getCustomerMap() throws ParseException{	
		return new ModelInitializer().getCustomerMap();
	}
	/**
	 * @param map
	 * @return
	 */
	public HashMap<String,Customer> setAddressMap(@WebParam(name="customerList") List<Customer> customerList){
		return new ModelInitializer().constructMapByList(customerList);
	}
	
	/**
	 * get the customer by the key from the cusomter Map
	 * @param key
	 * @return
	 * @throws ParseException
	 */
	public Customer getCustomerByKey(String key) throws ParseException{
		Map<String,Customer> customerMap = new ModelInitializer().getCustomerMap();
		Customer customer = null;
		if(customerMap.containsKey(key)){
			customer = customerMap.get(key);
		}
		return customer;
	}
	
	/**
	 * @param customer
	 * @return
	 */
	public Customer changeCustomer(Customer customer){
		customer.setName("Steve");
		customer.setHeight(23243.08d);
		if(null == customer.getList()){
			customer.setList(new ArrayList<Integer>());
		}
		customer.getList().add(new Integer(1232));
		return customer;
	}
	
	/**
	 * Add a Customer into Customer Array
	 * @param customer
	 * @return
	 * @throws ParseException
	 */
	public Customer[] addToCustomerArray(Customer customer) throws ParseException{
		List<Customer> customerList = new ModelInitializer().getCustomersList();
		if(null == customerList){
			customerList = new ArrayList<Customer>();
		}
		customerList.add(customer);
		System.out.println("customer Name =" + customer.getName());
		System.out.println("List Size = " + customerList.size());
		return customerList.toArray(new Customer[customerList.size()]);
	}
}
