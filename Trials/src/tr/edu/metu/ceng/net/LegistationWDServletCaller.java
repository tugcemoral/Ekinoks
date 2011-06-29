package tr.edu.metu.ceng.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LegistationWDServletCaller {

	private static final String LEGISTATION_PATH_PROVIDER_URL = "http://192.168.2.67:7070/LegistationServer/getLegistationsPath";

	public static void main(String[] args) throws Exception {
		URL externalServerURL = new URL(LEGISTATION_PATH_PROVIDER_URL);

		// open a connection to this server.
		HttpURLConnection connection = (HttpURLConnection) externalServerURL
				.openConnection();

		// set specific properties of this connection.
		connection.setUseCaches(true);
		connection.setRequestMethod("GET");

		// open the connection.
		connection.connect();
		String inputMessage = getInputMessage(connection);
		System.out.println(inputMessage);
	}

	private static String getInputMessage(HttpURLConnection connection)
			throws IOException {
		// create a string builder to constuct message.
		StringBuilder strBuilder = new StringBuilder();

		// get input stream and construct its reader.
		InputStream is = connection.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);

		// create the buffered reader.
		BufferedReader br = new BufferedReader(isr);
		// read the content.
		String line = null;
		while ((line = br.readLine()) != null) {
			strBuilder.append(line);
		}
		// close readers.
		br.close();
		isr.close();
		is.close();

		return strBuilder.toString();
	}

}
