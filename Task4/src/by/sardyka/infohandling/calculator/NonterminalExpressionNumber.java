package by.sardyka.infohandling.calculator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NonterminalExpressionNumber extends AbstractMathExpression {
	private double number;
	private static final Logger LOG = LogManager.getLogger(NonterminalExpressionNumber.class);

	public NonterminalExpressionNumber(double number) {
		this.number = number;
	}

	@Override
	public void interpret(Context context) {
		LOG.log(Level.TRACE,
				"\nThe method interpret(Context context) is pushing to context the value of number " + number);
		context.pushValue(number);
	}
}
