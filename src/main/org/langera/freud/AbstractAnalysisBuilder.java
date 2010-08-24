package org.langera.freud;

import org.hamcrest.Matcher;
import org.langera.freud.dsl.*;
import org.langera.freud.util.regex.RegexMatchAnalysisAssertion;
import org.langera.freud.util.regex.RegexMatchAnalysisAssertionAdapter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

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

public abstract class AbstractAnalysisBuilder<ThisDsl extends BooleanOperatorDsl, T>
        implements Builder<T>,
        BooleanOperatorDsl<ThisDsl>,
        UnaryBooleanOperatorDsl<ThisDsl>,
// TODO       MatchableDsl<ThisDsl>,
        ReadableDsl<ThisDsl>,
        NumericOperatorDsl<ThisDsl>
{
    private Constructor<? extends RegexMatchAnalysisAssertionAdapter> currentRegexAssertionConstructor = null;
    private AnalysisCalculation<T> currentCalculation = null;
    private Matcher<T> currentAssertion = null;
    private List<Builder> filterBuilderList = new LinkedList<Builder>();

    ///////////////////////////////////////////////////////////////////////
    // DSL

    protected BooleanOperatorDsl<ThisDsl> trueAssertion()
    {
        setAssertion(AnalysisUtils.<T>trueAssertion());
        return this;
    }

    @SuppressWarnings("unchecked")
    public BooleanOperatorDsl<ThisDsl> and(final BooleanOperatorDsl<ThisDsl> dsl)
    {
        // cheat IDEs by downcasting
        // (.. and making sure every DSL is also a builder). 
        updateAssertion(
                AnalysisUtils.andOperatorAssertion(currentAssertion, ((Builder) dsl).buildAssertion()));
        return this;
    }

    @SuppressWarnings("unchecked")
    public BooleanOperatorDsl<ThisDsl> or(final BooleanOperatorDsl<ThisDsl> dsl)
    {
        // cheat IDEs by downcasting
        // (.. and making sure every DSL is also a builder).
        updateAssertion(
                AnalysisUtils.orOperatorAssertion(currentAssertion, ((Builder) dsl).buildAssertion()));
        return this;
    }

    @SuppressWarnings("unchecked")
    public BooleanOperatorDsl<ThisDsl> no(BooleanOperatorDsl<ThisDsl> dsl)
    {
        // cheat IDEs by downcasting
        // (.. and making sure every DSL is also a builder).
        setAssertion(AnalysisUtils.negatedAssertion(((Builder) dsl).buildAssertion()));
        return this;
    }

    ///////////////////////////////////////////////////////////////////////
    // Readable DSL

    public BooleanOperatorDsl<ThisDsl> contains(String regex)
    {
        buildRegexAnalysisAssertion(regex, Boolean.FALSE);
        return this;
    }

    public BooleanOperatorDsl<ThisDsl> matches(String regex)
    {
        buildRegexAnalysisAssertion(regex, Boolean.TRUE);
        return this;
    }

    @SuppressWarnings("unchecked")
    private void buildRegexAnalysisAssertion(final String regex, final Boolean fullMatch)
    {
        try
        {
            setAssertion((Matcher<T>) new RegexMatchAnalysisAssertion(regex, fullMatch,
                                                                      currentRegexAssertionConstructor.newInstance()));
        }
        catch (InvocationTargetException e)
        {
            throw new RuntimeException("internal", e.getTargetException());
        }
        catch (InstantiationException e)
        {
            throw new RuntimeException("internal", e);
        }
        catch (IllegalAccessException e)
        {
            throw new RuntimeException("internal", e);
        }
    }

    ///////////////////////////////////////////////////////////////////////
    // Numeric Operator DSL

    @SuppressWarnings("unchecked")
    public NumericOperatorDsl<ThisDsl> add(NumericOperatorDsl<ThisDsl> dsl)
    {
        // cheat IDEs by downcasting
        // (.. and making sure every DSL is also a builder).        
        updateCalculation(AnalysisUtils.addOperatorCalculation(currentCalculation,
                                                               ((Builder) dsl).buildCalculation()));
        return this;
    }

    public NumericOperatorDsl<ThisDsl> numberOf(NumericOperatorDsl<ThisDsl> thisDslNumericOperatorDsl)
    {
        return this;
    }

    public NumericOperatorDsl<ThisDsl> add(int value)
    {
        updateCalculation(AnalysisUtils.addOperatorCalculation(currentCalculation,
                                                               AnalysisUtils.<T>noOpCalculation(value)));
        return this;
    }

    @SuppressWarnings("unchecked")
    public NumericOperatorDsl<ThisDsl> multiply(NumericOperatorDsl<ThisDsl> dsl)
    {
        // cheat IDEs by downcasting
        // (.. and making sure every DSL is also a builder).
        updateCalculation(AnalysisUtils.multiplyOperatorCalculation(currentCalculation,
                                                                    ((Builder) dsl).buildCalculation()));
        return this;
    }

    public NumericOperatorDsl<ThisDsl> multiply(int value)
    {
        updateCalculation(AnalysisUtils.multiplyOperatorCalculation(currentCalculation,
                                                                    AnalysisUtils.<T>noOpCalculation(value)));
        return this;
    }

    @SuppressWarnings("unchecked")
    public NumericOperatorDsl<ThisDsl> subtract(NumericOperatorDsl<ThisDsl> dsl)
    {
        // cheat IDEs by downcasting
        // (.. and making sure every DSL is also a builder).
        updateCalculation(AnalysisUtils.subtractOperatorCalculation(currentCalculation,
                                                                    ((Builder) dsl).buildCalculation()));
        return this;
    }

    public NumericOperatorDsl<ThisDsl> subtract(int value)
    {
        updateCalculation(AnalysisUtils.subtractOperatorCalculation(currentCalculation,
                                                                    AnalysisUtils.<T>noOpCalculation(value)));
        return this;
    }

    @SuppressWarnings("unchecked")
    public BooleanOperatorDsl<ThisDsl> equalTo(NumericOperatorDsl<ThisDsl> dsl)
    {
        if (currentCalculation == null)
        {
            throw new AnalysisInitialisationException("Cannot build numeric assertion for == " + dsl);
        }
        // cheat IDEs by downcasting
        // (.. and making sure every DSL is also a builder).
        setAssertion(AnalysisUtils.equalOperatorAssertion(currentCalculation,
                                                          ((Builder) dsl).buildCalculation()));
        return this;
    }

    public BooleanOperatorDsl<ThisDsl> equalTo(int value)
    {
        if (currentCalculation == null)
        {
            throw new AnalysisInitialisationException("Cannot build numeric assertion for == " + value);
        }
        setAssertion(AnalysisUtils.equalOperatorAssertion(currentCalculation,
                                                          AnalysisUtils.<T>noOpCalculation(value)));
        return this;
    }

    @SuppressWarnings("unchecked")
    public BooleanOperatorDsl<ThisDsl> greaterThanOrEqualTo(NumericOperatorDsl<ThisDsl> dsl)
    {
        if (currentCalculation == null)
        {
            throw new AnalysisInitialisationException("Cannot build numeric assertion for >= " + dsl);
        }
        // cheat IDEs by downcasting
        // (.. and making sure every DSL is also a builder).
        setAssertion(AnalysisUtils.greaterThanEqualOperatorAssertion(currentCalculation,
                                                                     ((Builder) dsl).buildCalculation()));

        return this;
    }

    public BooleanOperatorDsl<ThisDsl> greaterThanOrEqualTo(int value)
    {
        if (currentCalculation == null)
        {
            throw new AnalysisInitialisationException("Cannot build numeric assertion for >= " + value);
        }
        setAssertion(AnalysisUtils.greaterThanEqualOperatorAssertion(currentCalculation,
                                                                     AnalysisUtils.<T>noOpCalculation(value)));
        return this;
    }

    @SuppressWarnings("unchecked")
    public BooleanOperatorDsl<ThisDsl> lessThanOrEqualTo(NumericOperatorDsl<ThisDsl> dsl)
    {
        if (currentCalculation == null)
        {
            throw new AnalysisInitialisationException("Cannot build numeric assertion for =< " + dsl);
        }
        // cheat IDEs by downcasting
        // (.. and making sure every DSL is also a builder).
        setAssertion(AnalysisUtils.lessThanEqualOperatorAssertion(currentCalculation,
                                                                  ((Builder) dsl).buildCalculation()));
        return this;
    }

    public BooleanOperatorDsl<ThisDsl> lessThanOrEqualTo(int value)
    {
        if (currentCalculation == null)
        {
            throw new AnalysisInitialisationException("Cannot build numeric assertion for =< " + value);
        }
        setAssertion(AnalysisUtils.lessThanEqualOperatorAssertion(currentCalculation,
                                                                  AnalysisUtils.<T>noOpCalculation(value)));
        return this;
    }

    @SuppressWarnings("unchecked")
    public BooleanOperatorDsl<ThisDsl> greaterThan(NumericOperatorDsl<ThisDsl> dsl)
    {
        if (currentCalculation == null)
        {
            throw new AnalysisInitialisationException("Cannot build numeric assertion for > " + dsl);
        }
        // cheat IDEs by downcasting
        // (.. and making sure every DSL is also a builder).
        setAssertion(AnalysisUtils.greaterThanOperatorAssertion(currentCalculation,
                                                                ((Builder) dsl).buildCalculation()));
        return this;
    }

    public BooleanOperatorDsl<ThisDsl> greaterThan(int value)
    {
        if (currentCalculation == null)
        {
            throw new AnalysisInitialisationException("Cannot build numeric assertion for > " + value);
        }
        setAssertion(AnalysisUtils.greaterThanOperatorAssertion(currentCalculation,
                                                                AnalysisUtils.<T>noOpCalculation(value)));
        return this;
    }

    @SuppressWarnings("unchecked")
    public BooleanOperatorDsl<ThisDsl> lessThan(NumericOperatorDsl<ThisDsl> dsl)
    {
        if (currentCalculation == null)
        {
            throw new AnalysisInitialisationException("Cannot build numeric assertion for < " + dsl);
        }
        // cheat IDEs by downcasting
        // (.. and making sure every DSL is also a builder).
        setAssertion(AnalysisUtils.lessThanOperatorAssertion(currentCalculation,
                                                             ((Builder) dsl).buildCalculation()));
        return this;
    }

    public BooleanOperatorDsl<ThisDsl> lessThan(int value)
    {
        if (currentCalculation == null)
        {
            throw new AnalysisInitialisationException("Cannot build numeric assertion for < " + value);
        }
        setAssertion(AnalysisUtils.lessThanOperatorAssertion(currentCalculation,
                                                             AnalysisUtils.<T>noOpCalculation(value)));
        return this;
    }

///////////////////////////////////////////////////////////////////////
    // Hierarchy DSL

    public FilterDsl of(BooleanOperatorDsl dsl)
    {
        filterBuilderList.add((Builder) dsl);
        return this;
    }

    public Matcher<T> buildAssertion()
    {
        return currentAssertion;
    }

    public AnalysisCalculation<T> buildCalculation()
    {
        return currentCalculation;
    }

    public List<Builder> getFilterBuilderList()
    {
        return filterBuilderList;
    }

    ///////////////////////////////////////////////////////////////////////
    // currentAssertion management

    protected void setAssertion(Matcher<T> assertion)
    {
        this.currentAssertion = assertion;
    }

    protected void setCalculation(AnalysisCalculation<T> calculation)
    {
        this.currentCalculation = calculation;
    }

    protected void setRegexAssertionAdapterClass(Class<? extends RegexMatchAnalysisAssertionAdapter> currentRegexAssertionClass)
    {
        try
        {
            this.currentRegexAssertionConstructor = currentRegexAssertionClass.getConstructor();
        }
        catch (NoSuchMethodException e)
        {
            throw new RuntimeException("internal", e);
        }
    }

    private void updateAssertion(Matcher<T> assertion)
    {
        if (currentAssertion == null)
        {
            throw new AnalysisInitialisationException("Cannot build assertion " + assertion);
        }
        this.currentAssertion = assertion;
    }

    private void updateCalculation(AnalysisCalculation<T> calculation)
    {
        if (currentCalculation == null)
        {
            throw new AnalysisInitialisationException("Cannot build calculation " + calculation);
        }
        this.currentCalculation = calculation;
    }
}
