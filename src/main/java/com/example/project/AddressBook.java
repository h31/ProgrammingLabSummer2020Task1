package com.example.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.NoSuchElementException;

class Address {
    String street;
    int house;
    int flat;

    Address(String street, int house, int flat) {
        this.street = street;
        this.house = house;
        this.flat = flat;
    }
}

public class AddressBook {
    HashMap<String, Address> mapAddressBook;


    public AddressBook(HashMap<String, Address> mapAddressBook) {
        this.mapAddressBook = mapAddressBook;
    }


    public void add(String name, Address address) {
        if (!name.matches("[[а-яА-Я]\\w\\s-]+") ||
                !address.street.matches("[[а-яА-Я]\\w\\s-]+")) throw new IllegalArgumentException();
        mapAddressBook.put(name, address);
    }

    public void remove(String name) {
        if (!name.matches("[[а-яА-Я]\\w\\s-]+")) throw new IllegalArgumentException();
        if (!mapAddressBook.containsKey(name)) throw new NoSuchElementException();
        mapAddressBook.remove(name);
    }

    public void change(String name, Address address2) {
        if (!name.matches("[[а-яА-Я]\\w\\s-]+") ||
                !address2.street.matches("[[а-яА-Я]\\w\\s-]+")) throw new IllegalArgumentException();
        if (!mapAddressBook.containsKey(name)) throw new NoSuchElementException();
        this.remove(name);
        this.add(name, address2);
    }

    public Address getAddress(String name) {
        if (!name.matches("[[а-яА-Я]\\w\\s-]+")) throw new IllegalArgumentException();
        if (!mapAddressBook.containsKey(name)) throw new NoSuchElementException();
        return mapAddressBook.get(name);
    }

    public ArrayList<String> streetGetName(String streetName) {
        if (!streetName.matches("[[а-яА-Я]\\w\\s-]+")) throw new IllegalArgumentException();
        // Создаем пустой список фамилий
        ArrayList<String> listSurnames = new ArrayList();
        // Перебираем все ключи и сравниваем названия улиц в значениях для каждого ключа с streetName
        // добавляем нужные элементы в список
        for (String key : mapAddressBook.keySet()) {
            if (mapAddressBook.get(key).street.equals(streetName)) listSurnames.add(key);
        }
        Collections.sort(listSurnames);
        return listSurnames;
    }

    public ArrayList<String> houseGetName(String streetName, int numberOfHouse) {
        if (!streetName.matches("[[а-яА-Я]\\w\\s-]+")) throw new IllegalArgumentException();
        // Создаем пустой список фамилий
        ArrayList<String> listSurnames = new ArrayList<>();
        // Перебираем все ключи и сравниваем названия улиц и номера домов с streetName и numberOfHouse
        // добавляем нужные элементы в список
        for (String key : mapAddressBook.keySet()) {
            if (mapAddressBook.get(key).street.equals(streetName) &&
                    mapAddressBook.get(key).house == numberOfHouse) listSurnames.add(key);
        }
        Collections.sort(listSurnames);
        return listSurnames;
    }
}

