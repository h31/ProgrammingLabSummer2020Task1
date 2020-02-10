import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddressBook {
    private HashMap<String, Address> map = new HashMap<>();

    public void addPerson(String surname, Address address) {
        map.put(surname, address);
    }

    public void removePerson(String surname) {
        map.remove(surname);
    }

    public void changeAdds(String surname, Address address) {
        map.replace(surname, address);
    }

    public Address getAdds(String surname) {
        return map.get(surname);
    }

    public ArrayList<String> filterStreet(String street) {
        ArrayList<String> people = new ArrayList<>();
        for (Map.Entry<String, Address> element: this.map.entrySet()) {
            if (element.getValue().getStreet().equals(street)) people.add(element.getKey());
        }
        return people;
    }

    public ArrayList<String> filterHouse(String house) {
        ArrayList<String> people = new ArrayList<>();
        for (Map.Entry<String, Address> element: this.map.entrySet()) {
            if (element.getValue().getHouse().equals(house)) people.add(element.getKey());
        }
        return people;
    }
}

class Address {
    private String street;
    private String house;
    private String apartment;

    public Address(String street, String house, String apartment) {
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
