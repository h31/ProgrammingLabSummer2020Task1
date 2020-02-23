package ru.nikiens.addressbook

import nl.jqno.equalsverifier.EqualsVerifier
import spock.lang.Specification

class AddressBookTest extends Specification {
    def testBook = new AddressBook(
            new AbstractMap.SimpleEntry("foo", new Address("Foo", 1, 2)),
            new AbstractMap.SimpleEntry("Bar", new Address("Foo", 1, 2)),
            new AbstractMap.SimpleEntry("Baz", new Address("Bar", 2, 2)),
    )

    def "Test put()"() {
        given:
            def addr = new Address("Qux", 5, 5)
        and: "Should be null"
            def result1 = testBook.put("qux", addr)
        and: "Should be the previous value"
            def result2 = testBook.put("foo", addr)
        expect:
            result1 == null
            result2 == new Address("Foo", 1, 2)
    }

    def "Test remove()"() {
        given: "Should be the associated value"
            def entry1 = testBook.remove("foo")
        and: "Should be null"
            def entry2 = testBook.remove("бар")
        expect:
            entry1 == new Address("Foo", 1, 2)
            entry2 == null
    }

    def "Test setAddr()"() {
        given:
            def addr = new Address("Qux", 5, 5)
        and: "Should be the previous associated value"
            def entry1 = testBook.setAddr("Bar", addr)
        and: "Should be null"
            def entry2 = testBook.setAddr("Баз", addr)
        expect:
            entry1 == new Address("Foo", 1, 2)
            entry2 == null
    }

    def "Test getAddr()"() {
        given: "Should be the associated value"
            def entry1 = testBook.getAddr("Baz")
        and: "Should be null"
            def entry2 = testBook.getAddr("Баз")
        expect:
            entry1 == new Address("Bar", 2, 2)
            entry2 == null
    }

    def "Test getPeople()"() {
        given:
            def people = List.of("Bar", "foo")
            def result1 = testBook.getPeople("Foo")
        and:
            def empty = Collections.emptyList()
            def result2 = testBook.getPeople("Baz")
        expect:
            result1 == people
            result2 == empty
    }

    def "Test getPeople() with building"() {
        given:
            def people = List.of("Bar", "foo")
            def result1 = testBook.getPeople("Foo", 1)
        and:
            def empty = Collections.emptyList()
            def result2 = testBook.getPeople("Baz", 2)
        expect:
            result1 == people
            result2 == empty
    }

    def "Test equals() and hashCode() contract"() {
        when:
            EqualsVerifier.forClass(AddressBook.class).verify()
        then:
            noExceptionThrown()
    }
}