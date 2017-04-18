package test.by.sardyka.infohandling.datareader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import by.sardyka.infohandling.datareader.DataReader;

@RunWith(Parameterized.class)
public class DataReaderTest {
	private String input;
	private static File inputFile;
	private static File emptyFile;
	private static File deletedFile;

	public DataReaderTest(String input) {
		this.input = input;
	}

	@Parameters
	public static Collection<Object[]> DataReaderTestValue() {
		Object[][] obj = new Object[][] {
				{ "\tIt has survived not only five centuries, but also the leap into 13+ i-- electro"
						+ "nic type setting, remaining 3+5 essentially 6+9*(3-4) unchanged. It was pop"
						+ "ularised in the 5*(1*2*(3*(4*(5- --j + 4)-3)-2)-1) with the release of Letra"
						+ "set sheets containing Lorem Ipsum passages, and more recently with desktop pub"
						+ "lishing software like Aldus PageMaker including versions of Lorem Ipsum.\r\n\tIt i"
						+ "s a long established fact that a reader will be distracted by the readable cont"
						+ "ent of a page when looking at its layout." },
				{ "\tThe point of using (71-(2*i--*(3*(2-1/2*2)-2)-10/2))*++i Ipsum is that it has a mo"
						+ "re-or-less normal distribution of letters, as opposed to using 'Content her"
						+ "e, content here', making it look like readable English.\r\n\tIt i"
						+ "s a (-5+1/2*(2+5*2- --j))*1200 established fact that a reader wi"
						+ "ll be of a page when looking at its layout.\r\n\tBye." } };
		return Arrays.asList(obj);
	}

	@Rule
	public final TemporaryFolder folder = new TemporaryFolder();

	@Before
	public void initDataReader() throws IOException {
		inputFile = folder.newFile("inp.txt");
		FileWriter fw = new FileWriter(inputFile);
		fw.write(input);
		fw.flush();
		fw.close();
		emptyFile = folder.newFile("empty.txt");
		deletedFile = folder.newFile("del.txt");
	}

	@Test
	public void readDataTest() {
		String strFromReader = DataReader.readData(inputFile.getAbsolutePath());
		boolean actual = strFromReader.equals(input);
		assertTrue("readData reads incorrectly", actual);
	}

	@Test(expected = RuntimeException.class)
	public void readEmptyDataTest() throws RuntimeException {
		Object expected = null;
		Object actual = DataReader.readData(emptyFile.getAbsolutePath());
		assertEquals("For empty file there aren't RuntimException", expected, actual);
	}

	@Test(expected = RuntimeException.class)
	public void readNotExistingDataTest() throws RuntimeException {
		String fileName = deletedFile.getAbsolutePath();
		deletedFile.delete();
		Object expected = null;
		Object actual = DataReader.readData(fileName);
		assertEquals("For deleted file there aren't RuntimException", expected, actual);
	}

	@Test(expected = RuntimeException.class)
	public void readEmptySourceStringDataTest() throws RuntimeException {
		Object expected = null;
		Object actual = DataReader.readData("");
		assertEquals("For empty source string there aren't RuntimException", expected, actual);
	}
	
	@Test(expected = RuntimeException.class)
	public void readNullSourceDataTest() throws RuntimeException {
		Object expected = null;
		Object actual = DataReader.readData(null);
		assertEquals("For null source there aren't RuntimException", expected, actual);
	}
}
