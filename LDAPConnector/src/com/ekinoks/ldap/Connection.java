package com.ekinoks.ldap;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.NameAlreadyBoundException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

public class Connection {
	
	private static final String LDAP_SERVER_NAME = "ldap://192.168.2.61/";
	
	private static final String rootdn = "cn=Manager,dc=tugcem.ekinoks,dc=com";
	private static final String rootpass = "doodle";
	private static final String rootContext = "o=tugcem";

	public static void main(String[] args) {
		// set up environment to access the server

		Properties env = new Properties();

		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, LDAP_SERVER_NAME+ rootContext);
		env.setProperty(Context.URL_PKG_PREFIXES, "com.sun.jndi.url");
		
		env.setProperty(Context.REFERRAL, "ignore");
		env.setProperty(Context.SECURITY_AUTHENTICATION, "simple");
		
		//specify username and password.
		env.put(Context.SECURITY_PRINCIPAL, rootdn);
		env.put(Context.SECURITY_CREDENTIALS, rootpass);

		try {
			//--------------------------------------------------
			// Get the environment properties (props) for creating initial
			// context and specifying LDAP service provider parameters.
			//--------------------------------------------------
			// obtain initial directory context using the environment
			DirContext ctx = new InitialDirContext(env);
			
			System.out.println(ctx.getNameInNamespace());
			
//			Attributes attributes = ctx.getAttributes("cn=Manager");
//			
//			System.out.println(attributes.toString());
			
			// now, create the root context, which is just a subcontext
			// of this initial directory context.
//			ctx.createSubcontext(rootContext);
		} catch (NameAlreadyBoundException nabe) {
			System.err.println(rootContext + " has already been bound!");
		} catch (Exception e) {
			System.err.println(e);
		}
	}

}
