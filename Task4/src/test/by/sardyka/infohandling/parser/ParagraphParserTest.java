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
import by.sardyka.infohandling.parser.ParagraphParser;
import by.sardyka.infohandling.entity.IComponent;

@RunWith(Parameterized.class)
public class ParagraphParserTest {
		private ParagraphParser parser;
		private String inputStr;
		private String resultStr1;
		private String resultStr2;
		private int resultSize;		

		public ParagraphParserTest(String inputStr, String resultStr1, String resultStr2, int resultSize) {
			super();
			this.inputStr = inputStr;
			this.resultStr1 = resultStr1;
			this.resultStr2 = resultStr2;
			this.resultSize = resultSize;
		}

		@Parameters
		public static Collection<Object[]> ParagraphParserTestValue() {
			Object[][] obj = new Object[][] { {
					"\tIt has survived not only five centuries, but also the leap into 13 electronic ty"
							+ "pe setting, remaining 3 essentially 6 unchanged. It was popularised in the 5 wi"
							+ "th the release of Letraset sheets containing Lorem Ipsum passages, and mo"
							+ "re recently with desktop publishing software like Aldus PageMaker includ"
							+ "ing versions of Lorem Ipsum.\r\n",
					"It has survived not only five centuries, but also the leap int"
							+ "o 13 electronic type setting, remaining 3 essentially 6 unchanged. ",
					"It was popularised in the 5 with the release of Letraset she"
							+ "ets containing Lorem Ipsum passages, and more recently with desktop publi"
							+ "shing software like Aldus PageMaker including versions of Lorem Ipsum. ",
					2 }, {
							"\tIt is a 1200 established fact that a reader wi"
									+ "ll be of a page when looking at its layout. Bye.",
							"It is a 1200 established fact that a reader will be of a page when looking at its layout. ",
							"Bye. ", 2 } };
			return Arrays.asList(obj);
		}

		@Before
		public void initParagraphParser() {
			parser = new ParagraphParser();
		}

		@Test
		public void parseParagrphTest() throws IndexOutOfBoundsException, NullPointerException {
			IComponent result = parser.parse(inputStr);
			boolean b1 = resultSize == ((TextComposite) result).getTextComposite().size();
			boolean b2 = ((TextComposite) result).getTextComposite().get(0).toString().equals(resultStr1);
			boolean b3 = ((TextComposite) result).getTextComposite().get(1).toString().equals(resultStr2);
			boolean actual = b1 && b2 && b3;
			assertTrue("Method parse works incorrectly", actual);
		}
	}
