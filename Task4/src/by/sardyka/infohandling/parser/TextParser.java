package by.sardyka.infohandling.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.sardyka.infohandling.entity.IComponent;
import by.sardyka.infohandling.entity.TextComposite;
import by.sardyka.infohandling.entity.TextType;

public class TextParser extends AbstractParser {

	private static final String REG_PARAGRAPH = "[\\t][^\\t]+";
	private static final Logger LOG = LogManager.getLogger(TextParser.class);

	public TextParser() {
		parser = new ParagraphParser();
	}

	@Override
	public IComponent parse(String text) {
		LOG.log(Level.INFO, "\nThe method parse() is starting to parse the text:\n" + text);
		TextComposite result = new TextComposite(TextType.PARAGRAPH);
		Pattern patternParagraph = Pattern.compile(REG_PARAGRAPH);
		Matcher matcher = patternParagraph.matcher(text);
		while (matcher.find()) {
			String paragraph = matcher.group();
			result.add(parser.parse(paragraph));
		}
		LOG.log(Level.INFO, "\nThe method parse() is returning result:\n" + result);
		return result;
	}

}
