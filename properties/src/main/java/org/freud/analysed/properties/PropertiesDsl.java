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

package org.freud.analysed.properties;

import org.freud.core.FreudSource;
import org.freud.core.iterator.AnalysedObjects;
import org.freud.core.iterator.SubTypeAnalysedObjects;

import java.io.File;
import java.net.URL;
import java.util.Properties;

import static org.freud.core.FreudSource.typeOf;

public final class PropertiesDsl {

    private PropertiesDsl() {
        // static utility
    }

    @SuppressWarnings("unchecked")
    public static <T> Iterable<Property> propertyOf(Iterable<T> iterable) {
        Class type = typeOf(iterable);
        return propertyOf(new FreudSource(iterable, type));
    }

    @SuppressWarnings("unchecked")
    public static Iterable<Property> propertyOf(FreudSource source) {
        return new SubTypeAnalysedObjects<Properties, Property>(new PropertiesToPropertyCreator(), propertiesOf(source));
    }

    @SuppressWarnings("unchecked")
    public static Iterable<Properties> propertiesOf(FreudSource source) {
        if (File.class.equals(source.getType())) {
            return new AnalysedObjects<File, Properties>(new PropertiesFromFileCreator(), source.getSources());
        }
        if (URL.class.equals(source.getType())) {
            return new AnalysedObjects<URL, Properties>(new PropertiesFromUrlCreator(), source.getSources());
        }
        if (String.class.equals(source.getType())) {
            return new AnalysedObjects<String, Properties>(new PropertiesFromStringCreator(), source.getSources());
        }
        throw new UnsupportedOperationException("Unsupported conversion " + source.getType() + " to Properties");
    }
}
