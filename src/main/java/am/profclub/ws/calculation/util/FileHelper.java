package am.profclub.ws.calculation.util;

import java.io.*;

/**
 * Created by admin on 6/2/17.
 */
public class FileHelper {

	public static String create(String destination, String filename) throws IOException {
		File file = new File(destination);
		if (file.exists()) {
			if (!file.isDirectory()) {
				file.delete();
				file.mkdir();
			}
		} else {
			file.mkdir();
		}

		file = new File(destination, filename);
		if (file.exists()) {
			return file.getAbsolutePath();
		}

		boolean created = file.createNewFile();
		if (created) return file.getAbsolutePath();

		return null;
	}

	public static void write(File file, String content) {
		write(file, content, true);
	}

	public static void write(File file, String content, boolean append) {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(file, append);
			fileWriter.write(content);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			try {
				fileWriter.close();
			} catch (IOException e) {
				//ignore
			}
		}
	}
}
