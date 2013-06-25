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

package org.freud.core.listener;

import java.io.PrintWriter;

public final class PrintAnalysisListener implements AnalysisListener {
    private final PrintWriter out;

    public PrintAnalysisListener(final PrintWriter out) {
        this.out = out;
    }

    @Override
    public void failed(Object analysedObject, String details) {
        out.println("Analysis on [" + analysedObject + "] failed.");
    }

    @Override
    public void filtered(Object analysedObject, String details) {
        out.println("Analysis on [" + analysedObject + "] filtered.");
    }

    @Override
    public void passed(Object analysedObject) {
        out.println("Analysis on [" + analysedObject + "] passed.");
    }

    @Override
    public void unexpected(Object analysedObject, Exception exception) {
        out.println("Analysis got unexpected exception.");
        exception.printStackTrace(out);
    }

    @Override
    public void done() {
        // do nothing
    }
}
