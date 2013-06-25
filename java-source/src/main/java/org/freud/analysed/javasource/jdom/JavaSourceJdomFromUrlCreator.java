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

import org.freud.analysed.javasource.JavaSource;
import org.freud.core.Creator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import static org.freud.analysed.javasource.jdom.JavaSourceJdomParser.parseJavaSource;

public final class JavaSourceJdomFromUrlCreator implements Creator<URL, JavaSource> {
    @Override
    public JavaSource create(final URL source) {
        try {
            return parseJavaSource(new BufferedReader(new InputStreamReader(source.openStream())), source.toExternalForm());
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse java source " + source, e);
        }
    }
}
