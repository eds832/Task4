package by.sardyka.infohandling.functionality;

import java.util.ArrayList;
import java.util.Comparator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.sardyka.infohandling.entity.IComponent;
import by.sardyka.infohandling.entity.TextComposite;
import by.sardyka.infohandling.entity.TextType;

public class Functionality {

	private static final Logger LOG = LogManager.getLogger(Functionality.class);

	public static IComponent sortSentenceByLexeme(IComponent text) {
		if (text.getClass() != TextComposite.class) {
			LOG.log(Level.ERROR,
					"\nThe method sortSentenceByLexeme(IComponent text) is applicable only for TextComposite.class");
			throw new RuntimeException(
					"\nThe method sortSentenceByLexeme(IComponent text) is applicable only for TextComposite.class");
		}
		if (((TextComposite) text).getTextType() == TextType.PARAGRAPH) {
			LOG.log(Level.DEBUG,
					"\nThe method sortSentenceByLexeme(IComponent text) is starting to handle the text:\n" + text);
		} else {
			LOG.log(Level.ERROR,
					"\nThe method sortSentenceByLexeme(IComponent text) is applicable only for TextType.PARAGRAPH,"
							+ "\nnot for " + ((TextComposite) text).getTextType());
			throw new RuntimeException(
					"\nThe method sortSentenceByLexeme(IComponent text) is applicable only for TextType.PARAGRAPH,"
							+ "\nnot for " + ((TextComposite) text).getTextType());
		}
		ArrayList<IComponent> paragraphs = ((TextComposite) text).getTextComposite();
		ArrayList<IComponent> sentencesCopy = new ArrayList<>();
		for (IComponent paragraph : paragraphs) {
			ArrayList<IComponent> sentences = ((TextComposite) paragraph).getTextComposite();
			for (IComponent sentence : sentences) {
				sentencesCopy.add(sentence);
			}
		}
		sentencesCopy.sort(new Comparator<IComponent>() {
			@Override
			public int compare(IComponent o1, IComponent o2) {
				return ((TextComposite) o1).getTextComposite().size() - ((TextComposite) o2).getTextComposite().size();
			}
		});
		TextComposite result = new TextComposite(TextType.PARAGRAPH);
		for (IComponent sentence : sentencesCopy) {
			TextComposite resultSentence = new TextComposite(TextType.SENTENCE);
			resultSentence.add(sentence);
			result.add(resultSentence);
		}
		LOG.log(Level.INFO, "\nThe method sortSentenceByLexeme(IComponent text) is returning handled text:\n" + result);
		return result;
	}

	public static IComponent changeFirstLastLexeme(IComponent text) {
		if (text.getClass() != TextComposite.class) {
			LOG.log(Level.ERROR,
					"\nThe method changeFirstLastLexeme(IComponent text) is applicable only for TextComposite.class");
			throw new RuntimeException(
					"\nThe method changeFirstLastLexeme(IComponent text) is applicable only for TextComposite.class");
		}
		if (((TextComposite) text).getTextType() == TextType.PARAGRAPH) {
			LOG.log(Level.DEBUG,
					"\nThe method changeFirstLastLexeme(IComponent text) is starting to handle the text:\n" + text);
		} else {
			LOG.log(Level.ERROR,
					"\nThe method changeFirstLastLexeme(IComponent text) is applicable only for TextType.PARAGRAPH,"
							+ "\nnot for " + ((TextComposite) text).getTextType());
			throw new RuntimeException(
					"\nThe method changeFirstLastLexeme(IComponent text) is applicable only for TextType.PARAGRAPH,"
							+ "\nnot for " + ((TextComposite) text).getTextType());
		}
		ArrayList<IComponent> paragraphs = ((TextComposite) text).getTextComposite();
		TextComposite result = new TextComposite(TextType.PARAGRAPH);
		for (IComponent paragraph : paragraphs) {
			ArrayList<IComponent> sentences = ((TextComposite) paragraph).getTextComposite();
			TextComposite resultSentence = new TextComposite(TextType.SENTENCE);
			for (IComponent sentence : sentences) {
				ArrayList<IComponent> lexemes = new ArrayList<>(((TextComposite) sentence).getTextComposite());
				int size = lexemes.size();
				if (size > 1) {
					IComponent temporary1 = lexemes.remove(size - 1);
					IComponent temporary2 = lexemes.remove(0);
					lexemes.add(0, temporary1);
					lexemes.add(temporary2);
				}
				TextComposite resultLexemes = new TextComposite(TextType.LEXEME);
				resultLexemes.addAll(lexemes);
				resultSentence.add(resultLexemes);
			}
			result.add(resultSentence);
		}
		LOG.log(Level.INFO,
				"\nThe method changeFirstLastLexeme(IComponent text) is returning handled text:\n" + result);
		return result;
	}

