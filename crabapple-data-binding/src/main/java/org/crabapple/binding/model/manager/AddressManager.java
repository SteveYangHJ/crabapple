package org.crabapple.binding.model.manager;

import java.util.*;

import org.crabapple.binding.model.Address;



/**
 * 1. 提供List的WebService业务
 * @author Steve Yang, 2012-2-22 下午01:57:26
 */
public class AddressManager {
	
	public List<Address> getAddressList(){
		List<Address> addressList = new ArrayList<Address>();
		Address add = new Address();
		add.setIdentifier(1);
		add.setAddress("海淀");
		add.setCity("北京");
		add.setProvince("北京");
		add.setCountry("中国");
		add.setPostCode("010000");
		addressList.add(add);
		
		Address add1 = new Address();
		add1.setIdentifier(1);
		add1.setAddress("ChaoYang");
		add1.setCity("北京");
		add1.setProvince("Beijing");
		add1.setCountry("中国");
		add1.setPostCode("010000");
		addressList.add(add1);

		return addressList;
	}
	
	// 该方法不发布
	public List<Address> setAddressList(List<Address> addressList){
		return addressList;
	}
	
	public HashMap<Integer,Address> getAddressMap(){
		HashMap<Integer,Address> map = new HashMap<Integer,Address>();
		Address add = new Address();
		add.setIdentifier(1);
		add.setAddress("海淀");
		add.setCity("北京");
		add.setProvince("北京");
		add.setCountry("中国");
		add.setPostCode("010000");
		map.put(add.getIdentifier(), add);
		
		Address add1 = new Address();
		add1.setIdentifier(2);
		add1.setAddress("ChaoYang");
		add1.setCity("北京");
		add1.setProvince("Beijing");
		add1.setCountry("中国");
		add1.setPostCode("010000");
		map.put(add1.getIdentifier(), add1);
		
		return map;
	}
	
	public HashMap<Integer,Address> setAddressMap(HashMap<Integer,Address> map){
		return map;
	}
}
