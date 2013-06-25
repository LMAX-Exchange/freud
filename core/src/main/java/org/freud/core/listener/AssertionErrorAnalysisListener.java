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

public final class AssertionErrorAnalysisListener implements AnalysisListener {
    @Override
    public void failed(Object analysedObject, String details) {
        throw new AssertionError("Analysis on [" + analysedObject + "] failed." +
                                         (String.valueOf(details)));
    }

    @Override
    public void filtered(Object analysedObject, String details) {
        // do nothing
    }

    @Override
    public void passed(Object analysedObject) {
        // do nothing
    }

    @Override
    public void unexpected(Object analysedObject, Exception exception) {
        throw new AssertionError("Unexpected exception [" + exception + "]" +
                                         ((analysedObject == null) ? "" : " while analysing [" + analysedObject + "]."));
    }

    @Override
    public void done() {
        // do nothing
    }
}