	public static IComponent sortLexemeByFirstLetter(IComponent text) {
		if (text.getClass() != TextComposite.class) {
			LOG.log(Level.ERROR,
					"\nThe method sortLexemeByFirstLetter(IComponent text) is applicable only for TextComposite.class");
			throw new RuntimeException(
					"\nThe method sortLexemeByFirstLetter(IComponent text) is applicable only for TextComposite.class");
		}
		if (((TextComposite) text).getTextType() == TextType.PARAGRAPH) {
			LOG.log(Level.DEBUG,
					"\nThe method sortLexemeByFirstLetter(IComponent text) is starting to handle the text:\n" + text);
		} else {
			LOG.log(Level.ERROR,
					"\nThe method sortLexemeByFirstLetter(IComponent text) is applicable only for TextType.PARAGRAPH,"
							+ "\nnot for " + ((TextComposite) text).getTextType());
			throw new RuntimeException(
					"\nThe method sortLexemeByFirstLetter(IComponent text) is applicable only for TextType.PARAGRAPH,"
							+ "\nnot for " + ((TextComposite) text).getTextType());
		}
		ArrayList<IComponent> lexemesCopy = new ArrayList<>();
		ArrayList<IComponent> paragraphs = ((TextComposite) text).getTextComposite();
		for (IComponent paragraph : paragraphs) {
			ArrayList<IComponent> sentences = ((TextComposite) paragraph).getTextComposite();
			for (IComponent sentence : sentences) {
				ArrayList<IComponent> lexemes = new ArrayList<>(((TextComposite) sentence).getTextComposite());
				for (IComponent lexeme : lexemes) {
					lexemesCopy.add(lexeme);
				}
			}
		}
		lexemesCopy.sort(new Comparator<IComponent>() {
			@Override
			public int compare(IComponent o1, IComponent o2) {
				return (int) ((TextComposite) o1).toString().toLowerCase().charAt(0)
						- (int) ((TextComposite) o2).toString().toLowerCase().charAt(0);
			}
		});
		char checkPrevious = 32;
		TextComposite resultLexeme = new TextComposite(TextType.LEXEME);
		TextComposite result = new TextComposite(TextType.PARAGRAPH);
		for (int i = 0; i < lexemesCopy.size(); i++) {
			char checkNew = ((TextComposite) lexemesCopy.get(i)).toString().toLowerCase().charAt(0);
			if (checkNew != checkPrevious) {
				if (i != 0) {
					result.add(resultLexeme);
				}
				resultLexeme = new TextComposite(TextType.LEXEME);
			}
			resultLexeme.add(lexemesCopy.get(i));
			if (i == lexemesCopy.size() - 1) {
				result.add(resultLexeme);
			}
			checkPrevious = ((TextComposite) lexemesCopy.get(i)).toString().toLowerCase().charAt(0);
		}
		LOG.log(Level.INFO,
				"\nThe method sortLexemeByFirstLetter(IComponent text) is returning handled text:\n" + result);
		return result;
	}
}
