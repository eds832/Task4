package test.by.sardyka.infohandling.datawriter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import by.sardyka.infohandling.datawriter.DataWriter;

@RunWith(Parameterized.class)
public class DataWriterTest {

	private static File file;
	private String output;

	public DataWriterTest(String output) {
		super();
		this.output = output;
	}

	@Parameters
	public static Collection<Object[]> DataWriterTestValue() {
		Object[][] obj = new Object[][] {
				{ "\tIt has survived not only five centuries, but also the leap into 790 electron"
						+ "ic type setting, remaining 8 essentially -3 unchanged. It was popularis"
						+ "ed in the -92155 with the release of Letraset sheets containing Lorem Ip"
						+ "sum passages, and more recently with desktop publishing software like Ald"
						+ "us PageMaker including versions of Lorem Ipsum.\r\n\tIt is a long establis"
						+ "hed fact that a reader will be distracted by the readable content of a pag"
						+ "e when looking at its layout. The point of using -1145376 Ipsum is that it h"
						+ "as a more-or-less normal distribution of letters, as opposed to using 'Conte"
						+ "nt here, content here', making it look like readable English.\r\n\tIt is a -463"
						+ "800 established fact that a reader will be of a page when looking at its layout.\r\n\tBye." },
				{ "\t'Content\r\n\t-3 -92155 -1145376 -463800\r\n\t790\r\n\t8\r\n\tAldus\r\n\tBye.\r\n\tEnglish.\r\n\t"
						+ "It It Ipsum Ipsum. It Ipsum It\r\n\tLetraset Lorem Lorem\r\n\tPageMaker\r\n\tThe\r\n\t"
						+ "also and a a a at a as a a a at\r\n\tbut be by becenturies,\r\n\tcontaining content content\r\n\t"
						+ "desktop distracted distribution\r\n\telectronic essentially established established\r\n\t"
						+ "five fact fact\r\n\thas has here, here',\r\n\tinto in including is its is it it is its\r\n\t"
						+ "leap like long looking layout. letters, look like looking layout\r\n\t"
						+ "more more-or-less making\r\n\tnot normal\r\n\tonly of of of of of opposed of\r\n\t"
						+ "popularised passages, publishing page point page\r\n\tremaining release recently "
						+ "reader readable readable reader\r\n\tsurvived setting, sheets software\r\n\t"
						+ "the type the the that the that to that\r\n\tunchanged. using using\r\n\tversions\r\n\t"
						+ "was with with will when will when" } };
		return Arrays.asList(obj);
	}

	@Rule
	public final TemporaryFolder folder = new TemporaryFolder();

	@Before
	public void initDataWriter() throws IOException {
		file = folder.newFile("out.txt");
	}

	@Test
	public void writeStringDataTest() throws IOException {
		DataWriter.writeData(file.getAbsolutePath(), output);
		String text = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
		boolean actual = text.equals(output);
		assertTrue("writeData writes a string incorrectly", actual);
	}

	@Test
	public void writeNullDataTest() throws RuntimeException {
		try {
			DataWriter.writeData(file.getAbsolutePath(), null);
			fail("writeNullDataTest for writeData should have thrown a RuntimeException with a message");
		} catch (RuntimeException e) {
			assertEquals("\nThere is the empty data to write: null", e.getMessage());
		}
	}

	@Test
	public void writeEmptyDataTest() throws RuntimeException {
		try {
			DataWriter.writeData(file.getAbsolutePath(), "");
			fail("writeEmptyDataTest for writeData should have thrown a RuntimeException with a message");
		} catch (RuntimeException e) {
			assertEquals("\nThere is the empty data to write: ", e.getMessage());
		}
	}

	@Test
	public void writeNullFileNameTest() throws RuntimeException {
		try {
			DataWriter.writeData(null, output);
			fail("writeNullFileNameTest for writeData should have thrown a RuntimeException with a message");
		} catch (RuntimeException e) {
			assertEquals("\nThere is the empty file name to write: null", e.getMessage());
		}
	}

	@Test
	public void writeEmptyFileNameTest() throws RuntimeException {
		try {
			DataWriter.writeData("", output);
			fail("writeEmptyFileNameTest for writeData should have thrown a RuntimeException with a message");
		} catch (RuntimeException e) {
			assertEquals("\nThere is the empty file name to write: ", e.getMessage());
		}
	}

}
