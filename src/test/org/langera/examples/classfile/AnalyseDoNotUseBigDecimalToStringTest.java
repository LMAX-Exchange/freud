package org.langera.examples.classfile;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.langera.freud.core.FreudAnalyser;
import org.langera.freud.core.iterator.AnalysedObjectIterator;
import org.langera.freud.core.iterator.resource.ResourceIterators;
import org.langera.freud.core.listener.AnalysisListenerStub;
import org.langera.freud.optional.classfile.ClassFile;
import org.langera.freud.optional.classfile.parser.asm.AsmClassFileParser;

import java.math.BigDecimal;

import static org.langera.examples.classfile.ClassFileTestMatchers.methodNamed;

/**
 * Created by IntelliJ IDEA.
 * User: langera
 * Date: 28-Oct-2008
 * Time: 00:04:55
 */
public class AnalyseDoNotUseBigDecimalToStringTest
{
    private AnalysisListenerStub listener;
    private AsmClassFileParser parser;

    @Test
    public void shouldFailWhenBigDecimalToStringIsUsed() throws Exception
    {
        FreudAnalyser analyser = ClassFileExamples.doNotUseBigDecimalToString(
                resource("org/langera/examples/classfile/AnalyseDoNotUseBigDecimalToStringTest$UseOfBigDecimalToString"));
        analyser.analyse(listener);

        Assert.assertEquals(2, listener.getTotalObjectsAnalysed());
        listener.assertFailed(methodNamed("useIt"));
    }

    @Test
    public void shouldFailWhenBigDecimalToStringIsUsedImplicitely() throws Exception
    {
        FreudAnalyser analyser = ClassFileExamples.doNotUseBigDecimalToString(
                resource("org/langera/examples/classfile/AnalyseDoNotUseBigDecimalToStringTest$UseOfBigDecimalToStringImplicitely"));
        analyser.analyse(listener);

        Assert.assertEquals(2, listener.getTotalObjectsAnalysed());
        listener.assertFailed(methodNamed("useIt"));
    }

    @Test
    public void shouldPassWhenBigDecimalToStringIsNotUsed() throws Exception
    {
        FreudAnalyser analyser = ClassFileExamples.doNotUseBigDecimalToString(
                resource("org/langera/examples/classfile/AnalyseDoNotUseBigDecimalToStringTest$UseOfBigDecimalToPlainString"));

        analyser.analyse(listener);

        Assert.assertEquals(2, listener.getTotalObjectsAnalysed());
        listener.assertPassed(methodNamed("useIt"));
    }

    @Before
    public void setUp() throws Exception
    {
        listener = new AnalysisListenerStub();
        parser = new AsmClassFileParser(null);
    }

    private AnalysedObjectIterator<ClassFile> resource(final String className)
    {
        return ResourceIterators.<ClassFile>fileResourceIterator(
                parser, ClassLoader.getSystemClassLoader().
                getResource(className + ".class").toExternalForm().substring("file:".length()));
    }

    ////////////////////////////////////////////////

    private static final class UseOfBigDecimalToString
    {
        public String useIt(final BigDecimal bigDecimal)
        {
            return bigDecimal.toString();
        }
    }

    private static final class UseOfBigDecimalToStringImplicitely
    {
        public String useIt(final BigDecimal bigDecimal)
        {
            return "" + bigDecimal;
        }
    }

    private static final class UseOfBigDecimalToPlainString
    {
        public String useIt(final BigDecimal bigDecimal)
        {
            return bigDecimal.toPlainString();
        }
    }
}
