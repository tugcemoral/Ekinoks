package com.javaworld.sample;

import java.util.List;

public interface ContactDAO {

	public List<?> getAllContactNames();
	
	public List<?> getContactDetails(String commonName, String lastName);
	
	public void insertContact(Contact contactDTO);
	
	public void updateContact(Contact contactDTO);
	
	public void deleteContact(Contact contactDTO);
}
