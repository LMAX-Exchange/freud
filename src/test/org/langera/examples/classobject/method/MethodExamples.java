/*
 * Copyright (c) 2011.
 * This file is part of "Freud".
 *
 * Freud is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Freud is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Freud.  If not, see <http://www.gnu.org/licenses/>.
 * @author Amir Langer  langera_at_gmail_dot_com
 */

package org.langera.examples.classobject.method;

import org.junit.Test;
import org.langera.freud.core.Freud;
import org.langera.freud.core.FreudAnalyser;
import org.langera.freud.core.FreudConfigRegistry;
import org.langera.freud.core.iterator.AnalysedObjectIterator;
import org.langera.freud.optional.classobject.method.MethodFreudConfig;

import java.lang.reflect.Method;

import static org.langera.freud.core.matcher.FreudDsl.no;
import static org.langera.freud.optional.classobject.method.MethodDsl.methodAnnotation;
import static org.langera.freud.optional.classobject.method.MethodDsl.methodName;

public final class MethodExamples
{
    static
    {
        // Method is a third party class that needs a config - point ot it using a System property
        System.setProperty(Method.class.getName() + FreudConfigRegistry.FREUD_CONFIG_SUFFIX, MethodFreudConfig.class.getName());
    }

    private MethodExamples()
    {
        // a class of static methods - should not be initialised
    }

    public static FreudAnalyser allTestMethodsMustStartWithShould(final AnalysedObjectIterator<Class> iterator)
    {
        return Freud.iterateOver(Method.class).
                assertThat(methodName().matches("should.+").or(no(methodAnnotation(Test.class)))).within(iterator);
    }

}
