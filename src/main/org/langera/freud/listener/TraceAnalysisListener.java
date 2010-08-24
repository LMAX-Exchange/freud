package org.langera.freud.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

public final class TraceAnalysisListener implements AnalysisListener
{
    private static final Log LOGGER = LogFactory.getLog(TraceAnalysisListener.class);

    public void failed(Object toBeAnalysed, Matcher typeSafeMatcher)
    {
        LOGGER.trace("Analysis on [" + toBeAnalysed + "] failed.");
    }

    public void filtered(Object toBeAnalysed, Matcher typeSafeMatcher)
    {
        LOGGER.trace("Analysis on [" + toBeAnalysed + "] filtered.");
    }

    public void passed(Object toBeAnalysed, Matcher typeSafeMatcher)
    {
        LOGGER.trace("Analysis on [" + toBeAnalysed + "] passed.");
    }

    public void unexpected(Object toBeAnalysed, Exception exception)
    {
        LOGGER.trace("Analysis got unexpected exception.", exception);
    }
}
