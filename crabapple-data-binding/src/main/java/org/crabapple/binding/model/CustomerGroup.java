package org.crabapple.binding.model;


/**
 * 实体类CustomerGroup
 * @author Steve Yang, 2012-8-9 AM 11:15:14
 */
public class CustomerGroup {
	private Customer[] customers;
	private String groupName;
	private String groupType;
	//getter/setter
	public Customer[] getCustomers() {
		return customers;
	}
	public void setCustomers(Customer[] customers) {
		this.customers = customers;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGroupType() {
		return groupType;
	}
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
}
