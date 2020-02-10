import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class AddressBook {
    private HashMap<String, Address> map = new HashMap<>();

    @SafeVarargs
    AddressBook(Pair<String, Address>... people) {
        for(int i = 0; i!= people.length; i++) {
            map.put(people[i].getKey(),people[i].getValue());
        }
    }

    AddressBook() {}

    void add(String surname, Address address) {
        map.put(surname, address);
    }

    void remove(String surname) {
        map.remove(surname);
    }

    void changeAdds(String surname, Address address) {
        map.replace(surname, address);
    }

    Address getAdds(String surname) {
        return map.get(surname);
    }

    ArrayList<String> filterStreet(String street) {
        ArrayList<String> people = new ArrayList<>();
        for (Map.Entry<String, Address> element : this.map.entrySet()) {
            if (element.getValue().getStreet().equals(street)) people.add(element.getKey());
        }
        return people;
    }

    ArrayList<String> filterHouse(String street, String house) {
        ArrayList<String> people = new ArrayList<>();
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
}
