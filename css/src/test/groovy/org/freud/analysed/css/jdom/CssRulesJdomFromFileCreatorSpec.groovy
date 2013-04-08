package org.freud.analysed.css.jdom

import org.freud.analysed.css.rule.CssRule
import org.freud.analysed.css.rule.selector.CssSelector
import spock.lang.Specification
import spock.lang.Subject

import static org.freud.analysed.css.jdom.CssTestUtil.cssDeclaration
import static org.freud.analysed.css.jdom.CssTestUtil.cssRule
import static org.freud.analysed.css.jdom.CssTestUtil.cssSelector
import static org.freud.analysed.css.jdom.CssTestUtil.matchClosuresToList
import static org.freud.analysed.css.rule.selector.CssSelector.Combinator.ADJACENT_SIBLING
import static org.freud.analysed.css.rule.selector.CssSelector.Combinator.CHILD
import static org.freud.analysed.css.rule.selector.CssSelector.Combinator.DESCENDANT
import static org.freud.analysed.css.rule.selector.CssSelector.Type.ATTRIB
import static org.freud.analysed.css.rule.selector.CssSelector.Type.CLASS
import static org.freud.analysed.css.rule.selector.CssSelector.Type.ID
import static org.freud.analysed.css.rule.selector.CssSelector.Type.PSEUDO
import static org.freud.analysed.css.rule.selector.CssSelector.Type.TAG
import static org.freud.analysed.css.rule.selector.CssSelector.Type.UNIVERSAL

class CssRulesJdomFromFileCreatorSpec extends Specification {

    @Subject
    CssRulesJdomFromFileCreator creator = new CssRulesJdomFromFileCreator()

    def 'creates CssRules from a File'() {
    given:
        File file = new File(ClassLoader.getSystemResource('CssRulesJdomFromFileCreatorSpec/example.css').file)

    when:
        List<CssRule> rules = creator.create(file) as List
    then:
        matchClosuresToList.call([ cssRule([cssSelector(CLASS, 'class', [cssDeclaration('display', 'none')])]),
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
                cssSelector(PSEUDO, CssSelector.Combinator.PSEUDO, 'hover', [cssDeclaration('color', 'FFF')])]),
        cssRule([cssSelector(TAG, 'a', [cssDeclaration('display', 'none')]),
                cssSelector(TAG, CHILD, 'b', [cssDeclaration('display', 'none')]),
                cssSelector(TAG, CHILD, 'c', [cssDeclaration('display', 'none')])]),
        cssRule([cssSelector(UNIVERSAL, null, [cssDeclaration('display', 'block')])]),
        cssRule([cssSelector(TAG, 'tag', [cssDeclaration('display', 'block')]),
                 cssSelector(CLASS, DESCENDANT, 'class', [cssDeclaration('display', 'block')])]),
        cssRule([cssSelector(TAG, 'a', [cssDeclaration('display', 'block')]),
                cssSelector(ATTRIB, CssSelector.Combinator.ATTRIB, 'href', [cssDeclaration('display', 'block')])]),
        cssRule([cssSelector(TAG, 'a', [cssDeclaration('display', 'block')]),
                cssSelector(ATTRIB, CssSelector.Combinator.ATTRIB, 'href="foo.html"', [cssDeclaration('display', 'block')])]),
        cssRule([cssSelector(CLASS, 'tfx-btn-add', [cssDeclaration('background-image', 'url ( \'../images/drop-add.gif\' ) !important')])])], rules)
    }
}
