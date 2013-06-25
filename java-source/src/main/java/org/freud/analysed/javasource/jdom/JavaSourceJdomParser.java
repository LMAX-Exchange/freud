/*
 * Copyright 2013 LMAX Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

    private JavaSourceJdomParser() {
        // static utility
    }

    static JavaSourceJdom parseJavaSource(final Reader reader, final String identifier) throws RecognitionException, IOException {
        Document root = parseJavaSourceToDocument(reader);
        return new JavaSourceJdom(root, identifier);
    }

    private static Document parseJavaSourceToDocument(final Reader javaSourceReader) throws RecognitionException, IOException {
        JavaParser parser = new JavaParser(new CommonTokenStream(new JavaLexer(new ANTLRReaderStream(javaSourceReader))));
        final JdomTreeAdaptor treeAdaptor = new JdomTreeAdaptor(JAVA_SOURCE_ROOT_ELEMENT_NAME, JAVA_SOURCE_TOKEN_TYPES);
        parser.setTreeAdaptor(treeAdaptor);
        parser.compilationUnit();
        return treeAdaptor.getDocument();
    }

}
