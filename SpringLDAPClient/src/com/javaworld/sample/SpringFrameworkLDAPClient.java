package com.javaworld.sample;

import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;

public class SpringFrameworkLDAPClient {

	public static void main(String[] args) {
		try {
			Resource resource = new ClassPathResource(
					"com/javaworld/sample/springldap.xml");
			BeanFactory factory = new XmlBeanFactory(resource);
			ContactDAO ldapContact = (LDAPContactDAO) factory
					.getBean("ldapContact");
			
//			ldapContact.insertContact(new Contact("Tugcem", "Oral", "Desc: Admin"));
			
			List<?> allContactNames = ldapContact.getAllContactNames();
			
			for (Object object : allContactNames) {
				System.out.println("Contact: "+ object);
			}
			
//			List<?> contactList = ldapContact.getContactDetails("TEIEN",
//					"Oral");
//			for (int i = 0; i < contactList.size(); i++) {
//				System.out.println("Contact Name " + contactList.get(i));
//			}
//			Contact newContactDTO = new Contact();
//			newContactDTO.setCommonName("Rahul");
//			newContactDTO.setLastName("Dravid");
//			newContactDTO.setDescription("Former Indian opening batsman");
//			ldapContact.updateContact(newContactDTO);
		} catch (DataAccessException e) {
			System.out.println("Error occured " + e.getCause());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
