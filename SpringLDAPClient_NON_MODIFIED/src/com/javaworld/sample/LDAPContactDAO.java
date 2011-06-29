package com.javaworld.sample;

import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;

import org.springframework.ldap.AttributesMapper;
import org.springframework.ldap.LdapTemplate;
import org.springframework.ldap.support.DistinguishedName;
import org.springframework.ldap.support.filter.AndFilter;
import org.springframework.ldap.support.filter.EqualsFilter;

public class LDAPContactDAO implements ContactDAO{
	private LdapTemplate ldapTemplate;
	
	public void setLdapTemplate(LdapTemplate ldapTemplate) {
		this.ldapTemplate = ldapTemplate;
	}

	public List getAllContactNames() {
		return ldapTemplate.search("", "(objectclass=person)",
				new AttributesMapper() {
					public Object mapFromAttributes(Attributes attrs)
							throws NamingException {
						return attrs.get("cn").get();
					}
				});
	}

	public List getContactDetails(String commonName,String lastName){
		AndFilter andFilter = new AndFilter();
		andFilter.and(new EqualsFilter("objectclass","person"));
		andFilter.and(new EqualsFilter("cn",commonName));
		andFilter.and(new EqualsFilter("sn",lastName));
		System.out.println("LDAP Query " + andFilter.encode());
		return ldapTemplate.search("", andFilter.encode(),new ContactAttributeMapper());
		
	}
	
	public void deleteContact(ContactDTO contactDTO) {
		DistinguishedName newContactDN = new DistinguishedName("ou=users");
		newContactDN.add("cn", contactDTO.getCommonName());
		
		ldapTemplate.unbind(newContactDN);
	}

	public void insertContact(ContactDTO contactDTO) {

		Attributes personAttributes = new BasicAttributes();
		BasicAttribute personBasicAttribute = new BasicAttribute("objectclass");
		personBasicAttribute.add("person");
		personAttributes.put(personBasicAttribute);
		personAttributes.put("cn", contactDTO.getCommonName());
		personAttributes.put("sn", contactDTO.getLastName());
		personAttributes.put("description", contactDTO.getDescription());
	
		DistinguishedName newContactDN = new DistinguishedName("ou=users");
		newContactDN.add("cn", contactDTO.getCommonName());
		
		ldapTemplate.bind(newContactDN, null, personAttributes);
	}

	public void updateContact(ContactDTO contactDTO) {
		Attributes personAttributes = new BasicAttributes();
		BasicAttribute personBasicAttribute = new BasicAttribute("objectclass");
		personBasicAttribute.add("person");
		personAttributes.put(personBasicAttribute);
		personAttributes.put("cn", contactDTO.getCommonName());
		personAttributes.put("sn", contactDTO.getLastName());
		personAttributes.put("description", contactDTO.getDescription());
	
		DistinguishedName newContactDN = new DistinguishedName("ou=users");
		newContactDN.add("cn", contactDTO.getCommonName());
		
		ldapTemplate.rebind(newContactDN, null, personAttributes);
		
		
	}
}
