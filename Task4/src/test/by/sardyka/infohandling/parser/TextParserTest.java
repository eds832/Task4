package test.by.sardyka.infohandling.parser;

import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import by.sardyka.infohandling.entity.TextComposite;
import by.sardyka.infohandling.parser.TextParser;
import by.sardyka.infohandling.entity.IComponent;

@RunWith(Parameterized.class)
public class TextParserTest {
	private TextParser parser;
	private String inputStr;
	private String resultStr1;
	private String resultStr2;
	private int resultSize;

	public TextParserTest(String inputStr, String resultStr1, String resultStr2, int resultSize) {
		super();
		this.inputStr = inputStr;
		this.resultStr1 = resultStr1;
		this.resultStr2 = resultStr2;
		this.resultSize = resultSize;
	}

	@Parameters
	public static Collection<Object[]> TextParserTestValue() {
		Object[][] obj = new Object[][] { {
				"\tIt has survived not only five centuries, but also the leap into 13 electronic ty"
						+ "pe setting, remaining 3 essentially 6 unchanged. It was popularised in the 5 wi"
						+ "th the release of Letraset sheets containing Lorem Ipsum passages, and mo"
						+ "re recently with desktop publishing software like Aldus PageMaker includ"
						+ "ing versions of Lorem Ipsum.\r\n\tIt is a long established fact that a rea"
						+ "der will be distracted by the readable content of a page when looking at its layout. Th"
						+ "e point of using 71 Ipsum is that it has a more-or-less normal distribution of letters, as oppose"
						+ "d to using 'Content here, content here', making it look like readable English.\r\n",
				"It has survived not only five centuries, but also the leap int"
						+ "o 13 electronic type setting, remaining 3 essentially 6 unchange"
						+ "d. It was popularised in the 5 with the release of Letraset she"
						+ "ets containing Lorem Ipsum passages, and more recently with desktop publi"
						+ "shing software like Aldus PageMaker including versions of Lorem Ipsum. ",
				"It is a long established fact that a reader will be distracted by the re"
						+ "adable content of a page when looking at its layout. The point of usin"
						+ "g 71 Ipsum is that it has a more-or-less normal distribution of letters, as op"
						+ "posed to using 'Content here, content here', making it look like readable English. ",
				2 }, {
						"\tIt is a 1200 established fact that a reader wi"
								+ "ll be of a page when looking at its layout.\r\n\tBye.",
						"It is a 1200 established fact that a reader will be of a page when looking at its layout. ",
						"Bye. ", 2 } };
		return Arrays.asList(obj);
	}

	@Before
	public void initTextParser() {
		parser = new TextParser();
	}

	@Test
	public void parseTextTest() throws IndexOutOfBoundsException, NullPointerException {
		IComponent result = parser.parse(inputStr);
		boolean b1 = resultSize == ((TextComposite) result).getTextComposite().size();
		boolean b2 = ((TextComposite) result).getTextComposite().get(0).toString().equals(resultStr1);
		boolean b3 = ((TextComposite) result).getTextComposite().get(1).toString().equals(resultStr2);
		boolean actual = b1 && b2 && b3;
		assertTrue("Method parse works incorrectly", actual);
	}
}
