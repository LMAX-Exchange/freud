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