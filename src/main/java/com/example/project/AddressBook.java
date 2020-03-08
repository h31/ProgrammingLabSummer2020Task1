package com.example.project;

import java.util.*;

class Address {
    String street;
    int house;
    int flat;

    Address(String street, int house, int flat) {
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

public class AddressBook {
    public HashMap<String, Address> mapAddressBook;


    public AddressBook(HashMap<String, Address> mapAddressBook) {
        this.mapAddressBook = mapAddressBook;
    }

    @Override
    public String toString() {
        String res = new String();
        for (String key : mapAddressBook.keySet()) {
            res += key + " - " + mapAddressBook.get(key).toString()+ "\n";
        }
        return res;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressBook book = (AddressBook) o;
        return Objects.equals(mapAddressBook, book.mapAddressBook);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mapAddressBook);
    }

    public void add(String name, Address address) {
        if (!name.matches("[[а-яА-Я]\\d\\w\\s-]+") ||
                !address.street.matches("[[а-яА-Я]\\d\\w\\s-]+")) throw new IllegalArgumentException();
        if (mapAddressBook.containsKey(name)) throw new NoSuchElementException();
        mapAddressBook.put(name, address);
    }

    public void remove(String name) {
        if (!mapAddressBook.containsKey(name)) throw new NoSuchElementException();
        mapAddressBook.remove(name);
    }

    public void change(String name, Address address2) {
        if (!name.matches("[[а-яА-Я]\\d\\w\\s-]+") ||
                !address2.street.matches("[[а-яА-Я]\\d\\w\\s-]+")) throw new IllegalArgumentException();
        if (!mapAddressBook.containsKey(name)) throw new NoSuchElementException();
        this.remove(name);
        this.add(name, address2);
    }

    public Address getAddress(String name) {
        if (!name.matches("[[а-яА-Я]\\d\\w\\s-]+")) throw new IllegalArgumentException();
        if (!mapAddressBook.containsKey(name)) throw new NoSuchElementException();
        return mapAddressBook.get(name);
    }

    public HashSet<String> streetGetNames(String streetName) {
        if (!streetName.matches("[[а-яА-Я]\\d\\w\\s-]+")) throw new IllegalArgumentException();
        // Создаем пустой Set фамилий
        HashSet<String> listSurnames = new HashSet<>();
        // Перебираем все ключи и сравниваем названия улиц в значениях для каждого ключа с streetName
        // добавляем нужные элементы в Set
        for (String key : mapAddressBook.keySet()) {
            if (mapAddressBook.get(key).street.equals(streetName)) listSurnames.add(key);
        }
        return listSurnames;
    }

    public HashSet<String> houseGetNames(String streetName, int numberOfHouse) {
        if (!streetName.matches("[[а-яА-Я]\\d\\w\\s-]+")) throw new IllegalArgumentException();
        // Создаем пустой Set фамилий
        HashSet<String> listSurnames = new HashSet<>();
        // Перебираем все ключи и сравниваем названия улиц и номера домов с streetName и numberOfHouse
        // добавляем нужные элементы в Set
        for (String key : mapAddressBook.keySet()) {
            if (mapAddressBook.get(key).street.equals(streetName) &&
                    mapAddressBook.get(key).house == numberOfHouse) listSurnames.add(key);
        }
        return listSurnames;
    }
}

