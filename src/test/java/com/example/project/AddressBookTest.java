package com.example.project;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class AddressBookTest {

    @Test
    void add() {
        // 1 Добавление в пустую книгу
        AddressBook actual1 = new AddressBook();
        actual1.add("Забей ворота", new Address("2-я Советская", 38, 2767));
        AddressBook expected1 = new AddressBook();
        expected1.mapAddressBook.put("Забей ворота", new Address("2-я Советская", 38, 2767));
        assertEquals(expected1, actual1);

        // 2 Добаление в непустую книгу
        AddressBook actual2 = new AddressBook();
        actual2.mapAddressBook.put("Галиева", new Address("Харченко", 16, 318));
        actual2.mapAddressBook.put("Меркучева", new Address("Харченко", 16, 316));
        actual2.add("Салтыков-Щедрин", new Address("Большой проспект", 1, 1));
        AddressBook expected2 = new AddressBook();
        expected2.mapAddressBook.put("Галиева", new Address("Харченко", 16, 318));
        expected2.mapAddressBook.put("Меркучева", new Address("Харченко", 16, 316));
        expected2.mapAddressBook.put("Салтыков-Щедрин", new Address("Большой проспект", 1, 1));
        assertEquals(expected2, actual2);

        // 3 Фамилия не удовлетворяет
        assertThrows(IllegalArgumentException.class,
                () -> actual2.add("Кузне:цова", new Address("Харченко", 16, 520)));

        // 4 Название улицы не удовлетворяет
        assertThrows(IllegalArgumentException.class,
                () -> actual2.add("Кузнецова", new Address("Харченко?", 16, 520)));
    }

    @Test
    void remove() {
        // 1 Удаление из книги, состоящей из одного элемента
        AddressBook actual1 = new AddressBook();
        actual1.add("Забей ворота", new Address("2-я Советская", 38, 2767));
        actual1.remove("Забей ворота");
        AddressBook expected1 = new AddressBook();
        assertEquals(expected1, actual1);

        // 2 Удаление из книги большего размера
        AddressBook actual2 = new AddressBook();
        actual2.add("Галиева", new Address("Харченко", 16, 318));
        actual2.add("Меркучева", new Address("Харченко", 16, 316));
        actual2.add("Салтыков-Щедрин", new Address("Большой проспект", 1, 1));
        actual2.remove("Салтыков-Щедрин");
        AddressBook expected2 = new AddressBook();
        expected2.add("Галиева", new Address("Харченко", 16, 318));
        expected2.add("Меркучева", new Address("Харченко", 16, 316));
        assertEquals(expected2, actual2);

        // 3 Фамилия не содержится в книге
        assertThrows(NoSuchElementException.class,
                () -> actual2.remove("Петряева"));
    }

    @Test
    void change() {
        // 1 Изменение элемента
        AddressBook actual = new AddressBook();
        actual.add("Меркучева", new Address("Харченко", 16, 316));
        actual.add("Салтыков-Щедрин", new Address("Митяшкина", 43, 1));
        actual.change("Салтыков-Щедрин", new Address("Большой проспект", 1, 1));
        AddressBook expected = new AddressBook();
        expected.add("Меркучева", new Address("Харченко", 16, 316));
        expected.add("Салтыков-Щедрин", new Address("Большой проспект", 1, 1));
        assertEquals(expected, actual);

        // 2 Название улицы не удовлетворяет
        assertThrows(IllegalArgumentException.class,
                () -> actual.change("Салтыков-Щедрин", new Address("Героев*Панфиловцев", 38, 2767)));

        // 3 Фамилия не содержится в книге
        assertThrows(NoSuchElementException.class,
                () -> actual.change("Забей ворота", new Address("Героев-Панфиловцев", 38, 2767)));
    }

    @Test
    void getAddress() {
        // 1 Получение адреса
        AddressBook book = new AddressBook();
        book.add("Меркучева", new Address("Харченко", 16, 316));
        book.add("Петряева", new Address("Митяшкина", 43, 1));
        book.add("Салтыков-Щедрин", new Address("Большой проспект", 1, 1));
        Address actual = book.getAddress("Салтыков-Щедрин");
        Address expected = new Address("Большой проспект", 1, 1);
        assertEquals(expected.street, actual.street);

        // 2 Фамилия не удовлетворяет
        assertThrows(IllegalArgumentException.class,
                () -> book.getAddress("Салтыков%Щедрин"));

        // 3 Фамилия не содержится в книге
        assertThrows(NoSuchElementException.class,
                () -> book.getAddress("Галиева"));
    }

    @Test
    void streetGetNames() {
        // 1
        AddressBook book = new AddressBook();
        book.add("Меркучева", new Address("Харченко", 16, 316));
        book.add("Петряева", new Address("Харченко", 15, 316));
        book.add("Салтыков-Щедрин", new Address("Большой проспект", 1, 1));
        Set<String> actual1 = book.streetGetNames("Харченко");
        Set<String> expected1 = new HashSet<>(List.of("Меркучева", "Петряева"));
        assertEquals(expected1, actual1);

        // 2 Улица не содержится в адресах
        Set<String> actual2 = book.streetGetNames("Героев-Панфиловцев");
        Set<String> expected2 = new HashSet<>();
        assertEquals(expected2, actual2);

        // 3 Название улицы не удовлетворяет
        assertThrows(IllegalArgumentException.class,
                () -> book.streetGetNames("Героев^Панфиловцев"));
    }

    @Test
    void houseGetNames() {
        // 1
        AddressBook book = new AddressBook();
        book.add("Галиева", new Address("Харченко", 16, 318));
        book.add("Петряева", new Address("Харченко", 16, 316));
        book.add("Салтыков-Щедрин", new Address("Большой проспект", 1, 1));
        Set<String> actual1 = book.houseGetNames("Харченко", 16);
        Set<String> expected1 = new HashSet<>(List.of("Галиева", "Петряева"));
        assertEquals(expected1, actual1);

        // 2 Улица не содержится в адресах
        Set<String> actual2 = book.houseGetNames("Героев-Панфиловцев", 1);
        Set<String> expected2 = new HashSet<>();
        assertEquals(expected2, actual2);

        // 3 Название улицы не удовлетворяет
        assertThrows(IllegalArgumentException.class,
                () -> book.houseGetNames("Героев^Панфиловцев", 1));
    }
}