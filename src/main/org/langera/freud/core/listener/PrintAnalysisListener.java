package org.langera.freud.core.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hamcrest.Matcher;

import java.io.PrintWriter;

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

public final class PrintAnalysisListener implements AnalysisListener
{
    private final PrintWriter out;

    public PrintAnalysisListener(final PrintWriter out)
    {
        this.out = out;
    }

    @Override
    public void failed(Object analysedObject, Matcher typeSafeMatcher)
    {
        out.println("Analysis on [" + analysedObject + "] failed.");
    }

    @Override
    public void filtered(Object analysedObject, Matcher typeSafeMatcher)
    {
        out.println("Analysis on [" + analysedObject + "] filtered.");
    }

    @Override
    public void passed(Object analysedObject, Matcher typeSafeMatcher)
    {
        out.println("Analysis on [" + analysedObject + "] passed.");
    }

    @Override
    public void unexpected(Object analysedObject, Exception exception)
    {
        out.println("Analysis got unexpected exception.");
        exception.printStackTrace(out);
    }

    @Override
    public void done()
    {
        // do nothing
    }
}
