package com.javaworld.sample;

import java.util.List;

public interface ContactDAO {

	public List getAllContactNames();
	
	public List getContactDetails(String commonName, String lastName);
	
	public void insertContact(ContactDTO contactDTO);
	
	public void updateContact(ContactDTO contactDTO);
	
	public void deleteContact(ContactDTO contactDTO);
}
