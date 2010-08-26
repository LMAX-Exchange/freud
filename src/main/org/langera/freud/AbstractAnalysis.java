package org.langera.freud;

import org.hamcrest.Matcher;
import org.langera.freud.dsl.BooleanOperatorDsl;
import org.langera.freud.dsl.FilterDsl;
import org.langera.freud.dsl.NumericOperatorDsl;
import org.langera.freud.dsl.UnaryBooleanOperatorDsl;
import org.langera.freud.util.collection.AnalysedObjectIterator;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

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

public abstract class AbstractAnalysis<Type, This> implements Analysis
{
    private static final ThreadLocal<Map<Class, AnalysedObjectIterator>> CONTEXT =
            new ThreadLocal<Map<Class, AnalysedObjectIterator>>()
            {
                @Override
                protected Map<Class, AnalysedObjectIterator> initialValue()
                {
                    return new HashMap<Class, AnalysedObjectIterator>();
                }
            };

    @SuppressWarnings("unchecked")
    public static <T> T getCurrentlyAnalysed(Class<T> type)
    {
        return (T) CONTEXT.get().get(type).current();
    }

    static <T> void register(Class<T> type, AnalysedObjectIterator<T> iterator)
    {
        CONTEXT.get().put(type, iterator);
    }

    private final AnalysedObjectIterator<Type> iterator;
    private final Class<Type> type;

    // internal data structure
    private transient Builder currentBuilder;
    private transient Builder currentFilter;
    private transient AnalysisListener analysisListener = AnalysisUtils.defaultAnalysisListener();
    private transient CopyOnWriteArrayList<Builder> filterList = new CopyOnWriteArrayList<Builder>();
    private transient ConcurrentMap<Builder, List<Builder>> assertionBuilderListByFilterBuilderMap =
            new ConcurrentHashMap<Builder, List<Builder>>();

    protected AbstractAnalysis(final AnalysedObjectIterator<Type> iterator,
                               final Class<Type> type)
    {
        this.iterator = iterator;
        this.type = type;
        register(type, iterator);
    }

