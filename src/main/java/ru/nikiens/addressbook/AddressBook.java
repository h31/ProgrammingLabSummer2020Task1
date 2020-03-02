package ru.nikiens.addressbook;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class AddressBook {
    private final Map<Name, Address> addressMap = new HashMap<>();

    @SafeVarargs
    public AddressBook(AbstractMap.SimpleEntry<Name, Address>... addresses) {
        Stream.of(addresses).forEach(e -> put(e.getKey(), e.getValue()));
    }

    public Address put(Name surname, Address address) {
        return addressMap.putIfAbsent(surname, address);
    }

    public Address remove(Name surname) {
        return addressMap.remove(surname);
    }

    public Address setAddress(Name surname, Address address) {
        return addressMap.replace(surname, address);
    }

    public Address getAddress(Name surname) {
        return addressMap.get(surname);
    }

    public List<Name> getPeople(String street) {
        return addressMap.entrySet().stream()
                .filter(e -> e.getValue().getStreet().equals(street))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public List<Name> getPeople(String street, int building) {
        return addressMap.entrySet().stream()
                .filter(e -> e.getValue().getStreet().equals(street) &&
                        e.getValue().getBuilding() == building)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddressBook addressBook = (AddressBook) o;

        return Objects.equals(addressMap, addressBook.addressMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressMap);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        addressMap.forEach((k, v) ->
                builder
                        .append(k.toString())
                        .append(" -> ")
                        .append(v.toString())
                        .append("\n"));

        return builder.toString();
    }
}