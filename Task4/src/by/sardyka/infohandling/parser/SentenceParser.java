package by.sardyka.infohandling.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.sardyka.infohandling.entity.IComponent;
import by.sardyka.infohandling.entity.TextComposite;
import by.sardyka.infohandling.entity.TextType;

public class SentenceParser extends AbstractParser {

	private static final String REG_LEXEME = "'?[a-zA-Z]+(\\-?\\p{Lower}+)*\\p{Punct}*"
			+ "|[\\d\\-\\+\\)\\(ij]+[\\d\\s\\-\\+\\*\\/\\)\\(ij]+[\\d\\-\\+\\)\\(ij]+|\\-?\\d+";
	private static final Logger LOG = LogManager.getLogger(SentenceParser.class);

	public SentenceParser() {
		parser = new LexemeParser();
	}

	@Override
	public IComponent parse(String text) {
		LOG.log(Level.INFO, "\nThe method parse() is starting to parse the sentence:\n" + text);
		TextComposite result = new TextComposite(TextType.LEXEME);
		Pattern patternLexeme = Pattern.compile(REG_LEXEME);
		Matcher matcher = patternLexeme.matcher(text);
		while (matcher.find()) {
			String lexeme = matcher.group();
			result.add(parser.parse(lexeme));
		}
		LOG.log(Level.DEBUG, "\nThe method parse() is returning result:\n" + result);
		return result;
	}

}
