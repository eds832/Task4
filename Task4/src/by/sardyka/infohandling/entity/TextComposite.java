package by.sardyka.infohandling.entity;

import java.util.ArrayList;
import java.util.Collections;

public class TextComposite implements IComponent {

	private static final String AFTER_LEXEME = new String(new char[] { 32 });
	private static final String BEFORE_PARAGRAPH = "\t";
	private static final String AFTER_PARAGRAPH = "\r\n";
	private static final String EMPTY_STR = "";
	private ArrayList<IComponent> composite;
	private TextType textType;

	public TextComposite(TextType textType) {
		composite = new ArrayList<>();
		this.textType = textType;
	}

	public void add(IComponent component) {
		composite.add(component);
	}

	public void addAll(ArrayList<IComponent> composite) {
		this.composite = new ArrayList<>(composite);
	}

	public ArrayList<IComponent> getTextComposite() {
		return new ArrayList<IComponent>(Collections.unmodifiableList(composite));
	}

	public TextType getTextType() {
		return textType;
	}

	@Override
	public String toString() {
		String result = EMPTY_STR;
		for (int i = 0; i < composite.size(); i++) {
			if (textType == TextType.PARAGRAPH) {
				result += BEFORE_PARAGRAPH;
			}
			result += composite.get(i).toString();
			if (textType == TextType.LEXEME) {
				result += AFTER_LEXEME;
			}
			if (textType == TextType.PARAGRAPH && i != composite.size() - 1 && result.length() > 1
					&& result.endsWith(AFTER_LEXEME)) {
				result = result.substring(0, result.length() - 1);
				result += AFTER_PARAGRAPH;
			}
			if (textType == TextType.PARAGRAPH && i == composite.size() - 1 && result.length() > 1
					&& result.endsWith(AFTER_LEXEME)) {
				result = result.substring(0, result.length() - 1);
			}
		}
		return result;
	}

}
