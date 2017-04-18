package by.sardyka.infohandling.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.sardyka.infohandling.entity.IComponent;
import by.sardyka.infohandling.entity.TextComposite;
import by.sardyka.infohandling.entity.TextType;

public class ParagraphParser extends AbstractParser {

	private static final String REG_SENTENCE = "[\\p{Blank}]+[A-Z][^\\.!\\?]*[\\.!\\?]";
	private static final Logger LOG = LogManager.getLogger(ParagraphParser.class);

	public ParagraphParser() {
		parser = new SentenceParser();
	}

	@Override
	public IComponent parse(String text) {
		LOG.log(Level.INFO, "\nThe method parse() is starting to parse the paragraph:\n" + text);
		TextComposite result = new TextComposite(TextType.SENTENCE);
		Pattern patternSentence = Pattern.compile(REG_SENTENCE);
		Matcher matcher = patternSentence.matcher(text);
		while (matcher.find()) {
			String sentence = matcher.group();
			result.add(parser.parse(sentence));
		}
		LOG.log(Level.DEBUG, "\nThe method parse() is returning result:\n" + result);
		return result;
	}

}
