package com.example.project;

import java.util.*;

public class AddressBook {
    public Map<String, Address> mapAddressBook;
    private String regex = "[[а-яА-Я]\\d\\w\\s-]+";


    public AddressBook() {
        this.mapAddressBook = new HashMap();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (String key : mapAddressBook.keySet()) {
            res.append(key).append(" - ").append(mapAddressBook.get(key).toString()).append("\n");
        }
        return res.toString();
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
        if (!name.matches(regex)) throw new IllegalArgumentException();
        Address value = mapAddressBook.put(name, address);
        if (value != null) throw new NoSuchElementException();
    }

    public void remove(String name) {
        Address value = mapAddressBook.remove(name);
        if (value == null) throw new NoSuchElementException();
    }

    public void change(String name, Address address2) {
        if (!mapAddressBook.containsKey(name)) throw new NoSuchElementException();
        this.remove(name);
        this.add(name, address2);
    }

    public Address getAddress(String name) {
        if (!name.matches(regex)) throw new IllegalArgumentException();
        if (!mapAddressBook.containsKey(name)) throw new NoSuchElementException();
        return mapAddressBook.get(name);
    }

    public Set<String> streetGetNames(String streetName) {
        if (!streetName.matches(regex)) throw new IllegalArgumentException();
        // Создаем пустой Set фамилий
        Set<String> listSurnames = new HashSet<>();
        // Перебираем все ключи и сравниваем названия улиц в значениях для каждого ключа с streetName
        // Добавляем нужные элементы в Set
        for (String key : mapAddressBook.keySet()) {
            if (mapAddressBook.get(key).street.equals(streetName)) listSurnames.add(key);
        }
        return listSurnames;
    }

    public Set<String> houseGetNames(String streetName, int numberOfHouse) {
        if (!streetName.matches(regex)) throw new IllegalArgumentException();
        // Создаем пустой Set фамилий
        Set<String> listSurnames = new HashSet<>();
        // Перебираем все ключи и сравниваем названия улиц и номера домов с streetName и numberOfHouse
        // Добавляем нужные элементы в Set
        for (String key : mapAddressBook.keySet()) {
            if (mapAddressBook.get(key).street.equals(streetName) &&
                    mapAddressBook.get(key).house == numberOfHouse) listSurnames.add(key);
        }
        return listSurnames;
    }
}

