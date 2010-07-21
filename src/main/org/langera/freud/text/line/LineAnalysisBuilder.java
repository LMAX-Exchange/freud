package org.langera.freud.text.line;

import org.langera.freud.AbstractAnalysisBuilder;
import org.langera.freud.dsl.NumericOperatorDsl;
import org.langera.freud.dsl.ReadableDsl;
import org.langera.freud.text.line.assertion.LineLengthCalculation;
import org.langera.freud.text.line.assertion.LineNumberCalculation;
import org.langera.freud.text.line.assertion.LineRegexMatchAnalysisAssertionAdapter;

/**
 * This file is part of "Freud".
 * <p/>
 * Freud is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * Freud is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with Freud.  If not, see <http://www.gnu.org/licenses/>.
 *
 * @author Amir Langer  langera_at_gmail_dot_com
 */

public final class LineAnalysisBuilder extends AbstractAnalysisBuilder<LineAnalysisBuilder, TextLine>
        implements LineDsl
{
    public Class<TextLine> getType()
    {
        return TextLine.class;
    }

    public ReadableDsl<LineAnalysisBuilder> line()
    {
        setRegexAssertionAdapterClass(LineRegexMatchAnalysisAssertionAdapter.class);
        return (ReadableDsl<LineAnalysisBuilder>) trueAssertion();
    }

    public NumericOperatorDsl<LineAnalysisBuilder> lineLength()
    {
        setCalculation(LineLengthCalculation.getInstance());
        return this;
    }

    public NumericOperatorDsl<LineAnalysisBuilder> lineNumber()
    {
        setCalculation(LineNumberCalculation.getInstance());
        return this;
    }
}
