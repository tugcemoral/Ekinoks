package tr.edu.metu.ceng.parse;

public class StringParser {

	public static void main2(String[] args) {

		String username = "ali.toksoy@cob.gov.tr";

		if (username.contains("@")) {
			System.out.println("Contains @...");
			String mailDomain = username.substring(username.indexOf("@") + 1,
					username.length());
			System.out.println("Mail Domain: " + mailDomain);
			if (mailDomain.trim().equals("cob.gov.tr")
					|| mailDomain.trim().equals("cevreorman.gov.tr")) {
				System.out.println(mailDomain);
			}
		}

	}

	public static void main(String[] args) {
		String baseURL = "http://www.hacettepe.edu.tr/";

		String domain = baseURL.substring(baseURL.indexOf(".") + 1, baseURL.lastIndexOf("/"));
		
		System.out.println(domain);
	}

}
