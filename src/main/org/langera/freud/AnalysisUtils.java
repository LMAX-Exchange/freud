package org.langera.freud;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.langera.freud.listener.AnalysisListenerChain;
import org.langera.freud.listener.AssertionErrorAnalysisListener;
import org.langera.freud.listener.TraceAnalysisListener;

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

public final class AnalysisUtils
{
    private static final Matcher TRUE_ASSERTION = new TrueAnalysisAssertion();

    private AnalysisUtils()
    {
        // class of static methods - should not be constructed
    }

    public static AnalysisListener defaultAnalysisListener()
    {
        // TODO factory
        return new AnalysisListenerChain(new TraceAnalysisListener(), new AssertionErrorAnalysisListener());
    }

    @SuppressWarnings("unchecked")
    public static <T> Matcher<T> negatedAssertion(final Matcher<T> assertion)
    {
        return new NegateAnalysisAssertion(assertion);
    }


    @SuppressWarnings("unchecked")
    public static <T> Matcher<T> andOperatorAssertion(
            final Matcher<T> leftAssertion,
            final Matcher<T> rightAssertion)
    {
        return new AndAnalysisAssertion(leftAssertion, rightAssertion);
    }

    @SuppressWarnings("unchecked")
    public static <T> Matcher<T> orOperatorAssertion(
            final Matcher<T> leftAssertion,
            final Matcher<T> rightAssertion)
    {
        return new OrAnalysisAssertion(leftAssertion, rightAssertion);
    }

    ///////////////////////////////////////////////////////////////////////////////////

    @SuppressWarnings("unchecked")
    public static <T> AnalysisCalculation<T> noOpCalculation(final int value)
    {
        return new NoOpCalculation(value);
    }

    @SuppressWarnings("unchecked")
    public static <T> Matcher<T> equalOperatorAssertion(
            final AnalysisCalculation<T> leftAssertion,
            final AnalysisCalculation<T> rightAssertion)
    {
        return new EqualAnalysisAssertion(leftAssertion, rightAssertion);
    }

    @SuppressWarnings("unchecked")
    public static <T> Matcher<T> greaterThanOperatorAssertion(
            final AnalysisCalculation<T> leftAssertion,
            final AnalysisCalculation<T> rightAssertion)
    {
        return new GreaterThanAnalysisAssertion(leftAssertion, rightAssertion);
    }

    @SuppressWarnings("unchecked")
    public static <T> Matcher<T> lessThanOperatorAssertion(
            final AnalysisCalculation<T> leftAssertion,
            final AnalysisCalculation<T> rightAssertion)
    {
        return new LessThanAnalysisAssertion(leftAssertion, rightAssertion);
    }

    @SuppressWarnings("unchecked")
    public static <T> Matcher<T> greaterThanEqualOperatorAssertion(
            final AnalysisCalculation<T> leftAssertion,
            final AnalysisCalculation<T> rightAssertion)
    {
        return new GreaterThanEqualAnalysisAssertion(leftAssertion, rightAssertion);
    }

    @SuppressWarnings("unchecked")
    public static <T> Matcher<T> lessThanEqualOperatorAssertion(
            final AnalysisCalculation<T> leftAssertion,
            final AnalysisCalculation<T> rightAssertion)
    {
        return new LessThanEqualAnalysisAssertion(leftAssertion, rightAssertion);
    }

    @SuppressWarnings("unchecked")
    public static <T> AnalysisCalculation<T> addOperatorCalculation(
            final AnalysisCalculation<T> leftAssertion,
            final AnalysisCalculation<T> rightAssertion)
    {
        return new AddAnalysisCalculation(leftAssertion, rightAssertion);
    }


    @SuppressWarnings("unchecked")
    public static <T> AnalysisCalculation<T> subtractOperatorCalculation(
            final AnalysisCalculation<T> leftAssertion,
            final AnalysisCalculation<T> rightAssertion)
    {
        return new SubtractAnalysisCalculation(leftAssertion, rightAssertion);
    }


    @SuppressWarnings("unchecked")
    public static <T> AnalysisCalculation<T> multiplyOperatorCalculation(
            final AnalysisCalculation<T> leftAssertion,
            final AnalysisCalculation<T> rightAssertion)
    {
        return new MultiplyAnalysisCalculation(leftAssertion, rightAssertion);
    }

    @SuppressWarnings("unchecked")
    public static <T> Matcher<T> trueAssertion()
    {
        return (Matcher<T>) TRUE_ASSERTION;
    }

    @SuppressWarnings("unchecked")
    public static <T> Matcher<T>[] buildAssertions(List<Builder> builderList)
    {
        Matcher<T>[] assertions = new Matcher[builderList.size()];
        int i = 0;
        for (Builder assertionBuilder : builderList)
        {
            assertions[i] = assertionBuilder.buildAssertion();
        }
        return assertions;
    }

