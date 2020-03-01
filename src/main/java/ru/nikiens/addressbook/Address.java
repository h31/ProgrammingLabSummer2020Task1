package ru.nikiens.addressbook;

import java.util.Objects;

public final class Address {
    private final String street;
    private final int building;
    private final int flat;

    public Address(String street, int building, int flat) {
        if (building <= 0 ||
                flat <= 0 ||
                !street.matches(Name.Format.MULTIPLE.toString())) throw new IllegalArgumentException();

        this.street = street;
        this.building = building;
        this.flat = flat;
    }

    public String getStreet() {
        return street;
    }

    public int getBuilding() {
        return building;
    }

    public int getFlat() {
        return flat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        return Objects.equals(getFlat(), address.getFlat()) &&
                Objects.equals(getBuilding(), address.getBuilding()) &&
                Objects.equals(getStreet(), address.getStreet());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStreet(), getBuilding(), getFlat());
    }

    @Override
    public String toString() {
        return getStreet() + ", " + getBuilding() + ", " + getFlat();
    }
}