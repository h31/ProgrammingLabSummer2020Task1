import javafx.util.Pair;
import java.util.*;

class AddressBook {
    private HashMap<String, Address> map = new HashMap<>();

    @SafeVarargs
    AddressBook(Pair<String, Address>... people) {
        for (Pair<String, Address> element: people) {
            map.put(element.getKey(), element.getValue());
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
        if (!map.containsKey(surname)) throw new IllegalArgumentException();
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

    List<String> filterBuilding(String street, String building) {
        List<String> people = new ArrayList<>();
        for (Map.Entry<String, Address> element : this.map.entrySet()) {
            if (element.getValue().getStreet().equals(street) && element.getValue().getBuilding().equals(building))
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
    private String building;
    private String apartment;

    Address(String street, String building, String apartment) {
        this.street = street;
        this.building = building;
        this.apartment = apartment;
    }

    String getStreet() {
        return this.street;
    }

    String getBuilding() {
        return this.building;
    }

    String getApartment() {
        return this.apartment;
    }

    boolean equals(Address address) {
        return this.apartment.equals(address.apartment) & this.building.equals(address.building) & this.street.equals(address.street);
    }
}
