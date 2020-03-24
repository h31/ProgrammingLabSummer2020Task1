package com.example.project;

import java.util.Objects;

public class Address {
    public String street;
    public int house;
    public int flat;

    public Address(String street, int house, int flat) {
        if (!street.matches("[[а-яА-Я]\\d\\w\\s-]+")
                || house <= 0 || flat <= 0) throw new IllegalArgumentException();
        this.street = street;
        this.house = house;
        this.flat = flat;
    }

    @Override
    public String toString() {
        return "ул. " + street +
                ", д. " + house +
                ", кв. " + flat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return house == address.house &&
                flat == address.flat &&
                Objects.equals(street, address.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, house, flat);
    }
}