    private static final class TrueAnalysisAssertion extends BaseMatcher
    {
        public boolean matches(final Object toBeAnalysed)
        {
            return true;
        }

        @Override
        public String toString()
        {
            return "TRUE";
        }

        public void describeTo(Description description)
        {
            description.appendText(toString());
        }
    }


    private static final class NegateAnalysisAssertion<T> extends TypeSafeMatcher<T>
    {
        private final Matcher<T> assertion;

        public NegateAnalysisAssertion(final Matcher<T> assertion)
        {
            this.assertion = assertion;
        }

        public boolean matchesSafely(final T toBeAnalysed)
        {
            return !assertion.matches(toBeAnalysed);
        }

        @Override
        public String toString()
        {
            return "(NOT " + assertion + ")";
        }

        public void describeTo(Description description)
        {
            description.appendText(toString());
        }
    }

    private static final class OrAnalysisAssertion<T> extends AbstractBinaryOpAnalysisAssertion<T>
    {
        private OrAnalysisAssertion(Matcher<T> left, Matcher<T> right)
        {
            super(left, right);
        }

        @Override
        protected String operatorDisplayString()
        {
            return "OR";
        }

        public boolean matchesSafely(final T toBeAnalysed)
        {
            return getLeft().matches(toBeAnalysed) || getRight().matches(toBeAnalysed);
        }
    }

    private static final class AndAnalysisAssertion<T> extends AbstractBinaryOpAnalysisAssertion<T>
    {
        private AndAnalysisAssertion(Matcher<T> left, Matcher<T> right)
        {
            super(left, right);
        }

        @Override
        protected String operatorDisplayString()
        {
            return "AND";
        }

        public boolean matchesSafely(final T toBeAnalysed)
        {
            return getLeft().matches(toBeAnalysed) && getRight().matches(toBeAnalysed);
        }
    }

    private static abstract class AbstractBinaryOpAnalysisAssertion<T> extends TypeSafeMatcher<T>
    {
        private final Matcher<T> left;
        private final Matcher<T> right;

        public AbstractBinaryOpAnalysisAssertion(final Matcher<T> left, final Matcher<T> right)
        {
            this.left = left;
            this.right = right;
        }

        public Matcher<T> getLeft()
        {
            return left;
        }

        public Matcher<T> getRight()
        {
            return right;
        }

        protected abstract String operatorDisplayString();

        @Override
        public String toString()
        {
            return "(" + left + " " + operatorDisplayString() + " " + right + ")";
        }

        public void describeTo(Description description)
        {
            description.appendText(toString());
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    private static final class NoOpCalculation implements AnalysisCalculation
    {
        private int value;

        private NoOpCalculation(int value)
        {
            this.value = value;
        }

        public int analyse(Object toBeAnalysed)
        {
            return value;
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o)
            {
                return true;
            }
            if (o == null || getClass() != o.getClass())
            {
                return false;
            }

            NoOpCalculation that = (NoOpCalculation) o;

            return value == that.value;
        }

        @Override
        public int hashCode()
        {
            return value;
        }

        @Override
        public String toString()
        {
            return String.valueOf(value);
        }
    }

    private static final class EqualAnalysisAssertion<T> extends AbstractBinaryOpAnalysisCalculation<T>
    {
        private EqualAnalysisAssertion(AnalysisCalculation<T> left, AnalysisCalculation<T> right)
        {
            super(left, right, true);
        }

        @Override
        protected String operatorDisplayString()
        {
            return "==";
        }

        public boolean matchesSafely(final T toBeAnalysed)
        {
            return getLeft().analyse(toBeAnalysed) == getRight().analyse(toBeAnalysed);
        }

        public void describeTo(Description description)
        {
            description.appendText(toString());
        }
    }

    private static final class LessThanAnalysisAssertion<T>
            extends AbstractBinaryOpAnalysisCalculation<T>
    {
        private LessThanAnalysisAssertion(AnalysisCalculation<T> left, AnalysisCalculation<T> right)
        {
            super(left, right, false);
        }

        @Override
        protected String operatorDisplayString()
        {
            return "<";
        }

        public boolean matchesSafely(final T toBeAnalysed)
        {
            return getLeft().analyse(toBeAnalysed) < getRight().analyse(toBeAnalysed);
        }

        public void describeTo(Description description)
        {
            description.appendText(toString());
        }
    }

    private static final class LessThanEqualAnalysisAssertion<T>
            extends AbstractBinaryOpAnalysisCalculation<T>
    {
        private LessThanEqualAnalysisAssertion(AnalysisCalculation<T> left, AnalysisCalculation<T> right)
        {
            super(left, right, false);
        }

        @Override
        protected String operatorDisplayString()
        {
            return "<=";
        }

        public final boolean matchesSafely(final T toBeAnalysed)
        {
            return getLeft().analyse(toBeAnalysed) <= getRight().analyse(toBeAnalysed);
        }

        public void describeTo(Description description)
        {
            description.appendText(toString());
        }
    }

