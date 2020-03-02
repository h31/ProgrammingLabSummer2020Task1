import java.util.*;

public class AddressBook {
    private HashMap<String, Address> map = new HashMap<>();

    AddressBook(Pair ... people) {
        for (Pair element: people) {
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

    AddressBook changeAddress(String surname, Address address) {
        if (!map.containsKey(surname)) throw new IllegalArgumentException();
        map.replace(surname, address);
        return this;
    }

    Address getAddress(String surname) {
        if (!map.containsKey(surname)) throw new IllegalArgumentException();
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
