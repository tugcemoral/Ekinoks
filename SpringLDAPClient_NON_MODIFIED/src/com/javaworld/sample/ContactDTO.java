package com.javaworld.sample;

public class ContactDTO {
	String commonName;
	String lastName;
	String description;
	public String getCommonName() {
		return commonName;
	}
	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String toString() {
		StringBuffer contactDTOStr = new StringBuffer("Person=[");
		contactDTOStr.append(" Common Name = " + commonName);
		contactDTOStr.append(", Last Name = " + lastName);
		contactDTOStr.append(", Description = " + description);
		contactDTOStr.append(" ]");
		return contactDTOStr.toString();
	}
	
	
	
}
