import javafx.util.Pair;

import java.util.*;

class AddressBook {
    private HashMap<String, Address> map = new HashMap<>();

    @SafeVarargs
    AddressBook(Pair<String, Address>... people) {
        for (int i = 0; i != people.length; i++) {
            map.put(people[i].getKey(), people[i].getValue());
        }
    }

    private AddressBook(HashMap<String, Address> map) {
        this.map = map;
    }

    AddressBook add(String surname, Address address) {
        map.put(surname, address);
        return this;
    }

    AddressBook remove(String surname) {
        if (!map.containsKey(surname)) throw new NullPointerException();
        map.remove(surname);
        return this;
    }

    AddressBook changeAdds(String surname, Address address) {
        if (!map.containsKey(surname)) throw new NullPointerException();
        map.replace(surname, address);
        return this;
    }

    Address getAdds(String surname) {
        if (!map.containsKey(surname)) throw new NullPointerException();
        return map.get(surname);
    }

    List<String> filterStreet(String street) {
        List<String> people = new ArrayList<>();
        for (Map.Entry<String, Address> element : this.map.entrySet()) {
            if (element.getValue().getStreet().equals(street)) people.add(element.getKey());
        }
        return people;
    }

    List<String> filterHouse(String street, String house) {
        List<String> people = new ArrayList<>();
        for (Map.Entry<String, Address> element : this.map.entrySet()) {
            if (element.getValue().getStreet().equals(street) && element.getValue().getHouse().equals(house))
                people.add(element.getKey());
        }
        return people;
    }

    int size() {
        return this.map.size();
    }
}

class Address {
    private String street;
    private String house;
    private String apartment;

    Address(String street, String house, String apartment) {
        this.street = street;
        this.house = house;
        this.apartment = apartment;
    }

    String getStreet() {
        return this.street;
    }

    String getHouse() {
        return this.house;
    }

    String getApartment() {
        return this.apartment;
    }

    boolean equals(Address address) {
        return this.apartment.equals(address.apartment) & this.house.equals(address.house) & this.street.equals(address.street);
    }
}
