package by.sardyka.infohandling.datareader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

public class DataReader {
	private static final Logger LOG = LogManager.getLogger(DataReader.class);
	private static final String EMPTY_STR = "";

	public static String readData(String source) {
		if (source == null || source.isEmpty()) {
			LOG.log(Level.FATAL, "\nThere is the empty string of source: " + source);
			throw new RuntimeException("\nThere is the empty string of source: " + source);
		}
		String text = EMPTY_STR;
		try {
			text = new String(Files.readAllBytes(Paths.get(source)));
		} catch (IOException e) {
			LOG.log(Level.FATAL, "\nThere is a input-output problem with " + source);
			throw new RuntimeException("\nThere is a input-output problem with " + source);
		}
		if (!text.isEmpty()) {
			LOG.log(Level.INFO, "\nThe text was read");
		} else {
			LOG.log(Level.FATAL, "\nThis file is empty");
			throw new RuntimeException("\nThis file is empty");
		}
		return text;
	}

}
