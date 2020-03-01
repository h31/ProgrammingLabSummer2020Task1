package ru.nikiens.addressbook

import spock.lang.Specification
import spock.lang.Unroll

class NameTest extends Specification {
    @Unroll
    def "Test invalid args: #name"() {
        when:
            new Name(name)
        then:
            thrown(IllegalArgumentException)
        where:
            name << ["#%@#", "Foo Bar", "", " "]
    }

    @Unroll
    def "Test valid args: #name"() {
        when:
            new Name(name)
        then:
            noExceptionThrown()
        where:
            name << ["Foo", "Бар", "foo", "baz-qux"]
    }
}
