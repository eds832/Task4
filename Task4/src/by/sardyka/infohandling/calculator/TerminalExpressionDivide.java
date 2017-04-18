package by.sardyka.infohandling.calculator;

import by.sardyka.infohandling.exception.WrongMathExpressionException;

public class TerminalExpressionDivide extends AbstractMathExpression {
	@Override
	public void interpret(Context context) throws WrongMathExpressionException {
		context.pushValue(1. / context.popValue() * context.popValue());

	}

}
