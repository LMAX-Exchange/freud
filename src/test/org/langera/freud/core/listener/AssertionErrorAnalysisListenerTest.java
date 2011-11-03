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

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.startsWith;

@SuppressWarnings({"ThrowableInstanceNeverThrown"})
public class AssertionErrorAnalysisListenerTest
{


    @Test
    public void shouldThrowAssertionErrorOnlyOnFailureOrUnexpected() throws Exception
    {
        AssertionErrorAnalysisListener listener = new AssertionErrorAnalysisListener();

        listener.passed(null, null);
        listener.filtered(null, null);
        
        try
        {
            listener.failed(null, null);
            Assert.fail();
        } catch (AssertionError e)
        {
            Assert.assertThat(e.getMessage(), startsWith("Analysis on [null] failed."));
        }

        try
        {
            listener.unexpected(null, new Exception("foo"));
            Assert.fail();
        } catch (AssertionError e)
        {
            Assert.assertEquals("Unexpected exception [java.lang.Exception: foo]", e.getMessage());
        }
    }
}
