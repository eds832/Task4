package test.by.sardyka.infohandling.infosuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import test.by.sardyka.infohandling.calculator.CalculatorTest;
import test.by.sardyka.infohandling.datareader.DataReaderTest;
import test.by.sardyka.infohandling.datawriter.DataWriterTest;
import test.by.sardyka.infohandling.functionality.FunctionalityTest;
import test.by.sardyka.infohandling.parser.LexemeParserTest;
import test.by.sardyka.infohandling.parser.ParagraphParserTest;
import test.by.sardyka.infohandling.parser.SentenceParserTest;
import test.by.sardyka.infohandling.parser.TextParserTest;
import test.by.sardyka.infohandling.polishtransform.PolishTransformTest;

@Suite.SuiteClasses( { DataReaderTest.class, DataWriterTest.class, LexemeParserTest.class,
	SentenceParserTest.class, ParagraphParserTest.class, TextParserTest.class,
	PolishTransformTest.class, CalculatorTest.class, FunctionalityTest.class} )
@RunWith(Suite.class)
public class InfoSuite {
}
