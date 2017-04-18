package test.by.sardyka.infohandling.calculator;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import by.sardyka.infohandling.calculator.Calculator;
import by.sardyka.infohandling.exception.WrongMathExpressionException;
import by.sardyka.infohandling.polishtransform.PolishTransform;

@RunWith(Parameterized.class)
public class CalculatorTest {

	private Calculator calculator1;
	private Calculator calculator2;
	private int iValue;
	private int jValue;
	private String inputStr;
	private String wrongExpression;
	private double expected;
	private double delta;

	public CalculatorTest(int iValue, int jValue, String inputStr, String wrongExpression, double expected,
			double delta) {
		super();
		this.iValue = iValue;
		this.jValue = jValue;
		this.inputStr = inputStr;
		this.wrongExpression = wrongExpression;
		this.expected = expected;
		this.delta = delta;
	}

	@Parameters
	public static Collection<Object[]> CalculatorTestValue() {
		Object[][] obj = new Object[][] { { 5, 2, "(2*i--*(3*(2-1/2*2)-2)-10/2)*++i", "2+((4-", 25, 0.1 },
				{ 7, 3, " (-5+1/2*(2+5*2- --j))*1200", "*2/3-5+", 0, 0.1 } };
		return Arrays.asList(obj);
	}

	@Before
	public void initCalculator() {
		PolishTransform.setI(iValue);
		PolishTransform.setJ(jValue);
		calculator1 = new Calculator(new PolishTransform(inputStr).transform());
		calculator2 = new Calculator(new PolishTransform(wrongExpression).transform());
	}

	@Test
	public void calculateTest() throws WrongMathExpressionException {
		double actual = calculator1.calculate().doubleValue();
		assertEquals("Method calculate works incorrectly", expected, actual, delta);
	}

	@Test(expected = WrongMathExpressionException.class)
	public void calculatorExceptionTest() throws WrongMathExpressionException {
		Object expected = null;
		Object actual = calculator2.calculate();
		assertEquals("For wrong math expression there aren't WrongMathExpressionException", expected, actual);
	}
}
