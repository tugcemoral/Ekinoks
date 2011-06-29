package tr.edu.metu.ceng.net;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class URLNormalizer {

	public static void main(String[] args) throws UnsupportedEncodingException {
		String query = "eiaID=179&fileName="
				+ "i%C3%83%C2%B6%C3%83%C2%A7%C3%83%C2%A7%C3%85%C2%9F%C3%85%C2%9F%C3%85%C2%9Fsaa%C3%84%C2%B1%C3%84%C2%B1%C3%84%C2%B1%C3%85%C2%9F%C3%85%C2%9F%C3%84%C2%9F%C3%84%C2%9F";
		String[] paramValuePairs = query.split("&");

		for (String paramValuePair : paramValuePairs) {

			String[] paramVsValue = paramValuePair.split("=");

			System.out.println("Parameter Name: " + paramVsValue[0]);
			System.out.println("Parameter Value: " + paramVsValue[1]);

			if (paramVsValue[0].equals("fileName")) {
				String decodedName = URLDecoder
						.decode(paramVsValue[1], "UTF-8");
				System.out.println("Decoded Name: " + decodedName);
			}

		}
		System.out.println("Encoded: "
				+ URLDecoder.decode("i%20öççşşşsaaııışşğğ", "UTF-8"));

	}
}
