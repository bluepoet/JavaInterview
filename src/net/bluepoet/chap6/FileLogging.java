package net.bluepoet.chap6;

import java.io.File;
import java.io.FileWriter;

public class FileLogging implements Logging {
	private File toWrite;
	
	public FileLogging(File toWrite) {
		this.toWrite = toWrite;
	}
	
	@Override
	public void write(String message) {
		try {
			FileWriter fos = new FileWriter(toWrite);
			fos.write(message);
			fos.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