    private static final class GreaterThanAnalysisAssertion<T>
            extends AbstractBinaryOpAnalysisCalculation<T>
    {
        private GreaterThanAnalysisAssertion(AnalysisCalculation<T> left, AnalysisCalculation<T> right)
        {
            super(left, right, false);
        }

        @Override
        protected String operatorDisplayString()
        {
            return ">";
        }

        public final boolean matchesSafely(final T toBeAnalysed)
        {
            return getLeft().analyse(toBeAnalysed) > getRight().analyse(toBeAnalysed);
        }

        public void describeTo(Description description)
        {
            description.appendText(toString());
        }
    }

    private static final class GreaterThanEqualAnalysisAssertion<T>
            extends AbstractBinaryOpAnalysisCalculation<T>
    {
        private GreaterThanEqualAnalysisAssertion(AnalysisCalculation<T> left, AnalysisCalculation<T> right)
        {
            super(left, right, false);
        }

        @Override
        protected String operatorDisplayString()
        {
            return ">=";
        }

        public final boolean matchesSafely(final T toBeAnalysed)
        {
            return getLeft().analyse(toBeAnalysed) >= getRight().analyse(toBeAnalysed);
        }
    }

    private static final class AddAnalysisCalculation<T>
            extends AbstractBinaryOpAnalysisCalculation<T> implements AnalysisCalculation<T>
    {
        private AddAnalysisCalculation(AnalysisCalculation<T> left, AnalysisCalculation<T> right)
        {
            super(left, right, true);
        }

        @Override
        protected String operatorDisplayString()
        {
            return "+";
        }

        public int analyse(final T toBeAnalysed)
        {
            return getLeft().analyse(toBeAnalysed) + getRight().analyse(toBeAnalysed);
        }
    }

    private static final class SubtractAnalysisCalculation<T>
            extends AbstractBinaryOpAnalysisCalculation<T> implements AnalysisCalculation<T>
    {
        private SubtractAnalysisCalculation(AnalysisCalculation<T> left, AnalysisCalculation<T> right)
        {
            super(left, right, false);
        }

        @Override
        protected String operatorDisplayString()
        {
            return "-";
        }

        public int analyse(final T toBeAnalysed)
        {
            return getLeft().analyse(toBeAnalysed) - getRight().analyse(toBeAnalysed);
        }
    }

    private static final class MultiplyAnalysisCalculation<T>
            extends AbstractBinaryOpAnalysisCalculation<T> implements AnalysisCalculation<T>
    {
        private MultiplyAnalysisCalculation(AnalysisCalculation<T> left, AnalysisCalculation<T> right)
        {
            super(left, right, true);
        }

        @Override
        protected String operatorDisplayString()
        {
            return "*";
        }

        public int analyse(final T toBeAnalysed)
        {
            return getLeft().analyse(toBeAnalysed) * getRight().analyse(toBeAnalysed);
        }
    }

    private static abstract class AbstractBinaryOpAnalysisCalculation<T> extends TypeSafeMatcher<T>
    {
        private boolean symmetricOp;
        private AnalysisCalculation<T> left;
        private AnalysisCalculation<T> right;

        protected AbstractBinaryOpAnalysisCalculation(AnalysisCalculation<T> left, AnalysisCalculation<T> right, boolean symmetricOp)
        {
            this.left = left;
            this.right = right;
            this.symmetricOp = symmetricOp;
        }

        public AnalysisCalculation<T> getLeft()
        {
            return left;
        }

        public AnalysisCalculation<T> getRight()
        {
            return right;
        }

        protected boolean matchesSafely(T t)
        {
            return false;
        }

        public void describeTo(Description description)
        {
            description.appendText(toString());
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o)
            {
                return true;
            }
            if (o == null || getClass() != o.getClass())
            {
                return false;
            }

            AbstractBinaryOpAnalysisCalculation that = (AbstractBinaryOpAnalysisCalculation) o;

            if (!left.equals(that.left))
            {
                if (symmetricOp)
                {
                    if (!left.equals(that.right))
                    {
                        return false;
                    }
                    else
                    {
                        return (right.equals(that.left));
                    }
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return (right.equals(that.right));
            }
        }

        @Override
        public int hashCode()
        {
            int result = 31;
            result += left.hashCode();
            result += (symmetricOp ? 1 : 31) * right.hashCode();
            return result;
        }

        protected abstract String operatorDisplayString();

        @Override
        public String toString()
        {
            return "(" + left + " " + operatorDisplayString() + " " + right + ")";
        }
    }
}
