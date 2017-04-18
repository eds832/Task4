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
import by.sardyka.infohandling.parser.LexemeParser;
import by.sardyka.infohandling.entity.IComponent;

@RunWith(Parameterized.class)
public class LexemeParserTest {

	private LexemeParser parser;
	private String inputStr;
	private String resultStr1;
	private String resultStr2;
	private int resultSize;

	public LexemeParserTest(String inputStr, String resultStr1, String resultStr2, int resultSize) {
		super();
		this.inputStr = inputStr;
		this.resultStr1 = resultStr1;
		this.resultStr2 = resultStr2;
		this.resultSize = resultSize;
	}

	@Parameters
	public static Collection<Object[]> LexemeParserTestValue() {
		Object[][] obj = new Object[][] { { "\tR", "\t", "R", 2 }, { "71", "7", "1", 2 } };
		return Arrays.asList(obj);
	}

	@Before
	public void initLexemeParser() {
		parser = new LexemeParser();
	}

	@Test
	public void parseSentenceTest() throws IndexOutOfBoundsException, NullPointerException {
		IComponent result = parser.parse(inputStr);
		boolean b1 = resultSize == ((TextComposite) result).getTextComposite().size();
		boolean b2 = ((TextComposite) result).getTextComposite().get(0).toString().equals(resultStr1);
		boolean b3 = ((TextComposite) result).getTextComposite().get(1).toString().equals(resultStr2);
		boolean actual = b1 && b2 && b3;
		assertTrue("Method parse works incorrectly", actual);
	}
}
