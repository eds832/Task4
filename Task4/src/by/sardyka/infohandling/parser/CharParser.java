package by.sardyka.infohandling.parser;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.sardyka.infohandling.entity.CharLeaf;
import by.sardyka.infohandling.entity.IComponent;

public class CharParser extends AbstractParser {
	private static final Logger LOG = LogManager.getLogger(CharParser.class);

	public CharParser() {
	}

	@Override
	public IComponent parse(String text) {
		LOG.log(Level.TRACE, "\nThe method parse() is handling the char: " + text);
		return new CharLeaf(text.charAt(0));
	}

}
