package by.sardyka.infohandling.calculator;

import java.util.ArrayDeque;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.sardyka.infohandling.exception.WrongMathExpressionException;

public class Context {
	private static final Logger LOG = LogManager.getLogger(Context.class);
	private ArrayDeque<Double> contextValues = new ArrayDeque<>();

	public Double popValue() throws WrongMathExpressionException {
		if (!contextValues.isEmpty()) {
			return contextValues.pop();
		} else {
			LOG.log(Level.ERROR, "\nThere was trying of use empty ArrayDeque<Double> contextValues");
			throw new WrongMathExpressionException();
		}
	}

	public void pushValue(Double value) {
		this.contextValues.push(value);
	}
}
