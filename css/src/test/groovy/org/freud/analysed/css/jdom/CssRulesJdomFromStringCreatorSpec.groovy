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



package org.freud.analysed.css.jdom


import org.freud.analysed.css.rule.CssRule
import spock.lang.Specification
import spock.lang.Subject

import static org.freud.analysed.css.CssParsingFixture.cssDeclaration
import static org.freud.analysed.css.CssParsingFixture.cssRule
import static org.freud.analysed.css.CssParsingFixture.cssSelector
import static org.freud.analysed.css.rule.selector.CssSelector.Combinator.DESCENDANT
import static org.freud.analysed.css.rule.selector.CssSelector.Type.CLASS
import static org.freud.analysed.css.rule.selector.CssSelector.Type.ID
import static org.freud.analysed.css.rule.selector.CssSelector.Type.TAG
import static org.langera.spock.SpockExtension.matches

class CssRulesJdomFromStringCreatorSpec extends Specification {

    @Subject
    CssRulesJdomFromStringCreator creator = new CssRulesJdomFromStringCreator()

    def 'creates CssRules from a String'() {
    when:
        List<CssRule> rules = creator.create(
                '''
                tag .class {
                 display: none;
                }
                #id .class {
                 display: none;
                }
                #id {
                 display: none;
                } ''') as List
    then:

        matches([cssRule([cssSelector(TAG, 'tag', [cssDeclaration('display', 'none')]),
                                 cssSelector(CLASS, DESCENDANT, 'class', [cssDeclaration('display', 'none')])]),
                        cssRule([cssSelector(ID, 'id', [cssDeclaration('display', 'none')]),
                                        cssSelector(CLASS, DESCENDANT, 'class', [cssDeclaration('display', 'none')])]),
                        cssRule([cssSelector(ID, 'id', [cssDeclaration('display', 'none')])]),
                ]).call(rules)
    }
}
