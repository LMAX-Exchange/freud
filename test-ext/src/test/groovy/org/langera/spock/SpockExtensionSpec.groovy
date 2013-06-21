package org.langera.spock

import spock.lang.Specification

import static SpockExtension.matches

class SpockExtensionSpec extends Specification {

    def 'matches a list with a list closures'() {
    expect:
        matches([{ it == 1}, { it > 1}, { it < 4}]).call([1, 2, 3])
        matches([{ it == 1}, { it == 2}, { it == 3}]).call([1, 2, 3])
        !matches([{ it != 1}, { it == 2}, { it == 3}]).call([1, 2, 3])
        !matches([{ it == 1}, { it != 2}, { it == 3}]).call([1, 2, 3])
        !matches([{ it == 1}, { it == 2}, { it != 3}]).call([1, 2, 3])
        !matches([{ it == 1}, { it == 2}, { it == 3}, { it == 4}]).call([1, 2, 3])
        !matches([{ it == 1}]).call([1, 2, 3])
    }

    def 'matches different implementations of same type by comparing properties'() {
    given:
        Employee employee = new Employee(name: 'a', yearOfBirth: 1970, annualSalary: 17.00)
        Candidate candidate1 = new Candidate(name: 'a', yearOfBirth: 1970, annualSalaryExpectations: 17.00, lastWorkplace: 'Enron')
        Candidate candidate2 = new Candidate(name: 'a', yearOfBirth: 1969, annualSalaryExpectations: 17.00, lastWorkplace: 'Enron')
        Candidate candidate3 = new Candidate(name: 'b', yearOfBirth: 1969, annualSalaryExpectations: 17.00, lastWorkplace: 'Enron')
    expect:
        matches(employee, Person).call(employee)
        matches(employee, Person).call(candidate1)
        !matches(employee, Person).call(candidate2)
        !matches(employee, Person).call(candidate3)
        !matches(candidate1, Person).call(candidate3)
    }

    def 'blows up if type does not fit'() {
    given:
        Employee employee = new Employee(name: 'a', yearOfBirth: 1970, annualSalary: 17.00)
        Candidate candidate = new Candidate(name: 'a', yearOfBirth: 1970, annualSalaryExpectations: 17.00, lastWorkplace: 'Enron')
    when:
        matches(candidate, Candidate).call(employee)
    then:
        thrown IllegalArgumentException
    }

    def 'inspects an object by map of properties'() {
    given:
        Employee employee = new Employee(name: 'a', yearOfBirth: 1970, annualSalary: 17.00)
    expect:
        matches([name: 'a', yearOfBirth: 1970, annualSalary: 17.00]).call(employee)
        matches([name: { it == 'a'}, yearOfBirth: { it <= 1970}, annualSalary: {true}]).call(employee)
        !matches([name: 'b', yearOfBirth: 1970, annualSalary: 17.00]).call(employee)
        !matches([name: 'a', yearOfBirth: 1969, annualSalary: 17.00]).call(employee)
        !matches([name: 'a', yearOfBirth: 1970, annualSalary: 19.00]).call(employee)
        !matches([name: { it == 'a'}, yearOfBirth: { it > 1970}, annualSalary: {true}]).call(employee)
    }

    def 'blows up when there is a mismatch in properties'() {
    given:
        Employee employee = new Employee(name: 'a', yearOfBirth: 1970, annualSalary: 17.00)
    when:
        matches([name: 'a', yearOfBirth: 1970, someOtherProperty: 17.00]).call(employee)
    then:
        thrown NoSuchMethodException
    }

    static interface Person {
        String getName()
        int getYearOfBirth()
    }

    static class Employee implements Person {
        int yearOfBirth
        String name
        double annualSalary
    }

    static class Candidate implements Person {
        int yearOfBirth
        String name
        double annualSalaryExpectations
        String lastWorkplace
    }

}
