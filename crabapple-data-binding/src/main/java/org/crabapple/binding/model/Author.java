package org.crabapple.binding.model;

import javax.xml.bind.annotation.XmlElement;


public class Author{
	private String firstName;
	private String lastName;
	
	public Author(){}
	public Author(String firstName, String lastName){
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	// Getter/Setter
	@XmlElement(name="firstName1")
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@XmlElement(name="lastName1")
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
