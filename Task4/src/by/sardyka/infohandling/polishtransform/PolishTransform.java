package by.sardyka.infohandling.polishtransform;

import java.util.ArrayDeque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PolishTransform {

	private static final String I_OR_J = "[\\-\\+]{0,2}[ij][\\-\\+]{0,2}";
	private static final String DECREMENT = "\\-{2}";
	private static final String INCREMENT = "\\+{2}";
	private static final String I = "i";
	private static final String J = "j";
	private static final String DIGIT = "\\d";
	private static final String OPEN_BR = "(";
	private static final String CLOSE_BR = ")";
	private static final String MULTIPLY = "*";
	private static final String DIVIDE = "/";
	private static final String PLUS = "+";
	private static final String MINUS = "-";
	private static final String NEGATE = "_";
	private static final String EMPTY_STR = "";
	private static final String ZERO = "0";
	private static final int MAX_PRIORITY = 2;
	private static final int MIN_PRIORITY = 1;
	private static final Logger LOG = LogManager.getLogger(PolishTransform.class);
	private static final int MAX_VALUE = 1_000;
	private static int i;
	private static int j;
	private String text;
	private ArrayDeque<String> result;
	private ArrayDeque<String> stack;

	public PolishTransform(String text) {
		this.text = text;
		stack = new ArrayDeque<>();
		result = new ArrayDeque<>();
	}

	public static void setI(int i) {
		if (Math.abs(i) < MAX_VALUE) {
			PolishTransform.i = i;
		} else {
			throw new RuntimeException("Not aplicable value of j=" + j);
		}
	}

	public static void setJ(int j) {
		if (Math.abs(i) < MAX_VALUE) {
			PolishTransform.j = j;
		} else {
			throw new RuntimeException("Not aplicable value of i=" + i);
		}
	}

	public ArrayDeque<String> transform() {
		LOG.log(Level.INFO,
				"\nThe method transform() trying to transform expression: " + text + ", where i=" + i + ", j=" + j);
		Pattern patternIOrJ = Pattern.compile(I_OR_J);
		boolean iOrJChecked = false;
		while (!iOrJChecked) {
			Matcher startSearch = patternIOrJ.matcher(text);
			if (startSearch.find()) {
				String iOrJsubstring = text.substring(startSearch.start(), startSearch.end());
				if (iOrJsubstring.matches(DECREMENT + I)) {
					i--;
					if (i < 0) {
						text = text.replaceFirst(DECREMENT + I, OPEN_BR + ZERO + i + CLOSE_BR);
					} else {
						text = text.replaceFirst(DECREMENT + I, EMPTY_STR + i);
					}
				}
				if (iOrJsubstring.matches(INCREMENT + I)) {
					i++;
					if (i < 0) {
						text = text.replaceFirst(INCREMENT + I, OPEN_BR + ZERO + i + CLOSE_BR);
					} else {
						text = text.replaceFirst(INCREMENT + I, EMPTY_STR + i);
					}
				}
				if (iOrJsubstring.matches(DECREMENT + J)) {
					j--;
					if (j < 0) {
						text = text.replaceFirst(DECREMENT + J, OPEN_BR + ZERO + j + CLOSE_BR);
					} else {
						text = text.replaceFirst(DECREMENT + J, EMPTY_STR + j);
					}
				}
				if (iOrJsubstring.matches(INCREMENT + J)) {
					j++;
					if (j < 0) {
						text = text.replaceFirst(INCREMENT + J, OPEN_BR + ZERO + j + CLOSE_BR);
					} else {
						text = text.replaceFirst(INCREMENT + J, EMPTY_STR + j);
					}
				}
				if (iOrJsubstring.matches(I + DECREMENT)) {
					if (i < 0) {
						text = text.replaceFirst(I + DECREMENT, OPEN_BR + ZERO + i + CLOSE_BR);
					} else {
						text = text.replaceFirst(I + DECREMENT, EMPTY_STR + i);
					}
					i--;
				}
				if (iOrJsubstring.matches(J + INCREMENT)) {
					if (i < 0) {
						text = text.replaceFirst(J + INCREMENT, OPEN_BR + ZERO + i + CLOSE_BR);
					} else {
						text = text.replaceFirst(J + INCREMENT, EMPTY_STR + i);
					}
					i++;
				}
				if (iOrJsubstring.matches(J + DECREMENT)) {
					if (j < 0) {
						text = text.replaceFirst(J + DECREMENT, OPEN_BR + ZERO + j + CLOSE_BR);
					} else {
						text = text.replaceFirst(J + DECREMENT, EMPTY_STR + j);
					}
					j--;
				}
				if (iOrJsubstring.matches(J + INCREMENT)) {
					if (j < 0) {
						text = text.replaceFirst(J + INCREMENT, OPEN_BR + ZERO + j + CLOSE_BR);
					} else {
						text = text.replaceFirst(J + INCREMENT, EMPTY_STR + j);
					}
					j++;
				}
			}
			iOrJChecked = !patternIOrJ.matcher(text).find();
		}
		LOG.log(Level.INFO, "\nThe expression was partly transformed: " + text);
		StringBuilder temporary = new StringBuilder();
		String[] chars = text.split(EMPTY_STR);
		int k = 0;
		while (k < chars.length) {
			String oneElement = chars[k];
			if (oneElement.matches(DIGIT)) {
				temporary = temporary.append(oneElement);
				if (k + 1 < chars.length) {
					String nextElement = chars[k + 1];
					if (nextElement.matches(DIGIT)) {
						k++;
						continue;
					}
				}
				oneElement = temporary.toString();
				temporary.delete(0, temporary.length());
			}
			if (oneElement.equals(MINUS) && (k == 0 || (k > 0 && chars[k - 1].equals(OPEN_BR)))) {
				oneElement = NEGATE;
			}
			switch (oneElement) {
			case PLUS:
			case MINUS:
				handleOperator(oneElement, MIN_PRIORITY);
				break;
			case MULTIPLY:
			case DIVIDE:
				handleOperator(oneElement, MAX_PRIORITY);
				break;
			case OPEN_BR:
			case NEGATE:
				stack.push(oneElement);
				break;
			case CLOSE_BR:
				while (!stack.isEmpty()) {
					String operator = stack.pop();
					if (operator.equals(OPEN_BR)) {
						break;
					} else {
						result.push(operator);
					}
				}
				break;
			default:
				result.push(oneElement);
				if (!stack.isEmpty()) {
					String operator = stack.pop();
					if (operator.equals(NEGATE)) {
						result.push(operator);
					} else {
						stack.push(operator);
					}
				}
				break;
			}
			k++;
		}
		while (!stack.isEmpty()) {
			result.push(stack.pop());
		}
		LOG.log(Level.DEBUG, "\nThe method transform() is returning result");
		return result;
	}

	private void handleOperator(String operator, int priority) {
		while (!stack.isEmpty()) {
			String stackOperator = stack.pop();
			if (stackOperator.equals(OPEN_BR)) {
				stack.push(stackOperator);
				break;
			} else {
				int stackOpPriority = (stackOperator.equals(PLUS) || stackOperator.equals(MINUS)) ? MIN_PRIORITY
						: MAX_PRIORITY;
				if (stackOpPriority < priority) {
					stack.push(stackOperator);
					break;
				} else {
					result.push(stackOperator);
				}
			}
		}
		stack.push(operator);
	}
}