package org.langera.freud.text.line.assertion;

import org.langera.freud.AnalysisCalculation;
import org.langera.freud.text.line.TextLine;

/**
 *   This file is part of "Freud".
 *
 *   Freud is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU Lesser General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   Freud is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with Freud.  If not, see <http://www.gnu.org/licenses/>.
 *
 *   @author Amir Langer  langera_at_gmail_dot_com
**/

public final class LineLengthCalculation implements AnalysisCalculation<TextLine>
{
    private static final LineLengthCalculation SINGLETON = new LineLengthCalculation();

    public static LineLengthCalculation getInstance()
    {
        return SINGLETON;
    }
    private LineLengthCalculation()
    {
    }

    public int analyse(TextLine toBeAnalysed)
    {
        return toBeAnalysed.getLine().length();
    }

    @Override
    public String toString()
    {
        return "LineLength()";
    }
}
