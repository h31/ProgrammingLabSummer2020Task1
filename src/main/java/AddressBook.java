import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Objects;

public class AddressBook {
    private Map<String, Address> map = new HashMap<>();

    public AddressBook(Pair... people) {
        for (Pair element : people) {
            map.put(element.getKey(), element.getValue());
        }
    }

    public AddressBook(Map<String, Address> map) {
        this.map = map;
    }

    public AddressBook add(String surname, Address address) {
        map.put(surname, address);
        return this;
    }

    public AddressBook remove(String surname) {
        if (map.remove(surname) == null) throw new NoSuchElementException();
        return this;
    }

    public AddressBook changeAddress(String surname, Address address) {
        if (map.replace(surname, address) == null) throw new NoSuchElementException();
        return this;
    }

    public Address getAddress(String surname) {
        if (map.get(surname) == null) throw new NoSuchElementException();
        return map.get(surname);
    }

    public List<String> filterStreet(String street) {
        List<String> people = new ArrayList<>();
        for (Map.Entry<String, Address> element : this.map.entrySet()) {
            if (element.getValue().getStreet().equals(street)) people.add(element.getKey());
        }
        return people;
    }

    public List<String> filterBuilding(String street, String building) {
        List<String> people = new ArrayList<>();
        for (Map.Entry<String, Address> element : this.map.entrySet()) {
            if (element.getValue().getStreet().equals(street) && element.getValue().getBuilding().equals(building))
                people.add(element.getKey());
        }
        return people;
    }

    public int size() {
        return this.map.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressBook that = (AddressBook) o;
        return map.equals(that.map);
    }

    @Override
    public int hashCode() {
        return Objects.hash(map);
    }

    @Override
    public String toString() {
        return "AddressBook{" +
                "map=" + map +
                '}';
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


    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", building='" + building + '\'' +
                ", apartment='" + apartment + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return street.equals(address.street) &&
                building.equals(address.building) &&
                apartment.equals(address.apartment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, building, apartment);
    }
}
