package org.langera.freud.listener;

import org.hamcrest.Matcher;
import org.langera.freud.AnalysisListener;


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

public final class AnalysisListenerChain implements AnalysisListener
{
    private AnalysisListener[] listeners;

    public AnalysisListenerChain(AnalysisListener... listeners)
    {
        this.listeners = listeners;
    }

    public void failed(Object toBeAnalysed, Matcher typeSafeMatcher)
    {
        for (int i = 0, size = listeners.length; i < size; i++)
        {
            AnalysisListener listener = listeners[i];
            listener.failed(toBeAnalysed, typeSafeMatcher);
        }
    }

    public void filtered(Object toBeAnalysed, Matcher typeSafeMatcher)
    {
        for (int i = 0, size = listeners.length; i < size; i++)
        {
            AnalysisListener listener = listeners[i];
            listener.filtered(toBeAnalysed, typeSafeMatcher);
        }
    }

    public void passed(Object toBeAnalysed, Matcher typeSafeMatcher)
    {
        for (int i = 0, size = listeners.length; i < size; i++)
        {
            AnalysisListener listener = listeners[i];
            listener.passed(toBeAnalysed, typeSafeMatcher);
        }
    }

    public void unexpected(Object toBeAnalysed, Exception exception)
    {
        for (int i = 0, size = listeners.length; i < size; i++)
        {
            AnalysisListener listener = listeners[i];
            listener.unexpected(null, exception);
        }
    }
}