    public final void analyse(AnalysisListener listener)
    {
        if (listener == null)
        {
            throw new IllegalArgumentException("listener cannot be null");
        }
        this.analysisListener = listener;
        iterator.setListener(listener);
        List<Matcher<Type>> adapterList = buildAdapterList();
        for (Type currentlyAnalysed : iterator)
        {
            for (Matcher<Type> assertion : adapterList)
            {
                try
                {
                    assertion.matches(currentlyAnalysed);
                }
                catch (Exception e)
                {
                    listener.unexpected(currentlyAnalysed, e);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    protected final This assertThat(BooleanOperatorDsl dsl)
    {
        Builder builder = (Builder) dsl;
        if (currentFilter == null)
        {
            forEach(new StaticAssertionBuilder(AnalysisUtils.trueAssertion(), ((Builder) dsl).getType()));
        }
        else
        {
            if (!currentFilter.getType().equals(((Builder) dsl).getType()))
            {
                // TODO replace with adapters?
                throw new AnalysisInitialisationException("Type mismatch. forEach(" + currentFilter.getType() +
                        "), assertThat(" + ((Builder) dsl).getType() + ")");
            }
        }

        List<Builder> builderList = assertionBuilderListByFilterBuilderMap.get(currentFilter);
        builderList.add(builder);

        return (This) this;
    }

    protected final FilterDsl forEach(BooleanOperatorDsl dsl)
    {
        if (currentFilter != null &&
                assertionBuilderListByFilterBuilderMap.get(currentFilter).isEmpty())
        {
            throw new AnalysisInitialisationException("Cannot build analysis. 'forEach' decleration missing any 'assertThat'");
        }

        currentFilter = (Builder) dsl;
        filterList.addIfAbsent(currentFilter);
        assertionBuilderListByFilterBuilderMap.putIfAbsent(currentFilter, new LinkedList<Builder>());

        return currentFilter;
    }

    @SuppressWarnings("unchecked")
    public <Dsl extends BooleanOperatorDsl> BooleanOperatorDsl<Dsl> no(final BooleanOperatorDsl<Dsl> booleanOperatorDsl)
    {
        return ((UnaryBooleanOperatorDsl) booleanOperatorDsl).no(booleanOperatorDsl);
    }

    @SuppressWarnings("unchecked")
    public <Dsl extends BooleanOperatorDsl> NumericOperatorDsl<Dsl> numberOf(final NumericOperatorDsl<Dsl> numericOperatorDsl)
    {
        return ((NumericOperatorDsl) numericOperatorDsl).numberOf(numericOperatorDsl);
    }

    protected abstract NestedTypeAnalysisAdapter getAnalysisAdapter(final Class type, final Class nestedType);

    private NestedTypeAnalysisAdapter getAnalysisAdapterInternal(final Class type, final Class nestedType)
    {
        if (type == nestedType)
        {
            return SelfAnalysisAdapter.getInstance();
        }
        final NestedTypeAnalysisAdapter adapter = getAnalysisAdapter(type, nestedType);
        if (adapter == null)
        {
            throw new UnsupportedOperationException("Nested assertion from [" + type.getSimpleName() +
                    "] to type [" + nestedType.getSimpleName() + "] is not supported.");
        }
        return adapter;
    }

    @SuppressWarnings("unchecked")
    protected List<Matcher<Type>> buildAdapterList()
    {
        List<Matcher<Type>> adapterList =
                new ArrayList<Matcher<Type>>(filterList.size());
        for (Builder filterBuilder : filterList)
        {
            Matcher filter = filterBuilder.buildAssertion();
            Matcher<Type> hierarchicalFilter = buildAssertionForHierarchicalBuilderList(filterBuilder);
            Matcher[] assertions =
                    AnalysisUtils.buildAssertions(assertionBuilderListByFilterBuilderMap.get(filterBuilder));
            final Class otherType = filterBuilder.getType();
            adapterList.add(new NestedTypeAnalysisAssertion(
                    getAnalysisAdapterInternal(type, otherType),
                    filter, hierarchicalFilter,
                    assertions, otherType, analysisListener));
        }
        return adapterList;
    }

    @SuppressWarnings("unchecked")
    private Matcher<Type> buildAssertionForHierarchicalBuilderList(Builder filterBuilder)
    {
        Matcher<Type> filter = AnalysisUtils.trueAssertion();
        Map<Class, List<Matcher>> additionalFilterListByTypeMap =
                buildHierarchicalFilterListByTypeMap(filterBuilder);
        for (Map.Entry<Class, List<Matcher>> entry : additionalFilterListByTypeMap.entrySet())
        {
            List<Matcher> assertionList = entry.getValue();
            Matcher assertion = assertionList.get(0);
            for (int i = 1, size = assertionList.size(); i < size; i++)
            {
                assertion = AnalysisUtils.andOperatorAssertion(assertion, assertionList.get(i));
            }
            final Matcher<Type> assertionForType =
                    new NestedTypeFilterAnalysisAssertion(getAnalysisAdapterInternal(type, entry.getKey()), assertion);

            checkHierarchyViaAdapter(filterBuilder.getType(), entry.getKey());
            filter = AnalysisUtils.andOperatorAssertion(filter, assertionForType);
        }
        return filter;
    }

    private void checkHierarchyViaAdapter(Class filterClass, Class hierarchicalFilterClass)
    {
        getAnalysisAdapterInternal(hierarchicalFilterClass, filterClass);
    }

    @Override
    @SuppressWarnings("unchecked")
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (Builder filterBuilder : filterList)
        {
            final Matcher filter = filterBuilder.buildAssertion();
            sb.append("forEach(").append((filter == null) ? "" : filter).append(")");
            List<Builder> filterAttachedBuilderList = filterBuilder.getFilterBuilderList();
            for (Builder attachedFilterBuilder : filterAttachedBuilderList)
            {
                final Matcher attachedFilter = attachedFilterBuilder.buildAssertion();
                sb.append("of(").append(attachedFilter).append(")");
            }
            sb.append("assertThat(");
            List<Builder> assertionBuilderList = assertionBuilderListByFilterBuilderMap.get(filterBuilder);
            if (assertionBuilderList.isEmpty())
            {
                sb.append(")");
            }
            else
            {
                for (Builder assertionBuilder : assertionBuilderList)
                {
                    sb.append(assertionBuilder.buildAssertion());
                    sb.append(",");
                }
                sb.replace(sb.length() - 1, sb.length(), ")");
            }
        }
        return sb.toString();
    }

    /////////////////////////////////////////////////////////////////////////////////////

    @SuppressWarnings("unchecked")
    private static Map<Class, List<Matcher>> buildHierarchicalFilterListByTypeMap(Builder filterBuilder)
    {
        final List<Builder> builderList = filterBuilder.getFilterBuilderList();
        final Map<Class, List<Matcher>> additionalFilterListByTypeMap =
                new HashMap<Class, List<Matcher>>(builderList.size());
        for (Builder builder : builderList)
        {
            final Class type = builder.getType();
            List<Matcher> assertionList = additionalFilterListByTypeMap.get(type);
            if (assertionList == null)
            {
                assertionList = new LinkedList();
                additionalFilterListByTypeMap.put(type, assertionList);
            }
            assertionList.add(builder.buildAssertion());
        }
        return additionalFilterListByTypeMap;
    }
}
