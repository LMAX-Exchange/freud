package org.langera.spock

import java.beans.BeanInfo
import java.beans.PropertyDescriptor
import java.lang.reflect.Method

import static java.beans.Introspector.getBeanInfo

class SpockExtension {

//    static {
//        ExpandoMetaClass.enableGlobally()
//
//        Object.metaClass.has = { Map<String, Object> state ->
//            def instance = delegate
//            state.every { String key, Object value ->
//                Object actual = instance.metaClass.getProperty(instance, key)
//                return value == actual
//            }
//        }
//    }

    private SpockExtension() {
        // static utility
    }

    static Closure<Boolean> matches(List<Closure<Boolean>> matchers) {

        return { List list ->
            int index = 0
            matchers.size() == list.size() &&
                    matchers.every { Closure<Boolean> closure ->
                        closure.call(list[index++])
                    }
        }
    }

    static <T> Closure<Boolean> matches(T expected, Class<T> type) {
        return { T instance ->
            BeanInfo beanInfo = getBeanInfo(type)
            beanInfo.propertyDescriptors.every { PropertyDescriptor property ->
                Object value = property.readMethod.invoke(instance)
                Object expectedValue = property.readMethod.invoke(expected)
                return value == expectedValue
            }
        }
    }

    static Closure<Boolean> matches(Map<String, Object> expected) {
        return { instance ->
            Class instanceClass = instance.class
            expected.every { String property, Object expectedValue ->
                Method getter = instanceClass.getMethod("get${property.charAt(0).toUpperCase()}${property.substring(1)}")
                if (!getter) {
                    throw new IllegalArgumentException("Failed to find property $property")
                }
                else {
                    try {
                        getter.accessible = true
                        Object actualValue = getter.invoke(instance)
                        boolean result = actualValue == expectedValue
                        if (!result && expectedValue instanceof Closure) {
                            result = expectedValue.call(actualValue)
                        }
                        return result
                    }
                    catch (IllegalAccessException e) {
                        throw new IllegalArgumentException("Failed to get value for property $property", e)
                    }
                }
            }
        }
    }
}