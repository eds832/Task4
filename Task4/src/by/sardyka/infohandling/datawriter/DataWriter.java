package by.sardyka.infohandling.datawriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DataWriter {

	private static final Logger LOG = LogManager.getLogger(DataWriter.class);

	public static void writeData(String fileName, String data) {
		if (data == null || data.isEmpty()) {
			LOG.log(Level.FATAL, "\nThere is the empty data to write: " + data);
			throw new RuntimeException("\nThere is the empty data to write: " + data);
		}
		if (fileName == null || fileName.isEmpty()) {
			LOG.log(Level.FATAL, "\nThere is the empty file name to write: " + fileName);
			throw new RuntimeException("\nThere is the empty file name to write: " + fileName);
		}
		try {
			Files.write(Paths.get(fileName), data.getBytes(), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
		} catch (IOException e) {
			LOG.log(Level.FATAL, "\nThere is a input-otput problem with writing to " + fileName);
			throw new RuntimeException("\nThere is a input-otput problem with writing to " + fileName);
		}
		LOG.log(Level.INFO, "\nThe data was writen to the file: " + fileName);
	}
}
