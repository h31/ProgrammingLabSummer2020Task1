package com.example.project;

import java.util.Map;
import java.util.NoSuchElementException;

class NamePrice {
    String name;
    Integer priceRubles;
    Integer pricePennies;

    NamePrice(String name, Integer rubles, Integer pennies) {
        this.name = name;
        this.priceRubles = rubles;
        this.pricePennies = pennies;
    }
}

class Pair {
    Integer first;
    Integer second;

    Pair(Integer first, Integer second) {
        this.first = first;
        this.second = second;
    }
}

class PriceList {

    Map<Integer, NamePrice> priceList;

    public PriceList(Map<Integer, NamePrice> priceList) {
        this.priceList = priceList;
    }

    public void addProduct(Integer code, NamePrice namePrice) {
        if (!namePrice.name.matches("([A-Za-zА-Яа-я\\d]+[\\s-]?[A-Za-zА-Яа-я\\d]+)+"))
            throw new IllegalArgumentException();
        if (namePrice.pricePennies >= 100) throw new IllegalArgumentException();
        priceList.put(code, namePrice);
    }

    void removeProduct(Integer code) {
        if (!priceList.containsKey(code)) throw new NoSuchElementException();
        priceList.remove(code);
    }

    public void changeName(Integer code, String name2) {
        if (!priceList.containsKey(code)) throw new NoSuchElementException();
        if (!name2.matches("([A-Za-zА-Яа-я\\d]+[\\s-]?[A-Za-zА-Яа-я\\d]+)+")) throw new IllegalArgumentException();
        Integer rubles = priceList.get(code).priceRubles;
        Integer pennies = priceList.get(code).pricePennies;
        this.removeProduct(code);
        this.addProduct(code, new NamePrice(name2, rubles, pennies));
    }

    public void changeCode(Integer code1, Integer code2) {
        if (!priceList.containsKey(code1)) throw new NoSuchElementException();
        NamePrice constantValues = priceList.get(code1);
        this.removeProduct(code1);
        this.addProduct(code2, constantValues);
    }

    public Pair totalCost(Map<Integer, Integer> input) { //input keys are codes, values are amount
        if (!priceList.keySet().containsAll(input.keySet())) throw new NoSuchElementException();
        int rubles = 0;
        int pennies = 0;
        for (Integer code : input.keySet()) {
            rubles += input.get(code) * this.get(code).priceRubles;
            pennies += input.get(code) * this.get(code).pricePennies;
        }
        rubles += pennies / 100;
        pennies %= 100;
        return new Pair(rubles, pennies);
    }

    public int size() {
        return priceList.size();
    }

    public NamePrice get(Integer code) {
        return priceList.get(code);
    }
}