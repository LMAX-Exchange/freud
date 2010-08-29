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

public abstract class AbstractAnalysisBuilder<DSL extends BooleanOperatorDsl, T>
        implements Builder<T>,
        UnaryBooleanOperatorDsl<DSL>,
        CommonDsl<DSL, T>,
        NumericOperatorDsl<DSL>
{
    private Constructor<? extends RegexMatchAnalysisAssertionAdapter> currentRegexAssertionConstructor = null;
    private AnalysisCalculation<T> currentCalculation = null;
    private Matcher<T> currentAssertion = null;
    private List<Builder> filterBuilderList = new LinkedList<Builder>();

    ///////////////////////////////////////////////////////////////////////
    // DSL

    protected BooleanOperatorDsl<DSL> trueAssertion()
    {
        setAssertion(AnalysisUtils.<T>trueAssertion());
        return this;
    }

    @SuppressWarnings("unchecked")
    public BooleanOperatorDsl<DSL> and(final BooleanOperatorDsl<DSL> dsl)
    {
        // cheat IDEs by downcasting
        // (.. and making sure every DSL is also a builder). 
        updateAssertion(
                AnalysisUtils.andOperatorAssertion(currentAssertion, ((Builder) dsl).buildAssertion()));
        return this;
    }

    @SuppressWarnings("unchecked")
    public BooleanOperatorDsl<DSL> or(final BooleanOperatorDsl<DSL> dsl)
    {
        // cheat IDEs by downcasting
        // (.. and making sure every DSL is also a builder).
        updateAssertion(
                AnalysisUtils.orOperatorAssertion(currentAssertion, ((Builder) dsl).buildAssertion()));
        return this;
    }

    @SuppressWarnings("unchecked")
    public BooleanOperatorDsl<DSL> no(BooleanOperatorDsl<DSL> dsl)
    {
        // cheat IDEs by downcasting
        // (.. and making sure every DSL is also a builder).
        setAssertion(AnalysisUtils.negatedAssertion(((Builder) dsl).buildAssertion()));
        return this;
    }

    ///////////////////////////////////////////////////////////////////////
    // Matching DSL

    public BooleanOperatorDsl<DSL> is(Matcher<T> matcher)
    {
        setAssertion(matcher);
        return this;
    }


    ///////////////////////////////////////////////////////////////////////
    // Readable DSL

    public BooleanOperatorDsl<DSL> contains(String regex)
    {
        buildRegexAnalysisAssertion(regex, Boolean.FALSE);
        return this;
    }

    public BooleanOperatorDsl<DSL> matches(String regex)
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
    public NumericOperatorDsl<DSL> add(NumericOperatorDsl<DSL> dsl)
    {
        // cheat IDEs by downcasting
        // (.. and making sure every DSL is also a builder).        
        updateCalculation(AnalysisUtils.addOperatorCalculation(currentCalculation,
                                                               ((Builder) dsl).buildCalculation()));
        return this;
    }

    public NumericOperatorDsl<DSL> numberOf(NumericOperatorDsl<DSL> thisDslNumericOperatorDsl)
    {
        return this;
    }

    public NumericOperatorDsl<DSL> add(int value)
    {
        updateCalculation(AnalysisUtils.addOperatorCalculation(currentCalculation,
                                                               AnalysisUtils.<T>noOpCalculation(value)));
        return this;
    }

    @SuppressWarnings("unchecked")
    public NumericOperatorDsl<DSL> multiply(NumericOperatorDsl<DSL> dsl)
    {
        // cheat IDEs by downcasting
        // (.. and making sure every DSL is also a builder).
        updateCalculation(AnalysisUtils.multiplyOperatorCalculation(currentCalculation,
                                                                    ((Builder) dsl).buildCalculation()));
        return this;
    }

    public NumericOperatorDsl<DSL> multiply(int value)
    {
        updateCalculation(AnalysisUtils.multiplyOperatorCalculation(currentCalculation,
                                                                    AnalysisUtils.<T>noOpCalculation(value)));
        return this;
    }

    @SuppressWarnings("unchecked")
    public NumericOperatorDsl<DSL> subtract(NumericOperatorDsl<DSL> dsl)
    {
        // cheat IDEs by downcasting
        // (.. and making sure every DSL is also a builder).
        updateCalculation(AnalysisUtils.subtractOperatorCalculation(currentCalculation,
                                                                    ((Builder) dsl).buildCalculation()));
        return this;
    }

    public NumericOperatorDsl<DSL> subtract(int value)
    {
        updateCalculation(AnalysisUtils.subtractOperatorCalculation(currentCalculation,
                                                                    AnalysisUtils.<T>noOpCalculation(value)));
        return this;
    }

    @SuppressWarnings("unchecked")
    public BooleanOperatorDsl<DSL> equalTo(NumericOperatorDsl<DSL> dsl)
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

    public BooleanOperatorDsl<DSL> equalTo(int value)
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
    public BooleanOperatorDsl<DSL> greaterThanOrEqualTo(NumericOperatorDsl<DSL> dsl)
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

    public BooleanOperatorDsl<DSL> greaterThanOrEqualTo(int value)
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
    public BooleanOperatorDsl<DSL> lessThanOrEqualTo(NumericOperatorDsl<DSL> dsl)
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

    public BooleanOperatorDsl<DSL> lessThanOrEqualTo(int value)
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
    public BooleanOperatorDsl<DSL> greaterThan(NumericOperatorDsl<DSL> dsl)
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

    public BooleanOperatorDsl<DSL> greaterThan(int value)
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
    public BooleanOperatorDsl<DSL> lessThan(NumericOperatorDsl<DSL> dsl)
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

    public BooleanOperatorDsl<DSL> lessThan(int value)
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
