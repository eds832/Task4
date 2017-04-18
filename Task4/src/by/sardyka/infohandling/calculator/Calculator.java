package by.sardyka.infohandling.calculator;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.sardyka.infohandling.exception.WrongMathExpressionException;

public class Calculator {
	private static final String PLUS = "+";
	private static final String MINUS = "-";
	private static final String MULTIPLY = "*";
	private static final String DIVIDE = "/";
	private static final String NEGATE = "_";
	private ArrayList<AbstractMathExpression> listExpression;
	private static final Logger LOG = LogManager.getLogger(Calculator.class);

	public Calculator(ArrayDeque<String> expression) {
		listExpression = new ArrayList<>();
		prepare(expression);
	}

	private void prepare(ArrayDeque<String> expression) {
		while (!expression.isEmpty()) {
			String lexeme = expression.removeLast();
			if (!lexeme.isEmpty()) {
				switch (lexeme) {
				case PLUS:
					listExpression.add(new TerminalExpressionPlus());
					break;
				case MINUS:
					listExpression.add(new TerminalExpressionMinus());
					break;
				case MULTIPLY:
					listExpression.add(new TerminalExpressionMultiply());
					break;
				case DIVIDE:
					listExpression.add(new TerminalExpressionDivide());
					break;
				case NEGATE:
					listExpression.add(new TerminalExpressionNegate());
					break;
				default:
					Scanner scan = new Scanner(lexeme);
					if (scan.hasNextDouble()) {
						listExpression.add(new NonterminalExpressionNumber(scan.nextDouble()));
					}
				}
			}
		}
	}

	public Number calculate() throws WrongMathExpressionException {
		Context context = new Context();
		for (AbstractMathExpression terminal : listExpression) {
			terminal.interpret(context);
		}
		Number result = context.popValue();
		LOG.log(Level.INFO, "\nThe method calculate() is returning result = " + result);
		return result;
	}
}
