package org.crabapple.binding.model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * Customer, just for test Web Service
 * @author Steve Yang, 2012-7-25 AM. 11:20:37
 */
@XmlRootElement
public class Customer {
	// 以下4个复杂属性,主要测试web service中对复杂类型的处理
	private String name;
	private Date birthday;
	private Calendar birthdayCalendar;
	private double height;
	private String[] sports;
	private List<Integer> inlist;
	private boolean isExist;
	private Address houseAddress;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public String[] getSports() {
		return sports;
	}
	public void setSports(String[] sports) {
		this.sports = sports;
	}
	public List<Integer> getList() {
		return inlist;
	}
	public void setList(List<Integer> inlist) {
		this.inlist = inlist;
	}
	public boolean isExist() {
		return isExist;
	}
	public void setExist(boolean isExist) {
		this.isExist = isExist;
	}
	public Address getHouseAddress() {
		return houseAddress;
	}
	public void setHouseAddress(Address houseAddress) {
		this.houseAddress = houseAddress;
	}
	public Calendar getBirthdayCalendar() {
		return birthdayCalendar;
	}
	public void setBirthdayCalendar(Calendar birthdayCalendar) {
		this.birthdayCalendar = birthdayCalendar;
	}
}
