package by.sardyka.infohandling.parser;

import by.sardyka.infohandling.entity.IComponent;

public abstract class AbstractParser {

	protected AbstractParser parser;

	public AbstractParser() {
	}

	abstract public IComponent parse(String text);
	
}
