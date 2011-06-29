package com.javaworld.sample;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;

import org.springframework.ldap.core.AttributesMapper;

public class ContactAttributeMapper implements AttributesMapper{

	public Object mapFromAttributes(Attributes attributes) throws NamingException {
		Contact contactDTO = new Contact();
		String commonName = (String)attributes.get("cn").get();
		if(commonName != null)
			contactDTO.setCommonName(commonName);
		String lastName = (String)attributes.get("sn").get();
		if(lastName != null)
		contactDTO.setLastName(lastName);
		Attribute description = attributes.get("description");
		if(description != null)
			contactDTO.setDescription((String)description.get());
		return contactDTO;
	}

}
