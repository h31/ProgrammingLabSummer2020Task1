package ru.nikiens.addressbook

import nl.jqno.equalsverifier.EqualsVerifier
import spock.lang.Specification
import spock.lang.Unroll

class AddressTest extends Specification {
    @Unroll
    def "Test invalid args: #street, #building, #flat"() {
        when:
            new Address(street, building, flat)
        then:
            thrown(IllegalArgumentException)
        where:
            street    | building | flat
            "#@%^%^#" | 5        | 5
            "foo"     | -2       | 3
            "bar"     | 8        | 0
            ""        | 13       | 2
    }

    @Unroll
    def "Test valid args: #street, #building, #flat"() {
        when:
            new Address(street, building, flat)
        then:
            noExceptionThrown()
        where:
            street    | building | flat
            "Foo"     | 1        | 2
            "bar2"    | 3        | 4
            "baz-qux" | 5        | 6
            "Улица"   | 1        | 2
            "Foo Bar" | 2        | 2
    }

    def "Test toString()"() {
        given:
            def address = new Address("Foo", 15, 51)
        expect:
            address.toString() == "Foo, 15, 51"
    }

    def "Test equals() and hashCode() contract"() {
        when:
            EqualsVerifier.forClass(Address.class).verify()
        then:
            noExceptionThrown()
    }
}