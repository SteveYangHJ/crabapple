package org.crabapple.binding.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="customers")
public class CustomerList {
	private int count;
	private List<Customer> customers;
	
	// Getter/Setter
	public void setCount(int count) {
		this.count = count;
	}
	public int getCount() {
		return count;
	}
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	public List<Customer> getCustomers() {
		return customers;
	}
}
