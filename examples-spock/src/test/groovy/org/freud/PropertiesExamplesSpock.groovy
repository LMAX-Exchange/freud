package org.freud

import spock.lang.Specification

class PropertiesExamplesSpock extends Specification {

    def 'properties that contain max or min in their name must be a number'() {
//        return Freud.iterateOver(Property.class).
//                forEach(propertyKey().contains("\\.min").or(propertyKey().contains("\\.max"))).
//                assertThat(propertyValue().matches("\\d+")).in(iterator);
    }

    def 'properties file should not contain passwords'() {
//        return Freud.iterateOver(Property.class).
//                forEach(propertyKey().contains("\\.min").or(propertyKey().contains("\\.max"))).
//                assertThat(propertyValue().matches("\\d+")).in(iterator);
    }
}
