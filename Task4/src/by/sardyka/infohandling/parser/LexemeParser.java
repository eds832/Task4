package by.sardyka.infohandling.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.sardyka.infohandling.entity.IComponent;
import by.sardyka.infohandling.entity.TextComposite;
import by.sardyka.infohandling.entity.TextType;
import by.sardyka.infohandling.exception.WrongMathExpressionException;
import by.sardyka.infohandling.polishtransform.PolishTransform;
import by.sardyka.infohandling.calculator.Calculator;

public class LexemeParser extends AbstractParser {

	private static final String REG_MATH = "[\\d\\-\\+\\)\\(ij]+[\\d\\s\\-\\+\\*\\/\\)\\(ij]+[\\d\\-\\+\\)\\(ij]+";
	private static final String REG_CHAR = ".";
	private static final String MATH_STR = "'a math expression'";
	private static final Logger LOG = LogManager.getLogger(LexemeParser.class);

	public LexemeParser() {
		parser = new CharParser();
	}

	@Override
	public IComponent parse(String text) {
		LOG.log(Level.INFO, "\nThe method parse() is starting to parse the lexeme: " + text);
		TextComposite result = new TextComposite(TextType.WORD);
		Pattern patternMath = Pattern.compile(REG_MATH);
		Matcher matcherMath = patternMath.matcher(text);
		if (matcherMath.find()) {
			String math = MATH_STR;
			try {
				LOG.log(Level.DEBUG, "\nThe method parse() is trying to handle math expression: " + text);
				math = String.valueOf(new Calculator(new PolishTransform(text).transform()).calculate().intValue());
			} catch (WrongMathExpressionException e) {
				LOG.log(Level.ERROR,
						"\nThere is the wrong math expression: " + text + ", it was changed for " + MATH_STR);
			}
			text = math;
		}
		Pattern patternChar = Pattern.compile(REG_CHAR);
		Matcher matcherChar = patternChar.matcher(text);
		while (matcherChar.find()) {
			result.add(parser.parse(matcherChar.group()));
		}
		LOG.log(Level.DEBUG, "\nThe method parse() is returning result: " + result);
		return result;
	}

}
