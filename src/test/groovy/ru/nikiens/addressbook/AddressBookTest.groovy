package ru.nikiens.addressbook

import nl.jqno.equalsverifier.EqualsVerifier
import spock.lang.Specification

class AddressBookTest extends Specification {
    def testBook = new AddressBook(
            new AbstractMap.SimpleEntry(new Name("foo"), new Address("Foo", 1, 2)),
            new AbstractMap.SimpleEntry(new Name("Bar"), new Address("Foo", 1, 2)),
            new AbstractMap.SimpleEntry(new Name("Baz"), new Address("Bar", 2, 2)),
    )

    def "Test put()"() {
        given:
            def addr = new Address("Qux", 5, 5)
        and: "Should be null"
            def result1 = testBook.put(new Name("qux"), addr)
        and: "Should be the previous value"
            def result2 = testBook.put(new Name("foo"), addr)
        expect:
            result1 == null
            result2 == new Address("Foo", 1, 2)
    }

    def "Test remove()"() {
        given: "Should be the associated value"
            def entry1 = testBook.remove(new Name("foo"))
        and: "Should be null"
            def entry2 = testBook.remove(new Name("бар"))
        expect:
            entry1 == new Address("Foo", 1, 2)
            entry2 == null
    }

    def "Test setAddress()"() {
        given:
            def addr = new Address("Qux", 5, 5)
        and: "Should be the previous associated value"
            def entry1 = testBook.setAddress(new Name("Bar"), addr)
        and: "Should be null"
            def entry2 = testBook.setAddress(new Name("Баз"), addr)
        expect:
            entry1 == new Address("Foo", 1, 2)
            entry2 == null
    }

    def "Test getAddress()"() {
        given: "Should be the associated value"
            def entry1 = testBook.getAddress(new Name("Baz"))
        and: "Should be null"
            def entry2 = testBook.getAddress(new Name("Баз"))
        expect:
            entry1 == new Address("Bar", 2, 2)
            entry2 == null
    }

    def "Test getPeople()"() {
        given:
            def people = List.of(new Name("Bar"), new Name("foo"))
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
            def people = List.of(new Name("Bar"), new Name("foo"))
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

    def "Test toString()"() {
        given:
            def expected = "Bar -> Foo, 1, 2\n" +
                    "foo -> Foo, 1, 2\n"+
                    "Baz -> Bar, 2, 2\n"
        expect:
            testBook.toString() == expected
    }
}