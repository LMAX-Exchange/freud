package org.freud.analysed.javasource.jdom;

import org.antlr.runtime.ANTLRReaderStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.freud.analysed.javasource.parser.JavaLexer;
import org.freud.analysed.javasource.parser.JavaParser;
import org.freud.analysed.javasource.parser.JavaSourceTokenType;
import org.freud.core.parser.JdomTreeAdaptor;
import org.jdom.Document;

import java.io.IOException;
import java.io.Reader;

class JavaSourceJdomParser {

    static final JavaSourceTokenType[] JAVA_SOURCE_TOKEN_TYPES = JavaSourceTokenType.values();
    static final String JAVA_SOURCE_ROOT_ELEMENT_NAME = "JAVA_SOURCE";

    Document parseJavaSourceToDocument(final Reader javaSourceReader) throws RecognitionException, IOException {
        JavaParser parser = new JavaParser(new CommonTokenStream(new JavaLexer(new ANTLRReaderStream(javaSourceReader))));
        final JdomTreeAdaptor treeAdaptor = new JdomTreeAdaptor(JAVA_SOURCE_ROOT_ELEMENT_NAME, JAVA_SOURCE_TOKEN_TYPES);
        parser.setTreeAdaptor(treeAdaptor);
        parser.compilationUnit();
        return treeAdaptor.getDocument();
    }

}
