package by.sardyka.infohandling.calculator;

import by.sardyka.infohandling.exception.WrongMathExpressionException;

public class TerminalExpressionMinus extends AbstractMathExpression {
	@Override
	public void interpret(Context context) throws WrongMathExpressionException {
		context.pushValue(-context.popValue() + context.popValue());
	}
}
