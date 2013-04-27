package org.freud.analysed.css.jdom

import org.freud.analysed.css.rule.CssRule
import org.freud.analysed.css.rule.declaration.CssDeclaration
import org.freud.analysed.css.rule.selector.CssSelector

import static org.freud.analysed.css.rule.selector.CssSelector.Combinator.ADJACENT_SIBLING
import static org.freud.analysed.css.rule.selector.CssSelector.Combinator.ATTRIB
import static org.freud.analysed.css.rule.selector.CssSelector.Combinator.CHILD
import static org.freud.analysed.css.rule.selector.CssSelector.Combinator.DESCENDANT
import static org.freud.analysed.css.rule.selector.CssSelector.Combinator.PSEUDO
import static org.freud.analysed.css.rule.selector.CssSelector.Type.CLASS
import static org.freud.analysed.css.rule.selector.CssSelector.Type.ID
import static org.freud.analysed.css.rule.selector.CssSelector.Type.TAG
import static org.freud.analysed.css.rule.selector.CssSelector.Type.UNIVERSAL

class CssParsingFixture {

    static boolean exampleCssfileParsedBy(Closure<Iterable<CssRule>> parser) {
        matchClosuresToList.call(EXPECTED_EXAMPLE_CSS_RULES, parser.call(EXAMPLE_CSS) as List)
    }

    private static Closure<Boolean> matchClosuresToList = {
        List<Closure<Boolean>> closures, List list ->
        int index = 0
        return closures.size() == list.size() && closures.every { Closure<Boolean> closure ->
            closure.call(list[index++])
        }
    }

    private static final File EXAMPLE_CSS = new File(ClassLoader.getSystemResource('CssRulesJdomFromFileCreatorSpec/example.css').file)

    private static final List<Closure<Boolean>> EXPECTED_EXAMPLE_CSS_RULES = [cssRule([cssSelector(CLASS, 'class', [cssDeclaration('display', 'none')])]),
            cssRule([cssSelector(TAG, 'table', [cssDeclaration('display', 'none')])]),
            cssRule([cssSelector(TAG, 'tr', [cssDeclaration('display', 'none')])]),
            cssRule([cssSelector(TAG, 'td', [cssDeclaration('display', 'none')])]),
            cssRule([cssSelector(TAG, 'table', [cssDeclaration('display', 'none')]),
                    cssSelector(TAG, DESCENDANT, 'tr', [cssDeclaration('display', 'none')]),
                    cssSelector(TAG, DESCENDANT, 'td', [cssDeclaration('display', 'none')])]),
            cssRule([cssSelector(TAG, 'a', [cssDeclaration('display', 'none'), cssDeclaration('color', 'red')]),
                    cssSelector(ID, ADJACENT_SIBLING, 'my-link-id', [cssDeclaration('display', 'none'), cssDeclaration('color', 'red')]),
                    cssSelector(ID, CHILD, 'myOtherLinkId', [cssDeclaration('display', 'none'), cssDeclaration('color', 'red')])]),
            cssRule([cssSelector(TAG, 'a', [cssDeclaration('color', 'FFF')]),
                    cssSelector(CssSelector.Type.PSEUDO, PSEUDO, 'hover', [cssDeclaration('color', 'FFF')])]),
            cssRule([cssSelector(TAG, 'a', [cssDeclaration('display', 'none')]),
                    cssSelector(TAG, CHILD, 'b', [cssDeclaration('display', 'none')]),
                    cssSelector(TAG, CHILD, 'c', [cssDeclaration('display', 'none')])]),
            cssRule([cssSelector(UNIVERSAL, null, [cssDeclaration('display', 'block')])]),
            cssRule([cssSelector(TAG, 'tag', [cssDeclaration('display', 'block')]),
                    cssSelector(CLASS, DESCENDANT, 'class', [cssDeclaration('display', 'block')])]),
            cssRule([cssSelector(TAG, 'a', [cssDeclaration('display', 'block')]),
                    cssSelector(CssSelector.Type.ATTRIB, ATTRIB, 'href', [cssDeclaration('display', 'block')])]),
            cssRule([cssSelector(TAG, 'a', [cssDeclaration('display', 'block')]),
                    cssSelector(CssSelector.Type.ATTRIB, ATTRIB, 'href="foo.html"', [cssDeclaration('display', 'block')])]),
            cssRule([cssSelector(CLASS, 'tfx-btn-add', [cssDeclaration('background-image', 'url ( \'../images/drop-add.gif\' ) !important')])])]



    static Closure<Boolean> cssRule(List<Closure<Boolean>> selectorsConditions) {
        return { CssRule rule ->
            matchClosuresToList.call(selectorsConditions, rule.cssSelectors)
        }
    }

    static Closure<Boolean> cssSelector(CssSelector.Type type, String selectorString,
                                        List<Closure<Boolean>> declarationsConditions) {
        return cssSelector(type, null, selectorString, declarationsConditions)
    }

    static Closure<Boolean> cssSelector(CssSelector.Type type,
                                        CssSelector.Combinator combinator,
                                        String selectorString,
                                        List<Closure<Boolean>> declarationsConditions) {
        return { CssSelector selector ->
            selector.type == type && selector.selectorString == selectorString && selector.combinator == combinator &&
                    matchClosuresToList.call(declarationsConditions, selector.cssRuleForSelector.cssDeclarations)
        }
    }

    static Closure<Boolean> cssDeclaration(String key, String value) {
        return { CssDeclaration declaration ->
            declaration.key == key && declaration.value == value
        }
    }
}
