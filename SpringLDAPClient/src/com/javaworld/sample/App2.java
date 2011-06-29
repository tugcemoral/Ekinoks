package com.javaworld.sample;

import static javax.naming.directory.SearchControls.SUBTREE_SCOPE;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import com.sun.jndi.ldap.LdapCtxFactory;

class App2 {

	public static void main(String[] args) {

		// if (args.length != 4 && args.length != 2) {
		// System.out
		// .println("Purpose: authenticate user against Active Directory and list group membership.");
		// System.out
		// .println("Usage: App2 <username> <password> <domain> <server>");
		// System.out.println("Short usage: App2 <username> <password>");
		// System.out
		// .println("(short usage assumes 'xyz.tld' as domain and 'abc' as server)");
		// System.exit(1);
		// }

		String username = "TEIEN2";
		String password = "Tt12345";

		String domainName = "cevreorman";
		String serverName = "cevreorman.gov.tr";

		System.out.println("Authenticating " + username + "@" + domainName
				+ " through ldap://" + serverName + "/");

		// bind by using the specified username/password
		Hashtable props = new Hashtable();
		// String principalName = username + "@" + domainName;
		String principalName = domainName + "\\" + username;
		
		props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		props.put(Context.PROVIDER_URL, "ldap://" + serverName + "/");
		props.put(Context.SECURITY_PRINCIPAL, principalName);
		props.put(Context.SECURITY_CREDENTIALS, password);

		try {
			DirContext ctx = new InitialDirContext(props);
			System.out.println("Authentication succeeded!");
			ctx.close();
			
//			// DirContext context = LdapCtxFactory.getLdapCtxInstance("ldap://"
//			// + serverName
//			// + "." + domainName + '/', props);
//			DirContext context = LdapCtxFactory.getLdapCtxInstance("ldap://"
//					+ serverName + '/', props);
//
//			// locate this user's record
//			SearchControls controls = new SearchControls();
//			controls.setSearchScope(SUBTREE_SCOPE);
//			NamingEnumeration<SearchResult> renum = context.search(
//					toDC(domainName), "(& (userPrincipalName=" + principalName
//							+ ")(objectClass=user))", controls);
//			if (!renum.hasMore()) {
//				System.out.println("Cannot locate user information for "
//						+ username);
//				System.exit(1);
//			}
//			SearchResult result = renum.next();
//
//			// List<GrantedAuthority> groups = new
//			// ArrayList<GrantedAuthority>();
//			Attribute memberOf = result.getAttributes().get("memberOf");
//			if (memberOf != null) {// null if this user belongs to no group at
//									// all
//				for (int i = 0; i < memberOf.size(); i++) {
//					Attributes atts = context.getAttributes(memberOf.get(i)
//							.toString(), new String[] { "CN" });
//					Attribute att = atts.get("CN");
//					// groups.add(new
//					// GrantedAuthorityImpl(att.get().toString()));
//				}
//			}
//
//			context.close();
//
//			System.out.println();
//			System.out.println("User belongs to: ");
//			// Iterator ig = groups.iterator();
//			// while (ig.hasNext()) {
//			// System.out.println("   " + ig.next().toString());
//			// }

		} catch (AuthenticationException a) {
			System.out.println("Authentication failed: " + a);
			System.exit(1);
		} catch (NamingException e) {
			System.out
					.println("Failed to bind to LDAP / get account information: "
							+ e);
			System.exit(1);
		}
	}

	private static String toDC(String domainName) {
		StringBuilder buf = new StringBuilder();
		for (String token : domainName.split("\\.")) {
			if (token.length() == 0)
				continue; // defensive check
			if (buf.length() > 0)
				buf.append(",");
			buf.append("DC=").append(token);
		}
		return buf.toString();
	}

}