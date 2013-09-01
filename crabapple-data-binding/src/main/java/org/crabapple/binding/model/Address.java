package org.crabapple.binding.model;

import java.io.Serializable;


/**
 * 1. 服务器端的复杂类型
 * 2. 要传递的对象必须是可序列化的
 * @author Steve Yang, 2012-2-22 PM. 01:55:34
 */
public class Address implements Serializable {
	private static final long serialVersionUID = 7440856487744623788L;

	private Integer identifier;
	// 地址
	private String address;
	// 城市
	private String city;
	// 省份
	private String province;
	// 国家
	private String country;
	// 邮编
	private String postCode;
	
	// Constructor 
	public Address(){}
	public Address(Integer identifier, String address, String city, String province, String country, String postCode){
		this.identifier = identifier;
		this.address = address;
		this.city = city;
		this.province = province;
		this.country = country;
		this.postCode = postCode;
	}
	
	public Integer getIdentifier() {
		return identifier;
	}
	public void setIdentifier(Integer identifier) {
		this.identifier = identifier;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
}
