package test.by.sardyka.infohandling.functionality;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import by.sardyka.infohandling.entity.IComponent;
import by.sardyka.infohandling.functionality.Functionality;
import by.sardyka.infohandling.parser.TextParser;

@RunWith(Parameterized.class)
public class FunctionalityTest {

	private IComponent text;
	private String inputStr;
	private String outputStr1;
	private String outputStr2;
	private String outputStr3;

	public FunctionalityTest(String inputStr, String outputStr1, String outputStr2, String outputStr3) {
		super();
		this.inputStr = inputStr;
		this.outputStr1 = outputStr1;
		this.outputStr2 = outputStr2;
		this.outputStr3 = outputStr3;
	}

	@Parameters
	public static Collection<Object[]> CalculatorTestValue() {
		Object[][] obj = new Object[][] { {
				"\tIt is a -464400 established fact that a reader wi"
						+ "ll be of a page when looking at its layout.\r\n\tBye.",
				"\tBye.\r\n\tIt is a -464400 established fact that a reader will be of a page when looking at i"
						+ "ts layout.",
				"\tlayout. is a -464400 established fact that a reader will be of a page when looking at its I"
						+ "t\r\n\tBye.",
				"\t-464400\r\n\ta a a at\r\n\tbe Bye.\r\n\testablished\r\n\tfact\r\n\tIt is its\r\n\tl"
						+ "ooking layout.\r\n\tof\r\n\tpage\r\n\treader\r\n\tthat\r\n\twill when" } };
		return Arrays.asList(obj);
	}

	@Before
	public void initCalculator() {
		text = new TextParser().parse(inputStr);
	}

	@Test
	public void sortSentenceByLexemeTest() {
		boolean actual = Functionality.sortSentenceByLexeme(text).toString().equals(outputStr1);
		assertTrue("Method sortSentenceByLexeme works incorrectly", actual);
	}

	@Test
	public void changeFirstLastLexemeTest() {
		boolean actual = Functionality.changeFirstLastLexeme(text).toString().equals(outputStr2);
		assertTrue("Method changeFirstLastLexeme works incorrectly", actual);
	}

	@Test
	public void sortLexemeByFirstLetterTest() {
		boolean actual = Functionality.sortLexemeByFirstLetter(text).toString().equals(outputStr3);
		assertTrue("Method sortLexemeByFirstLetter works incorrectly", actual);
	}
}
