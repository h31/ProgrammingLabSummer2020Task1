package com.example.project;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PriceListTest {

    @Test
    void addProduct() {
        PriceList productsList = new PriceList();
        productsList.addProduct(1, new NamePrice("название", 4, 6));
        assertEquals(1, productsList.size());
        productsList.addProduct(3, new NamePrice("название2", 7, 1));
        assertEquals(2, productsList.size());
        assertThrows(IllegalArgumentException.class,
                () -> productsList.addProduct(1, new NamePrice("название-", 4, 6)));
        assertThrows(IllegalArgumentException.class,
                () -> productsList.addProduct(1, new NamePrice("название", 4, 600)));
    }

    @Test
    void removeProduct() {
        PriceList productsList = new PriceList();
        productsList.addProduct(1, new NamePrice("название", 4, 6));
        productsList.addProduct(3, new NamePrice("название2", 7, 1));
        assertThrows(NoSuchElementException.class,
                () -> productsList.removeProduct(5));
        productsList.removeProduct(3);
        assertEquals(1, productsList.size());
        productsList.removeProduct(1);
        assertEquals(0, productsList.size());
    }

    @Test
    void changeName() {
        PriceList productsList = new PriceList();
        productsList.addProduct(1, new NamePrice("название", 4, 6));
        assertNotEquals(productsList.get(1).name, "название2");
        productsList.changeName(1, "название2");
        assertEquals(productsList.get(1).name, "название2");
        assertThrows(NoSuchElementException.class,
                () -> productsList.changeName(5, "название-3"));
        assertThrows(IllegalArgumentException.class,
                () -> productsList.changeName(1, "название3 -"));
    }

    @Test
    void changePrice() {
        PriceList productsList = new PriceList();
        productsList.addProduct(1, new NamePrice("название", 4, 6));
        productsList.changePrice(1, 3, 5);
        assertEquals(productsList.get(1).pricePennies, 5);
        assertThrows(NoSuchElementException.class,
                () -> productsList.changePrice(5, 1, 1));
        assertThrows(IllegalArgumentException.class,
                () -> productsList.changePrice(1, -1, 0));
    }

    @Test
    void totalCost() {
        PriceList productsList = new PriceList();
        productsList.addProduct(1, new NamePrice("название", 4, 30));
        productsList.addProduct(5, new NamePrice("название2", 9, 50));
        assertEquals(productsList.totalCost(Map.of(1, 3, 5, 4)).first * 100 +
                productsList.totalCost(Map.of(1, 3, 5, 4)).second, 5090);
        assertThrows(NoSuchElementException.class,
                () -> productsList.totalCost(Map.of(2, 3, 5, 4)));
    }
}