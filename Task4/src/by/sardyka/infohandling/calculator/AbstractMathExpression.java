package by.sardyka.infohandling.calculator;

import by.sardyka.infohandling.exception.WrongMathExpressionException;

public abstract class AbstractMathExpression {
public abstract void interpret(Context context) throws WrongMathExpressionException;
}
