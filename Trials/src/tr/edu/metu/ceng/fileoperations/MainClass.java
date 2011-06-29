package tr.edu.metu.ceng.fileoperations;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class MainClass {

	public static void main(String args[]) throws Exception {

		URL u = new URL(
				"http://88.255.237.94/resource/yonetmelikler/Afet%20Bolgelerinde%20Yapilacak%20Yapilar%20Hakkinda%20Yonetmelik.pdf");
		URLConnection uc = u.openConnection();
		String contentType = uc.getContentType();
		int contentLength = uc.getContentLength();
		if (contentType.startsWith("text/") || contentLength == -1) {
			throw new IOException("This is not a binary file.");
		}
		InputStream raw = uc.getInputStream();
		InputStream in = new BufferedInputStream(raw);
		byte[] data = new byte[contentLength];
		int bytesRead = 0;
		int offset = 0;
		while (offset < contentLength) {
			bytesRead = in.read(data, offset, data.length - offset);
			if (bytesRead == -1)
				break;
			offset += bytesRead;
		}
		in.close();

		if (offset != contentLength) {
			throw new IOException("Only read " + offset + " bytes; Expected "
					+ contentLength + " bytes");
		}

		String filename = u.getFile().substring(u.getFile().lastIndexOf('/') + 1);
		FileOutputStream out = new FileOutputStream("./" + filename);
		out.write(data);
		out.flush();
		out.close();
	}
}