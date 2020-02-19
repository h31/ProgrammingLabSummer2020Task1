package ru.nikiens.addressbook

import nl.jqno.equalsverifier.EqualsVerifier
import spock.lang.Specification

class AddressTest extends Specification {
    def "test invalid args"() {
        when:
            new Address(street, building, flat)

        then:
            thrown(IllegalArgumentException)

        where:
            street      | building  | flat
            "#@%^%^#"   | 5         | 5
            "foo"       | -2        | 3
            "bar"       | 8         | 0
            ""          | 13        | 2
    }

    def "test valid args"() {
        when:
            new Address(street, building, flat)

        then:
            noExceptionThrown()

        where:
            street      | building  | flat
            "Foo"       | 1         | 2
            "bar2"      | 3         | 4
            "baz-biz"   | 5         | 6
    }

    def "test toString()"() {
        given:
            def address = new Address("Foo", 15, 51)

        expect:
            address.toString() == "Address{street='Foo', building=15, flat=51}"
    }

    def "test equals() and hashCode() contract"() {
        when:
            EqualsVerifier.forClass(Address.class).verify()
        then:
            noExceptionThrown()
    }
}