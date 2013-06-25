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

import org.freud.core.Creator;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

public final class PropertiesFromStringCreator implements Creator<String, Properties> {

    @Override
    public Properties create(final String source) {
        Properties properties = new Properties();
        try {
            properties.load(new StringReader(source));
        }
        catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        return properties;
    }
}
