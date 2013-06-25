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

package org.freud.core.parser;

import org.jdom.DataConversionException;
import org.jdom.Element;

import java.util.Comparator;

import static org.freud.core.parser.JdomTreeAdaptor.LINE_NUMBER_ATTR;

public final class JdomTreePositionComparator implements Comparator<Element> {
    private static final JdomTreePositionComparator SINGLETON = new JdomTreePositionComparator();

    public static JdomTreePositionComparator getInstance() {
        return SINGLETON;
    }

    private JdomTreePositionComparator() {
    }

    public int compare(Element element1, Element element2) {
        try {
            int lineNumber1 = element1.getAttribute(LINE_NUMBER_ATTR).getIntValue();
            int lineNumber2 = element2.getAttribute(LINE_NUMBER_ATTR).getIntValue();
            if (lineNumber1 == lineNumber2) {
                int colNumber1 = element1.getAttribute(JdomTreeAdaptor.COL_NUMBER_ATTR).getIntValue();
                int colNumber2 = element2.getAttribute(JdomTreeAdaptor.COL_NUMBER_ATTR).getIntValue();
                return (colNumber1 < colNumber2) ? -1 : (colNumber1 == colNumber2) ? 0 : 1;
            }
            return (lineNumber1 < lineNumber2) ? -1 : 1;
        }
        catch (DataConversionException e) {
            throw new IllegalStateException("unexpected", e);
        }
    }
}
