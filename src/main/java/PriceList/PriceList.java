package PriceList;

import javafx.util.Pair;

import java.util.HashMap;

public class PriceList {

    private HashMap<Item, Double> items = new HashMap<>();


    @SafeVarargs
    PriceList(Pair<Item, Double>... newItems) {
        for (Pair<Item, Double> element: newItems) {
            items.put(element.getKey(), element.getValue());
        }
    }

    public PriceList add(Pair<Item, Double> newItem) {
        for (Item item: items.keySet()) {
            if (item.getName().equals(newItem.getKey().getName())) throw new IllegalArgumentException();
            if (item.getId() == newItem.getKey().getId()) throw new IllegalArgumentException();
        }
        items.put(newItem.getKey(), newItem.getValue());
        return this;
    }
    public PriceList add(Item newItem, Double price) {
        for (Item item: items.keySet()) {
            if (item.getName().equals(newItem.getName())) throw new IllegalArgumentException();
            if (item.getId() == newItem.getId()) throw new IllegalArgumentException();
        }
        items.put(newItem, price);
        return this;
    }
    public PriceList add(String name,Integer id, Double price) {
        for (Item item: items.keySet()) {
            if (item.getName().equals(name)) throw new IllegalArgumentException();
            if (item.getId() == id) throw new IllegalArgumentException();
        }
        items.put(new Item(name,id), price);
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


    public void changeName(String newName) {
        name = newName;
    }

    public void changeId(int newId) {
        id = newId;
    }


}
