package tr.edu.metu.ceng.folder;

import java.io.File;
import java.io.IOException;

public class FileCreator {

	public static void main(String[] args) {

		// File file = new File("/tmp");

		long l = 0l;

		while (l < 999999999) {
			File file = new File("/tmp/tempFiles" + l);
			if (file.exists())
				file.delete();
			l++;
		}

	}

}
