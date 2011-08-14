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

package org.langera.freud.core.listener;

import org.hamcrest.Matcher;


public final class AnalysisListenerChain implements AnalysisListener
{
    private AnalysisListener[] listeners;

    public AnalysisListenerChain(AnalysisListener... listeners)
    {
        this.listeners = listeners;
    }

    @Override
    public void failed(Object analysedObject, Matcher typeSafeMatcher)
    {
        for (int i = 0, size = listeners.length; i < size; i++)
        {
            AnalysisListener listener = listeners[i];
            listener.failed(analysedObject, typeSafeMatcher);
        }
    }

    @Override
    public void filtered(Object analysedObject, Matcher typeSafeMatcher)
    {
        for (int i = 0, size = listeners.length; i < size; i++)
        {
            AnalysisListener listener = listeners[i];
            listener.filtered(analysedObject, typeSafeMatcher);
        }
    }

    @Override
    public void passed(Object analysedObject, Matcher typeSafeMatcher)
    {
        for (int i = 0, size = listeners.length; i < size; i++)
        {
            AnalysisListener listener = listeners[i];
            listener.passed(analysedObject, typeSafeMatcher);
        }
    }

    @Override
    public void unexpected(Object analysedObject, Exception exception)
    {
        for (int i = 0, size = listeners.length; i < size; i++)
        {
            AnalysisListener listener = listeners[i];
            listener.unexpected(analysedObject, exception);
        }
    }

    @Override
    public void done()
    {
        for (int i = 0, size = listeners.length; i < size; i++)
        {
            AnalysisListener listener = listeners[i];
            listener.done();
        }
    }
}
