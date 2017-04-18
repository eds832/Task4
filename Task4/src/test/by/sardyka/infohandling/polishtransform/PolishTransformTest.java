package test.by.sardyka.infohandling.polishtransform;

import static org.junit.Assert.assertTrue;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import by.sardyka.infohandling.polishtransform.PolishTransform;

@RunWith(Parameterized.class)
public class PolishTransformTest {

	private PolishTransform transformer;
	private int iValue;
	private int jValue;
	private String inputStr;
	private String resultStr;

	public PolishTransformTest(int iValue, int jValue, String inputStr, String resultStr) {
		super();
		this.iValue = iValue;
		this.jValue = jValue;
		this.inputStr = inputStr;
		this.resultStr = resultStr;
	}

	@Parameters
	public static Collection<Object[]> PolishTransformTestValue() {
		Object[][] obj = new Object[][] { { 5, 2, "(2*i--*(3*(2-1/2*2)-2)-10/2)*++i", "25*3212/2*-*2-*102/-5*" },
				{ 7, 3, "    (-5+1/2*(2+5*2- --j))*1200", "    5_12/252*+ 2-*+1200*" } };
		return Arrays.asList(obj);
	}

	@Before
	public void initPolishTransform() {
		PolishTransform.setI(iValue);
		PolishTransform.setJ(jValue);
		transformer = new PolishTransform(inputStr);
	}

	@Test
	public void transformTest() throws IndexOutOfBoundsException, NullPointerException {
		ArrayDeque<String> d = transformer.transform();
		String result = d.removeLast() + d.removeLast() + d.removeLast() + d.removeLast() + d.removeLast()
				+ d.removeLast() + d.removeLast() + d.removeLast() + d.removeLast() + d.removeLast() + d.removeLast()
				+ d.removeLast() + d.removeLast() + d.removeLast() + d.removeLast()
				+ d.removeLast() + d.removeLast() + d.removeLast() + d.removeLast() + d.removeLast() + d.removeLast();
		boolean actual = result.equals(resultStr);
		assertTrue("Method transform() works incorrectly", actual);
	}
}
