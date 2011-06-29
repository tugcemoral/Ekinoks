package tr.edu.metu.ceng.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServletCaller {

	public static void main(String[] args) throws Exception {
		String uploadServerIP = "192.168.2.67:9090";
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("http://");
		stringBuilder.append(uploadServerIP);
		stringBuilder.append("/FileUploadServer/deleteFile?");
		stringBuilder.append("eiaID=");
		stringBuilder.append(179);
		 stringBuilder.append("&recursive=");
		 stringBuilder.append(true);
		URL externalServerURL = new URL(stringBuilder.toString());

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
