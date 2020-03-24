package com.example.project;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

class NamePrice {
    String name;
    int priceRubles;
    int pricePennies;

    NamePrice(String name, int rubles, int pennies) {
        this.name = name;
        this.priceRubles = rubles;
        this.pricePennies = pennies;
        if (!this.name.matches("([A-Za-zА-Яа-я\\d]+[\\s-]?[A-Za-zА-Яа-я\\d]+)+")
                || this.pricePennies<0 || this.priceRubles<0 || this.pricePennies >= 100)
            throw new IllegalArgumentException();
    }
}

class Pair {
    int first;
    int second;

    Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}

public class PriceList {

    public Map<Integer, NamePrice> priceList;

    public PriceList() {
        priceList = new HashMap<Integer, NamePrice>();
    }

    public void addProduct(Integer code, NamePrice namePrice) {
        if (priceList.put(code, namePrice) != null) throw new IllegalArgumentException(); }

    public void removeProduct(Integer code) {
        if (priceList.put(code, new NamePrice("any", 0, 0)) == null) {
            priceList.remove(code);
            throw new NoSuchElementException();
        }
        priceList.remove(code);
    }

    public void changeName(Integer code, String newName) {
        if (!priceList.containsKey(code)) throw new NoSuchElementException();
        int rubles = priceList.get(code).priceRubles;
        int pennies = priceList.get(code).pricePennies;
        this.removeProduct(code);
        this.addProduct(code, new NamePrice(newName, rubles, pennies));
    }

    public void changePrice(Integer code, Integer newPriceRubles, Integer newPricePennies) {
        if (!priceList.containsKey(code)) throw new NoSuchElementException();
        String name = priceList.get(code).name;
        this.removeProduct(code);
        this.addProduct(code, new NamePrice(name, newPriceRubles, newPricePennies));
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceList priceList1 = (PriceList) o;
        return Objects.equals(priceList, priceList1.priceList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(priceList);
    }

    @Override
    public String toString() {
        return "PriceList{" +
                "priceList=" + priceList +
                '}';
    }
}