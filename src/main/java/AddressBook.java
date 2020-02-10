public class AddressBook {
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
