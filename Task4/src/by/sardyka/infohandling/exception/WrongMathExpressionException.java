package by.sardyka.infohandling.exception;

public class WrongMathExpressionException extends Exception{

	private static final long serialVersionUID = 1L;

	public WrongMathExpressionException() {
		super();
	}

	public WrongMathExpressionException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public WrongMathExpressionException(String arg0) {
		super(arg0);
	}

	public WrongMathExpressionException(Throwable arg0) {
		super(arg0);
	}
	

}
