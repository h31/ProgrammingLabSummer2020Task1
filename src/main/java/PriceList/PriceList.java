package PriceList;

import java.util.HashMap;
import java.util.Objects;

public class PriceList {

    private HashMap<Item, Double> items = new HashMap<>();
    PriceList() {
        items = new HashMap<Item, Double>();
    }

    public PriceList add(Item newItem, Double price) {
        for (Item item: items.keySet()) {
            if (item.getName().equals(newItem.getName())) throw new IllegalArgumentException();
            if (item.getId() == newItem.getId()) throw new IllegalArgumentException();
        }
        items.put(newItem, Math.floor(price*100)/100);
        return this;
    }
    public PriceList add(String name,Integer id, Double price) {
        for (Item item: items.keySet()) {
            if (item.getName().equals(name)) throw new IllegalArgumentException();
            if (item.getId() == id) throw new IllegalArgumentException();
        }
        items.put(new Item(name,id), Math.floor(price*100)/100);
        return this;
    }

    public PriceList changePrice(String name, Double newPrice) {
        for (Item item: items.keySet()) {
            if (item.getName().equals(name)) {
                items.put(item, Math.floor(newPrice*100)/100);
                return this;
            }

        }
        throw new IllegalArgumentException();

    }

    public PriceList changePrice(int id, Double newPrice) {
        for (Item item: items.keySet()) {
            if (item.getId() == id) {
                items.put(item, Math.floor(newPrice*100)/100);
                return this;
            }

        }
        throw new IllegalArgumentException();
    }

    public PriceList changeName(int id, String newName) {
        for (Item item: items.keySet()) {
            if (item.getName().equals(newName)) throw new IllegalArgumentException();
            if (item.getId() == id) {
                item.changeName(newName);
                return this;
            }

        }
        throw new IllegalArgumentException();
    }

    public PriceList changeName(String oldName, String newName) {
        for (Item item: items.keySet()) {
            if (item.getName().equals(newName)) throw new IllegalArgumentException();
            if (item.getName().equals(oldName)) {
                item.changeName(newName);
                return this;
            }

        }
        throw new IllegalArgumentException();
    }

    public PriceList remove(String name) {
        for (Item item: items.keySet()) {
            if (item.getName().equals(name)) {
                items.remove(item);
                return this;
            }

        }
        throw new IllegalArgumentException();
    }

    public PriceList remove(int id) {
        for (Item item: items.keySet()) {
            if (item.getId() == id) {
                items.remove(item);
                return this;
            }
        }
        throw new IllegalArgumentException();
    }

    public double getPrice(String name) {
        for (Item item: items.keySet()) {
            if (item.getName().equals(name)) {
                return items.get(item);
            }
        }
        throw new IllegalArgumentException();

    }

    public double getPrice(int id) {
        for (Item item: items.keySet()) {
            if (item.getId() == id) {
                return items.get(item);
            }
        }
        throw new IllegalArgumentException();
    }
    public int size() {
        return items.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceList priceList = (PriceList) o;
        return items.equals(priceList.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items);
    }
}

class Item {
    private String name;
    private Integer id;

    public Item(String newName, int newId) {
        name = newName;
        id = newId;
    }
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }


    public Item changeName(String newName) {
        name = newName;
        return this;
    }

    public Item changeId(int newId) {
        id = newId;
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return name.equals(item.name) &&
                id.equals(item.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}
